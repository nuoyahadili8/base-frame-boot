package com.github.al.controller;


import com.github.al.common.shiro.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.github.al.entity.SysUser;

/**
 * Controller公共组件
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年11月9日 下午9:42:26
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUser getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
