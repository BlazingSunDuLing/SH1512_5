package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.Activity.DetailPages;
import com.example.administrator.sh1512_5.ModelBean.SurplusDataBean;
import com.example.administrator.sh1512_5.MyView.MylittleStar;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.RectFTrasformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANG-PC on 2016/5/14.
 */
public class GuidSurplusListAdapter extends BaseAdapter {
    private List<SurplusDataBean.DataBean.ItemsBean> mItemsBeans = new ArrayList<>();
    private Context mContext;
    private int num;

    public GuidSurplusListAdapter(List<SurplusDataBean.DataBean.ItemsBean> itemsBeans, Context context) {
        mItemsBeans = itemsBeans;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mItemsBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemsBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        num = position;
        MyViewHolder myViewHolder = null;
        if (convertView == null) {
            myViewHolder = new MyViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, parent, false);
            myViewHolder.mStar = (MylittleStar) convertView.findViewById(R.id.GuidItemTwoCardMylittleStar);
            myViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.GuidItemTwoCardIV);
            myViewHolder.mImageViewNew = (ImageView) convertView.findViewById(R.id.GuidItemTwoCardIvNEW);
            myViewHolder.mTextView = (TextView) convertView.findViewById(R.id.GuidItemTwoCardTitleTV);
            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.mStar.setStarText(mItemsBeans.get(position).getLikes_count());
        Picasso.with(mContext).load(mItemsBeans.get(position).getCover_image_url()).transform(new RectFTrasformation()).placeholder(R.mipmap.e).into(myViewHolder.mImageView);
        myViewHolder.mTextView.setText(mItemsBeans.get(position).getTitle());
        myViewHolder.mImageViewNew.setVisibility(View.GONE);
        myViewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i(num + "被点击了", "======");
                Intent intent = new Intent(mContext, DetailPages.class);
                intent.putExtra("webUrl", mItemsBeans.get(num).getContent_url());
                intent.putExtra("title", mItemsBeans.get(num).getTitle());
                intent.putExtra("imageUrl", mItemsBeans.get(num).getCover_image_url());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }


    class MyViewHolder {
        MylittleStar mStar = new MylittleStar(mContext);
        ImageView mImageView, mImageViewNew;
        TextView mTextView;
    }

    //让item失去点击
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
