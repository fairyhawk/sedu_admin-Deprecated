package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;

/**
 * 用于查询注册用户的课程信息、专业信息
 * 
 * @author Basil
 * 
 */
public interface IDisCourse {
	/**
	 * @author chenshuai 功能：根据用户ID获取用户前台已购买课程集合
	 * @param args
	 * @param cusId
	 *            用户ID
	 * @return
	 */
	public List<UserCenterCourseDTO> getUserCenterCourseListByCusId(
			QueryCourseCondition queryCourseCondition);

	/**
	 * @author liuqinggang 功能：根据用户ID获取用户前台已购包集合
	 * @param args
	 * @param cusId
	 *            用户ID
	 * @return
	 */
	public List<SellWay> getUserCenterSellWayListByCusId(
			QueryCourseCondition queryCourseCondition);

	/** ***V1.1方法(method)声明区域 开始**** */

	/** ***V1.1方法(method)声明区域 结束**** */

}
