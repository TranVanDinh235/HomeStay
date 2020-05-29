package com.example.homestay.ui.discover.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.TopicItem;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.ui.list.ListHouseActivity;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.utils.AppConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.homestay.utils.AppConstants.TAG_LIST_HOUSE_TYPE;
import static com.example.homestay.utils.AppConstants.TAG_TOPIC_ITEM_ID;

public class TopicItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Callback mCallback;
    private List<TopicItem> mTopicItemList;

    public TopicItemAdapter(List<TopicItem> mItemList){
        this.mTopicItemList = mItemList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_item_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        return mTopicItemList.size();
    }

    @Override
    public int getItemCount() {
        return mTopicItemList.size();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void addItem(List<TopicItem> mTopicItemList){
        this.mTopicItemList.addAll(mTopicItemList);
        notifyDataSetChanged();
    }

    public interface Callback {

    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_topic_item_photo)
        ImageView photoImageView;

        @BindView(R.id.item_topic_item_title)
        TextView titleTextView;

        @BindView(R.id.item_topic_item_subtitle) TextView subTitleTextView;

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

            final TopicItem item = mTopicItemList.get(position);

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

            itemView.setOnClickListener(view -> {
                Intent i = new Intent(itemView.getContext(), ListHouseActivity.class);
                i.putExtra(TAG_LIST_HOUSE_TYPE, AppConstants.TAG_TOPIC_ITEM);
                i.putExtra(TAG_TOPIC_ITEM_ID, String.valueOf(item.getId()));
                Activity activity = (MainActivity) itemView.getContext();
                activity.startActivityForResult(i, 12);
            });
        }
    }
}
