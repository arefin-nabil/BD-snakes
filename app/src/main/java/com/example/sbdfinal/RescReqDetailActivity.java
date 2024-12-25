package com.example.sbdfinal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class RescReqDetailActivity extends AppCompatActivity {
    TextView tooltitel, name, detail;
    ImageView backbtn, image, poster;
    AppCompatButton fbpg, fbgrp, regbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resc_req_detail);

        tooltitel = findViewById(R.id.tooltitel);
        backbtn = findViewById(R.id.backbtn);
        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        detail = findViewById(R.id.detail);
        poster = findViewById(R.id.poster);
        fbpg = findViewById(R.id.fbpg);
        fbgrp = findViewById(R.id.fbgrp);
        regbtn = findViewById(R.id.regbtn);

        // Setup marquee for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);

        // Back button handling
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Handle back press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        String image = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        String poster = getIntent().getStringExtra("poster");
        String detail = getIntent().getStringExtra("detail");
        String regUrl = getIntent().getStringExtra("regUrl");
        String fbpg = getIntent().getStringExtra("fbpg");
        String fbgrp = getIntent().getStringExtra("fbgrp");

        this.name.setText(name);
        this.detail.setText(detail);

        Glide.with(this).load(image).into(this.image);
        Glide.with(this).load(poster).into(this.poster);

        this.fbpg.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"+fbpg));
            startActivity(intent);
        });
        this.fbgrp.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/"+fbgrp));
            startActivity(intent);
        });
        this.regbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(regUrl));
            startActivity(intent);
        });




    }
}