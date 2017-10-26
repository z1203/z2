package com.bwie.mockexam.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张丹阳 on 2017/10/14.
 */

public class Url_util {

    public static final Context context = null;
    public static final String url="http://120.27.23.105/";

    public static final String REGISTER=url + "user/reg?";//注册
    public static final String LOGIN=url + "user/login?";//登录

    public static boolean compileExChar(Context context,String str){

        String limitEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";

        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);

        if( m.find()){
            Toast.makeText(context, "不允许输入特殊符号！", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public static boolean phoneError(Context context,String str){
        if(str.length()!=11){
            Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public static SharedPreferences shared=null;
    public static final String Key="user";

    public static SharedPreferences getShared(Context context){
        if(shared==null){
            shared = context.getSharedPreferences(Key,Context.MODE_PRIVATE);
        }
        return shared;
    }
}
