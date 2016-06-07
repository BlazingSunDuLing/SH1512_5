package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.administrator.sh1512_5.R;

/**
 * Created by BlazingSun on 2016/5/12.
 */
public class ProfileServiceActivity extends Activity {
    private ImageView servicer_imageviewl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_service);
        initView();
        initListener();
    }

    private void initListener() {
        servicer_imageviewl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        servicer_imageviewl = (ImageView) findViewById(R.id.servicer_imageview);
    }
}
