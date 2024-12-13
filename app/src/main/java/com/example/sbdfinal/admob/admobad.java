package com.example.sbdfinal.admob;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sbdfinal.HomeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class admobad {

    public static void sdkinitialize (Context Context){
        if (!constant.AD_IS_ON) return;

        new Thread(()->{
            MobileAds.initialize(Context, initializationStatus-> {

            });
        }).start();
    }//sdkinitialize end here===========

    public static void setBannerAd (LinearLayout adContainerView, Context context){
        if (!constant.AD_IS_ON) return;

// Create a new ad view.
        AdView adView = new AdView(context);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                Toast.makeText(context, "Ad Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        adView.setAdUnitId(constant.BANNER_AD_ID);
        adView.setAdSize(AdSize.BANNER);

// Replace ad container with new ad view.
        adContainerView.removeAllViews();
        adContainerView.addView(adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


    }

}
