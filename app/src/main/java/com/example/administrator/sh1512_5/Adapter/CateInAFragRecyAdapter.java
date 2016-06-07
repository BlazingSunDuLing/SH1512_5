package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.sh1512_5.ModelBean.CateARecyBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.ChangeToDp;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.RectFTrasformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-5-9.
 */
public class CateInAFragRecyAdapter extends RecyclerView.Adapter<CateInAFragRecyAdapter.MyViewHolder>{
    private List<CateARecyBean> mRecyList=new ArrayList<>();
    private Context mContext;
    private CateARecyBean mARecybean;
    public CateInAFragRecyAdapter(List<CateARecyBean> mRecyList,Context mContext) {
        this.mRecyList=mRecyList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_a_recy,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //300X147;
        int width= (int) ChangeToDp.getDp(mContext,150);
        int height= (int) ChangeToDp.getDp(mContext,73);
        //holder.mCateInFragRecyItemImg.setLayoutParams(new ViewGroup.LayoutParams(width,height));
        Picasso.with(mContext)
                .load(mRecyList.get(position).getData().getBanner_image_url())
                .placeholder(R.mipmap.bg_loading)
                .error(R.mipmap.bg_error_loading)
                .transform(new RectFTrasformation())
                .into(holder.mCateInFragRecyItemImg);
    }

    @Override
    public int getItemCount() {
        return mRecyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView mCateInFragRecyItemImg;
        public MyViewHolder(View itemView) {
            super(itemView);
            mCateInFragRecyItemImg=(ImageView)itemView.findViewById(R.id.CateInFragRecyItemImg);
        }
    }
}
