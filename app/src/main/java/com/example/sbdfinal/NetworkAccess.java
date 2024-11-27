package com.example.sbdfinal;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class NetworkAccess {


    // Method to check network connectivity
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    // Method to show a custom alert dialog if there is no network connection
    public static void showNoConnectionDialog(final Activity activity, String message) {

        if (!isConnected(activity)) {
            // Use activity.getLayoutInflater() to inflate the custom layout
            LayoutInflater inflater = activity.getLayoutInflater();
            android.view.View customView = inflater.inflate(R.layout.networkaccessdialog, null);

            Button closebutton = customView.findViewById(R.id.closebutton);
            TextView title = customView.findViewById(R.id.title);

            title.setText(message);

            // Initialize AlertDialog.Builder with activity as the context
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setView(customView);
            AlertDialog dialog = builder.create();

            // Set background transparent
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }

            // Disable dismiss on outside touch and back button
//            dialog.setCanceledOnTouchOutside(false);
//            dialog.setCancelable(false);

            // Set button action to dismiss the dialog
            closebutton.setOnClickListener(v -> dialog.dismiss());

            dialog.show();
        }
    }


}
