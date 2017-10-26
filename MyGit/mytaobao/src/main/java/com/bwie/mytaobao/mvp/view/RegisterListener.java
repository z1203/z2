package com.bwie.mytaobao.mvp.view;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterListener {

    void onNameError();

    void onPwdError();

    void onPwdCopyError();

    void onEmailError();

    void onSuccess();
}
