package snowroller.myapplication.note;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import snowroller.myapplication.R;
import snowroller.myapplication.model.Note;
import snowroller.myapplication.model.NotesHandler;

//File > Settings > Editor > Font > Enable font ligatures

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText title;
    private EditText body;
    private Button saveButton;
    private Button newButton;

    private Note currentNote;

    private RecyclerView.Adapter<NotesViewHolder> adapter;

    NotesHandler notesHandler = new NotesHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        saveButton = findViewById(R.id.saveButton);
        newButton = findViewById(R.id.newButton);

        adapter = new RecyclerView.Adapter<NotesViewHolder>() {

            @Override
            public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(
                        android.R.layout.simple_list_item_1,
                        parent,
                        false);
                NotesViewHolder vh = new NotesViewHolder(v);
                return vh;
            }

            @Override
            public void onBindViewHolder(NotesViewHolder vh, int position) {
                TextView tv = (TextView) vh.itemView;
                tv.setText(notesHandler.getNotes().get(position).getTitle());
            }

            @Override
            public int getItemCount() {
                return notesHandler.getNotes().size();
            }
        };

        //All items have same size
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter
        mRecyclerView.setAdapter(adapter);
    }

    public void saveButton(View view) {
        if (currentNote != null) {
            currentNote.setTitle(title.getText().toString());
            currentNote.setBody(body.getText().toString());
            adapter.notifyDataSetChanged();
        }
    }

    public void newButton(View view) {
        currentNote = notesHandler.createNote("Title", "Message goes here");
        title.setText(currentNote.getTitle());
        body.setText(currentNote.getBody());
        adapter.notifyDataSetChanged();
    }

    private class NotesViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        public NotesViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            currentNote = notesHandler.getNote(getAdapterPosition());
            title.setText(currentNote.getTitle());
            body.setText(currentNote.getBody());
        }
    }

}

