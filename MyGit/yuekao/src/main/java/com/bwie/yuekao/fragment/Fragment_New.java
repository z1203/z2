package com.bwie.yuekao.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayout;
import com.bawei.swiperefreshlayoutlibrary.SwipyRefreshLayoutDirection;
import com.bwie.yuekao.R;
import com.bwie.yuekao.adapter.MyNewAdapter;
import com.bwie.yuekao.adapter.MyViewPagerAdapter;
import com.bwie.yuekao.bean.NewBean;
import com.bwie.yuekao.okhttp.GsonObjectCallback;
import com.bwie.yuekao.okhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/10/25.
 */

public class Fragment_New extends Fragment {

    private ViewPager image_pager;
    private RadioGroup radioGroup;
    private List<String> imgList = new ArrayList<>();
    private int a = 0;
    private int i;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            image_pager.setCurrentItem(what);
        }
    };
    private Handler handlerData = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    getData();
                    break;
            }
        }
    };
    private RecyclerView recyView;
    private SwipyRefreshLayout srl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        image_pager = view.findViewById(R.id.image_pager);
        radioGroup = view.findViewById(R.id.radioGroup);
        recyView = view.findViewById(R.id.recyclerView);
        srl = view.findViewById(R.id.srl);

        imgList.add("https://pic4.zhimg.com/v2-c48d2c752ec7b8b183055667b76596c7.jpg");
        imgList.add("https://pic3.zhimg.com/v2-8d3803d6014153f1aa9835b47ccd7db2.jpg");
        imgList.add("https://pic3.zhimg.com/v2-8569d560d951c65cc1c712b8976c8fba.jpg");
        imgList.add("https://pic2.zhimg.com/v2-e7582788c34b9d40b7b849ea3458d0dd.jpg");

        //关联适配器
        MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter(getActivity(),imgList);
        image_pager.setAdapter(pagerAdapter);
        
        //调用关联图片与button的方法
        getRadioGroup();
        //开启一个线程，实现无限轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(3000);
                        a++;
                        handler.sendEmptyMessage(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyView.setLayoutManager(linearLayoutManager);

        srl.setDirection(SwipyRefreshLayoutDirection.BOTH);
        srl.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                getData();
                handlerData.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "下拉刷新", Toast.LENGTH_SHORT).show();

                        srl.setRefreshing(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoad(int index) {

                getData();
                handlerData.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "上拉加载", Toast.LENGTH_SHORT).show();
                        srl.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        getData();
        return view;
    }
    public void getData(){

        //获取网络数据
        OkHttp3Utils.doGet("http://news-at.zhihu.com/api/4/news/latest", new GsonObjectCallback<NewBean>() {
            @Override
            public void onUi(NewBean newBean) {
                Log.i("LOG",newBean.toString());
                List<NewBean.StoriesBean> stories = newBean.getStories();
                MyNewAdapter newAdapter = new MyNewAdapter(getActivity(),stories);
                recyView.setAdapter(newAdapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
    //实现button和图片联动
    private void getRadioGroup(){
        image_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position%imgList.size()){
                    case 0:
                        radioGroup.check(R.id.rb1);
                        break;
                    case 1:
                        radioGroup.check(R.id.rb2);
                        break;
                    case 2:
                        radioGroup.check(R.id.rb3);
                        break;
                    case 3:
                        radioGroup.check(R.id.rb4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i%imgList.size()){
                    case R.id.rb1:
                        image_pager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        image_pager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        image_pager.setCurrentItem(2);
                        break;
                    case R.id.rb4:
                        image_pager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

}
