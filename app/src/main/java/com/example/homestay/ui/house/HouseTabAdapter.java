package com.example.homestay.ui.house;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.homestay.data.network.entity.House;
import com.example.homestay.ui.house.fragment.FacilitiesFragment;
import com.example.homestay.ui.house.fragment.HouseRulesFragment;
import com.example.homestay.ui.house.fragment.PriceFragment;
import com.example.homestay.ui.house.fragment.DetailFragment;
import com.example.homestay.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class HouseTabAdapter extends FragmentPagerAdapter {

    private House mHouse;
    private List<Fragment> fragments;
    private int mCurrentPosition = -1;

    public HouseTabAdapter(@NonNull FragmentManager fm, House mHouse) {
        super(fm);
        this.mHouse = mHouse;
        this.fragments = new ArrayList<Fragment>();
        fragments.add(new DetailFragment(mHouse));
        fragments.add(new FacilitiesFragment(mHouse));
        fragments.add(new PriceFragment(mHouse));
        fragments.add(new HouseRulesFragment(mHouse));
    }

    public HouseTabAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public HouseTabAdapter(@NonNull FragmentManager fm, int behavior, House house) {
        super(fm, behavior);
        this.mHouse = house;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            WrapperViewPager pager = (WrapperViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
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
        return CommonUtils.getHouseTabTitle(position);
    }
}
