package com.bwie.yuekao.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张丹阳 on 2017/10/25.
 */

public class MyViewPagerAdapter extends PagerAdapter{

    private Context context;
    private List<String> imageList;
    private final DisplayImageOptions options;

    public MyViewPagerAdapter(Context context, List<String> imageList) {
        this.context = context;
        this.imageList = imageList;
        options = new DisplayImageOptions.Builder().build();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(context);
        ImageLoader.getInstance().displayImage(imageList.get(position%imageList.size()),iv,options);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
