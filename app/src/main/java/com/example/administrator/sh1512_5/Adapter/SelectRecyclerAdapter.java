package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.ModelBean.SelectRecycleBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.RectFTrasformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BlazingSun on 2016/5/6.
 */
public class SelectRecyclerAdapter extends RecyclerView.Adapter<SelectRecyclerAdapter.MyViewHolder> {
    private final Context mContext;
    private List<SelectRecycleBean.DataBean.ItemsBean> mDatas = new ArrayList<>();


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, SelectRecycleBean.DataBean.ItemsBean data);
    }

    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener = null;

    private void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnRecyclerViewItemClickListener = listener;
    }


    public SelectRecyclerAdapter(Context context, List<SelectRecycleBean.DataBean.ItemsBean> mDatas) {
        this.mDatas = mDatas;
        Log.i("mDatas", "=======" + mDatas.size());
        this.mContext = context;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_selsect_recyclerview, parent, false);
        MyViewHolder viewholder = new MyViewHolder(view);

        return viewholder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


       Picasso
               .with(mContext)
               .load(mDatas.get(position)
               .getData().getCover_image_url())
               //.transform(new RectFTrasformation())
               .into(holder.grid_iv);



        holder.grid_tv_name.setText(mDatas.get(position).getData().getName());
        holder.grid_tv_price.setText(mDatas.get(position).getData().getPrice());
        holder.grid_tv_favoritesr.setText(mDatas.get(position).getData().getFavorites_count()+"");


    }

    @Override
    public int getItemCount() {
        Log.i("mDatas", "=======" + mDatas.size());
        return mDatas == null ? 0 : mDatas.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView grid_iv;
        TextView grid_tv_name, grid_tv_price, grid_tv_favoritesr;

        public MyViewHolder(View itemView) {
            super(itemView);
            grid_iv = (ImageView) itemView.findViewById(R.id.grid_iv);
            grid_tv_name = (TextView) itemView.findViewById(R.id.grid_tv_name);
            grid_tv_price = (TextView) itemView.findViewById(R.id.grid_tv_price);
            grid_tv_favoritesr = (TextView) itemView.findViewById(R.id.grid_tv_favoritesr);
        }


    }

    public void setDatas(List<SelectRecycleBean.DataBean.ItemsBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }


}
