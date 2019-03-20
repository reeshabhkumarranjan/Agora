package com.example.agora.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.agora.model.Utils;
import com.example.agora.view.LoginActivity;

public final class LogoutController extends Notifiable {
    public LogoutController(Context context) {
        super(context);
    }

    @Override
    public void notify(Boolean successful, String message) {
        // This does not require any notification as no Async task will be created.
        // requestLogout will return only after a logout.
    }

    public void requestLogout(){
        Utils.getInstance().logoutRequest();
        Intent intent=new Intent(context,LoginActivity.class);
        context.startActivity(intent);
        ((Activity)context).finish(); // So that pressing back on login screen doesn't bring back to dashboard
    }
}
