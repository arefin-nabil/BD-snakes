package com.example.sbdfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HospitalActivity extends AppCompatActivity {

    TextView tooltitel;
    ImageView backbtn;
    CardView medicalcollage, zelasadar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);
        medicalcollage = findViewById(R.id.medicalcollage);
        zelasadar = findViewById(R.id.zelasadar);

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

        medicalcollage.setOnClickListener(v -> {
            Intent intent = new Intent(HospitalActivity.this, HospitalListActivity.class);
            intent.putExtra("url", "https://www.arefinnabil.site/medicalclg.json");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


        zelasadar.setOnClickListener(v -> {
            Intent intent = new Intent(HospitalActivity.this, HospitalListActivity.class);
            intent.putExtra("url", "https://www.arefinnabil.site/zelasadarhosp.json");
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });


    }

}