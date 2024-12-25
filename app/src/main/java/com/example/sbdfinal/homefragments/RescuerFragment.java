package com.example.sbdfinal.homefragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbdfinal.R;
import com.example.sbdfinal.RescuerListActivity;
import com.example.sbdfinal.RescuerRequestActivity;


import java.util.ArrayList;
import java.util.HashMap;


public class RescuerFragment extends Fragment {

    RecyclerView recyclerView;
    AppCompatButton seemorebtn, reqrescuer;
    ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
    HashMap<String, Object> hashMap;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rescuer, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        seemorebtn = view.findViewById(R.id.seemorebtn);
        reqrescuer = view.findViewById(R.id.reqrescuer);

        //================= See more button ============================
        seemorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RescuerListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //================= See more button ============================

        //================= Rescuer request button ============================
        reqrescuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RescuerRequestActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //================= Rescuer Request button ============================


        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RescuerFragment.myAdapter());


        RescuerFragment.myAdapter adapter = new RescuerFragment.myAdapter();
        recyclerView.setAdapter(adapter);

        //Hashmap data function
        hashMapdata();


        return view;
    }

    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata() {

        hashMap = new HashMap<>();
        hashMap.put("name", "Wildlife And Snake Rescue Team in Bangladesh - WSRTBD");
        hashMap.put("number", "01722938276");
        hashMap.put("profileimg", R.drawable.wsrtbd);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Deep Ecology And Snake Conservation Foundation");
        hashMap.put("number", "01718414517");
        hashMap.put("profileimg", R.drawable.deep);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Society for Snake & Snakebite Awareness (3SA)");
        hashMap.put("number", "01710364864");
        hashMap.put("profileimg", R.drawable.sssa);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name", "Snake Rescue Team Bangladesh - (SRTBD)");
        hashMap.put("number", "01614589111");
        hashMap.put("profileimg", R.drawable.srtbd);
        arrayList.add(hashMap);

    }
    //========================= hashmap data created for recyclerview ENDS here ================================


    //=============== Adapter Class created for recyclerview STARTS here================================
    private class myAdapter extends RecyclerView.Adapter<RescuerFragment.myAdapter.myViewholder> {

        private class myViewholder extends RecyclerView.ViewHolder {
            //item view er variable nibo eikhane
            ImageView profileimg;
            TextView name, number;
            LinearLayout locationlayout, callicon;

            public myViewholder(@NonNull View itemView) {
                super(itemView);

                profileimg = itemView.findViewById(R.id.rescuerprofileimg);
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                callicon = itemView.findViewById(R.id.callicon);
                locationlayout = itemView.findViewById(R.id.locationlayout);

            }
        }

        @NonNull
        @Override
        public RescuerFragment.myAdapter.myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //item view nibo eikhane,, item view er layout inflate korbo
            LayoutInflater inflater = getLayoutInflater();
            View myView = inflater.inflate(R.layout.rescueritems, parent, false);
            return new RescuerFragment.myAdapter.myViewholder(myView);
        }

        @Override
        public void onBindViewHolder(@NonNull RescuerFragment.myAdapter.myViewholder holder, int position) {
            //hashmap theke ene item view e data set korbo,,
            HashMap<String, Object> hashMap = arrayList.get(position);
            String name = (String) hashMap.get("name");
            String number = (String) hashMap.get("number");
            int image = (int) hashMap.get("profileimg");

            holder.profileimg.setImageResource(image);
            holder.name.setText(name);
            holder.number.append(number);

            holder.locationlayout.setVisibility(View.GONE);

            holder.callicon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phoneNumber = "tel:" + number; // Replace with the phone number you want to call
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse(phoneNumber));

                    // Check if there is an app that can handle this intent (like the phone dialer)
                    if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(dialIntent); // Open the dialer with the phone number pre-filled
                    } else {
                        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("phone number", number); // Only the number, not "tel:"
                        clipboard.setPrimaryClip(clip);

                        // Show a toast to notify the user
                        Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
                    }
                }
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