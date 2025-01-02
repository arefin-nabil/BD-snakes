package com.example.sbdfinal.homefragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.sbdfinal.ArticleDetailActivity;
import com.example.sbdfinal.ArticleListActivity;
import com.example.sbdfinal.R;

import java.util.HashMap;

public class ArticleFragment extends Fragment {
    CardView cardView1, cardView2;
    TextView title1, title2, author1, author2;
    ImageView coverimg1, coverimg2;
    AppCompatButton seemorebtn;
    HashMap<String, String> hashMap;
    HashMap<String, String> hashMap2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        seemorebtn = view.findViewById(R.id.seemorebtn);
        cardView1 = view.findViewById(R.id.cardView1);
        cardView2 = view.findViewById(R.id.cardView2);
        title1 = view.findViewById(R.id.title1);
        title2 = view.findViewById(R.id.title2);
        author1 = view.findViewById(R.id.author1);
        author2 = view.findViewById(R.id.author2);
        coverimg1 = view.findViewById(R.id.coverimg1);
        coverimg2 = view.findViewById(R.id.coverimg2);

        //================= See more button ============================
        seemorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArticleListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //================= See more button ============================

        //Hashmap data function
        hashMapdata();

        String coverimg11 = hashMap.get("coverimg11");
        String title11 = hashMap.get("title11");
        String author11 = hashMap.get("author11");
        String authorimg11 = hashMap.get("authorimg11");
        String authordesc11 = hashMap.get("authordesc11");
        String text11 = hashMap.get("text11");
        String text21 = hashMap.get("text21");
        String facebook11 = hashMap.get("facebook11");

        title1.setText(title11);
        author1.setText(author11);

        Glide.with(getContext())
                .load(coverimg11)
                .fitCenter()
                .placeholder(R.drawable.loadingimg)
                .into(coverimg1);

        cardView1.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
            intent.putExtra("coverimg", coverimg11);
            intent.putExtra("title", title11);
            intent.putExtra("author", author11);
            intent.putExtra("authorimg", authorimg11);
            intent.putExtra("authordesc", authordesc11);
            intent.putExtra("text1", text11);
            intent.putExtra("text2", text21);
            intent.putExtra("facebook", facebook11);
            startActivity(intent);

            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });



        String coverimg = hashMap2.get("coverimg");
        String title = hashMap2.get("title");
        String author = hashMap2.get("author");
        String authorimg = hashMap2.get("authorimg");
        String authordesc = hashMap2.get("authordesc");
        String text1 = hashMap2.get("text1");
        String text2 = hashMap2.get("text2");
        String facebook = hashMap2.get("facebook");

        title2.setText(title);
        author2.setText(author);

        Glide.with(getContext())
                .load(coverimg)
                .fitCenter()
                .placeholder(R.drawable.loadingimg)
                .into(coverimg2);



        cardView2.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
            intent.putExtra("coverimg", coverimg);
            intent.putExtra("title", title);
            intent.putExtra("author", author);
            intent.putExtra("authorimg", authorimg);
            intent.putExtra("authordesc", authordesc);
            intent.putExtra("text1", text1);
            intent.putExtra("text2", text2);
            intent.putExtra("facebook", facebook);
            startActivity(intent);

            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        return view;
    }


    //========================= hashmap data Starts Here ================================
    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("title11", "সাপের বিষের তীব্রতা কীভাবে পরিমাপ করা হয়?");
        hashMap.put("author11", "ফেরদৌস আলম");
        hashMap.put("coverimg11", "https://www.arefinnabil.site/imageresource/articleimg/offline_art1.jpg");
        hashMap.put("authorimg11", "https://www.arefinnabil.site/imageresource/rescuer/ferdousalam.jpg");
        hashMap.put("authordesc11", "President, WSRTBD");
        hashMap.put("text11", "আমাদের অনেকের মনে প্রশ্ন জাগে, 'সাপের বিষের তীব্রতা কীভাবে পরিমাপ করা হয়?' আসুন জেনে নেই বিষের তীব্রতা পরিমাপের পদ্ধতি। \n" +
                "বিষের তীব্রতার পরিমাপক হলো Median Lethal Dose (LD50)। এই পদ্ধতিতে, যত সংখ্যক প্রাণীর ওপর পরীক্ষা করা হয়, তাদের অর্ধেক (৫০%) প্রাণীকে মারতে যতটা বিষ প্রয়োজন, তার কেজিপ্রতি পরিমাণই হচ্ছে LD50.\n" +
                "মনে করুন, আমরা ১০০ টি ইঁদুরের উপর একটি সাপের বিষের তীব্রতা নির্ণয় করব। প্রতিটি ইঁদুরের ভর ২০ গ্রাম। অর্থাৎ, ১০০ টি ইঁদুরের ভর ২ কেজি। এর অর্ধেক সংখ্যক ইঁদুর অর্থাৎ ৫০টি ইঁদুর যার ভর ১ কেজি মারতে যত মিলিগ্রাম বিষ প্রয়োজন হবে সেটাই ওই সাপের Median Lethal Dose বা LD50.\n" +
                "LD50 যে সাপের যত কম, তার বিষের তীব্রতা তত বেশি।");
        hashMap.put("text21", "চারভাবে বিষ প্রয়োগ করে কোনো বিষধর প্রাণীর বিষের তীব্রতা(LD50) পরিমাপ করা হয়-\n" +
                "১. Subcutaneous injection- চামড়ার নিচের চর্বির স্তরে বিষ প্রয়োগ করা হয়।\n" +
                "২. Intravenous injection- সরাসরি শিরায় বিষ প্রয়োগ করা হয়।\n" +
                "৩. Intraperitoneal injection- পেটে বা দেহগহ্বরে বিষ প্রয়োগ করা হয়৷ \n" +
                "৪. Intramascular injection- মাংসপেশিতে বিষ প্রয়োগ করা হয়।\n" +
                "এগুলোর মধ্যে Subcutaneous পদ্ধতিতে নির্ণীত Median Lethal Dose বা SCLD50-এর মানকে বিষের তীব্রতা পরিমাপে সবচেয়ে গ্রহণযোগ্য বলে মনে করা হয়।\n" +
                "Subcutaneous LD50 অনুযায়ী, \n" +
                "★পৃথিবীর সবচেয়ে বিষধর সাপ হলো Inland Taipan (0.025 mg/kg)।\n" +
                "★দ্বিতীয় স্থানে আছে Dubois' Sea Snake (0.044 mg/kg)। এটি সামুদ্রিক সাপগুলোর মধ্যে সবচেয়ে বিষধর।\n" +
                "★বাংলাদেশের সবচেয়ে বিষধর সাপ Yellow-bellied Sea Snake বা  হলদেপেট সামুদ্রিক সাপ (0.067 mg/kg)। এটি সামুদ্রিক সাপ।\n" +
                "★বাংলাদেশের স্থলচর সাপেদের মধ্যে সবচেয়ে বিষধর সাপ কালাচ বা Common Krait (0.325 mg/kg)।");
        hashMap.put("facebook11","FardoushAlam313");

        hashMap2 = new HashMap<>();
        hashMap2.put("title", "সাপ সম্পর্কে ১০ টি অজানা তথ্য");
        hashMap2.put("author", "নাবিদ আল জুবায়ের");
        hashMap2.put("coverimg", "https://www.arefinnabil.site/imageresource/articleimg/offline_art2.jpg");
        hashMap2.put("authorimg", "https://www.arefinnabil.site/imageresource/articleimg/nabiladjubayer.jpg");
        hashMap2.put("authordesc", "Organizing Secretary, WSRTBD");
        hashMap2.put("text1", "সাপ দেখে ভয় পায়না এমন লোক পাওয়া দুষ্কর। সাপ সম্পর্কে ভুল তথ্য জানার ফলে মানুষ সাপকে ভয় পায়, ভয় না পেয়ে একটু সতর্ক থাকলেই সাপে কামড় এড়ানো সম্ভব। সাপের প্রতি মানুষের ব্যপক আগ্রহ, আগ্রহের মাত্রা বৃদ্ধি করতে সাপ সম্পর্কে এমন কিছু তথ্য দিবো যেগুলো হয়তো কখনো জানা ছিলোনা আপনাদের!\n" +
                "১. সাপের দৈহিক বৃদ্ধি কখনো থেমে যায় না। সাধারণত প্রাণীদের বৃদ্ধি একটা নির্দিষ্ট বয়স পর্যন্ত চলমান থাকে। তারপর দৈহিক বৃদ্ধি বন্ধ হয়ে যায়। সাপের বয়সের সাথে বৃদ্ধির গতি কমলেও দৈহিক বৃদ্ধি থেমে থাকে না। \n" +
                "২. সাধারণত প্রাণী যত বড়ই হোক, হাড়ের সংখ্যা একই থাকে। যেমন মানুষের ২০৬ টি হাড়।\n" +
                "সাপের মাথার খুলি এবং চোয়ালে ১০ টি হাড় থাকে, তবে সাপের আকার ও জাত ভেদে পাঁজর এবং কশেরুকা ব্যাপকভাবে পরিবর্তিত হয়। ১০ সেমি লম্বা পুঁয়ে /blind snake সাপের গড়ে ২০০ টি কশেরুকা এবং মোট ৬০০ টি হাড় থাকে। সাপের প্রজাতি ও দৈর্ঘ্যের উপর ভিত্তি করে ৬০০-১৮০০ টি হাড় হয়। \n" +
                "৩. আমরা সবাই জানি সাপ ডিম দেয়। তবে জানলে অবাক হবেন বিশ্বে ৩০% সাপ এমন আছে যারা সরাসরি বাচ্চা প্রসব করে। অর্থাৎ স্থলজ-জলজ  মিলিয়ে ৭০% সাপ ডিম দেয়, বাকি গুলো বাচ্চা প্রসব করে। \n" +
                "৪. Ophiophobia বা সর্পভিতী খবুই কমন এবং মারাত্মক সমস্যা। বিশ্বে প্রতি ৩ জনে ১ জন প্রাপ্তবয়স্কের এই ভিতী আছে। এই ফোবিয়ার কারণে মানুষের হার্টঅ্যাটাকে মৃত্যু পর্যন্ত হতে পারে! \n" +
                "৫. প্রজতি ভেদে বন্দী অবস্থায় সাপ ৩০-৪০ বছর পর্যন্ত বাঁচতে পারে, কিন্তু বন্য অবস্থায় সর্বচ্চো ১৫-২০ বছর পর্যন্ত বেঁচে থাকতে পারে।\n" +
                "৬. বিশ্বের সবথেকে বিষধর সাপ \"ইনল্যান্ড টাইপ্যান\"। এটি প্রতি কামড়ে ৪৪-১১০ মিলিগ্রাম বিষ ইনজেক্ট করতে পারে। অস্ট্রেলিয়ান প্রজাতির ইনল্যান্ড টাইপান প্রতি কামড়ে যত বিষ ইনজেক্ট করে সেটা দিয়ে ২.৫ লক্ষ ইঁদুর মারা সম্ভব!");
        hashMap2.put("text2", "৭. সাপ এতোটাই নিম্ন মস্তিষ্কের প্রাণী যে এরা খাবারের আকৃতি অনুমান না করেই খেতে শুরু করে। সৃষ্টিকর্তা ওদের একটা বিশেষ ক্ষমতা দিয়েছে, ওরা ওদের মুখের চেয়ে দ্বিগুণ আকৃতির খাদ্য গ্রহণে সক্ষম, সাপেদের চোয়াল ব্যপক প্রসারিত হতে পারে। তবে অতি নিম্ন মস্তিষ্কের হওয়ায় এরা অতিরিক্ত খাওয়ার ফলে পেট ফেটে মৃত্যুবরণ করতে পারে! অনেক ক্ষেত্রে সাপ তার আকৃতির থেকেও বড় প্রাণী খেয়ে নেয়। পরে সেটা বমি করে বের করতে হয়। \n" +
                "৮. কিং কোবরা বা রাজ গোখরাকে বিশ্বের অন্যতম বুদ্ধিমত্তা সম্পন্ন সাপ হিসেবে বিবেচনা করা হয়। এরা একমাত্র প্রজাতির সাপ যাদের একটি শক্তিশালী পারিবারিক অনুভূতি রয়েছে। বেশিরভাগ সাপের প্রজাতি বাচ্চা জন্মের পরই তাদেরকে মুক্ত করে দেয়। সাপের বাচ্চা জন্ম থেকেই শিকার করে খাওয়ার উপযোগী হয়। কিং কোবরা একমাত্র সাপ যারা থাকার জন্যে বাসা তৈরি করে এবং তাদের বাচ্চাদের সংরক্ষণ করে।\n" +
                "৯. সাপের বিষদাঁত গুলো ৬-১০ সপ্তাহ পর্যন্ত স্থায়ী থাকে। এর মধ্যে শিকার করতে গিয়ে তাদের দাঁত ভেঙে যায়। পেছন থেকে সাধারণ দাঁত গুলো সামনের দিকে চলে এসে পুনরায় বিষদাঁত গঠন করে। এভাবে তার মৃত্যুর আগ পর্যন্ত চলমান থাকে। \n" +
                "১০. সাপ বছরে মাত্র ৬-৩০ বার খাবার খায়। সাপ সুবিধাবাদী শিকারী, কিন্তু খাবারের অভাব হলে তারা তাদের বিপাক কার্যক্রম ৭০% কমিয়ে দিতে পারে। সবমিলিয়ে, সুস্থ থাকতে এবং বেড়ে ওঠার জন্য সাপের বছরে মাত্র ৬-৩০ বার খাবার প্রয়োজন।");
        hashMap2.put("facebook2","100037084667877");

    }

    //========================= hashmap data ENDS here ================================


}