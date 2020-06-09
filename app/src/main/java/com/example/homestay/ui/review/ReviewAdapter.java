package com.example.homestay.ui.review;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.Review;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.TimeUtils;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<Review> mReviewList;

    public ReviewAdapter(List<Review> reviewList){
        this.mReviewList = reviewList;
    }


    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public void addItem(List<Review> reviewList){
        this.mReviewList.addAll(reviewList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder{

        @BindView(R.id.item_review_avatar)
        CircleImageView avatarImageView;

        @BindView(R.id.item_review_name)
        TextView nameTextView;

        @BindView(R.id.item_review_content)
        TextView contentTextView;

        @BindView(R.id.item_review_time)
        TextView timeTextView;

        @BindView(R.id.item_review_rating_tv)
        TextView ratingTextView;

        @BindView(R.id.item_review_rating)
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            final Review item = mReviewList.get(position);

            if (item.getPic_url() != null) {
                Glide.with(itemView.getContext())
                        .load(item.getPic_url())
                        .asBitmap()
                        .centerCrop()
                        .into(avatarImageView);
            }

            if(item.getUser_name() != null){
                nameTextView.setText(item.getUser_name());
            }

            if(item.getContent() != null){
                contentTextView.setText(item.getContent());
            }

            if(item.getTime() != null)
                timeTextView.setText(TimeUtils.timeReview(item.getTime()));

            ratingTextView.setText(CommonUtils.getRatingText(item.getRating()));
            ratingBar.setRating(item.getRating());

        }
    }
}
