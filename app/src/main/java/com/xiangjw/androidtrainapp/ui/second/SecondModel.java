package com.xiangjw.androidtrainapp.ui.second;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.xiangjw.androidtrainapp.R;
import com.xiangjw.androidtrainapp.bean.second.SecondKnowledge;
import com.xiangjw.androidtrainapp.network.NetResult;
import com.xiangjw.androidtrainapp.ui.second.base.IBaseModel;
import com.xiangjw.androidtrainapp.utils.StringUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SecondModel implements IBaseModel {
    interface ModelLoadListener{
        void loadDone(NetResult<List<SecondKnowledge>> result);
    }

    private static final int MSG_LOAD = 1;

    ModelLoadListener listener;
    private MyHandler handler = new MyHandler(this);

    private void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case MSG_LOAD:
                listener.loadDone((NetResult)msg.obj);
                break;
        }
    }

    public void requestData(int page , int pageNum , final String keyword , ModelLoadListener listener){
        this.listener = listener;
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    List<SecondKnowledge> data = getData();
                    List<SecondKnowledge> pageData = new ArrayList<>();
                    for(int i = 0 , okNum = 0 ; i < data.size() ; i ++){
                        if(pageData.size() >= pageNum){
                            break;
                        }
                        boolean isOk = false;
                        if(StringUtils.isNotEmpty(keyword)){
                            if(data.get(i).getName().toLowerCase().contains(keyword.toLowerCase())
                                || data.get(i).getType().toLowerCase().contains(keyword.toLowerCase())
                                || data.get(i).getSubject().toLowerCase().contains(keyword.toLowerCase())){
                                isOk = true;
                            }
                        }else{
                            isOk = true;
                        }

                        if(isOk){
                            if(okNum >= (page - 1) * pageNum
                                && okNum < page * pageNum){
                                pageData.add(data.get(i));
                            }
                            okNum ++;
                        }
                    }

                    Message msg = new Message();
                    msg.what = MSG_LOAD;
                    msg.obj = NetResult.success(pageData);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private List<SecondKnowledge> getData(){
        List<SecondKnowledge> data= new ArrayList<SecondKnowledge>();
        data.add(new SecondKnowledge("IPC" ,"相关原理", "进程通信" , "s" , R.mipmap.logo1));
        data.add(new SecondKnowledge("Handler" ,"相关原理" , "线程通信" , "" , R.mipmap.logo2));
        data.add(new SecondKnowledge("ListView" ,"相关原理" , "" , "" , R.mipmap.logo3));
        data.add(new SecondKnowledge("RecyclerView" ,"相关原理" , "" , "" ));
        data.add(new SecondKnowledge("Animation" ,"相关原理" , "" , ""));
        data.add(new SecondKnowledge("Activity、Window、View" ,"相关原理" , "" , ""));
        data.add(new SecondKnowledge("View绘制" ,"自定义View" , "" , ""));
        data.add(new SecondKnowledge("事件分发机制" ,"自定义View" , "" , ""));
        data.add(new SecondKnowledge("布局优化" ,"性能优化" , "" , ""));
        data.add(new SecondKnowledge("过渡渲染" ,"性能优化" , "" , ""));
        data.add(new SecondKnowledge("ANR" ,"性能优化" , "" , ""));
        data.add(new SecondKnowledge("监控" ,"性能优化" , "" , ""));
        data.add(new SecondKnowledge("OOM" ,"内存优化" , "" , ""));
        data.add(new SecondKnowledge("内存泄漏" ,"内存优化" , "" , ""));
        data.add(new SecondKnowledge("内存检测" ,"内存优化" , "" , ""));
        data.add(new SecondKnowledge("内存分析" ,"内存优化" , "" , ""));
        data.add(new SecondKnowledge("Bitmap优化" ,"内存优化" , "" , ""));
        data.add(new SecondKnowledge("WakeLock" ,"电量优化" , "" , ""));
        data.add(new SecondKnowledge("JobScheduler" ,"电量优化" , "" , ""));
        data.add(new SecondKnowledge("设计模式" ,"设计模式与架构" , "" , ""));
        data.add(new SecondKnowledge("MVC、MVP、MVVM" ,"设计模式与架构" , "" , ""));
        data.add(new SecondKnowledge("组件化" ,"设计模式与架构" , "" , ""));
        data.add(new SecondKnowledge("NDK" ,"NDK开发" , "" , ""));
        data.add(new SecondKnowledge("WebView" ,"混合开发" , "" , ""));
        data.add(new SecondKnowledge("ReactNative" ,"混合开发" , "" , ""));
        data.add(new SecondKnowledge("Flutter" ,"混合开发" , "" , ""));
        data.add(new SecondKnowledge("Okhttp" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("Retrofit" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("Volley" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("Glide" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("Dagger2" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("ButterKnife" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("EventBus" ,"开源框架" , "" , ""));
        data.add(new SecondKnowledge("RxJava" ,"开源框架" , "状态监听" , ""));
        data.add(new SecondKnowledge("热修复" ,"动态化" , "http、https、socket" , ""));
        data.add(new SecondKnowledge("插件化" ,"动态化" , "" , ""));
        data.add(new SecondKnowledge("安全" ,"动态化" , "" , ""));
        data.add(new SecondKnowledge("网络优化" ,"动态化" , "" , ""));
        return data;
    }

    /**
     * 定义静态内部类+弱引用。避免handler持有外部对象。导致内存泄露
     */
    private static class MyHandler extends Handler{
        private WeakReference<SecondModel> model;

        public MyHandler(SecondModel model) {
            this.model = new WeakReference<>(model);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SecondModel first = model.get();
            if(first != null){
                first.handleMessage(msg);
            }
        }
    }
}
