package com.example.administrator.sh1512_5.Utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by YANG-PC on 2016/5/12.
 *
 * ItemDecoration是设置各个Item样式的东西了。
 * 所以继承重写ItemDecoration就可以实现间距
 * 然后在使用RecyclerView的地方：使用这个类
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildPosition(view) != 0) {
            outRect.top = space;
        }
    }
}
