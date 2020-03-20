package com.xiangjw.androidtrainapp.ui.first;

import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.network.NetResult;
import com.xiangjw.androidtrainapp.ui.first.base.BasePresenter;

import java.util.List;

public class FirstPresenter extends BasePresenter<FirstContact.Model , FirstContact.View> implements FirstContact.Presenter{

    private List<FirstKnowledge> data;
    private int page;

    @Override
    public void refreshData(String keyword) {
        page = 1;
        getModel().requestData(page , NetResult.PAGE_NUM , keyword , new FirstContact.ModelLoadListener() {
            @Override
            public void loadDone(NetResult<List<FirstKnowledge>> result) {
                if(result.isSuccess()){
                    data = result.getObject();
                    getView().refreshDataOk(data);
                }else{
                    getView().refreshDataFail(result.getMsg());
                }
            }
        });
    }

    @Override
    public void loadMoreData(String keyword) {
        getModel().requestData(page + 1 , NetResult.PAGE_NUM , keyword , new FirstContact.ModelLoadListener() {
            @Override
            public void loadDone(NetResult<List<FirstKnowledge>> result) {
                if(result.isSuccess()){
                    page ++;
                    data.addAll(result.getObject());
                    getView().loadMoreDataOk(data);
                }else{
                    getView().loadMoreDataFail(result.getMsg());
                }
            }
        });
    }

    @Override
    protected FirstContact.Model createModel() {
        return new FirstModel();
    }
}
