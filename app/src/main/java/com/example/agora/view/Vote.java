package com.example.agora.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.agora.R;
import com.example.agora.model.Candidate;
import com.example.agora.model.Utils;

import java.util.ArrayList;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        getSupportActionBar().setTitle("Vote");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Candidate> candidateArrayList= Utils.getInstance().getCandidateArrayList();
        CandidateAdapter candidateAdapter=new CandidateAdapter(this,R.layout.candidate_list_item,candidateArrayList);
        ListView candidateListView=(ListView)findViewById(R.id.candidateListView);
        candidateListView.setAdapter(candidateAdapter);
        /*
        Necessary verbose
         */
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
