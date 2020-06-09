package com.example.homestay.ui.search;

import android.content.Context;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.entity.SearchResult;
import com.example.homestay.data.network.request.SearchBody;
import com.example.homestay.di.ApplicationContext;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BaseViewHolder;
import com.example.homestay.utils.StringUtils;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.otaliastudios.autocomplete.RecyclerViewPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

public class SearchBarPresenter extends RecyclerViewPresenter<SearchResult> implements TabLayout.OnTabSelectedListener{
    private DataManager mDataManager;
    private SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    private SearchBarAdapter mAdapter;

    private int tabPosition = 0;
    private CharSequence lastQuery;

    public SearchBarPresenter(Context context) {
        super(context);
    }

    @Inject
    public SearchBarPresenter(@ApplicationContext Context context, DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable){
        super(context);
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        mAdapter = new SearchBarAdapter(new ArrayList<>());
        return mAdapter;
    }

    @NonNull
    @Override
    protected ViewGroup getView() {
        ViewGroup rv = super.getView();
        ContextThemeWrapper ctx = new ContextThemeWrapper(getContext(), R.style.Theme_AppCompat);
        ViewGroup container = (ViewGroup) LayoutInflater.from(ctx).inflate(R.layout.search_bar_popup, null);
        // Add RecyclerView to our container

        ViewGroup rvContainer = container.findViewById(R.id.recycler_view_container);

        rvContainer.addView(rv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        // Set up bar that reacts to clicks and syncs with 'females' boolean
        TabLayout tabLayout = container.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Homestay"));
        tabLayout.addTab(tabLayout.newTab().setText("Chủ nhà"));
        tabLayout.addTab(tabLayout.newTab().setText("Địa điểm"));
        //noinspection ConstantConditions
        tabLayout.getTabAt(tabPosition).select();
        tabLayout.addOnTabSelectedListener(this);
        return container;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        lastQuery = query;
        try {
            if (TextUtils.isEmpty(lastQuery)) return;

            SearchBody searchBody = new SearchBody(lastQuery.toString());
            String json = new Gson().toJson(searchBody);
            JSONObject body = new JSONObject(json);

            if(tabPosition == 0){
                mCompositeDisposable.add(mDataManager
                        .doServerApiSearchHouseDataCall(body)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(response -> {
                            mAdapter.setData(response.getData());
                            mAdapter.notifyDataSetChanged();
                        }));
            } else if(tabPosition == 1){
                mCompositeDisposable.add(mDataManager
                        .doServerApiSearchHostDataCall(body)
                        .subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(response -> {
                            mAdapter.setData(response.getData());
                            mAdapter.notifyDataSetChanged();
                        }));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected PopupDimensions getPopupDimensions() {
        PopupDimensions dims = new PopupDimensions();
        dims.width = 800;
        dims.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dims;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        tabPosition = tab.getPosition();
        onQuery(lastQuery);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    protected class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> {

        List<SearchResult> mSearchResultList;

        SearchBarAdapter(List<SearchResult> searchResults){
            this.mSearchResultList = searchResults;
        }

        @NonNull
        @Override
        public SearchBarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SearchBarAdapter.ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SearchBarAdapter.ViewHolder holder, int position) {
            holder.onBind(position);
        }

        @Override
        public int getItemCount() {
            return (mSearchResultList == null || mSearchResultList.isEmpty()) ? 0 : mSearchResultList.size();
        }

        void setData(List<SearchResult> searchResults){
            this.mSearchResultList = searchResults;
        }

        public class ViewHolder extends BaseViewHolder implements View.OnClickListener{

            private SearchResult item;

            @BindView(R.id.item_search_content)
            TextView mContentTextView;

            @BindView(R.id.item_search_icon)
            ImageView mIconImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            protected void clear() {

            }

            @Override
            public void onBind(int position) {
                super.onBind(position);
                item = mSearchResultList.get(position);
                switch (item.getType()){
                    case 0: {
                        mContentTextView.setText(StringUtils.cutString(item.getHostName()));
                        mIconImageView.setImageResource(R.drawable.ic_host);
                        break;
                    }
                    case 1: {
                        mContentTextView.setText(StringUtils.cutString(item.getTitle()));
                        mIconImageView.setImageResource(R.drawable.ic_house);
                        break;
                    }
                    case 2: {
                        mContentTextView.setText(StringUtils.cutString(item.getAddress()));
                        break;
                    }
                }
            }

            @Override
            public void onClick(View v) {
                v.setOnClickListener(view -> dispatchClick(item));
            }
        }
    }
}
