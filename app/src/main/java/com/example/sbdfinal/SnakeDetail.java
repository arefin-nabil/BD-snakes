package com.example.sbdfinal;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.sbdfinal.admob.admobad;

import java.util.HashMap;

public class SnakeDetail extends AppCompatActivity {

    TextView tooltitel, textView, textView1, textView2, textView3, textView4, textView5;
    ImageView backbtn, image1, image2, image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_detail);

        tooltitel = findViewById(R.id.tooltitel);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        backbtn = findViewById(R.id.backbtn);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        LinearLayout bannerAd = findViewById(R.id.bannerAd);
        admobad.sdkinitialize(this);
        admobad.setBannerAd(bannerAd, this);

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

        // Retrieve the color passed via Intent
        String color = getIntent().getStringExtra("bgColor");
        if (color != null) {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor(color));
        }

        String snakebangname = getIntent().getStringExtra("snakebangname");
        String snakeengname = getIntent().getStringExtra("snakeengname");
        String snakesciname = getIntent().getStringExtra("snakesciname");
        String identity = getIntent().getStringExtra("identity");
        String detail = getIntent().getStringExtra("detail");
        String ending = getIntent().getStringExtra("ending");
        String image1 = getIntent().getStringExtra("image1");
        String image2 = getIntent().getStringExtra("image2");
        String image3 = getIntent().getStringExtra("image3");

        Glide.with(this).load(image1).into(this.image1);
        Glide.with(this).load(image2).into(this.image2);
        Glide.with(this).load(image3).into(this.image3);


        textView.setText(snakebangname);
        textView1.setText(snakeengname);
        textView2.setText(snakesciname);
        textView3.setText(identity);
        textView4.setText(detail);
        textView5.setText(ending);

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