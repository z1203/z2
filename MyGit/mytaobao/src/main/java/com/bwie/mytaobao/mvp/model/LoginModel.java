package com.bwie.mytaobao.mvp.model;

import android.content.Context;

import com.bwie.mytaobao.mvp.view.LoginListener;

/**
 * Created by 张丹阳 on 2017/10/12.
 */

public interface LoginModel {
    void login(Context context, String name, String pwd, LoginListener loginListener);
}
