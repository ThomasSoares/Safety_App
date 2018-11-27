package com.example.thomas.safepanic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView=findViewById(R.id.textView);

        LocalStorage retrieve=new LocalStorage(getApplicationContext());
        String firstName=retrieve.getStorage("firstName");
        String lastName=retrieve.getStorage("lastName");
        String email=retrieve.getStorage("email");
        String phone=retrieve.getStorage("phone");

        textView.setText("Name: "+firstName+" "+lastName+"\nemail: "+email+"\nphone: "+phone);
    }
}
