package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.R;

/**
 * Created by BlazingSun on 2016/5/15.
 */
public class ProfileMessageActivity  extends Activity{

    private ImageView message_imageview;
    private EditText message_cellphone,message_passwprd;
    private Button message_btn_loig;
    private TextView message_tv;
    private  ImageView message_sina,message_weixin,message_qq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_message);
        initview();
        message_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initview() {
        message_imageview = (ImageView) findViewById(R.id.message_imageview);
        message_cellphone = (EditText) findViewById(R.id.message_cellphone);
        message_passwprd = (EditText) findViewById(R.id.message_passwprd);
        message_btn_loig = (Button) findViewById(R.id.message_btn_loig);
        message_tv = (TextView) findViewById(R.id.message_tv);
        message_sina = (ImageView) findViewById(R.id.message_sina);
        message_weixin = (ImageView) findViewById(R.id.message_weixin);
        message_qq = (ImageView) findViewById(R.id.message_qq);
    }
}
