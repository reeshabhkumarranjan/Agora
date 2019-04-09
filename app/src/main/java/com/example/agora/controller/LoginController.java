package com.example.agora.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.agora.model.Utils;
import com.example.agora.view.Dashboard;

public final class LoginController extends Notifiable  {

    public LoginController(Context context) {
        super(context);
    }

    public void requestLogin(String username, String password){

        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            Toast.makeText(context, "Fill in something first!", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(username)){
            Toast.makeText(context, "Fill in the username!", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(context, "Fill in the password!", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(context, "Please wait", Toast.LENGTH_SHORT).show();
            Utils.getInstance().signInRequest(username,password,this);
        }
    }

    @Override
    public void notify(Boolean successful, String message) {
        if(successful){
            Intent intent=new Intent(context, Dashboard.class);
            context.startActivity(intent);
            ((Activity)context).finish(); // So that pressing back on Dashboard screen doesn't bring the app back to login-screen
        }
        else{
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
