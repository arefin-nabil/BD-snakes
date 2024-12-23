package com.example.sbdfinal;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HospitalListActivity extends AppCompatActivity {

    TextView tooltitel;
    ImageView backbtn;
    LinearLayout loadinglottie;
    LottieAnimationView lottieAnimationView;
    RecyclerView recyclerView;
    SearchView searchview;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        backbtn = findViewById(R.id.backbtn);
        tooltitel = findViewById(R.id.tooltitel);
        loadinglottie = findViewById(R.id.loadinglottie);
        lottieAnimationView = findViewById(R.id.lottieAnimationView);
        recyclerView = findViewById(R.id.recyclerView);
        searchview = findViewById(R.id.searchview);
        searchview.clearFocus();

        //---------- Marquee Text for ToolBar -----------
        tooltitel.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tooltitel.setMarqueeRepeatLimit(-1);
        tooltitel.setSelected(true);
        //---------- Marquee Text for ToolBar ENDS -----------

        backbtn.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

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

        loadinglottie.setVisibility(View.VISIBLE);
        searchview.setVisibility(View.GONE);

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
                HospitalListActivity.myAdapter adapter = (HospitalListActivity.myAdapter) recyclerView.getAdapter();
                adapter.filter(newText);// Call filter method of the adapter
                return false;
            }
        });

        // Initialize RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(HospitalListActivity.this));

        // Initialize the adapter with the populated data
        recyclerView.setAdapter(new HospitalListActivity.myAdapter(HospitalListActivity.this, arrayList));


        String url = getIntent().getStringExtra("url");

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



                                String name = jsnarray.getString("name");
                                String number = jsnarray.getString("number");
                                String location = jsnarray.getString("location");


                                hashMap=new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("number",number);
                                hashMap.put("location",location);
                                arrayList.add(hashMap);

                                HospitalListActivity.myAdapter adapter = new HospitalListActivity.myAdapter(HospitalListActivity .this, arrayList);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(HospitalListActivity.this));


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
                Toast.makeText(HospitalListActivity.this, "Please ensure you have an active internet connection and try again.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(HospitalListActivity.this);
        queue.add(arrayRequest);

    }


    private class myAdapter extends RecyclerView.Adapter<HospitalListActivity.myAdapter.myViewholder> {
        private Context context;
        private List<HashMap<String, String>> originalList;
        private List<HashMap<String, String>> filteredList;

        // Constructor
        public myAdapter(Context context, List<HashMap<String, String>> arrayList) {
            this.context = context;
            this.originalList = new ArrayList<>(arrayList); // Store the original list
            this.filteredList = new ArrayList<>(arrayList); // Copy the data for filtering
        }

        // ViewHolder class
        public class myViewholder extends RecyclerView.ViewHolder {
            ImageView rescuerprofileimg;
            TextView name, number;
            LinearLayout callicon, locationicon;

            public myViewholder(@NonNull View itemView) {
                super(itemView);
                rescuerprofileimg = itemView.findViewById(R.id.rescuerprofileimg);
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                callicon = itemView.findViewById(R.id.callicon);
                locationicon = itemView.findViewById(R.id.locationicon);
            }
        }

        @NonNull
        @Override
        public HospitalListActivity.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View myView = inflater.inflate(R.layout.hospitalitems, parent, false);
            return new HospitalListActivity.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull HospitalListActivity.myAdapter.myViewholder holder, int position) {
            HashMap<String, String> hashMap = filteredList.get(position);

            String name = hashMap.get("name");
            String number = hashMap.get("number");
            String location = hashMap.get("location");



            // Set data
            holder.name.setText(name);
            holder.number.setText(number);


            // Set click listener for call icon
            holder.callicon.setOnClickListener(v -> {
                String phoneNumber = "tel:" + number;
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse(phoneNumber));

                if (dialIntent.resolveActivity(context.getPackageManager()) != null) {
                    // Start the dialer app
                    context.startActivity(dialIntent);
                } else {
                    // No dialer app found, copy the number to the clipboard
                    ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("phone number", number); // Only the number, not "tel:"
                    clipboard.setPrimaryClip(clip);

                    // Show a toast to notify the user
                    Toast.makeText(context, "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
                }
            });

            holder.locationicon.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(location));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            });

            // Animate item view
            holder.itemView.startAnimation(AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left));
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
                    String name = item.get("name");
                    String location = item.get("location");

                    // Case-insensitive check for name and location
                    if ((name != null && name.toLowerCase().contains(query.toLowerCase())) ||
                            (location != null && location.toLowerCase().contains(query.toLowerCase()))) {
                        filteredList.add(item); // Add the item to filteredList if it matches
                    }
                }
            }

            notifyDataSetChanged(); // Notify adapter to refresh the RecyclerView with filtered data
        }
    }
}