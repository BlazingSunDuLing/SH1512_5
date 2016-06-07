package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.sh1512_5.Activity.ProfileCartActivity;
import com.example.administrator.sh1512_5.Activity.ProfileCouponActivity;
import com.example.administrator.sh1512_5.Activity.ProfileMessageActivity;
import com.example.administrator.sh1512_5.Activity.ProfileOrderActivity;
import com.example.administrator.sh1512_5.Activity.ProfileServiceActivity;

import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.R;
import com.zxing.activity.CaptureActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private TabLayout mTabLayout;
    private TextView pro_cart, pro_order, pro_coupon, pro_service;
    private ImageView pro_image_message, pro_image_settings, pro_image_scan, pro_image_me;
    private TextView pro_textview_me;
    private ViewPager pro_ViewPager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    /*自定义变量*/
    private Context mContext;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
        /*else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pro_cart:
                startActivity(new Intent(mContext, ProfileCartActivity.class));
                break;
            case R.id.pro_order:
                startActivity(new Intent(mContext, ProfileOrderActivity.class));
                break;
            case R.id.pro_coupon:
                startActivity(new Intent(mContext, ProfileCouponActivity.class));
                break;
            case R.id.pro_service:
                startActivity(new Intent(mContext, ProfileServiceActivity.class));
                break;
            case R.id.pro_image_scan:
                startActivityForResult(new Intent(mContext, CaptureActivity.class), 0);
                break;
            case R.id.pro_image_message:
                startActivity(new Intent(mContext, ProfileMessageActivity.class));
                break;
            case R.id.pro_image_settings:

                break;
            case R.id.pro_image_me:

                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initListener();
        initTablayout();

    }

    private void initTablayout() {

        for (int i = 0; i < Constant.TAB_BOTTON_TEXT2.length; i++) {
            mTabLayout.addTab(mTabLayout.
                    newTab().setText(Constant.TAB_BOTTON_TEXT2[i]).setTag(i));
        }
    }

    private void initListener() {
//      购物车 礼券 订单 客服 监听方法
        pro_cart.setOnClickListener(this);
        pro_order.setOnClickListener(this);
        pro_coupon.setOnClickListener(this);
        pro_service.setOnClickListener(this);
//       注册 二维码 设置 头像 监听方法
        pro_image_message.setOnClickListener(this);
        pro_image_scan.setOnClickListener(this);
        pro_image_settings.setOnClickListener(this);
        pro_image_me.setOnClickListener(this);

//      TabLayout监听方法
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //获取当前被选中的tab标签的tag属性
                int tag = Integer.parseInt(tab.getTag().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(View view) {
//        TabLayout
        mTabLayout = (TabLayout) view.findViewById(R.id.pro_tablayout);
 //        ViewPager
        pro_ViewPager = (ViewPager) view.findViewById(R.id.pro_ViewPager);
//        用户名
        pro_textview_me = (TextView) view.findViewById(R.id.pro_textview_me);
//      购物车 礼券 订单 客服
        pro_cart = (TextView) view.findViewById(R.id.pro_cart);
        pro_order = (TextView) view.findViewById(R.id.pro_order);
        pro_coupon = (TextView) view.findViewById(R.id.pro_coupon);
        pro_service = (TextView) view.findViewById(R.id.pro_service);
//       注册 二维码 设置 头像
        pro_image_message = (ImageView) view.findViewById(R.id.pro_image_message);
        pro_image_scan = (ImageView) view.findViewById(R.id.pro_image_scan);
        pro_image_settings = (ImageView) view.findViewById(R.id.pro_image_settings);
        pro_image_me = (ImageView) view.findViewById(R.id.pro_image_me);
    }


}

