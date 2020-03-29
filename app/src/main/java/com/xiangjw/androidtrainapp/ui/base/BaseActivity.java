package com.xiangjw.androidtrainapp.ui.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import com.xiangjw.androidtrainapp.ui.first.activities.A1ActiActivity;
import com.xiangjw.androidtrainapp.utils.DebugLog;

public abstract class BaseActivity<T extends ViewBinding> extends AppCompatActivity {
    protected T binding;

    private boolean showActionBack = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onCreate");

        binding = initBindingView(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        if (showActionBack && getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected abstract T initBindingView(LayoutInflater inflater);

    protected void openActivity(Class target){
        Intent intent = new Intent();
        intent.setClass(this , target);
        startActivity(intent);
    }

    protected void openActivity(Class target , Bundle bundle){
        Intent intent = new Intent();
        intent.setClass(this , target);
        startActivity(intent , bundle);
    }

    protected void back(){
        finish();
    }

    public void hideActionBack() {
        this.showActionBack = false;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (showActionBack && getSupportActionBar() != null){
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;

                default:
                    break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onResume");
    }

    @Override
    protected void onPause() {
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onRestart");
    }

    @Override
    protected void onDestroy() {
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DebugLog.i(BaseActivity.class , this.getClass().getSimpleName() + " onRestoreInstanceState");
    }
}
