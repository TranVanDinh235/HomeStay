package com.example.homestay.ui.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.homestay.R;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseFragment;

@PerActivity
public class MessageFragment extends BaseFragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message, container, false);

        return root;
    }

    @Override
    protected void setUp(View view) {

    }
}