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
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;
import java.util.HashMap;

public class MidVenimFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> finalarrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mid_venim, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();
        finalArrayList();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MidVenimFragment.myAdapter());

        MidVenimFragment.myAdapter adapter = new MidVenimFragment.myAdapter();
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


    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "কাল নাগিনী");
        hashMap.put("snakeengname", "Ornate flying snake");
        hashMap.put("snakesciname", "Chrysopelea ornata");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "সবুজ ফণিমনসা");
        hashMap.put("snakeengname", "Green Cat snake");
        hashMap.put("snakesciname", "Boiga cyanea");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "পাতি ফণিমনসা");
        hashMap.put("snakeengname", "Common Indian cat snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "নোনাবোড়া ");
        hashMap.put("snakeengname", "Dog-faced water snake");
        hashMap.put("snakesciname", "Boiga cyanea");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "মেটে/মাইট্টা সাপ");
        hashMap.put("snakeengname", "Smooth Scaled water snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);
    }


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
                return new MidVenimFragment.myAdapter.snakeviewholder(view);
            }
            else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_ad, parent, false);
                return new MidVenimFragment.myAdapter.adviewholder(view);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            if (getItemViewType(position) == SNAKE) {

                MidVenimFragment.myAdapter.snakeviewholder snakeviewholder = (MidVenimFragment.myAdapter.snakeviewholder) holder;
                HashMap<String, String> hashMap = finalarrayList.get(position);

                String snakebangname = hashMap.get("snakebangname");
                String snakeengname = hashMap.get("snakeengname");
                String snakesciname = hashMap.get("snakesciname");

                snakeviewholder.snakecardbg.setCardBackgroundColor(Color.parseColor("#F2E318"));
                snakeviewholder.snakebangname.setText(snakebangname);
                snakeviewholder.snakeengname.setText(snakeengname);
                snakeviewholder.snakesciname.setText(snakesciname);

            }
            else if (getItemViewType(position) == AD){

                MidVenimFragment.myAdapter.adviewholder adviewholder = (MidVenimFragment.myAdapter.adviewholder) holder;

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