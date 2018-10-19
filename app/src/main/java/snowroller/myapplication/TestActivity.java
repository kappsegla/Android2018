package snowroller.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {

    private final int ID = 1234;
    private TextView v;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        v = findViewById(R.id.textView3);

        sharedPreferences =
                getSharedPreferences("default", MODE_PRIVATE);

        //Retrieve a key-value pair
        String text = sharedPreferences.getString("TextKey", "Default text");
        v.setText(text);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //Save a key-value pair
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TextKey", v.getText().toString());
        editor.remove("TextKey");
        editor.commit();
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
        if (requestCode == ID && data != null) {
            String answer = data.getStringExtra("ANSWER");
            v.setText(answer);
//            //Save a key-value pair
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putString("TextKey", answer);
//            editor.commit();
        }
    }
}
