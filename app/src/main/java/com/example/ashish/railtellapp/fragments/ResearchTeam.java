package com.example.ashish.railtellapp.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ashish.railtellapp.R;
import com.example.ashish.railtellapp.ankush;
import com.example.ashish.railtellapp.deepak;
import com.example.ashish.railtellapp.prof;
import com.example.ashish.railtellapp.shruti;
import com.example.ashish.railtellapp.tasneem;


public class ResearchTeam extends Fragment {

    public Button d1;
    public void Dharmendra(View view){
        d1=(Button) view.findViewById(R.id.d1);
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(getActivity(), prof.class);
                startActivity(detail);
            }
        });
    }
    public Button d2;
    public void Scholar1(View view){
        d2=(Button) view.findViewById(R.id.d2);
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail2=new Intent(getActivity(), tasneem.class);
                startActivity(detail2);
            }
        });
    }
    public Button d3;
    public void Scholar2(View view){
        d3=(Button) view.findViewById(R.id.d3);
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail3=new Intent(getActivity(), shruti.class);
                startActivity(detail3);
            }
        });
    }
    public Button d4;
    public void Scholar3(View view){
        d4=(Button) view.findViewById(R.id.d4);
        d4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail4=new Intent(getActivity(), ankush.class);
                startActivity(detail4);
            }
        }));
    }
    public Button d5;
    public void Scholar4(View view){
        d5=(Button) view.findViewById(R.id.d5);
        d5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail5=new Intent(getActivity(), deepak.class);
                startActivity(detail5);
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_research_team, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Dharmendra(view);
        Scholar1(view);
        Scholar2(view);
        Scholar3(view);
        Scholar4(view);
    }
}
