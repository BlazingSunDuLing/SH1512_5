package com.example.administrator.sh1512_5.MyView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.example.administrator.sh1512_5.R;

/**
 * Created by YANG-PC on 2016/5/10.
 */
public class MyGridView extends GridView {
    int column;

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);//获取第一个item的对象
//        int column = getWidth() / localView1.getWidth();//获取有几列
        column = 4;
        int childCount = getChildCount();//获取共有多少个item
        Log.d("====childCount==" + childCount, "++++colunm+++" + column);
        Paint localPaint;
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(getContext().getResources().getColor(R.color.colorGray));
        for (int i = 0; i < childCount; i++) {
            View cellView = getChildAt(i);
            if ((i + 1) % column == 0) {
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            } else if ((i + 1) > (childCount - (childCount % column))) {
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
            } else {
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }
        }
        if (childCount % column != 0) {
            for (int j = 0; j < (column - childCount % column); j++) {
                View lastView = getChildAt(childCount - 1);
                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth() * j, lastView.getBottom(), localPaint);
            }
        }
    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//        View localView1 = getChildAt(0);//获取第一个item的对象
//        int column = getWidth() / localView1.getWidth();//获取有几列
//        int childCount = getChildCount();//获取共有多少个item
//        Log.d("====childCount==" + childCount, "++++colunm+++" + column);
//        Paint localPaint;
//        localPaint = new Paint();
//        localPaint.setStyle(Paint.Style.STROKE);
//        localPaint.setColor(getContext().getResources().getColor(R.color.colorGray));
//        for (int i = 0; i < childCount; i++) {
//            View cellView = getChildAt(i);
//            if ((i + 1) % column == 0) {
//                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
//            } else if ((i + 1) > (childCount - (childCount % column))) {
//                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
//            } else {
//                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
//                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
//            }
//        }
//        if (childCount % column != 0) {
//            for (int j = 0; j < (column - childCount % column); j++) {
//                View lastView = getChildAt(childCount - 1);
//                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth() * j, lastView.getBottom(), localPaint);
//            }
//        }
//    }
}
