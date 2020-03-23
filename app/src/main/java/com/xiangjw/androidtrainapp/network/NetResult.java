package com.xiangjw.androidtrainapp.network;

public class NetResult<T> {
    public static final int PAGE_NUM = 10;

    private boolean success;

    private String msg;

    private T object;

    public NetResult(boolean success, String msg, T object) {
        this.success = success;
        this.msg = msg;
        this.object = object;
    }

    public static <M> NetResult success(M object){
        return new NetResult<M>(true , null , object);
    }

    public static <M> NetResult fail(String msg){
        return new NetResult<M>(false , msg , null);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public T getObject() {
        return object;
    }
}
