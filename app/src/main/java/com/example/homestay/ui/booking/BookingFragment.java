package com.example.homestay.ui.booking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseFragment;

public class BookingFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_booking, container, false);

        return root;
    }


    @Override
    protected void setUp(View view) {

    }
}
