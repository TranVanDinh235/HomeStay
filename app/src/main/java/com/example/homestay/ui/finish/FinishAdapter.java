package com.example.homestay.ui.finish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.Booking;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinishAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Booking> mHouseList;

    private Callback mCallback;

    public FinishAdapter(List<Booking> houses){
        this.mHouseList = houses;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_finish, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void clear(){
        mHouseList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public void addItem(List<Booking> houseList){
        mHouseList.addAll(houseList);
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback){
        this.mCallback = callback;
    }

    public interface Callback {
        void onItemClick(int houseId);
    }

    public class ViewHolder extends BaseViewHolder implements View.OnClickListener{

        @BindView(R.id.item_finish_address)
        TextView mAddressTextView;

        @BindView(R.id.item_finish_title)
        TextView mTitleTextView;

        @BindView(R.id.item_finish_photo)
        ImageView mPhotoImageView;

        @BindView(R.id.item_finish_review)
        TextView mReviewTextView;

        @BindView(R.id.item_finish_price)
        TextView mPriceTextView;

        @BindView(R.id.item_finish_old_price)
        TextView mOldPriceTextView;

        @BindView(R.id.item_finish_state)
        TextView mStateTextView;

        @BindView(R.id.item_finish_time)
        TextView mTimeTextView;

        @BindView(R.id.item_finish_rating)
        RatingBar mRatingBar;

        private Booking mHouse;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            mHouse = mHouseList.get(position);

            if(mHouse.getAddress() != null) {
                mAddressTextView.setText(mHouse.getAddress());
            }

            if(mHouse.getTitle() != null) {
                mTitleTextView.setText(StringUtils.cutString(mHouse.getTitle(), 50));
            }

            if(mHouse.getPrice() != null) {
                if (mHouse.getPromotion() == 0) {
                    mPriceTextView.setText(StringUtils.toRate(mHouse.getPrice()));

                    mOldPriceTextView.setVisibility(View.GONE);
                    mPriceTextView.setVisibility(View.VISIBLE);
                } else {
                    mOldPriceTextView.setText(StringUtils.toRate(mHouse.getPrice()));
                    mPriceTextView.setText(StringUtils.toRate(mHouse.getPrice(), mHouse.getPromotion()));

                    mOldPriceTextView.setVisibility(View.VISIBLE);
                    mPriceTextView.setVisibility(View.VISIBLE);
                }
            }

            if(mHouse.getTotalReview() == 0){
                mRatingBar.setVisibility(View.GONE);
                mReviewTextView.setVisibility(View.GONE);
            } else {
                String review = mHouse.getTotalReview() + " nhận xét";
                mReviewTextView.setText(review);
                mRatingBar.setRating(mHouse.getRating());
                mRatingBar.setVisibility(View.VISIBLE);
                mReviewTextView.setVisibility(View.VISIBLE);
            }

            if(mHouse.getState() == 1){
                mStateTextView.setText(R.string.success);
                mStateTextView.setTextColor(itemView.getResources().getColor(R.color.colorPrimary));
            } else {
                mStateTextView.setText(R.string.fail);
                mStateTextView.setTextColor(itemView.getResources().getColor(R.color.red50_400));
            }

            if(mHouse.getPhoto() != null && !mHouse.getPhoto().equalsIgnoreCase("")){
                Glide.with(itemView.getContext())
                        .load(mHouse.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(mPhotoImageView);
            }

            String time = DateTimeUtils.houseDateToString(mHouse.getStartDate()) + " - "
                    + DateTimeUtils.houseDateToString(mHouse.getEndDate());

            mTimeTextView.setText(time);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
