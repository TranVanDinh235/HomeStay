package com.example.homestay.ui.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.ButterKnife;

public class PopularPlaceAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private PopularPlaceAdapter.Callback mCallback;
    private List<DiscoverResponse.PopularPlace> mItemPopularPlaceList;

    public PopularPlaceAdapter(List<DiscoverResponse.PopularPlace> mItemPopularPlaceList){
        this.mItemPopularPlaceList = mItemPopularPlaceList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mItemPopularPlaceList != null && mItemPopularPlaceList.size() > 0) {
            return mItemPopularPlaceList.size();
        } else {
            return 1;
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback{

    }

    public class ViewHolder extends BaseViewHolder{

        ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(itemView);
        }

        @Override
        protected void clear() {

        }
    }
}
