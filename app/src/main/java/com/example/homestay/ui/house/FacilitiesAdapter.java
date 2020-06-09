package com.example.homestay.ui.house;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.IconUtil;
import com.example.homestay.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FacilitiesAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private List<String> facilities;

    public FacilitiesAdapter(House house, int type) {
        switch (type) {
            case 0: {
                this.facilities = StringUtils.strToList(house.getFacilities());
                break;
            }
            case 1: {
                this.facilities = StringUtils.strToList(house.getRoomFacilities());
                break;
            }
            case 2: {
                this.facilities = StringUtils.strToList(house.getHouseRules());
                break;
            }
            case 3: {
                this.facilities = StringUtils.strToList(house.getSpecialFacilities());
                break;
            }
            case 4: {
                this.facilities = StringUtils.strToList(house.getFamilies());
                break;
            }
            case 5: {
                this.facilities = StringUtils.strToList(house.getEntertainment());
                break;
            }
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facilities, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.item_facilities_icon)
        ImageView iconImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);
            iconImageView.setImageResource(IconUtil.getIconResource(facilities.get(position)));
        }

        @Override
        protected void clear() {

        }
    }
}
