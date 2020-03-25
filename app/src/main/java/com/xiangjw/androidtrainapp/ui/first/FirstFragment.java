package com.xiangjw.androidtrainapp.ui.first;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.adapter.base.LoadMoreAdapter;
import com.xiangjw.androidtrainapp.adapter.base.LoadMoreScrollListener;
import com.xiangjw.androidtrainapp.adapter.first.FirstKnowledgeAdapter;
import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.databinding.FragmentFirstBinding;
import com.xiangjw.androidtrainapp.ui.first.base.BaseFragment;
import com.xiangjw.androidtrainapp.uiutils.ConvertUtils;
import com.xiangjw.androidtrainapp.uiutils.CustomListDivider;
import com.xiangjw.androidtrainapp.utils.DebugLog;

import java.util.List;

public class FirstFragment extends BaseFragment<FirstPresenter , FragmentFirstBinding> implements FirstContact.View , FirstKnowledgeAdapter.ListClickListener{

    private SearchView searchView;
    private String searchStr;

    FirstKnowledgeAdapter firstKnowledgeAdapter;
    private LoadMoreAdapter adapter;

    @Override
    protected FragmentFirstBinding getViewBinding(LayoutInflater inflater) {
        return FragmentFirstBinding.inflate(inflater);
    }

    @Override
    protected void initView() {
        searchStr = "";
        setHasOptionsMenu(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.list.setLayoutManager(layoutManager);
        binding.list.addItemDecoration(new CustomListDivider(1
                , ConvertUtils.dp2px(getContext() , 10)
                , ConvertUtils.dp2px(getContext() , 10) , getResources().getColor(R.color.colorSp) , false));

        firstKnowledgeAdapter = new FirstKnowledgeAdapter(null , this);
        adapter = new LoadMoreAdapter(firstKnowledgeAdapter , this);
        binding.list.setAdapter(adapter);
        binding.list.addOnScrollListener(new LoadMoreScrollListener() {
            @Override
            public void scrollLoadMore() {
                if(adapter.canLoadMore()) {
                    notifyLoadMore(LoadMoreAdapter.LoadMoreState.LOADING);
                    presenter.loadMoreData(searchStr);
                }
            }
        });

        startLoad();
    }

    private void notifyLoadMore(LoadMoreAdapter.LoadMoreState state){
        adapter.setLoadMoreState(state);
        binding.list.post(new Runnable() {//如果不这样，滚动下一页刷新时会报错
            @Override
            public void run() {
                adapter.notifyItemChanged(adapter.getItemCount() - 1);
            }
        });
    }

    @Override
    public void onDestroyView() {
        binding.list.clearOnScrollListeners();
        super.onDestroyView();
    }

    public void startLoad(){
        firstKnowledgeAdapter.setList(null);
        adapter.notifyDataSetChanged();
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.FIRST_LOADING);
        presenter.refreshData(searchStr);
    }

    @Override
    public void refreshDataOk(List<FirstKnowledge> data) {
        firstKnowledgeAdapter.setList(data);
        adapter.notifyDataSetChanged();
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.COMPLETE);
    }

    @Override
    public void loadMoreDataOk(List<FirstKnowledge> data) {
        firstKnowledgeAdapter.setList(data);
        adapter.notifyDataSetChanged();
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.COMPLETE);
    }

    @Override
    public void refreshDataFail(String msg) {
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.COMPLETE);
    }

    @Override
    public void loadNoData() {
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.NO_DATA);
    }

    @Override
    public void loadMoreDataDone(List<FirstKnowledge> data) {
        firstKnowledgeAdapter.setList(data);
        adapter.notifyDataSetChanged();
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.NO_DATA);
    }

    @Override
    public void loadMoreDataFail(String msg) {
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.COMPLETE);
    }

    @Override
    public void onClickItem(FirstKnowledge item) {
        DebugLog.i(FirstFragment.class , "列表点击：" + item.getName());
    }

    @Override
    public void onClickLoadMore() {
        DebugLog.i(FirstFragment.class , "列表点击更多");
        notifyLoadMore(LoadMoreAdapter.LoadMoreState.LOADING);
        presenter.loadMoreData(searchStr);
    }

    @Override
    protected FirstPresenter createPresenter() {
        return new FirstPresenter();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.action_bar_menu , menu);
        MenuItem search = menu.findItem(R.id.action_search);
        searchView = (SearchView) search.getActionView();
        searchView.setQueryHint("请输入关键字搜索");
        searchView.setSubmitButtonEnabled(true);
        searchView.setMaxWidth(1080);
        ImageView sButton = searchView.findViewById(R.id.search_button);
        sButton.setColorFilter(getResources().getColor(R.color.colorTitleText));
        ImageView searchButton = searchView.findViewById(R.id.search_go_btn);
        searchButton.setImageResource(R.drawable.ic_check_24dp);
        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setColorFilter(getResources().getColor(R.color.colorTitleText));
        SearchView.SearchAutoComplete mSearchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);

        //设置输入框提示文字样式
        mSearchAutoComplete.setHintTextColor(getResources().getColor(R.color.colorTitleText));//设置提示文字颜色
        mSearchAutoComplete.setTextColor(getResources().getColor(R.color.colorTitleText));//设置内容文字颜色
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchStr = query;
                startLoad();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if("".equals(newText) && !"".equals(searchStr)){
                    searchStr = "";
                    startLoad();
                }
                return false;
            }
        });
    }
}