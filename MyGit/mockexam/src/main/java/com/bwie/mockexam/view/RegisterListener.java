package com.bwie.mockexam.view;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterListener {

    void onNameError();

    void onPwdError();

    void onSuccess();
}
