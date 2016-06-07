package com.example.administrator.sh1512_5.MyView;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.sh1512_5.Adapter.MyViewPagerAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.GuidRecyclerFristItemPagerBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANG-PC on 2016/5/5.
 */
public class MyViewPager extends LinearLayout {

    private ViewPager mViewPager;
    private RadioGroup mGroup;
    ArrayList<String> imageUrls = new ArrayList<>();
    ArrayList<ImageView> mImageViews = new ArrayList<>();
    private List<GuidRecyclerFristItemPagerBean.DataBean> mDataBeans = new ArrayList<>();
    private MyViewPagerAdapter mAdapter;
    Handler mHandler = new Handler();
    Runnable mRunnable;

    boolean flag = false;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //网络加载数据
        initAsync(context);
        //初始化ViewPager的显示
        initViewPager(context);
    }

    public MyViewPager(Context context) {
        super(context);
        //网络加载数据
        initAsync(context);
        //初始化ViewPager的显示
        initViewPager(context);
    }

    private void initViewPager(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.myviewpager_layout, this, true);
        mViewPager = (ViewPager) view.findViewById(R.id.GuidItemFristMyViewPager);
        mGroup = (RadioGroup) view.findViewById(R.id.GuidItemFristRadioGroup);
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Log.d("ViewPager", "=====");
            mImageViews.add(imageView);
        }
        mAdapter = new MyViewPagerAdapter(mImageViews);
        mViewPager.setAdapter(mAdapter);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int current = mViewPager.getCurrentItem();
                current++;
                mViewPager.setCurrentItem(current);
                mHandler.postDelayed(this, 2000);
            }
        };

        mHandler.postDelayed(mRunnable, 2000);
        //监听viewPager
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton radioButton = (RadioButton) mGroup.getChildAt(position % mImageViews.size());
                mGroup.check(radioButton.getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_DRAGGING://手动触摸
                        flag = true;
                        mHandler.removeCallbacks(mRunnable);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE://滑动停止
                        if (flag) {
                            mHandler.postDelayed(mRunnable, 1000);
                            flag = false;
                        }
                        break;
                }
            }
        });
    }

    private void initAsync(final Context context) {
        if (mDataBeans.size() == 0) {
            new MyAsyncTask_Auto<GuidRecyclerFristItemPagerBean>(GuidRecyclerFristItemPagerBean.class, new MyAsyncTask_Auto.DataCallback<GuidRecyclerFristItemPagerBean>() {
                @Override
                public void getData(GuidRecyclerFristItemPagerBean guidRecyclerFristItemPagerBean) {
                    if (guidRecyclerFristItemPagerBean != null) {
                        mDataBeans.add(guidRecyclerFristItemPagerBean.getData());
                        for (int i = 0; i < guidRecyclerFristItemPagerBean.getData().getBanners().size(); i++) {
//                        Log.d("执行到此", "==========");
                            imageUrls.add(guidRecyclerFristItemPagerBean.getData().getBanners().get(i).getImage_url());
                            Log.d("imageurl=="+imageUrls.get(i).toString(),"49520");
                            Picasso.with(context)
                                    .load(imageUrls.get(i))
                                    .placeholder(R.mipmap.abc_menu_dropdown_panel_holo_dark)
                                    .into(mImageViews.get(i));
//                        Log.d("GuidFragment", "======" + imageUrls.size());
                        }
                        mAdapter.notifyDataSetChanged();
                    }


                }
            }).execute(Constant.GUID_RECYCLER_FRISTITEM_VIEWPAGER_URL);
        }
    }
}
