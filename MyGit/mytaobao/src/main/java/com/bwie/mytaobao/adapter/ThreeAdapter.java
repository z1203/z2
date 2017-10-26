package com.bwie.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.SecondAndThreeBean;

import java.util.List;

/**
 * Created by 13435 on 2017/10/17.
 */

public class ThreeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SecondAndThreeBean.DatasBean.ClassListBean> three;
    public ThreeAdapter(Context context, List<SecondAndThreeBean.DatasBean.ClassListBean> three) {
        this.context=context;
        this.three=three;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.three_level,parent,false);
        return new Mythree(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Mythree mt=new Mythree(holder.itemView);
        mt.tvthree.setText(three.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return three.size();
    }

    public class Mythree extends RecyclerView.ViewHolder{

        TextView tvthree;

        public Mythree(View itemView) {
            super(itemView);
            tvthree=itemView.findViewById(R.id.three_item_tv);
        }
    }
}
