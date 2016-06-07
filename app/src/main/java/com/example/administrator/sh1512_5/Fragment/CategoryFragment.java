package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.sh1512_5.Adapter.CateFragVpAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.CateALvBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.CateFragtvPager)
    ViewPager mCateFragtvPager;
    @Bind(R.id.CateFragtRbA)
    RadioButton mCateFragtRbA;
    @Bind(R.id.CateFragtRbB)
    RadioButton mCateFragtRbB;
    @Bind(R.id.CateFragtRg)
    RadioGroup mCateFragtRg;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private static CategoryFragment mCategoryFragment;
    /*自定义变量*/
    private Context mContext;
    private List<Fragment> listFrag;
    private List<CateALvBean.DataBean.ChannelGroupsBean> channel_groups = new ArrayList<>();
    private CateALvBean mLvBean;
    public Invalidate invali;
    public interface Invalidate{
        void invalidate(int n);
    }

    public  void getInvalidate(Invalidate invalidate){
        this.invali=invalidate;
    }
    /*单例模式获取对象，懒汉式*/
    public static CategoryFragment getInstance(){
        return Singoton.instance;
    }
    private static class Singoton{
        private static final CategoryFragment instance=CategoryFragment.newInstance("","");
    }

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragment();
        initViewPager();
        initListener();
        initHttp();
    }

    private void initHttp() {
        //A.攻略fragment 专题列表项下面
        new MyAsyncTask_Auto<CateALvBean>(CateALvBean.class, new MyAsyncTask_Auto.DataCallback<CateALvBean>() {

            @Override
            public void getData(CateALvBean cateALvBean) {
                LogUtils.i("mtg", ">>>>>>开始下载A2的数据");
                mLvBean = cateALvBean;
                if(mLvBean!=null){
                    channel_groups.addAll(cateALvBean.getData().getChannel_groups());
                    invali.invalidate(1);       //回调方法，用于数据下载完成后刷新数据
                    LogUtils.i("mtg", "》》》》》A2回调刷新完成");
                }
            }
        }).execute(Constant.CategoryUrl1);
    }

    private void initFragment() {
        listFrag = new ArrayList<Fragment>();
        listFrag.add(CategoryInAFragment.newInstance(channel_groups, ""));
        listFrag.add(CategoryInBFragment.newInstance("", ""));
    }

    private void initViewPager() {
        CateFragVpAdapter adapter = new CateFragVpAdapter(getFragmentManager(), listFrag);
        mCateFragtvPager.setAdapter(adapter);
        mCateFragtvPager.setCurrentItem(0);
        changeViewPager();
    }

    private void changeViewPager() {
        if (mCateFragtRbA.isChecked()) {
            mCateFragtvPager.setCurrentItem(0);
        } else {
            mCateFragtvPager.setCurrentItem(1);
        }
    }

    private void initListener() {
        /*ViewPager的监听，滑动后对应着改变RG*/
        mCateFragtvPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    //第一个按钮开始变化
                    mCateFragtRbA.setChecked(true);
                } else {
                    //第二个按钮开始变化
                    mCateFragtRbB.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*RadioGroup的监听，改变即换一页*/
        mCateFragtRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mCateFragtRbA.getId()) {
                    mCateFragtvPager.setCurrentItem(0, true);
                } else if (checkedId == mCateFragtRbB.getId()) {
                    mCateFragtvPager.setCurrentItem(1, true);
                }
            }
        });
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
