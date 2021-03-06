package com.bwie.mytaobao.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.fragment.GouWuFragment;
import com.bwie.mytaobao.fragment.HomeFragment;
import com.bwie.mytaobao.fragment.MyFragment;
import com.bwie.mytaobao.fragment.WeiTaoFragment;
import com.bwie.mytaobao.utils.Url_Utils;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private HomeFragment homeFragment;
    private WeiTaoFragment weiTaoFragment;
    private GouWuFragment gouWuFragment;
    private MyFragment myFragment;
    private RadioButton home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏头部标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        home_page = (RadioButton) findViewById(R.id.home_page);
        //获得fragment的实例
        homeFragment = new HomeFragment();
        weiTaoFragment = new WeiTaoFragment();
        gouWuFragment = new GouWuFragment();
        myFragment = new MyFragment();

        //将fragment加载出来
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, homeFragment);
        transaction.add(R.id.frame, weiTaoFragment);
        transaction.add(R.id.frame, gouWuFragment);
        transaction.add(R.id.frame, myFragment);
        transaction.commit();

        getSupportFragmentManager().beginTransaction().show(homeFragment)
                .hide(weiTaoFragment)
                .hide(gouWuFragment)
                .hide(myFragment).commit();

        //为radioGroup设置点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.home_page:
                        getSupportFragmentManager().beginTransaction().show(homeFragment)
                                .hide(weiTaoFragment)
                                .hide(gouWuFragment)
                                .hide(myFragment).commit();

                        break;
                    case R.id.weitao:
                        getSupportFragmentManager().beginTransaction().show(weiTaoFragment)
                                .hide(homeFragment)
                                .hide(gouWuFragment)
                                .hide(myFragment).commit();
                        break;
                    case R.id.shopcar:
                        getSupportFragmentManager().beginTransaction().show(gouWuFragment)
                                .hide(homeFragment)
                                .hide(weiTaoFragment)
                                .hide(myFragment).commit();
                        break;
                    case R.id.rb_my:
                        getSupportFragmentManager().beginTransaction().show(myFragment)
                                .hide(homeFragment)
                                .hide(weiTaoFragment)
                                .hide(gouWuFragment).commit();
                        SharedPreferences shared = Url_Utils.getShared(MainActivity.this);
                        boolean flag = shared.getBoolean("flag", false);
                        if(flag==false){
                            Intent intent=new Intent(MainActivity.this,Login_Register_Activity.class);
                            startActivity(intent);
                        }
                        break;
                }
            }
        });

    }
}
