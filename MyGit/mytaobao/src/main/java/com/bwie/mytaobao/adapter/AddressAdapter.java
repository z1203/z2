package com.bwie.mytaobao.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.Address_ZhanBean;
import com.bwie.mytaobao.bean.LogOutBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 张丹阳 on 2017/10/22.
 */

public class AddressAdapter extends BaseAdapter{

    private Context context;
    private List<Address_ZhanBean.DatasBean.AddressListBean> list;

    public AddressAdapter(Context context, List<Address_ZhanBean.DatasBean.AddressListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view = View.inflate(context, R.layout.item_address,null);
            holder.zhan_name = view.findViewById(R.id.zhan_name);
            holder.zhan_phone = view.findViewById(R.id.zhan_phone);
            holder.zhan_address = view.findViewById(R.id.zhan_address);
            holder.zhan_del = view.findViewById(R.id.zhan_del);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        final Address_ZhanBean.DatasBean.AddressListBean bean = list.get(i);
        holder.zhan_name.setText(bean.getTrue_name());
        holder.zhan_phone.setText(bean.getMob_phone());
        holder.zhan_address.setText(bean.getArea_info());
        holder.zhan_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = Url_Utils.getShared(context);
                String key = shared.getString("key", "");
                Map<String,String> delMap = new HashMap<String, String>();
                delMap.put("key",key);
                delMap.put("address_id",bean.getAddress_id());
                OkHttp3Utils.doPost(Url_Utils.ADD_DEL, delMap, new GsonObjectCallback<LogOutBean>() {
                    @Override
                    public void onUi(LogOutBean logOutBean) {
                        if(logOutBean.getCode()==200){
                            list.remove(i);
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

        return view;
    }

    class ViewHolder{
        TextView zhan_name;
        TextView zhan_phone;
        TextView zhan_address;
        TextView zhan_del;
    }
}
