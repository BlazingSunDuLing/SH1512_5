package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.sh1512_5.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by YANG-PC on 2016/5/6.
 */
public class GuidRecyclerFristItemRecyclerAdapter extends RecyclerView.Adapter<GuidRecyclerFristItemRecyclerAdapter.MyViewHolder> {

    private ArrayList<String> iMageUrls = new ArrayList<>();
    private Context mContext;
    private RecyclerView mRecyclerView;
    private int width;

    public GuidRecyclerFristItemRecyclerAdapter(ArrayList<String> iMageUrls, Context context, RecyclerView recyclerView) {
        this.iMageUrls = iMageUrls;
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        Log.d("===+++ImageUrls+++", "=====" + iMageUrls.size());
        width = mContext.getResources().getDisplayMetrics().widthPixels / 5 - 20;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.itemfrist_recycler_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Log.d("===+++ImageUrls+++", "=====" + iMageUrls.size());
        Picasso.with(mContext).load(iMageUrls.get(position)).tag(mRecyclerView).resize(width, width).centerCrop().into(holder.mImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataCallBack.getPosition(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("===+getItemCout+", "=====" + iMageUrls.size());
        return iMageUrls.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.GuidItemFristRecyclerItemImage);
        }
    }

    DataCallBack mDataCallBack;

    //回调接口
    public interface DataCallBack {
        void getPosition(int position);
    }
}
