package com.example.administrator.sh1512_5.MyView;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.sh1512_5.R;

public class MyAnimatorLoading extends LinearLayout {
    private boolean isShow;     //数据是否已加载，若是加载则取消动画
    private int initSrc;
    private ImageView loading;
    private Animator animator;
    private LinearLayout lineLoading;
    private int[] loadeImg={R.mipmap.loading_01,
            R.mipmap.loading_02,R.mipmap.loading_03,
            R.mipmap.loading_04,R.mipmap.loading_05};
    private int temp=0;

    public MyAnimatorLoading(Context context) {
        this(context, null, 0);
    }

    public MyAnimatorLoading(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyAnimatorLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.MyAnimatorLoading);
        isShow= a.getBoolean(R.styleable.MyAnimatorLoading_show_visibility, true);
        Log.i("mtg", "先构造");
        initSrc=a.getResourceId(R.styleable.MyAnimatorLoading_initSrc,R.mipmap.loading_01);
        View v=LayoutInflater.from(context).inflate(R.layout.my_animator_loading, this, true);
        loading= (ImageView) v.findViewById(R.id.loading);
        lineLoading= (LinearLayout) v.findViewById(R.id.lineLoading);
        if(!isShow){
            lineLoading.setVisibility(GONE);
        }else {
            lineLoading.setVisibility(VISIBLE);
            init(context);
        }
        a.recycle();
    }

    private void init(Context context) {
        Log.i("mtg","++++++loadeImg"+loadeImg[1]);

        animator= AnimatorInflater.loadAnimator(context, R.animator.dialog_loading);
        animator.setTarget(loading);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (!isShow) {
                    animation.cancel();
                }
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                loading.setImageResource(loadeImg[temp++%4]);
                Log.i("mtg","><?>?>?>?>?>?"+temp%4);
            }
        });
        animator.start();
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

}