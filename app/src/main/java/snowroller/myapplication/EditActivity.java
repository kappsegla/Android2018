package snowroller.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = findViewById(R.id.editText2);
        Intent intent = getIntent();
        String text = intent.getStringExtra("TEXT");
        editText.setText(text);
    }

    public void doneButtonClicked(View view) {
        Intent answer = new Intent();
        answer.putExtra("ANSWER", editText.getText().toString());

        setResult(0, answer);
        finish();
    }
}
