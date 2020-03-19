package com.mikey.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mikey.AppActivity.ChangePersonActivity;
import com.mikey.AppActivity.LoginActivity;
import com.example.xz.weiji.R;
import com.umeng.analytics.MobclickAgent;

import cn.bmob.v3.BmobUser;


public class MyFragment extends Fragment implements View.OnClickListener {
    private Context context;
    private View view;
    private RelativeLayout rl_changeperson;
    private RelativeLayout rl_settings;
    private RelativeLayout rl_aboutus;
    private RelativeLayout rl_logout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wode, container, false);
        initView(view);
        return view;
    }


    private void initView(View view) {
        rl_changeperson = (RelativeLayout) view.findViewById(R.id.rl_changeperson);
        rl_changeperson.setOnClickListener(this);
        rl_settings = (RelativeLayout) view.findViewById(R.id.rl_settings);
        rl_settings.setOnClickListener(this);
        rl_aboutus = (RelativeLayout) view.findViewById(R.id.rl_aboutus);
        rl_aboutus.setOnClickListener(this);
        rl_logout = (RelativeLayout) view.findViewById(R.id.rl_logout);
        rl_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_changeperson:
                startActivity(new Intent(context, ChangePersonActivity.class));
                break;
            case R.id.rl_aboutus:
                Toast.makeText(context,"麦奇科技责任有限公司",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_logout:
                isconfirm();
                break;
            case R.id.rl_settings:
                Toast.makeText(context,"还没写完呢",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void isconfirm() {
        new AlertDialog.Builder(context).setMessage("确认退出当前账号？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BmobUser.logOut();
                        startActivity(new Intent(context, LoginActivity.class));
                        getActivity().finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                }).show();
    }


    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MyFragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MyFragment");
    }
}
