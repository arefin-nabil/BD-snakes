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

public class RescuerListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_list);

        recyclerView = findViewById(R.id.recyclerView);

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

        String url = "http://192.168.56.1/Apps/rescuerlist.json";

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

                                myAdapter adapter = new myAdapter();
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
    private class myAdapter extends RecyclerView.Adapter<myAdapter.myViewholder>{


        private class myViewholder extends RecyclerView.ViewHolder{

            TextView name, number, location;
            ImageView rescuerprofileimg;
            LinearLayout callicon;
            public myViewholder(@NonNull View itemView) {
                super(itemView);


                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                location = itemView.findViewById(R.id.location);
                rescuerprofileimg = itemView.findViewById(R.id.rescuerprofileimg);
                callicon = itemView.findViewById(R.id.callicon);


            }
        }


        @NonNull
        @Override
        public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.rescueritems,parent,false);

            return new myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull myViewholder holder, int position) {


            HashMap <String,String> hashMap = arrayList.get(position);

            String sname = hashMap.get("name");
            String snumber = hashMap.get("number");
            String slocation = hashMap.get("location");
            String sprofileimg = hashMap.get("profileimg");

            holder.name.setText(sname);
            holder.number.setText(snumber);
            holder.location.setText(slocation);

            Glide
                    .with(RescuerListActivity.this)
                    .load(sprofileimg)
                    .circleCrop()
                    .placeholder(R.drawable.logo)
                    .into(holder.rescuerprofileimg);

            holder.itemView.startAnimation(AnimationUtils.loadAnimation(RescuerListActivity.this, android.R.anim.slide_in_left));


            holder.callicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = "tel:" + snumber; // Replace with the phone number you want to call
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse(phoneNumber));

                    // Check if there is an app that can handle this intent (like the phone dialer)
                    if (dialIntent.resolveActivity(RescuerListActivity.this.getPackageManager()) != null) {
                        startActivity(dialIntent); // Open the dialer with the phone number pre-filled
                    } else {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("phone number", snumber);
                        clipboard.setPrimaryClip(clip);

                        // Show a toast to notify the user
                        Toast.makeText(RescuerListActivity.this, "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }


}