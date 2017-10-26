package com.bwie.mockexam.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwie.mockexam.bean.RegBean;
import com.bwie.mockexam.okhttp.GsonObjectCallback;
import com.bwie.mockexam.okhttp.OkHttp3Utils;
import com.bwie.mockexam.utils.Url_util;
import com.bwie.mockexam.view.LoginListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/10/12.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final Context context, final String name, String pwd, final LoginListener loginListener) {
        if(TextUtils.isEmpty(name)){
            loginListener.onLoNameError();
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            loginListener.onLoPwdError();
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断手机号不能低于11位
        if(Url_util.phoneError(context,name)==false){
            return;
        }
        //判断密码框不能输入特殊字符
        if(Url_util.compileExChar(context,pwd)==false){
            return;
        }

        Map<String ,String> logParams=new HashMap<>();
        logParams.put("mobile",name);
        logParams.put("password",pwd);
        OkHttp3Utils.doPost(Url_util.LOGIN, logParams, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regRequestBean) {
                if(regRequestBean.getCode()==0){
                    SharedPreferences shared = Url_util.getShared(context);
                    SharedPreferences.Editor edit = shared.edit();
                    edit.putString("name",name);
                    edit.putInt("id",regRequestBean.getData().getUid());
                    edit.putBoolean("flag",true);
                    edit.commit();
                    loginListener.onLoginSuccess();
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                }else {
                    loginListener.unSuccess();
                    Toast.makeText(context, "用户名或密码有误", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });
    }
}
