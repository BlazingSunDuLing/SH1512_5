package com.example.administrator.sh1512_5.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 16-5-9.
 * 给图片四角画圆角的类
 */
public class RectFTrasformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap bmap) {
        /*int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        Bitmap bmap = bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Canvas canvas=new Canvas(bmap);
        RectF rf=new RectF(0,0,bmap.getWidth(),bitmap.getWidth());
        canvas.drawRoundRect(rf, 80, 80, paint);//改变80，80数值可以改变圆角大小
        canvas.drawBitmap(bmap, 0, 0, paint);
        if(bmap!=bitmap){
            bitmap.recycle();
        }
        return bmap;*/

        int width=bmap.getWidth();
        int height=bmap.getHeight();
        Bitmap squaredBitmap = Bitmap.createBitmap(bmap, 0, 0, width, height);
        if (squaredBitmap != bmap) {
            bmap.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, bmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        RectF rf=new RectF(0,0,width,height);
        canvas.drawRoundRect(rf, 15, 15, paint);//改变15值可改变圆角大小,观察发现值越大,角越大
        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "RectF";
    }
}
