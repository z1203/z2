package com.bwie.mockexam.presenter;

import android.content.Context;

import com.bwie.mockexam.model.RegisterModel;
import com.bwie.mockexam.model.RegisterModelImpl;
import com.bwie.mockexam.view.RegisterListener;
import com.bwie.mockexam.view.RegisterView;

/**
 * Created by 张丹阳 on 2017/10/14.
 */

public class RegisterPreImpl implements RegisterPresenter,RegisterListener {

    RegisterView registerView;
    RegisterModel registerModel;

    public RegisterPreImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void regData(Context context, String name, String pwd) {
        registerModel.register(context,name,pwd,this);
    }

    @Override
    public void onNameError() {
        if(registerView!=null){
            registerView.setNameError();
        }
    }

    @Override
    public void onPwdError() {
        if(registerView!=null){
            registerView.setPwdError();
        }
    }

    @Override
    public void onSuccess() {
        if(registerView!=null){
            registerView.toRegisterChenge();
        }
    }
}
