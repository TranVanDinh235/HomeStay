package com.example.homestay.ui.collection;

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
import com.example.homestay.ui.discover.adapter.CityAdapter;
import com.example.homestay.utils.CommonUtils;
import com.joooonho.SelectableRoundedImageView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private CityAdapter.Callback mCallback;
    private List<House> mHouseList;

    @Inject
    public CollectionAdapter(List<House> mHouseList){
        this.mHouseList = mHouseList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_house_small, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    public void addItem(List<House> mHouseList){
        this.mHouseList.addAll(mHouseList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_house_small_photo)
        RoundedImageView itemHousePhotoImageView;

        @BindView(R.id.item_house_small_title)
        TextView itemHouseTitleTextView;

        @BindView(R.id.item_house_small_old_rate)
        TextView itemHouseOldRateTextView;

        @BindView(R.id.item_house_small_rate)
        TextView itemHouseRateTextView;

        @BindView(R.id.item_house_small_star)
        TextView itemHouseStarTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final House item = mHouseList.get(position);

            if (item.getPhoto() != null) {
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(itemHousePhotoImageView);
            }
            if (item.getTitle() != null) itemHouseTitleTextView.setText(CommonUtils.cutString(item.getTitle()));

            if (item.getRate() != null) {
                if (item.getPromotion() == 0) {
                    itemHouseRateTextView.setText(CommonUtils.getRate(item.getRate(), 0));
                    itemHouseOldRateTextView.setVisibility(View.GONE);
                } else {
                    itemHouseOldRateTextView.setText(CommonUtils.getRate(item.getRate(), 0));
                    itemHouseOldRateTextView.setVisibility(View.VISIBLE);
                    itemHouseRateTextView.setText(CommonUtils.getRate(item.getRate(), item.getPromotion()));
                }
            }
        }
    }
}
