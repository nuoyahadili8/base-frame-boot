package com.github.al.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StringUtil {

	
	/**
	 * @author chenyi
	 * @Description 首字母转小写
	 * @param
	 * @date 2017-2-16 下午3:40:32
	 */
	public static String toLowerCaseFirstChar(String s){
		if(s==null || s.equals("")) return null;
		return s.replaceFirst(s.substring(0, 1),s.substring(0, 1).toLowerCase()) ;
	}
	
	
	/**
	 * @author chenyi
	 * @Description 首字母转大写
	 * @param
	 * @date 2017-2-16 下午3:40:42
	 */
    public static String toUpCaseFirstChar(String s) {
    	if(s==null || s.equals("")) return null;
		return s.replaceFirst(s.substring(0, 1),s.substring(0, 1).toUpperCase()) ;
    }
    
    public static String fromDateH(Date date) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(date);
    }
	
    /**
	 * @author chenyi
	 * @Description 验证字符串是否为空
	 * @param
	 * @date 2017-2-16 下午3:40:52
	 */
    public static boolean isEmpty(String s){
    	if(s!=null && !s.trim().equals("")){
    		return false;
    	}
    	return true;
    }
    
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }  
    
    /**
 	 * @author chenyi
 	 * @Description 不足位数，左侧补0
 	 * @param
 	 * @date 2017-3-9 上午8:44:07
 	 */
    public static String flushLeft(int length, String content){  
    	char c = '0';
        String str = "";     
        String cs = "";     
        if (content.length() > length){     
             str = content;     
        }else{    
             for (int i = 0; i < length - content.length(); i++){     
                 cs = cs + c;     
             }  
           }  
        str = cs+content;      
        return str;      
    }    
    
    /**
	 * @author chenyi
	 * @Description  左侧补足十位
	 * @param
	 * @date 2017年7月10日 上午11:20:44 
	 */    
    public static String flushTenLeft(String content){
    	return flushLeft(10, content);
    }
    
    /**
	 * @author chenyi
	 * @Description  价格补位 首位1+0000
	 * @param
	 * @date 2017年7月11日 上午11:13:50 
	 */
    public static String flushPrice(String content){
    	return "1"+flushLeft(10, content);
    }
    
    
    /**
	 * @author chenyi
	 * @Description list<string> 转成串，用逗号隔开
	 * @param
	 * @date 2017-3-10 上午10:39:34
	 */
    public static String listToStr(List<String> sList){  
    	String str = "";
    	if(sList!=null && sList.size()>0){
    		for(int i=0;i<sList.size();i++){
    			str+=sList.get(i)+",";
    		}
    		if(str.endsWith(",")){
    			str = str.substring(0,str.length()-1);
    		}
    	}
        return str;      
    }   
    
    /**
	 * @author chenyi
	 * @Description 时间格式去掉最后的.0
	 * @param
	 * @date 2017-5-2 下午4:02:07
	 */
    public static String getStandDate(String date){
    	if(date!=null && date.indexOf(".0")>-1)
    		date = date.replace(".0", "");
    	return date;
    }
    
	public static boolean isBlank(CharSequence cs){
		int len = 0;
		if(cs == null || (len = cs.length()) == 0){
			return true;
		}
		for (int i = 0; i < len; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(flushLeft(10,"aaafaa"));
	}

}
