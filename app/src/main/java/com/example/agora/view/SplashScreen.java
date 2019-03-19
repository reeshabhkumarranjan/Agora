package com.example.agora.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.agora.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int delayDuration=500;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO if the user is logged in (loggedIn variable in Utils) then open the Dashboard instead
                Intent intent=new Intent(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },delayDuration);
    }
}
