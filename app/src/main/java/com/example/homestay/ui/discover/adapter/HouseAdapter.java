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
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.CommonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private HouseAdapter.Callback mCallback;
    private List<DiscoverResponse.House> mHouseList;

    public HouseAdapter(List<DiscoverResponse.House> mHouseList){
        this.mHouseList = mHouseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if (mHouseList != null && mHouseList.size() > 0) {
            return mHouseList.size();
        } else {
            return 1;
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }


    public interface Callback {
        void onHouseClickListener();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.house_photo)
        ImageView housePhotoImageView;

        @BindView(R.id.house_title)
        TextView houseTitleTextView;

        @BindView(R.id.house_original_price)
        TextView houseOriginalPriceTextView;

        @BindView(R.id.house_price)
        TextView housePriceTextView;

        @BindView(R.id.house_type)
        TextView houseTypeTextView;

        @BindView(R.id.house_address)
        TextView houseAddressTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            housePhotoImageView.setImageDrawable(null);
            houseTitleTextView.setText("");
            housePriceTextView.setText("");
            houseOriginalPriceTextView.setText("");
            houseTypeTextView.setText("");
            houseAddressTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final DiscoverResponse.House item = mHouseList.get(position);

            if (item.getPhoto() != null) {
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(housePhotoImageView);
            }

            if (item.getTitle() != null) {
                houseTitleTextView.setText(item.getTitle());
            }

            if (item.getOriginalPrice() != null) {
                houseOriginalPriceTextView.setText(item.getOriginalPrice());
            }

            if (item.getPrice() != null) {
                housePriceTextView.setText(item.getPrice());
            }

            houseTypeTextView.setText(CommonUtils.getHouseType(item.getType()));

            if (item.getAddress() != null) {
                houseAddressTextView.setText(item.getAddress());
            }
        }
    }
}
