package com.bwie.mytaobao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 张丹阳 on 2017/10/11.
 */

public class Url_Utils {

    public static final String url="http://169.254.110.146/mobile/index.php?";

    public static final String CLIENT="android";       //client固定值

    public static final String LOGIN= url +"act=login";                       //登录
    public static final String REGISTER= url +"act=login&op=register";       //注册
    public static final String LOGOUT= url +"act=logout";                     //注销
    public static final String STAIR_CLASSIFY= url +"act=goods_class";//一级分类
    public static final String DICHOTOMIES = url + "act=goods_class&gc_id=";//二三级分类
    public static final String SEARCH = url + "act=goods&op=goods_list&page=100";//搜索
    public static final String GOODS_DATAILS = url + "act=goods&op=goods_detail";//商品详情
    public static final String JIESHAO = url + "act=goods&op=goods_body";//商品介绍
    public static final String ADD_TO_CART = url + "act=member_cart&op=cart_add";//添加到购物车
    public static final String GOUWUCHE = url + "act=member_cart&op=cart_list";//购物车表
    public static final String DELECT = url + "act=member_cart&op=cart_del";//删除
    public static final String ADDRESS = url + "act=member_address&op=address_add";//收货地址
    public static final String ZHANSHI = url + "act=member_address&op=address_list";//展示收货地址
    public static final String ADD_DEL = url + "act=member_address&op=address_del";//删除收货地址

    public static SharedPreferences shared=null;
    public static final String Key="user";

    public static SharedPreferences getShared(Context context){
        if(shared==null){
            shared = context.getSharedPreferences(Key,Context.MODE_PRIVATE);
        }
        return shared;
    }

}
