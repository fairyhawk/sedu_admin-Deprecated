package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Clazz;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * Clazz班级功能实现类
 * User: guoqiang.liu
 * Date: 2010-10-14
 */
@SuppressWarnings("unchecked")
public class ClazzImpl extends BaseService implements IClazz{
    public java.lang.Integer addClazz(Clazz clazz) {
    	return simpleDao.createEntity("Clazz_NS.createClazz",clazz);
    }

    public void delClazzById(int clazzId){
        simpleDao.deleteEntity("Clazz_NS.deleteClazzById",clazzId);
    }

    public void updateClazz(Clazz clazz) {
        simpleDao.updateEntity("Clazz_NS.updateClazz",clazz);
    }

    public Clazz getClazzById(int clazzId) {
        return simpleDao.getEntity("Clazz_NS.getClazzById",clazzId);
    }

    public List<Clazz> getClazzList(QueryClazzCondition queryClazzCondition) {
        return simpleDao.getForList("Clazz_NS.getClazzList",queryClazzCondition);
    }

	public PageResult getClazzsListByCondition(QueryClazzCondition queryClazzCondition) {
		return simpleDao.getPageResult("Clazz_NS.getClazzsListByCondition", "Clazz_NS.getClazzsListByConditionCount", queryClazzCondition);
	}
	
	/**
	 * 获取班级for TAG
	 * @param queryClazzCondition
	 * @return
	 */
	public List getClazzListForTag(QueryClazzCondition queryClazzCondition){
		return simpleDao.getForList("Clazz_NS.getClazzListForTag", queryClazzCondition);
	}
	
}
