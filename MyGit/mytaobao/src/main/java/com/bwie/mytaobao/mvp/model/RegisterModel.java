package com.bwie.mytaobao.mvp.model;

import android.content.Context;

import com.bwie.mytaobao.mvp.view.RegisterListener;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public interface RegisterModel {
    void register(Context context, String name, String pwd, String pwdCopy,String email, RegisterListener registerListener);
}
