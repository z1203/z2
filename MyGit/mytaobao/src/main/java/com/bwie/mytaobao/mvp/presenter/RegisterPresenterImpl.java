package com.bwie.mytaobao.mvp.presenter;

import android.content.Context;

import com.bwie.mytaobao.mvp.model.RegisterModel;
import com.bwie.mytaobao.mvp.model.RegisterModelImpl;
import com.bwie.mytaobao.mvp.view.RegisterListener;
import com.bwie.mytaobao.mvp.view.RegisterView;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public class RegisterPresenterImpl implements RegisterPresenter,RegisterListener{

    RegisterView registerView;
    RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void validatePass(Context context, String name, String pwd, String pwdCopy,String email) {
        registerModel.register(context,name,pwd,pwdCopy,email,this);

    }

    @Override
    public void onNameError() {
        if (registerView != null) {
            registerView.setNameError();
        }
    }

    @Override
    public void onPwdError() {
        if (registerView != null) {
            registerView.setPwdError();
        }
    }

    @Override
    public void onPwdCopyError() {
        if (registerView != null) {
            registerView.setPwdCopyError();
        }
    }

    @Override
    public void onEmailError() {
        if (registerView != null) {
            registerView.setEmailError();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.toRegisterChenge();
        }
    }
}
