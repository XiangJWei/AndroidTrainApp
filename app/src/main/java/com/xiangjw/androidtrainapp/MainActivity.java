package com.xiangjw.androidtrainapp;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xiangjw.androidtrainapp.databinding.ActivityMainBinding;
import com.xiangjw.androidtrainapp.ui.base.BaseActivity;
import com.xiangjw.androidtrainapp.uiutils.ToastUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private long lastExitSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化tab
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_first, R.id.navigation_second, R.id.navigation_third)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    protected ActivityMainBinding initBindingView(LayoutInflater inflater) {
        return ActivityMainBinding.inflate(inflater);
    }

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if(now - lastExitSeconds < 2000){
            finish();
//            System.exit(0);
        }else{
            lastExitSeconds = now;
            ToastUtils.show("再点击一次即退出程序");
        }
    }
}
