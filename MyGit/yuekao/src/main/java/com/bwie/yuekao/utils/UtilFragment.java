package com.bwie.yuekao.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 张丹阳 on 2017/10/25.
 */

public class UtilFragment extends FragmentPagerAdapter{

    private List<String> tabList;
    private List<Fragment> fragList;

    public UtilFragment(FragmentManager fm,List<String> tabList,List<Fragment> fragList) {
        super(fm);
        this.tabList = tabList;
        this.fragList = fragList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
