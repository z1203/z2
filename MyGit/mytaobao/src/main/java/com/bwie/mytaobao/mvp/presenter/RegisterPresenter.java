package com.bwie.mytaobao.mvp.presenter;

import android.content.Context;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterPresenter {
    void validatePass(Context context, String name, String pwd,String pwdCopy,String email);
}
