package com.github.al.common.utils;

import java.util.List;

/**
 * Created by 陈熠
 * 2017/7/7.
 */
public class TreeBean {
    private String id;
    private String name;
    private String parent_id;
    private List<TreeBean>  children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public List<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(List<TreeBean> children) {
        this.children = children;
    }
}
