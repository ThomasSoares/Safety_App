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
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{

    TextView signUpTextView;
    ImageView belowCreateImageView;
    ConstraintLayout backgroundCreate;

    EditText firstNameCreateEditText, lastNameCreateEditText;
    Button nextCreateButton;

    public void initialize()
    {
        signUpTextView=findViewById(R.id.createTextView);
        belowCreateImageView=findViewById(R.id.belowCreateText);
        backgroundCreate=findViewById(R.id.backgroundCreate);

        firstNameCreateEditText=findViewById(R.id.firstNameEditText);
        lastNameCreateEditText=findViewById(R.id.lastNameEditText);
        nextCreateButton=findViewById(R.id.nextCreateButton);
    }

    public void listeners()
    {
        signUpTextView.setOnClickListener(this);
        belowCreateImageView.setOnClickListener(this);
        backgroundCreate.setOnClickListener(this);
        nextCreateButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
        if(isEmpty(firstNameCreateEditText))
        {
            firstNameCreateEditText.setError("Cannot be empty");
        }
        if(isEmpty(lastNameCreateEditText))
        {
            lastNameCreateEditText.setError("Cannot be empty");
        }
        else
        {
            LocalStorage store=new LocalStorage(getApplicationContext());
            store.addStorage("firstName",firstNameCreateEditText.getText().toString());
            store.addStorage("lastName",lastNameCreateEditText.getText().toString());

            Intent intent=new Intent(getApplicationContext(), Signup1Activity.class);
            startActivity(intent);
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

        if(v.getId()==R.id.nextCreateButton)//checks if next button is clicked
        {
            nextClicked();
        }
        else if((v.getId()==R.id.backgroundCreate) || (v.getId()==R.id.createTextView) || (v.getId()==R.id.belowCreateText))
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
