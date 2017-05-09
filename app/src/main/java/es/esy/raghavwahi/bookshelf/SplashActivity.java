package es.esy.raghavwahi.bookshelf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.prefs.Preferences;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        preferences = getSharedPreferences(Util.PREFS_NAME,MODE_PRIVATE);
        boolean isRegistered = preferences.contains(Util.KEY_NAME);

        if (isRegistered){
            handler.sendEmptyMessageDelayed(102,2250);
        }else {
            handler.sendEmptyMessageDelayed(101,2250);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 102){
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }else if (msg.what == 101){
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }else{

            }
        }
    };
}
