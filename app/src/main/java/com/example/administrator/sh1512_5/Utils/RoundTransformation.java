package com.example.administrator.sh1512_5.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 16-5-9.
 * 给图片画圆的类
 */
public class RoundTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap bmap) {
        int size = Math.min(bmap.getWidth(), bmap.getHeight());
        int x = (bmap.getWidth() - size) / 2;
        int y = (bmap.getHeight() - size) / 2;

        Bitmap squaredBitmap = Bitmap.createBitmap(bmap, x, y, size, size);
        if (squaredBitmap != bmap) {
            bmap.recycle();
        }
        Bitmap bitmap = Bitmap.createBitmap(size, size, bmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
        BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "Round";
    }
}
