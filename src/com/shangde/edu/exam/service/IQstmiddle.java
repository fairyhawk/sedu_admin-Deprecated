package com.shangde.edu.exam.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.domain.Qstmiddle;

/**
 * Qstmiddle管理接口 User: 何海强 Date: 2011-05-19
 */
public interface IQstmiddle extends Serializable {
	/**
	 * 添加Qstmiddle
	 * 
	 * @param qstmiddle
	 *            要添加的Qstmiddle
	 * @return id
	 */
	public java.lang.Integer addQstmiddle(Qstmiddle qstmiddle);

	/**
	 * 根据id删除一个Qstmiddle
	 * 
	 * @param eqId
	 *            要删除的id
	 */
	public void delQstmiddleById(int eqId);

	/**
	 * 根据条件获取Qstmiddle列表
	 * 
	 * @param queryQstmiddleCondition
	 *            查询条件
	 * @return 查询结果
	 */
	public List<Qstmiddle> getQstmiddleList(
			QueryQstmiddleCondition queryQstmiddleCondition);

	/**
	 * 跳转到试题添加页面时，查询试题信息
	 * @return
	 */
	public List<Qstmiddle> getQstmiddleToAddQstList(QueryQstmiddleCondition queryQstmiddleCondition);
	
	/**
	 * 根据qstid查询Qstmiddle列表
	 */
	public List<Qstmiddle> getQstbyQstid(int qstid);
	
	/**
	 * 根据随机ID生成试题
	 * @return
	 */
	public List<Qstmiddle> getQstmiddleById(List<Integer> idList);
}