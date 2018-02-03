package com.github.al.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtil {
	
    
    /**
	 * @author chenyi
	 * @Description 金额元转分
	 * @param
	 * @date 2017-5-17 下午4:31:40
	 */
    public static String amountToBranch(String amount) {
    	if(StringUtil.isEmpty(amount)) return "0";
    	BigDecimal bigAmount = new BigDecimal(amount);
    	BigDecimal divisor = new BigDecimal(100);
    	DecimalFormat df =new DecimalFormat("#0.000"); 
    	String newAmount = df.format(bigAmount.multiply(divisor));
    	//截取两位
    	if(newAmount.length()>1){
    		newAmount = newAmount.substring(0,newAmount.length()-1);
    	}
    	return newAmount;
    }
    
    
	/**
	 * @author chenyi
	 * @Description 金额分转元
	 * @param
	 * @date 2017-5-17 下午4:37:39
	 */
	public static String amountToPrimary(String amount) {
		if(StringUtil.isEmpty(amount)) return "0";
		BigDecimal bigAmount = new BigDecimal(amount);
		BigDecimal divisor = new BigDecimal(100);
		DecimalFormat df =new DecimalFormat("#0.000");
		String newAmount  = df.format(bigAmount.divide(divisor));
		//截取两位
    	if(newAmount.length()>1){
    		newAmount = newAmount.substring(0,newAmount.length()-1);
    	}
		return newAmount;
	}

	
	/**
	 * @author chenyi
	 * @Description  金额分转元，不保留小数
	 * @param
	 * @date 2017年7月18日 下午3:03:49 
	 */
	public static String amountTransferUnit(String amount) {
		if(StringUtil.isEmpty(amount)) return "0";
		BigDecimal bigAmount = new BigDecimal(amount);
		BigDecimal divisor = new BigDecimal(100);
		DecimalFormat df =new DecimalFormat("#0");
		String newAmount  = df.format(bigAmount.divide(divisor));
		return newAmount;
	}
	/**
	 * @author chenyi
	 * @Description 获取金额的百分比积分,
	 * @param  amount 元单位
	 * @param  percentage 百分比0.00-100.00
	 * @date 2017-5-17 下午4:37:39
	 */
	public static  int getInter(String amount,String percentage){
		String regEx ="^(((\\d|[1-9]\\d)(\\.\\d{1,2})?)|100|100.0|100.00)$";
		boolean isMatch = percentage.matches(regEx);
		if(!isMatch){return 0;}
		BigDecimal bigAmount = new BigDecimal(amount);
		DecimalFormat df =new DecimalFormat("#0.00");
		String newAmount =  df.format(bigAmount);
		//截取金额前半部分
		String moneyLeft=newAmount.substring(0,newAmount.length()-3);
		int totalInter=Integer.parseInt(moneyLeft);
		//计算获得积分值
		BigDecimal totalInterValue = new BigDecimal(totalInter);
		BigDecimal percentageValue = new BigDecimal(percentage);
		//乘法
		BigDecimal bigDecimalMultiply = totalInterValue.multiply(percentageValue);
		int multiply = bigDecimalMultiply.intValue();
		int giveInter=multiply/100;
		return giveInter;
	}
    /**
	 * @author chenyi
	 * @Description 金额千分位格视化,
	 * @param  amount 分单位
	 * @date 2017-5-17 下午4:37:39
	 */
    public static String formatMoney(String amount) {
    	if(StringUtil.isEmpty(amount)) return "0";
    	BigDecimal bigAmount = new BigDecimal(amount);
    	BigDecimal divisor = new BigDecimal(100);
    	DecimalFormat df =new DecimalFormat("#0.000");
    	String newAmount =  df.format(bigAmount.divide(divisor));
    	//截取两位
    	if(newAmount.length()>1){
    		newAmount = newAmount.substring(0,newAmount.length()-1);
    	}
		String moneyStr=newAmount;
		//截取金额前半部分
		String moneyLeft=moneyStr.substring(0,moneyStr.length()-3);
		//截取金额后半部分
		String moneyRight=moneyStr.substring(moneyStr.length()-3,moneyStr.length());
		//金额格式化
		String formatMoney=DecimalFormat.getNumberInstance().format(Integer.parseInt(moneyLeft));
    	return formatMoney+moneyRight;
    }

	public static String formatBigDecimal(String amount) {
		if(StringUtil.isEmpty(amount)) return "0";
		BigDecimal  money= new BigDecimal(amount);
		DecimalFormat df =new DecimalFormat("#0.000");
		String newAmount =  df.format(money);
		//截取两位
		if(newAmount.length()>1){
			newAmount = newAmount.substring(0,newAmount.length()-1);
		}
		String moneyStr=newAmount;
		//截取金额前半部分
		String moneyLeft=moneyStr.substring(0,moneyStr.length()-3);
		//截取金额后半部分
		String moneyRight=moneyStr.substring(moneyStr.length()-3,moneyStr.length());
		//金额格式化
		String formatMoney=DecimalFormat.getNumberInstance().format(Integer.parseInt(moneyLeft));
		return formatMoney+moneyRight;
	}

	/**
	 * @author chenyi
	 * @Description 余额格视化
	 * @param
	 * @date 2017-5-17 下午4:37:39
	 *
	 *
	 */
	public static String formatCash(String amount) {
		if(StringUtil.isEmpty(amount)) return "0";
		BigDecimal bigAmount = new BigDecimal(amount);
		BigDecimal divisor = new BigDecimal(1);
		DecimalFormat df =new DecimalFormat("#0.000");
		String newAmount =  df.format(bigAmount.divide(divisor));
		//截取两位
		if(newAmount.length()>1){
			newAmount = newAmount.substring(0,newAmount.length()-1);
		}
		String moneyStr=newAmount;
		//截取金额前半部分
		String moneyLeft=moneyStr.substring(0,moneyStr.length()-3);
		//截取金额后半部分
		String moneyRight=moneyStr.substring(moneyStr.length()-3,moneyStr.length());
		//金额格式化
		String formatMoney=DecimalFormat.getNumberInstance().format(Integer.parseInt(moneyLeft));
		return formatMoney+moneyRight;
	}
	
	/**
	 * @author chenyi
	 * @Description 此方法单位分， 价格进位，例0.01 格式化后1 
	 * @param
	 * @date 2017年9月25日 下午8:50:02 
	 */
	public static String priceCarry(String amount){
		if(amount.length()<3) return "100";//小于一元的，都当一元处理
		//如果尾数是00则不进位
		if(amount.substring(amount.length()-2, amount.length()).equals("00")){
			return amount;
		}else{
			int carryAmount = (Integer.parseInt(amount.substring(0, amount.length()-2))+1)*100;
			return String.valueOf(carryAmount);
		}
	}
	
	public static void main(String[] args){
		
		System.out.println(formatMoney("16700"));
		//System.out.println(amountToBranch("2000012"));
		//System.out.println(amountToPrimary("2000012"));
		//System.out.println(formatMoney("123103300.512"));
		//System.out.println(DecimalFormat.getNumberInstance().format(1245600000));
		//System.out.println(DecimalFormat.getNumberInstance().format(Integer.parseInt(formatMoney("1231000.512"))));
	}
    
    
}
