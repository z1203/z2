package com.bwie.mytaobao.mvp.view;

/**
 * Created by 张丹阳 on 2017/10/12.
 */

public interface LoginListener {

    void onLoNameError();

    void onLoPwdError();

    void unSuccess();

    void onLoginSuccess();
}
