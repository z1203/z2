package com.bwie.fenxiang;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by 张丹阳 on 2017/10/10.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }
    {
        //AppID必须填对
        PlatformConfig.setWeixin("wx1c2f30b7393d48b5", "436e61598fce8e202eb3dd69ca586d27");
    }
}
