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

import com.xiangjw.androidtrainapp.R;

public class FirstFragment extends Fragment {

    private FirstViewModel firstViewModel;
    private SearchView searchView;
    private String searchStr;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        firstViewModel =
                ViewModelProviders.of(this).get(FirstViewModel.class);
        View root = inflater.inflate(R.layout.fragment_first, container, false);
        final TextView textView = root.findViewById(R.id.text_first);
        firstViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        searchStr = "";
        setHasOptionsMenu(true);

        return root;
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