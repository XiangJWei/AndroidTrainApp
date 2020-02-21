package com.xiangjw.androidtrainapp.ui.third;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ThirdViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ThirdViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is third fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}