package com.example.administrator.sh1512_5.Fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.sh1512_5.Activity.SearchActivity;
import com.example.administrator.sh1512_5.Adapter.GuidDropDownGridViewAdapter;
import com.example.administrator.sh1512_5.Adapter.GuidFragmentPagerAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.GuidTabLayoutBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuidFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuidFragment extends Fragment {
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
    private boolean flag = false;
    private boolean frag = true;
    public int flagItemNum = 0;
    boolean flagItem = false;
    public View mView, mView1;
    private List<GuidTabLayoutBean.DataBean> mDataBeans = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private List<String> mTabText = new ArrayList<>();
    private ArrayList<Integer> tabId = new ArrayList<>();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mImageView, guidSearch;
    private LinearLayout mLinearLayout;
    private TextView mTextView;
    private GridView mGridView;
    private Animator alphaShow, alphaHide, transDown, transUp;
    private ViewGroup.LayoutParams tapParams, linearParams, textParams;
    private GuidFragmentPagerAdapter mGuidFragmentPagerAdapter;
    private GuidDropDownGridViewAdapter guidAdater;
    private GuidSurplusFragment mSurplusFragment = new GuidSurplusFragment();
    private String forepartUrl = "http://api.liwushuo.com/v2/channels/";
    private String backUrl = "/items?limit=20&offset=0&gender=1&generation=1";

    public GuidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuidFragment newInstance(String param1, String param2) {
        GuidFragment fragment = new GuidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化动画
        initAnimator();
//        mHandler = new Handler();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guid, container, false);
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

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDropDown();//初始化下拉列表
        initTextView(view);//初始换"切换频道"
        initLinear(view);//初始化三角图标
        //初始化TAB标签
        initTabLayout(view);
        //网络请求数据
        localData();
    }

    private void initView(View view) {
        mGridView = (GridView) view.findViewById(R.id.GuidFragGridView);
        mImageView = (ImageView) view.findViewById(R.id.GuidFragImage);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.GuidFragLinear);
        guidSearch = (ImageView) view.findViewById(R.id.GuidSearch);
        guidSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDropDown() {
        transUp.setTarget(mGridView);
        transUp.start();
        guidAdater = new GuidDropDownGridViewAdapter(mTabText, mContext);
        mGridView.setAdapter(guidAdater);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showGridItem(position);//为选中的item添加红线
                animator();
                mViewPager.setCurrentItem(position);//显示指定的Fragment
                Log.d("+++position++" + position, "+++++");

            }

        });

    }

    public void showGridItem(int position) {
        mView = mGridView.getChildAt(flagItemNum).findViewById(R.id.GuidDropDownItemBottomLine);
        mView1 = mGridView.getChildAt(position).findViewById(R.id.GuidDropDownItemBottomLine);
        if (flagItem && flagItemNum != position) {
            goneOrVisible();//显示和隐藏红线
            flagItemNum = position;
        } else {
            goneOrVisible();
            flagItem = true;
            flagItemNum = position;
        }
        Log.d("+++flagItem++" + flagItemNum, "+++++");
    }

    private void goneOrVisible() {
        mView.setVisibility(View.GONE);
        mView1.setVisibility(View.VISIBLE);
    }

    private void animator() {
        //隐藏text
        textAlphaHide();
        //列表上移
        transUp();
        flag = false;
    }

    private void initLinear(View view) {
        linearParams = mLinearLayout.getLayoutParams();
        linearParams.width = mContext.getResources().getDisplayMetrics().widthPixels / 10;
        mLinearLayout.setLayoutParams(linearParams);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    animator();
                    Log.d("++++++点击+++", "======");
                } else {
                    //隐藏tap
                    tapAlphaHide();
                    //列表下移
                    transDown();
                    flag = true;
                    Log.d("++++++再次点击+++", "======");
                }
            }
        });
    }

    private void textAlphaShow() {
        alphaShow.setTarget(mTextView);
        alphaShow.start();
        mTextView.setVisibility(View.VISIBLE);
    }

    private void textAlphaHide() {
        alphaHide.setTarget(mTextView);
        alphaHide.start();
        alphaHide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mLinearLayout.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                mTextView.setVisibility(View.GONE);
                //显示
                tapAlphaShow();
                mLinearLayout.setClickable(true);
            }
        });
    }

    private void transDown() {
        transDown.setTarget(mGridView);
        transDown.start();
        mImageView.setImageResource(R.mipmap.arrow_index_up);
        mGridView.setAlpha(1);
        mGridView.setVisibility(View.VISIBLE);
    }

    private void transUp() {
        transUp.setTarget(mGridView);
        transUp.start();
        mImageView.setImageResource(R.mipmap.arrow_index_down);
    }

    private void initTextView(View view) {
        mTextView = (TextView) view.findViewById(R.id.GuidFragText);
        textParams = mTextView.getLayoutParams();
        textParams.width = mContext.getResources().getDisplayMetrics().widthPixels / 10 * 9;
        mTextView.setLayoutParams(textParams);
    }

    private void tapAlphaShow() {
        alphaShow.setTarget(mTabLayout);
        alphaShow.start();
        mTabLayout.setVisibility(View.VISIBLE);
    }

    private void tapAlphaHide() {
        alphaHide.setTarget(mTabLayout);
        alphaHide.start();
        alphaHide.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mLinearLayout.setClickable(false);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                textAlphaShow();
                mGridView.setVisibility(View.VISIBLE);
                mTabLayout.setVisibility(View.GONE);
//                textAlphaShow();
                mLinearLayout.setClickable(true);
            }
        });

    }

    private void initAnimator() {
        alphaShow = AnimatorInflater.loadAnimator(mContext, R.animator.drop_show);
        alphaHide = AnimatorInflater.loadAnimator(mContext, R.animator.drop_hide);
        transDown = AnimatorInflater.loadAnimator(mContext, R.animator.drop_down);
        transUp = AnimatorInflater.loadAnimator(mContext, R.animator.drop_up);
    }

    private void localData() {
        if (mDataBeans.size() == 0) {
            new MyAsyncTask_Auto<GuidTabLayoutBean>(GuidTabLayoutBean.class, new MyAsyncTask_Auto.DataCallback<GuidTabLayoutBean>() {
                @Override
                public void getData(GuidTabLayoutBean guidTabLayoutBean) {
                    Log.d("guide数据", "========");
                    if (guidTabLayoutBean != null) {
                        mDataBeans.add(guidTabLayoutBean.getData());
                        for (int i = 0; i < guidTabLayoutBean.getData().getChannels().size(); i++) {
                            //添加TAB标签
                            mTabLayout.addTab(mTabLayout.newTab().setText(guidTabLayoutBean.getData().getChannels().get(i).getName()).setTag(i));
                            //获取TAB标签文本集合
                            mTabText.add(guidTabLayoutBean.getData().getChannels().get(i).getName());
                            tabId.add(guidTabLayoutBean.getData().getChannels().get(i).getId());
                        }
                        tabId.remove(0);
                        //初始化Fragment集合
                        initFragmentData();
                        mGuidFragmentPagerAdapter.notifyDataSetChanged();
                        guidAdater.notifyDataSetChanged();
                        Log.d("==mTabText==", mTabText.size() + "====");
                    }
                }
            }).execute(Constant.GUID_TABLAYOUT_URL);
        }
    }

    private void initTabLayout(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.GuidFragViewPager);
        mTabLayout = (TabLayout) view.findViewById(R.id.GuidFragTabLayout);
        //代码设置控件的宽高
        tapParams = mTabLayout.getLayoutParams();
        tapParams.width = mContext.getResources().getDisplayMetrics().widthPixels / 10 * 9;
        mTabLayout.setLayoutParams(tapParams);
        mTabLayout.setTabTextColors(Color.BLACK, Color.BLACK);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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

    private void initFragmentData() {
        mFragments.add(new GuidHandpickFragment());
        mGuidFragmentPagerAdapter = new GuidFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTabText);
        for (int i = 1; i < mTabText.size(); i++) {
            GuidSurplusFragment guidSurplusFragment = new GuidSurplusFragment();
            guidSurplusFragment.setUrl(forepartUrl + tabId.get(i - 1) + backUrl);
            mFragments.add(guidSurplusFragment);
        }
        mViewPager.setAdapter(mGuidFragmentPagerAdapter);
        //页面发生改变
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mTabText.size() != 0) {
                    showGridItem(position);
                    Log.d("++GuidTab++" + mTabText.toString(), "=========");
                }
//
                Log.d("++GuidTab1++" + mTabText.toString(), "=========");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
