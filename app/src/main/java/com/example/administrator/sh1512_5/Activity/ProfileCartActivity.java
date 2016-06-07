package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.sh1512_5.R;

/**
 * Created by BlazingSun on 2016/5/12.
 */
public class ProfileCartActivity extends Activity {

    private ImageView cart_imageview;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_cart);

        initView();
        cart_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        cart_imageview = (ImageView) findViewById(R.id.cart_imageview);
        mLinearLayout = (LinearLayout) findViewById(R.id.cart_linear);
    }
}
