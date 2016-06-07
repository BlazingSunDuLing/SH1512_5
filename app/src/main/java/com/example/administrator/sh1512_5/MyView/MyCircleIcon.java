package com.example.administrator.sh1512_5.MyView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.administrator.sh1512_5.R;

/**
 * Created by Administrator on 16-3-30.
 */
public class MyCircleIcon extends LinearLayout{
    private int Icon;
    private ImageView myIcon;
    public MyCircleIcon(Context context) {
        this(context,null,0);
    }

    public MyCircleIcon(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyCircleIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.MyCircleIcon);
        Icon=a.getResourceId(R.styleable.MyCircleIcon_Src,R.mipmap.eat_huaji);
        init(context);
        a.recycle();
    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_circle_icon, this, true);
        myIcon = (ImageView) v.findViewById(R.id.myIcon);
        setMyCircleIcon(Icon);
    }


    public void setMyCircleIcon(int Icon) {
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), Icon);
        ProcessBitmap(bitmap);
    }

    public void setMyCircleIconByBitmap(Bitmap bitmap){
        ProcessBitmap(bitmap);
    }

    private void ProcessBitmap(Bitmap bitmap) {
        float radiu;
        Bitmap bmap=Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5);
        Canvas canvas=new Canvas(bmap);
        float cx=bitmap.getWidth();
        float cy=bitmap.getHeight();
        cx=cx/2;
        cy=cy/2;
        if(cx>cy){
            radiu=cy;
        }else {
            radiu=cx;
        }
        canvas.drawCircle(cx, cy, radiu, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        myIcon.setImageBitmap(bmap);
    }
}
