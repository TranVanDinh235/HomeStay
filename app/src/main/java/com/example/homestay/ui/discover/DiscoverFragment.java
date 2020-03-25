package com.example.homestay.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.discover.adapter.DiscoverAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


@PerActivity
public class DiscoverFragment extends BaseFragment implements DiscoverView, DiscoverAdapter.Callback {

    @Inject
    DiscoverPresenter<DiscoverView> mPresenter;

    @BindView(R.id.layout_date_picker) ConstraintLayout layoutDatePicker;

    @BindView(R.id.finest) TextView finest;

    @BindView(R.id.top_deal) TextView topDeals;

    @BindView(R.id.popular_place) TextView popularPlace;

    @BindView(R.id.promotion) TextView promotion;

    @BindView(R.id.HN_title) TextView HaNoiTitle;

    @BindView(R.id.SG_title) TextView SaiGonTitle;

    @BindView(R.id.VT_title) TextView VungTauTitle;

    @BindView(R.id.DL_title) TextView DaLatTitle;

    @BindView(R.id.recycler_finest)
    RecyclerView finestGirdView;

    @BindView(R.id.gird_view_promotion)
    RecyclerView promotionGirdView;

    @BindView(R.id.recycler_HN)
    RecyclerView HNRecyclerView;

    @BindView(R.id.recycler_VT)
    RecyclerView VTRecyclerView;

    @BindView(R.id.recycler_DL)
    RecyclerView DLRecyclerView;

    @BindView(R.id.recycler_SG)
    RecyclerView SGRecyclerView;

    @BindView(R.id.recycler_popular_place)
    RecyclerView popularPlaceRecyclerView;

    @BindView(R.id.recycler_view_top_deal)
    RecyclerView topDealsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_discover, container, false);


        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
        }

        MaterialDatePicker.Builder<?> builder = setupDateSelectorBuilder();
        CalendarConstraints.Builder constraintBuilder = setupConstraintBuilder();

        layoutDatePicker.setOnClickListener(v -> {
            builder.setCalendarConstraints(constraintBuilder.build());
            MaterialDatePicker<?> picker = builder.build();
            picker.show(getFragmentManager(), picker.toString());
        });
        return root;
    }

    @Override
    protected void setUp(View view) {
        view.setOnClickListener(v -> {

        });
    }

    private MaterialDatePicker.Builder<?> setupDateSelectorBuilder(){
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR);
        builder.setTitleText("Test picker");

        return builder;
    }

    private CalendarConstraints.Builder setupConstraintBuilder(){
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        return constraintsBuilder;
    }

    @Override
    public void showData(DiscoverResponse discoverResponse) {
        finest.setText(discoverResponse.getData().getFinest());
        topDeals.setText(discoverResponse.getData().getTopDeals());
        popularPlace.setText(discoverResponse.getData().getPopularPlace());
        promotion.setText(discoverResponse.getData().getPromotion());
        HaNoiTitle.setText(discoverResponse.getData().getHNtitle());
        SaiGonTitle.setText(discoverResponse.getData().getSGtitle());
        DaLatTitle.setText(discoverResponse.getData().getDLtitle());
        VungTauTitle.setText(discoverResponse.getData().getVTtitle());


    }

    @Override
    public void onHomeEmptyViewRetryClick() {

    }
}