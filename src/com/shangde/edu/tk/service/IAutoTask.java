package com.shangde.edu.tk.service;

import java.util.List;

import com.shangde.edu.tk.condition.QueryAutoTaskCondition;
import com.shangde.edu.tk.dto.AutoTaskDTO;


/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.06.05 <br>
 * 
 * @return
 */
public interface IAutoTask {
	
	
	/**
	 * 根据查询条件获取数据(数据为：需要更新subject_id的customer id)
	 * @param queryAutoTaskCondition
	 * @return List<AutoTaskDTO>
	 */
	public List<AutoTaskDTO> getDataList(QueryAutoTaskCondition queryAutoTaskCondition);
	
	/**
	 * 清空临时表
	 */
	public void deleteFinanceSubject();
	
	/**
	 * 临时表(Finance_Subject)插入数据
	 */
	public void insertFinanceSubject(String nowDate);
	
	/**
	 * 更新客户表(cus_customer_tbl)
	 */
	public void updateCostomer();

}
