package com.example.administrator.sh1512_5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YANG-PC on 2016/5/7.
 */
public class GuidFragmentPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabTexts = new ArrayList<>();

    public GuidFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, List<String> tabTexts) {
        super(fm);
        this.mFragments = fragments;
        this.mTabTexts = tabTexts;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTexts.get(position);
    }
}
