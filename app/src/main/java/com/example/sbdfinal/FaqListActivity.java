package com.example.sbdfinal;

import static java.security.AccessController.getContext;

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

public class FaqListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchview;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;
    ImageView backbtn;
    TextView tooltitel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_list);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(FaqListActivity.this));

        // Initialize the adapter with the populated data
        recyclerView.setAdapter(new FaqListActivity.myAdapter(FaqListActivity.this, arrayList));




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

                FaqListActivity.myAdapter adapter = (FaqListActivity.myAdapter) recyclerView.getAdapter();
                adapter.filter(newText);// Call filter method of the adapter
                return false;
            }
        });


        String url = "http://192.168.0.114/Apps/faq.json";

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {

                        try {

                            for (int x=0; x<jsonArray.length(); x++){

                                JSONObject jsnarray = jsonArray.getJSONObject(x);

                                Log.d("Sresponse", jsnarray.toString());



                                String question = jsnarray.getString("question");
                                String answer = jsnarray.getString("answer");
                                String author = jsnarray.getString("author");



                                hashMap=new HashMap<>();
                                hashMap.put("question",question);
                                hashMap.put("answer",answer);
                                hashMap.put("author",author);
                                arrayList.add(hashMap);

                                FaqListActivity.myAdapter adapter = new FaqListActivity.myAdapter(FaqListActivity .this, arrayList);
                                recyclerView.setAdapter(adapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(FaqListActivity.this));


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

        RequestQueue queue = Volley.newRequestQueue(FaqListActivity.this);
        queue.add(arrayRequest);


    }

    private class myAdapter extends RecyclerView.Adapter<FaqListActivity.myAdapter.myViewholder> {
        private Context context;
        private List<HashMap<String, String>> originalList;
        private List<HashMap<String, String>> filteredList;

        // Constructor
        public myAdapter(Context context, List<HashMap<String, String>> arrayList) {
            this.context = context;
            this.originalList = new ArrayList<>(arrayList); // Store the original list
            this.filteredList = new ArrayList<>(arrayList); // Copy the data for filtering
        }

        private SparseBooleanArray expandedPositions = new SparseBooleanArray();
        private int expandedPosition = RecyclerView.NO_POSITION;
        public class myViewholder extends RecyclerView.ViewHolder {
            LinearLayout motherLayout, discLayout;
            RelativeLayout itemClicked;
            ImageView arrowImg;
            TextView question, answer, author;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                motherLayout = itemView.findViewById(R.id.motherLayout);
                itemClicked = itemView.findViewById(R.id.itemClicked);
                arrowImg = itemView.findViewById(R.id.arrowImg);
                discLayout = itemView.findViewById(R.id.discLayout);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.answer);
                author = itemView.findViewById(R.id.author);

            }
        }

        @NonNull
        @Override
        public FaqListActivity.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View myView = inflater.inflate(R.layout.faqitems, parent, false);
            return new FaqListActivity.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull FaqListActivity.myAdapter.myViewholder holder, int position) {
            HashMap<String, String> hashMap = filteredList.get(position);

            String question = hashMap.get("question");
            String answer = hashMap.get("answer");
            String author = hashMap.get("author");

            holder.question.setText(question);
            holder.answer.setText(answer);
            holder.author.setText(author);


// Set the visibility and arrow icon based on the expandedPositions state

            boolean isExpanded = expandedPosition == position;

            boolean isDarkMode = (FaqListActivity.this.getResources().getConfiguration().uiMode
                    & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;

            holder.discLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

            if (isDarkMode) {
                holder.motherLayout.setBackgroundColor(isExpanded ? Color.parseColor("#724CAF50") : Color.parseColor("#33000000"));
            }else {
                holder.motherLayout.setBackgroundColor(isExpanded ? Color.parseColor("#724CAF50") : Color.parseColor("#FFFFF0"));
            }


            holder.arrowImg.setImageResource(isExpanded ? R.drawable.uparrow : R.drawable.downarrow);

            // Handle click listener to expand/collapse the item
            holder.itemClicked.setOnClickListener(v -> {
                boolean currentlyExpanded = expandedPositions.get(position, false);
                int previousExpandedPosition = expandedPosition;

                // Set the new expanded position (collapse if clicking the same item)
                expandedPosition = isExpanded ? RecyclerView.NO_POSITION : position;

                // Notify changes to collapse the previous item and expand the new one
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(expandedPosition);
            });

            //item er animation control
            holder.itemView.setAnimation(null);
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
                    String question = item.get("question");
                    String answer = item.get("answer");

                    // Case-insensitive check for name and location
                    if ((question != null && question.toLowerCase().contains(query.toLowerCase()))) {
                        filteredList.add(item); // Add the item to filteredList if it matches
                    }
                }
            }

            notifyDataSetChanged(); // Notify adapter to refresh the RecyclerView with filtered data
        }
    }


}