package com.example.xz.weiji.AppActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xz.weiji.DataTable.User;
import com.example.xz.weiji.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private MaterialEditText lg_username;
    private MaterialEditText lg_password;
    private Button bt_login;
    private User user;
    private TextView tv_lg_rg;
    private BmobUser bmobUser;
    private ProgressDialog progressDialog;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    lg_username.setText((String)msg.obj);
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "df965ca1f844643790948edd1c2a34eb");
        bmobUser = BmobUser.getCurrentUser();
        if (bmobUser != null) {
            directLogin();
        }
        setContentView(R.layout.activity_login);
        initView();
        //友盟的相关接口
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.setSecret(this,"5e72e8f7895cca5f2d0000cc");


    }

    private void directLogin() {

        Intent t = new Intent(LoginActivity.this, ReFirestpageActivity.class);
        t.putExtra("name", bmobUser.getUsername());
        startActivity(t);
        finish();

    }


    private void loginUser() {
        user = new User();
        user.setUsername(lg_username.getText().toString());
        user.setPassword(lg_password.getText().toString());
        user.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser user, BmobException e) {
                if (e == null) {
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent t = new Intent(LoginActivity.this, ReFirestpageActivity.class);
                    startActivity(t);
                    //友盟账号登陆统计
                    MobclickAgent.onProfileSignIn(user.getObjectId());
                    finish();
                    progressDialog.cancel();
                } else {
                    Toast.makeText(LoginActivity.this, "账号不存在或密码错误", Toast.LENGTH_SHORT).show();
                    progressDialog.cancel();
                }

            }
        });

    }

    void initView() {

        lg_username = (MaterialEditText) findViewById(R.id.et_lg_username);
        lg_password = (MaterialEditText) findViewById(R.id.et_lg_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        tv_lg_rg = (TextView) findViewById(R.id.tv_lg_register);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在登陆");
        progressDialog.setCancelable(true);

        bt_login.setOnClickListener(this);
        tv_lg_rg.setOnClickListener(this);

        Intent i = getIntent();
        lg_username.setText(i.getStringExtra("username"));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击登录按钮
            case R.id.bt_login:
                progressDialog.show();
                MobclickAgent.onEvent(this, "login", "友盟");
                loginUser();
                break;
            //点击注册
            case R.id.tv_lg_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
        }
    }
}
