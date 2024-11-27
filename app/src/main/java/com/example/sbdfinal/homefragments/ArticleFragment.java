package com.example.sbdfinal.homefragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sbdfinal.ArticleDetailActivity;
import com.example.sbdfinal.ArticleListActivity;
import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleFragment extends Fragment {

    RecyclerView recyclerView;
    AppCompatButton seemorebtn;
    LinearLayout loadinglottie;
    ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
    HashMap<String, Object> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
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
                        if (getActivity() != null && NetworkAccess.isConnected(getActivity())) {
                            // Network is connected, go to the next activity
                            Intent intent = new Intent(getActivity(), ArticleListActivity.class);
                            intent.putExtra("FRAGMENT_TO_LOAD", "ArticleFragment");
                            startActivity(intent);
                            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        } else {
                            // Network is not connected, show the no connection dialog
                            if (getActivity() != null) {
                                NetworkAccess.showNoConnectionDialog(getActivity(), "আরো চমৎকার সব নিবন্ধ দেখতে ইন্টারনেটে সংযোগ চালু করুন");
                            }
                        }
                        // Hide the loading animation (regardless of network status)
                        if (loadinglottie != null) {
                            loadinglottie.setVisibility(View.GONE);
                        }
                    }
                }, 700); // Delay for seconds
            }
        });

        //================= click button with dialog ============================


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ArticleFragment.myAdapter());


        ArticleFragment.myAdapter adapter = new ArticleFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        //Hashmap data function
        hashMapdata();

        return view;
    }



    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){

        hashMap = new HashMap<>();
        hashMap.put("titel", "একই প্রজাতির দুইটি সাপকে খাবার ছাড়া দীর্ঘ দিন বন্দি করে রাখলে কী হবে?");
        hashMap.put("author", "মো: মেহেদী হিমু");
        hashMap.put("coverimg", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "বিভিন্নপ্রকার সর্প বিষ ও এদের কার্যকারীতা");
        hashMap.put("author", "নাবিদ আল জুবায়ের");
        hashMap.put("coverimg", R.drawable.logo);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "কালাচ সাপ রাতে ঘুমন্ত মানুষকে কেন কামড়ায়?");
        hashMap.put("author", "নাবিদ আল জুবায়ের");
        hashMap.put("coverimg", R.drawable.logo);
        arrayList.add(hashMap);

    }

    //========================= hashmap data created for recyclerview ENDS here ================================





    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<ArticleFragment.myAdapter.myViewholder>{
        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            TextView title, author;
            ImageView coverimg;
            CardView cardView;
            public myViewholder(@NonNull View itemView) {
                super(itemView);

                title = (TextView) itemView.findViewById(R.id.title);
                author = (TextView) itemView.findViewById(R.id.author);
                coverimg = (ImageView) itemView.findViewById(R.id.coverimg);
                cardView = (CardView) itemView.findViewById(R.id.cardView);

            }
        }

        @NonNull
        @Override
        public ArticleFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.articleitems,parent,false);
            return new ArticleFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, Object> hashMap = arrayList.get(position);

            String title = (String) hashMap.get("titel");
            String author = (String) hashMap.get("author");
            int image = (int) hashMap.get("coverimg");

            holder.coverimg.setImageResource(image);
            holder.title.setText(title);
            holder.author.append(author);


            //eikhane image set korbo




            //item ke click korle kaj korabo
            holder.cardView.setOnClickListener(v -> {
                // Show the loading animation
                loadinglottie.setVisibility(View.VISIBLE);

                // Delay for 2 seconds before checking network and taking action
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
                        startActivity(intent);
                        // Hide the loading animation (regardless of network status)
                        if (loadinglottie != null) {
                            loadinglottie.setVisibility(View.GONE);
                        }
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }, 1000); // Delay for seconds


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