package com.xiangjw.androidtrainapp.ui.first;

import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.network.NetResult;
import com.xiangjw.androidtrainapp.ui.first.base.IBaseModel;
import com.xiangjw.androidtrainapp.ui.first.base.IBaseView;

import java.util.List;

/**
 * 接口管理类,契约类
 */
public class FirstContact {

    interface Presenter{
        void refreshData(String keyword);
        void loadMoreData(String keyword);
    }

    interface Model extends IBaseModel{
        void requestData(int page , int pageNum , String keyword , ModelLoadListener listener);
    }

    interface View extends IBaseView {
        void refreshDataOk(List<FirstKnowledge> data);
        void loadMoreDataOk(List<FirstKnowledge> data);
        void refreshDataFail(String msg);
        void loadMoreDataFail(String msg);
        void loadMoreDataDone(List<FirstKnowledge> data);
        void loadNoData();
    }

    interface ModelLoadListener{
        void loadDone(NetResult<List<FirstKnowledge>> result);
    }
}
