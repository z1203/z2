package com.bwie.mycartutils.bean;

/**
 * 1. 类的用途
 * 2. @author chensi
 * 3. @date 2017/10/24 19:43
 */

public class GroupBean {
    public String group_name;
    public Boolean isSelected=false;

    public GroupBean() {
    }

    public GroupBean(String group_name, Boolean isSelected) {
        this.group_name = group_name;
        this.isSelected = isSelected;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
