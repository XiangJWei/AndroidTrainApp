package com.xiangjw.androidtrainapp.uiutils;

import android.content.Context;
import android.widget.Toast;

import com.xiangjw.androidtrainapp.app.TrainApplication;

public class ToastUtils {
    public static void show(String info){
        Toast.makeText(TrainApplication.getAppContext() , info , Toast.LENGTH_SHORT).show();
    }

    public static void show(int resId){
        Toast.makeText(TrainApplication.getAppContext() , resId , Toast.LENGTH_SHORT).show();
    }
}
