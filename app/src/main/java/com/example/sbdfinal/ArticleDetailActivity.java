package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.sbdfinal.admob.admobad;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView backbtn, authorimage, coverimg;
    TextView tooltitel, text1, text2, articletitel, authordes, authorname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        LinearLayout bannerAd = findViewById(R.id.bannerAd);
        admobad.sdkinitialize(this);
        admobad.setBannerAd(bannerAd, this);

        // Initialize views
        tooltitel = findViewById(R.id.tooltitel);
        authorimage = findViewById(R.id.authorimage);
        backbtn = findViewById(R.id.backbtn);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        articletitel = findViewById(R.id.articletitel);
        authordes = findViewById(R.id.authordes);
        authorname = findViewById(R.id.authorname);
        coverimg = findViewById(R.id.coverimg);


        // Back button click handler
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Marquee text for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);

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


        String coverimg = getIntent().getStringExtra("coverimg");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String authorimg = getIntent().getStringExtra("authorimg");
        String authordesc = getIntent().getStringExtra("authordesc");
        String text1 = getIntent().getStringExtra("text1");
        String text2 = getIntent().getStringExtra("text2");


        Glide.with(this).load(coverimg).into(this.coverimg);
        Glide.with(this).load(authorimg).circleCrop().into(this.authorimage);

        articletitel.setText(title);
        authorname.setText(author);
        authordes.setText(authordesc);
        this.text1.setText(text1);
        this.text2.setText(text2);


    }


}