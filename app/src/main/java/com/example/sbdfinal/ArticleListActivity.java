package com.example.sbdfinal;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArticleListActivity extends AppCompatActivity {
    LinearLayout loadinglottie;
    LottieAnimationView lottieAnimationView;
    RecyclerView recyclerView;
    SearchView searchview;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ImageView backbtn;
    TextView tooltitel;
    private long lastAdShownTime = 0; // Initialize to 0 (no ad shown yet)
    private static final long AD_INTERVAL = 180000; // 3 minute in milliseconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);


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

        searchview = findViewById(R.id.searchview);
        searchview.clearFocus();


        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(ArticleListActivity.this));

        // Initialize the adapter with the populated data
        recyclerView.setAdapter(new ArticleListActivity.myAdapter(ArticleListActivity.this, arrayList));


        // Set listener for the SearchView
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Optional: Handle the query submission
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the RecyclerView data based on the text input in the SearchView

                ArticleListActivity.myAdapter adapter = (ArticleListActivity.myAdapter) recyclerView.getAdapter();
                adapter.filter(newText);// Call filter method of the adapter
                return false;
            }
        });

        loadinglottie.setVisibility(View.VISIBLE);
        searchview.setVisibility(View.GONE);

        String url = "https://www.arefinnabil.site/articlelist.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {

                            for (int x=0; x<jsonArray.length(); x++){

                                loadinglottie.setVisibility(View.GONE);
                                searchview.setVisibility(View.VISIBLE);

                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                Log.d("Sresponse", jsnarray.toString());



                                String coverimg = jsnarray.getString("coverimg");
                                String title = jsnarray.getString("title");
                                String author = jsnarray.getString("author");
                                String authorimg = jsnarray.getString("authorimg");
                                String authordesc = jsnarray.getString("authordesc");
                                String text1 = jsnarray.getString("text1");
                                String text2 = jsnarray.getString("text2");
                                String facebook = jsnarray.getString("facebook");




                                hashMap=new HashMap<>();
                                hashMap.put("coverimg",coverimg);
                                hashMap.put("title",title);
                                hashMap.put("author",author);
                                hashMap.put("authorimg",authorimg);
                                hashMap.put("authordesc",authordesc);
                                hashMap.put("text1",text1);
                                hashMap.put("text2",text2);
                                hashMap.put("facebook",facebook);
                                arrayList.add(hashMap);

                                ArticleListActivity.myAdapter adapter = new ArticleListActivity.myAdapter(ArticleListActivity.this, arrayList);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(ArticleListActivity.this));


                            }


                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadinglottie.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                searchview.setVisibility(View.GONE);
                Toast.makeText(ArticleListActivity.this, "Please ensure you have an active internet connection and try again.", Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue queue = Volley.newRequestQueue(ArticleListActivity.this);
        queue.add(arrayRequest);

    }


    private class myAdapter extends RecyclerView.Adapter<ArticleListActivity.myAdapter.myViewholder> {
        private Context context;
        private List<HashMap<String, String>> originalList;
        private List<HashMap<String, String>> filteredList;

        // Constructor
        public myAdapter(Context context, List<HashMap<String, String>> arrayList) {
            this.context = context;
            this.originalList = new ArrayList<>(arrayList); // Store the original list
            this.filteredList = new ArrayList<>(arrayList); // Copy the data for filtering
        }

        public class myViewholder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView coverimg;
            TextView title, author;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                coverimg = itemView.findViewById(R.id.coverimg);
                title = itemView.findViewById(R.id.title);
                cardView = itemView.findViewById(R.id.cardView);
                author = itemView.findViewById(R.id.author);

            }
        }

        @NonNull
        @Override
        public ArticleListActivity.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View myView = inflater.inflate(R.layout.articleitems, parent, false);
            return new ArticleListActivity.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull ArticleListActivity.myAdapter.myViewholder holder, int position) {
            HashMap<String, String> hashMap = filteredList.get(position);

            String coverimg = hashMap.get("coverimg");
            String title = hashMap.get("title");
            String author = hashMap.get("author");
            String authorimg = hashMap.get("authorimg");
            String authordesc = hashMap.get("authordesc");
            String text1 = hashMap.get("text1");
            String text2 = hashMap.get("text2");
            String facebook = hashMap.get("facebook");


            holder.title.setText(title);
            holder.author.setText(author);

            // Load profile image using Glide
            Glide.with(context)
                    .load(coverimg)
                    .fitCenter()
                    .placeholder(R.drawable.loadingimg)
                    .into(holder.coverimg);


            holder.cardView.setOnClickListener(v -> {

                long currentTime = System.currentTimeMillis();

                // Check if 1 minute has passed since the last ad was shown
                if (currentTime - lastAdShownTime >= AD_INTERVAL) {
                    // Show the ad
                    new AdmobAd(ArticleListActivity.this, new AdmobAdCallBack() {
                        @Override
                        public void onAdDismissed() {
                            // Update the last ad shown time
                            lastAdShownTime = System.currentTimeMillis();

                            Intent intent = new Intent(context, ArticleDetailActivity.class);
                            intent.putExtra("coverimg", coverimg);
                            intent.putExtra("title", title);
                            intent.putExtra("author", author);
                            intent.putExtra("authorimg", authorimg);
                            intent.putExtra("authordesc", authordesc);
                            intent.putExtra("text1", text1);
                            intent.putExtra("text2", text2);
                            intent.putExtra("facebook", facebook);
                            context.startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }}).showAdmobInterstitial(true);

                }else {
                    Intent intent = new Intent(context, ArticleDetailActivity.class);
                    intent.putExtra("coverimg", coverimg);
                    intent.putExtra("title", title);
                    intent.putExtra("author", author);
                    intent.putExtra("authorimg", authorimg);
                    intent.putExtra("authordesc", authordesc);
                    intent.putExtra("text1", text1);
                    intent.putExtra("text2", text2);
                    intent.putExtra("facebook", facebook);
                    context.startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
            //item er animation control
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(ArticleListActivity.this, android.R.anim.slide_in_left));
        }

        @Override
        public int getItemCount() {
            return filteredList.size(); // Return size of filtered list
        }

        // Filter method for SearchView
        public void filter(String query) {
            filteredList.clear(); // Clear the filtered list before adding new results

            if (query.isEmpty()) {
                // If query is empty, show all items
                filteredList.addAll(originalList);
            } else {
                // Loop through the original list and filter based on name or location
                for (HashMap<String, String> item : originalList) {
                    String title = item.get("title");
                    String author = item.get("author");

                    // Case-insensitive check for name and location
                    if ((title != null && title.toLowerCase().contains(query.toLowerCase())) ||
                            (author != null && author.toLowerCase().contains(query.toLowerCase()))) {
                        filteredList.add(item); // Add the item to filteredList if it matches
                    }
                }
            }

            notifyDataSetChanged(); // Notify adapter to refresh the RecyclerView with filtered data
        }
    }


}