package com.example.sbdfinal;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bdtopcoder.smartadmob.AdmobAd;
import com.bdtopcoder.smartadmob.AdmobAdCallBack;
import com.bumptech.glide.Glide;
import com.example.sbdfinal.snakefragments.NonVenomFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RescuerRequestActivity extends AppCompatActivity {
    LinearLayout loadinglottie;
    LottieAnimationView lottieAnimationView;
    ImageView backbtn;
    TextView tooltitel;
    RecyclerView recyclerView;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_request);

        MyAdmob.checkAdStatus(this);

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadAdmobInterstitialAd();

        //---------- Back Button -----------
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //---------- Back Button -----------

        recyclerView = findViewById(R.id.recyclerView);
        tooltitel = findViewById(R.id.tooltitel);
        backbtn = findViewById(R.id.backbtn);
        loadinglottie = findViewById(R.id.loadinglottie);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        // Back button click handler
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Marquee text for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        // marquee text

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(RescuerRequestActivity.this));

        // Initialize the adapter with the populated data
        recyclerView.setAdapter(new RescuerRequestActivity.MyAdapter(RescuerRequestActivity.this, arrayList));

        loadinglottie.setVisibility(View.VISIBLE);

        String url = "https://www.arefinnabil.site/rescrequest.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                String image = jsnarray.getString("image");
                                String name = jsnarray.getString("name");
                                String poster = jsnarray.getString("poster");
                                String detail = jsnarray.getString("detail");
                                String regUrl = jsnarray.getString("regUrl");
                                String fbpg = jsnarray.getString("fbpg");
                                String fbgrp = jsnarray.getString("fbgrp");


                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("image", image);
                                hashMap.put("name", name);
                                hashMap.put("poster", poster);
                                hashMap.put("detail", detail);
                                hashMap.put("regUrl", regUrl);
                                hashMap.put("fbpg", fbpg);
                                hashMap.put("fbgrp", fbgrp);

                                arrayList.add(hashMap);
                            }

                            RescuerRequestActivity.MyAdapter adapter = new RescuerRequestActivity.MyAdapter(RescuerRequestActivity.this, arrayList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(RescuerRequestActivity.this));

                            loadinglottie.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            Log.e("PosterActivity", "JSON Parsing Error", e);
                            loadinglottie.setVisibility(View.GONE);
                            Toast.makeText(RescuerRequestActivity.this, "Data parsing error. We're working to resolve it.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loadinglottie.setVisibility(View.GONE);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(RescuerRequestActivity.this, "Please check your internet connection and try again.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(arrayRequest);

    }

    private class MyAdapter extends RecyclerView.Adapter<RescuerRequestActivity.MyAdapter.MyViewHolder> {
        private final Context context;
        private final List<HashMap<String, String>> dataList;

        public MyAdapter(Context context, List<HashMap<String, String>> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView name;
            AppCompatButton seemorebtn;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
                name = itemView.findViewById(R.id.name);
                seemorebtn = itemView.findViewById(R.id.seemorebtn);
            }
        }

        @NonNull
        @Override
        public RescuerRequestActivity.MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.rescuerreqitems, parent, false);
            return new RescuerRequestActivity.MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RescuerRequestActivity.MyAdapter.MyViewHolder holder, int position) {

            String imageUrl = dataList.get(position).get("image");
            String sname = dataList.get(position).get("name");
            String poster = dataList.get(position).get("poster");
            String detail = dataList.get(position).get("detail");
            String regUrl = dataList.get(position).get("regUrl");
            String fbpg = dataList.get(position).get("fbpg");
            String fbgrp = dataList.get(position).get("fbgrp");


            holder.name.setText(sname);

            // Load image with Glide
            Glide.with(context)
                    .load(imageUrl)
                    .fitCenter()
                    .circleCrop()
                    .placeholder(R.drawable.loadingimg)
                    .into(holder.image);

            // OnClick to view full image
            holder.seemorebtn.setOnClickListener(v -> {

                new AdmobAd(RescuerRequestActivity.this, new AdmobAdCallBack() {
                    @Override
                    public void onAdDismissed() {
                        Intent intent = new Intent(context, RescReqDetailActivity.class);
                        intent.putExtra("image", imageUrl);
                        intent.putExtra("name", sname);
                        intent.putExtra("poster", poster);
                        intent.putExtra("detail", detail);
                        intent.putExtra("regUrl", regUrl);
                        intent.putExtra("fbpg", fbpg);
                        intent.putExtra("fbgrp", fbgrp);

                        context.startActivity(intent);
                        ((AppCompatActivity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                }).showAdmobInterstitial(true);


            });

            // Animation
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

}