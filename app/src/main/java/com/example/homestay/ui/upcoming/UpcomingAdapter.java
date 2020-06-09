package com.example.homestay.ui.upcoming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.ui.list.ListHouseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<House> mHouseList;

    private Callback mCallback;

    public UpcomingAdapter(List<House> houses){
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

    public void clear(){
        mHouseList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mHouseList.size();
    }

    public void addItem(List<House> houseList){
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

        @BindView(R.id.item_upcoming_address)
        TextView mAddressTextView;

        @BindView(R.id.item_upcoming_title)
        TextView mTitleTextView;

        @BindView(R.id.item_upcoming_photo)
        ImageView mPhotoImageView;

        @BindView(R.id.item_upcoming_cancel)
        TextView mCancelTextView;

        @BindView(R.id.item_upcoming_get_qr_code)
        TextView mGetQRCodeTextView;

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
            final House house = mHouseList.get(position);
            mAddressTextView.setText(house.getAddress());
            mTitleTextView.setText(house.getTitle());
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == mGetQRCodeTextView.getId()){

            }
        }
    }
}
