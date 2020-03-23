package com.xiangjw.androidtrainapp.adapter.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangjw.androidtrainapp.utils.DebugLog;

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private boolean isScrollDown = false;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isScrollDown = dy > 0;
        LinearLayoutManager manager = (LinearLayoutManager)recyclerView.getLayoutManager();
        int lastOne = manager.findLastCompletelyVisibleItemPosition();
        if(isScrollDown && lastOne == manager.getItemCount() - 1){
            scrollLoadMore();
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    public abstract void scrollLoadMore();
}
