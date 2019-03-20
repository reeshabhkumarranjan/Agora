package com.example.agora.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.example.agora.R;
import com.example.agora.controller.RegisterController;

public class Register extends AppCompatActivity {

    private RegisterController controller=new RegisterController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Views
                EditText usernameEditText=(EditText)findViewById(R.id.usernameEditText);
                EditText passwordEditText=(EditText)findViewById(R.id.passwordEditText);
                EditText emailEditText=(EditText)findViewById(R.id.emailEditText);
                EditText firstNameEditText=(EditText)findViewById(R.id.firstNameEditText);
                EditText lastNameEditText=(EditText)findViewById(R.id.lastNameEditText);

                // Strings extracted
                String identifier=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();
                String email=emailEditText.getText().toString();
                String firstName=firstNameEditText.getText().toString();
                String lastName=lastNameEditText.getText().toString();

                // make a register-request
                controller.requestRegister(identifier,password,email,firstName,lastName);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
