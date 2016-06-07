package com.example.administrator.sh1512_5.ModelBean;

import java.util.List;

/**
 * Created by YANG-PC on 2016/5/6.
 */
public class GuidRecyclerFristItemRecyclerBean {

    /**
     * code : 200
     * data : {"secondary_banners":[{"ad_monitors":[],"id":35,"image_url":"http://img02.liwushuo.com/image/160503/jj2y919zo.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=243","webp_url":"http://img02.liwushuo.com/image/160503/jj2y919zo.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":33,"image_url":"http://img02.liwushuo.com/image/160503/ydv76bnsp.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=246","webp_url":"http://img02.liwushuo.com/image/160503/ydv76bnsp.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":36,"image_url":"http://img03.liwushuo.com/image/160503/p3tkow02z.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=247","webp_url":"http://img03.liwushuo.com/image/160503/p3tkow02z.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":39,"image_url":"http://img02.liwushuo.com/image/160503/b2ykz1bqc.jpg-w720","target_url":"liwushuo:///page?type=url&url=http%3A%2F%2Fevent.liwushuo.com%2Fevent%2Ftravel-to-maldives%2Findex.html%3Futm_medium%3Dad%26utm_campaign%3Dmaldives_tour&right_item_callback=Core.share%28%29&right_item_title=","webp_url":"http://img02.liwushuo.com/image/160503/b2ykz1bqc.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":7,"image_url":"http://img01.liwushuo.com/image/160504/318seeot7.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1043032&page_action=navigation","webp_url":"http://img01.liwushuo.com/image/160504/318seeot7.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":32,"image_url":"http://img03.liwushuo.com/image/160503/oe88p5q8w.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=244","webp_url":"http://img03.liwushuo.com/image/160503/oe88p5q8w.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":34,"image_url":"http://img02.liwushuo.com/image/160503/00qo7xn94.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=245","webp_url":"http://img02.liwushuo.com/image/160503/00qo7xn94.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":1,"image_url":"http://img01.liwushuo.com/image/160503/ho57o879q.jpg-w720","target_url":"liwushuo:///page?type=url&url=https%3A%2F%2Fevent.liwushuo.com%2Ftopics%2Fdaily-lucky","webp_url":"http://img01.liwushuo.com/image/160503/ho57o879q.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":38,"image_url":"http://img03.liwushuo.com/image/160504/m9dpqwp1d.jpg-w720","target_url":"liwushuo:///page?type=post&post_id=1043033&page_action=navigation","webp_url":"http://img03.liwushuo.com/image/160504/m9dpqwp1d.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"id":8,"image_url":"http://img03.liwushuo.com/image/160503/u0ix543r8.jpg-w720","target_url":"liwushuo:///page?type=topic&topic_id=172","webp_url":"http://img03.liwushuo.com/image/160503/u0ix543r8.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * id : 35
         * image_url : http://img02.liwushuo.com/image/160503/jj2y919zo.jpg-w720
         * target_url : liwushuo:///page?type=topic&topic_id=243
         * webp_url : http://img02.liwushuo.com/image/160503/jj2y919zo.jpg?imageView2/2/w/720/q/85/format/webp
         */

        private List<SecondaryBannersBean> secondary_banners;

        public List<SecondaryBannersBean> getSecondary_banners() {
            return secondary_banners;
        }

        public void setSecondary_banners(List<SecondaryBannersBean> secondary_banners) {
            this.secondary_banners = secondary_banners;
        }

        public static class SecondaryBannersBean {
            private int id;
            private String image_url;
            private String target_url;
            private String webp_url;
            private List<?> ad_monitors;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }
        }
    }
}
