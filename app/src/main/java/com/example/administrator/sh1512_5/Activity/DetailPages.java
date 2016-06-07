package com.example.administrator.sh1512_5.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.Base.BaseActivity;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.squareup.picasso.Picasso;

public class DetailPages extends BaseActivity {

    private ImageView mImageView, backImage;
    private TextView mTextView;
    private WebView mWebView;
    private String webUrl;
    private String title;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_detail_pages);
        mImageView = (ImageView) findViewById(R.id.DetailPagesIV);
        mTextView = (TextView) findViewById(R.id.DetailPagesTV);
        mWebView = (WebView) findViewById(R.id.DetailPagesWeb);
        backImage = (ImageView) findViewById(R.id.DetailPagesBackIV);
    }

    @Override
    public void initData() {
        getIntentData();
        Picasso.with(this).load(imageUrl).placeholder(R.mipmap.bg_loading).error(R.mipmap.bg_error_loading).into(mImageView);
        mTextView.setText(title);
        mWebView.loadUrl(webUrl);
        mWebView.setWebViewClient(new WebViewClient());
    }

    private void getIntentData() {
        Intent intent = getIntent();
        webUrl = intent.getStringExtra("webUrl");
        title = intent.getStringExtra("title");
        imageUrl = intent.getStringExtra("imageUrl");
        LogUtils.i("imageUrl  " + imageUrl.toString(), "+++");
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(" 返回被点击了=", "=========");
                finish();

            }
        });
    }

}
