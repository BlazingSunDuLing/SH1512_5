package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.ModelBean.CateALvBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.RoundTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 16-5-10.
 */
public class CateInAFragLvGvAdapter extends BaseAdapter {
    private List<CateALvBean.DataBean.ChannelGroupsBean.ChannelsBean> channels;

    public void setChannels(List<CateALvBean.DataBean.ChannelGroupsBean.ChannelsBean> channels) {
        this.channels = channels;
    }

    private Context mContext;
    public CateInAFragLvGvAdapter(List<CateALvBean.DataBean.ChannelGroupsBean.ChannelsBean> channels, Context mContext) {
        if(this.channels!=null){
            this.channels.clear();
        }
        this.channels=channels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return channels.size();
    }

    @Override
    public Object getItem(int position) {
        return channels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_a_lv_item_gv, parent, false);
            holder = new ViewHolder();
            holder.mItemCateInfragAlvIcon = (ImageView) convertView.findViewById(R.id.ItemCateInfragAlvIcon);
            holder.mItemCateInfragAlvTv = (TextView) convertView.findViewById(R.id.ItemCateInfragAlvTv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mContext)
                .load(channels.get(position).getIcon_url())
                .placeholder(R.mipmap.bg_loading)
                .error(R.mipmap.bg_error_loading)
                .transform(new RoundTransformation())
                .into(holder.mItemCateInfragAlvIcon);
        holder.mItemCateInfragAlvTv.setText(channels.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        private ImageView mItemCateInfragAlvIcon;
        private TextView mItemCateInfragAlvTv;
    }

}


