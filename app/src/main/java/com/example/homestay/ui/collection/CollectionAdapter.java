package com.example.homestay.ui.collection;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.ui.base.BaseViewHolder;

import javax.inject.Inject;

public class CollectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    @Inject
    CollectionAdapter(){

    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
