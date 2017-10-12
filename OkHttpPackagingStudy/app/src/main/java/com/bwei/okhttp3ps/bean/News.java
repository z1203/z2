package com.bwei.okhttp3ps.bean;

import java.util.List;

/**
 * 1. 类的用途 集合
 * 2. @author forever
 * 3. @date 2017/9/27 10:25
 */

public class News {

    private List<DataInfo> data;

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    public class DataInfo {
        private String img;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
