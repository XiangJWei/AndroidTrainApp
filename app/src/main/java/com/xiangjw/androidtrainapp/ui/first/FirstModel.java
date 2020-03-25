package com.xiangjw.androidtrainapp.ui.first;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.xiangjw.androidtrainapp.bean.first.FirstKnowledge;
import com.xiangjw.androidtrainapp.network.NetResult;
import com.xiangjw.androidtrainapp.utils.DebugLog;
import com.xiangjw.androidtrainapp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstModel implements FirstContact.Model{
    private static final int MSG_LOAD = 1;

    FirstContact.ModelLoadListener listener;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MSG_LOAD:
                    listener.loadDone((NetResult)msg.obj);
                    break;
            }
        }
    };
    @Override
    public void requestData(int page , int pageNum , final String keyword , FirstContact.ModelLoadListener listener){
        this.listener = listener;
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    List<FirstKnowledge> data = getData();
                    List<FirstKnowledge> pageData = new ArrayList<>();
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

    private List<FirstKnowledge> getData(){
        List<FirstKnowledge> data= new ArrayList<FirstKnowledge>();
        data.add(new FirstKnowledge("Activity" ,"基础知识", "四大组件之首" , "2020-03-19"));
        data.add(new FirstKnowledge("Fragment" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Service" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Broadcast" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("ContentProvider" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Application" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("IntentFilter" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Mainfest" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("进程" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("线程" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Layouts" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("Widgets" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("ListView" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("RecycleView" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("SurfaceView" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("ViewPager" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("Window" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("Animation" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("通知栏" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("屏幕适配" ,"界面" , "" , ""));
        data.add(new FirstKnowledge("OkHttp" ,"第三方" , "" , ""));
        data.add(new FirstKnowledge("Retrofit" ,"第三方" , "" , ""));
        data.add(new FirstKnowledge("RxJava" ,"第三方" , "" , ""));
        data.add(new FirstKnowledge("Glide" ,"第三方" , "" , ""));
        data.add(new FirstKnowledge("ButterKnife" ,"第三方" , "" , ""));
        data.add(new FirstKnowledge("电话" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("定位" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("相机" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("相册" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("壁纸" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("SD卡" ,"设备" , "" , ""));
        data.add(new FirstKnowledge("音频" ,"多媒体" , "" , ""));
        data.add(new FirstKnowledge("视频" ,"多媒体" , "" , ""));
        data.add(new FirstKnowledge("移动网络和WIFI" ,"网络" , "状态监听" , ""));
        data.add(new FirstKnowledge("网络请求" ,"网络" , "http、https、socket" , ""));
        data.add(new FirstKnowledge("文件操作" ,"网络" , "" , ""));
        data.add(new FirstKnowledge("图片加载" ,"网络" , "" , ""));
        data.add(new FirstKnowledge("SQLite" ,"持久化" , "" , ""));
        data.add(new FirstKnowledge("File" ,"持久化" , "" , ""));
        data.add(new FirstKnowledge("SharedPreference" ,"持久化" , "" , ""));
        data.add(new FirstKnowledge("单元测试" ,"测试" , "" , ""));
        return data;
    }
}
