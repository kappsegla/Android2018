package snowroller.myapplication.note;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import snowroller.myapplication.R;

public class SecondActivity extends AppCompatActivity {

    private TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String text = intent.getStringExtra("MESSAGE");
        v = findViewById(R.id.textView2);
        v.setText(text);
    }
}
