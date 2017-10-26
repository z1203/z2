package com.bwie.mockexam.model;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwie.mockexam.bean.RegBean;
import com.bwie.mockexam.okhttp.GsonObjectCallback;
import com.bwie.mockexam.okhttp.OkHttp3Utils;
import com.bwie.mockexam.utils.Url_util;
import com.bwie.mockexam.view.RegisterListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/10/14.
 */

public class RegisterModelImpl implements RegisterModel {

    @Override
    public void register(final Context context, String name, String pwd, final RegisterListener registerListener) {
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
        //判断手机号不能低于11位
        if(Url_util.phoneError(context,name)==false){
            return;
        }
        //判断密码框不能输入特殊字符
        if(Url_util.compileExChar(context,pwd)==false){
            return;
        }

        Map<String,String> regMap=new HashMap<>();
        regMap.put("mobile",name);
        regMap.put("password",pwd);

        OkHttp3Utils.doPost(Url_util.REGISTER, regMap, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regBean) {
                if(regBean.getCode()==0){
                    registerListener.onSuccess();
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
