package com.github.al.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 记录组织机构管理信息
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 17:39:31
 */
public class Organize implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//部门标识
	private String orgId;
	//部门编号
	private String orgCode;
	//部门名称
	private String orgName;
	//上一级部门标识
	private String parentOrgId;
	//排序
	private BigDecimal sortNo;
	//是否显示  0：否 1：是
	private String state;
	//添加时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date inDate;
	private boolean open;

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	//父部门名称
	private String parentOrgName;
	//父部门编号
	private String parentOrgCode;

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
}
