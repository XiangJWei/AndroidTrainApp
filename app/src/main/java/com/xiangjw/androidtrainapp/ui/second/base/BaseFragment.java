package com.xiangjw.androidtrainapp.ui.second.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewbinding.ViewBinding;

import com.xiangjw.androidtrainapp.utils.DebugLog;

public abstract class BaseFragment<VM extends ViewModel , B extends ViewBinding> extends Fragment {

    protected VM viewModel;
    protected B binding;
    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = (VM)ViewModelProviders.of(this).get(getViewModelClass());
        binding = getViewBinding(inflater);
        rootView = binding.getRoot();

        startObserver();
        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract Class getViewModelClass();

    protected abstract B getViewBinding(LayoutInflater inflater);

    protected abstract void initView();

    protected abstract void startObserver();
}
