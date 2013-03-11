package com.shangde.edu.cus.action;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryAbnormalCustomerCondition;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;
import com.shangde.edu.cus.domain.FreezeLog;
import com.shangde.edu.cus.dto.CustomerFreezeStatusDTO;
import com.shangde.edu.cus.dto.FreezeLogDetialDTO;
import com.shangde.edu.cus.dto.LoginRecordDetailDTO;
import com.shangde.edu.cus.service.IFreezeLog;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sys.domain.User;

/**
 * 异常登录用户的查询，以及冻结/解冻等相关功能的实现。
 * 
 * @author 郑强
 */
public class AbnormalCustomerAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(AbnormalCustomerAction.class);

	// Services.
	private IFreezeLog freezeLogService;
	private IUserMsg userMsgService;
	private IMessage messageService;

	// Conditions.
	private QueryAbnormalCustomerCondition queryAbnormalCusCond;
	private QueryLoginRecordCondition queryLoginRecordCond;

	// DTOs.
	private List<FreezeLogDetialDTO> freezeLogList;
	private List<LoginRecordDetailDTO> loginRecordList;
	private CustomerFreezeStatusDTO cusFreezeStatus;

	private Map<String, String> hourSelect;
	private Integer cusId;
	private Integer[] sendMsgCusIds;

	/** 初始化页面 */
	public String showAbnormalCustomer() {
		try {
			PageResult pr = getPage();
			if (pr == null) {
				pr = new PageResult();
				pr.setCurrentPage(1);
				setPage(pr);
			}
			initHourSelect();
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("AbnormalCustomerAction.showAbnormalCustomer", e);
		}
		return SUCCESS;
	}

	/** 查询异常登录用户 */
	public String searchAbnormalCustomer() {
		try {
			initHourSelect();
			if (queryAbnormalCusCond == null) {
				queryAbnormalCusCond = new QueryAbnormalCustomerCondition();
			}
			if (queryAbnormalCusCond.getEndTime() == null) {
				queryAbnormalCusCond.setEndTime(new Date());
			}
			if (queryAbnormalCusCond.getStartTime() == null) {
				queryAbnormalCusCond.setStartTime(new Date(queryAbnormalCusCond
						.getEndTime().getTime()
						- 1000 * 60 * 60 * 24 * 7));
			}
			if (queryAbnormalCusCond.getAbnormalTimes() == null
					|| queryAbnormalCusCond.getAbnormalTimes() == 0) {
				queryAbnormalCusCond.setAbnormalTimes(3);
			}
			PageResult result = freezeLogService
					.searchAbnormalCustomer(queryAbnormalCusCond);
			setPage(result);
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("AbnormalCustomerAction.searchAbnormalCustomer", e);
		}
		return SUCCESS;
	}

	/** 修改用户冻结状态 */
	public String changeFreezeStatus() {
		try {
			freezeLogService.updateCustomerFreezeStatus(cusFreezeStatus);
			FreezeLog freezeLog = new FreezeLog();
			freezeLog.setCusId(cusFreezeStatus.getCusId());
			freezeLog.setOperationType(cusFreezeStatus.getFreezeStatus());
			freezeLog.setOperator(getLoginedUser().getUserId());
			freezeLogService.saveFreezeLog(freezeLog);
		} catch (Exception e) {
			logger.error("AbnormalCustomerAction.changeFreezeStatus", e);
		}
		return "changeSuccess";
	}

	/** 显示用户的登录日志 */
	public String showLoginDetial() {
		loginRecordList = freezeLogService
				.searchLoginRecordDetail(queryLoginRecordCond);
		return "showLoginDetial";
	}

	/** 显示用户的冻结状态操作日志 */
	public String showFreezeLogDetial() {
		freezeLogList = freezeLogService.searchFreezeLogDetial(cusId);
		return "showFreezeLogDetial";
	}

	/** 向异常登录的用户发送站内信息进行提醒 */
	public String sendUserMsg() {
		try {
			Message message = messageService.getMessageByKey("abnormal_alert");
			if (message == null) {
				Message msg = new Message();
				msg.setCreateTime(new Date());
				msg.setMsgType(5);
				msg.setMsgSort(0);
				msg.setMsgContent("您的帐号近期出现异常登录的情况，为了保障您的合法权益，"
						+ "建议您妥善保管好自己的帐号和密码，并修改密码，谨防他人使用！");
				msg.setMsgTitle("异常登录提醒");
				msg.setSenderType(2);
				msg.setSenderId(1);
				msg.setKeyWord("abnormal_alert");
				messageService.addMessage(msg);
			}
			message = messageService.getMessageByKey("abnormal_alert");
			User user = this.getLoginedUser();
			int userId = user.getUserId();
			String userName = user.getUserName();
			if (sendMsgCusIds != null && sendMsgCusIds.length > 0) {
				for (int i = 0; i < sendMsgCusIds.length; i++) {
					UserMsg userMsg = new UserMsg();
					userMsg.setReceiverId(sendMsgCusIds[i]);
					userMsg.setSendTime(new Date());
					userMsg.setMsgId(message.getMsgId());
					userMsg.setSenderType(UserMsg.SENDER_TYPE_ADMIN);
					userMsg.setSenderId(userId);
					userMsg.setSenderName(userName);
					userMsg.setMsgStatus(0);
					userMsg.setReceiverType(1);
					userMsg.setIsExpired(0);
					userMsg.setMsg(message);
					userMsgService.addUserMsg(userMsg);
				}
			}
		} catch (Exception e) {
			logger.error("AbnormalCustomerAction.sendUserMsg", e);
		}
		return "changeSuccess";
	}

	/** 初始化时间下拉列表 */
	private void initHourSelect() {
		hourSelect = new LinkedHashMap<String, String>();
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 0; i <= 23; i++) {
			String time = df.format(i) + ":00:00";
			hourSelect.put(time, time);
		}
		hourSelect.put("23:59:59", "23:59:59");
	}

	public IFreezeLog getFreezeLogService() {
		return freezeLogService;
	}

	public void setFreezeLogService(IFreezeLog freezeLogService) {
		this.freezeLogService = freezeLogService;
	}

	public QueryAbnormalCustomerCondition getQueryAbnormalCusCond() {
		return queryAbnormalCusCond;
	}

	public void setQueryAbnormalCusCond(
			QueryAbnormalCustomerCondition queryAbnormalCusCond) {
		this.queryAbnormalCusCond = queryAbnormalCusCond;
	}

	public Map<String, String> getHourSelect() {
		return hourSelect;
	}

	public void setHourSelect(Map<String, String> hourSelect) {
		this.hourSelect = hourSelect;
	}

	public CustomerFreezeStatusDTO getCusFreezeStatus() {
		return cusFreezeStatus;
	}

	public void setCusFreezeStatus(CustomerFreezeStatusDTO cusFreezeStatus) {
		this.cusFreezeStatus = cusFreezeStatus;
	}

	public QueryLoginRecordCondition getQueryLoginRecordCond() {
		return queryLoginRecordCond;
	}

	public void setQueryLoginRecordCond(
			QueryLoginRecordCondition queryLoginRecordCond) {
		this.queryLoginRecordCond = queryLoginRecordCond;
	}

	public List<LoginRecordDetailDTO> getLoginRecordList() {
		return loginRecordList;
	}

	public void setLoginRecordList(List<LoginRecordDetailDTO> loginRecordList) {
		this.loginRecordList = loginRecordList;
	}

	public Integer getCusId() {
		return cusId;
	}

	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}

	public List<FreezeLogDetialDTO> getFreezeLogList() {
		return freezeLogList;
	}

	public void setFreezeLogList(List<FreezeLogDetialDTO> freezeLogList) {
		this.freezeLogList = freezeLogList;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public Integer[] getSendMsgCusIds() {
		return sendMsgCusIds;
	}

	public void setSendMsgCusIds(Integer[] sendMsgCusIds) {
		this.sendMsgCusIds = sendMsgCusIds;
	}

}
