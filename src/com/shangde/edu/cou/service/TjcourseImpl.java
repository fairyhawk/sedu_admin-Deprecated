package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Tjcourse;
import com.shangde.edu.cou.condition.QueryTjcourseCondition;
import com.shangde.common.service.BaseService;

/**
 * Tjcourse推荐课程服务接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class TjcourseImpl extends BaseService implements ITjcourse{
    public java.lang.Integer addTjcourse(Tjcourse tjcourse) {
    	return simpleDao.createEntity("Tjcourse_NS.createTjcourse",tjcourse);
    }

    public void delTjcourseById(int id){
        simpleDao.deleteEntity("Tjcourse_NS.deleteTjcourseById",id);
    }
    
    public void delTjcourseByCourseId(Tjcourse tjcourse){
        simpleDao.deleteEntity("Tjcourse_NS.deleteTjcourseByCourseId",tjcourse);
    }

    public void updateTjcourse(Tjcourse tjcourse) {
        simpleDao.updateEntity("Tjcourse_NS.updateTjcourse",tjcourse);
    }

    public Tjcourse getTjcourseById(int id) {
        return simpleDao.getEntity("Tjcourse_NS.getTjcourseById",id);
    }

    public List<Tjcourse> getTjcourseList(QueryTjcourseCondition queryTjcourseCondition) {
        return simpleDao.getForList("Tjcourse_NS.getTjcourseList",queryTjcourseCondition);
    }
}
