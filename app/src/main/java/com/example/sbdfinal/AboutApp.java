package com.example.sbdfinal;

import android.os.Bundle;
import android.text.Html;
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

public class AboutApp extends AppCompatActivity {

    TextView myname, credittext, tooltitel;
    ImageView backbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);


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




        //--------------------- HTML text for About App ------------------------------------
        String formattedText = "<b>নূরুল আরেফিন নাবিল</b><br>সদস্য, <i>Wildlife And Snake Rescue Team In Bangladesh (WSRTBD)</i>";
        myname.setText(Html.fromHtml(formattedText, Html.FROM_HTML_MODE_LEGACY));


        String formattedText1 = "অ্যাপটিতে ব্যবহৃত অধিকাংশ তথ্যই ইন্টারনেট এবং দেশ-বিদেশের স্বনামধন্য বন্যপ্রাণী বিশেষজ্ঞদের সহযোগিতায় সংগ্রহ করা হয়েছে। কিছু তথ্য আন্তর্জাতিক জার্নাল থেকে অনুবাদ করে যুক্ত করা হয়েছে।<br><br>"
                + "বিশেষ কৃতজ্ঞতা <i>Wildlife And Snake Rescue Team In Bangladesh (WSRTBD)</i>-এর সকল সদস্যদের প্রতি।<br><br>"
                + "অধিকাংশ ছবি সংগৃহীত হয়েছে <i>Wildlife And Snake Rescue Team In Bangladesh</i> এর ফেসবুক পেজ ও গ্রুপ থেকে এবং কিছু ছবি অন্যান্য ওয়েবসাইট থেকেও নেওয়া হয়েছে।";
        credittext.setText(Html.fromHtml(formattedText1, Html.FROM_HTML_MODE_LEGACY));
        //--------------------- HTML text for About App ------------------------------------




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