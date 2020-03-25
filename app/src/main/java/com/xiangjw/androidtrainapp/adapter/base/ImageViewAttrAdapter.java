package com.xiangjw.androidtrainapp.adapter.base;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class ImageViewAttrAdapter {
    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        if(resId == 0){
            view.setVisibility(View.GONE);
        }else{
            view.setVisibility(View.VISIBLE);
            view.setImageResource(resId);
        }
    }
}
