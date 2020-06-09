package com.example.homestay.ui.house.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseFragment;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

@PerActivity
public class HouseRulesFragment extends BaseFragment {

    private final House mHouse;

    @BindView(R.id.layout_house_rules_tv)
    TextView mHouseRulesTextView;

    public HouseRulesFragment(House house){
        this.mHouse = house;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_house_rules, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected void setUp(View view) {
        mHouseRulesTextView.setText(mHouse.getHouseRules());
    }
}
