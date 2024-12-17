package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager2.widget.ViewPager2;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.example.sbdfinal.adapter.TabLayoutAdapter;
import com.example.sbdfinal.models.TabModel;
import com.example.sbdfinal.snakefragments.MidVenomFragment;
import com.example.sbdfinal.snakefragments.NonVenomFragment;
import com.example.sbdfinal.snakefragments.SeaSnakesFragment;
import com.example.sbdfinal.snakefragments.VenomousFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

public class SnakeListActivity extends AppCompatActivity {

    TabLayout tablayout;
    ViewPager2 viewpager;
    TextView tooltitel;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_list);

        tablayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);
        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);

        MyAdmob.loadAdUnit();

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------

        backbtn.setOnClickListener(v ->{
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        int tabPosition = getIntent().getIntExtra("TAB_POSITION", 0);

        loadtablayout();

        viewpager.setCurrentItem(tabPosition);



        //---------- Back Button -----------
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //---------- Back Button -----------
    }


    public void loadtablayout(){

        List<TabModel> tabs = Arrays.asList(
                new TabModel("নির্বিষ সাপ", NonVenomFragment.class),
                new TabModel("মৃদু বিষধর সাপ", MidVenomFragment.class),
                new TabModel("বিষধর সাপ", VenomousFragment.class),
                new TabModel("সামুদ্রিক সাপ", SeaSnakesFragment.class)
        );

        for (TabModel tab : tabs) {
            tablayout.addTab(tablayout.newTab().setText(tab.getTitel()));
        }

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}


        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tablayout.selectTab(tablayout.getTabAt(position));
            }
        });

        TabLayoutAdapter adapter = new TabLayoutAdapter(this, tabs);
        viewpager.setAdapter(adapter);

    }





}