package com.example.administrator.sh1512_5.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode;

/**
 * 作者：$(USER)  (ˉ(∞)ˉ) on $(DATE) $(HOUR):$(MINUTE)
 * jdk版本：jdk1.7
 * 程序版本：1.0
 */
public class SharedPreUtils {
    private static String SHARE_NAME="config"; //SharedPreferences文件名
    private static SharedPreferences sp;
    public static void saveBoolean(Context context,String key,boolean value){
        if(sp==null){
            //创建的文件名+访问模式
            sp=context.getSharedPreferences(SHARE_NAME,context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).commit();
    }
    public static boolean getBooleans(Context context, String key, boolean defValue){
        if(sp==null){
            sp=context.getSharedPreferences(SHARE_NAME,context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
}
