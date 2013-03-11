package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Coursepic;
import com.shangde.edu.cou.condition.QueryCoursepicCondition;
import com.shangde.common.service.BaseService;

/**
 * Coursepic课程图片服务类
 * ICoursepic接口的实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class CoursepicImpl extends BaseService implements ICoursepic{
	/**
     * 添加课程图片
     * @param coursepic 课程图片
     * @return id
     */
    public java.lang.Integer addCoursepic(Coursepic coursepic) {
    	return simpleDao.createEntity("Coursepic_NS.createCoursepic",coursepic);
    }
    
    /**
     * 删除课程图片
     * @param picId
     */
    public void delCoursepicById(int picId){
        simpleDao.deleteEntity("Coursepic_NS.deleteCoursepicById",picId);
    }
    
    /**
     * 更新课程图片޸Coursepic
     * @param coursepic
     */
    public void updateCoursepic(Coursepic coursepic) {
        simpleDao.updateEntity("Coursepic_NS.updateCoursepic",coursepic);
    }
    
    /**
     * 根据课程图片ID获取课程图片
     * @param picId
     * @return
     */
    public Coursepic getCoursepicById(int picId) {
        return simpleDao.getEntity("Coursepic_NS.getCoursepicById",picId);
    }
    
    /**
     * 获取课程图片集合
     * @param queryCoursepicCondition
     * @return 集合
     */
    public List<Coursepic> getCoursepicList(QueryCoursepicCondition queryCoursepicCondition) {
        return simpleDao.getForList("Coursepic_NS.getCoursepicList",queryCoursepicCondition);
    }
    
    /**
     * 获取课程图片主分类
     * @param queryCoursepicCondition
     * @return
     */
    public List<Coursepic> getMyCoursepicList(QueryCoursepicCondition queryCoursepicCondition) {
        return simpleDao.getForList("Coursepic_NS.getMyCoursepicList",queryCoursepicCondition);
    }
    
    /**
     * 按课程名删除课程图片
     * @param courseId
     */
    public void deleteCoursepicByCourseId(int courseId){
    	simpleDao.deleteEntity("Coursepic_NS.deleteCoursepicByCourseId", courseId);
    }
}
