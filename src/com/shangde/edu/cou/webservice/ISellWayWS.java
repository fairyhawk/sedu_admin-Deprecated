package com.shangde.edu.cou.webservice;

import java.util.List;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.sys.domain.Subject;

/**
 * 
 * @author	caowei
 * @date	2011-8-8
 * @desc
 */
public interface ISellWayWS {

	/**
	 * 获取指定专业名下面的售卖方式
	 * 
	 * @param querySellWayCondition
	 * 			querySellWayCondition.setSubjectId();
	 * @return
	 * 			获取售卖方式的集合
	 */
	public List<SellWay> getSellWayInfoList(int subjectId);

}
