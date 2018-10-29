package snowroller.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        v = findViewById(R.id.textView2);
        v.setText(text);
        Log.i("Intent", "onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.i("Intent", "onNewIntent");
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        v = findViewById(R.id.textView2);
        v.setText(text);
    }
}
