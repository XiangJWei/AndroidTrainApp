package com.xiangjw.androidtrainapp.ui.first;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.adapter.first.FirstKnowledgeAdapter;
import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.databinding.FragmentFirstBinding;
import com.xiangjw.androidtrainapp.ui.first.base.BaseFragment;
import com.xiangjw.androidtrainapp.utils.DebugLog;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends BaseFragment<FirstPresenter , FragmentFirstBinding> implements FirstContact.View{

    private SearchView searchView;
    private String searchStr;

    private FirstKnowledgeAdapter adapter;

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

        presenter.refreshData(searchStr);
    }

    @Override
    public void refreshDataOk(List<FirstKnowledge> data) {
        adapter = new FirstKnowledgeAdapter(data);
        binding.list.setAdapter(adapter);
    }

    @Override
    public void loadMoreDataOk(List<FirstKnowledge> data) {
        adapter = new FirstKnowledgeAdapter(data);
        binding.list.setAdapter(adapter);
    }

    @Override
    public void refreshDataFail(String msg) {

    }

    @Override
    public void loadMoreDataFail(String msg) {

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
                doSearch();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if("".equals(newText) && !"".equals(searchStr)){
                    searchStr = "";
                    clearSearch();
                }
                return false;
            }
        });
    }

    public void doSearch(){
        presenter.refreshData(searchStr);
    }

    public void clearSearch(){
        DebugLog.i(FirstFragment.class , "搜索取消");
    }

}