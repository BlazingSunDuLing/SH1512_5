package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.sh1512_5.Adapter.GuidRecyclerAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.GuidRecyclerFristItemRecyclerBean;
import com.example.administrator.sh1512_5.ModelBean.GuidRecyclerTwoItemBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;
import com.example.administrator.sh1512_5.Utils.SpaceItemDecoration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuidHandpickFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuidHandpickFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuidHandpickFragment extends Fragment {
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
    private List<GuidRecyclerFristItemRecyclerBean.DataBean> mDataBeans = new ArrayList<>();
    private List<GuidRecyclerTwoItemBean.DataBean.ItemsBean> mItemsBeans = new ArrayList<>();
    private ArrayList<List> mArrayList = new ArrayList<>();
    private Date mDate1;
    private Date mDate2;
    private long num1 = 0;
    private long num2 = 0;
    private int way;
    private String nextUrl = null;
    private String twoUrl = Constant.GUID_RECYCLER_TWOITEM_URL;
    private ArrayList<String> mUrls = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GuidRecyclerAdapter mAdapter;
    private final Calendar mCalendar = Calendar.getInstance();
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GuidHandpickFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuidHandpickFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuidHandpickFragment newInstance(String param1, String param2) {
        GuidHandpickFragment fragment = new GuidHandpickFragment();
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
        return inflater.inflate(R.layout.fragment_guid_handpick, container, false);
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
//        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
        //初始化Recycler
        initRecycleView(view);
        //第一个Item里Recycler的网络请求
        localFristItemRecyclerData();
        //第二种Item布局的网络请求

        localTwoItemRecyclerData();

    }

    private void localTwoItemRecyclerData() {

        Log.d("+++adf574+++进入++   ", "=========");
//            long systemTime = System.currentTimeMillis();
        if (mArrayList.size() == 0) {
            localTwoItemData();
        }
    }

    private void localTwoItemData() {
        new MyAsyncTask_Auto<GuidRecyclerTwoItemBean>(GuidRecyclerTwoItemBean.class, new MyAsyncTask_Auto.DataCallback<GuidRecyclerTwoItemBean>() {

            @Override
            public void getData(GuidRecyclerTwoItemBean guidRecyclerTwoItemBean) {
                int num = guidRecyclerTwoItemBean.getData().getItems().size();
                String lastTime;
                String nextTime;
                if (guidRecyclerTwoItemBean != null) {
                    for (int i = 0; i < num; i++) {
                        if (i < num - 1) {
                            num1 = guidRecyclerTwoItemBean.getData().getItems().get(i).getCreated_at();
                            num2 = guidRecyclerTwoItemBean.getData().getItems().get(i + 1).getCreated_at();
                            mDate1 = new Date(num1 * 1000);//在这里,只能先将毫秒值数值赋值给num,然后再用num*1000;原因:搞不懂
                            mDate2 = new Date(num2 * 1000);
                            lastTime = mFormat.format(mDate1);
                            nextTime = mFormat.format(mDate2);
                            if (lastTime.equals(nextTime)) {
                                mItemsBeans.add(guidRecyclerTwoItemBean.getData().getItems().get(i));
                                Log.d("++++++第二层++   ", "lastTime: " + lastTime + "===nextTime: " + nextTime + "=====i++====" + i);
                            } else {
                                Log.d("+++adfer+++第三层++   ", "lastTime: " + lastTime + "===nextTime: " + nextTime + "=====i++====" + i);
//                                    lastTime = nextTime;
                                initItemBeanList(guidRecyclerTwoItemBean, i);//将mItemBeans添加到集合中并初始化mItemBeans集合对象
                            }

                        } else {
                            initItemBeanList(guidRecyclerTwoItemBean, i);
                            notifyData();
                            Log.d("notify", "======mArrayList.size()=" + mArrayList.size());
                        }

                    }

//                                break;
                    Log.d("notify", "======mArrayList.size()=" + mArrayList.get(1).size());

                    nextUrl = guidRecyclerTwoItemBean.getData().getPaging().getNext_url();
                    twoUrl = nextUrl;
                }
            }
        }).execute(twoUrl);
    }


    private void initItemBeanList(GuidRecyclerTwoItemBean guidRecyclerTwoItemBean, int i) {
        mItemsBeans.add(guidRecyclerTwoItemBean.getData().getItems().get(i));
        mArrayList.add(mItemsBeans);
        mItemsBeans = new ArrayList<GuidRecyclerTwoItemBean.DataBean.ItemsBean>();
    }

    private void localFristItemRecyclerData() {
        if (mDataBeans.size() == 0) {
            new MyAsyncTask_Auto<GuidRecyclerFristItemRecyclerBean>(GuidRecyclerFristItemRecyclerBean.class, new MyAsyncTask_Auto.DataCallback<GuidRecyclerFristItemRecyclerBean>() {
                @Override
                public void getData(GuidRecyclerFristItemRecyclerBean guidRecyclerFristItemRecycleBean) {
                    if (guidRecyclerFristItemRecycleBean != null) {
                        mDataBeans.add(guidRecyclerFristItemRecycleBean.getData());
                        for (int i = 0; i < guidRecyclerFristItemRecycleBean.getData().getSecondary_banners().size(); i++) {
                            mUrls.add(guidRecyclerFristItemRecycleBean.getData().getSecondary_banners().get(i).getImage_url());
                            Log.d("GuidFragment", "======" + mUrls.size());
                        }
                        mAdapter.setIMageUrls(mUrls);
//                        notifyData();
                    }
                }
            }).execute(Constant.GUID_RECYCLER_FRISTITEM_RECYCLER_URL);
        }
    }

    private void notifyData() {
        if (mUrls.size() != 0 && mArrayList.size() != 0) {
            mAdapter.notifyDataSetChanged();
            Log.d("执行到此", "====notify======");
        }
    }

    private void initRecycleView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.GuidFragRecycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_item_panding)));//设置列表项间距
        mAdapter = new GuidRecyclerAdapter(mContext, mUrls, mArrayList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int bottom=recyclerView.getBottom();
//                LogUtils.i("mtg",">>>>>>到了集合shangbu"+bottom);
                if(bottom==mArrayList.size()-1&&newState==RecyclerView.SCROLL_STATE_IDLE){
                    localTwoItemData();
//                    LogUtils.i("mtg",">>>>>>到了集合底部");
                }
            }
        });


    }
}
