package com.example.administrator.sh1512_5;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.administrator.sh1512_5.Base.BaseActivity;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.Fragment.CategoryFragment;
import com.example.administrator.sh1512_5.Fragment.GuidFragment;
import com.example.administrator.sh1512_5.Fragment.ProfileFragment;
import com.example.administrator.sh1512_5.Fragment.SelectFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ArrayList<Fragment> mList = new ArrayList<>();
    private TabLayout mainactivityTabLayout;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mainactivityTabLayout = (TabLayout) findViewById(R.id.mainactivityTabLayout);
        initFragment();//初始化Fragment
        initTabLayout();//初始化TabLayout
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initListener() {
        //TabLayout的监听
        mainactivityTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取当前被选中的tab标签的tag属性
                int tag = Integer.parseInt(tab.getTag().toString());
                //控制Fragment的显示
                Fragment fragment = mFragmentManager.findFragmentByTag("" + tag);
                if (fragment == null) {
                    //代表没哟被显示在屏幕上
                    mFragmentManager.beginTransaction().add(R.id.mainactivityFragment, mList.get(tag), tag + "").commit();
                }
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                for (int i = 0; i < mList.size(); i++) {
                    if (i == tag) {
                        fragmentTransaction.show(mList.get(i));
                    } else {
                        fragmentTransaction.hide(mList.get(i));
                    }
                }
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void initFragment() {
        mList.add(new GuidFragment());
        mList.add(new SelectFragment());
        mList.add(CategoryFragment.getInstance());
        mList.add(new ProfileFragment());

        //碎片管理器
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.mainactivityFragment, mList.get(0), "0").commit();
    }

    public void initTabLayout() {
        //设置点击前后的字体颜色
        mainactivityTabLayout.setTabTextColors(R.color.colorGray, Color.RED);
        //设置Tab标签的对齐方式,平分TabLayout的全部宽度
        mainactivityTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //不显示下方的导航条
        mainactivityTabLayout.setSelectedTabIndicatorHeight(0);

        for (int i = 0; i < Constant.TAB_BOTTON_TEXT.length; i++) {
            mainactivityTabLayout.addTab(mainactivityTabLayout.
                    newTab().setText(Constant.TAB_BOTTON_TEXT[i]).setIcon(Constant.TAB_BOTTON_ICON[i]).setTag(i));
        }
    }
}
