package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryRefundCondition;
import com.shangde.edu.finance.domain.Refund;
import com.shangde.edu.finance.domain.RefundDTO;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能接口,客服人员对申请商品退费，编辑退费信息以及撤消与审核商品退费的操作
 *			                
 *
 * @author		Yangning
 * @date		2011-11-21
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public interface IRefund {
	
	/**
	 * 客服人员写入退费
	 * @param refund
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-21
	 */
	public Integer addRefund(Refund refund);
	
	/**
	 * 客服人员查看数据
	 * @param refund
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-23
	 */
	public PageResult getRefundByPage(QueryRefundCondition refund);
	
	public List<RefundDTO> getRefundList(QueryRefundCondition refund);
	
	/**
	 * 客服人员查看详情
	 * @param id
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-23
	 */
	public RefundDTO getRefundById(Integer id);
	
	/**
	 * 根据流水查退费
	 * @param id
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-24
	 */
	public RefundDTO getRefundBySellId(Integer id);
	
	/**
	 * 员撤销退费
	 * @param id
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-24
	 */
	public Integer updateRufundCancel(Refund refund);
	
	/**
	 * 财务人员确认退费
	 * @param id
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-24
	 */
	public Integer updateRefundConfirm(Refund refund);
	
	/**
	 * 客服取消退费
	 * @param id
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-24
	 */
	public Integer delRefundById(Integer id);

	/**
	 * 客服修改提交 
	 * @param refund
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-24
	 */
	public Integer updateRefund(Refund refund);
	
	/**
	 * 根据状态查找退费记录
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-30
	 */
	public Boolean getRefundByIdStatus(Refund refund);

}
