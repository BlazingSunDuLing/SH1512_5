package com.example.administrator.sh1512_5.MyView;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.sh1512_5.R;

/**
 * Created by Administrator on 16-3-30.
 */
public class MyDialogToast extends Dialog {
    public MyDialogToast(Context context) {
        super(context);
    }

    public MyDialogToast(Context context, int themeResId) {
        super(context, themeResId);
        MyCircleIcon v= (MyCircleIcon) findViewById(R.id.IconToastIcon);
    }

    /**
     * 创建一个MyDialogToast的帮助类
     */
    public static class Builder {
        private Context context;
        private String message;
        private View contentView;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         *
         * @param message
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from Resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = context.getText(message).toString();
            return this;
        }

        /**
         * set a custom content view for the Dialog.
         * If a message is set ,the contentView is not added to the Dialog;
         *
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Create a custom dialog
         *
         * @return
         */
        public MyDialogToast create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            MyDialogToast myDialogToast = new MyDialogToast(context, R.style.dialog);
            View view = inflater.inflate(R.layout.my_dialog_toast, null);
            myDialogToast.addContentView(view, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            if (message != null) {
                TextView v = (TextView) view.findViewById(R.id.tvToastMessage);
                v.setText(message);
            }
            myDialogToast.setContentView(view);
            return myDialogToast;
        }
    }

    /**
     * 在自己里面设置一个静态类封装调用自己的方法，便于外类直接调用
     * @param context 传入调用类的context
     * @param str  设置显示的文字
     * @param time 设置显示时间，为防止主线程ANR，所输入的时间大于5000则强制转为4999ms
     */
    public static void Show(String str, int time,Context context) {
        if (time < 5000) {
            Builder builder=new Builder(context);
            builder.setMessage(str);
            final MyDialogToast dialogToast = builder.create();
            dialogToast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialogToast.dismiss();
                }
            }, time);
        }else {
            time=4999;
			Show(str,time,context);
        }
    }
}
