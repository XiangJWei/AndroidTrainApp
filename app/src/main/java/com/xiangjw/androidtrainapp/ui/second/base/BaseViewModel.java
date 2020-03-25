package com.xiangjw.androidtrainapp.ui.second.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<M extends IBaseModel> extends ViewModel implements IBaseViewModel{
    protected M model;

    public BaseViewModel() {
        if(model == null){
            model = createModel();
        }
    }

    protected abstract M createModel();

    @Override
    protected void onCleared() {
        model = null;
    }
}
