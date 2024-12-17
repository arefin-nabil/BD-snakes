package com.example.sbdfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.bdtopcoder.smartadmob.AdmobAdCallBack;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class SnakeBiteList extends AppCompatActivity {
    TextView tooltitel;
    ImageView backbtn;
    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_bite);

        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);
        recyclerView = findViewById(R.id.recyclerView);

        MyAdmob.loadAdUnit();

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        admobAd.initializeAdmobAd();
        admobAd.loadAdmobInterstitialAd();
        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------

        backbtn.setOnClickListener(v ->{
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


        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(SnakeBiteList.this));
        recyclerView.setAdapter(new SnakeBiteList.myAdapter());


        SnakeBiteList.myAdapter adapter = new SnakeBiteList.myAdapter();
        recyclerView.setAdapter(adapter);

        //Hashmap data function
        hashMapdata();


    }

    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){
        hashMap = new HashMap<>();
        hashMap.put("title", "বিষের ধরন");
        hashMap.put("detail", "সাপের বিষ ভেনম (Venom) নামে পরিচিত। এটি স্বচ্ছ, আঠালো, চটচটে হালকা হলুদ বা খড় রংয়ের প্রোটিনধর্মী তরল পদার্থ। বিষক্রিয়ার উপর ভিত্তি করে সাপের বিষ প্রধানত দুই ধরনের-\n" +
                "১. নিউরোটক্সিন। যেমনঃ- গোখরা, কেউটে, কালাচ প্রজাতির সাপের বিষ\n" +
                "২. হিমোটক্সিন। যেমনঃ- চন্দ্রবোড়া, গ্রীন পিট ভাইপার প্রজাতির সাপের বিষ। তাছাড়াও সাপের বিষে সাইটোটক্সিন ও মায়োটক্সিন ধরনের বিষও পাওয়া যায়।\n\n"+
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "নিউরোটক্সিন বিষের লক্ষণ");
        hashMap.put("detail","গোখরা, কেউটে, কালাচ প্রভৃতি প্রজাতির সাপের বিষ নিউরোটক্সিন বা স্নায়ুবিনাশী। নিউরোটক্সিন বিষের প্রধান প্রধান লক্ষণসমূহ হলোঃ\n" +
                "১. ক্ষত স্থান ফুলে ওঠে এবং হালকা ব্যথা অনুভূত হয়;\n" +
                "২. প্রচন্ড শ্বাসকষ্ট হয়, চোখে ঝাপসা দেখা, ঘুম ঘুম ভাব ও চোখেরপাতা বন্ধ হয়ে আসে;\n" +
                "৩. হাত-পা অবশ হয়ে যায় ও অচেতন হয়ে পড়ে, ঘাড় সোজা রাখতে পারে না, প্রস্রাব বন্ধ হয়ে যায়;\n" +
                "৪. বমি বমিভাব বা বমি হওয়া;\n" +
                "৫. মুখ দিয়ে লালা ঝরে, খাবার ও ঢোক গিলতে অসুবিধা হয় প্রভৃতি;\n" +
                "৬. তবে অনেক সময় কালাচ প্রজাতির সাপের দংশনে কোন প্রকার ব্যাথা অনুভূত হয়না এবং দংশনের চিহ্ন থাকে না।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "হিমোটক্সিন বিষের লক্ষণ");
        hashMap.put("detail", "চন্দ্রবোড়া, গ্রীন পিট ভাইপারসহ অন্যান্য ভাইপার প্রজাতি সাপের বিষ হিমোটক্সিন যা, রক্ত জমাট বাধা প্রক্রিয়ায় বাধা প্রদান করে। হিমোটক্সিন বিষের প্রধান প্রধান লক্ষণসমূহ হলোঃ\n" +
                "১. ক্ষতস্থান থেকে অনবরত রক্তপাত এবং অস্বাভাবিকভাবে ফুলে ওঠা ও ফোস্কা পড়া;\n" +
                "২. ক্ষতস্থানে প্রচণ্ড ব্যথা অনুভূত হওয়া, কখনো কখনো সারা শরীর ফুলে যাওয়া;\n" +
                "৩. চোখ লাল হয়ে যায় এবং মুখ, নাক, প্রস্রাব, মল ও দাতেঁর গোড়া দিয়ে রক্ত বের হয়;\n" +
                "৪. শরীরের অভ্যন্তরে রক্তক্ষরণের ফলে রোগী অজ্ঞান হয়ে যেতে পারে;\n" +
                "৫. পেটে তীব্র ব্যথা, হৎপিন্ডের স্পন্দন দূর্বল হয়ে যায় এবং কিডনির কার্যকারীতা বিকল হয়ে রোগীর মৃত্যু হয়।\n\n"+
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "সর্প দংশন প্রতিরোধে করণীয়");
        hashMap.put("detail", "আমাদের দেশে সাধারণত গ্রামাঞ্চলে মানুষ বেশি সৰ্প দংশনের শিকার হয়। সাপ বন জঙ্গল ছাড়াও বাড়িঘরের আশেপাশে থাকে। সর্প দংশন প্রতিরোধ করার উপায়সমূহঃ\n" +
                "১. সাপ নিয়ে নাড়াচড়া বা খেলাধুলা করা যাবে না;\n" +
                "২. সাপ থাকতে পারে এমন জায়গা যেমন- ঘন ঘাস বা ঝোপঝাড়, ইট বা পাথরের ফাঁক, ইঁদুর বা কোন প্রকার গর্ত ইত্যাদি এড়িয়ে চলতে হবে;\n" +
                "৩. জঙ্গল বা ঝোপঝাড়ে যেতে হলে অবশ্যই লম্বা লাঠি ব্যবহার করতে হবে;\n" +
                "৪. রাতে চলাফেরার সময় টর্চ লাইট বা অন্য কোনো বাতি ব্যবহার করতে হবে;\n" +
                "৫. বাড়ির চারপাশ পরিষ্কার পরিচ্ছন্ন রাখতে হবে যাতে সাপ লুকিয়ে থাকতে না পারে। প্রয়োজনে ঘরের চারপাশে ব্লিচিং পাউডার ব্যবহার করতে হবে যাতে পোকামাকড়, ইঁদুরসহ অন্যান্য প্রাণী ঘরে প্রবেশ করতে না পারে;\n" +
                "৬. রাতে মাটিতে ঘুমানো যাবে না;\n" +
                "৭. রাতে ঘুমানোর সময় অবশ্যই মশারি ব্যবহার করতে হবে ও বিছানার চারপাশে ভালভাবে গুঁজে দিতে হবে;\n" +
                "৮. ঘরবাড়ি ইঁদুর মুক্ত রাখতে হবে;\n" +
                "৯. বাড়ির ভেতর-বাহিরে কোন গর্ত থাকলে তা ভরাট করে দিতে হবে;" +
                "১০. মাছ ধরার সময় ‘চাই’ কিংবা ‘জালের' মধ্যে হাত দেওয়ার আগে সাপ আছে কি-না তা দেখে নিতে হবে;\n" +
                "১১. খালি পায়ে চলাচল না করা;\n" +
                "১২. কৃষি কাজ করার সময় প্রয়োজনে গামবুট ব্যবহার করতে হবে;\n" +
                "১৩. সাপ দেখা মাত্রই অহেতুক মারতে যাওয়া যাবে না ইত্যাদি।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "সর্প দংশনের প্রাথমিক চিকিৎসা");
        hashMap.put("detail", "১. রোগীকে মানসিকভাবে শান্ত রাখা;\n" +
                "২. ক্ষতস্থান পরিস্কার পানি দিয়ে পরিস্কার করা;\n" +
                "৩. প্রযোজ্য ক্ষেত্রে সর্প দংশিত স্থানের দুই আঙ্গুল উপরে ক্রেপ ব্যান্ডেজ/তিন ইঞ্চি শাড়ির পাড়/গামছা দিয়ে হালকা করে বাধঁতে হবে, যাতে হৎপিন্ডের দিকে রক্তের প্রবাহের গতি কমে যায়;\n" +
                "৪. বাঁধন যেন কোন অবস্থাতেই খুব বেশী শক্ত না হয়, সেদিকে লক্ষ্য রাখতে হবে। বাধঁনে নাইলনের মিহি বা চিকন সূতা/দড়ি ব্যবহার করা যাবে না;\n" +
                "৫. রোগীকে কোন ভাবেই হাঁটাচলা করতে দেওয়া যাবে না;\n" +
                "৬.\n" +
                "হবে;\n" +
                "রোগীকে যথাসম্ভব স্থির অবস্থায় বসিয়ে রাখতে\n" +
                "৭. শরীরের যে অংশে সাপ কেটেছে তা বুকের অবস্থান থেকে যথেষ্ট নিচে রাখতে হবে;\n" +
                "৮. সাপে কাটলে তাৎক্ষণিকভাবে শরীরের আংটি, চুরি, ব্রেসলেট, ঘড়ি খুলে ফেলতে হবে;\n" +
                "৯. ওঝা বা বেদের মাধ্যমে অবৈজ্ঞানিক উপায়ে চিকিৎসা করিয়ে সময় নষ্ট করা যাবে না;\n" +
                "১০. রোগীকে অবশ্যই যানবাহন যোগে হাসপাতালে নেয়া উচিত এবং সবসময় রোগীকে যানবাহনের মাঝখানে বসিয়ে নিতে হবে।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "সর্প দংশনের প্রচলিত অপচিকিৎসা");
        hashMap.put("detail", "১. রোগীকে ওঝা/কবিরাজ/বেদে সম্প্রদায়ের কাছে চিকিৎসার জন্য নিয়ে যাওয়া ও সময় নষ্ট করা;\n" +
                "২. দংশিত স্থানে ধারালো ব্লেড বা ছুরি দিয়ে কেটে রক্ত বের করা;\n" +
                "৩. মুখ দিয়ে চুষে দংশিত স্থান থেকে রক্ত বের করা;\n" +
                "৪. তেল, ঘি, মরিচ, গোবর ইত্যাদি খাইয়ে রোগীকে বমি করানোর জন্য চেষ্টা করা;\n" +
                "৫. রোগীকে কেঁচো/কেঁচোর রস খাওয়ানোর চেষ্টা করা;\n" +
                "৬. গাছ-গাছড়ার রস দিয়ে প্রলেপ দেয়া ও কাঁচা ডিম, কফি, গোবর, তেঁতুল জাতীয় বিভিন্ন টক খাবার খাওয়ানো;\n" +
                "৭. একাধিক স্থানে নাইলনের রশি দিয়ে খুব শক্ত করে বাঁধন দেয়া;\n" +
                "৮. কাকিল৷ মাছের ঠোঁট ও বেল গাছের কাঁটা দিয়ে সর্পদংশনের স্থান ছিদ্র করে রক্ত বের করার চেষ্টা করা;\n" +
                "৯. এসিড জাতীয় রাসায়নিক দ্রব্য দিয়ে সর্পদংশনের স্থান পুড়িয়ে দেয়৷ ও মরিচের গুঁড়া লাগানো;\n" +
                "১০. রোগীর দংশিত স্থানে চুন ও শিমের বিচি লাগানো;\n" +
                "১১. রাসেল'স ভাইপার, গ্রিন পিট ভাইপার দংশন হলে আক্রান্ত স্থান ফুলে গেলে ছিদ্র করে রক্ত বের করার চেষ্টা করা;\n" +
                "১২. সাপ হত্যা না করলে রোগী সুস্থ হবে না-এই মতবাদে সাপ ধরতে উদ্বুদ্ধ হওয়া;" +
                "১৩. কড়ি বা বাটি চালান দিয়ে দংশিত সাপকে আনার অপচেষ্টা করা ইত্যাদি।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "সাপ সম্পর্কিত প্রচলিত কুসংস্কার");
        hashMap.put("detail", "■ সাপের মাথায় মণি থাকে;\n" +
                "■ বীণ বাজালে সাপ আসে ও নাচে; ■ সাপ গরুর দুধ খায়;\n" +
                "■ ফুলের গন্ধে সাপ আসে; ■ সাপ উড়তে পারে;\n" +
                "■ কড়ি চালান দিয়ে দংশিত সাপ আনা যায়; দাঁড়াশ সাপ মারাত্মক বিষধর- এটি লেজ দিয়ে আঘাত করলে স্থানটি পঁচে যায়;\n" +
                "■ সাপ মানুষকে চিনে রাখে, দিনের বেলায় আঘাত করলে রাতে ঘরে এসে কামড় দেয়;\n" +
                "■ কার্বলিক এসিড এবং মন্ত্রতন্ত্রতে সাপ নিয়ন্ত্রন করা যায়;\n" +
                "■ দুমুখো সাপ আছে;\n" +
                "■ মাইট্যা বা মেটে সাপ দংশনের পরে গোবরে পা পড়লে মৃত্যু নিশ্চিত;\n" +
                "■ শনি-মঙ্গলবার বিষহীন সাপের বিষ হয়;\n" +
                "■ সাপের পা দেখলে, ধনী হয়ে যাবে;\n" +
                "■ যে কোন সাপ দংশন করলে ওঝা ভালো করতে পারে ইত্যাদি।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "পরিবেশে সাপের গুরুত্ব");
        hashMap.put("detail", "■ সাপ আমাদের পরিবেশের ভারসাম্য রক্ষায় গুরুত্বপূর্ণ ভূমিকা পালন করে;\n" +
                "■ একটি প্রাপ্ত বয়স্ক দাঁড়াশ সাপ বছরে ৮০-১০০ টি ইঁদুর খেয়ে থাকে এবং কৃষকের বন্ধু হিসেবে কাজ করে;\n" +
                "■ মানুষের জীবন রক্ষায় বিষধর সাপের দংশনের প্রতিষেধক হিসেবে অ্যান্টিভেনম তৈরীতে বিষধর সাপের বিষ ব্যবহৃত হয়;\n" +
                "■ এছাড়াও সাপের বিষ জীবন রক্ষাকারী ঔষধ তৈরীর কাঁচামাল হিসেবে ব্যবহৃত হয়।\n\n" +
                "রিসোর্স - সর্প দংশনে সচেতনতা অ্যাপ");
        hashMap.put("image", "https://www.rainforesttrust.org/app/uploads/2024/07/Black-Mamba-by-reptiles4all-2shutterstock_190881020-400x257.jpg");
        arrayList.add(hashMap);


    }
    //========================= hashmap data created for recyclerview ENDS here ================================


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<SnakeBiteList.myAdapter.myViewholder>{

        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            CardView cardbg;
            TextView title;
            ImageView image;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                cardbg = itemView.findViewById(R.id.cardbg);
                title = itemView.findViewById(R.id.title);
                image = itemView.findViewById(R.id.image);

            }
        }

        @NonNull
        @Override
        public SnakeBiteList.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.snakebiteitems,parent,false);
            return new SnakeBiteList.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull SnakeBiteList.myAdapter.myViewholder holder, int position) {

            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String title = (String) hashMap.get("title");
            String detail = (String) hashMap.get("detail");
            String image = (String) hashMap.get("image");


            holder.title.setText(title);

            // Load profile image using Glide
            Glide.with(SnakeBiteList.this)
                    .load(image)
                    .circleCrop()
                    .placeholder(R.drawable.logo)
                    .into(holder.image);

            holder.cardbg.setOnClickListener(v -> {

                new AdmobAd(SnakeBiteList.this, new AdmobAdCallBack() {
                    @Override
                    public void onAdDismissed() {
                        Intent intent = new Intent(SnakeBiteList.this, SnakeBiteDetail.class);
                        intent.putExtra("title", title);
                        intent.putExtra("detail", detail);

                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }).showAdmobInterstitial(true);

                });

            //item er animation control
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(SnakeBiteList.this, android.R.anim.slide_in_left));
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
    //=============== Adapter Class for recyclerview ENDS here ================================

}