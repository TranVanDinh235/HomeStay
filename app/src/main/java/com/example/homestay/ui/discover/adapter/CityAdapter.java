package com.example.homestay.ui.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.City;
import com.example.homestay.ui.base.BaseViewHolder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<City> mCityList;

    public CityAdapter(List<City> mCityList) {
        this.mCityList = mCityList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void addItem(List<City> cityList){
        mCityList.addAll(cityList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onCityItemClick();
    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_city_photo)
        ImageView itemCityPhotoImageView;

        @BindView(R.id.item_city_name)
        TextView itemCityNameTextView;

        @BindView(R.id.item_city_title)
        TextView itemCityTitleTextView;

        @BindView(R.id.item_city_load_more)
        ExtendedFloatingActionButton loadMoreButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            itemCityPhotoImageView.setImageDrawable(null);
            itemCityNameTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final City item = mCityList.get(position);

            if (item.getPhoto() != null) {
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(itemCityPhotoImageView);
            }

            if(item.getName() != null) {
                itemCityNameTextView.setText(item.getName());
            }

            if(item.getTitle() != null){
                itemCityTitleTextView.setText(item.getTitle());
            }

        }
    }
}
