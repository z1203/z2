package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.LogOutBean;
import com.bwie.mytaobao.bean.RegRequestBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class SetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tui_login;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        tui_login = (Button) findViewById(R.id.tui_login);

        tui_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        shared = Url_Utils.getShared(this);
        String key = shared.getString("key", "");
        String name = shared.getString("name", "");

        Map<String ,String> tui_log=new HashMap<>();
        tui_log.put("key",key);
        tui_log.put("client", Url_Utils.CLIENT);
        tui_log.put("username",name);
        OkHttp3Utils.doPost(Url_Utils.LOGOUT, tui_log, new GsonObjectCallback<LogOutBean>() {
            @Override
            public void onUi(LogOutBean regRequestBean) {
                if(regRequestBean.getCode()==200 && regRequestBean.getDatas()==1){
                    Intent intent=new Intent(SetActivity.this,MainActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor edit = shared.edit();
                    edit.putBoolean("flag", false);
                    edit.commit();
                    Toast.makeText(SetActivity.this,"注销登录",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
}
