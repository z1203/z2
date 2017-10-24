package com.bwie.mytaobao.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.adapter.ExListViewAdapter;
import com.bwie.mytaobao.bean.GouWuCheBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/9/28.
 */

public class GouWuFragment extends Fragment implements View.OnClickListener {

    private CheckBox box_all;
    private TextView total;
    private Button jieSuan;
    private View view;
    private ExpandableListView exList;
    private List<GouWuCheBean.DatasBean.CartListBean> list;
    private ExListViewAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //找控件
        box_all = view.findViewById(R.id.box_All);
        total = view.findViewById(R.id.total);
        jieSuan = view.findViewById(R.id.jieSuan);


        box_all.setOnClickListener(this);
        jieSuan.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_fragment, container, false);
        exList = view.findViewById(R.id.celv);

        SharedPreferences shared = Url_Utils.getShared(getActivity());
        final String key = shared.getString("key", "");
        Map<String,String> map = new HashMap<String,String>();
        map.put("key",key);

        OkHttp3Utils.doPost(Url_Utils.GOUWUCHE, map, new GsonObjectCallback<GouWuCheBean>() {
            @Override
            public void onUi(GouWuCheBean gouWuCheBean) {
                if(gouWuCheBean.getCode()==200){
                    list = gouWuCheBean.getDatas().getCart_list();

                    adapter = new ExListViewAdapter(getActivity(), list,box_all,total);
                    exList.setAdapter(adapter);

                    int count = exList.getCount();
                    for (int i=0;i<count;i++){
                        exList.expandGroup(i);
                    }
                }
            }
            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.box_All:
                for(int i=0;i<list.size();i++){
                    list.get(i).setAllCheck(box_all.isChecked());
                    for(int j=0;j<list.get(i).getGoods().size();j++){
                        list.get(i).getGoods().get(j).setItemCheck(box_all.isChecked());
                    }
                }
                adapter.sum();
                adapter.notifyDataSetChanged();
                break;
            case R.id.jieSuan:

                break;
        }
    }
}
