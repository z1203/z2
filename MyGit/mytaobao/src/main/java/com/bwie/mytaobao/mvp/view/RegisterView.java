package com.bwie.mytaobao.mvp.view;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterView {
    //用户名输入错误
    void setNameError();

    //密码输入错误
    void setPwdError();

    //密码重新输入错误
    void setPwdCopyError();

    //邮箱输入错误
    void setEmailError();

    //注册成功跳转
    void toRegisterChenge();
}
