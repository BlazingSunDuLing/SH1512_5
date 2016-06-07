package com.example.administrator.sh1512_5.Utils;

import android.nfc.Tag;
import android.util.Log;

/**
 * 作者：$(USER)  (ˉ(∞)ˉ) on $(DATE) $(HOUR):$(MINUTE)
 * jdk版本：jdk1.7
 * 程序版本：1.0
 */
public class LogUtils {
    private static boolean isDebug=true;
    public static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void j(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void m(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
}
