package com.example.homestay.ui.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpcomingFragment extends BaseFragment implements UpcomingView, UpcomingAdapter.Callback{

    @Inject
    UpcomingPresenter<UpcomingView> mPresenter;

    @Inject
    UpcomingAdapter mAdapter;

    @BindView(R.id.layout_upcoming_rv)
    RecyclerView mUpcomingRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
        }
        return root;
    }

    @Override
    protected void setUp(View view) {
        mAdapter.setCallback(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mUpcomingRecyclerView.setLayoutManager(layoutManager);
        mUpcomingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mUpcomingRecyclerView.setAdapter(mAdapter);

        mPresenter.getTripsUpcomingData();
    }

    @Override
    public void onItemClick(int houseId) {

    }

    @Override
    public void showData(ListHouseResponse response) {
        mAdapter.addItem(response.getHouseList().getHouses());
    }
}
