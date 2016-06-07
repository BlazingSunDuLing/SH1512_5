package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.sh1512_5.ModelBean.CateALvBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.ChangeToDp;
import com.example.administrator.sh1512_5.Utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 16-5-10.
 */
public class CateInAFragLvAdapter extends BaseAdapter{
    private List<CateALvBean.DataBean.ChannelGroupsBean> channel_groups;
    private Context mContext;
    private CateInAFragLvGvAdapter adapter;
    private static int i=0;
    public CateInAFragLvAdapter(List<CateALvBean.DataBean.ChannelGroupsBean> channel_groups,Context mContext) {
        this.channel_groups = channel_groups;
        this.mContext=mContext;
    }

    @Override
    public int getCount() {
        return channel_groups.size();
    }

    @Override
    public Object getItem(int position) {
        return channel_groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_a_lv,parent,false);
            holder=new ViewHolder();
            holder.mCateInFragLvTv= (TextView)convertView.findViewById(R.id.CateInFragLvTv);
            holder.mCateInFragLvGv= (GridView)convertView.findViewById(R.id.CateInFragLvGv);
            initGridView(position);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //设置每一个GvItem的集合
        adapter.setChannels(channel_groups.get(position).getChannels());
        //设置计算后的高度
        setGvLayoutParams(holder.mCateInFragLvGv,position);
        holder.mCateInFragLvTv.setText(channel_groups.get(position).getName());
        holder.mCateInFragLvGv.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
        return convertView;
    }

    private void initGridView(int position) {
        adapter=new CateInAFragLvGvAdapter(channel_groups.get(position).getChannels(),mContext);
    }

    class ViewHolder{
        private TextView mCateInFragLvTv;
        private GridView mCateInFragLvGv;
    }
    /*计算Gv高度*/
    private float calculateHeight(int position){
        float height;
        int i=channel_groups.get(position).getChannels().size()/4;
        int h=channel_groups.get(position).getChannels().size()%4;
        if(h!=0){
            i=i+1;
        }
        float oneLine=10+20+75+20; //pading的5dp上下为10dp;Gv每个竖直间距20dp;图片75dp;文字20dp
        height=i*oneLine-20;
        float dp = ChangeToDp.getDp(mContext, height);
        return dp;
    }
    /*动态设置Gv的高度，以自适应Lv的高度*/
    private void setGvLayoutParams(GridView gv,int position){
        ViewGroup.LayoutParams params = gv.getLayoutParams();
        params.width= (int) ChangeToDp.getDisplayMetrics(mContext,ChangeToDp.WIDTH);
        params.height= (int) calculateHeight(position);
        gv.setLayoutParams(params);
    }
}
