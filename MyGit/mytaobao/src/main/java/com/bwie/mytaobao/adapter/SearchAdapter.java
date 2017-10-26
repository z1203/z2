package com.bwie.mytaobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.Goods_Search;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张丹阳 on 2017/10/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements View.OnClickListener {

    Context context;
    List<Goods_Search.DatasBean.GoodsListBean> list;
    private OnItemCilckListener mOnItemClickListener=null;

    public SearchAdapter(Context context, List<Goods_Search.DatasBean.GoodsListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_search,null);
        ViewHolder holder=new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewHolder viewHolder=new ViewHolder(holder.itemView);

        viewHolder.goods_name.setText(list.get(position).getGoods_name());
        viewHolder.goods_price.setText(list.get(position).getGoods_price());
        Picasso.with(viewHolder.goods_img.getContext()).load(list.get(position).getGoods_image_url()).placeholder(R.mipmap.ic_launcher).into(holder.goods_img);

        //保存到tag
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    //点击事件
    public interface OnItemCilckListener{
        void onItemClick(View view,int position);
    }
    //最后暴露给外面的调用者，定义一个设置Listener的方法
    public void setOnItemClickListener(OnItemCilckListener listener) {
        this.mOnItemClickListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView goods_img;
        TextView goods_name;
        TextView goods_price;

        public ViewHolder(View itemView) {
            super(itemView);
            goods_img = itemView.findViewById(R.id.goods_img);
            goods_name = itemView.findViewById(R.id.goods_name);
            goods_price = itemView.findViewById(R.id.goods_price);
        }
    }
}
