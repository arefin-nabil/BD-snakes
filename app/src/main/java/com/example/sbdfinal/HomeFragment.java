package com.example.sbdfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.TransitionRes;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
    HashMap<String, Object> hashMap;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);


        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new myAdapter());


        myAdapter adapter = new myAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        //Hashmap data function
        hashMapdata();





        return view;
    }


    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){
        hashMap = new HashMap<>();
        hashMap.put("titel", "নির্বিষ সাপ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "মৃদু বিষধর সাপ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "বিষধর সাপ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "বিষাক্ত সাপ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "জাতীয় জরুরী নাম্বার সমূহ");
        hashMap.put("image", R.drawable.numberi);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "হাসপাতাল তালিকা");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "চিকিৎসামৃদু বিষধর সাপ মৃদু বিষধর সাপ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "যোগাযোগ");
        hashMap.put("image", R.drawable.logo);
        arrayList.add(hashMap);
    }

    //========================= hashmap data created for recyclerview ENDS here ================================







    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<myAdapter.myViewholder>{
        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            TextView textView;
            ImageView imageView;
            CardView cardView;
            public myViewholder(@NonNull View itemView) {
                super(itemView);

                textView = (TextView) itemView.findViewById(R.id.textView);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
                cardView = (CardView) itemView.findViewById(R.id.cardView);

            }
        }

        @NonNull
        @Override
        public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.homeitems,parent,false);
            return new myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, Object> hashMap = arrayList.get(position);
            String titel = (String) hashMap.get("titel");
            int image = (int) hashMap.get("image");

            holder.imageView.setImageResource(image);
            holder.textView.setText(titel);

            //eikhane image set korbo

            //item ke click korle kaj korabo
            holder.cardView.setOnClickListener(v -> {
                if (position == 0){
                    snakelistactivity(0);
                } else if (position == 1) {
                    snakelistactivity(1);
                } else if (position == 2) {
                    snakelistactivity(2);
                } else if (position == 3) {
                    snakelistactivity(3);
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


    private void snakelistactivity(int tabPosition) {
        Intent intent = new Intent(getActivity(), SnakeListActivity.class);
        intent.putExtra("TAB_POSITION", tabPosition); // Pass tab position
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}