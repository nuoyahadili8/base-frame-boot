package com.github.al.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.al.common.utils.StringUtil;
import com.github.al.dao.AreaDao;
import com.github.al.entity.Area;
import com.github.al.service.AreaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("areaService")
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public Area queryObject(String areaId){
		return areaDao.queryObject(areaId);
	}
	
	@Override
	public List<Area> queryList(Map<String, Object> map){
		return areaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return areaDao.queryTotal(map);
	}
	
	@Override
	public void save(Area area){
		areaDao.save(area);
	}
	
	@Override
	public void update(Area area){
		areaDao.update(area);
	}
	
	@Override
	public void delete(String areaId){
		areaDao.delete(areaId);
	}


	/**
	 * @author chenjiabin
	 * @Description 根据分类ID中转成分类名称串
	 * @param
	 *
	 */
	@Override
	public  String getAreaNameStr(String area_id_str){
		String area_name_str="";
		if(StringUtil.isEmpty(area_id_str)){
			return area_name_str;
		}
		if(area_id_str.indexOf(",")>-1){
			String[] menu_id = area_id_str.split(",");
			for(int i=0;i<menu_id.length;i++){
				area_name_str+=getAreaName(menu_id[i]);
				if(i!=menu_id.length-1){
					area_name_str+=",";
				}
			}
		}else{
			area_name_str+=getAreaName(area_id_str);
		}
		return area_name_str;
	}
	/**
	 * @author chenjiabin
	 * @Description 根据菜单ID获取菜单名称
	 * @param
	 *
	 */
	public  String getAreaName(String areaId){
		String areaName =  "";
		Area area=queryObject(areaId);
		if(area!=null){}
		areaName=area.getAreaName();

		return areaName;
	}
	@Override
	public void deleteBatch(String[] areaIds){
		for(String id:areaIds){
			delete(id);
			//删除下级地区
			deleteSon(id);
		}
		areaDao.deleteBatch(areaIds);
	}

	//删除下级
	private void deleteSon(String pId) {
		List<Area> areaList = findByParentId(pId);
		if (areaList != null) {
			for (Area area:areaList) {
				delete(area.getAreaId());
				//删除下级地区
				deleteSon(area.getAreaId());
			}
		}
	}

	//禁用或启用
	public void updateState(String[] ids,String stateValue) {
		for (String id:ids){
			Area area =queryObject(id);
			area.setState(stateValue);
			update(area);
			//禁用或启用下级
			updateSon(id,stateValue);
		}
	}

	//禁用或启用下级
	private void updateSon(String pId,String stateValue) {
		List<Area> areaList = findByParentId(pId);
		if (areaList != null) {
			for (int i = 0; i < areaList.size(); i++) {
				Area area = areaList.get(i);
				area.setState(stateValue);
				update(area);
				//禁用下级地区
				updateSon(area.getAreaId(),stateValue);
			}
		}
	}

	private List<Area> findByParentId(String pId) {
		return areaDao.findByParentId(pId);
	}

	@Override
	public List<Area> getAreaListByIsShow(HashMap<String, Object> paraMap) {
		return areaDao.getAreaListByIsShow(paraMap);
	}

	@Override
	public int getCount(Map<String, Object> params) {
		return areaDao.getCount(params);
	}

}
