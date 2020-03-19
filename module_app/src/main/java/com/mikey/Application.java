package com.mikey;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/7.
 */

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.yanzhenjie.nohttp.NoHttp;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

public class Application extends LitePalApplication {

    private static Application application;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application","Application");
        NoHttp.initialize(this);
        application =this;

        BGASwipeBackManager.getInstance().init(this);

        LitePal.initialize(this);

        //初始化友盟5e72e8f7895cca5f2d0000cc
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5e72e8f7895cca5f2d0000cc");
        //友盟设置场景模式
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        //使用友盟的集成测试
        UMConfigure.setLogEnabled(true);
        MobclickAgent.setDebugMode(true);
        //禁止默认页面统计形式，采用手动统计模式
        MobclickAgent.openActivityDurationTrack(false);
        //android 7.0调用系统相机报错
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
    }

    public static Application getApplication() {
        return application;
    }
}
