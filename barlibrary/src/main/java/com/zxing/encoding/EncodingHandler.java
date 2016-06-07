package com.zxing.encoding;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;
/**
 * @author Ryan Tang
 *
 */
public final class EncodingHandler {
	private static final int BLACK = 0xff000000;
	
	public static Bitmap createQRCode(String str,int widthAndHeight) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        //设置解析字符串时使用的字符编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		/*
		* encode 方法参数：
		* 1. 生成图形的数据
		* 2. BarcodeFormat.QR_CODE 生成二维码图形
		* 3,4 生成图形的宽高
		*
		* 返回值用于记录图形的数据信息
		* */
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				//判断在x,y点上是否应该显示某种颜色的图块
				if (matrix.get(x, y)) {
					pixels[y * width + x] = 0xffff0000;
				}

				//如果需要显示多颜色的二维码
//				if (x < width/2) {
//					if (matrix.get(x, y)) {
//					pixels[y * width + x] = 0xffff0000;
//					}
//				} else {
//					if (matrix.get(x, y)) {
//						pixels[y * width + x] = 0xff00ff00;
//					}
//				}
			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		//让bitmap显示显示pixels数组中指定的样式
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}


	//自定义重载方法，用于实现中心带图标的二维码
	public static Bitmap createQRCode(String str,int widthAndHeight,Bitmap center) throws WriterException {
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		//设置解析字符串时使用的字符编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		/*
		* encode 方法参数：
		* 1. 生成图形的数据
		* 2. BarcodeFormat.QR_CODE 生成二维码图形
		* 3,4 生成图形的宽高
		*
		* 返回值用于记录图形的数据信息
		* */
		BitMatrix matrix = new MultiFormatWriter().encode(str,
				BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int[] pixels = new int[width * height];

		int centerw = center.getWidth()/2;
		int centerH = center.getHeight()/2;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				//判断当前x,y点是否位于图片区域
				if (x > width/2-centerw && x < width/2 + centerw
						&& y > height/2 - centerH && y<height/2+centerH){
					pixels[y * width + x] = center.getPixel(x - width/2 + centerw,y - height/2+centerH);
				} else {
					//判断在x,y点上是否应该显示某种颜色的图块
					if (matrix.get(x, y)) {
						pixels[y * width + x] = 0xff000000;
					}
				}


			}
		}
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		//让bitmap显示显示pixels数组中指定的样式
		bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
		return bitmap;
	}


}
