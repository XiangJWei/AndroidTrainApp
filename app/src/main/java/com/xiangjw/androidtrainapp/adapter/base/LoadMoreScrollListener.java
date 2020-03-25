package com.xiangjw.androidtrainapp.adapter.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private boolean isScrollDown = false;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        isScrollDown = dy > 0;
        RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
        if(lm instanceof LinearLayoutManager){
            LinearLayoutManager manager = (LinearLayoutManager)lm;
            int lastOne = manager.findLastCompletelyVisibleItemPosition();
            if(isScrollDown && lastOne == manager.getItemCount() - 1){
                scrollLoadMore();
            }
        }else if(lm instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager)lm;
            int[] lastOnes = manager.findLastCompletelyVisibleItemPositions(null);
            int lastOne = Math.max(lastOnes[0] , lastOnes[1]);
            if(isScrollDown && lastOne == manager.getItemCount() - 1){
                scrollLoadMore();
            }
        }

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    public abstract void scrollLoadMore();
}
