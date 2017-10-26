package com.bwie.fenxiang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class MainActivity extends AppCompatActivity {

    private Button share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        share = (Button) findViewById(R.id.but);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给微信分享添加计数元事件
                MobclickAgent.onEvent(MainActivity.this, "click");
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                        .withText("分享分享分享分享分享分享")//分享内容
                        .setCallback(shareListener)//回调监听器
                        .share();
            }
        });

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            Log.i("sss", "失败" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("home");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("home");
        MobclickAgent.onPause(this);
    }
}
