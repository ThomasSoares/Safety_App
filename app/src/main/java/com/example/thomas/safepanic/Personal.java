package com.example.thomas.safepanic;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Personal {
    String fname;
    String lname;
    String gender;
    int age;
    String phone;

    double latitude;
    double longtitude;
    boolean emergency;

    public Personal()
    {

    }

    public Personal(String fname, String lname, String gender, int age, String phone, double latitude, double longtitude, boolean emergency)
    {
        this.fname=fname;
        this.lname=lname;
        this.gender=gender;
        this.age=age;
        this.phone=phone;
        this.longtitude=longtitude;
        this.latitude=latitude;
        this.emergency=emergency;
    }


    public void addtoDb() {

        String primaryId=phone;

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");

        //initialising with phone
        usersRef.child(primaryId);

        //descriptions
        usersRef.child(primaryId).child("description").child("fname").setValue(fname);
        usersRef.child(primaryId).child("description").child("lname").setValue(lname);
        usersRef.child(primaryId).child("description").child("gender").setValue(gender);

        //location
        usersRef.child(primaryId).child("location");
        usersRef.child(primaryId).child("location").child("latitude").setValue(latitude);
        usersRef.child(primaryId).child("location").child("longitude").setValue(longtitude);

        //emergency
        usersRef.child(primaryId).child("emergency").setValue(false);

        usersRef.child(primaryId).child("groups");
    }


}
