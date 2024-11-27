package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class SnakeListActivity extends AppCompatActivity {

    TabLayout tablayout;
    TextView myname, credittext, tooltitel;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_list);

        tablayout = findViewById(R.id.tablayout);
        myname = findViewById(R.id.myname);
        credittext = findViewById(R.id.credittext);
        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);

        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------

        backbtn.setOnClickListener(v ->{
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });



        FragmentManager fragmentManager = SnakeListActivity.this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int tabPosition = getIntent().getIntExtra("TAB_POSITION", 0);

        if (tabPosition == 0){
            tablayout.selectTab(tablayout.getTabAt(0));
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.framelayout, new NonVenomFragment());
            fragmentTransaction.commit();
        } else if (tabPosition == 1) {
            tablayout.selectTab(tablayout.getTabAt(1));
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.framelayout, new MidVenimFragment());
            fragmentTransaction.commit();
        } else if (tabPosition == 2) {
            tablayout.selectTab(tablayout.getTabAt(2));
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.framelayout, new VenomousFragment());
            fragmentTransaction.commit();
        } else if (tabPosition == 3) {
            tablayout.selectTab(tablayout.getTabAt(3));
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            fragmentTransaction.replace(R.id.framelayout, new PoisonFragment());
            fragmentTransaction.commit();
        }


        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int tabposition = tab.getPosition();

                FragmentManager fragmentManager = SnakeListActivity.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if(tabposition == 0){
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.framelayout, new NonVenomFragment());
                    fragmentTransaction.commit();
                } else if (tabposition == 1) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.framelayout, new MidVenimFragment());
                    fragmentTransaction.commit();
                } else if (tabposition == 2) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.framelayout, new VenomousFragment());
                    fragmentTransaction.commit();
                } else if (tabposition == 3) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
                    fragmentTransaction.replace(R.id.framelayout, new PoisonFragment());
                    fragmentTransaction.commit();
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //================ tab layout finish ====================

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}