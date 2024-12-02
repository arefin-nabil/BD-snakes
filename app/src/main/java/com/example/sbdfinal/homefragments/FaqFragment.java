package com.example.sbdfinal.homefragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.bdtopcoder.smartadmob.AdmobAdCallBack;
import com.example.sbdfinal.FaqListActivity;
import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;
import com.example.sbdfinal.RescuerListActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class FaqFragment extends Fragment {

    AppCompatButton seemorebtn;
    LinearLayout loadinglottie;
    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faq, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        seemorebtn = view.findViewById(R.id.seemorebtn);
        loadinglottie = view.findViewById(R.id.loadinglottie);


        //================= click button with dialog ============================

        seemorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the loading animation
                loadinglottie.setVisibility(View.VISIBLE);

                // Delay for 2 seconds before checking network and taking action
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Check network access after the delay
                        if (NetworkAccess.isConnected(getActivity())) {
                            // Network is connected, go to the next activity
                            new AdmobAd(getActivity(), new AdmobAdCallBack() {
                                @Override
                                public void onAdDismissed() {
                                    Intent intent = new Intent(getActivity(), FaqListActivity.class);
                                    startActivity(intent);
                                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }).loadAdmobInterstitialAd().showAdmobInterstitial(true);


                        } else {
                            // Network is not connected, show the no connection dialog
                            NetworkAccess.showNoConnectionDialog(getActivity(), "আরো প্রশ্নোত্তর দেখতে ইন্টারনেটে সংযোগ চালু করুন");
                        }

                        // Hide the loading animation (regardless of network status)
                        loadinglottie.setVisibility(View.GONE);
                    }
                }, 700); // Delay for 1 seconds (1000 milliseconds)
            }
        });
        //================= click button with dialog ============================



        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new FaqFragment.myAdapter());


        FaqFragment.myAdapter adapter = new FaqFragment.myAdapter();
        recyclerView.setAdapter(adapter);

        //Hashmap data function
        hashMapdata();

        return view;
    }

    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){
        hashMap = new HashMap<>();
        hashMap.put("question", "রয়েল বেঙ্গল টাইগার নাকি হরিণ শুকর বানর ও বনের অন্যান্য প্রাণীর কন্ঠ হুবহু নকল করতে পারে? এটা কি আসলেই সম্ভব?");
        hashMap.put("answer", "হ্যাঁ, এটা সত্যি।\n'রয়েল বেঙ্গল টাইগার' হরিণ, শুকর, বানর সহ কিছু প্রাণীর সাউন্ড অনুকরণ করতে সক্ষম; এটি তাঁদের একটি ইউনিক হান্টিং স্ট্র্যাটেজি। শিকারের সাউন্ড অনুকরণ করার মাধ্যমে শিকারকে কাছে টেনে নিয়ে আসে।");
        hashMap.put("author", "উত্তর - নজরুল ইসলাম");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("question", "জলাতঙ্ক রোগ কিভাবে সংক্রামিত হয়?");
        hashMap.put("answer", "জলাতঙ্ক ভাইরাস স্তন্যপায়ী প্রাণীর স্নায়ুতন্ত্রে ছরিয়ে পড়ে। জলাতঙ্ক জীবাণু আক্রান্ত প্রাণীর লালাতে থাকে। জলাতঙ্ক আক্রান্ত প্রাণীর কামড় বা আঁচড়ের ফলে প্রাথমিকভাবে এই রোগ ছড়ায়। আক্রান্ত স্থানের চামড়ায় নখের আঁচড়েক্ষত থেকে এ রোগ ছড়াতে পারে।");
        hashMap.put("author", "মো: খোরশেদ আলম");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("question", "প্রাণীর কামড়ে আক্রান্ত হলে কিভাবে পরিচর্যা করতে হবে?");
        hashMap.put("answer", "কোন ব্যক্তি প্রাণীর কামড়ে আক্রান্ত হলে-\n" +
                "⚫১০-১৫ মিনিট ধরে সাবান ও পানি দিয়ে দ্রুত ক্ষতস্থান পরিষ্কার করতে হবে। সাবান পাওয়া না গেলে শুধু পানি দিয়েই ধুয়ে ফেলতে হবে। এটাই জলাতঙ্কের সব চেয়ে ফলপ্রসু প্রাথমিক চিকিৎসা।\n" +
                "⚫যদি পাওয়া যায় ৭০% অ্যালকোহল/ইথানল অথবা প্রভিডন আয়োডিন দ্বারা ক্ষতস্থান সম্পূর্ণভাবে পরিষ্কার করতে হবে।\n" +
                "⚫পরবর্তী চিকিৎসার জন্য আক্রান্ত ব্যক্তিকে যত দ্রুত সম্ভব স্বাস্থ্যকেন্দ্রে নিতে হবে।");
        hashMap.put("author", "মো: খোরশেদ আলম");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("question", "প্রাণীর কামড়ে ক্ষতস্থানে কি করা উচিত নয়?");
        hashMap.put("answer", "মরিচের গুঁড়া, গাছের রস, অ্যাসিড অথবা অ্যালকালিস জাতীয় জ্বলাতনকর বস্তু লাগানো।\n" +
                "ক্ষতস্থান পট্টি বা ব্যান্ডেজ দ্বারা ঢেকে দেওয়া।");
        hashMap.put("author", "মো: খোরশেদ আলম");
        arrayList.add(hashMap);


    }
    //========================= hashmap data created for recyclerview ENDS here ================================




    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<FaqFragment.myAdapter.myViewholder>{

        private SparseBooleanArray expandedPositions = new SparseBooleanArray();
        private int expandedPosition = RecyclerView.NO_POSITION;
        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            LinearLayout motherLayout, discLayout;
            RelativeLayout itemClicked;
            ImageView arrowImg;
            TextView question, answer, author;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                motherLayout = itemView.findViewById(R.id.motherLayout);
                itemClicked = itemView.findViewById(R.id.itemClicked);
                arrowImg = itemView.findViewById(R.id.arrowImg);
                discLayout = itemView.findViewById(R.id.discLayout);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.answer);
                author = itemView.findViewById(R.id.author);

            }
        }

        @NonNull
        @Override
        public FaqFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.faqitems,parent,false);
            return new FaqFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull FaqFragment.myAdapter.myViewholder holder, int position) {

            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String question = (String) hashMap.get("question");
            String answer = (String) hashMap.get("answer");
            String author = (String) hashMap.get("author");


            holder.question.setText(question);
            holder.answer.setText(answer);
            holder.author.setText(author);

            // Set the visibility and arrow icon based on the expandedPositions state

            boolean isExpanded = expandedPosition == position;

            boolean isDarkMode = (getContext().getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;

            holder.discLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

            if (isDarkMode) {
                holder.motherLayout.setBackgroundColor(isExpanded ? Color.parseColor("#724CAF50") : Color.parseColor("#33000000"));
            }else {
                holder.motherLayout.setBackgroundColor(isExpanded ? Color.parseColor("#724CAF50") : Color.parseColor("#FFFFFF"));
            }


            holder.arrowImg.setImageResource(isExpanded ? R.drawable.uparrow : R.drawable.downarrow);

            // Handle click listener to expand/collapse the item
            holder.itemClicked.setOnClickListener(v -> {
                boolean currentlyExpanded = expandedPositions.get(position, false);
                int previousExpandedPosition = expandedPosition;

                // Set the new expanded position (collapse if clicking the same item)
                expandedPosition = isExpanded ? RecyclerView.NO_POSITION : position;

                // Notify changes to collapse the previous item and expand the new one
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(expandedPosition);
            });

            //item er animation control
            holder.itemView.setAnimation(null);
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
    //=============== Adapter Class for recyclerview ENDS here ================================


}