package com.example.sbdfinal.snakefragments;

import android.content.Intent;
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


import com.example.sbdfinal.R;
import com.example.sbdfinal.SnakeDetail;


import java.util.ArrayList;
import java.util.HashMap;

public class NonVenomFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_venom, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NonVenomFragment.myAdapter());

        NonVenomFragment.myAdapter adapter = new NonVenomFragment.myAdapter();
        recyclerView.setAdapter(adapter);

        return view;

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


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<NonVenomFragment.myAdapter.myViewholder>{

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
        public NonVenomFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.snakeitems,parent,false);
            return new NonVenomFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull NonVenomFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String snakebangname = hashMap.get("snakebangname");
            String snakeengname = hashMap.get("snakeengname");
            String snakesciname = hashMap.get("snakesciname");

            holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#bafabf"));
            holder.snakebangname.setText(snakebangname);
            holder.snakeengname.setText(snakeengname);
            holder.snakesciname.setText(snakesciname);

            holder.snakecardbg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), SnakeDetail.class);
                    intent.putExtra("bgColor", "#FFC107");
                    startActivity(intent);
                }
            });

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