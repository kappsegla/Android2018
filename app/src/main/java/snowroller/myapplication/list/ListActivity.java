package snowroller.myapplication.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import snowroller.myapplication.R;

public class ListActivity extends AppCompatActivity {

    List<ItemInfo> infoList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //Set adapter for recyclerView
        adapter = new MyRecyclerViewAdapter(infoList);
        recyclerView.setAdapter(adapter);

        //Register for change events for documents stored in collection items on firestore
        db.collection("items").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (dc.getType() == DocumentChange.Type.ADDED) {

                        String id = dc.getDocument().getId();

                        ItemInfo item = dc.getDocument().toObject(ItemInfo.class);
                        item.image = getResources().getDrawable(R.drawable.pumpa,getTheme());
                        item.id = id;
                        adapter.addItem(item);
                    }
                    else if(dc.getType() == DocumentChange.Type.REMOVED){
                        String id = dc.getDocument().getId();
                        adapter.removeItem(id);
                    }
                }
            }
        });

        //Add Swipe to delete functionality
        SwipeToDeleteCallback swipeHandler = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                //adapter.removeItem(viewHolder.getAdapterPosition());
                db.collection("items").document(adapter.getItem(viewHolder.getAdapterPosition()).id)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeHandler);
        touchHelper.attachToRecyclerView(recyclerView);


        //Floating action button, register onclick listener
        findViewById(R.id.floatingActionButton).setOnClickListener(view -> {

            // Create a new user with a first and last name
            ItemInfo info = new ItemInfo(null, "Main title","Sub title");

            // Add a new document with a generated ID
            db.collection("items")
                    .add(info)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("firebase", "Error adding document", e);
                        }
                    });
//            adapter.addItem(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"New Item","New Text"));
        });
    }
}
