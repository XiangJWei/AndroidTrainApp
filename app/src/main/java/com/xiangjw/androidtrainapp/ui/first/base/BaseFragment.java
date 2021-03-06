package com.xiangjw.androidtrainapp.ui.first.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class BaseFragment<T extends BasePresenter , B extends ViewBinding> extends Fragment implements IBaseView{
    protected View rootView;
    protected T presenter;
    protected B binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding(inflater);
        rootView = binding.getRoot();

        presenter = createPresenter();
        presenter.attachView(this);
        initView();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        if(presenter != null){
            presenter.detachView();
            presenter = null;
        }
        super.onDestroyView();
    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    protected abstract B getViewBinding(LayoutInflater inflater);

    protected abstract void initView();

    protected abstract T createPresenter();

    protected void openActivity(Class target){
        Intent intent = new Intent();
        intent.setClass(getBaseContext() , target);
        startActivity(intent);
    }

    protected void openActivity(Class target , Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(getBaseContext() , target);
        startActivity(intent , bundle);
    }
}
