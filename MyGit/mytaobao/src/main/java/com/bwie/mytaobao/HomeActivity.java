package com.bwie.mytaobao;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private int i=3;
    private Handler handler=new Handler(){};
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if(i>0){
                i--;
                handler.postDelayed(runnable,1000);
            }else {
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                handler.removeCallbacks(runnable);
                HomeActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //隐藏头部标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        handler.postDelayed(runnable,1000);

    }
}
