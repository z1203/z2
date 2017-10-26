package com.bwie.mytaobao.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.bean.Add_Address;
import com.bwie.mytaobao.utils.Url_Utils;
import com.bwie.mytaobao.utils.oKhttp.GsonObjectCallback;
import com.bwie.mytaobao.utils.oKhttp.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText add_name;
    private EditText add_phone;
    private EditText add_dress;
    private EditText add_miao;
    private Button add_yes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_name = (EditText) findViewById(R.id.add_name);
        add_phone = (EditText) findViewById(R.id.add_phone);
        add_dress = (EditText) findViewById(R.id.add_dress);
        add_miao = (EditText) findViewById(R.id.add_miao);
        add_yes = (Button) findViewById(R.id.add_yes);

        add_yes.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        SharedPreferences shared = Url_Utils.getShared(AddActivity.this);
        String key = shared.getString("key", "");
        Map<String,String> addMap = new HashMap<String, String>();
        addMap.put("key",key);
        addMap.put("true_name",add_name.getText().toString());
        addMap.put("mob_phone",add_phone.getText().toString());
        addMap.put("city_id","1");
        addMap.put("area_id","16");
        addMap.put("address",add_dress.getText().toString());
        addMap.put("area_info",add_miao.getText().toString());
        OkHttp3Utils.doPost(Url_Utils.ADDRESS, addMap, new GsonObjectCallback<Add_Address>() {
            @Override
            public void onUi(Add_Address add_address) {
                if(add_address.getCode()==200){
                    Intent intent=new Intent(AddActivity.this,AddressActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    AddActivity.this.finish();
                    Toast.makeText(AddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
