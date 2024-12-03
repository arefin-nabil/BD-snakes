package com.example.sbdfinal.snakefragments;

import android.content.res.Configuration;
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
import android.widget.TextView;

import com.example.sbdfinal.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MidVenomFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mid_venom, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MidVenomFragment.myAdapter());

        MidVenomFragment.myAdapter adapter = new MidVenomFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        return view;

    }
    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "কাল নাগিনী");
        hashMap.put("snakeengname", "Ornate flying snake");
        hashMap.put("snakesciname", "Chrysopelea ornata");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "সবুজ ফণিমনসা");
        hashMap.put("snakeengname", "Green Cat snake");
        hashMap.put("snakesciname", "Boiga cyanea");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "পাতি ফণিমনসা");
        hashMap.put("snakeengname", "Common Indian cat snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "নোনাবোড়া ");
        hashMap.put("snakeengname", "Dog-faced water snake");
        hashMap.put("snakesciname", "Boiga cyanea");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "মেটে/মাইট্টা সাপ");
        hashMap.put("snakeengname", "Smooth Scaled water snake");
        hashMap.put("snakesciname", "Collecting");
        arrayList.add(hashMap);
    }



    // ================ adapter =================
    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<MidVenomFragment.myAdapter.myViewholder>{

        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            TextView snakebangname, snakeengname, snakesciname;
            CardView snakecardbg;
            public myViewholder(@NonNull View itemView) {
                super(itemView);

                snakebangname = itemView.findViewById(R.id.snakebangname);
                snakeengname = itemView.findViewById(R.id.snakeengname);
                snakesciname = itemView.findViewById(R.id.snakesciname);
                snakecardbg = itemView.findViewById(R.id.snakecardbg);

            }
        }

        @NonNull
        @Override
        public MidVenomFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.snakeitems,parent,false);
            return new MidVenomFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull MidVenomFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String snakebangname = hashMap.get("snakebangname");
            String snakeengname = hashMap.get("snakeengname");
            String snakesciname = hashMap.get("snakesciname");

            boolean isDarkMode = (getContext().getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;

            if (isDarkMode) {
                holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#33000000"));
            } else {
                holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#fefda9"));
            }

            holder.snakebangname.setText(snakebangname);
            holder.snakeengname.setText(snakeengname);
            holder.snakesciname.setText(snakesciname);


            //item er animation control
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(getContext(), android.R.anim.slide_in_left));
        }
        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
    //=============== Adapter Class for recyclerview ENDS here ================================



}