package com.shangde.edu.card.service;

import java.util.*;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardMainCondition;
import com.shangde.edu.card.domain.*;

public interface CardMainService {
	void saveCardCourseInfo(CardMain cardMain,List<Integer>sellIds) throws Exception;
	/**
	 * 获取课程卡主信息列表
	 * @param queryCardCourseMainCondition
	 * @return
	 * @throws Exception
	 */
	PageResult getCardCourseMainList(QueryCardMainCondition queryCardMainCondition) throws Exception;
	
	/**
	 * 获取卡基本信息
	 * @param cardMain
	 * @return
	 * @throws Exception
	 */
	CardMainModel getCardMainInfo(CardMain cardMain) throws Exception ;
	/**
	 * 更新卡基本信息
	 * @param cardMain
	 * @throws Exception
	 */
	void updateCardMain(CardMain cardMain) throws Exception ;
	/**
	 * 作废课程基本卡时，必须要作废该基本卡所属的明细卡
	 * @param cardMain
	 * @throws Exception
	 */
	void abolishCardCourseMain (CardMain cardMain) throws Exception;
	/**
	 * 获取当日已过期的卡
	 * @return
	 * @throws Exception
	 */
	List<CardMain> getOutDateCard() throws Exception;
	/**
	 * 激活课程卡
	 * @param cardMain
	 * @throws Exception
	 */
	void activateCardMain(CardMain cardMain,CardCourse cardCourse) throws Exception;
	
}
