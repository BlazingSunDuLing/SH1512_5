package com.example.administrator.sh1512_5.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by YANG-PC on 2016/4/12.
 */
public class MyAsyncTask_Auto<T> extends AsyncTask<String, Void, T> {

    //声明T的class类型类对象，并通过构造方法进行赋值
    Class<T> cls;

    public MyAsyncTask_Auto(Class<T> cls, DataCallback dataCallback) {
        this.cls = cls;
        this.dataCallback = dataCallback;
    }

    @Override
    protected T doInBackground(String... params) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                byte[] b = new byte[1024];
                int num = -1;
                StringBuilder builder = new StringBuilder();
                while ((num = is.read(b)) != -1) {
                    builder.append(new String(b, 0, num));
                }
                LogUtils.i("mtg","》》》json开始——》");
                String json = builder.toString();
                Log.d("+++JSON++", json);
                LogUtils.i("mtg","》》》"+json);
                //使用Gson进行自动解析
                return new Gson().fromJson(json, cls);
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
        dataCallback.getData(t);
    }

    DataCallback dataCallback;

    public interface DataCallback<T> {
        void getData(T t);
    }
}
