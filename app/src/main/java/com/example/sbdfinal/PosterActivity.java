package com.example.sbdfinal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bdtopcoder.smartadmob.AdmobAd;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PosterActivity extends AppCompatActivity {
    private LinearLayout loadinglottie;
    private RecyclerView recyclerView;
    private TextView tooltitel;
    private ImageView backbtn;
    private LottieAnimationView lottieAnimationView;
    private ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        recyclerView = findViewById(R.id.recyclerView);
        tooltitel = findViewById(R.id.tooltitel);
        backbtn = findViewById(R.id.backbtn);
        loadinglottie = findViewById(R.id.loadinglottie);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);

        MyAdmob.loadAdUnit();

        AdmobAd admobAd = new AdmobAd(this);
        admobAd.loadBanner(findViewById(R.id.bannerAd));

        // Setup marquee for toolbar title
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);

        // Back button handling
        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        // Handle back press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        // Set up RecyclerView with StaggeredGridLayoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter and set it to RecyclerView
        adapter = new MyAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

        // Load data
        loadImages();
    }

    private void loadImages() {
        loadinglottie.setVisibility(View.VISIBLE);

        String url = "https://www.arefinnabil.site/imagelist.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            for (int x = 0; x < jsonArray.length(); x++) {
                                JSONObject jsnarray = jsonArray.getJSONObject(x);
                                String image = jsnarray.getString("image");

                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("image", image);
                                arrayList.add(hashMap);
                            }

                            adapter.notifyDataSetChanged();
                            loadinglottie.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            Log.e("PosterActivity", "JSON Parsing Error", e);
                            loadinglottie.setVisibility(View.GONE);
                            Toast.makeText(PosterActivity.this, "Data parsing error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadinglottie.setVisibility(View.GONE);
                        lottieAnimationView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(PosterActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(arrayRequest);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private final Context context;
        private final List<HashMap<String, String>> dataList;

        public MyAdapter(Context context, List<HashMap<String, String>> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView image;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.image);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.imageitems, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            String imageUrl = dataList.get(position).get("image");

            // Load image with Glide
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.logo)
                    .into(holder.image);

            // OnClick to view full image
            holder.image.setOnClickListener(v -> {
                Intent intent = new Intent(context, ImageViewActivity.class);
                intent.putExtra("image", imageUrl);
                context.startActivity(intent);
                ((AppCompatActivity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

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