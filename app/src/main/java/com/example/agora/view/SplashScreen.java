package com.example.agora.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.agora.R;
import com.example.agora.model.Utils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        Initiate the sharedPreferences object in Utils class and load user-data
         */
        SharedPreferences sharedPreferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        Utils.setSharedPreferences(sharedPreferences);
        Utils.getInstance().loadUserData();

        // continue with the splash screen logic

        int delayDuration=500;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO if the user is logged in (loggedIn variable in Utils) then open the Dashboard instead

                if(Utils.getInstance().isLoggedIn()){
                    Intent intent=new Intent(SplashScreen.this,Dashboard.class);
                    startActivity(intent);
                }
                else{
                    Intent intent=new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                }

                finish();
            }
        },delayDuration);
    }
}
