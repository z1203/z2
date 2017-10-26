package com.bwie.mytaobao.mvp.model;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.bwie.mytaobao.bean.RegRequestBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;
import com.bwie.mytaobao.mvp.view.RegisterListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public class RegisterModelImpl implements RegisterModel {
    @Override
    public void register(final Context context, String name, String pwd, String pwdCopy, String email, final RegisterListener registerListener) {
        if(TextUtils.isEmpty(name)){
            registerListener.onNameError();
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            registerListener.onPwdError();
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pwdCopy)){
            registerListener.onPwdCopyError();
            Toast.makeText(context, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pwdCopy.equals(pwd)){
            Toast.makeText(context, "请输入相同的密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)){
            registerListener.onEmailError();
            Toast.makeText(context, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String ,String> regParams=new HashMap<>();
        regParams.put("username",name);
        regParams.put("password",pwd);
        regParams.put("password_confirm",pwdCopy);
        regParams.put("email",email);
        regParams.put("client",Url_Utils.CLIENT);
        OkHttp3Utils.doPost(Url_Utils.REGISTER, regParams, new GsonObjectCallback<RegRequestBean>() {
            @Override
            public void onUi(RegRequestBean regRequestBean) {
                Log.e("SSSS",regRequestBean+"注册返回的数据");
                if(regRequestBean.getCode()==200){
                    registerListener.onSuccess();
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailed(Call call, IOException e) {
                //Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
