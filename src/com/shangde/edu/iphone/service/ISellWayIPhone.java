package com.shangde.edu.iphone.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;

public interface ISellWayIPhone {
	
	/**
	 * 根据当前用户ID  得到用户所包含的售卖方式
	 * @param querySellWayIPhoneCondition
	 * @return 返回售卖方式集合
	 */
	public PageResult getContractSellWayByCusId(QuerySellWayIPhoneCondition querySellWayIPhoneCondition);
	
	
	/**
	 * 为张栋提供售卖方式老师数据
	 */
	public List<Teacher> getTeacherBySellWayId(int sellWayId);

}
