package com.bwie.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.SecondAndThreeBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 13435 on 2017/10/17.
 */

public class SecondAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements View.OnClickListener {
    Context context;
    List<SecondAndThreeBean.DatasBean.ClassListBean> secondList;
    //第一实现
    private OnItemClickListener mOnItemClickListener = null;
    private int i;

    public SecondAdapter(Context context, List<SecondAndThreeBean.DatasBean.ClassListBean> secondList) {
        this.context=context;
        this.secondList=secondList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.second_level,parent,false);
        //2view点击事件
        view.setOnClickListener(this);
        return new Mytwo(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mytwo md=new Mytwo(holder.itemView);
        //保存到tag
        md.itemView.setTag(position);
        md.tvtwo.setText(secondList.get(position).getGc_name());
        i = Integer.parseInt(secondList.get(position).getGc_id());
    }

    @Override
    public int getItemCount() {
        return secondList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view,(int)view.getTag());
        }
    }

    //可调用点击事件
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);

    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener=listener;
    }

    public class Mytwo extends RecyclerView.ViewHolder{

        TextView tvtwo;
        RecyclerView rv_three;

        public Mytwo(View itemView) {
            super(itemView);
            tvtwo=itemView.findViewById(R.id.two_item_tv);
            rv_three = itemView.findViewById(R.id.rv_three);

            rv_three.setLayoutManager(new GridLayoutManager(context,3));

            OkHttp3Utils.doGet(Url_Utils.DICHOTOMIES + i, new GsonObjectCallback<SecondAndThreeBean>() {
                @Override
                public void onUi(SecondAndThreeBean secondAndThreeBean) {
                    List<SecondAndThreeBean.DatasBean.ClassListBean> threeList = secondAndThreeBean.getDatas().getClass_list();
                    ThreeAdapter threeAdapter = new ThreeAdapter(context,threeList);
                    rv_three.setAdapter(threeAdapter);
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        }
    }
}
