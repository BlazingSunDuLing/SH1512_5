package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.sh1512_5.R;

/**
 * Created by BlazingSun on 2016/5/15.
 */
public class ProfileOrderActivity  extends Activity {

    private ImageView order_imageview;
    private LinearLayout mLinearLayout;
    private TextView order_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_order);
        initView();
        initListener();
    }

    private void initListener() {
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileOrderActivity.this,ProCommon.class));
            }
        });
        order_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        order_imageview = (ImageView) findViewById(R.id.order_imageview);
        mLinearLayout = (LinearLayout) findViewById(R.id.order_linear);
        order_btn = (TextView) findViewById(R.id.order_btn);
    }
}

