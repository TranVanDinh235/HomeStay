package com.example.homestay.utils;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.List;

public class PullLoadMoreUtil{

    public static void setupRecyclerView(Context context, PullLoadMoreRecyclerView rv,
                                         boolean isLoadMore, boolean isLoadRefresh,
                                         PullLoadMoreRecyclerView.PullLoadMoreListener listener) {
        rv.setLinearLayout();
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setFooterViewText(context.getString(R.string.loading));
        rv.setOnPullLoadMoreListener(listener);
        setLoadMore(isLoadMore, rv);
        setRefresh(isLoadRefresh, rv);
    }

    public static void setLoadMore(boolean isLoadMore, PullLoadMoreRecyclerView rv) {
        rv.setRefreshing(isLoadMore);
        rv.setIsLoadMore(isLoadMore);
        rv.setPushRefreshEnable(isLoadMore);
        if (!isLoadMore) {
            rv.setPullLoadMoreCompleted();
        }
    }

    public static void setRefresh(boolean isLoadRefresh, PullLoadMoreRecyclerView rv) {
        rv.setRefreshing(isLoadRefresh);
        rv.setPullRefreshEnable(isLoadRefresh);
    }

    public static void stopLoad(PullLoadMoreRecyclerView rv) {
        rv.setRefreshing(true);
        rv.setIsLoadMore(false);
        rv.setPushRefreshEnable(false);
        rv.setPullRefreshEnable(true);
        rv.setPullLoadMoreCompleted();
    }

    public static <T> void onRefresh(List<T> list, RecyclerView.Adapter adapter, PullLoadMoreRecyclerView rv){
        list.clear();
        adapter.notifyDataSetChanged();
        setLoadMore(true, rv);
    }
}
