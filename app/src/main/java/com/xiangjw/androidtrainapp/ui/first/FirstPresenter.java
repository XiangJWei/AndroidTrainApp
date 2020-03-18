package com.xiangjw.androidtrainapp.ui.first;

import com.xiangjw.androidtrainapp.ui.first.base.BasePresenter;

public class FirstPresenter extends BasePresenter<FirstContact.Model , FirstContact.View> implements FirstContact.Presenter{

    @Override
    public void loadData() {
        getModel().requestData(new FirstContact.ModelLoadListener() {
            @Override
            public void loadDone(String data) {
                getView().showData(data);
            }
        });
    }

    @Override
    protected FirstContact.Model createModel() {
        return new FirstModel();
    }
}
