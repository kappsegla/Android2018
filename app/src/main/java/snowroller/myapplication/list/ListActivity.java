package snowroller.myapplication.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

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

        adapter = new MyRecyclerViewAdapter(infoList);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.floatingActionButton).setOnClickListener(view -> {
            adapter.addItem(new ItemInfo(getResources().getDrawable(R.drawable.pumpa,getTheme()),"New Item","New Text"));
        });
    }
}
