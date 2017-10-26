package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.AddShopCarBean;
import com.bwie.mytaobao.bean.GoodsDataBean;
import com.bwie.mytaobao.bean.LogOutBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

import static android.R.attr.id;

public class GoodsDatailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView goods_page;
    private TextView goodDatails_name;
    private TextView goodsDatails_price;
    private TextView yunfei;
    private TextView xiaoliang;
    private TextView fahuodi;
    private ImageView tui_last;
    private LinearLayout canshu;
    private Button gouwuche;
    private Button goumai;
    private WebView webView;
    private String[] split;
    private String name;
    private String price;
    private String storage;
    private int sum=1;
    private ImageView pop_image;
    private TextView pop_price;
    private TextView pop_kuC;
    private TextView pop_name;
    private ImageButton sub;
    private ImageButton plu;
    private TextView count;
    private TextView pop_zprice;
    private Button pop_qure;
    private String id;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_datails);
        //隐藏头部标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        goods_page = (ImageView) findViewById(R.id.goods_page);
        goodDatails_name = (TextView) findViewById(R.id.goodDatails_name);
        goodsDatails_price = (TextView) findViewById(R.id.goodsDatails_price);
        yunfei = (TextView) findViewById(R.id.yunfei);
        xiaoliang = (TextView) findViewById(R.id.xiaoliang);
        fahuodi = (TextView) findViewById(R.id.faHuoDi);
        tui_last = (ImageView) findViewById(R.id.tui_last);
        canshu = (LinearLayout) findViewById(R.id.canshu);
        gouwuche = (Button) findViewById(R.id.gouwuche);
        goumai = (Button) findViewById(R.id.goumai);
        webView = (WebView) findViewById(R.id.webView);

        //设置点击事件
        tui_last.setOnClickListener(this);
        canshu.setOnClickListener(this);
        gouwuche.setOnClickListener(this);
        goumai.setOnClickListener(this);

        //获取传过来的商品ID
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        Toast.makeText(GoodsDatailsActivity.this, id,Toast.LENGTH_SHORT).show();

        OkHttp3Utils.doGet(Url_Utils.GOODS_DATAILS +"&goods_id="+ id, new GsonObjectCallback<GoodsDataBean>() {
            @Override
            public void onUi(GoodsDataBean goodsDataBean) {
                if(goodsDataBean.getCode()==200){
                    //获取图片
                    String image = goodsDataBean.getDatas().getGoods_image();
                    //分割图片地址
                    split = image.split(",");
                    Picasso.with(GoodsDatailsActivity.this)
                            .load(split[0])
                            .into(goods_page);
                    //获取商品名称
                    name = goodsDataBean.getDatas().getGoods_info().getGoods_name();
                    goodDatails_name.setText(name);
                    //获取商品价格
                    price = goodsDataBean.getDatas().getGoods_info().getGoods_price();
                    goodsDatails_price.setText("¥ "+ price);

                    yunfei.setText(goodsDataBean.getDatas().getGoods_hair_info().getContent());
                    xiaoliang.setText(goodsDataBean.getDatas().getGoods_hair_info().getIf_store_cn());
                    fahuodi.setText(goodsDataBean.getDatas().getGoods_hair_info().getArea_name());
                    WebSettings settings = webView.getSettings();
                    settings.setJavaScriptEnabled(true);
                    //设置支持缩放
                    settings.setBuiltInZoomControls(true);
                    webView.loadUrl(Url_Utils.JIESHAO +"&goods_id="+ id);
                    //popupWindow中的数据
                    //库存
                    storage = goodsDataBean.getDatas().getGoods_info().getGoods_storage();
                }
            }
            @Override
            public void onFailed(Call call, IOException e) {
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tui_last:
                GoodsDatailsActivity.this.finish();
                break;
            case R.id.canshu:

                break;
            case R.id.gouwuche:
                shared = Url_Utils.getShared(GoodsDatailsActivity.this);
                final boolean flag = shared.getBoolean("flag", false);
                if(flag==true){
                    View inflate = View.inflate(GoodsDatailsActivity.this, R.layout.popup_window, null);
                    pop_image = inflate.findViewById(R.id.pop_Image);
                    pop_price = inflate.findViewById(R.id.pop_price);
                    pop_kuC = inflate.findViewById(R.id.pop_kuC);
                    pop_name = inflate.findViewById(R.id.pop_name);
                    sub = inflate.findViewById(R.id.sub);
                    plu = inflate.findViewById(R.id.plus);
                    count = inflate.findViewById(R.id.sum);
                    pop_zprice = inflate.findViewById(R.id.pop_Zprice);
                    pop_qure = inflate.findViewById(R.id.pop_qure);

                    final PopupWindow popupWindow = new PopupWindow(inflate, WindowManager.LayoutParams.MATCH_PARENT,580,true);
                    View popWimdow = View.inflate(GoodsDatailsActivity.this, R.layout.activity_goods_datails, null);
                    popupWindow.setOutsideTouchable(true);   //设置外部点击关闭ppw窗口
                    popupWindow.setFocusable(true);
                    popupWindow.setTouchable(true);
                    popupWindow.setBackgroundDrawable(new ColorDrawable());
                    popupWindow.showAtLocation(popWimdow, Gravity.BOTTOM,0,0);
                    //设置值
                    Picasso.with(GoodsDatailsActivity.this)
                            .load(split[0])
                            .into(pop_image);
                    pop_name.setText(name);
                    pop_price.setText("¥ "+price);
                    pop_kuC.setText("库存"+storage+"件");
                    count.setText(sum+"");
                    sub.setEnabled(false);
                    //设置总价格
                    final Double Zprice = Double.parseDouble(price);
                    pop_zprice.setText("总计：¥"+ Zprice);
                    //减号的点击事件
                    sub.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sum--;
                            count.setText(sum+"");
                            pop_zprice.setText("总计：¥"+ Zprice*sum);

                            if(sum<2){
                                sub.setEnabled(false);
                            }
                        }
                    });
                    //加号的点击事件
                    plu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sum++;
                            count.setText(sum+"");
                            sub.setEnabled(true);
                            pop_zprice.setText("总计：¥"+ Zprice*sum);
                        }
                    });

                    //确定加入购物车
                    pop_qure.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String key = shared.getString("key", "");
                            Map<String,String> map = new HashMap<String, String>();
                            map.put("key",key);
                            map.put("goods_id",id);
                            map.put("quantity",sum+"");

                            OkHttp3Utils.doPost(Url_Utils.ADD_TO_CART, map, new GsonObjectCallback<AddShopCarBean>() {
                                @Override
                                public void onUi(AddShopCarBean logOutBean) {
                                    if(logOutBean.getCode()==200&&logOutBean.getDatas()==1){
                                        popupWindow.dismiss();
                                        Toast.makeText(GoodsDatailsActivity.this,"已加入购物车"+sum+"件",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailed(Call call, IOException e) {
                                }
                            });
                        }
                    });
                }else {
                    Intent intent=new Intent(GoodsDatailsActivity.this,Login_Register_Activity.class);
                    startActivity(intent);
                }
                break;
            case R.id.goumai:

                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
