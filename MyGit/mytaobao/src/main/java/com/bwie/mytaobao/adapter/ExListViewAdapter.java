package com.bwie.mytaobao.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.GouWuCheBean;
import com.bwie.mytaobao.bean.LogOutBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

import static com.bwie.mytaobao.bean.GouWuCheBean.*;

/**
 * Created by 张丹阳 on 2017/10/20.
 */

public class ExListViewAdapter extends BaseExpandableListAdapter{

    private Context context;
    private CheckBox box_All;
    private TextView total;
    private List<GouWuCheBean.DatasBean.CartListBean> list;


    public ExListViewAdapter(Context context, List<GouWuCheBean.DatasBean.CartListBean> list,CheckBox box_All,TextView total) {
        this.context = context;
        this.list = list;
        this.box_All = box_All;
        this.total = total;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getGoods().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getGoods().get(childPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup viewGroup) {
        GViewHolder gholder;
        if(convertView==null){
            gholder = new GViewHolder();
            convertView = View.inflate(context, R.layout.group_item,null);
            gholder.dianPu = convertView.findViewById(R.id.dianPu);
            gholder.group_box = convertView.findViewById(R.id.group_box);
            convertView.setTag(gholder);
        }else{
            gholder = (GViewHolder) convertView.getTag();
        }

        gholder.group_box.setOnClickListener(new OnGroupClickListener(groupPosition,gholder.group_box));
        gholder.dianPu.setText(list.get(groupPosition).getStore_name());
        gholder.group_box.setChecked(list.get(groupPosition).isAllCheck());
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup viewGroup) {
        CViewHolder cholder;
        if(convertView==null){
            cholder = new CViewHolder();
            convertView = View.inflate(context,R.layout.child_item,null);
            cholder.er_iv = convertView.findViewById(R.id.er_iv);
            cholder.er_name = convertView.findViewById(R.id.er_name);
            cholder.er_price = convertView.findViewById(R.id.er_price);
            cholder.child_box = convertView.findViewById(R.id.child_box);
            cholder.er_num = convertView.findViewById(R.id.er_num);
            cholder.del = convertView.findViewById(R.id.del);
            convertView.setTag(cholder);
        }else {
            cholder = (CViewHolder) convertView.getTag();
        }
        cholder.child_box.setChecked(list.get(groupPosition).getGoods().get(childPosition).isItemCheck());
        cholder.child_box.setOnClickListener(new OnChildCheckListener(groupPosition,childPosition,cholder.child_box));
        cholder.er_name.setText(list.get(groupPosition).getGoods().get(childPosition).getGoods_name());
        cholder.er_price.setText("¥"+list.get(groupPosition).getGoods().get(childPosition).getGoods_price());
        cholder.er_num.setText("×"+list.get(groupPosition).getGoods().get(childPosition).getGoods_num());
        Picasso.with(context).load(list.get(groupPosition).getGoods().get(childPosition).getGoods_image_url()).into(cholder.er_iv);
        cholder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = Url_Utils.getShared(context);
                String key = shared.getString("key", "");
                Map<String,String> delMap= new HashMap<String, String>();
                delMap.put("key",key);
                delMap.put("cart_id",list.get(groupPosition).getGoods().get(childPosition).getCart_id());
                OkHttp3Utils.doPost(Url_Utils.DELECT, delMap, new GsonObjectCallback<LogOutBean>() {
                    @Override
                    public void onUi(LogOutBean logOutBean) {
                        if(logOutBean.getCode()==200){
                            list.get(groupPosition).getGoods().remove(childPosition);
                            notifyDataSetChanged();
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });
            }
        });

        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public void onGroupExpanded(int groupPosition) {

    }
    @Override
    public void onGroupCollapsed(int groupPosition) {

    }
    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }
    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    class GViewHolder{
        TextView dianPu;
        CheckBox group_box;
    }
    class CViewHolder{
        ImageView er_iv;
        TextView er_name;
        TextView er_price;
        TextView er_num;
        CheckBox child_box;
        Button del;
    }

    //一级监听
    private class OnGroupClickListener implements View.OnClickListener{
        int groupPosition;
        CheckBox group_box;

        public OnGroupClickListener(int groupPosition, CheckBox group_box) {
            this.group_box = group_box;
            this.groupPosition = groupPosition;
        }
        @Override
        public void onClick(View view) {
            if(list.get(groupPosition).isAllCheck()){
                list.get(groupPosition).setAllCheck(false);
                box_All.setChecked(false);
                for (int i = 0; i < list.get(groupPosition).getGoods().size(); i++) {
                    list.get(groupPosition).getGoods().get(i).setItemCheck(false);
                }
            }else {
                int num = 0;
                list.get(groupPosition).setAllCheck(true);
                for (int i = 0; i < list.get(groupPosition).getGoods().size(); i++) {
                    list.get(groupPosition).getGoods().get(i).setItemCheck(true);
                }
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).isAllCheck()) {
                        num++;
                    }
                }
                if (num == list.size()) {
                    box_All.setChecked(true);
                }
            }
            sum();
            notifyDataSetChanged();
        }
    }
    //二级监听
    private class OnChildCheckListener implements View.OnClickListener {
        int groupPosition;
        int childPosition;
        CheckBox child_box;

        public OnChildCheckListener(int groupPosition, int childPosition, CheckBox child_box) {
            this.child_box = child_box;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View view) {
            if(list.get(groupPosition).getGoods().get(childPosition).isItemCheck()){
                list.get(groupPosition).getGoods().get(childPosition).setItemCheck(false);
                box_All.setChecked(false);
                list.get(groupPosition).setAllCheck(false);
            }else {
                int num = 0;
                int num1 = 0;
                list.get(groupPosition).getGoods().get(childPosition).setItemCheck(true);
                for (int i = 0; i < list.get(groupPosition).getGoods().size(); i++) {
                    if (list.get(groupPosition).getGoods().get(i).isItemCheck()) {
                        num++;
                    }
                }
                if (list.get(groupPosition).getGoods().size() == num) {
                    list.get(groupPosition).setAllCheck(true);
                }
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).isAllCheck()) {
                        num1++;
                    }
                }
                if (num1 == list.size()) {
                    box_All.setChecked(true);
                }
            }
            sum();
            notifyDataSetChanged();
        }
    }

    public void sum(){
        int num=0;
        double price = 0;

        for (GouWuCheBean.DatasBean.CartListBean bean:list) {
            for (GouWuCheBean.DatasBean.CartListBean.GoodsBean goodBean:bean.getGoods()) {
                String goods_price = goodBean.getGoods_price();
                if(goodBean.isItemCheck()){
                    num++;
                    price += Double.parseDouble(goods_price);
                }
            }
        }
        total.setText("¥："+price+"(共"+num+"件)");
    }

}
