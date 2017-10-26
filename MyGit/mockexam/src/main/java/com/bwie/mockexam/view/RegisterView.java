package com.bwie.mockexam.view;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterView {
    //用户名输入错误
    void setNameError();

    //密码输入错误
    void setPwdError();

    //注册成功跳转
    void toRegisterChenge();
}
