package com.bwie.mytaobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.adapter.SecondAdapter;
import com.bwie.mytaobao.adapter.StairAdapter;
import com.bwie.mytaobao.adapter.ThreeAdapter;
import com.bwie.mytaobao.bean.SecondAndThreeBean;
import com.bwie.mytaobao.bean.StairBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/9/28.
 */

public class WeiTaoFragment extends Fragment{

    private RecyclerView rv_stair;
    private RecyclerView rv_second;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weitao_fragment, container, false);

        //初始化组件
        rv_stair = view.findViewById(R.id.rv_stair);
        rv_second = view.findViewById(R.id.rv_second);


        rv_stair.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_second.setLayoutManager(new LinearLayoutManager(getActivity()));


        upLoad();
        return view;
    }

    private void upLoad() {

        OkHttp3Utils.doGet(Url_Utils.STAIR_CLASSIFY, new GsonObjectCallback<StairBean>() {
            @Override
            public void onUi(StairBean stairBean) {
                final List<StairBean.DatasBean.ClassListBean> stairList = stairBean.getDatas().getClass_list();
                final StairAdapter stairAdapter = new StairAdapter(getActivity(),stairList);
                rv_stair.setAdapter(stairAdapter);
                stairAdapter.setOnItemClickListener(new StairAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        int i = Integer.parseInt(stairList.get(position).getGc_id());
                        OkHttp3Utils.doGet(Url_Utils.DICHOTOMIES + i, new GsonObjectCallback<SecondAndThreeBean>() {
                            @Override
                            public void onUi(SecondAndThreeBean secondAndThreeBean) {
                                List<SecondAndThreeBean.DatasBean.ClassListBean> secondList = secondAndThreeBean.getDatas().getClass_list();
                                SecondAdapter secondAdapter = new SecondAdapter(getActivity(),secondList);
                                rv_second.setAdapter(secondAdapter);

                            }
                            @Override
                            public void onFailed(Call call, IOException e) {

                            }
                        });
                    }
                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

}
