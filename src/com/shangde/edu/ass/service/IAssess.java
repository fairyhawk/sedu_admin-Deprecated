package com.shangde.edu.ass.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.ass.domain.Assess;
import com.shangde.edu.ass.condition.QueryAssessCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.sys.domain.Subject;

/**
 * 
 * User: guoqiang.liu
 * Date: 2011-05-23
 */
public interface IAssess {
	 /**
     *	addAssess
     * @param  插入
     * @return id
     */
    public java.lang.Integer addAssess(Assess assess);

    /**
     * 删除
     */
    public void delAssessById(int assessId);
   
    /**
     *更新 
     *@param Assess assess
     */
    public void updateAssess(Assess assess);

   
    /**
     *根据ID查询
     *@param int assessId
     */
    public Assess getAssessById(int assessId);

    /**
     * 
     * @param 根据条件查询
     * @return List<Assess> 
     */
    public List<Assess> getAssessListByCondition(QueryAssessCondition queryAssessCondition);
    /**
     * 查询未购买专业，右侧导航显示 add by zhangjuqiang
     * @return
     */
    public List<Subject> getUnBuySubject(List<Integer> sids);
    /**
     * 查询用户注册时所选专业，未购买时，知识中心设为默认专业
     */
	public Subject getDefaultSubject(int cusId);
	/**
	 * 查詢用戶購買專業下的未評價的課程知識點
	 */
	public List<Kpoint> getKpointListForUnAss(QueryAssessCondition aCondition);
	
	public List<Subject> getBuySubjectListByCusId(int cusId);
	
	public Subject getCurrSubject(int subId);
	/**
	 * 根评分等级查询知识点数量
	 */
	public int getKpointCountByLevel(QueryAssessCondition aCondition);
	
	public List<String> getWatchedKpointList(int subId,int cusId);
	
	/**
	 * 得到全部恢复，或者按专业ID 课程ID查询
	 * @param queryAssessCondition
	 * @return
	 */
	public List<Assess> getAllAssessList(QueryAssessCondition queryAssessCondition);
	
	/**
	 * 评论分页查询，根据专业  课程 售卖方式 知识点
	 * @param queryAssessCondition
	 * @return
	 */
	public PageResult getPageAssessList(QueryAssessCondition queryAssessCondition);
	
	/**
	 * 后台评论分页查询
	 * @param queryAssessCondition
	 * @return
	 */
	public PageResult getBackPageAssessList(QueryAssessCondition queryAssessCondition);
	
	/**
	 * 得到当前专业  当前节点的  总评论数
	 */
	public int getAssessCount(QueryAssessCondition queryAssessCondition);
	
	/**
	 * 根据用户ID 得到当前用户评论
	 * @param queryAssessCondition
	 * @return
	 */
	public List<Assess> getAssessByCusId(QueryAssessCondition queryAssessCondition);
	
	public List<Integer> getAssKpointIdsByCusId(QueryAssessCondition aCondition);
	/**
	 * 查找知识点所属包，有可能包括在不同的售卖方式中
	 */
	public int getSellWayIdsByKpointId(QueryAssessCondition aCondition);
	//public int getMyAssessCount(QueryAssessCondition condition);
	public PageResult getMoreAssessByCondition(QueryAssessCondition condition);	
	
	public PageResult getMoreKpointListForUnAss(QueryAssessCondition condition);
	
	public int getAssessCountByKpointId(QueryAssessCondition condition);
}