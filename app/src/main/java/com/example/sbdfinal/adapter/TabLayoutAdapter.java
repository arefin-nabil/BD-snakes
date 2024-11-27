package com.example.sbdfinal.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sbdfinal.models.TabModel;

import java.util.List;

public class TabLayoutAdapter extends FragmentStateAdapter {

    private final List<TabModel> tabs;

    public TabLayoutAdapter(@NonNull FragmentActivity fragmentActivity, List<TabModel> tabs) {
        super(fragmentActivity);
        this.tabs = tabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        try {
            return tabs.get(position).getFragmentClass().newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return tabs.size();
    }
}
