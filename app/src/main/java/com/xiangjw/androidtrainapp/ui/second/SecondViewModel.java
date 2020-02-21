package com.xiangjw.androidtrainapp.ui.second;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SecondViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SecondViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is second fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}