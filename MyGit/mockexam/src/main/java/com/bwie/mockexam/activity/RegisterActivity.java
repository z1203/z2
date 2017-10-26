package com.bwie.mockexam.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.mockexam.R;
import com.bwie.mockexam.presenter.RegisterPreImpl;
import com.bwie.mockexam.presenter.RegisterPresenter;
import com.bwie.mockexam.view.RegisterListener;
import com.bwie.mockexam.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterView {

    private EditText reg_phone;
    private EditText reg_pwd;
    private Button register;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_phone = (EditText) findViewById(R.id.reg_phone);
        reg_pwd = (EditText) findViewById(R.id.reg_pwd);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);

        presenter = new RegisterPreImpl(this);

    }

    @Override
    public void onClick(View view) {
        String phone = reg_phone.getText().toString();
        String pwd = reg_pwd.getText().toString();
        presenter.regData(this,phone,pwd);
    }

    @Override
    public void setNameError() {
        reg_phone.setError("phone cannot be empty");
    }

    @Override
    public void setPwdError() {
        reg_pwd.setError("pwd cannot be empty");
    }

    @Override
    public void toRegisterChenge() {
        //注册成功跳转到登陆界面
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //结束当前Activity
        RegisterActivity.this.finish();
    }
}
