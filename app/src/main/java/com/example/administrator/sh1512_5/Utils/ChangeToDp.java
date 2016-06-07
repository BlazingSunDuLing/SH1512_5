package com.example.administrator.sh1512_5.Utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 16-5-4.
 */
public class ChangeToDp {
    public final static  int WIDTH=0;
    public final static  int HEIGHT=1;

    //将输入的数值转换为dp值
    public static float getDp(Context mContext,float dp){
       return mContext.getResources().getSystem().getDisplayMetrics().density*dp;
    }

    //获取当前手机屏幕的宽高(传入context与我自定义的方向静态变量。例：(getDisplayMetrics(mContext,ChangeToDp.WIDTH)))
    public static float getDisplayMetrics(Context context,int direction){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        if(direction==WIDTH){
            return metrics.widthPixels;
        }else {
            return metrics.heightPixels;
        }
    }
}
