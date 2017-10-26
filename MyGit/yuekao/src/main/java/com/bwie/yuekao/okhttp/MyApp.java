package com.bwie.yuekao.okhttp;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * 1. 类的用途
 * 2. @author forever
 * 3. @date 2017/9/8 12:33
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader.getInstance().init(configuration);

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
