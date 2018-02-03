package com.github.al.common.enumresource;

import com.github.al.common.utils.EnumMessage;

/**
 * @Author: chenyi
 * @Email: 228112142@qq.com
 * @Description: 默认密码
 * @Date: 2017/9/2 21:27
 */
public enum DefaultEnum implements EnumMessage {
    PARENT_ACCOUNT("chenyi","默认上级账号"),
    PASSWORD("123456","默认会员密码");
    private final String code;
    private final String value;
    private DefaultEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}