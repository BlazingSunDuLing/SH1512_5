package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.example.administrator.sh1512_5.Activity.CategInAFragTvAllActivity;
import com.example.administrator.sh1512_5.Adapter.CateInAFragLvAdapter;
import com.example.administrator.sh1512_5.Adapter.CateInAFragRecyAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.CateALvBean;
import com.example.administrator.sh1512_5.ModelBean.CateARecyBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.LogUtils;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;
import java.util.ArrayList;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryInAFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryInAFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.CateInFragRecy)
    RecyclerView mCateInFragRecy;
    @Bind(R.id.CateInFragCard)
    CardView mCateInFragCard;
    @Bind(R.id.CateInFragLv)
    ListView mCateInFragLv;
    @Bind(R.id.CateInFragTvAll)
    TextView mCateInFragTvAll;

    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private ArrayList<CateALvBean.DataBean.ChannelGroupsBean> channel_groups;//A.攻略fragment 专题列表项下面链接 送女票、送男票
    /*自定义变量*/
    private Context mContext;
    private List<CateARecyBean> mRecyList= new ArrayList();;
    private CateInAFragRecyAdapter adapter;
    private CateInAFragLvAdapter adapterLv;

    public CategoryInAFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CategoryInAFragment newInstance(List<CateALvBean.DataBean.ChannelGroupsBean> channel_groups, String param2) {
        CategoryInAFragment fragment = new CategoryInAFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, (ArrayList<? extends Parcelable>) channel_groups);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            channel_groups = getArguments().getParcelableArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_category_in_a, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initAdapter();
        initRecy();
        initListener();
        initListView();
        upDate();
        LogUtils.i("mtg", ">>>CategoryInAFrag...onViewCreated");
    }

    private void initView() {

    }

    private void initData() {
        //mCateInFragRecy的数据
        for (int i = 0; i < 10; i++) {
            new MyAsyncTask_Auto<CateARecyBean>(CateARecyBean.class, new MyAsyncTask_Auto.DataCallback<CateARecyBean>(){
                @Override
                public void getData(CateARecyBean cateARecyBean) {
                    if (cateARecyBean != null){
                        mRecyList.add(cateARecyBean);
                    }
                    if (mRecyList.size() == 10){
                        adapter.notifyDataSetChanged();
                    }
                }
            }).execute(Constant.getUrl(Constant.PROJECT_ItemId[i],Constant.PROJECT));
        }
    }

    private void initListener() {

    }

    private void initAdapter() {
        adapter = new CateInAFragRecyAdapter(mRecyList,mContext);
        adapterLv=new CateInAFragLvAdapter(channel_groups,mContext);
    }

    private void initRecy() {
        mCateInFragRecy.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mContext, OrientationHelper.HORIZONTAL, false);
        mCateInFragRecy.setLayoutManager(manager);
        mCateInFragRecy.setAdapter(adapter);
    }
    private void initListView() {
        mCateInFragLv.setAdapter(adapterLv);
    }
    //数据下载完成后开始刷新listview界面
    public void upDate(){
        CategoryFragment instance = CategoryFragment.getInstance();
        instance.getInvalidate(new CategoryFragment.Invalidate() {
            @Override
            public void invalidate(int n) {
                if(n==1){
                    adapterLv.notifyDataSetChanged();
                    LogUtils.i("mtg", ">>>回调刷新成功");
                }
            }
        });
    };
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

    @OnClick(R.id.CateInFragTvAll)
    public void onClick(){
        startActivity(new Intent(mContext, CategInAFragTvAllActivity.class));
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
