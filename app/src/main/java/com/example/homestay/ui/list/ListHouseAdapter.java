package com.example.homestay.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.model.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.CommonUtils;
import com.joooonho.SelectableRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHouseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<House> mHouseList;

    ListHouseAdapter(List<House> mHouseList){
        this.mHouseList = mHouseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListHouseAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_large, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_house_large_photo)
        SelectableRoundedImageView photoImageView;

        @BindView(R.id.item_house_large_title)
        TextView titleTextView;

        @BindView(R.id.item_house_large_rate)
        TextView rateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            photoImageView.setImageDrawable(null);
            titleTextView.setText("");
            rateTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final House item = mHouseList.get(position);
            if(item.getPhoto() != null){
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(photoImageView);
            }

            if(item.getTitle() != null) titleTextView.setText(item.getTitle());

            String type = CommonUtils.getHouseType(item.getType());
            titleTextView.setText(type);

        }
    }
}
