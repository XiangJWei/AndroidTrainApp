package com.xiangjw.androidtrainapp.utils;

import android.os.Looper;
import android.util.Log;

import com.xiangjw.androidtrainapp.BuildConfig;

public class DebugLog {
    public static void i(Class name , String info){
        if(BuildConfig.DEBUG){
            Log.i("DebugLog:" + name.getSimpleName() + '-'
                    + Looper.getMainLooper().getThread().getId() + '-'
                    + Thread.currentThread().getId(), info);
        }
    }
}
