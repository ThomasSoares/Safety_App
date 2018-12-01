package com.example.thomas.safepanic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OpeningActivity extends AppCompatActivity {

    CheckBox termsCheckBox;
    Button nextButon;
    boolean termsAgreed;//flag to check if the checkbox was selected or not

    private FirebaseAuth mAuth;

    public void initialize()
    {
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        }

        termsCheckBox=findViewById(R.id.termsCheckBox);
        nextButon=findViewById(R.id.nextButton);
        termsAgreed=false;
    }

    public void listeners()
    {
        //if checkbox selected then set it to true
        termsCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(termsCheckBox.isChecked())
                {
                    termsAgreed=true;
                }
                else
                {
                    termsAgreed=false;
                }
            }
        });

        //if button is clicked
        nextButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(termsAgreed!=true)
                {
                    Toast.makeText(getApplicationContext(),"Kindly accept terms and conditions", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        initialize();
        listeners();
    }
}
