package com.github.al.entity;

import java.io.Serializable;


/**
 *
 *
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-08-11 10:52:35
 */
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private String areaId;
	//地区名称
	private String areaName;
	//拼音缩写
	private String enName;
	//首字母
	private String wordIndex;
	//上级地区
	private String parentAreaId;

	private String[] parentAreaIds;
	//上级地区名称
	private String parentAreaName;
	//排序
	private Integer sortNo;
	//行政等级
	private Integer areaLevel;
	//0:不是 1:是
	private String isCity;
	//所属区域
	private String region;
	//地区串
	private String levelArea;
	//0：禁用 1：启用
	private String state;
	//邮政编码
	private String postCode;
	//行政编码
	private String xzCode;

	/**
	 * 设置：主键
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	/**
	 * 获取：主键
	 */
	public String getAreaId() {
		return areaId;
	}
	/**
	 * 设置：地区名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	/**
	 * 获取：地区名称
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * 设置：拼音缩写
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}
	/**
	 * 获取：拼音缩写
	 */
	public String getEnName() {
		return enName;
	}
	/**
	 * 设置：首字母
	 */
	public void setWordIndex(String wordIndex) {
		this.wordIndex = wordIndex;
	}
	/**
	 * 获取：首字母
	 */
	public String getWordIndex() {
		return wordIndex;
	}
	/**
	 * 设置：上级地区
	 */
	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
	/**
	 * 获取：上级地区
	 */
	public String getParentAreaId() {
		return parentAreaId;
	}
	/**
	 * 设置：排序
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSortNo() {
		return sortNo;
	}
	/**
	 * 设置：行政等级
	 */
	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}
	/**
	 * 获取：行政等级
	 */
	public Integer getAreaLevel() {
		return areaLevel;
	}
	/**
	 * 设置：0:不是 1:是
	 */
	public void setIsCity(String isCity) {
		this.isCity = isCity;
	}
	/**
	 * 获取：0:不是 1:是
	 */
	public String getIsCity() {
		return isCity;
	}
	/**
	 * 设置：所属区域
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * 获取：所属区域
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * 设置：地区串
	 */
	public void setLevelArea(String levelArea) {
		this.levelArea = levelArea;
	}
	/**
	 * 获取：地区串
	 */
	public String getLevelArea() {
		return levelArea;
	}
	/**
	 * 设置：0：禁用 1：启用
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：0：禁用 1：启用
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：行政编码
	 */
	public void setXzCode(String xzCode) {
		this.xzCode = xzCode;
	}
	/**
	 * 获取：行政编码
	 */
	public String getXzCode() {
		return xzCode;
	}

	public String getParentAreaName() {
		return parentAreaName;
	}

	public void setParentAreaName(String parentAreaName) {
		this.parentAreaName = parentAreaName;
	}

	public String[] getParentAreaIds() {
		return parentAreaIds;
	}

	public void setParentAreaIds(String[] parentAreaIds) {
		this.parentAreaIds = parentAreaIds;
	}
}
