package com.shangde.edu.exam.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.dto.QstAddQueryDTO;
import com.shangde.edu.exam.condition.QueryQstCondition;

/**
 * Qst试题
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IQst {
    /**
     * 添加试题
     * @param qst 试题
     * @return id
     */
    public java.lang.Integer addQst(Qst qst);

    /**
     * 通过ID删除试题
     * @param qstId 试题Id
     */
    public void delQstById(int qstId);

    /**
     * 更新试题
     * @param qst 试题
     */
    public void updateQst(Qst qst);

    /**
     * 通过ID获取试题
     * @param qstId 试题ID
     * @return 试题
     */
    public Qst getQstById(int qstId);

    /**
     * 查询试题信息跳转到添加子题
     * @param qstId
     * @return
     */
    public Qst getQstCaiLiaoToAdd(int qstId);
    
    /**
     * 查询试题信息跳转到试题更新页
     * @param qstId
     * @return
     */
    public Qst getQstToUpdateById(int qstId);
    
    /**
     * 根据ID获取试题用过添加试题时 信息记录
     * @author daichangfu
     * @param qstId
     * @return
     */
    public QstAddQueryDTO getQstAddById(int qstId);
    
    /**
     * 根据条件获取试题集合
     * @param queryQstCondition 查询条件
     * @return 试题集合
     */
    public List<Qst> getQstList(QueryQstCondition queryQstCondition);
    
    /**
     * 通过试卷ID获取试题
     * @param epId 试题ID
     * @return 试题
     */
    public List<Qst> getQstByIdList(int epId);
    
    
    /**
     * 分页查询弹框
     */
    public PageResult getQstListPageAll(QueryQstCondition queryQstCondition);
 
    /**
     * 分页查询
     */
    public PageResult getQstListPageAllqst(QueryQstCondition queryQstCondition);

    /**
     * 查询所有的课程
     * @return  liming
     */
	public List<Coursesort> getCourseList();
	
	 /**
     * 根据试题类型获得所有试题 
     * @author 杨海波  2012-07-25 13:29
     * @param type
     * @return
     */
    public List<Qst> getAllQstByType(QueryQstCondition con);
	
}