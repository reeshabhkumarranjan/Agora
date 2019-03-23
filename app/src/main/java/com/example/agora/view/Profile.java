package com.example.agora.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agora.R;
import com.example.agora.model.Utils;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.profileOptionsFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile.this,EditProfile.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Loading user data
         */

        // Loading profile picture
        ConstraintLayout headerConstraintLayout=(ConstraintLayout)findViewById(R.id.headerConstraintLayout);
        CircleImageView profileCircleImageView=(CircleImageView)headerConstraintLayout.findViewById(R.id.profileCircleImageView);
        Picasso.get().load(Utils.getInstance().getAvatarURL()).into(profileCircleImageView);

        // Loading full name
        TextView fullNameTextView=(TextView)findViewById(R.id.fullNameTextView);
        fullNameTextView.setText(Utils.getInstance().getFirstName()+" "+Utils.getInstance().getLastName());

        // Loading username
        TextView usernameTextView=(TextView)findViewById(R.id.usernameTextView);
        usernameTextView.setText(Utils.getInstance().getUsername());

        // Loading email
        TextView emailTextView=(TextView)findViewById(R.id.emailTextView);
        emailTextView.setText(Utils.getInstance().getEmail());
    }

}
