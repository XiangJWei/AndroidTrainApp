package com.xiangjw.androidtrainapp.ui.first.base;

import com.xiangjw.androidtrainapp.utils.DebugLog;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class BasePresenter <M extends IBaseModel , V extends IBaseView>{
    private M model;//model对象
    private V view;//经过动态代理绑定的对象，调用其方法时就会进入handler里，可进行拦截

    private WeakReference<V> reference;//原本尊view的弱引用，为了不影响本尊被回收。

    protected void attachView(V view){
        reference = new WeakReference<>(view);
        //弱引用存本尊，动过动态代理来扩展和控制本尊的实现
        Object obj = Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces() , new ViewProxyHandler(reference.get()));
        this.view = (V)obj;//生成代理对象。这样调this.view的方法时就会进入Handler里去扩展或截断

        if(model == null){
            model = createModel();
        }
    }

    protected M getModel(){
        return model;
    }

    protected V getView(){
        return view;
    }

    protected void detachView(){
        model = null;
        if (isViewAttached()){
            reference.clear();
            reference = null;
        }
    }

    protected boolean isViewAttached(){
        return reference != null && reference.get() != null;
    }

    protected abstract M createModel();

    private class ViewProxyHandler implements InvocationHandler{
        private V handlerView;

        public ViewProxyHandler(V handlerView){
            this.handlerView = handlerView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            DebugLog.i(BasePresenter.class , "调用：" + handlerView.getClass().getSimpleName() + "->" + method.getName());
            //本尊调用方法时触发
            if(isViewAttached()){
                //弱引用里的本尊没有被回收，才会调用
                return method.invoke(handlerView , args);
            }
            return null;
        }
    }
}
