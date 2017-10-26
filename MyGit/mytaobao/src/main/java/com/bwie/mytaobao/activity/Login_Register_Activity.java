package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bwie.mytaobao.R;

public class Login_Register_Activity extends AppCompatActivity {

    private Button login_first;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register_);

        login_first = (Button) findViewById(R.id.login_first);
        register = (Button) findViewById(R.id.register);

        //点击登录按钮跳转到登陆界面
        login_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Register_Activity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //点击注册按钮跳转到注册界面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Register_Activity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    //返回按钮的监听
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Login_Register_Activity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        //结束当前页面
        Login_Register_Activity.this.finish();
    }

}
