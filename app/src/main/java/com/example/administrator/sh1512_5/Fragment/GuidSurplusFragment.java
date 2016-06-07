package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.administrator.sh1512_5.Adapter.GuidSurplusListAdapter;
import com.example.administrator.sh1512_5.ModelBean.SurplusDataBean;
import com.example.administrator.sh1512_5.R;
import com.example.administrator.sh1512_5.Utils.MyAsyncTask_Auto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GuidSurplusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GuidSurplusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuidSurplusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView mListView;
    private OnFragmentInteractionListener mListener;

    /*自定义变量*/
    private Context mContext;

    private List<SurplusDataBean.DataBean.ItemsBean> mItemsBeans = new ArrayList<>();

    private GuidSurplusListAdapter mAdapter;
    private View mView;
    private String nextUrl;


    public void setUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public GuidSurplusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuidSurplusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GuidSurplusFragment newInstance(String param1, String param2) {
        GuidSurplusFragment fragment = new GuidSurplusFragment();
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
        return inflater.inflate(R.layout.fragment_guid_surplus, container, false);
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
        mView = LayoutInflater.from(mContext).inflate(R.layout.itemtow_guid_infrag_recycler_cardview, null);
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
        if (mItemsBeans.size() == 0) {
            surplusLocalData();//请求数据
        }
        mListView = (ListView) view.findViewById(R.id.GuidFragSurplusListView);
        mListView.setDividerHeight(0);
        mAdapter = new GuidSurplusListAdapter(mItemsBeans, mContext);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //当滑动停止时,当前屏幕显示的最后一个item是否是列表的最后一个
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && view.getLastVisiblePosition() == view.getCount() - 1) {
                    surplusLocalData();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void surplusLocalData() {
        new MyAsyncTask_Auto<SurplusDataBean>(SurplusDataBean.class, new MyAsyncTask_Auto.DataCallback<SurplusDataBean>() {
            @Override
            public void getData(SurplusDataBean surplusDataBean) {
                if (surplusDataBean != null) {
                    for (int i = 0; i < surplusDataBean.getData().getItems().size(); i++) {
                        mItemsBeans.add(surplusDataBean.getData().getItems().get(i));
                    }
                    nextUrl = surplusDataBean.getData().getPaging().getNext_url();
                    mAdapter.notifyDataSetChanged();
                }

            }
        }).execute(nextUrl);
    }
}
