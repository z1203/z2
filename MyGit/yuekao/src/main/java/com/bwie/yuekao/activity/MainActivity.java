package com.bwie.yuekao.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.yuekao.R;
import com.bwie.yuekao.fragment.Fragment_New;
import com.bwie.yuekao.fragment.Fragment_P;
import com.bwie.yuekao.fragment.Fragment_Pt;
import com.bwie.yuekao.fragment.Fragment_Theme;
import com.bwie.yuekao.utils.UtilFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> tabList = new ArrayList<String>();
    private List<Fragment> fragList = new ArrayList<Fragment>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Fragment_New fragment_new;
    private Fragment_P fragment_p;
    private Fragment_Theme fragment_theme;
    private Fragment_Pt fragment_pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //实例化Fragment
        fragment_new = new Fragment_New();
        fragment_p = new Fragment_P();
        fragment_theme = new Fragment_Theme();
        fragment_pt = new Fragment_Pt();

        //调用添加tab标签的方法
        initData();
        initView();

    }

    //在tabList中添加数据
    private void initData() {
        //往tabList中添加数据
        tabList.add("最新日报");
        tabList.add("专栏");
        tabList.add("热门");
        tabList.add("主题日报");

    }

    private void initView(){
        //设置标签
        //tabLayout.setTabMode(tabLayout.MODE_SCROLLABLE);
        for (int i=0;i<tabList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(tabList.get(i)));
        }
        //将实例化的fragment放入集合
        fragList.add(fragment_new);
        fragList.add(fragment_p);
        fragList.add(fragment_pt);
        fragList.add(fragment_theme);

        //关联fragmentAdapter
        UtilFragment utilFragment = new UtilFragment(getSupportFragmentManager(),tabList,fragList);
        viewPager.setAdapter(utilFragment);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(4);

    }

}
