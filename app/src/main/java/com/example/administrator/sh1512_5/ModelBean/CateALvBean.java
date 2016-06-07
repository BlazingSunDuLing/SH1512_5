package com.example.administrator.sh1512_5.ModelBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-5-8.
 */
public class CateALvBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * channels : [{"group_id":2,"icon_url":"http://img01.liwushuo.com/image/160414/j868ssqkl.png-pw144","id":10,"items_count":413,"name":"送女票","order":8,"status":0},{"group_id":2,"icon_url":"http://img02.liwushuo.com/image/160401/56j52lxyq.png-pw144","id":9,"items_count":263,"name":"送男票","order":7,"status":0},{"group_id":2,"icon_url":"http://img03.liwushuo.com/image/160401/9fml1mzye.png-pw144","id":24,"items_count":80,"name":"送宝贝","order":6,"status":0},{"group_id":2,"icon_url":"http://img01.liwushuo.com/image/160401/tsskwt8rq.png-pw144","id":6,"items_count":185,"name":"送爸妈","order":5,"status":0},{"group_id":2,"icon_url":"http://img03.liwushuo.com/image/160401/vq7h2y5ru.png-pw144","id":26,"items_count":560,"name":"送基友","order":5,"status":0},{"group_id":2,"icon_url":"http://img02.liwushuo.com/image/160401/vmdxu2nrw.png-pw144","id":5,"items_count":1023,"name":"送闺蜜","order":4,"status":0},{"group_id":2,"icon_url":"http://img01.liwushuo.com/image/160414/esbe2og0n.png-pw144","id":17,"items_count":75,"name":"送同事","order":2,"status":0},{"group_id":2,"icon_url":"http://img01.liwushuo.com/image/160414/49ludf8iy.png-pw144","id":132,"items_count":11,"name":"新婚夫妇","order":1,"status":0},{"group_id":2,"icon_url":"http://img02.liwushuo.com/image/160414/gglbvbdjd.png-pw144","id":133,"items_count":20,"name":"新手妈妈","order":0,"status":0},{"group_id":2,"icon_url":"http://img03.liwushuo.com/image/160426/7gmz13c47.png-pw144","id":134,"items_count":99,"name":"送自己","order":0,"status":0}]
         * id : 2
         * name : 对象
         * order : 6
         * status : 0
         */

        private List<ChannelGroupsBean> channel_groups;

        public List<ChannelGroupsBean> getChannel_groups() {
            return channel_groups;
        }

        public void setChannel_groups(List<ChannelGroupsBean> channel_groups) {
            this.channel_groups = channel_groups;
        }

        public static class ChannelGroupsBean implements Parcelable{
            private int id;
            private String name;
            private int order;
            private int status;
            private  List<ChannelsBean> channels;

            public ChannelGroupsBean(Parcel in) {
                this.id=in.readInt();
                this.name=in.readString();
                this.order=in.readInt();
                this.status=in.readInt();
                in.readList(channels,ClassLoader.getSystemClassLoader());
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<ChannelsBean> getChannels() {
                return channels;
            }

            public void setChannels(List<ChannelsBean> channels) {
                this.channels = channels;
            }

            public static final Creator<ChannelGroupsBean> CREATOR=new Creator<ChannelGroupsBean>() {
                @Override
                public ChannelGroupsBean createFromParcel(Parcel in) {
                    ChannelGroupsBean bean=new ChannelGroupsBean(in);
                    bean.setName(in.readString());
                    bean.setId(in.readInt());
                    if(bean.channels==null){
                        bean.channels=new ArrayList<>();
                    }
                    in.readList(bean.channels,ChannelsBean.class.getClassLoader());
                    bean.setOrder(in.readInt());
                    bean.setStatus(in.readInt());
                    return bean;
                }

                @Override
                public ChannelGroupsBean[] newArray(int size) {
                    return new ChannelGroupsBean[0];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(order);
                dest.writeInt(status);
                dest.writeList(channels);
            }

            public static class ChannelsBean {
                private int group_id;
                private String icon_url;
                private int id;
                private int items_count;
                private String name;
                private int order;
                private int status;

                public int getGroup_id() {
                    return group_id;
                }

                public void setGroup_id(int group_id) {
                    this.group_id = group_id;
                }

                public String getIcon_url() {
                    return icon_url;
                }

                public void setIcon_url(String icon_url) {
                    this.icon_url = icon_url;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getItems_count() {
                    return items_count;
                }

                public void setItems_count(int items_count) {
                    this.items_count = items_count;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getOrder() {
                    return order;
                }

                public void setOrder(int order) {
                    this.order = order;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

            }
        }
    }
}