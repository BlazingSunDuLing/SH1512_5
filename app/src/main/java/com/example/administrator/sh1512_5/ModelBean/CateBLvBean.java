package com.example.administrator.sh1512_5.ModelBean;

import java.util.List;

/**
 * Created by Administrator on 16-5-12.
 */
public class CateBLvBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * icon_url : http://img02.liwushuo.com/image/150615/3nc5tejwl.png-pw144
         * id : 1
         * name : 热门分类
         * order : 11
         * status : 0
         * subcategories : [{"icon_url":"http://img01.liwushuo.com/image/150615/urgs9vy8a.png-pw144","id":7,"items_count":-92,"name":"智能设备","order":7,"parent_id":1,"status":0},{"icon_url":"http://img02.liwushuo.com/image/150615/d4dhjw1we.png-pw144","id":6,"items_count":-205,"name":"鲜花蛋糕","order":6,"parent_id":1,"status":0},{"icon_url":"http://img03.liwushuo.com/image/150626/itxjlg4jh.png-pw144","id":5,"items_count":-100,"name":"书 ","order":5,"parent_id":1,"status":0},{"icon_url":"http://img03.liwushuo.com/image/150615/12vqbzrkq.png-pw144","id":4,"items_count":-97,"name":"模型拼图","order":4,"parent_id":1,"status":0},{"icon_url":"http://img03.liwushuo.com/image/150312/xdjtcaanm.png-pw144","id":3,"items_count":-50,"name":"毛绒公仔","order":3,"parent_id":1,"status":0},{"icon_url":"http://img01.liwushuo.com/image/150615/dsu66u5k2.png-pw144","id":2,"items_count":-78,"name":"动漫/明星周边","order":2,"parent_id":1,"status":0},{"icon_url":"http://img02.liwushuo.com/image/150615/v65bdnets.png-pw144","id":1,"items_count":-323,"name":"创意礼品","order":1,"parent_id":1,"status":0}]
         */

        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            private String icon_url;
            private int id;
            private String name;
            private int order;
            /**
             * icon_url : http://img01.liwushuo.com/image/150615/urgs9vy8a.png-pw144
             * id : 7
             * items_count : -92
             * name : 智能设备
             * order : 7
             * parent_id : 1
             * status : 0
             */

            private List<SubcategoriesBean> subcategories;

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

            public List<SubcategoriesBean> getSubcategories() {
                return subcategories;
            }

            public void setSubcategories(List<SubcategoriesBean> subcategories) {
                this.subcategories = subcategories;
            }

            public static class SubcategoriesBean {
                private String icon_url;
                private int id;
                private String name;
                private int order;
                private int parent_id;

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

                public int getParent_id() {
                    return parent_id;
                }

                public void setParent_id(int parent_id) {
                    this.parent_id = parent_id;
                }
            }
        }
    }
}
