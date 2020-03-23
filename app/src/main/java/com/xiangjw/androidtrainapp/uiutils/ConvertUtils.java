package com.xiangjw.androidtrainapp.uiutils;

import android.content.Context;

public class ConvertUtils {
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
