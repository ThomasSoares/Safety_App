package com.example.thomas.safepanic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    View parentHolder;
    Button helpButton;
    TextView textView;

    public void listeners()
    {
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(parentHolder.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder=inflater.inflate(R.layout.fragment_home, container, false);

        helpButton=parentHolder.findViewById(R.id.helpButton);
        textView=parentHolder.findViewById(R.id.textView);

        listeners();

        return parentHolder;
    }


}
