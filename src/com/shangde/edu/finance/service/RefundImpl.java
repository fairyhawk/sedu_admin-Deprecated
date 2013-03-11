package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryRefundCondition;
import com.shangde.edu.finance.domain.Refund;
import com.shangde.edu.finance.domain.RefundDTO;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能接口实现,客服人员对申请商品退费，编辑退费信息以及撤消商品退费的操作
 *			审核，以及对不满足退费要求的商品进行撤消退费的操作
 *
 * @author		Yangning
 * @date		2011-11-21
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class RefundImpl extends BaseServiceManyDb implements IRefund {

	public Integer addRefund(Refund refund) {
		return simpleDao.createEntity("Refund_NS.addRefund", refund);
	}

	public PageResult getRefundByPage(QueryRefundCondition refund) {
		return simpleDaoRead.getPageResult("Refund_NS.getRefundList", "Refund_NS.getRefundCount", refund);
	}

	public List<RefundDTO> getRefundList(QueryRefundCondition refund) {
		return simpleDao.getForList("Refund_NS.getRefundList", refund);
	}

	public RefundDTO getRefundById(Integer id) {
		return simpleDao.getEntity("Refund_NS.getRefundById", id);
	}

	public Integer updateRufundCancel(Refund refund) {
		// TODO Auto-generated method stub
		int result = simpleDao.update("Refund_NS.cancelRefund", refund);
		return result;
	}

	public Integer updateRefundConfirm(Refund refund) {
		int result = 0;
		/*--是否全额退款如果是则重置订单与流水标记位--*/
		if(refund.getIsfull() == true){
			/*--更改自身状态--*/
			result = simpleDao.update("Refund_NS.confirmRefund", refund);
			/*--更改流水状态--*/
			result += simpleDao.update("CashRecord_NS.updateStatusForRefud", refund.getCashid());
			/*--查看该订单下是否所有商品为已退费，如为已退费则将订单置为已退费--*/
			Integer rcount = simpleDao.getEntity("Refund_NS.getContractCountRefund", refund.getContractno());
			Integer fcount = simpleDao.getEntity("CashRecord_NS.getConCountRefund", refund.getContractno());
			/*--查看是否为货到付款 如为货到付款置订单状态为4,否则为3--*/
			Integer localPayCount = simpleDao.getEntity("Contract_NS.isLocalPay", refund.getContractno());
			if(localPayCount > 0){
				/*--货到付款时取消运费判断--*/
				if(rcount.equals(fcount - 1)){
//					result += simpleDao.update("Contract_NS.updateStatusForRefundLocal", refund.getContractno());
					result += simpleDao.update("Contract_NS.updateStatusForRefund", refund.getContractno());
				}
			}else{
				/*--在线支付订单状态更改--*/
				if(rcount.equals(fcount)){
					result += simpleDao.update("Contract_NS.updateStatusForRefund", refund.getContractno());
				}
			}
		}else{
			/*--更改自身状态--*/
			result = simpleDao.update("Refund_NS.confirmRefund", refund);
		}
		return result;
	}
	
	public RefundDTO getRefundBySellId(Integer id) {
		return simpleDao.getEntity("Refund_NS.getRefundBySellId", id);
	}
	
	public Integer delRefundById(Integer id){
		return simpleDao.delete("Refund_NS.delRefundByid", id);
	}

	public Integer updateRefund(Refund refund) {
		return simpleDao.update("Refund_NS.updateRefund", refund);
	}

	public Boolean getRefundByIdStatus(Refund refund) {
		Integer result = simpleDao.getEntity("Refund_NS.getRefundByStatus", refund);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
}
