package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.adapter.SearchAdapter;
import com.bwie.mytaobao.bean.Goods_Search;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView return_last;
    private EditText search_text;
    private Button click_search;
    private RecyclerView shop_recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //隐藏头部标题栏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //找控件
        return_last = (ImageView) findViewById(R.id.return_last);
        search_text = (EditText) findViewById(R.id.search_text);
        click_search = (Button) findViewById(R.id.click_search);
        shop_recycle = (RecyclerView) findViewById(R.id.shop_recycler);

        //设置点击事件
        return_last.setOnClickListener(this);
        click_search.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.return_last://点击返回箭头，结束当前页面
                SearchActivity.this.finish();
                break;
            case R.id.click_search:
                getSearchData();
                break;
        }
    }

    //获取查询的商品数据
    public void getSearchData(){
        OkHttp3Utils.doGet(Url_Utils.SEARCH , new GsonObjectCallback<Goods_Search>() {
            @Override
            public void onUi(Goods_Search goods_search) {
                if(goods_search.getCode()==200){
                    final List<Goods_Search.DatasBean.GoodsListBean> goods_list = goods_search.getDatas().getGoods_list();
                    //获取布局管理器
                    LinearLayoutManager layoutManager=new LinearLayoutManager(SearchActivity.this);
                    shop_recycle.setLayoutManager(layoutManager);
                    SearchAdapter adapter=new SearchAdapter(SearchActivity.this,goods_list);
                    shop_recycle.setAdapter(adapter);
                    //点击跳转到商品详情界面
                    adapter.setOnItemClickListener(new SearchAdapter.OnItemCilckListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent=new Intent(SearchActivity.this,GoodsDatailsActivity.class);
                            intent.putExtra("id",goods_list.get(position).getGoods_id());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

}
