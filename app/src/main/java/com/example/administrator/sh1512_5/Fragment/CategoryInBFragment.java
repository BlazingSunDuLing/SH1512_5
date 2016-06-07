package com.example.administrator.sh1512_5.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.sh1512_5.Adapter.CateInBFragLvRightAdapter;
import com.example.administrator.sh1512_5.Adapter.CateInBFragLvleftAdapter;
import com.example.administrator.sh1512_5.Constant.Constant;
import com.example.administrator.sh1512_5.ModelBean.CateBLvBean;
import com.example.administrator.sh1512_5.MyView.MyAnimatorLoading;
import com.example.administrator.sh1512_5.MyView.MyDialogToast;
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
 * Use the {@link CategoryInBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryInBFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.CateInFragBTvSelect)
    TextView mCateInFragBTvSelect;
    @Bind(R.id.CateInFragBLvLeft)
    ListView mCateInFragBLvLeft;
    @Bind(R.id.CateInFragBLvRight)
    ListView mCateInFragBLvRight;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //自定义变量
    private Context mContext;
    private OnFragmentInteractionListener mListener;
    private CateBLvBean mcateBLvBean;
    private CateInBFragLvleftAdapter adapterLeft;
    private CateInBFragLvRightAdapter adapterRight;
    private List<CateBLvBean.DataBean.CategoriesBean> categoriesList=new ArrayList<>();
    private View lastView;
    private int lastPosition=-1;

    public CategoryInBFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryInBFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryInBFragment newInstance(String param1, String param2) {
        CategoryInBFragment fragment = new CategoryInBFragment();
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
        View v = inflater.inflate(R.layout.fragment_category_in_b, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initAdapter();
        initData();
        initListener();
        setListView();
    }

    private void setListView() {
        mCateInFragBLvLeft.setAdapter(adapterLeft);
        mCateInFragBLvRight.setAdapter(adapterRight);
    }

    private void initView() {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_b_lv_left,null,false);
        isSelect(view,true,0);
    }

    private void initData() {
        if (mcateBLvBean == null) {
            new MyAsyncTask_Auto<CateBLvBean>(CateBLvBean.class, new MyAsyncTask_Auto.DataCallback<CateBLvBean>() {

                @Override
                public void getData(CateBLvBean cateBLvBean) {
                    mcateBLvBean = cateBLvBean;
                    if(mcateBLvBean!=null){
                        categoriesList.addAll(cateBLvBean.getData().getCategories());
                        adapterLeft.notifyDataSetChanged();
                        adapterRight.notifyDataSetChanged();
                        LogUtils.i("mtg"," B:下载完毕>"+categoriesList);
                    }
                }
            }).execute(Constant.CategoryUrlB);
        }
    }

    private void initAdapter() {
        adapterLeft = new CateInBFragLvleftAdapter(mContext,categoriesList);
        adapterRight = new CateInBFragLvRightAdapter(mContext,categoriesList);
    }

    private void initListener() {
        mCateInFragBLvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int visiblePosition = mCateInFragBLvLeft.getFirstVisiblePosition();
                int lastVisiblePosition = mCateInFragBLvLeft.getLastVisiblePosition();
                if (position >= visiblePosition && position <= lastVisiblePosition) {
                    if (lastView != null) {
                        isSelect(lastView, false, lastPosition);
                    }
                    isSelect(view, true, position);
                    lastView = view;
                    lastPosition = position;
                }
                LogUtils.i("mtg", "第一position:" + visiblePosition + "|*:*|最后position:" + lastVisiblePosition);
            }
        });
        mCateInFragBLvLeft.setOnScrollListener(new AbsListView.OnScrollListener() {
            int visiblePosition = mCateInFragBLvLeft.getFirstVisiblePosition();
            int lastVisiblePosition = mCateInFragBLvLeft.getLastVisiblePosition();

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        //reSetSelect();
                        break;
                }
            }

            /*恢复点击状态*/
            private void reSetSelect() {
                View view = mCateInFragBLvLeft.getChildAt(lastPosition);
                isSelect(view, true, lastPosition);
                LogUtils.i("mtg", ")))))>重新设置点击中...");
                LogUtils.i("mtg", "执行了滚动" + lastPosition + " 可见第一个" + visiblePosition + " 可见最后" + lastVisiblePosition);
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mCateInFragBLvRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            int checkPosition;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                View v=LayoutInflater.from(mContext).inflate(R.layout.item_cate_infrag_b_lv_left,null);
                checkPosition = view.getFirstVisiblePosition();
                TextView bg = (TextView) v.findViewById(R.id.mCateInFragBLvLeftTvBg);
                TextView leftTv= (TextView) v.findViewById(R.id.mCateInFragBLvLeftTv);
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        mCateInFragBLvLeft.setSelection(checkPosition);
                        leftTv.setTextColor(Color.RED);
                        leftTv.setBackgroundColor(Color.WHITE);
                        bg.setVisibility(View.VISIBLE);
                        LogUtils.i("mtg",">>右边的position"+checkPosition);
                        break;
                }
                mCateInFragBLvLeft.setSelection(checkPosition);
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        mCateInFragBTvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialogToast.Show("正在跳转中...", 3000, mContext);
            }
        });
    }
    /*左侧list是否设置点击*/
    private void isSelect(View v,Boolean is,int position) {
        TextView bg = (TextView) v.findViewById(R.id.mCateInFragBLvLeftTvBg);
        TextView leftTv= (TextView) v.findViewById(R.id.mCateInFragBLvLeftTv);
        if(is==true){
            mCateInFragBLvLeft.setItemChecked(position,true);
            leftTv.setTextColor(Color.RED);
            leftTv.setBackgroundColor(Color.WHITE);
            bg.setVisibility(View.VISIBLE);
            if(position>0){
                mCateInFragBLvRight.setSelection(position);
            }else if(position==0){
                mCateInFragBLvRight.setSelection(position);
            }
            LogUtils.i("mtg",">>大坏蛋");
        }else{
            leftTv.setTextColor(Color.BLACK);
            leftTv.setBackgroundColor(getResources().getColor(R.color.colorsoLightGray));
            bg.setVisibility(View.GONE);
            mCateInFragBLvLeft.setItemChecked(position, false);
        }
    }
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
