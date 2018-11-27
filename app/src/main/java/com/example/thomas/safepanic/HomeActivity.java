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

        LocalStorage store=new LocalStorage(getApplicationContext());
        String firstName=store.getStorage("firstName");
        String lastName=store.getStorage("lastName");
        String email=store.getStorage("email");
        String phone=store.getStorage("phone");

        textView.setText("Name: "+firstName+" "+lastName+"\nemail: "+email+"\nphone: "+phone);
    }
}
