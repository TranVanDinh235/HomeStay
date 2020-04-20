package com.example.homestay.ui.discover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.model.entity.Topic;
import com.example.homestay.data.network.model.entity.TopicItem;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopicAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback mCallback;
    List<Topic> mTopicList;

    public TopicAdapter(List<Topic> mTopicList) {
        this.mTopicList = mTopicList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopicAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
            return mTopicList.size();
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public void addItem(List<Topic> mTopicList){
        this.mTopicList.addAll(mTopicList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onTopicEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder{
        private Context mContext;

        @BindView(R.id.item_topic_title) TextView mItemTopicTitleTextView;
        @BindView(R.id.item_topic_subtitle) TextView mItemTopicSubtitleTextView;
        @BindView(R.id.item_topic_recycler_view) RecyclerView mItemTopicRecyclerView;

        TopicItemAdapter mTopicItemAdapter;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        @Override
        protected void clear() {
            mItemTopicTitleTextView.setText("");
            mItemTopicSubtitleTextView.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Topic topic = mTopicList.get(position);

            mTopicItemAdapter = new TopicItemAdapter(new ArrayList<TopicItem>());

            if(topic.getTitle() != null){
                mItemTopicTitleTextView.setText(topic.getTitle());
            }

            if(topic.getSubTitle() != null){
                mItemTopicSubtitleTextView.setText(topic.getSubTitle());
            }

            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
            mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mItemTopicRecyclerView.setLayoutManager(mLinearLayoutManager);
            mItemTopicRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mItemTopicRecyclerView.setAdapter(mTopicItemAdapter);

            if(topic.getTopicItem() != null){
                mTopicItemAdapter.addItem(topic.getTopicItem());
            }
        }
    }
}
