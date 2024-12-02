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


import java.util.ArrayList;
import java.util.HashMap;

public class SeaSnakesFragment extends Fragment {


    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sea_snakes, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        hashMapdata();

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SeaSnakesFragment.myAdapter());

        SeaSnakesFragment.myAdapter adapter = new SeaSnakesFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        return view;
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
//=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<SeaSnakesFragment.myAdapter.myViewholder>{

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
        public SeaSnakesFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.snakeitems,parent,false);
            return new SeaSnakesFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull SeaSnakesFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);
            String snakebangname = hashMap.get("snakebangname");
            String snakeengname = hashMap.get("snakeengname");
            String snakesciname = hashMap.get("snakesciname");

            holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#a9b2fe"));
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