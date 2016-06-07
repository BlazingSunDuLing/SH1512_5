package com.example.administrator.sh1512_5.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.administrator.sh1512_5.Base.BaseActivity;
import com.example.administrator.sh1512_5.MyView.MySearch;
import com.example.administrator.sh1512_5.R;

public class SearchActivity extends BaseActivity {

    private ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_search);
        MySearch mySearch = new MySearch(this);
        backIV = (ImageView) findViewById(R.id.searchBackIV);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
