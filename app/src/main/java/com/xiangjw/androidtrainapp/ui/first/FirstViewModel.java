package com.xiangjw.androidtrainapp.ui.first;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FirstViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FirstViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is first fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}