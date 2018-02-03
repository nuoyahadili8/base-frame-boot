package com.github.al.dao;

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
public interface CommparaDao extends BaseDao<Commpara> {

    List<Commpara> getCodeValues(Map<String, Object> params);

    List<Commpara> findByVerify(Commpara commpara);
}
