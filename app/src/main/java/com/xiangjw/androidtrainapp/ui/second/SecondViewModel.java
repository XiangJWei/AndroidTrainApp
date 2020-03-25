package com.xiangjw.androidtrainapp.ui.second;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.xiangjw.androidtrainapp.adapter.base.LoadMoreAdapter;
import com.xiangjw.androidtrainapp.bean.second.SecondKnowledge;
import com.xiangjw.androidtrainapp.network.NetResult;
import com.xiangjw.androidtrainapp.ui.second.base.BaseViewModel;

import java.util.List;

public class SecondViewModel extends BaseViewModel<SecondModel> {
    private int page;
    private MutableLiveData<List<SecondKnowledge>> data;
    private MutableLiveData<LoadMoreAdapter.LoadMoreState> state;

    public SecondViewModel() {
        super();
        data = new MutableLiveData<>();
        state = new MutableLiveData<>();
    }

    @Override
    protected SecondModel createModel() {
        return new SecondModel();
    }

    public LiveData<List<SecondKnowledge>> getData() {
        return data;
    }

    public LiveData<LoadMoreAdapter.LoadMoreState> getState() {
        return state;
    }

    public void refreshData(String keyword) {
        page = 1;
        state.setValue(LoadMoreAdapter.LoadMoreState.FIRST_LOADING);
        model.requestData(page , NetResult.PAGE_NUM , keyword , new SecondModel.ModelLoadListener() {
            @Override
            public void loadDone(NetResult<List<SecondKnowledge>> result) {
                if(result.isSuccess()){
                    if(result.getObject() == null || result.getObject().size() == 0){
                        state.setValue(LoadMoreAdapter.LoadMoreState.NO_DATA);
                    }else if(result.getObject().size() < NetResult.PAGE_NUM){
                        data.setValue(result.getObject());
                        state.setValue(LoadMoreAdapter.LoadMoreState.NO_DATA);
                    }else{
                        data.setValue(result.getObject());
                        state.setValue(LoadMoreAdapter.LoadMoreState.COMPLETE);
                    }
                }else{
                    state.setValue(LoadMoreAdapter.LoadMoreState.COMPLETE);
                }
            }
        });
    }

    public void loadMoreData(String keyword) {
        state.setValue(LoadMoreAdapter.LoadMoreState.LOADING);
        model.requestData(page + 1 , NetResult.PAGE_NUM , keyword , new SecondModel.ModelLoadListener() {
            @Override
            public void loadDone(NetResult<List<SecondKnowledge>> result) {
                if(result.isSuccess()){
                    if(result.getObject() == null || result.getObject().size() == 0){
                        state.setValue(LoadMoreAdapter.LoadMoreState.NO_DATA);
                    }else if(result.getObject().size() < NetResult.PAGE_NUM){
                        page ++;
                        data.getValue().addAll(result.getObject());
                        state.setValue(LoadMoreAdapter.LoadMoreState.NO_DATA);
                    }else{
                        page ++;
                        data.getValue().addAll(result.getObject());
                        state.setValue(LoadMoreAdapter.LoadMoreState.COMPLETE);
                    }

                }else{
                    state.setValue(LoadMoreAdapter.LoadMoreState.COMPLETE);
                }
            }
        });
    }
}