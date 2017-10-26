package com.bwie.mycartutils.bean;

/**
 * 1. 类的用途
 * 2. @author chensi
 * 3. @date 2017/10/24 19:45
 */

public class ChildBean {
    private String title;
    private String price;
    private String path;
    private Boolean isSelected=false;
    private int num;

    public ChildBean() {
    }

    public ChildBean(String title, String price, String path, Boolean isSelected, int num) {
        this.title = title;
        this.price = price;
        this.path = path;
        this.isSelected = isSelected;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
