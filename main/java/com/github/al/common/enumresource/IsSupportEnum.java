package com.github.al.common.enumresource;


import com.github.al.common.utils.EnumMessage;

/**
 * Created by 陈熠
 * 2017/7/20.
 */
public enum IsSupportEnum implements EnumMessage {
    YES("1","支持"),
    NO("0","不支持");
    private final String code;
    private final String value;
    private IsSupportEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}
