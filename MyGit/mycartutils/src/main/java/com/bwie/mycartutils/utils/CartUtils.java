package com.bwie.mycartutils.utils;

import android.content.Context;
import android.graphics.Color;
import android.transition.Slide;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mycartutils.R;
import com.bwie.mycartutils.bean.ChildBean;
import com.bwie.mycartutils.bean.GroupBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 1. 单例模式封装的购物车
 * 2. @author chensi
 * 3. @date 2017/10/24 19:07
 */

public class CartUtils {

    private static ElvAdapter adapter;

    /**
     * 以下均为必传参数
     * @param context 上下文
     * @param flist   父类集合
     * @param slist   子类集合
     * @param elv      ExpandableListView
     * @param all      全选checkbox
     * @param all_count  合计数量textview
     * @param all_money  合计价钱textview
     */
    public static void setCartData(Context context, final List<GroupBean> flist, final List<List<ChildBean>> slist, ExpandableListView elv, final CheckBox all, final TextView all_count, final TextView all_money){
        all_count.setBackgroundResource(R.color.count);
        all_money.setBackgroundResource(R.color.money);
        all_count.setTextColor(Color.WHITE);
        all_money.setTextColor(Color.WHITE);
        elv.setGroupIndicator(null);
        adapter = new ElvAdapter(flist,slist,context,all,all_count,all_money);
        elv.setAdapter(adapter);
        for (int i=0;i<flist.size();i++){
            elv.expandGroup(i);
        }
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<flist.size();i++){
                    flist.get(i).setSelected(all.isChecked());
                    for (int j=0;j<slist.get(i).size();j++){
                        slist.get(i).get(j).setSelected(all.isChecked());
                    }
                }
                adapter.notifyDataSetChanged();
                checkNum(flist,slist,all_count,all_money);
            }
        });
    }

    public static void checkNum(List<GroupBean> flist,List<List<ChildBean>> slist,TextView count,TextView money){
        int all_count=0;
        int all_money = 0;
        for (int i=0;i<flist.size();i++){
            for (int j=0;j<slist.get(i).size();j++) {
                if(slist.get(i).get(j).getSelected()){
                    all_count += slist.get(i).get(j).getNum();
                    int v = (int) (Double.parseDouble(slist.get(i).get(j).getPrice())*slist.get(i).get(j).getNum());
                    all_money += v;
                }
            }
        }
        count.setText("总计:"+all_count);
        money.setText("计算:"+all_money);


    }

    static class ElvAdapter extends BaseExpandableListAdapter{
        List<GroupBean> flist;
        List<List<ChildBean>> slist;
        Context context;
        CheckBox all;
        TextView all_count;
        TextView all_money;

        public ElvAdapter(List<GroupBean> flist, List<List<ChildBean>> slist,Context context,CheckBox all,TextView all_count,TextView all_money) {
            this.flist = flist;
            this.slist = slist;
            this.context=context;
            this.all=all;
            this.all_count=all_count;
            this.all_money=all_money;
        }

        @Override
        public int getGroupCount() {
            return flist.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return slist.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return flist.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return slist.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view=null;
            GroupHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(context, R.layout.group,null);
                view=convertView;
                holder=new GroupHolder();
                holder.group=(CheckBox)view.findViewById(R.id.group);
                holder.group_name=(TextView) view.findViewById(R.id.group_name);
                convertView.setTag(holder);
            }else{
                view=convertView;
                holder= (GroupHolder) convertView.getTag();
            }
            holder.group_name.setText(flist.get(groupPosition).getGroup_name());
            holder.group.setChecked(flist.get(groupPosition).getSelected());
            holder.group.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flist.get(groupPosition).getSelected()){
                        flist.get(groupPosition).setSelected(false);
                        all.setChecked(false);
                        for (int i=0;i<slist.get(groupPosition).size();i++){
                            slist.get(groupPosition).get(i).setSelected(false);
                        }
                    }else{
                        int count=0;
                        flist.get(groupPosition).setSelected(true);
                        for (int i=0;i<slist.get(groupPosition).size();i++){
                            slist.get(groupPosition).get(i).setSelected(true);
                        }
                        for (int i=0;i<flist.size();i++){
                            if(flist.get(i).getSelected()){
                                count++;
                            }
                        }
                        if(count==flist.size()){
                            all.setChecked(true);
                        }
                    }
                    checkNum(flist,slist,all_count,all_money);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
        class GroupHolder{
            CheckBox group;
            TextView group_name;
        }
        class ChildHolder{
            CheckBox child;
            TextView title;
            ImageView image;
            TextView price;
            Jiajianqi amount_view;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view=null;
            ChildHolder holder=null;
            if(convertView==null){
                convertView=View.inflate(context,R.layout.child,null);
                view=convertView;
                holder=new ChildHolder();
                holder.child=(CheckBox)view.findViewById(R.id.child);
                holder.title=(TextView) view.findViewById(R.id.title);
                holder.price=(TextView)view.findViewById(R.id.price);
                holder.image=(ImageView)view.findViewById(R.id.image);
                holder.amount_view=(Jiajianqi)view.findViewById(R.id.amount_view);
                convertView.setTag(holder);
            }else{
                view=convertView;
                holder= (ChildHolder) convertView.getTag();
            }
            Picasso.with(context).load(slist.get(groupPosition).get(childPosition).getPath()).into(holder.image);
            holder.title.setText(slist.get(groupPosition).get(childPosition).getTitle());
            holder.price.setText("￥"+slist.get(groupPosition).get(childPosition).getPrice());
            holder.child.setChecked(slist.get(groupPosition).get(childPosition).getSelected());
            final ChildHolder finalHolder = holder;
            holder.amount_view.setGoods_storage(100);
            holder.amount_view.setAmount(slist.get(groupPosition).get(childPosition).getNum());
            holder.amount_view.setOnAmountChangeListener(new Jiajianqi.OnAmountChangeListener() {
                @Override
                public void onAmountChange(View view, int amount) {
                    slist.get(groupPosition).get(childPosition).setNum(amount);
                    checkNum(flist,slist,all_count,all_money);
                }
            });
            holder.child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(slist.get(groupPosition).get(childPosition).getSelected()){
                       slist.get(groupPosition).get(childPosition).setSelected(false);
                        flist.get(groupPosition).setSelected(false);
                        all.setChecked(false);
                    }else{
                        int fcount=0;
                        int scount=0;
                        slist.get(groupPosition).get(childPosition).setSelected(true);
                        for (int i=0;i<slist.get(groupPosition).size();i++){
                            if(slist.get(groupPosition).get(i).getSelected()){
                                scount++;
                            }
                        }
                        if(scount==slist.get(groupPosition).size()){
                            flist.get(groupPosition).setSelected(true);
                        }
                        for (int i=0;i<flist.size();i++){
                            if(flist.get(i).getSelected()){
                                fcount++;
                            }
                        }
                        if(fcount==flist.size()){
                            all.setChecked(true);
                        }

                    }
                    checkNum(flist,slist,all_count,all_money);
                    notifyDataSetChanged();
                }
            });
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
