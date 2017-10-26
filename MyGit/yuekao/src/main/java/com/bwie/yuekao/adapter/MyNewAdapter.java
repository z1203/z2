package com.bwie.yuekao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yuekao.R;
import com.bwie.yuekao.bean.NewBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 张丹阳 on 2017/10/25.
 */

public class MyNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<NewBean.StoriesBean> list;
    private DisplayImageOptions options;

    public MyNewAdapter(Context context, List<NewBean.StoriesBean> list) {
        this.context = context;
        this.list = list;
        options = new DisplayImageOptions.Builder().build();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_new, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=new ViewHolder(holder.itemView);

        viewHolder.title_new.setText(list.get(position).getTitle());
        ImageLoader.getInstance().displayImage(list.get(position).getImages().get(0),viewHolder.images_new,options);

        viewHolder.itemView.setTag(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView images_new;
        TextView title_new;

        public ViewHolder(View itemView) {
            super(itemView);
            images_new = itemView.findViewById(R.id.images_new);
            title_new = itemView.findViewById(R.id.title_new);
        }
    }
}
