package com.example.administrator.sh1512_5.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.GuidRecyclerFristItemPagerBean;
import com.example.administrator.sh1512_5.ModelBean.GuidRecyclerTwoItemBean;
import com.example.administrator.sh1512_5.MyView.MyViewPager;
import com.example.administrator.sh1512_5.MyView.MylittleStar;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.RectFTrasformation;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by YANG-PC on 2016/5/5.
 */
public class GuidRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private int id = 0x123;
    private ArrayList<GuidRecyclerFristItemPagerBean.DataBean.BannersBean> mFristBannerBeans = new ArrayList<>();
    private List<GuidRecyclerTwoItemBean.DataBean.ItemsBean> mItemsBeans = new ArrayList<>();
    private GuidRecyclerTwoItemBean.DataBean.ItemsBean mItemsBean = new GuidRecyclerTwoItemBean.DataBean.ItemsBean();
    private ArrayList<List> mArrayList = new ArrayList<>();
    private ArrayList<String> mIMageUrls = new ArrayList<>();
    private GuidRecyclerFristItemRecyclerAdapter mGuidRecyclerFristItemRecyclerAdapter;
    private final Calendar mCalendar = Calendar.getInstance();
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    //    private int number = -1;
    private long systemTime = 0;
    TextView mTitle;
    ImageView mNewImage, mLocalImage;
    MylittleStar mStar;
    long num = 0;
    ViewGroup viewGroup;
    int j = 0;

    public void setIMageUrls(ArrayList<String> IMageUrls) {
        mIMageUrls = IMageUrls;
        mGuidRecyclerFristItemRecyclerAdapter.notifyDataSetChanged();
        Log.d("===+++GuidRecy+++", "=====" + mIMageUrls.size());

    }

    public GuidRecyclerAdapter(Context context) {
        mContext = context;
    }

    public GuidRecyclerAdapter(Context context, ArrayList<String> iMageUrls, ArrayList<List> arrayList) {
        mStar = new MylittleStar(context);
        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        systemTime = System.currentTimeMillis();
        this.mContext = context;
        this.mIMageUrls = iMageUrls;
//        this.mItemsBeans = itemsBeans;
        this.mArrayList = arrayList;
        Log.d(mItemsBeans.size() + "======", "234");
    }

    int[] type = {0, 1};

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return type[0];
        } else {
            return type[1];
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        //根据不同的viewType类型初始化不同的ViewHolder对象
        switch (viewType) {
            case 0:
                holder = new MyViewHolder1(LayoutInflater.from(mContext).inflate(R.layout.itemfrist_guid_infrag_recycler_layout, parent, false));
                break;
            case 1:
                holder = new MyViewHolder2(LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_layout, parent, false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int number = position - 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Log.d("position:" + position, "++number:" + number + "m");
        //判断当前返回的holder属性的那种ViewHoder的子类
        //根据不同的种类，设置其中控件数据的显示
        if (holder instanceof MyViewHolder2) {
            Log.d("position:" + position, "++number:" + number + "mArrayList.get(number).size()=" + mArrayList.get(number).size());
            MyViewHolder2 holder2 = (MyViewHolder2) holder;
            ViewGroup mViewGroup = (ViewGroup) holder2.itemView;
            mItemsBean = (GuidRecyclerTwoItemBean.DataBean.ItemsBean) mArrayList.get(number).get(0);
            num = mItemsBean.getCreated_at();
            holder2.mDate = new Date(num * 1000);//在这里,只能先将毫秒值数值赋值给num,然后再用num*1000;原因:搞不懂
            mCalendar.setTime(holder2.mDate);
            holder2.mWay = String.valueOf(mCalendar.get(Calendar.DAY_OF_WEEK));
            holder2.mTime.setText(mFormat.format(holder2.mDate));//年月日

            for (int i = 0; i < mArrayList.get(number).size(); i++) {
                ViewGroup mView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, null);
                mItemsBean = (GuidRecyclerTwoItemBean.DataBean.ItemsBean) mArrayList.get(number).get(i);

                mTitle = (TextView) mView.findViewById(R.id.GuidItemTwoCardTitleTV);
                mNewImage = (ImageView) mView.findViewById(R.id.GuidItemTwoCardIvNEW);
                mLocalImage = (ImageView) mView.findViewById(R.id.GuidItemTwoCardIV);
                mStar = (MylittleStar) mView.findViewById(R.id.GuidItemTwoCardMylittleStar);
                Picasso.with(mContext).load(mItemsBean.getCover_image_url()).placeholder(R.mipmap.e).transform(new RectFTrasformation()).into(mLocalImage);
                mStar.setStarText(mItemsBean.getLikes_count());//喜欢数
                mTitle.setText(mItemsBean.getTitle());//标题
                mStar.setId(mItemsBean.getId() + i);
                mViewGroup.addView(mView);
            }

//            for (int i = 0; i < mArrayList.get(number).size(); i++) {
////                ViewGroup mView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, null);
//                mItemsBean = (GuidRecyclerTwoItemBean.DataBean.ItemsBean) mArrayList.get(number).get(i);
//
//                Picasso.with(mContext).load(mItemsBean.getCover_image_url()).placeholder(R.mipmap.e).transform(new RectFTrasformation()).into(holder2.mLocalImage);
//                holder2.mStar.setStarText(mItemsBean.getLikes_count());//喜欢数
//                holder2.mTitle.setText(mItemsBean.getTitle());//标题
//            }
            week(holder2);//判断星期
            holder2.setIsRecyclable(false);
            if (number == 0) {
                holder2.mLayout.setVisibility(View.VISIBLE);

//                if (systemTime>num*1000 && ){
////                    holder2.nextUpdateTime.setText();
//                }
            }
            Log.d(holder2.mWay + "==5598==", "--" + System.currentTimeMillis());
        }
    }

    private void week(MyViewHolder2 holder2) {
        switch (Integer.parseInt(holder2.mWay)) {
            case 1:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[0]);
                break;
            case 2:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[1]);
                break;
            case 3:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[2]);
                break;
            case 4:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[3]);
                break;
            case 5:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[4]);
                break;
            case 6:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[5]);
                break;
            case 7:
                holder2.mWeek.setText(Constant.GUID_RECYCLER_TWOITEM_WEEK[6]);
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(mArrayList.size() + "==4568====", "234");
        return mArrayList.size() + 1;
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        MyViewPager mMyViewPager = new MyViewPager(mContext);
        public RecyclerView mRecyclerView;

        public MyViewHolder1(View itemView) {
            super(itemView);
            mRecyclerView = (RecyclerView) itemView.findViewById(R.id.GuidItemFristRecycler);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mGuidRecyclerFristItemRecyclerAdapter = new GuidRecyclerFristItemRecyclerAdapter(mIMageUrls, mContext, mRecyclerView);
            mRecyclerView.setAdapter(mGuidRecyclerFristItemRecyclerAdapter);
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {
        Date mDate;
        String mWay;
        //        ViewGroup mView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, null);
        MylittleStar mStar = new MylittleStar(mContext);
        TextView mTime, mTitle, mWeek, nextUpdateTime;
        ImageView mNewImage, mLocalImage;
        LinearLayout mLayout;
        ViewGroup mViewGroup;

        //        LinearLayout mLayout;
//        TextView mTitle = new TextView(mContext);
//        ImageView mNewImage = new ImageView(mContext);
//        ImageView mLocalImage = new ImageView(mContext);
//        LinearLayout mLayout = new LinearLayout(mContext);
        public MyViewHolder2(View itemView) {
            super(itemView);
            mViewGroup = (ViewGroup) itemView;
            mTime = (TextView) itemView.findViewById(R.id.GuidItemTwoTV);
            mWeek = (TextView) itemView.findViewById(R.id.GuidItemTwoWeek);
            mLayout = (LinearLayout) itemView.findViewById(R.id.GuidItemTwoUpdateTime);
            nextUpdateTime = (TextView) itemView.findViewById(R.id.GuidItemTwoNextUpdateTimeTV);
//          MylittleStar mStar = new MylittleStar(mContext);
//            for (int i = 0; i < mArrayList.get(j).size(); i++) {
//
//                mItemsBean = (GuidRecyclerTwoItemBean.DataBean.ItemsBean) mArrayList.get(j).get(i);
//                ViewGroup mView = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, null);
//                mViewGroup.addView(mView);
//
////                ImageView mNewImage;
////                ImageView mLocalImage = new ImageView(mContext);
//                mTitle = (TextView) itemView.findViewById(R.id.GuidItemTwoCardTitleTV);
//                mNewImage = (ImageView) itemView.findViewById(R.id.GuidItemTwoCardIvNEW);
//                mLocalImage = (ImageView) itemView.findViewById(R.id.GuidItemTwoCardIV);
//                mStar = (MylittleStar) itemView.findViewById(R.id.GuidItemTwoCardMylittleStar);
//            }
//            j++;
        }
    }

    DataCallback mDataCallback;

    public interface DataCallback {
        void position(int position);
    }
}
