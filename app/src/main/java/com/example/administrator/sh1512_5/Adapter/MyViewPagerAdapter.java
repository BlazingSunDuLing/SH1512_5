package com.example.administrator.sh1512_5.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by YANG-PC on 2016/5/5.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    private ArrayList<ImageView> mImageViews = new ArrayList<>();

    public MyViewPagerAdapter(ArrayList<ImageView> imageViews) {
        mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position % mImageViews.size());
        if (container.getParent() != null) {
           container.removeViewInLayout(mImageViews.get(position % mImageViews.size()));
            container.addView(imageView);

        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mImageViews.get(position % mImageViews.size()));
    }
}
