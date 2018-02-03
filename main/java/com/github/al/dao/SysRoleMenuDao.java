package com.github.al.dao;

import com.github.al.entity.SysRoleMenu;

import java.util.List;

/**
 * 角色与菜单对应关系
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:33:46
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu> {
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);
}
