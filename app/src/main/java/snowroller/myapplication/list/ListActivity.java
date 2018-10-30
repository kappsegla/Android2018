package snowroller.myapplication.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import snowroller.myapplication.R;

public class ListActivity extends AppCompatActivity {

    List<ItemInfo> infoList = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        infoList.add(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"Item1","Welcome to the jungle"));
        infoList.add(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"Item2","Welcome to town"));
        infoList.add(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"Item3","Welcome to the forest"));
        infoList.add(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"Item4","Welcome to hell"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //Set adapter for recyclerView
        adapter = new MyRecyclerViewAdapter(infoList);
        recyclerView.setAdapter(adapter);

        //Add Swipe to delete functionality
        SwipeToDeleteCallback swipeHandler = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                adapter.removeItem(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper touchHelper = new ItemTouchHelper(swipeHandler);
        touchHelper.attachToRecyclerView(recyclerView);


        //Floating action button, register onclick listener
        findViewById(R.id.floatingActionButton).setOnClickListener(view -> {
            adapter.addItem(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"New Item","New Text"));
        });
    }
}
