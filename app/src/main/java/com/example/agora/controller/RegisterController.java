package com.example.agora.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.agora.model.Utils;
import com.example.agora.view.LoginActivity;

public final class RegisterController extends Notifiable {

    public RegisterController(Context context) {
        super(context);
    }

    public void requestRegister(String identifier, String password, String email, String firstName, String lastName){

        if(TextUtils.isEmpty(identifier) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)){
            Toast.makeText(context, "Please fill in all the details!", Toast.LENGTH_SHORT).show();
        }
        // TODO match email textfield with an email regular expression
        else{
            Utils.getInstance().registerRequest(identifier,password,email,firstName,lastName,this);
        }
    }

    @Override
    public void notify(Boolean successful, String message) {
        if(successful){
            /*
            show a toast message regarding the email instructions, and open login screen
             */
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            // Start login activity
            Intent intent=new Intent(context,LoginActivity.class);
            context.startActivity(intent);
        }

        else{
            /*
            show an error toast message because user already exists
             */
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
