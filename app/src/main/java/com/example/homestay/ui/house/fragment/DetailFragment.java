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
public class DetailFragment extends BaseFragment {

    @BindView(R.id.layout_detail_bath_room_tv)
    TextView bathroomTextView;

    @BindView(R.id.layout_detail_bed_room_tv)
    TextView bedroomTextView;

    @BindView(R.id.layout_detail_bed_tv)
    TextView bedTextView;

    @BindView(R.id.layout_detail_guest_tv)
    TextView guestTextView;

    @BindView(R.id.layout_detail_check_in_tv)
    TextView checkInTextView;

    @BindView(R.id.layout_detail_check_out_tv)
    TextView checkOutTextView;

    private final House mHouse;

    public DetailFragment(House house){
        this.mHouse = house;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    protected void setUp(View view) {
        String bathroom = mHouse.getBathrooms() + " phòng";
        bathroomTextView.setText(bathroom);
        String bedroom = mHouse.getBedrooms() + " phòng";
        bedroomTextView.setText(bedroom);
        String bed = mHouse.getBeds() + " giường";
        bedTextView.setText(bed);
        String guest = mHouse.getGuests() + " khách ( tối đa " + mHouse.getMaxGuests()  + " khách )";
        guestTextView.setText(guest);
        String checkIn = "Check in: " + mHouse.getCheckInStart() + "h - " + mHouse.getCheckInEnd() + "h" ;
        checkInTextView.setText(checkIn);
        String checkOut = "Check out: " + mHouse.getCheckOut()+ "h";
        checkOutTextView.setText(checkOut);
    }
}
