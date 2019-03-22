package com.example.agora.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.agora.R;
import com.example.agora.model.Candidate;

import java.util.List;

public final class CandidateAdapter extends ArrayAdapter {
    public CandidateAdapter(Context context, int resource, int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Candidate candidate= (Candidate) getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.candidate_list_item,parent,false);
        }

        final TextView candidateNameTextView=(TextView)convertView.findViewById(R.id.candidateNameTextView);
        TextView candidateScoreTextView=(TextView)convertView.findViewById(R.id.candidateScoreTextView);
        SeekBar candidateScoreSeekBar=(SeekBar)convertView.findViewById(R.id.candidateScoreSeekBar);

        candidateNameTextView.setText(candidate.getName());
        candidateScoreTextView.setText(""+candidate.getScore());
        candidateScoreSeekBar.setProgress(candidate.getScore());
        candidateScoreSeekBar.setMax(100);

        candidateScoreSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    candidate.setScore(progress);
                }
                candidateNameTextView.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return convertView;
    }


}
