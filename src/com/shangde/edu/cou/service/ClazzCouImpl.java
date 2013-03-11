package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryClazzCouCondition;
import com.shangde.edu.cou.domain.ClazzCou;

/**
 *
 * User: guoqiang.liu
 * Date: 2010-10-14
 */
@SuppressWarnings("unchecked")
public class ClazzCouImpl extends BaseService implements IClazzCou{
    public java.lang.Integer addClazzCou(ClazzCou clazzCou) {
return simpleDao.createEntity("ClazzCou_NS.createClazzCou",clazzCou);
    }

    public void delClazzCouById(int id){
        simpleDao.deleteEntity("ClazzCou_NS.deleteClazzCouById",id);
    }

    public void updateClazzCou(ClazzCou clazzCou) {
        simpleDao.updateEntity("ClazzCou_NS.updateClazzCou",clazzCou);
    }

    public ClazzCou getClazzCouById(int id) {
        return simpleDao.getEntity("ClazzCou_NS.getClazzCouById",id);
    }

    public List<ClazzCou> getClazzCouList(QueryClazzCouCondition queryClazzCouCondition) {
        return simpleDao.getForList("ClazzCou_NS.getClazzCouList",queryClazzCouCondition);
    }
    
    public PageResult getCourseListByClazzId(QueryClazzCouCondition queryClazzCouCondition){
    	return simpleDao.getPageResult("ClazzCou_NS.getCourseListByClazzId", "ClazzCou_NS.getCourseListByClazzIdCount", queryClazzCouCondition);
    }
    
    public Integer isExistInClazz(QueryClazzCouCondition queryClazzCouCondition){
    //	System.out.println(simpleDao.getEntity("ClazzCou_NS.isExistInClazz", queryClazzCouCondition));
    	return simpleDao.getEntity("ClazzCou_NS.isExistInClazz", queryClazzCouCondition);
    }
    
    public void deleteCourseInClazz(QueryClazzCouCondition queryClazzCouCondition){
    	simpleDao.deleteEntity("ClazzCou_NS.deleteCourseInClazz", queryClazzCouCondition);
    }
    
    public List getClazzCouByCourseId(int courseId){
    	return simpleDao.getForList("ClazzCou_NS.getClazzCouByCourseId", courseId);
    }
}
