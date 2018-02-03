package com.github.al.service;

import com.github.al.entity.Commpara;

import java.util.List;
import java.util.Map;

/**
 * 字典管理
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 14:49:28
 */
public interface CommparaService {
	
	Commpara queryObject(Integer paraId);
	
	List<Commpara> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(Commpara commpara);
	
	void update(Commpara commpara);
	
	void delete(Integer paraId);
	
	void deleteBatch(Integer[] paraIds);

    void updateState(Integer[] ids, String stateValue);

	List<Commpara> getCodeValues(Map<String, Object> params);

    List<Commpara> findByVerify(Commpara commpara);
}
