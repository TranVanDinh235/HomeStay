package com.example.homestay.ui.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.homestay.R;
import com.example.homestay.data.network.model.HouseListResponse;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

@PerActivity
public class CollectionFragment extends BaseFragment implements CollectionView{

    @Inject
    CollectionPresenter<CollectionView> mPresenter;

    @Inject
    CollectionAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.layout_collection_list)
    RecyclerView listRecyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_collection, container, false);
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
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        listRecyclerView.setLayoutManager(layoutManager);
        listRecyclerView.setItemAnimator(new DefaultItemAnimator());
        listRecyclerView.setAdapter(mAdapter);
        if(mPresenter.isUserLoggedInMode()) mPresenter.getListHouse();
    }

    @Override
    public void showCollection(HouseListResponse response) {
        mAdapter.addItem(response.getHouseList().getHouses());
    }
}