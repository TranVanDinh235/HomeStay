package com.example.homestay.ui.trips;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.homestay.ui.favorites.FavoritesFragment;
import com.example.homestay.ui.finish.FinishFragment;
import com.example.homestay.ui.upcoming.UpcomingFragment;
import com.example.homestay.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class TripsTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private int mCurrentPosition = -1;

    public TripsTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new UpcomingFragment());
        fragments.add(new FinishFragment());
        fragments.add(new FavoritesFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return CommonUtils.getTripsTabTitle(position);
    }
}
