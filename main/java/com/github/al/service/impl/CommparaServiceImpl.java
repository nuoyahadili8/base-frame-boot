package com.github.al.service.impl;

import com.github.al.dao.CommparaDao;
import com.github.al.service.CommparaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.al.entity.Commpara;

import java.util.List;
import java.util.Map;


@Service("commparaService")
@Transactional
public class CommparaServiceImpl implements CommparaService {
	@Autowired
	private CommparaDao commparaDao;
	
	@Override
	public Commpara queryObject(Integer paraId){
		return commparaDao.queryObject(paraId);
	}
	
	@Override
	public List<Commpara> queryList(Map<String, Object> map){
		return commparaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return commparaDao.queryTotal(map);
	}
	
	@Override
	public void save(Commpara commpara){
		commparaDao.save(commpara);
	}
	
	@Override
	public void update(Commpara commpara){
		commparaDao.update(commpara);
	}
	
	@Override
	public void delete(Integer paraId){
		commparaDao.delete(paraId);
	}
	
	@Override
	public void deleteBatch(Integer[] paraIds){
		commparaDao.deleteBatch(paraIds);
	}

    @Override
    public void updateState(Integer[] ids,String stateValue) {
        for (Integer id:ids){
			Commpara commpara=queryObject(id);
			commpara.setState(stateValue);
            update(commpara);
        }
    }

	@Override
	public List<Commpara> getCodeValues(Map<String, Object> params) {
		return commparaDao.getCodeValues(params);
	}

	@Override
	public List<Commpara> findByVerify(Commpara commpara) {
		return commparaDao.findByVerify(commpara);
	}

}
