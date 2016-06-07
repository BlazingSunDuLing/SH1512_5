package com.example.administrator.sh1512_5.MyView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.sh1512_5.R;


public class MylittleStar extends LinearLayout {
    private ImageView favouriteImg;
    private TextView favouriteNum;
    private boolean isSelect=false; //是否标记为喜欢 默认为不喜欢

    private int starImg;
    private int starNum;
    public MylittleStar(Context context) {
        this(context,null,0);
    }

    public MylittleStar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MylittleStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MylittleStar);
        starImg=typedArray.getResourceId(R.styleable.MylittleStar_starIcon,R.mipmap.ic_action_compact_favourite_normal);
        starNum= (int) typedArray.getInt(R.styleable.MylittleStar_starNum, 0);
        init(context);
        typedArray.recycle();
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_little_star, this, true);
        favouriteImg= (ImageView) view.findViewById(R.id.favouriteImg);
        favouriteNum= (TextView) view.findViewById(R.id.favouriteNum);
        favouriteImg.setImageResource(starImg);
        favouriteNum.setText(starNum + "");
        setListener();
        invalidate();
    }

    public void setStarText(int num) {
        favouriteNum.setText(num+"");
    }
    public String getStarText(){
        return favouriteNum.getText().toString();
    }

    public void setStarIcon(int i) {
        favouriteImg.setImageResource(i);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Drawable getStarImg(){
        return favouriteImg.getResources().getDrawable(starImg,null);
    }
    private void setListener() {
        favouriteImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //喜欢 就执行
                if(!isSelect){
                    favouriteImg.setBackgroundResource(R.mipmap.ic_action_compact_favourite_selected);
                    Toast.makeText(getContext(), ">m<..主人，你喜欢的东西已经为您收好了", Toast.LENGTH_SHORT).show();
                    isSelect=true;
                }
                //不喜欢就执行
                else {
                    favouriteImg.setBackgroundResource(R.mipmap.ic_action_compact_favourite_normal);
                    Toast.makeText(getContext(), ">_<#主人不喜欢的，我都不喜欢！", Toast.LENGTH_SHORT).show();
                    isSelect=false;
                }
            }
        });
    }
}
