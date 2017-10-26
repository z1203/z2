package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.mvp.presenter.RegisterPresenter;
import com.bwie.mytaobao.mvp.presenter.RegisterPresenterImpl;
import com.bwie.mytaobao.mvp.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterView {

    private EditText reg_name;
    private EditText reg_pwd;
    private EditText reg_pwdCopy;
    private Button reg_but;
    private RegisterPresenter presenter;
    private EditText reg_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_name = (EditText) findViewById(R.id.reg_name);
        reg_pwd = (EditText) findViewById(R.id.reg_pwd);
        reg_pwdCopy = (EditText) findViewById(R.id.reg_pwdCopy);
        reg_but = (Button) findViewById(R.id.reg_but);
        reg_email = (EditText) findViewById(R.id.reg_email);

        //为注册按钮设置点击事件
        reg_but.setOnClickListener(this);

        presenter = new RegisterPresenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        //获取输入的用户信息
        String name = reg_name.getText().toString();
        String pwd = reg_pwd.getText().toString();
        String pwdCopt = reg_pwdCopy.getText().toString();
        String email = reg_email.getText().toString();
        //调用方法
        presenter.validatePass(this,name,pwd,pwdCopt,email);
    }

    @Override
    public void setNameError() {
        reg_name.setError("name cannot be empty");
    }

    @Override
    public void setPwdError() {
        reg_pwd.setError("pwd cannot be empty");
    }

    @Override
    public void setPwdCopyError() {
        reg_pwdCopy.setError("pwdCopy cannot be empty");
    }

    @Override
    public void setEmailError() {
        reg_email.setError("email cannot be empty");
    }

    @Override
    public void toRegisterChenge() {
        //注册成功跳转到登陆界面
        Intent intent=new Intent(RegisterActivity.this,Login_Register_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //结束当前Activity
        RegisterActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
