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
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.sbdfinal.ArticleDetailActivity;
import com.example.sbdfinal.ArticleListActivity;
import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;
import com.example.sbdfinal.RescuerListActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleFragment extends Fragment {

    RecyclerView recyclerView;
    AppCompatButton seemorebtn;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        seemorebtn = view.findViewById(R.id.seemorebtn);


        //================= See more button ============================
        seemorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hang tight! We're loading your data.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ArticleListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //================= See more button ============================


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ArticleFragment.myAdapter());


        ArticleFragment.myAdapter adapter = new ArticleFragment.myAdapter();
        recyclerView.setAdapter(adapter);


        //Hashmap data function
        hashMapdata();

        return view;
    }


    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("title", "একই প্রজাতির দুইটি সাপকে খাবার ছাড়া দীর্ঘ দিন বন্দি করে রাখলে কী হবে?");
        hashMap.put("author", "মো: মেহেদী হিমু");
        hashMap.put("coverimg", "");
        hashMap.put("authorimg", "");
        hashMap.put("authordesc", "");
        hashMap.put("text1", "");
        hashMap.put("text2", "");
        hashMap.put("facebook","");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("title", "কালাচ সাপ রাতে ঘুমন্ত মানুষকে কেন কামড়ায়?");
        hashMap.put("author", "নাবিদ আল জুবায়ের");
        hashMap.put("coverimg", "");
        hashMap.put("authorimg", "");
        hashMap.put("authordesc", "");
        hashMap.put("text1", "");
        hashMap.put("text2", "");
        hashMap.put("facebook","");
        arrayList.add(hashMap);

    }

    //========================= hashmap data created for recyclerview ENDS here ================================


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<ArticleFragment.myAdapter.myViewholder> {
        private class myViewholder extends RecyclerView.ViewHolder {
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
            View myView = inflater.inflate(R.layout.articleitems, parent, false);
            return new ArticleFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, String> hashMap = arrayList.get(position);

            String coverimg = hashMap.get("coverimg");
            String title = hashMap.get("title");
            String author = hashMap.get("author");
            String authorimg = hashMap.get("authorimg");
            String authordesc = hashMap.get("authordesc");
            String text1 = hashMap.get("text1");
            String text2 = hashMap.get("text2");
            String facebook = hashMap.get("facebook");

            holder.title.setText(title);
            holder.author.append(author);


            //eikhane image set korbo

            Glide.with(getContext())
                    .load(coverimg)
                    .centerCrop()
                    .placeholder(R.drawable.loadingimg)
                    .into(holder.coverimg);


            //item ke click korle kaj korabo
            holder.cardView.setOnClickListener(v -> {


                Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
                intent.putExtra("coverimg", coverimg);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("authorimg", authorimg);
                intent.putExtra("authordesc", authordesc);
                intent.putExtra("text1", text1);
                intent.putExtra("text2", text2);
                intent.putExtra("facebook", facebook);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


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