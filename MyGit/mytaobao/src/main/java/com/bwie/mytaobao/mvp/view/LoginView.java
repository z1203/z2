package com.bwie.mytaobao.mvp.view;

/**
 * Created by 张丹阳 on 2017/10/12.
 */

public interface LoginView {
    //用户名输入错误
    void setLoNameError();

    //密码输入错误
    void setLoPwdError();

    //登录失败
    void toEmpty();

    //登录成功
    void toLogin();
}
