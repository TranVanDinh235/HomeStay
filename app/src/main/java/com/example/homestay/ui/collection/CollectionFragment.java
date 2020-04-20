package com.example.homestay.ui.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homestay.R;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseFragment;

import javax.inject.Inject;

@PerActivity
public class CollectionFragment extends BaseFragment {

    @Inject
    CollectionPresenter<CollectionView> presenter;

    @Inject
    CollectionAdapter adapter;

    @Inject
    LinearLayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_collection, container, false);

        return root;
    }

    @Override
    protected void setUp(View view) {

    }
}