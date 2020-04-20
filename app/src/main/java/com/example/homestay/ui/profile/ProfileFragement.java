package com.example.homestay.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseFragment;

@PerActivity
public class ProfileFragement extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        return root;
    }

    @Override
    protected void setUp(View view) {

    }
}
