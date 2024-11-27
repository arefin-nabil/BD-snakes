package com.example.sbdfinal;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerid;
    MaterialToolbar Mtoolbarid;
    TextView tooltitel;
    BottomNavigationView bottomnav;
    NavigationView navigationid;

    long backPressedTime;
    Toast backPressedToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //====================== ID will define here ========================

        tooltitel = findViewById(R.id.tooltitel);
        Mtoolbarid = findViewById(R.id.Mtoolbarid);
        drawerid = findViewById(R.id.drawerid);
        bottomnav = findViewById(R.id.bottomnav);
        navigationid = findViewById(R.id.navigationid);

        //====================== ID will define here ========================


        // Setup ActionBarDrawerToggle
        setSupportActionBar(Mtoolbarid);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                HomeActivity.this, drawerid, Mtoolbarid, R.string.open, R.string.close);
        drawerid.addDrawerListener(toggle);
        //built in menu icon
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_ATOP);


    }






}

