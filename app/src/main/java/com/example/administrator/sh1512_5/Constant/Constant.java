package com.example.administrator.sh1512_5.Constant;

import com.example.administrator.sh1512_5.R;

/**
 * Created by Administrator on 16-5-3.
 * 所有常量均写成public静态
 */
public class Constant {
    public static String url = "";
    //MainActivity底部Tab标签的文本
    public static final String[] TAB_BOTTON_TEXT = {"指南", "热门", "分类", "我的"};
    //ProfileFragmen  Tab标签标签的文本
    public static final String[] TAB_BOTTON_TEXT2 = {"礼物","攻略"};
    //MainActivity底部Tab标签的图片点击效果资源
    public static final int[] TAB_BOTTON_ICON = {R.drawable.guidetab, R.drawable.selecttab, R.drawable.categorytab, R.drawable.profiletab};

    /* 第一个fragment的链接*/
    //指南页的Tab标签文本的链接
    public static final String GUID_TABLAYOUT_URL = "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=1";
    //指南页第一种item布局里的viewpager的url
    public static final String GUID_RECYCLER_FRISTITEM_VIEWPAGER_URL = "http://api.liwushuo.com/v2/banners";
    //指南页第一种item布局里recyclerView的url
    public static final String GUID_RECYCLER_FRISTITEM_RECYCLER_URL = "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=1";
    //指南页第二种item布局的url
    public static final String GUID_RECYCLER_TWOITEM_URL = "http://api.liwushuo.com/v2/channels/100/items?limit=20&ad=2&gender=1&offset=0&generation=1";
    //指南页星期
    public static final String [] GUID_RECYCLER_TWOITEM_WEEK = {"周日","周一","周二","周三","周四","周五","周六"};
    /*第二个fragment链接*/
    public   static final  String SELECT_RECYCLER_URL = "http://api.liwushuo.com/v2/items?limit=20&offset=0&gender=1&generation=1";
    public   static final  String SELECT_RECYCLER2_URL = "http://api.liwushuo.com/v2/items?generation=1&gender=1&limit=20&offset=20";
    /*第三个fragment链接*/
    //A.攻略fragment 专题列表项下面
    public static String CategoryUrl1="http://api.liwushuo.com/v2/channel_groups/all";
    //专题列表项系列：
    // 1.完美礼物指南
    public static String CategoryUrl3="http://api.liwushuo.com/v2/collections/241/posts?limit=20&offset=0";
    //2.抹茶福音控
    public static String CategoryUrl4="http://api.liwushuo.com/v2/collections/231/posts?limit=20&offset=0";
    //3.文艺钢笔
    public static String CategoryUrl5="http://api.liwushuo.com/v2/collections/229/posts?limit=20&offset=0";
    //B.礼物fragment
    public static String CategoryUrlB="http://api.liwushuo.com/v2/item_categories/tree";
    //提供ListView里面所需的数据json 先取得"A.攻略fragment 专题列表项下面"数据的itemId再动态获取数据
    public static final int PROJECT=0;
    public static final int ITEM_GV=1;
    public static final int[] PROJECT_ItemId={241,231,229,228,227,226,225,224,223,222};
    public static String getUrl(int ItemId,int which){
        switch (which){
            case Constant.PROJECT:
                return "http://api.liwushuo.com/v2/collections/"+ItemId+"/posts?limit=20&offset=0";
            case Constant.ITEM_GV:
                return "http://api.liwushuo.com/v2/channels/"+ItemId+"/items?limit=20&gender=1&offset=0&generation=2&order_by=now";
        }
        return null;
    }
}
