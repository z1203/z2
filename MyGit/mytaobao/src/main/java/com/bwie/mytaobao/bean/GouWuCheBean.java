package com.bwie.mytaobao.bean;

import java.util.List;

/**
 * Created by 张丹阳 on 2017/10/20.
 */

public class GouWuCheBean {

    /**
     * code : 200
     * datas : {"cart_list":[{"store_id":"1","store_name":"好商城V5","goods":[{"cart_id":"3","buyer_id":"8","store_id":"1","store_name":"好商城V5","goods_id":"100009","goods_name":"劳力士Rolex 日志型系列 116200 63200 自动机械钢带男表联保正品","goods_price":"52800.00","goods_num":"1","goods_image":"1_04752627958339099.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100009","gc_id":"587","transport_id":"0","goods_freight":"0.00","goods_vat":"0","goods_storage":"99","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","groupbuy_info":[],"xianshi_info":[],"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","sole_info":[],"contractlist":[],"goods_image_url":"http://169.254.133.48/data/upload/shop/store/goods/1/1_04752627958339099_240.jpg","goods_total":"52800.00"}]}],"sum":"52800.00","cart_count":1}
     */

    private int code;
    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "GouWuCheBean{" +
                "code=" + code +
                ", datas=" + datas +
                '}';
    }

    public static class DatasBean {
        /**
         * cart_list : [{"store_id":"1","store_name":"好商城V5","goods":[{"cart_id":"3","buyer_id":"8","store_id":"1","store_name":"好商城V5","goods_id":"100009","goods_name":"劳力士Rolex 日志型系列 116200 63200 自动机械钢带男表联保正品","goods_price":"52800.00","goods_num":"1","goods_image":"1_04752627958339099.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100009","gc_id":"587","transport_id":"0","goods_freight":"0.00","goods_vat":"0","goods_storage":"99","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","groupbuy_info":[],"xianshi_info":[],"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","sole_info":[],"contractlist":[],"goods_image_url":"http://169.254.133.48/data/upload/shop/store/goods/1/1_04752627958339099_240.jpg","goods_total":"52800.00"}]}]
         * sum : 52800.00
         * cart_count : 1
         */

        private String sum;
        private int cart_count;
        private List<CartListBean> cart_list;

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public int getCart_count() {
            return cart_count;
        }

        public void setCart_count(int cart_count) {
            this.cart_count = cart_count;
        }

        public List<CartListBean> getCart_list() {
            return cart_list;
        }

        public void setCart_list(List<CartListBean> cart_list) {
            this.cart_list = cart_list;
        }

        @Override
        public String toString() {
            return "DatasBean{" +
                    "sum='" + sum + '\'' +
                    ", cart_count=" + cart_count +
                    ", cart_list=" + cart_list +
                    '}';
        }

        public static class CartListBean {
            /**
             * store_id : 1
             * store_name : 好商城V5
             * goods : [{"cart_id":"3","buyer_id":"8","store_id":"1","store_name":"好商城V5","goods_id":"100009","goods_name":"劳力士Rolex 日志型系列 116200 63200 自动机械钢带男表联保正品","goods_price":"52800.00","goods_num":"1","goods_image":"1_04752627958339099.jpg","bl_id":"0","state":true,"storage_state":true,"goods_commonid":"100009","gc_id":"587","transport_id":"0","goods_freight":"0.00","goods_vat":"0","goods_storage":"99","goods_storage_alarm":"0","is_fcode":"0","have_gift":"0","groupbuy_info":[],"xianshi_info":[],"is_book":"0","book_down_payment":"0.00","book_final_payment":"0.00","book_down_time":"0","is_chain":"0","sole_info":[],"contractlist":[],"goods_image_url":"http://169.254.133.48/data/upload/shop/store/goods/1/1_04752627958339099_240.jpg","goods_total":"52800.00"}]
             */

            private String store_id;
            private String store_name;
            private List<GoodsBean> goods;
            public boolean allCheck = false;

            public boolean isAllCheck() {
                return allCheck;
            }

            public void setAllCheck(boolean allCheck) {
                this.allCheck = allCheck;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            @Override
            public String toString() {
                return "CartListBean{" +
                        "store_id='" + store_id + '\'' +
                        ", store_name='" + store_name + '\'' +
                        ", goods=" + goods +
                        '}';
            }

            public static class GoodsBean {
                /**
                 * cart_id : 3
                 * buyer_id : 8
                 * store_id : 1
                 * store_name : 好商城V5
                 * goods_id : 100009
                 * goods_name : 劳力士Rolex 日志型系列 116200 63200 自动机械钢带男表联保正品
                 * goods_price : 52800.00
                 * goods_num : 1
                 * goods_image : 1_04752627958339099.jpg
                 * bl_id : 0
                 * state : true
                 * storage_state : true
                 * goods_commonid : 100009
                 * gc_id : 587
                 * transport_id : 0
                 * goods_freight : 0.00
                 * goods_vat : 0
                 * goods_storage : 99
                 * goods_storage_alarm : 0
                 * is_fcode : 0
                 * have_gift : 0
                 * groupbuy_info : []
                 * xianshi_info : []
                 * is_book : 0
                 * book_down_payment : 0.00
                 * book_final_payment : 0.00
                 * book_down_time : 0
                 * is_chain : 0
                 * sole_info : []
                 * contractlist : []
                 * goods_image_url : http://169.254.133.48/data/upload/shop/store/goods/1/1_04752627958339099_240.jpg
                 * goods_total : 52800.00
                 */

                private String cart_id;
                private String buyer_id;
                private String store_id;
                private String store_name;
                private String goods_id;
                private String goods_name;
                private String goods_price;
                private String goods_num;
                private String goods_image;
                private String bl_id;
                private boolean state;
                private boolean storage_state;
                private String goods_commonid;
                private String gc_id;
                private String transport_id;
                private String goods_freight;
                private String goods_vat;
                private String goods_storage;
                private String goods_storage_alarm;
                private String is_fcode;
                private String have_gift;
                private String is_book;
                private String book_down_payment;
                private String book_final_payment;
                private String book_down_time;
                private String is_chain;
                private String goods_image_url;
                private String goods_total;
                private List<?> groupbuy_info;
                private List<?> xianshi_info;
                private List<?> sole_info;
                private List<?> contractlist;
                public boolean itemCheck = false;

                public boolean isItemCheck() {
                    return itemCheck;
                }

                public void setItemCheck(boolean itemCheck) {
                    this.itemCheck = itemCheck;
                }

                public String getCart_id() {
                    return cart_id;
                }

                public void setCart_id(String cart_id) {
                    this.cart_id = cart_id;
                }

                public String getBuyer_id() {
                    return buyer_id;
                }

                public void setBuyer_id(String buyer_id) {
                    this.buyer_id = buyer_id;
                }

                public String getStore_id() {
                    return store_id;
                }

                public void setStore_id(String store_id) {
                    this.store_id = store_id;
                }

                public String getStore_name() {
                    return store_name;
                }

                public void setStore_name(String store_name) {
                    this.store_name = store_name;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getBl_id() {
                    return bl_id;
                }

                public void setBl_id(String bl_id) {
                    this.bl_id = bl_id;
                }

                public boolean isState() {
                    return state;
                }

                public void setState(boolean state) {
                    this.state = state;
                }

                public boolean isStorage_state() {
                    return storage_state;
                }

                public void setStorage_state(boolean storage_state) {
                    this.storage_state = storage_state;
                }

                public String getGoods_commonid() {
                    return goods_commonid;
                }

                public void setGoods_commonid(String goods_commonid) {
                    this.goods_commonid = goods_commonid;
                }

                public String getGc_id() {
                    return gc_id;
                }

                public void setGc_id(String gc_id) {
                    this.gc_id = gc_id;
                }

                public String getTransport_id() {
                    return transport_id;
                }

                public void setTransport_id(String transport_id) {
                    this.transport_id = transport_id;
                }

                public String getGoods_freight() {
                    return goods_freight;
                }

                public void setGoods_freight(String goods_freight) {
                    this.goods_freight = goods_freight;
                }

                public String getGoods_vat() {
                    return goods_vat;
                }

                public void setGoods_vat(String goods_vat) {
                    this.goods_vat = goods_vat;
                }

                public String getGoods_storage() {
                    return goods_storage;
                }

                public void setGoods_storage(String goods_storage) {
                    this.goods_storage = goods_storage;
                }

                public String getGoods_storage_alarm() {
                    return goods_storage_alarm;
                }

                public void setGoods_storage_alarm(String goods_storage_alarm) {
                    this.goods_storage_alarm = goods_storage_alarm;
                }

                public String getIs_fcode() {
                    return is_fcode;
                }

                public void setIs_fcode(String is_fcode) {
                    this.is_fcode = is_fcode;
                }

                public String getHave_gift() {
                    return have_gift;
                }

                public void setHave_gift(String have_gift) {
                    this.have_gift = have_gift;
                }

                public String getIs_book() {
                    return is_book;
                }

                public void setIs_book(String is_book) {
                    this.is_book = is_book;
                }

                public String getBook_down_payment() {
                    return book_down_payment;
                }

                public void setBook_down_payment(String book_down_payment) {
                    this.book_down_payment = book_down_payment;
                }

                public String getBook_final_payment() {
                    return book_final_payment;
                }

                public void setBook_final_payment(String book_final_payment) {
                    this.book_final_payment = book_final_payment;
                }

                public String getBook_down_time() {
                    return book_down_time;
                }

                public void setBook_down_time(String book_down_time) {
                    this.book_down_time = book_down_time;
                }

                public String getIs_chain() {
                    return is_chain;
                }

                public void setIs_chain(String is_chain) {
                    this.is_chain = is_chain;
                }

                public String getGoods_image_url() {
                    return goods_image_url;
                }

                public void setGoods_image_url(String goods_image_url) {
                    this.goods_image_url = goods_image_url;
                }

                public String getGoods_total() {
                    return goods_total;
                }

                public void setGoods_total(String goods_total) {
                    this.goods_total = goods_total;
                }

                public List<?> getGroupbuy_info() {
                    return groupbuy_info;
                }

                public void setGroupbuy_info(List<?> groupbuy_info) {
                    this.groupbuy_info = groupbuy_info;
                }

                public List<?> getXianshi_info() {
                    return xianshi_info;
                }

                public void setXianshi_info(List<?> xianshi_info) {
                    this.xianshi_info = xianshi_info;
                }

                public List<?> getSole_info() {
                    return sole_info;
                }

                public void setSole_info(List<?> sole_info) {
                    this.sole_info = sole_info;
                }

                public List<?> getContractlist() {
                    return contractlist;
                }

                public void setContractlist(List<?> contractlist) {
                    this.contractlist = contractlist;
                }

                @Override
                public String toString() {
                    return "GoodsBean{" +
                            "cart_id='" + cart_id + '\'' +
                            ", buyer_id='" + buyer_id + '\'' +
                            ", store_id='" + store_id + '\'' +
                            ", store_name='" + store_name + '\'' +
                            ", goods_id='" + goods_id + '\'' +
                            ", goods_name='" + goods_name + '\'' +
                            ", goods_price='" + goods_price + '\'' +
                            ", goods_num='" + goods_num + '\'' +
                            ", goods_image='" + goods_image + '\'' +
                            ", bl_id='" + bl_id + '\'' +
                            ", state=" + state +
                            ", storage_state=" + storage_state +
                            ", goods_commonid='" + goods_commonid + '\'' +
                            ", gc_id='" + gc_id + '\'' +
                            ", transport_id='" + transport_id + '\'' +
                            ", goods_freight='" + goods_freight + '\'' +
                            ", goods_vat='" + goods_vat + '\'' +
                            ", goods_storage='" + goods_storage + '\'' +
                            ", goods_storage_alarm='" + goods_storage_alarm + '\'' +
                            ", is_fcode='" + is_fcode + '\'' +
                            ", have_gift='" + have_gift + '\'' +
                            ", is_book='" + is_book + '\'' +
                            ", book_down_payment='" + book_down_payment + '\'' +
                            ", book_final_payment='" + book_final_payment + '\'' +
                            ", book_down_time='" + book_down_time + '\'' +
                            ", is_chain='" + is_chain + '\'' +
                            ", goods_image_url='" + goods_image_url + '\'' +
                            ", goods_total='" + goods_total + '\'' +
                            ", groupbuy_info=" + groupbuy_info +
                            ", xianshi_info=" + xianshi_info +
                            ", sole_info=" + sole_info +
                            ", contractlist=" + contractlist +
                            '}';
                }
            }
        }
    }
}
