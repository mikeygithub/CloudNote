package com.mikey.AppActivity;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;


public class BaseActivity extends AppCompatActivity {
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.toString());
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.toString());
        MobclickAgent.onPause(this);
    }
}
