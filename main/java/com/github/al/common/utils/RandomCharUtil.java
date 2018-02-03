package com.github.al.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @function 功能  生成随机标识
 * @author  创建人 林俊钦
 * @date  创建日期  Jun 26, 2011
 */
public class RandomCharUtil {
	
    
    public static void main(String[] args){
    	System.out.println(generateUUID());
    	/*for(int i=0;i<100;i++){
    		System.out.println(getSixNumRand());
    	}*/
    }
    
    /**
	 * @author chenyi
	 * @Description  生成UUID
	 * @param
	 * @date 2016-12-31 下午4:37:16
	 */
    public static String generateUUID(){
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString().replace("-", "");
    }
    

    
    /**
     * 方法描述：生成十位随机字符
     * @return
     */
    public static String getNumberRand(){
    	String tenChar = generateUUID();
    	tenChar = tenChar.substring(0,10);
        return tenChar;
    }
    
    /**
	 * @author chenyi
	 * @Description 随机生成6位数字 
	 * @param
	 * @date 2017-2-10 上午11:22:34
	 */
    public static String getSixNumRand(){
    	String sixNum = String.valueOf(new Random().nextInt(999999));
    	return String.format("%0" + 6 + "d", Integer.parseInt(sixNum));
    }
    
    /**
	 * @author chenyi
	 * @Description  生成订单号规则
	 * @param
	 * @date 2017年7月21日 上午10:32:38 
	 */
    public static final String randOrderNumber() {
        String retStr = "";
        String rand = "";
        //产生随机六位数字
        for (int i = 0; i < 6; i++) {
            int j = (int) (Math.random() * (9 - i)) + i;
            rand = rand + String.valueOf(j);
        }
        //时间算到毫秒
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String regdate;
        regdate = formatter.format(new Date());
        retStr = regdate + rand;
        return retStr;
    }
    
    
}