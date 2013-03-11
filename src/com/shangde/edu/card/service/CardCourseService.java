package com.shangde.edu.card.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardCourseCondition;
import com.shangde.edu.card.domain.*;

public interface CardCourseService {
	/**
	 * 保存课程卡
	 * @param couseCard
	 */
	void saveCourseCard(CardMain cardMain,List<Integer> sellIds) throws Exception;
	/**
	 * 查询课程列表
	 * @param queryCardCourseCondition
	 * @return
	 * @throws Exception
	 */
	PageResult getCardCourseList(QueryCardCourseCondition queryCardCourseCondition) throws Exception; 
	/**
	 * 作废课程卡
	 * @param cardCourse
	 * @throws Exception
	 */
	void abolishCardCourse(CardCourse cardCourse) throws Exception ;
	/**
	 * 获取课程卡详细信息
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	CardCourseModel getCardCourseDetail(CardCourse cardCourse) throws Exception ;
	/**
	 * 判断当日过期的课程卡，并进行更新
	 * @throws Exception
	 */
	void updateCardCourseOutDate(CardMain cardMain,CardCourse cardCourse) throws Exception ;
	/**
	 * 获取订单编号列表
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	List<String> getOrderCodeList(CardCourse cardCourse) throws Exception;
	
	/**
	 * 激活课程卡明细信息
	 * @param cardCourse
	 * @throws Exception
	 */
	void activateCardCourse(CardCourse cardCourse) throws Exception;
	
}
