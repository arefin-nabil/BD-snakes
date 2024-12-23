package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import com.bdtopcoder.smartadmob.AdmobAd;

public class SnakeBiteDetail extends AppCompatActivity {

    TextView tooltitel, title, detail;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_bite_detail);

        title = findViewById(R.id.title);
        detail = findViewById(R.id.detail);
        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);


        MyAdmob.checkAdStatus(this);

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        // Initialize views
        tooltitel = findViewById(R.id.tooltitel);
        backbtn = findViewById(R.id.backbtn);

        // Back button click handler
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Marquee text for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);


        String stitle = getIntent().getStringExtra("title");
        String sdetail = getIntent().getStringExtra("detail");

        title.setText(stitle);
        detail.setText(sdetail);


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
}