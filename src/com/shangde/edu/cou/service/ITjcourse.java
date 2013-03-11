package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Tjcourse;
import com.shangde.edu.cou.condition.QueryTjcourseCondition;


/**
 * Tjcourse推荐服务接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface ITjcourse {
    /**
     * 添加推荐课程
     * @param tjcourse 
     * @return id
     */
    public java.lang.Integer addTjcourse(Tjcourse tjcourse);

    /**
     * 删除推荐课程
     * @param id
     */
    public void delTjcourseById(int id);


    /**
     * 跟新推荐课程
     * @param tjcourse
     */
    public void updateTjcourse(Tjcourse tjcourse);

    /**
     * 获取推荐课程根据id查询
     * @param id
     */
    public Tjcourse getTjcourseById(int id);

    /**
     * 获取推荐课程根据查询条件查询
     * @param queryTjcourseCondition
     */
    public List<Tjcourse> getTjcourseList(QueryTjcourseCondition queryTjcourseCondition);
    
    /**
     * 删除推荐课程
     * @param tjcourse
     */
    public void delTjcourseByCourseId(Tjcourse tjcourse);
}