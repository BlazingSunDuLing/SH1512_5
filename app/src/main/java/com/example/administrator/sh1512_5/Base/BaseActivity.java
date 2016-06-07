package com.example.administrator.sh1512_5.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Administrator on 16-5-3.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppCompatActivity 去除标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
        initAdapter();
        initListener();
    }
    public abstract void initView();
    public abstract void initData();
    public abstract void initAdapter();
    public abstract void initListener();
}
