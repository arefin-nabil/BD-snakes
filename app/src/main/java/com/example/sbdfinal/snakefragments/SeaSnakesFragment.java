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

public class SeaSnakesFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> finalarrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sea_snakes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();
        finalArrayList();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SeaSnakesFragment.myAdapter());

        SeaSnakesFragment.myAdapter adapter = new SeaSnakesFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        return view;
    }


    private void finalArrayList(){

        finalarrayList = new ArrayList<>();

        for (int i = 0; i < arrayList.size(); i++) {

            if (i>0 && i%5==0){
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
        hashMap.put("snakebangname", "ডাউডিন সামুদ্রিক সাপ");
        hashMap.put("snakeengname", "Daudin’s Sea Snake");
        hashMap.put("snakesciname", "Hydrophis Nigrocinctus");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "বড়শিনাক সামুদ্রিক সাপ");
        hashMap.put("snakeengname", "Hook-nosed Sea Snake");
        hashMap.put("snakesciname", "Enhydrina schistosa");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "কালো-হলুদ বলয় লাঠি সামুদ্রিক সাপ");
        hashMap.put("snakeengname", "Annulated Sea Snake");
        hashMap.put("snakesciname", "Hydrophis cyanocinctus");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "ডোরা/লাঠি সামুদ্রিক সাপ");
        hashMap.put("snakeengname", "Striped Sea Snake");
        hashMap.put("snakesciname", "Hydrophis fasciatus");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("itemType", "snake");
        hashMap.put("snakebangname", "বইঠা টেবি সামুদ্রিক সাপ");
        hashMap.put("snakeengname", "Shaw’s Sea Snake");
        hashMap.put("snakesciname", "Lepemis curtus");
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
                return new SeaSnakesFragment.myAdapter.snakeviewholder(view);
            }
            else {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_ad, parent, false);
                return new SeaSnakesFragment.myAdapter.adviewholder(view);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


            if (getItemViewType(position) == SNAKE) {

                SeaSnakesFragment.myAdapter.snakeviewholder snakeviewholder = (SeaSnakesFragment.myAdapter.snakeviewholder) holder;
                HashMap<String, String> hashMap = finalarrayList.get(position);

                String snakebangname = hashMap.get("snakebangname");
                String snakeengname = hashMap.get("snakeengname");
                String snakesciname = hashMap.get("snakesciname");

                snakeviewholder.snakecardbg.setCardBackgroundColor(Color.parseColor("#FF4545"));
                snakeviewholder.snakebangname.setText(snakebangname);
                snakeviewholder.snakeengname.setText(snakeengname);
                snakeviewholder.snakesciname.setText(snakesciname);

            }
            else if (getItemViewType(position) == AD){

                SeaSnakesFragment.myAdapter.adviewholder adviewholder = (SeaSnakesFragment.myAdapter.adviewholder) holder;

                if (NetworkAccess.isConnected(getContext())) {
                    new AdmobAd(getActivity(), new AdmobAdCallBack() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            AdmobAdCallBack.super.onNativeAdLoaded(nativeAd);
                        }
                    }).initializeAdmobAd().loadAdmobNativeAd(adviewholder.my_template);
                    adviewholder.adlinerlayout.setVisibility(View.VISIBLE);
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