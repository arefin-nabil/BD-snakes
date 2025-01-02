package com.example.sbdfinal.homefragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sbdfinal.HospitalActivity;
import com.example.sbdfinal.PosterActivity;
import com.example.sbdfinal.R;
import com.example.sbdfinal.SnakeBiteList;
import com.example.sbdfinal.SnakeListActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeFragment extends Fragment {
    GridView gridView;
    ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
    HashMap<String, Object> hashMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gridView);


        // Load data into the ArrayList
        hashMapdata();

        gridView.setNumColumns(2);
        // Set up GridView adapter
        MyAdapter adapter = new MyAdapter();
        gridView.setAdapter(adapter);

        // Set item click listener
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            if (position >= 0 && position <= 3) {
                snakelistactivity(position);
            } else if (position == 4) {
                openActivity(SnakeBiteList.class);
            } else if (position == 5) {
                openActivity(HospitalActivity.class);
            } else if (position == 6) {
                openActivity(PosterActivity.class);
            } else if (position == 7) {
                emergencycontactdialog();
            }
        });

        return view;
    }


    //========================= hashmap data created for recyclerview Starts Here ================================
    private void hashMapdata(){
        hashMap = new HashMap<>();
        hashMap.put("titel", "নির্বিষ সাপ");
        hashMap.put("image", R.drawable.nonvenom);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "মৃদু বিষধর সাপ");
        hashMap.put("image", R.drawable.midvenom);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "বিষধর সাপ");
        hashMap.put("image", R.drawable.venom);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "সামুুদ্রিক সাপ");
        hashMap.put("image", R.drawable.seasnake);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "সাপে কাটলে করনীয়");
        hashMap.put("image", R.drawable.snakebite);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "হাসপাতাল");
        hashMap.put("image", R.drawable.hospital);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "পোস্টার ও লিফলেট");
        hashMap.put("image", R.drawable.poster);
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("titel", "জাতীয় জরুরী নাম্বার সমূহ");
        hashMap.put("image", R.drawable.emergency);
        arrayList.add(hashMap);
    }

    //========================= hashmap data created for recyclerview ENDS here ================================







    //=============== Adapter Class created for recyclerview STARTS here================================
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.homeitems, parent, false);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.imageView);
                holder.textView = convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            HashMap<String, Object> item = arrayList.get(position);
            holder.textView.setText((String) item.get("titel"));
            holder.imageView.setImageResource((int) item.get("image"));


            return convertView;
        }

        class ViewHolder {
            TextView textView;
            ImageView imageView;
        }
    }

    private void snakelistactivity(int tabPosition) {
        Intent intent = new Intent(getActivity(), SnakeListActivity.class);
        intent.putExtra("TAB_POSITION", tabPosition);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(getActivity(), activityClass);
        startActivity(intent);
        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private String[] emergencyNumbers = {"999", "333", "16163", "1090", "16430"};

    private void emergencycontactdialog() {
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.emergencynumdialog, null);

        LinearLayout callicon = customView.findViewById(R.id.callicon);
        LinearLayout callicon1 = customView.findViewById(R.id.callicon1);
        LinearLayout callicon2 = customView.findViewById(R.id.callicon2);
        LinearLayout callicon3 = customView.findViewById(R.id.callicon3);
        LinearLayout callicon4 = customView.findViewById(R.id.callicon4);
        Button closebutton = customView.findViewById(R.id.closebutton);
        Button seemorebtn = customView.findViewById(R.id.seemorebtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(customView);
        AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        seemorebtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bangladesh.gov.bd/site/page/aaebba14-f52a-4a3d-98fd-a3f8b911d3d9"));
            startActivity(intent);
            dialog.dismiss();
        });

        closebutton.setOnClickListener(v -> dialog.dismiss());

        callicon.setOnClickListener(v -> callNumber(emergencyNumbers[0]));
        callicon1.setOnClickListener(v -> callNumber(emergencyNumbers[1]));
        callicon2.setOnClickListener(v -> callNumber(emergencyNumbers[2]));
        callicon3.setOnClickListener(v -> callNumber(emergencyNumbers[3]));
        callicon4.setOnClickListener(v -> callNumber(emergencyNumbers[4]));

        dialog.show();
    }

    private void callNumber(String number) {
        String phoneNumber = "tel:" + number;
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(phoneNumber));

        if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("phone number", number);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
        }
    }

}