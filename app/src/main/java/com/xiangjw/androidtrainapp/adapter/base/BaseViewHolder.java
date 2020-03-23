package com.xiangjw.androidtrainapp.adapter.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class BaseViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    private T binding;

    public BaseViewHolder(@NonNull View itemView, T binding) {
        super(itemView);
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
