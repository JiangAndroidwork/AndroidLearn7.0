package com.laojiang.androidlearn70;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jiang on 2016/12/27.
 */

public class MyApplication extends Application {
    private Context context;
    private static MyApplication instance;
    /**调试模式  打包时改成false 减少资源占用*/
    public static boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        instance = this;
    }
    public static MyApplication getInstance(){
        return instance;
    }
}
