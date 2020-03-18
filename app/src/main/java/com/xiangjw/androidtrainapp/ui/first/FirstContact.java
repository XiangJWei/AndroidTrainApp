package com.xiangjw.androidtrainapp.ui.first;

import com.xiangjw.androidtrainapp.ui.first.base.IBaseModel;
import com.xiangjw.androidtrainapp.ui.first.base.IBaseView;

/**
 * 接口管理类,契约类
 */
public class FirstContact {
    interface Presenter{
        void loadData();
    }

    interface Model extends IBaseModel{
        void requestData(ModelLoadListener listener);
    }

    interface View extends IBaseView {
        void showData(String info);
    }

    interface ModelLoadListener{
        void loadDone(String data);
    }
}
