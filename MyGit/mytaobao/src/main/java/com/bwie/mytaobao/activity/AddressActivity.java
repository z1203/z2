package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.adapter.AddressAdapter;
import com.bwie.mytaobao.bean.Address_ZhanBean;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

public class AddressActivity extends AppCompatActivity implements View.OnClickListener {

    private Button address;
    private ListView lv_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        address = (Button) findViewById(R.id.address);
        lv_add = (ListView) findViewById(R.id.lv_add);

        address.setOnClickListener(this);

        SharedPreferences shared = Url_Utils.getShared(AddressActivity.this);
        String key = shared.getString("key", "");
        Map<String,String> zhanMap=new HashMap<>();
        zhanMap.put("key",key);

        OkHttp3Utils.doPost(Url_Utils.ZHANSHI, zhanMap, new GsonObjectCallback<Address_ZhanBean>() {
            @Override
            public void onUi(Address_ZhanBean address_zhanBean) {
                if(address_zhanBean.getCode()==200){
                    List<Address_ZhanBean.DatasBean.AddressListBean> address_list = address_zhanBean.getDatas().getAddress_list();
                    AddressAdapter addressAdapter=new AddressAdapter(AddressActivity.this,address_list);
                    lv_add.setAdapter(addressAdapter);
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(AddressActivity.this,AddActivity.class);
        startActivity(intent);
    }
}
