package com.example.sbdfinal;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.bdtopcoder.smartadmob.AdmobAdCallBack;

public class ArticleDetailActivity extends AppCompatActivity {

    ImageView backbtn, authorimage;
    TextView tooltitel, article;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        // Initialize views
        tooltitel = findViewById(R.id.tooltitel);
        authorimage = findViewById(R.id.authorimage);
        backbtn = findViewById(R.id.backbtn);
        article = findViewById(R.id.article);

        // Back button click handler
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Marquee text for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);

        article.setText("যদি দুটি একই প্রজাতির সাপকে এক জায়গায় আটকে রাখা হয় এবং খাবার ছাড়া অনেক দিন রাখা হয়, তবে তাদের আচরণ কী হবে তা নির্ভর করবে সাপের প্রজাতি, তাদের শারীরিক অবস্থা এবং তাদের স্বাভাবিক আচরণের ওপর। তবে কিছু সাপের মধ্যে সত্যিই একে অপরকে খেয়ে ফেলার প্রবণতা দেখা যায়, বিশেষ করে যখন তাদের খাবারের অভাব হয় এবং তারা চরম ক্ষুধার্ত হয়ে পড়ে।\n" +
                "কিছু সাপ প্রজাতি, বিশেষ করে যারা মাংসাশী বা নিখুঁত শিকারী, যেমন কিং কোবরা, কখনো কখনো নিজের প্রজাতির অন্য সাপকেও শিকার হিসেবে বিবেচনা করতে পারে। এমনকি কিছু সাপ স্বাভাবিক অবস্থায়ও অন্য সাপকে শিকার হিসেবে খায়। সবচেয়ে বড় উদাহরণ হিসেবে আছে শঙ্খিনী।\n" +
                "তবে, সব সাপ প্রজাতির মধ্যে এই ক্যানিবালিজম দেখা যায় না।\n" +
                "যদি তাদের একটানা দীর্ঘ সময় খাবার না দেওয়া হয়, তবে চরম ক্ষুধার কারণে তারা একে অপরের প্রতি আগ্রাসী হতে পারে। এ ক্ষেত্রে তুলনামূলকভাবে শক্তিশালী বা বড় সাপটি দুর্বল সাপটির ওপর আক্রমণ করতে পারে এবং শিকারের অভাবে তাকে খেয়ে ফেলতে পারে। তবে এমন ঘটনা বেশ বিরল এবং একে সাপের স্বাভাবিক আচরণ হিসেবে বিবেচনা করা যায় না, এটি মূলত তাদের খাদ্য সংকটের প্রতিক্রিয়ায় ঘটে।\n" +
                "সাপেরা সাধারণত শিকার ধরার আগে হজম এবং শক্তির সংরক্ষণ প্রক্রিয়ার ওপর নির্ভর করে। তারা দীর্ঘ সময় না খেয়ে বেঁচে থাকতে পারে, কারণ তাদের শরীর অনেক ধীরে শক্তি খরচ করে। তবে, যখন সেই সীমা অতিক্রম করে তাদের শরীর চরম শক্তি সংকটে পড়ে, তখন হয়তো তারা চরম ব্যবস্থা হিসেবে নিজেদের প্রজাতির সাপকেও খাবার হিসেবে ভাবতে পারে।\n" +
                "তবে মনে রাখা গুরুত্বপূর্ণ, সাপের মধ্যে ক্যানিবালিজম খুবই ব্যতিক্রমী এবং বিরল ঘটনা এবং মূলত এটি প্রজাতিগত বৈচিত্র্যের কারণে ঘটে। সব সাপ একে অপরকে খেয়ে ফেলার চেষ্টা করবে না।");

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}