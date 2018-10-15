package snowroller.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private final int ID = 1234;
    private TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        v = findViewById(R.id.textView3);
    }

    public void editButtonClicked(View view) {
        String text = v.getText().toString();
        Intent i = new Intent(this, EditActivity.class);
        i.putExtra("TEXT", text);

        startActivityForResult(i, ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == ID)
        {
            String answer = data.getStringExtra("ANSWER");
            v.setText(answer);
        }
    }
}
