package com.github.al.common.enumresource;


import com.github.al.common.utils.EnumMessage;

/**
 * Created by 陈熠s
 * 2017/7/20.
 */
public enum ProvinceEnum implements EnumMessage {
    NEIMENGGUZIZHIQU("111150000","内蒙古自治区"),
    LIAONINGSHENG("1111210000","辽宁省"),
    JILINSHENG("1111220000","吉林省"),
    HEILONGJIANGSHENG("1111230000","黑龙江省"),
    JIANGSUSHENG("1111320000","江苏省"),
    ZHEJIANGSHENG("1111330000","浙江省"),
    ANHUISHENG("1111340000","安徽省"),
    JIANGXISHENG("1111360000","江西省"),
    SHANDONGSHENG("1111370000","山东省"),
    HENANSHENG("1111410000","河南省"),
    HUNANSHENG("1111430000","湖南省"),
    GUANGDONGSHENG("1111440000","广东省"),
    GUANGXIZHUANGZUZIZHIQU("1111450000","广西壮族自治区"),
    HAINANSHENG("1111460000","海南省"),
    SICHUANSHENG("1111510000","四川省"),
    GUIZHOUSHENG("1111520000","贵州省"),
    YUNNANSHENG("1111530000","云南省"),
    XIZANGZIZHIQU("1111540000","西藏自治区"),
    SHANXISHENG("1111610000","陕西省"),
    GANSUSHENG("1111620000","甘肃省"),
    QINGHAISHENG("1111630000","青海省"),
    NINGXIAHUIZUZIZHIQU("1111640000","宁夏回族自治区"),
    XINJIANGWEIWUERZIZHIQU("1111650000","新疆维吾尔自治区"),
    TIANJIN("2111120000","天津"),
    SHANGHAI("2111310000","上海"),
    CHONGQING("2111500000","重庆"),
    BEI_JING("2111110000","北京"),
    HE_BEI("1111130000","河北省"),
    FU_JIAN("1111350000","福建省");

    private final String code;
    private final String value;
    private ProvinceEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}

