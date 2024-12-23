package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class ImageViewActivity extends AppCompatActivity {
    PhotoView photoView;
    TextView tooltitel;
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);

        MyAdmob.checkAdStatus(this);

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------

        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


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

        photoView = findViewById(R.id.photo_view);

        // Get the image URL from the Intent
        String imageUrl = getIntent().getStringExtra("image");
        if (imageUrl != null) {
            Glide.with(this)
                    .load(imageUrl)
                    .into(photoView);
        }

    }

}