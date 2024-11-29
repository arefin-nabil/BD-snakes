package com.example.sbdfinal.snakefragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bdtopcoder.smartadmob.AdmobAd;
import com.bdtopcoder.smartadmob.AdmobAdCallBack;
import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;
import com.example.sbdfinal.homefragments.RescuerFragment;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.HashMap;

public class NonVenomFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> finalarrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_venom, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();
        finalArrayList();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NonVenomFragment.myAdapter());

        NonVenomFragment.myAdapter adapter = new NonVenomFragment.myAdapter();
        recyclerView.setAdapter(adapter);

        return view;

    }

    private void finalArrayList(){

        finalarrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {

            if (i>0 && i%4==0){
                hashMap = new HashMap<>();
                hashMap.put("itemType", "ad");
                finalarrayList.add(hashMap);
            }

            hashMap = arrayList.get(i);
            finalarrayList.add(hashMap);

        }
    }

    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "জলঢোড়া সাপ");
        hashMap.put("snakeengname", "Checkered keelback snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "দাঁড়াশ সাপ");
        hashMap.put("snakeengname", "Indian Rat Snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "ইন্দোচিনা দাঁড়াশ সাপ");
        hashMap.put("snakeengname", "Indochinese Rat Snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "সবুজ দাঁড়াশ সাপ");
        hashMap.put("snakeengname", "Green rat snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "পাতি ঘরগিন্নি সাপ");
        hashMap.put("snakeengname", "Common wolf snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "দাগি ঘরগিন্নি সাপ");
        hashMap.put("snakeengname", "Banded wolf snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "হলুদ-ছাপ ঘরগিন্নি সাপ");
        hashMap.put("snakeengname", "Twin spotted wolf snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "জাউয়ের ঘরগিন্নি সাপ");
        hashMap.put("snakeengname", "Zaw's wolf snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "পাতি বেত আচড়া সাপ");
        hashMap.put("snakeengname", "Common Bronzeback Tree Snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "চিত্রিত বেত আচড়া সাপ");
        hashMap.put("snakeengname", "Eastern Bronzeback Tree Snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "জলপাইরঙা বেত আঁচড়া সাপ");
        hashMap.put("snakeengname", "Green bronzeback tree snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "ব্যান্ড বেত আঁচড়া সাপ");
        hashMap.put("snakeengname", "Painted bronzeback tree snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "পাতি দুধরাজ সাপ");
        hashMap.put("snakeengname", "common Trinket Snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "তামাটে মাথা দুধরাজ সাপ");
        hashMap.put("snakeengname", "Copper headed Trinket snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);


    }
    //========================= hashmap data created for recyclerview ENDS here ================================


    // ================ adapter =================

    private class myAdapter extends RecyclerView.Adapter {

        int SNAKE = 0;
        int AD = 1;

        private class snakeviewholder extends RecyclerView.ViewHolder {

            TextView snakebangname, snakeengname, snakesciname;
            CardView snakecardbg;
            public snakeviewholder(@NonNull View itemView) {
                super(itemView);

                snakebangname = itemView.findViewById(R.id.snakebangname);
                snakeengname = itemView.findViewById(R.id.snakeengname);
                snakesciname = itemView.findViewById(R.id.snakesciname);
                snakecardbg = itemView.findViewById(R.id.snakecardbg);

            }
        }

        private class adviewholder extends RecyclerView.ViewHolder {

            LinearLayout adlinerlayout;
            TemplateView my_template;

            public adviewholder(@NonNull View itemView) {
                super(itemView);

                adlinerlayout = itemView.findViewById(R.id.adlinerlayout);
                my_template = itemView.findViewById(R.id.my_template);


            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            if (viewType == SNAKE) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.snakeitems, parent, false);
                return new snakeviewholder(view);
            }
            else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_ad, parent, false);
                return new adviewholder(view);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            if (getItemViewType(position) == SNAKE) {

                snakeviewholder snakeviewholder = (snakeviewholder) holder;
                HashMap<String, String> hashMap = finalarrayList.get(position);

                String snakebangname = hashMap.get("snakebangname");
                String snakeengname = hashMap.get("snakeengname");
                String snakesciname = hashMap.get("snakesciname");

                snakeviewholder.snakecardbg.setCardBackgroundColor(Color.parseColor("#B7E892"));
                snakeviewholder.snakebangname.setText(snakebangname);
                snakeviewholder.snakeengname.setText(snakeengname);
                snakeviewholder.snakesciname.setText(snakesciname);

            }


            else if (getItemViewType(position) == AD){

                NonVenomFragment.myAdapter.adviewholder adviewholder = (NonVenomFragment.myAdapter.adviewholder) holder;

                if (NetworkAccess.isConnected(getContext())) {
                    new AdmobAd(getActivity(), new AdmobAdCallBack() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            AdmobAdCallBack.super.onNativeAdLoaded(nativeAd);
                            adviewholder.adlinerlayout.setVisibility(View.VISIBLE);
                        }
                    }).initializeAdmobAd().loadAdmobNativeAd(adviewholder.my_template);
                } else {
                    adviewholder.adlinerlayout.setVisibility(View.GONE); // Hide ad if not ready
                }
            }

            holder.itemView.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left));
        }

        @Override
        public int getItemCount() {
            return finalarrayList.size();
        }

        @Override
        public int getItemViewType(int position) {

            hashMap = finalarrayList.get(position);
            String itemType = hashMap.get("itemType");
            if (itemType.contains("snake")) return SNAKE;
            else return AD;

        }
    }


}