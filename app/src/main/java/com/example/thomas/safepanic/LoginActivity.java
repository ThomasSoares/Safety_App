package com.example.thomas.safepanic;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{

    EditText emailEditText, passwordEditText;
    Button loginButton;
    CheckBox rememberCheckBox;
    TextView forgotTextView, loginTextView, createTextView;
    ImageView belowLoginText;
    ConstraintLayout background;
    boolean rememberMeChecked;// needed to see if user checkmarked the remember me or not

    public void initialize()
    {
        //INITIALIZING ALL VARIABLES

        emailEditText=findViewById(R.id.emailCreateEditText);
        passwordEditText=findViewById(R.id.passwordCreateEditText);
        loginButton=findViewById(R.id.loginButton);
        rememberCheckBox=findViewById(R.id.rememberCreateCheckBox);
        forgotTextView=findViewById(R.id.forgotTextView);
        loginTextView=findViewById(R.id.loginTextView);
        createTextView=findViewById(R.id.createTextView);
        belowLoginText=findViewById(R.id.belowLoginText);
        background=findViewById(R.id.background);
        rememberMeChecked=false;
    }

    public void listeners()
    {
        //ADDING REQUIRED LISTENERS

        loginButton.setOnClickListener(this);
        rememberCheckBox.setOnClickListener(this);
        forgotTextView.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
        createTextView.setOnClickListener(this);
        belowLoginText.setOnClickListener(this);
        background.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

    public void loginClicked()
    {
        //FUNCTION THAT LOGS IN THE USER

        hideKeyboard();
        if(isEmpty(emailEditText))
        {
            emailEditText.setError("Cannot be empty");
        }
        if(isEmpty(passwordEditText))
        {
            passwordEditText.setError("Cannot be empty");
        }
        else
        {
            if(rememberMeChecked)
            {
                Toast.makeText(getApplicationContext(),"Remember Login!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Login!",Toast.LENGTH_SHORT).show();
            }
            if(emailEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin"))
            {
                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
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

        if(v.getId()==R.id.loginButton)//checks if login button is clicked
        {
            loginClicked();
        }
        else if(v.getId()==R.id.forgotTextView)//checks if the forgot? button has been clicked
        {
            Toast.makeText(getApplicationContext(),"Forgot!",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId()==R.id.rememberCreateCheckBox)//checks if checkbox has been clicked
        {
            if(rememberCheckBox.isChecked())
            {
                rememberMeChecked=true;
            }
            else
            {
                rememberMeChecked=false;
            }
        }
        else if(v.getId()==R.id.createTextView)
        {
            Intent intent=new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(intent);
        }
        else if((v.getId()==R.id.background) || (v.getId()==R.id.loginTextView) || (v.getId()==R.id.belowLoginText))
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
            loginClicked();
        }

        return false;
    }
}
