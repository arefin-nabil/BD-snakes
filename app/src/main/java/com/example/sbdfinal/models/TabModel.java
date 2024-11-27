package com.example.sbdfinal.models;

import androidx.fragment.app.Fragment;

public class TabModel {
    private String titel;

    public TabModel(String titel, Class<? extends Fragment> fragmentClass) {
        this.titel = titel;
        FragmentClass = fragmentClass;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return FragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        FragmentClass = fragmentClass;
    }

    private Class<? extends Fragment> FragmentClass;
}
