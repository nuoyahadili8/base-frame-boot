package com.github.al.common.enumresource;


import com.github.al.common.utils.EnumMessage;

/**
 * Created by 陈熠
 * 2017/7/20.
 */
public enum SexEnum implements EnumMessage {
    MEN("1","男"),
    WOMEN("2","女"),
    UNKNOWN("0","保密");
    private final String code;
    private final String value;
    private SexEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}
