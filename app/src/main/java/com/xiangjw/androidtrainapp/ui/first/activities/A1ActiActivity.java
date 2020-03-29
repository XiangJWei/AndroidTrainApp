package com.xiangjw.androidtrainapp.ui.first.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;


import androidx.annotation.Nullable;

import com.xiangjw.androidtrainapp.databinding.FirstA1ActiBinding;
import com.xiangjw.androidtrainapp.ui.base.BaseActivity;
import com.xiangjw.androidtrainapp.utils.DebugLog;

public class A1ActiActivity extends BaseActivity<FirstA1ActiBinding> {

    @Override
    protected FirstA1ActiBinding initBindingView(LayoutInflater inflater) {
        return FirstA1ActiBinding.inflate(inflater);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.text.setText("hehe");
    }
}
