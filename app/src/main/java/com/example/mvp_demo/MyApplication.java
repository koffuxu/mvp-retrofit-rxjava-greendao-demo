package com.example.mvp_demo;

import android.app.Application;
import android.content.Context;

/**
 * 负责启动时的全局初始化工�?
 * Created by hasee on 2016/8/22.
 */
public class MyApplication extends Application {

    /**网络的基本地址*/
    public static final String BASIC_URL;
    public static Context context;
    static {
        BASIC_URL = "http://www.baidu.com/";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //实例化数据库�?
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
