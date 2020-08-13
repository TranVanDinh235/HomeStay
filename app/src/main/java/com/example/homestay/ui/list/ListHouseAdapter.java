package com.example.homestay.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.City;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHouseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<House> mHouseList;

    public ListHouseAdapter(List<House> mHouseList){
        this.mHouseList = mHouseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
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

    public void addItem(List<House> houseList){
        mHouseList.addAll(houseList);
        notifyDataSetChanged();
    }

    public void clear(){
        mHouseList.clear();
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback){
        this.mCallback = callback;
    }

    public interface Callback {
        void onItemClick(int houseId);
    }

    public class ViewHolder extends BaseViewHolder implements View.OnClickListener {

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
        TextView mReviewTextView;

        @BindView(R.id.item_house_stars)
        AppCompatRatingBar mRatingBar;

        @BindView(R.id.item_house_old_rate)
        TextView mOldRateTextView;

        @BindView(R.id.item_house_new_rate)
        TextView mNewRateTextView;

        private House item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void clear() {
            photoImageView.setImageDrawable(null);
            titleTextView.setText("");
            typeTextView.setText("");
            detailTextView.setText("");
            addressTextView.setText("");
            mReviewTextView.setText("");
            mOldRateTextView.setText("");
            mNewRateTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            item = mHouseList.get(position);
            if(item.getPhoto() != null && !item.getPhoto().equalsIgnoreCase("")){
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(photoImageView);
            }

            if(item.getTitle() != null) titleTextView.setText(StringUtils.cutString(item.getTitle(), 30));

            String type = CommonUtils.getHouseType(item.getType());
            typeTextView.setText(type);

            if(item.getAddress() != null)
            addressTextView.setText(item.getAddress());

            String detail = item.getBathrooms() + " phòng tắm - " + item.getBedrooms() +" phòng ngủ - " + item.getBeds() + " giường";
            detailTextView.setText(detail);

            if(item.getTotalReview() > 0){
                String review = " (" + item.getTotalReview() + ")";
                mReviewTextView.setText(review);
                mRatingBar.setRating(item.getRating());
                mReviewTextView.setVisibility(View.VISIBLE);
                mRatingBar.setVisibility(View.VISIBLE);
            } else {
                mReviewTextView.setVisibility(View.GONE);
                mRatingBar.setVisibility(View.GONE);
            }

            if(item.getPrice() != null) {
                if (item.getPromotion() == 0) {
                    mNewRateTextView.setText(StringUtils.toRate(item.getPrice()));

                    mOldRateTextView.setVisibility(View.GONE);
                    mNewRateTextView.setVisibility(View.VISIBLE);
                } else {
                    mOldRateTextView.setText(StringUtils.toRate(item.getPrice()));
                    mNewRateTextView.setText(StringUtils.toRate(item.getPrice(), item.getPromotion()));

                    mOldRateTextView.setVisibility(View.VISIBLE);
                    mNewRateTextView.setVisibility(View.VISIBLE);
                }
            }

        }

        @Override
        public void onClick(View v) {
            mCallback.onItemClick(item.getId());
        }
    }
}
