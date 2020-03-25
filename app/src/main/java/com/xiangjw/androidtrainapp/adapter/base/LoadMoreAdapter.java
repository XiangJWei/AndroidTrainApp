package com.xiangjw.androidtrainapp.adapter.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.databinding.BaseListLoadMoreBinding;


/**
 * 装饰器模式给RecyclerView.Adapter加分页加载的功能
 */
public class LoadMoreAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseListLoadMoreBinding>> {
    public enum LoadMoreState{
        FIRST_LOADING,
        FIRST_LOADING_ERROR,
        LOADING,
        COMPLETE,
        NO_DATA
    }

    private static final int NORMAL_VIEW = 1;
    private static final int FOOTER_VIEW = 2;

    private RecyclerView.Adapter adapter;

    private LoadMoreState state;
    private BaseListClickListener listener;

    public LoadMoreAdapter(RecyclerView.Adapter adapter , BaseListClickListener listener) {
        this.adapter = adapter;
        state = LoadMoreState.FIRST_LOADING;
        this.listener = listener;
    }

    public void setLoadMoreState(LoadMoreState state){
        this.state = state;
    }

    public boolean canLoadMore(){
        return state == LoadMoreState.COMPLETE;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()){
            return FOOTER_VIEW;
        }else{
            return NORMAL_VIEW;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager manager = (GridLayoutManager)layoutManager;
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    // 如果当前是footer的位置，那么该item占据多个单元格，正常情况下占据1个单元格
                    return getItemViewType(position) == FOOTER_VIEW ? manager.getSpanCount() : 1;
                }
            });
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == NORMAL_VIEW){
            return (BaseViewHolder)adapter.onCreateViewHolder(parent, viewType);
        }else{
            BaseListLoadMoreBinding binding = BaseListLoadMoreBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);
            BaseViewHolder<BaseListLoadMoreBinding> holder = new BaseViewHolder(binding.getRoot() , binding);
            holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((state == LoadMoreState.COMPLETE || state == LoadMoreState.FIRST_LOADING_ERROR)
                            && listener != null){
                        listener.onClickLoadMore();
                    }
                }
            });

            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if(holder.getBinding() instanceof BaseListLoadMoreBinding){
            BaseViewHolder<BaseListLoadMoreBinding> hold = (BaseViewHolder<BaseListLoadMoreBinding>)holder;
            switch (state){
                case FIRST_LOADING:
                    hold.getBinding().progress.setVisibility(View.VISIBLE);
                    hold.getBinding().text.setVisibility(View.INVISIBLE);
                    hold.getBinding().left.setVisibility(View.INVISIBLE);
                    hold.getBinding().right.setVisibility(View.INVISIBLE);
                    break;
                case FIRST_LOADING_ERROR:
                    hold.getBinding().progress.setVisibility(View.INVISIBLE);
                    hold.getBinding().text.setVisibility(View.VISIBLE);
                    hold.getBinding().text.setText(R.string.load_restart);
                    hold.getBinding().left.setVisibility(View.INVISIBLE);
                    hold.getBinding().right.setVisibility(View.INVISIBLE);
                    break;
                case LOADING:
                    hold.getBinding().progress.setVisibility(View.VISIBLE);
                    hold.getBinding().text.setVisibility(View.INVISIBLE);
                    hold.getBinding().left.setVisibility(View.VISIBLE);
                    hold.getBinding().right.setVisibility(View.VISIBLE);
                    break;
                case COMPLETE:
                    hold.getBinding().progress.setVisibility(View.INVISIBLE);
                    hold.getBinding().text.setVisibility(View.VISIBLE);
                    hold.getBinding().text.setText(R.string.load_more_complete);
                    hold.getBinding().left.setVisibility(View.VISIBLE);
                    hold.getBinding().right.setVisibility(View.VISIBLE);
                    break;
                case NO_DATA:
                    hold.getBinding().progress.setVisibility(View.INVISIBLE);
                    hold.getBinding().text.setVisibility(View.VISIBLE);
                    hold.getBinding().text.setText(R.string.load_more_nodata);
                    hold.getBinding().left.setVisibility(View.VISIBLE);
                    hold.getBinding().right.setVisibility(View.VISIBLE);
                    break;
            }
        }else{
            adapter.onBindViewHolder(holder , position);
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + 1;
    }
}
