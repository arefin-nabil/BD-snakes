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

import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;


import java.util.ArrayList;
import java.util.HashMap;

public class VenomousFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venomous, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new VenomousFragment.myAdapter());

        VenomousFragment.myAdapter adapter = new VenomousFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        return view;

    }



    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "লাল গলা ঢোড়া (বিষধর+বিষাক্ত)");
        hashMap.put("snakeengname", "Red necked-keelback");
        hashMap.put("snakesciname", "Rhabdophis subminiatus");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "পদ্ম গোখরা");
        hashMap.put("snakeengname", "Monocled cobra");
        hashMap.put("snakesciname", "Naja kauthia");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "সবুজ ফণিমনসা");
        hashMap.put("snakeengname", "Spectacled cobra");
        hashMap.put("snakesciname", "Naja naja");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "রাজ গোখরা");
        hashMap.put("snakeengname", "King cobra");
        hashMap.put("snakesciname", "Ophiophagus hannah");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "কালাচ");
        hashMap.put("snakeengname", "Common krait");
        hashMap.put("snakesciname", "Bungarus caeruleus");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("snakebangname", "বড় কৃষ্ণ কালাচ");
        hashMap.put("snakeengname", "Greater black krait");
        hashMap.put("snakesciname", "Bungarus niger");
        arrayList.add(hashMap);
    }


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<VenomousFragment.myAdapter.myViewholder>{

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
        public VenomousFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.snakeitems,parent,false);
            return new VenomousFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull VenomousFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String snakebangname = hashMap.get("snakebangname");
            String snakeengname = hashMap.get("snakeengname");
            String snakesciname = hashMap.get("snakesciname");

            holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#fea9a9"));
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