package snowroller.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class TestActivity extends AppCompatActivity {

    private final int ID = 1234;
    private TextView v;
    private ImageView imageView;
    private SharedPreferences sharedPreferences;
    //private List<Drawable> images= new ArrayList<>();
    private TypedArray pictures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        v = findViewById(R.id.textView3);
        imageView = findViewById(R.id.imageView);
        imageView.setTag(0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        Log.i("TestActivity","onCreate is running, everything looks good");

        //Preload images
        //images.add(getResources().getDrawable(R.drawable.pic1, getTheme()));
        //images.add(getResources().getDrawable(R.drawable.pic2, getTheme()));
        Resources res = getResources();
        pictures = res.obtainTypedArray(R.array.pictures);

        sharedPreferences =
                getSharedPreferences("default", MODE_PRIVATE);

        //Retrieve a key-value pair
        String text = sharedPreferences.getString("TextKey", "Default text");
        v.setText(text);

        if( savedInstanceState != null)
        {
            //Restart of activity after configuration change
            int i = savedInstanceState.getInt("currentPic");
            Drawable drawable = pictures.getDrawable(i);
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
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(TestActivity.this);
                dialog.setMessage("Vill du avsluta?")
                        .setTitle("Avsluta?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
             dialog.create().show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Save a key-value pair
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("TextKey", v.getText().toString());
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
        }
    }

    public void imageViewClicked(View view) {
        if( ((Integer) imageView.getTag()) == 1) {
            imageView.setImageDrawable(pictures.getDrawable(0));
            imageView.setTag(0);
        }
        else
        {
            imageView.setImageDrawable(pictures.getDrawable(1));
            imageView.setTag(1);
        }
    }
}
