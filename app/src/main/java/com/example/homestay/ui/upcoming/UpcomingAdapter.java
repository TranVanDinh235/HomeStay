package com.example.homestay.ui.upcoming;

import android.graphics.Bitmap;
import android.graphics.Color;
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
import com.example.homestay.data.network.entity.QRCode;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.ui.list.ListHouseAdapter;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.StringUtils;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpcomingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Booking> mHouseList;

    private Callback mCallback;

    public UpcomingAdapter(List<Booking> houses) {
        this.mHouseList = houses;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upcoming, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void clear() {
        mHouseList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public void addItem(List<Booking> houseList) {
        mHouseList.addAll(houseList);
        notifyDataSetChanged();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public interface Callback {
        void onItemClick(Booking house);

        void onGetQRCode(Bitmap bitmap);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.item_upcoming_address)
        TextView mAddressTextView;

        @BindView(R.id.item_upcoming_title)
        TextView mTitleTextView;

        @BindView(R.id.item_upcoming_photo)
        ImageView mPhotoImageView;

        @BindView(R.id.item_upcoming_review)
        TextView mReviewTextView;

        @BindView(R.id.item_upcoming_price)
        TextView mPriceTextView;

        @BindView(R.id.item_upcoming_old_price)
        TextView mOldPriceTextView;

        @BindView(R.id.item_upcoming_qr_code)
        TextView mGetQRCodeTextView;

        @BindView(R.id.item_upcoming_time)
        TextView mTimeTextView;

        @BindView(R.id.item_upcoming_rating)
        RatingBar mRatingBar;

        private Booking mHouse;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.onItemClick(mHouse);
                }
            });
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            mHouse = mHouseList.get(position);

            if (mHouse.getAddress() != null) {
                mAddressTextView.setText(mHouse.getAddress());
            }

            if (mHouse.getTitle() != null) {
                mTitleTextView.setText(StringUtils.cutString(mHouse.getTitle(), 50));
            }

            if (mHouse.getPrice() != null) {
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

            if (mHouse.getTotalReview() == 0) {
                mRatingBar.setVisibility(View.GONE);
                mReviewTextView.setVisibility(View.GONE);
            } else {
                String review = mHouse.getTotalReview() + " nhận xét";
                mReviewTextView.setText(review);
                mRatingBar.setRating(mHouse.getRating());
                mRatingBar.setVisibility(View.VISIBLE);
                mReviewTextView.setVisibility(View.VISIBLE);
            }

            if (mHouse.getPhoto() != null && !mHouse.getPhoto().equalsIgnoreCase("")) {
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

        @OnClick(R.id.item_upcoming_qr_code)
        public void onQRCodeClick(View v) {
            QRCodeWriter writer = new QRCodeWriter();
            try {
                QRCode qrCode = new QRCode(String.valueOf(mHouse.getId()), String.valueOf(mHouse.getGuestId()));
                BitMatrix bitMatrix = writer.encode(new Gson().toJson(qrCode), BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
                mCallback.onGetQRCode(bmp);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
