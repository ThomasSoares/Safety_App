package com.example.thomas.safepanic;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    public static FragmentManager mFragmentManager;
    public static FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*
        textView=findViewById(R.id.textView);

        LocalStorage retrieve=new LocalStorage(getApplicationContext());
        String firstName=retrieve.getStorage("firstName");
        String lastName=retrieve.getStorage("lastName");
        String email=retrieve.getStorage("email");
        String phone=retrieve.getStorage("phone");

        textView.setText("Name: "+firstName+" "+lastName+"\nemail: "+email+"\nphone: "+phone);
        */

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();


        Fragment fragment=new HomeFragment();
        mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        int id=menuItem.getItemId();

                        if(id==R.id.nav_home)
                        {
                            Fragment fragment=new HomeFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                        }
                        else if(id==R.id.nav_friends)
                        {
                            Fragment fragment=new FriendsFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                        }
                        else if(id==R.id.nav_profile)
                        {
                            Fragment fragment=new ProfileFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                        }
                        else if(id==R.id.nav_notifications)
                        {
                            Fragment fragment=new NotificationsFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                        }
                        else if(id==R.id.nav_groups)
                        {
                            Fragment fragment=new GroupsFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                        }
                        else if(id==R.id.nav_invite)
                        {
                            Fragment fragment=new GroupsFragment();
                            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                        }
                        else if(id==R.id.nav_logout)
                        {
                            FirebaseAuth.getInstance().signOut();

                            Toast.makeText(getApplicationContext(),"Successfully Logged Out!", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        return true;
                    }
                });
        navigationView.getMenu().getItem(0).setChecked(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
