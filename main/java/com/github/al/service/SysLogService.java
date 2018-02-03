package com.github.al.service;

import com.github.al.entity.SysLog;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService {
	
	SysLog queryObject(Long id);
	
	List<SysLog> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLog sysLog);
	
	void update(SysLog sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
