package snowroller.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        editText = findViewById(R.id.editText);
    }

    public void sendButtonClicked(View view) {
        Intent intent = new Intent(this, SecondActivity.class);

        String text = editText.getText().toString();
        intent.putExtra("MESSAGE", text);

        startActivity(intent);
    }
}
