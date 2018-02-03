package com.github.al.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * oss配置
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-12-13 10:07:04
 */
public class SysOss implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**bucket**/
	private String bucket;
	/**访问域名**/
	private String url;
	/**endpoint**/
	private String endpoint;
	/**accessKeyId**/
	private String accessKeyId;
	/**accessKeySecret**/
	private String accessKeySecret;
	/**状态**/
	private String state;
	/**创建时间**/
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 设置：bucket
	 */
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	/**
	 * 获取：bucket
	 */
	public String getBucket() {
		return bucket;
	}
	/**
	 * 设置：访问域名
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：访问域名
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：endpoint
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	/**
	 * 获取：endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}
	/**
	 * 设置：accessKeyId
	 */
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	/**
	 * 获取：accessKeyId
	 */
	public String getAccessKeyId() {
		return accessKeyId;
	}
	/**
	 * 设置：accessKeySecret
	 */
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	/**
	 * 获取：accessKeySecret
	 */
	public String getAccessKeySecret() {
		return accessKeySecret;
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
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
