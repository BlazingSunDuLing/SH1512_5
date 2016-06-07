package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.sh1512_5.ModelBean.CateBLvBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.RoundTransformation;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Administrator on 16-5-12.
 */
public class CateInBFragLvRightItemRecyAdapter extends Adapter<CateInBFragLvRightItemRecyAdapter.MyViewHolder> {
    private List<CateBLvBean.DataBean.CategoriesBean.SubcategoriesBean> subcategories;
    private Context mContext;

    public CateInBFragLvRightItemRecyAdapter(Context mContext,List<CateBLvBean.DataBean.CategoriesBean.SubcategoriesBean> subcategories) {
        this.subcategories = subcategories;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_b_lv_right_item_recy,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(mContext)
                .load(subcategories.get(position).getIcon_url())
                .placeholder(R.mipmap.bg_loading)
                .error(R.mipmap.bg_error_loading)
                .transform(new RoundTransformation())
                .into(holder.mCateInFragBLvRightGvImg);
            holder.mCateInFragBLvRightGvTv.setText(subcategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView mCateInFragBLvRightGvImg;
        private TextView mCateInFragBLvRightGvTv;
        public MyViewHolder(View itemView) {
            super(itemView);
            mCateInFragBLvRightGvImg= (ImageView) itemView.findViewById(R.id.CateInFragBLvRightGvImg);
            mCateInFragBLvRightGvTv= (TextView) itemView.findViewById(R.id.CateInFragBLvRightGvTv);
        }
    }
}
