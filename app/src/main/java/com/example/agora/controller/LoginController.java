package com.example.agora.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.agora.model.Utils;
import com.example.agora.view.Dashboard;

public final class LoginController extends Notifiable  {

    public LoginController(Context context) {
        super(context);
    }

    public void requestLogin(String username, String password){
        Utils.getInstance().signInRequest(username,password,this);
    }

    @Override
    public void notify(Boolean successful, String message) {
        if(successful){
            Intent intent=new Intent(context, Dashboard.class);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
