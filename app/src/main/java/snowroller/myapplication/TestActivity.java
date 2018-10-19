package snowroller.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private final int ID = 1234;
    private TextView v;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;
    private List<Drawable> images= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        v = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
        imageView.setTag(0);

        //Preload images
        images.add(getResources().getDrawable(R.drawable.pic1, getTheme()));
        images.add(getResources().getDrawable(R.drawable.pic2, getTheme()));

        sharedPreferences =
                getSharedPreferences("default", MODE_PRIVATE);

        //Retrieve a key-value pair
        String text = sharedPreferences.getString("TextKey", "Default text");
        v.setText(text);

        if( savedInstanceState != null)
        {
            //Restart of activity after configuration change
            int i = savedInstanceState.getInt("currentPic");
            //imageView.setImageResource(id);
            Drawable drawable = images.get(i);
            imageView.setImageDrawable(drawable);
            imageView.setTag(i);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentPic", (Integer) imageView.getTag());
        super.onSaveInstanceState(outState);

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
//        String text = v.getText().toString();
//        Intent i = new Intent(this, EditActivity.class);
//        i.putExtra("TEXT", text);
//        startActivityForResult(i, ID);
        if( ((Integer) imageView.getTag()) == 1) {
            imageView.setImageDrawable(images.get(0));
            imageView.setTag(0);
        }
        else
        {
            imageView.setImageDrawable(images.get(1));
            imageView.setTag(1);
        }
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
