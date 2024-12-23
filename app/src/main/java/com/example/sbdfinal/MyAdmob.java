package com.example.sbdfinal;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bdtopcoder.smartadmob.AdmobAdUnit;

public class MyAdmob {
    public static void checkAdStatus(Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://www.arefinnabil.site/admob.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Update ad status based on response
                        if (response.contains("AD_IS_ON")) {
                            AdmobAdUnit.ADMOB_AD_IS_ON = true;
                            AdmobAdUnit.ADMOB_BANNER_AD = "ca-app-pub-3940256099942544/9214589741";
                            AdmobAdUnit.ADMOB_INTERSTITIAL_AD = "ca-app-pub-3940256099942544/1033173712";
                        }else{
                            AdmobAdUnit.ADMOB_AD_IS_ON = false;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
}
