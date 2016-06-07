package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.sh1512_5.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANG-PC on 2016/5/10.
 */
public class GuidDropDownGridViewAdapter extends BaseAdapter {
    private List<String> mStrings = new ArrayList<>();
    private Context mContext;

    public GuidDropDownGridViewAdapter(List<String> strings, Context context) {
        mStrings = strings;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_guid_drop_down_tv, parent, false);
            myViewHolder.mTextView = (TextView) convertView.findViewById(R.id.GuidDropDownItemTV);
            myViewHolder.GuidDropDownItemBottomLine = (TextView) convertView.findViewById(R.id.GuidDropDownItemBottomLine);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.mTextView.setText(mStrings.get(position));
        if (position == 0) {
            myViewHolder.GuidDropDownItemBottomLine.setVisibility(View.VISIBLE);
            Log.d("GuidDropDownItemBott"+position,"+");
        }
        return convertView;
    }

    class MyViewHolder {
        TextView mTextView, GuidDropDownItemBottomLine;
    }
}
