package com.example.homestay.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.model.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.ui.discover.adapter.TopicAdapter;
import com.example.homestay.utils.CommonUtils;

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
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house_view, parent, false));
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

        @BindView(R.id.item_house_photo)
        ImageView photoImageView;

        @BindView(R.id.item_house_title)
        TextView titleTextView;

        @BindView(R.id.item_house_type)
        TextView typeTextView;

        @BindView(R.id.item_house_detail)
        TextView detailTextView;

        @BindView(R.id.item_house_address)
        TextView addressTextView;

        @BindView(R.id.item_house_review)
        TextView reviewTextView;

        @BindView(R.id.item_house_rate)
        TextView rateTextView;

        @BindView(R.id.item_house_new_rate)
        TextView newRateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            photoImageView.setImageDrawable(null);
            titleTextView.setText("");
            typeTextView.setText("");
            detailTextView.setText("");
            addressTextView.setText("");
            reviewTextView.setText("");
            rateTextView.setText("");
            newRateTextView.setText("");
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
