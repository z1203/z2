package com.bwie.mockexam.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.mockexam.R;
import com.bwie.mockexam.presenter.LoginPresenter;
import com.bwie.mockexam.presenter.LoginPresenterImpl;
import com.bwie.mockexam.view.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,LoginView {

    private EditText login_phone;
    private EditText login_pwd;
    private Button login;
    private Button lo_register;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_phone = (EditText) findViewById(R.id.login_phone);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login = (Button) findViewById(R.id.login);
        lo_register = (Button) findViewById(R.id.login_register);

        login.setOnClickListener(this);
        lo_register.setOnClickListener(this);
        loginPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String name = login_phone.getText().toString();
                String pwd = login_pwd.getText().toString();
                loginPresenter.logindatePass(this,name,pwd);
                break;
            case R.id.login_register:
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void setLoNameError() {
        login_phone.setError("name cannot empty");
    }

    @Override
    public void setLoPwdError() {
        login_pwd.setError("pwd cannot empty");
    }

    @Override
    public void toEmpty() {
        login_phone.setText("");
        login_pwd.setText("");
    }

    @Override
    public void toLogin() {
        Intent intent=new Intent(MainActivity.this,UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        //结束当前页面
        MainActivity.this.finish();
    }
}
