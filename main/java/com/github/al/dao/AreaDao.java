package com.github.al.dao;

import com.github.al.entity.Area;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-08-11 10:52:35
 */
public interface AreaDao extends BaseDao<Area> {

    List<Area> getAreaListByIsShow(HashMap<String, Object> paraMap);

    int getCount(Map<String, Object> params);

    List<Area> findByParentId(String pId);
}
