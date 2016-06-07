package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.example.administrator.sh1512_5.Base.BaseApp;
import com.example.administrator.sh1512_5.MainActivity;

import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.SharedPreUtils;

/**
 * 1.展示Login和启动界面
 * 2.版本比对，版本更新
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Context context = BaseApp.getContext();
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    Log.i("mtg",">>>>>>>>run");
                    if (SharedPreUtils.getBooleans(SplashActivity.this, "myKey", true)) {
                        startActivity(new Intent(SplashActivity.this, GuidPagerActivity.class));
                        SharedPreUtils.saveBoolean(SplashActivity.this, "myKey", false);
                        finish();
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                }
            };
            Handler handler = new Handler();
            handler.postDelayed(r, 1000);
}}
