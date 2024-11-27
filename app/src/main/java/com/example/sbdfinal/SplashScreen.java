package com.example.sbdfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        // Find your views
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);

        // Load animations
        Animation slideTopToBottom = AnimationUtils.loadAnimation(this, R.anim.slide_top_to_bottom);

        // Start animations
        imageView.startAnimation(slideTopToBottom);
        textView.startAnimation(slideTopToBottom);



//=============== handle splash screen ==========================
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                startActivity(intent);
                finish();
                //slide in, out animation for activity
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, 2750);
//=============== handle splash screen ==========================

    }


}