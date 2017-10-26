package com.bwie.mockexam.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mockexam.R;
import com.bwie.mockexam.utils.Url_util;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView user_var;
    private Button tui_login;
    private Button jump;
    private Intent intent;
    private TextView user_name;
    private TextView nickName;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_var = (ImageView) findViewById(R.id.user_var);
        user_name = (TextView) findViewById(R.id.user_name);
        nickName = (TextView) findViewById(R.id.nickName);
        tui_login = (Button) findViewById(R.id.tui_login);
        jump = (Button) findViewById(R.id.jump);

        user_var.setOnClickListener(this);
        tui_login.setOnClickListener(this);
        jump.setOnClickListener(this);

        shared = Url_util.getShared(this);
        boolean flag = shared.getBoolean("flag", false);
        if(flag==true){
            String name = shared.getString("name", "");
            user_name.setText(name);
            nickName.setText("dddddd");
            SharedPreferences.Editor edit = shared.edit();
            edit.putBoolean("flag",false);
            edit.commit();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_var:
                intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.tui_login:
                SharedPreferences.Editor edit = shared.edit();
                edit.putBoolean("flag",false);
                edit.commit();

                Intent logoutIntent = new Intent(UserActivity.this, UserActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                break;
            case R.id.jump:
                intent=new Intent(UserActivity.this,ShopActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
