package com.example.sbdfinal.homefragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbdfinal.R;
import com.example.sbdfinal.RescuerListActivity;
import com.example.sbdfinal.RescuerRequestActivity;


public class RescuerFragment extends Fragment {

    TextView number1, number2, number3, number4;
    LinearLayout callicon1, callicon2, callicon3, callicon4;
    String num1, num2, num3, num4;
    AppCompatButton seemorebtn, reqrescuer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rescuer, container, false);

        seemorebtn = view.findViewById(R.id.seemorebtn);
        reqrescuer = view.findViewById(R.id.reqrescuer);
        number1 = view.findViewById(R.id.number1);
        number2 = view.findViewById(R.id.number2);
        number3 = view.findViewById(R.id.number3);
        number4 = view.findViewById(R.id.number4);
        callicon1 = view.findViewById(R.id.callicon1);
        callicon2 = view.findViewById(R.id.callicon2);
        callicon3 = view.findViewById(R.id.callicon3);
        callicon4 = view.findViewById(R.id.callicon4);

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

        num1 = number1.getText().toString();
        num2 = number2.getText().toString();
        num3 = number3.getText().toString();
        num4 = number4.getText().toString();

        callicon1.setOnClickListener(V->{
            String phoneNumber = "tel:" + num1; // Replace with the phone number you want to call
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse(phoneNumber));

            if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(dialIntent); // Open the dialer with the phone number pre-filled
            } else {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", num1); // Only the number, not "tel:"
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
            }
        });

        callicon2.setOnClickListener(V->{
            String phoneNumber = "tel:" + num2; // Replace with the phone number you want to call
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse(phoneNumber));

            if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(dialIntent); // Open the dialer with the phone number pre-filled
            } else {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", num2); // Only the number, not "tel:"
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
            }
        });

        callicon3.setOnClickListener(V->{
            String phoneNumber = "tel:" + num3; // Replace with the phone number you want to call
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse(phoneNumber));

            if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(dialIntent); // Open the dialer with the phone number pre-filled
            } else {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", num3); // Only the number, not "tel:"
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
            }
        });

        callicon4.setOnClickListener(V->{
            String phoneNumber = "tel:" + num4; // Replace with the phone number you want to call
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse(phoneNumber));

            if (dialIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(dialIntent); // Open the dialer with the phone number pre-filled
            } else {
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("phone number", num4); // Only the number, not "tel:"
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getContext(), "No dialer app found. Number copied to clipboard.", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


}