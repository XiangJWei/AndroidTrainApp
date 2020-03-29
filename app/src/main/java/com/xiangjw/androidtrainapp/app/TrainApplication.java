package com.xiangjw.androidtrainapp.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.xiangjw.androidtrainapp.utils.DebugLog;

public class TrainApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        DebugLog.i(TrainApplication.class , "TrainApplication onCreate");
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DebugLog.i(TrainApplication.class , "TrainApplication onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        DebugLog.i(TrainApplication.class , "TrainApplication onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        DebugLog.i(TrainApplication.class , "TrainApplication onTrimMemory:" + level);
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        DebugLog.i(TrainApplication.class , "TrainApplication onTerminate");
        super.onTerminate();
    }
}
