package com.example.homestay.ui.discover.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.ui.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiscoverAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    private List<DiscoverResponse.Item> mItemDiscoverList;

    public DiscoverAdapter(List<DiscoverResponse.Item> mItemList){
        this.mItemDiscoverList = mItemList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discover_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mItemDiscoverList != null && mItemDiscoverList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public int getItemCount() {
        if (mItemDiscoverList != null && mItemDiscoverList.size() > 0) {
            return mItemDiscoverList.size();
        } else {
            return 1;
        }
    }

    public interface Callback {
        void onHomeEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.photo_item_discover)
        ImageView photoImageView;

        @BindView(R.id.title_discover)
        TextView titleTextView;

        @BindView(R.id.sub_title_discover) TextView subTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
            photoImageView.setImageDrawable(null);
            titleTextView.setText("");
            subTitleTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final DiscoverResponse.Item item = mItemDiscoverList.get(position);

            if (item.getPhoto() != null) {
                Glide.with(itemView.getContext())
                        .load(item.getPhoto())
                        .asBitmap()
                        .centerCrop()
                        .into(photoImageView);
            }

            if(item.getTitle() != null) {
                titleTextView.setText(item.getTitle());
            }

            if(item.getSubTitle() != null){
                subTitleTextView.setText(item.getSubTitle());
            }
        }
    }

    public class EmptyViewHolder extends BaseViewHolder{

        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if (mCallback != null)
                mCallback.onHomeEmptyViewRetryClick();
        }
    }
}
