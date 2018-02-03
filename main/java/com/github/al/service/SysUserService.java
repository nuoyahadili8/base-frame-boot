package com.github.al.service;

import com.github.al.entity.SysUser;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * 根据用户ID，查询用户
	 * @param userId
	 * @return
	 */
	SysUser queryObject(Long userId);
	
	/**
	 * 查询用户列表
	 */
	List<SysUser> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存用户
	 */
	void save(SysUser user);
	
	/**
	 * 修改用户
	 */
	void update(SysUser user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] userIds);
	
	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	int updatePassword(Long userId, String password, String newPassword);

    void initPassword(Long[] userIds);
}
