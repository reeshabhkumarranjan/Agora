package com.example.agora.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.agora.R;
import com.example.agora.controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    LoginController loginController=new LoginController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signInButton=findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditTextView=(EditText)findViewById(R.id.usernameEditText);
                EditText passwordEditTextView=(EditText)findViewById(R.id.passwordEditTextView);
                String username=usernameEditTextView.getText().toString();
                String password=passwordEditTextView.getText().toString();
                loginController.requestLogin(username,password);
            }
        });

        Button registerButton=(Button)findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }
}
