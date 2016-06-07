package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.sh1512_5.ModelBean.CateBLvBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 16-5-12.
 */
public class CateInBFragLvRightAdapter extends BaseAdapter {
    private Context mContext;
    private List<CateBLvBean.DataBean.CategoriesBean> categoriesList;
    private CateInBFragLvRightItemRecyAdapter adapter;
    private RecyclerView.LayoutManager manager;

    public CateInBFragLvRightAdapter(Context mContext, List<CateBLvBean.DataBean.CategoriesBean> categoriesList) {
        this.mContext = mContext;
        this.categoriesList = categoriesList;
    }

    @Override
    public int getCount() {
        if(categoriesList.size()!=0){
            return categoriesList.get(0).getSubcategories().size();
        }else {
            return categoriesList.size();
        }
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_b_lv_right,parent,false);
            holder=new ViewHolder();
            holder.mCateInFragBLvRightTv= (TextView) convertView.findViewById(R.id.CateInFragBLvRightTv);
            holder.mCateInFragBRightRecy= (RecyclerView) convertView.findViewById(R.id.CateInFragBRightRecy);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        if(position<categoriesList.size()){
            holder.mCateInFragBLvRightTv.setText(categoriesList.get(position+1).getName());
            LogUtils.i("mtg",">>"+categoriesList.get(position+1).getName());
        }
        adapter =new CateInBFragLvRightItemRecyAdapter(mContext,categoriesList.get(position).getSubcategories());
        initRecy(holder.mCateInFragBRightRecy);
        adapter.notifyDataSetChanged();
        return convertView;
    }

    class ViewHolder{
        private TextView mCateInFragBLvRightTv;
        private RecyclerView mCateInFragBRightRecy;
    }
    private void initRecy(RecyclerView mCateInFragBRightRecy) {
        mCateInFragBRightRecy.setHasFixedSize(true);
        mCateInFragBRightRecy.setAdapter(adapter);
        manager =new GridLayoutManager(mContext,3,OrientationHelper.VERTICAL,false);
        mCateInFragBRightRecy.setLayoutManager(manager);
    }
}
