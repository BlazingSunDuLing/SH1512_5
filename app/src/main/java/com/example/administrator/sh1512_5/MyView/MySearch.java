package com.example.administrator.sh1512_5.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.administrator.sh1512_5.R;


/**
 * Created by YANG-PC on 2016/5/15.
 */
public class MySearch extends LinearLayout {

    private EditText searchEdit;



    public MySearch(Context context) {
        super(context);
    }

    public MySearch(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.mysearch,this,true);
        searchEdit = (EditText) view.findViewById(R.id.mySearchEdit);

    }







}
