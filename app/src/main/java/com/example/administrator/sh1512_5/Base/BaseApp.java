package com.example.administrator.sh1512_5.Base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 16-5-3.
 */
public class BaseApp extends Application {
    public static Context mContext;

    public BaseApp() {
        mContext=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext() {
        return mContext;
    }
}
