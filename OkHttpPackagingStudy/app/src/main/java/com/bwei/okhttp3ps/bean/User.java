package com.bwei.okhttp3ps.bean;

import java.util.List;

/**
 * 1. 类的用途  对象
 * 2. @author forever
 * 3. @date 2017/9/24 19:06
 */

public class User {
    private String result;
    private List<DataInfo> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    public class DataInfo {
        private String TITLE;

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }
    }
}
