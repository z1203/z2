package com.bwie.mytaobao.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.mytaobao.R;
import com.bwie.mytaobao.activity.AddressActivity;
import com.bwie.mytaobao.activity.SetActivity;
import com.bwie.mytaobao.utils.Url_Utils;

/**
 * Created by 张丹阳 on 2017/9/28.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private ImageView user_var;
    private TextView user_mess;
    private TextView set_logOut;
    private SharedPreferences shared;
    private TextView shouHuo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, container, false);

        user_var = view.findViewById(R.id.user_var);
        user_mess = view.findViewById(R.id.user_mess);
        set_logOut = view.findViewById(R.id.set_logOut);
        shouHuo = view.findViewById(R.id.shouHuoDiZhi);

        set_logOut.setOnClickListener(this);
        user_var.setOnClickListener(this);
        user_mess.setOnClickListener(this);
        shouHuo.setOnClickListener(this);

        shared = Url_Utils.getShared(getActivity());
        boolean flag = shared.getBoolean("flag", false);
        if(flag==true){
            String name = shared.getString("name", "");
            user_mess.setText(name);
        }

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_var:

                break;
            case R.id.user_mess:

                break;
            case R.id.set_logOut:
                Intent intent=new Intent(getActivity(), SetActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.shouHuoDiZhi:
                Intent inten=new Intent(getActivity(), AddressActivity.class);
                startActivity(inten);
                break;
        }
    }
}
