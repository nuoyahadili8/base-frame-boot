package com.github.al.common.utils;

/**
 * Created by chenyi on 2016/12/6.
 */
public class UUID {
    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };
    public static String[] numbers = new String[] {   "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9" };

    public static String getUrl() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
    public static String getCode(int limit) {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = java.util.UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < limit; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }
    /**
     * @author chenyi
     * @Description 随机数字
     * @param
     * @date date2017/11/7
     */
    public static int getNumber(int limit) {
        String max="1";
        for (int i=1;i<limit;i++){
            max+="0";
        }
        return (int)((Math.random()*9+1)*Integer.parseInt(max));

    }
}
