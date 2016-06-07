package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.sh1512_5.ModelBean.CateBLvBean;
import com.example.administrator.sh1512_5.R;
import java.util.List;

/**
 * Created by YANG-PC on 2016/5/11.
 */
public class CateInBFragLvleftAdapter extends BaseAdapter {
    private List<CateBLvBean.DataBean.CategoriesBean> categoriesList;
    private Context mContext;
    public CateInBFragLvleftAdapter(Context mContext,List<CateBLvBean.DataBean.CategoriesBean> categoriesList) {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
    }

    @Override
    public int getCount() {
        return categoriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(holder==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_b_lv_left,parent,false);
            holder=new ViewHolder();
            holder.mCateInFragBLvLeftTv = (TextView) convertView.findViewById(R.id.mCateInFragBLvLeftTv);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.mCateInFragBLvLeftTv.setText(categoriesList.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        private TextView mCateInFragBLvLeftTv;
    }
}
