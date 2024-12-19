package com.example.sbdfinal.snakefragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.sbdfinal.NetworkAccess;
import com.example.sbdfinal.R;
import com.example.sbdfinal.SnakeDetail;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VenomousFragment extends Fragment {
    LinearLayout loadinglottie;
    RecyclerView recyclerView;
    LottieAnimationView lottieAnimationView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venomous, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        lottieAnimationView = view.findViewById(R.id.lottieAnimationView);
        loadinglottie = view.findViewById(R.id.loadinglottie);

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        VenomousFragment.myAdapter adapter = new VenomousFragment.myAdapter(getContext(), arrayList);
        recyclerView.setAdapter(adapter);

        loadinglottie.setVisibility(View.VISIBLE);

        String url = "http://192.168.0.112/Apps/venemous.json";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            for (int x = 0; x < jsonArray.length(); x++) {

                                loadinglottie.setVisibility(View.GONE);
                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                Log.d("Sresponse", jsnarray.toString());

                                String snakebangname = jsnarray.getString("snakebangname");
                                String snakeengname = jsnarray.getString("snakeengname");
                                String snakesciname = jsnarray.getString("snakesciname");
                                String identity = jsnarray.getString("identity");
                                String detail = jsnarray.getString("detail");
                                String ending = jsnarray.getString("ending");
                                String image1 = jsnarray.getString("image1");
                                String image2 = jsnarray.getString("image2");
                                String image3 = jsnarray.getString("image3");

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("snakebangname", snakebangname);
                                hashMap.put("snakeengname", snakeengname);
                                hashMap.put("snakesciname", snakesciname);
                                hashMap.put("identity", identity);
                                hashMap.put("detail", detail);
                                hashMap.put("ending", ending);
                                hashMap.put("image1", image1);
                                hashMap.put("image2", image2);
                                hashMap.put("image3", image3);

                                arrayList.add(hashMap);
                            }

                            // Notify the adapter that the data set has changed
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley Error", error.toString());
                loadinglottie.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Please ensure you have an active internet connection and try again.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(arrayRequest);


        return view;

    }


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<VenomousFragment.myAdapter.myViewholder>{
        private Context context;

        private ArrayList<HashMap<String, String>> arrayList;

        public myAdapter(Context context, ArrayList<HashMap<String, String>> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        private class myViewholder extends RecyclerView.ViewHolder{
            //item view er variable nibo eikhane
            TextView snakebangname, snakeengname, snakesciname;
            CardView snakecardbg;
            ImageView snakeimg;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                snakebangname = itemView.findViewById(R.id.snakebangname);
                snakeengname = itemView.findViewById(R.id.snakeengname);
                snakesciname = itemView.findViewById(R.id.snakesciname);
                snakecardbg = itemView.findViewById(R.id.snakecardbg);
                snakeimg = itemView.findViewById(R.id.snakeimg);


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
            String identity = hashMap.get("identity");
            String detail = hashMap.get("detail");
            String ending = hashMap.get("ending");
            String image1 = hashMap.get("image1");
            String image2 = hashMap.get("image2");
            String image3 = hashMap.get("image3");

            // Load profile image using Glide
            Glide.with(context)
                    .load(image1)
                    .circleCrop()
                    .placeholder(R.drawable.logo)
                    .into(holder.snakeimg);


            holder.snakecardbg.setCardBackgroundColor(Color.parseColor("#fea9a9"));
            holder.snakebangname.setText(snakebangname);
            holder.snakeengname.setText(snakeengname);
            holder.snakesciname.setText(snakesciname);

            holder.snakecardbg.setOnClickListener(v -> {
                Intent intent = new Intent(context, SnakeDetail.class);
                intent.putExtra("snakebangname", snakebangname);
                intent.putExtra("snakeengname", snakeengname);
                intent.putExtra("snakesciname", snakesciname);
                intent.putExtra("identity", identity);
                intent.putExtra("detail", detail);
                intent.putExtra("ending", ending);
                intent.putExtra("image1", image1);
                intent.putExtra("image2", image2);
                intent.putExtra("image3", image3);

                context.startActivity(intent);
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