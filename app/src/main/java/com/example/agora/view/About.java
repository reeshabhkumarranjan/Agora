package com.example.agora.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.agora.R;

import java.util.ArrayList;

public class About extends AppCompatActivity {

    private ArrayList<String> creditArrayList=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(About.this, "Please use our Gitter Channels to communicate with us.", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("https://gitter.im/aossie/home");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        creditArrayList.add("Freepik");
        creditArrayList.add("Smashicons");

        // populating credits list
        // thanks to https://stackoverflow.com/a/4540859/5394180
        ListView creditsListView=(ListView)findViewById(R.id.creditsListView);
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,creditArrayList);
        creditsListView.setAdapter(stringArrayAdapter);

        // opening link on taps
        Button learnMoreButton=(Button)findViewById(R.id.learnMoreButton);
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(About.this, "This is the repository for Agora", Toast.LENGTH_SHORT).show();
                // thanks to https://stackoverflow.com/a/4930319/5394180
                Uri uri = Uri.parse("https://gitlab.com/aossie/Agora/");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}
