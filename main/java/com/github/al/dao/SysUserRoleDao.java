package com.github.al.dao;

import org.springframework.stereotype.Repository;
import com.github.al.entity.SysUserRole;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:34:46
 */
@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRole> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
