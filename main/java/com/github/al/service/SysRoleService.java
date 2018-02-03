package com.github.al.service;

import com.github.al.entity.SysRole;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService {
	
	SysRole queryObject(Long roleId);
	
	List<SysRole> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);

    List<SysRole> findAll(Map<String, Object> params);
}
