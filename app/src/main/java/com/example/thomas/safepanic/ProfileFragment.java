package com.example.thomas.safepanic;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    View parentHolder;
    TextView nameTextView, emailTextView, phoneTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder= inflater.inflate(R.layout.fragment_profile, container, false);

        nameTextView=parentHolder.findViewById(R.id.nameTextView);
        emailTextView=parentHolder.findViewById(R.id.emailTextView);
        phoneTextView=parentHolder.findViewById(R.id.phoneTextView);

        LocalStorage obj=new LocalStorage(getContext());

        nameTextView.setText(obj.getStorage("firstName")+" "+obj.getStorage("lastName"));
        emailTextView.setText(obj.getStorage("email"));
        phoneTextView.setText(obj.getStorage("phone"));

        return parentHolder;
    }


}
