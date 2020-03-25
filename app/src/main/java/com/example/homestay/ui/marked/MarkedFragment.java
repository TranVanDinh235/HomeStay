package com.example.homestay.ui.marked;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homestay.R;
import com.example.homestay.di.PerActivity;

import javax.inject.Inject;

@PerActivity
public class MarkedFragment extends Fragment {

    @Inject
    MarkedPresenter<MarkedView> presenter;

    @Inject
    MarkedAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_marked, container, false);

        return root;
    }
}