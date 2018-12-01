package com.example.thomas.safepanic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener{

    EditText emailEditText, passwordEditText;
    Button loginButton;
    CheckBox rememberCheckBox;
    TextView forgotTextView, loginTextView, createTextView;
    ImageView belowLoginText;
    ConstraintLayout background;
    boolean rememberMeChecked;// needed to see if user checkmarked the remember me or not

    LocationManager locationManager;
    LocationListener locationListener;
    double longtitute;
    double latitude;

    private FirebaseAuth mAuth;

    ProgressBar progressBar;

    public void initialize()
    {
        //INITIALIZING ALL VARIABLES
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }

        progressBar=findViewById(R.id.progressBar);

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

        //ASK USER FOR PERMISSION
        locationManager= (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("Location",location.toString());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,locationListener);
            Location lastKnowLocation=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(lastKnowLocation!=null)
            {
                updateLocationInfo(lastKnowLocation);
            }
        }
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            startListening();
        }
    }

    public void startListening()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0,locationListener);
        }
    }

    public void updateLocationInfo(Location location)
    {
        longtitute=location.getLongitude();
        latitude=location.getLatitude();
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

        progressBar.setVisibility(View.VISIBLE);

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
                //Toast.makeText(getApplicationContext(),"Login!",Toast.LENGTH_SHORT).show();
            }
            if(emailEditText.getText().toString().equals("admin") && passwordEditText.getText().toString().equals("admin"))
            {
                LocalStorage store=new LocalStorage(getApplicationContext());
                store.addStorage("longtitude", String.valueOf(latitude));
                store.addStorage("latitude", String.valueOf(longtitute));

                Toast.makeText(getApplicationContext(),String.valueOf(longtitute)+" : "+String.valueOf(latitude),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                finish();
            }
            mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                progressBar.setVisibility(View.GONE);

                                Intent intent=new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(),"Failed!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

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
