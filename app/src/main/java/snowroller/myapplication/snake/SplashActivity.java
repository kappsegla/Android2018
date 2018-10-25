package snowroller.myapplication.snake;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    //https://www.bignerdranch.com/blog/splash-screens-the-right-way/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SnakeActivity.class);
        new Handler().postDelayed( ()-> startActivity(intent),2000 );
        //finish();  //Replaced by  android:noHistory="true" in manifest
    }
}
