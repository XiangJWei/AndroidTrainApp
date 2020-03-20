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
                    List<FirstKnowledge> data = getData();
                    List<FirstKnowledge> pageData = new ArrayList<>();
                    for(int i = 0 , okNum = 0 ; i < data.size() ; i ++){
                        if(pageData.size() >= pageNum){
                            break;
                        }
                        boolean isOk = false;
                        if(StringUtils.isNotEmpty(keyword)){
                            if(keyword.equals(data.get(i).getName())
                                || keyword.equals(data.get(i).getType())
                                || keyword.equals(data.get(i).getSubject())){
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
        data.add(new FirstKnowledge("Layouts" ,"基础知识" , "" , ""));
        data.add(new FirstKnowledge("Widgets" ,"基础知识" , "" , ""));
        return data;
    }
}
