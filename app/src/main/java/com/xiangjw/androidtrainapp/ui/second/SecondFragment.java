package com.xiangjw.androidtrainapp.ui.second;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewbinding.ViewBinding;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.adapter.base.LoadMoreAdapter;
import com.xiangjw.androidtrainapp.adapter.base.LoadMoreScrollListener;
import com.xiangjw.androidtrainapp.adapter.first.FirstKnowledgeAdapter;
import com.xiangjw.androidtrainapp.adapter.second.SecondKnowledgeAdapter;
import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.bean.second.SecondKnowledge;
import com.xiangjw.androidtrainapp.databinding.FragmentFirstBinding;
import com.xiangjw.androidtrainapp.databinding.FragmentSecondBinding;
import com.xiangjw.androidtrainapp.ui.first.FirstFragment;
import com.xiangjw.androidtrainapp.ui.first.FirstPresenter;
import com.xiangjw.androidtrainapp.ui.second.base.BaseFragment;
import com.xiangjw.androidtrainapp.uiutils.ConvertUtils;
import com.xiangjw.androidtrainapp.uiutils.CustomGridDivider;
import com.xiangjw.androidtrainapp.uiutils.CustomListDivider;
import com.xiangjw.androidtrainapp.utils.DebugLog;

import java.util.List;

public class SecondFragment extends BaseFragment<SecondViewModel , FragmentSecondBinding> implements SecondKnowledgeAdapter.ListClickListener{
    private SearchView searchView;
    private String searchStr;

    SecondKnowledgeAdapter secondKnowledgeAdapter;
    private LoadMoreAdapter adapter;

    @Override
    public Class getViewModelClass() {
        return SecondViewModel.class;
    }

    @Override
    public FragmentSecondBinding getViewBinding(LayoutInflater inflater) {
        return FragmentSecondBinding.inflate(inflater);
    }

    @Override
    public void startObserver() {
        viewModel.getData().observe(this, new Observer<List<SecondKnowledge>>() {
            @Override
            public void onChanged(List<SecondKnowledge> s) {
                secondKnowledgeAdapter.setList(s);
                adapter.notifyDataSetChanged();
            }
        });

        viewModel.getState().observe(this, new Observer<LoadMoreAdapter.LoadMoreState>() {
            @Override
            public void onChanged(LoadMoreAdapter.LoadMoreState loadMoreState) {
                notifyLoadMore(loadMoreState);
            }
        });
    }

    @Override
    protected void initView() {
        searchStr = "";
        setHasOptionsMenu(true);

//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext() , 2);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2 , StaggeredGridLayoutManager.VERTICAL);
        binding.list.setLayoutManager(layoutManager);
        binding.list.addItemDecoration(new CustomGridDivider(
                ConvertUtils.dp2px(getContext() , 10)
                , ConvertUtils.dp2px(getContext() , 10)));

        secondKnowledgeAdapter = new SecondKnowledgeAdapter(null , this);
        adapter = new LoadMoreAdapter(secondKnowledgeAdapter , this);
        binding.list.setAdapter(adapter);
        binding.list.addOnScrollListener(new LoadMoreScrollListener() {
            @Override
            public void scrollLoadMore() {
                if(adapter.canLoadMore()) {
                    viewModel.loadMoreData(searchStr);
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
        secondKnowledgeAdapter.setList(null);
        adapter.notifyDataSetChanged();
        viewModel.refreshData(searchStr);
    }

    @Override
    public void onClickItem(SecondKnowledge item) {
        DebugLog.i(FirstFragment.class , "列表点击：" + item.getName());
    }

    public void onClickLoadMore() {
        DebugLog.i(FirstFragment.class , "列表点击更多");
        viewModel.loadMoreData(searchStr);
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