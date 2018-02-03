package com.github.al.common.utils;

/**
 * Created by 陈熠
 * 2017/7/5.
 * 用于返回前端下拉树、单选款、复选框数据格式
 */
public class SelectBean {
    //参数值
    private String value;
    //参数名
    private String name;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
