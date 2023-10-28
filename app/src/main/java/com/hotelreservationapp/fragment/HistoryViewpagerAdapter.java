package com.hotelreservationapp.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HistoryViewpagerAdapter extends FragmentStatePagerAdapter {
    public HistoryViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CurrentFragment();
            case 1:
                return new CompletedFragment();
            case 2:
                return new CanceledFragment();
            default:
                return new CurrentFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position) {
            case 0:
                title = "Current";
                break;
            case 1:
                title = "Completed";
                break;
            case 2:
                title = "Canceled";
                break;

        }
        return title;
    }
}
