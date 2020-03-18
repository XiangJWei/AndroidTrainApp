package com.xiangjw.androidtrainapp.ui.first;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.ui.first.base.BaseFragment;
import com.xiangjw.androidtrainapp.utils.DebugLog;

public class FirstFragment extends BaseFragment<FirstPresenter> implements FirstContact.View{

    private SearchView searchView;
    private String searchStr;

    private RecyclerView list;
    private TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_first;
    }

    @Override
    protected void initView() {
        textView = rootView.findViewById(R.id.text_first);
        searchStr = "";
        setHasOptionsMenu(true);

        presenter.loadData();
    }

    @Override
    protected FirstPresenter createPresenter() {
        return new FirstPresenter();
    }

    @Override
    public void showData(String info) {
        DebugLog.i(FirstFragment.class , "showData:" + info);
        textView.setText(info);
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
        Toast.makeText(getContext() , searchStr , Toast.LENGTH_SHORT).show();
    }

    public void clearSearch(){
        Toast.makeText(getContext() , "搜索取消" , Toast.LENGTH_SHORT).show();
    }
}