package com.xiangjw.androidtrainapp.ui.first.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView{
    protected View rootView;
    protected T presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId() , container , false);

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

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract T createPresenter();
}
