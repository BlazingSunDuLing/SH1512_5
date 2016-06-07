package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.sh1512_5.Adapter.SelectRecyclerAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.SelectRecycleBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SelectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectFragment extends Fragment {
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private SelectRecyclerAdapter  mSelectRecyclerAdapter;
    private List<SelectRecycleBean.DataBean> mDatas = new ArrayList<>();
    private List<SelectRecycleBean.DataBean.ItemsBean>mItemsBeans = new ArrayList<>();
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
    private GridLayoutManager mManager;


    public SelectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectFragment newInstance(String param1, String param2) {
        SelectFragment fragment = new SelectFragment();
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
        return inflater.inflate(R.layout.fragment_select, container, false);
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

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_select);

        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE);//设置圆圈大小
        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setProgressBackgroundColor(R.color.colorRed); // 设定下拉圆圈的背景
   /*
   * 监听器SwipeRefreshLayout.OnRefreshListener中的方法，当下拉刷新后触发
   * */
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //停止刷新
                        mSwipeLayout.setRefreshing(false);
                    }
                }, 5000);//五秒后发送消息，停止刷新
            }
        });

        localData(Constant.SELECT_RECYCLER_URL);
        initRecyclView(view);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mLastVisibleItem;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItem = mManager.findLastVisibleItemPosition()+1;//列表项从0开始算故加1
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemCount = mSelectRecyclerAdapter.getItemCount();
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&mLastVisibleItem==itemCount){

                }
            }
        });

    }

    private void initRecyclView(View view) {
       mSelectRecyclerAdapter = new SelectRecyclerAdapter(mContext, mItemsBeans);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycleView_select);
        mManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(mManager);
       mRecyclerView.setAdapter(mSelectRecyclerAdapter);

    }


    private void localData(String url) {
        if (mDatas.size() ==0){
            new MyAsyncTask_Auto<SelectRecycleBean>(SelectRecycleBean.class, new MyAsyncTask_Auto.DataCallback<SelectRecycleBean>() {
                @Override
                public void getData(SelectRecycleBean dataBean) {

                    if (dataBean!=null){
                        for (int i = 0;i<dataBean.getData().getItems().size();i++){
                            mItemsBeans.add(dataBean.getData().getItems().get(i));
                            Log.i("======","================================"+dataBean.getData().getItems());
                        }
                            mSelectRecyclerAdapter.notifyDataSetChanged();
                    }

                }
            }).execute(url);
        }
    }


}
