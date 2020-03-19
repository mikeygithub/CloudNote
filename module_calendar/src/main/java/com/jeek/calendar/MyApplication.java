package com.jeek.calendar;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

public class MyApplication extends Application {


    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        Log.i("Application","MyApplication");

    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
