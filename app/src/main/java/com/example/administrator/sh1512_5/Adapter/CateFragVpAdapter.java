package com.example.administrator.sh1512_5.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-5-7.
 */
public class CateFragVpAdapter extends FragmentPagerAdapter{
    private List<Fragment> listFrag;
    private ArrayList<String> listTemp;
    public CateFragVpAdapter(FragmentManager fm,List<Fragment> listFrag) {
        super(fm);
        this.listFrag=listFrag;
        initTemp();
    }

    private void initTemp() {
        Log.i("mtg","initTemp");
        listTemp=new ArrayList<>();
        for (int i = 0; i < listFrag.size(); i++) {
            listTemp.add("temp");
            Log.i("mtg",""+listTemp.toString());
        }
    }

    @Override
    public Fragment getItem(int position) {

        return listFrag.get(position);
    }

    @Override
    public int getCount() {
        return listFrag.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTemp.get(position);
    }
}
