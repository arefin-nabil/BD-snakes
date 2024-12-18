package com.example.sbdfinal;

import com.bdtopcoder.smartadmob.AdmobAdUnit;

public class MyAdmob {
    public static void loadAdUnit(){
        AdmobAdUnit.ADMOB_AD_IS_ON = true;
        AdmobAdUnit.ADMOB_BANNER_AD = "ca-app-pub-3940256099942544/9214589741";
        AdmobAdUnit.ADMOB_INTERSTITIAL_AD = "ca-app-pub-3940256099942544/1033173712";
    }
}
