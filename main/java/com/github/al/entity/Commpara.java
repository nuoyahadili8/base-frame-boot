package com.github.al.entity;

import java.io.Serializable;


/**
 * 字典管理
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 14:49:28
 */
public class Commpara implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer paraId;
	//参数编码
	private String paraCode;
	//参数名称
	private String paraName;
	//参数值
	private String paraKey;
	//排序
	private String sortNo;
	//状态
	private String state;

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 设置：主键
	 */
	public void setParaId(Integer paraId) {
		this.paraId = paraId;
	}
	/**
	 * 获取：主键
	 */
	public Integer getParaId() {
		return paraId;
	}
	/**
	 * 设置：参数编码
	 */
	public void setParaCode(String paraCode) {
		this.paraCode = paraCode;
	}
	/**
	 * 获取：参数编码
	 */
	public String getParaCode() {
		return paraCode;
	}
	/**
	 * 设置：参数名称
	 */
	public void setParaName(String paraName) {
		this.paraName = paraName;
	}
	/**
	 * 获取：参数名称
	 */
	public String getParaName() {
		return paraName;
	}
	/**
	 * 设置：参数值
	 */
	public void setParaKey(String paraKey) {
		this.paraKey = paraKey;
	}
	/**
	 * 获取：参数值
	 */
	public String getParaKey() {
		return paraKey;
	}
	/**
	 * 设置：排序
	 */
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * 获取：排序
	 */
	public String getSortNo() {
		return sortNo;
	}
	/**
	 * 设置：状态
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public String getState() {
		return state;
	}
}
