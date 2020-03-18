package com.xiangjw.androidtrainapp.ui.first;


import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.xiangjw.androidtrainapp.utils.DebugLog;

public class FirstModel implements FirstContact.Model{
    FirstContact.ModelLoadListener listener;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    listener.loadDone((String)msg.obj);
                    DebugLog.i(FirstModel.class , "loadOk");
                    break;
            }
        }
    };
    @Override
    public void requestData(FirstContact.ModelLoadListener listener){
        this.listener = listener;
        new Thread(){
            @Override
            public void run() {
                try {
                    DebugLog.i(FirstModel.class , "startLoad");
                    Thread.sleep(3000);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = "Test from network";
                    handler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
