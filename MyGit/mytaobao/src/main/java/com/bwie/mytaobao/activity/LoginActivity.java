package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.mvp.presenter.LoginPresenter;
import com.bwie.mytaobao.mvp.presenter.LoginPresenterImpl;
import com.bwie.mytaobao.mvp.view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,LoginView {

    private EditText login_name;
    private EditText login_pwd;
    private Button login;
    private Button forget_pwd;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_name = (EditText) findViewById(R.id.login_name);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        forget_pwd = (Button) findViewById(R.id.forget_pwd);
        login = (Button) findViewById(R.id.login);

        //登陆的点击事件
        login.setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        String name = login_name.getText().toString();
        String pwd = login_pwd.getText().toString();
        loginPresenter.logindatePass(this,name,pwd);
    }

    @Override
    public void setLoNameError() {
        login_name.setError("name cannot empty");
    }

    @Override
    public void setLoPwdError() {
        login_pwd.setError("pwd cannot empty");
    }

    @Override
    public void toEmpty() {
        login_name.setText("");
        login_pwd.setText("");
    }

    @Override
    public void toLogin() {
        //注册成功跳转到登陆界面
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        //结束当前页面
        LoginActivity.this.finish();
    }

    //返回按钮的监听
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        //结束当前页面
        LoginActivity.this.finish();
    }

}
