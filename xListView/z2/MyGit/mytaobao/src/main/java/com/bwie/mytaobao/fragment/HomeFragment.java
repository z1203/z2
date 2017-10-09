package com.bwie.mytaobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.mytaobao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张丹阳 on 2017/9/28.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView saoMiao;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取资源ID
        saoMiao = getActivity().findViewById(R.id.saoMiao);
        //设置点击事件
        saoMiao.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        return view;
    }

    //扫一扫的点击事件
    @Override
    public void onClick(View view) {
        
    }
}
