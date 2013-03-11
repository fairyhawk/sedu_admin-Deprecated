package com.shangde.edu.cus.service;

import java.io.Serializable;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCusProtocalCondition;
import com.shangde.edu.cus.domain.CusProtocalDetail;

public interface ICusProtocalDetail extends Serializable {
   
	public int addCusProtocalDetail (CusProtocalDetail detail);
	
	public int delCusProtocalDetailById (int id);
	
	public CusProtocalDetail getCusProtocalDetailById(int id);
	
	public List<CusProtocalDetail> getCusProtocalDetailList(QueryCusProtocalCondition condition);
	
	public PageResult getCusProtocalDetailPage(QueryCusProtocalCondition condition);
	
}