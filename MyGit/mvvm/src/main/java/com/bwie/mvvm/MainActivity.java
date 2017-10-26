package com.bwie.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ActivityMainBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        User user=new User();
        user.setName("张三");
        user.setAge(20);
        user.setImage("http://img2.cache.netease.com/auto/2016/7/28/201607282215432cd8a.jpg");
        dataBinding.setUser(user);

    }
}
