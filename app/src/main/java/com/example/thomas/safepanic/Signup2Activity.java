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

public class Signup2Activity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{

    TextView createTextView2;
    ImageView belowCreateText2;
    ConstraintLayout backgroundCreate2;

    EditText phoneEditText;
    Button generateOtpButton;
    Button finishButton;

    public void initialize()
    {
        createTextView2=findViewById(R.id.createTextView2);
        belowCreateText2=findViewById(R.id.belowCreateText2);
        backgroundCreate2=findViewById(R.id.backgroundCreate2);

        phoneEditText=findViewById(R.id.phoneEditText);
        generateOtpButton=findViewById(R.id.generateOtpButton);
        finishButton=findViewById(R.id.finishButton);
    }

    public void listeners()
    {
        createTextView2.setOnClickListener(this);
        belowCreateText2.setOnClickListener(this);
        backgroundCreate2.setOnClickListener(this);
        generateOtpButton.setOnClickListener(this);
        finishButton.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

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

    public void generateClicked() {
        //FUNCTION THAT LOGS IN THE USER

        hideKeyboard();
        if (isEmpty(phoneEditText)) {
            phoneEditText.setError("Cannot be empty");
        }
        else {
            Toast.makeText(getApplicationContext(),"Generated OTP",Toast.LENGTH_SHORT).show();

        }
    }

    public void finishClicked()
    {
        hideKeyboard();
        LocalStorage store=new LocalStorage(getApplicationContext());
        store.addStorage("phone",phoneEditText.getText().toString());

        Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);

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

        if(v.getId()==R.id.generateOtpButton)//checks if next button is clicked
        {
            generateClicked();
        }
        else if(v.getId()==R.id.finishButton)
        {
            finishClicked();
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
            generateClicked();
        }
        return false;
    }
}
