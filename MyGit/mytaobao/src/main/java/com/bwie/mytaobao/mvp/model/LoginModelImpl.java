package com.bwie.mytaobao.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwie.mytaobao.bean.RegRequestBean;
import com.bwie.mytaobao.mvp.view.LoginListener;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

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

        Map<String ,String> logParams=new HashMap<>();
        logParams.put("username",name);
        logParams.put("password",pwd);
        logParams.put("client", Url_Utils.CLIENT);
        OkHttp3Utils.doPost(Url_Utils.LOGIN, logParams, new GsonObjectCallback<RegRequestBean>() {
            @Override
            public void onUi(RegRequestBean regRequestBean) {
                if(regRequestBean.getCode()==200){
                    SharedPreferences shared = Url_Utils.getShared(context);
                    SharedPreferences.Editor edit = shared.edit();
                    edit.putBoolean("flag",true);
                    edit.putString("name",regRequestBean.getDatas().getUsername());
                    edit.putString("userid",regRequestBean.getDatas().getUserid());
                    edit.putString("key",regRequestBean.getDatas().getKey());
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
