package com.bwie.mockexam.model;

import android.content.Context;

import com.bwie.mockexam.view.RegisterListener;

/**
 * Created by 张丹阳 on 2017/10/14.
 */

public interface RegisterModel {
    void register(Context context, String name, String pwd, RegisterListener registerListener);
}
