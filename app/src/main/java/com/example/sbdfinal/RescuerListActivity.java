package com.example.sbdfinal;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.getSystemService;
import static java.security.AccessController.getContext;

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
import com.bdtopcoder.smartadmob.AdmobAd;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RescuerListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchview;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ImageView backbtn;
    TextView tooltitel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_list);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(RescuerListActivity.this));

        // Initialize the adapter with the populated data
        recyclerView.setAdapter(new myAdapter(RescuerListActivity.this, arrayList));




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

                myAdapter adapter = (myAdapter) recyclerView.getAdapter();
                adapter.filter(newText);// Call filter method of the adapter
                return false;
            }
        });



        String url = "http://192.168.0.112/Apps/rescuerlist.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {

                            for (int x=0; x<jsonArray.length(); x++){

                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                Log.d("Sresponse", jsnarray.toString());



                                String name = jsnarray.getString("name");
                                String number = jsnarray.getString("number");
                                String location = jsnarray.getString("location");
                                String profileimg = jsnarray.getString("profileimg");



                                hashMap=new HashMap<>();
                                hashMap.put("name",name);
                                hashMap.put("number",number);
                                hashMap.put("location",location);
                                hashMap.put("profileimg",profileimg);
                                arrayList.add(hashMap);

                                myAdapter adapter = new myAdapter(RescuerListActivity.this, arrayList);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(RescuerListActivity.this));


                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(RescuerListActivity.this);
        queue.add(arrayRequest);


    }
    private class myAdapter extends RecyclerView.Adapter<RescuerListActivity.myAdapter.myViewholder> {
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
            TextView name, number, location;
            LinearLayout callicon;

            public myViewholder(@NonNull View itemView) {
                super(itemView);
                rescuerprofileimg = itemView.findViewById(R.id.rescuerprofileimg);
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                callicon = itemView.findViewById(R.id.callicon);
                location = itemView.findViewById(R.id.location);
            }
        }

        @NonNull
        @Override
        public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View myView = inflater.inflate(R.layout.rescueritems, parent, false);
            return new myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewholder holder, int position) {
            HashMap<String, String> hashMap = filteredList.get(position);

            String name = hashMap.get("name");
            String number = hashMap.get("number");
            String location = hashMap.get("location");
            String imgeurl = hashMap.get("profileimg");

            // Load profile image using Glide
            Glide.with(context)
                    .load(imgeurl)
                    .circleCrop()
                    .placeholder(R.drawable.logo)
                    .into(holder.rescuerprofileimg);

            // Set data
            holder.name.setText(name);
            holder.number.setText(number);
            holder.location.setText(location);

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