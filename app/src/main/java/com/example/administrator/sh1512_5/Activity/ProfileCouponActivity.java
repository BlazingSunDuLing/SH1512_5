package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.sh1512_5.R;

/**
 * Created by BlazingSun on 2016/5/12.
 */
public class ProfileCouponActivity  extends Activity {
    private ImageView coupon_imageview;
    private Button coupon_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_coupon);

        initView();
        initListener();
    }

    private void initListener() {
        coupon_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        coupon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        coupon_imageview = (ImageView) findViewById(R.id.coupon_imageview);
        coupon_btn = (Button) findViewById(R.id.coupon_btn);
    }


}
