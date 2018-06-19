package com.example.hp.edume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    int time_delay = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run()
            {
                Intent i = new Intent (SplashActivity.this,LoginActivity.class) ;
                startActivity(i);
                SplashActivity.this.finish();
            }
        } , time_delay );
    }
}