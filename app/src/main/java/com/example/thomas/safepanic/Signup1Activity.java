package com.example.thomas.safepanic;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Signup1Activity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{

    TextView createTextView1;
    ImageView belowCreateText1;
    ConstraintLayout backgroundCreate1;

    EditText emailCreateEditText, passwordCreateEditText, confirmCreatePasswordEditText;
    Button nextCreateButton1;

    public void initialize()
    {
        createTextView1=findViewById(R.id.createTextView1);
        belowCreateText1=findViewById(R.id.belowCreateText1);
        backgroundCreate1=findViewById(R.id.backgroundCreate1);

        emailCreateEditText=findViewById(R.id.emailCreateEditText);
        passwordCreateEditText=findViewById(R.id.passwordCreateEditText);
        confirmCreatePasswordEditText=findViewById(R.id.confirmCreatePasswordEditText);
        nextCreateButton1=findViewById(R.id.nextCreateButton1);
    }

    public void listeners()
    {
        createTextView1.setOnClickListener(this);
        belowCreateText1.setOnClickListener(this);
        backgroundCreate1.setOnClickListener(this);
        nextCreateButton1.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup1);

        initialize();
        listeners();
    }

    private boolean isEmpty(EditText etText)
    {
        //FUNCTION TO CHECK IF EDIT TEXT IS EMPTY

        if (etText.getText().toString().trim().length() > 0)
        {
            return false;
        }
        return true;
    }

    public void nextClicked()
    {
        //FUNCTION THAT LOGS IN THE USER

        hideKeyboard();
        if(isEmpty(emailCreateEditText))
        {
            emailCreateEditText.setError("Cannot be empty");
        }
        if(isEmpty(passwordCreateEditText))
        {
            passwordCreateEditText.setError("Cannot be empty");
        }
        if(isEmpty(confirmCreatePasswordEditText))
        {
            confirmCreatePasswordEditText.setError("Cannot be empty");
        }
        else
        {
            if(passwordCreateEditText.getText().toString().equals(confirmCreatePasswordEditText.getText().toString()))
            {//check if passwords match
                if(passwordCreateEditText.getText().toString().length()<6)
                {
                    passwordCreateEditText.setError("Password need to have 6 or more characters");
                }
                else
                {
                    LocalStorage store=new LocalStorage(getApplicationContext());
                    store.addStorage("password",passwordCreateEditText.getText().toString());
                    store.addStorage("email",emailCreateEditText.getText().toString());

                    Intent intent=new Intent(getApplicationContext(), Signup2Activity.class);
                    startActivity(intent);
                }

            }

            else
            {
                confirmCreatePasswordEditText.setError("Passwords don't match");
            }
        }

    }

    public void hideKeyboard()
    {
        //FUNCTION THAT HIDES THE KEYBOARD

        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText())
        {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

    }

    @Override
    public void onClick(View v) {
        //FUNCTION TO CHECK WHICH ITEM HAS BEEN CLICKED

        if(v.getId()==R.id.nextCreateButton1)//checks if next button is clicked
        {
            nextClicked();
        }
        else if((v.getId()==R.id.backgroundCreate1) || (v.getId()==R.id.createTextView1) || (v.getId()==R.id.belowCreateText1))
        {
            //checks if background or any other element has been clicked
            hideKeyboard();
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        //FUNCTION TO CHECK WHEN THE ENTER BUTTON OF THE KEYBOARD HAS BEEN CLICKED

        if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)
        {
            nextClicked();
        }
        return false;
    }
}
