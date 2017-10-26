package com.bwie.mockexam.presenter;

import android.content.Context;

import com.bwie.mockexam.model.LoginModel;
import com.bwie.mockexam.model.LoginModelImpl;
import com.bwie.mockexam.view.LoginListener;
import com.bwie.mockexam.view.LoginView;

/**
 * Created by 张丹阳 on 2017/10/12.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginListener {

    LoginView loginView;
    LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    @Override
    public void logindatePass(Context context, String name, String pwd) {
        loginModel.login(context,name,pwd,this);
    }

    @Override
    public void onLoNameError() {
        if(loginView!=null){
            loginView.setLoNameError();
        }
    }

    @Override
    public void onLoPwdError() {
        if(loginView!=null){
            loginView.setLoPwdError();
        }
    }

    @Override
    public void unSuccess() {
        if(loginView!=null){
            loginView.toEmpty();
        }
    }

    @Override
    public void onLoginSuccess() {
        if(loginView!=null){
            loginView.toLogin();
        }
    }
}
