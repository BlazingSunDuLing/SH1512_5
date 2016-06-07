package com.example.administrator.sh1512_5.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.example.administrator.sh1512_5.MainActivity;
import com.example.administrator.sh1512_5.R;
import java.util.ArrayList;
import java.util.List;

public class GuidPagerActivity extends Activity {
    private ViewPager vPager;
    private RadioGroup rg;
    private int[] imgs={R.mipmap.walkthrough_1,R.mipmap.walkthrough_2,R.mipmap.walkthrough_3,R.mipmap.walkthrough_4,};
    private List<View> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guid_pager);
        initView();
        initData();
        initViewpager();
    }

    private void initData() {
        listItem=new ArrayList<>();
        for (int i = 0; i <imgs.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_guid, null);
            ImageView guid_itemImg = (ImageView) view.findViewById(R.id.guid_itemImg);
            guid_itemImg.setImageResource(imgs[i]);
            listItem.add(view);
        }
    }

    private void initViewpager() {
        vPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listItem.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View v=listItem.get(position);
                container.addView(v);
                if(position==listItem.size()-1){
                    ImageButton guidButton= (ImageButton) v.findViewById(R.id.guidBtn);
                    guidButton.setVisibility(View.VISIBLE);
                    guidButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuidPagerActivity.this, MainActivity.class));
                        }
                    });
                }
                return super.instantiateItem(container, position);
            }
        });
    }

    private void initView() {
        vPager= (ViewPager) findViewById(R.id.guidPager);
        rg= (RadioGroup) findViewById(R.id.rg);
    }

}
