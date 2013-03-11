package com.shangde.edu.msg.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.msg.condition.QueryMessageCondition;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sys.domain.User;

public class MsgAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(MsgAction.class);
	private Message msg;//信息
	private String searchKey;//查询条件
	private Map<String,String> msgTypeMap;
	private Map<String,String> msgSortMap;
	private ConfigService configurator;//配置
	private IMessage messageService;//消息服务
	private ICustomer customerService;
	private IUserMsg userMsgService;
	private QueryMessageCondition queryMessageCondition;
	
	private String userName;//用户名
	private int userId;//用户ID

	/**
	 * @author chenshuai
	 * 功能：进入发送信息界面
	 * @param args
	 * @return
	 */
	public String toSendMsg(){
		try{
			msgTypeMap = configurator.getMsgTypeMap();
			msgSortMap = configurator.getMsgSortMap();
		}catch(Exception ex){
			logger.error("MsgAction.toSendMsg", ex);
			return ERROR;
		}
		return "toSendMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：将信息发送给全部用户
	 * @param args
	 * @return
	 */
	public String addMsg(){
		try{
			
			if(msg != null && msg.getMsgType() > 0){
				User user = (User)ActionContext.getContext().getSession().get(CommonAction.SYS_USER_SESSION_NAME);
				final int msgType = msg.getMsgType();
				
				msg.setSenderType(UserMsg.SENDER_TYPE_ADMIN);
				msg.setSenderName(user.getUserName());
				msg.setSenderId(user.getUserId());
				
				final int msgId = messageService.addMessage(msg);//获取消息Id
				
				List<Customer> customerList = null;
				QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
				
				if(msgType == Message.MSG_TYPE_SEND_TO_ALL){//群发给全部
					customerList = customerService.getCustomerList(queryCustomerCondition);
				}else if(msgType == Message.MSG_TYPE_SEND_TO_INNER){//群发给内部学员
					queryCustomerCondition.setCusType("1");
					customerList = customerService.getCustomerList(queryCustomerCondition);
				}else if(msgType == Message.MSG_TYPE_SEND_TO_WEBUSER){//群发给网站正式用户
					queryCustomerCondition.setCusType("0");
					customerList = customerService.getCustomerList(queryCustomerCondition);
				}else if(msgType == Message.MSG_TYPE_SEND_TO_SINGLE){
					if(userId > 0){
						Customer cus = customerService.getCustomerById(userId);
						if(cus != null){
							customerList = new ArrayList();
							customerList.add(cus);
						}
					}
				}
				
				userMsgService.sendMsgs(customerList, msgId, user.getUserId(), user.getUserName());
			}
			
		}catch(Exception ex){
			logger.error("MsgAction.addMsg", ex);
			return ERROR;
		}
		
		return "addMsg";
	}
	
	/**
	 * @author cxs
	 * 功能：消息列表
	 * @param args
	 * @return
	 */
	public String listMsgsByCondition(){
		try{
			if(searchKey != null && !searchKey.trim().equals("")){
				this.getQueryMessageCondition().setSearchKey(searchKey);
			}
			
			this.setPage(messageService.getMsgListByConditon(this.getQueryMessageCondition()));
			this.setPageUrlParms();
		}catch(Exception ex){
			logger.error("MsgAction.listMsgsByCondition", ex);
			return ERROR;
		}
		return "listMsgsByCondition";
	}
	
	/**
	 * @author chenshuai
	 * 功能：进入修改信息
	 * @param args
	 * @return
	 */
	public String toUpdateMsg(){
		try{
			if(msg != null && msg.getMsgId() != 0){
				msg = messageService.getMessageById(msg.getMsgId());
			}
		}catch(Exception ex){
			logger.error("MsgAction.toUpdateMsg", ex);
			return ERROR;
		}
		
		return "toUpdateMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：删除信息
	 * @param args
	 * @return
	 */
	public String deleteMsg(){
		try{
			if(msg != null){
				messageService.delMessageById(msg.getMsgId());
			}
		}catch(Exception ex){
			logger.error("MsgAction.deleteMsg", ex);
			return ERROR;
		}
		return "deleteMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：更新消息
	 * @param args
	 * @return
	 */
	public String updateMsg(){
		try{
			if(msg != null && msg.getMsgId() != 0){
				Message msgtemp = messageService.getMessageById(msg.getMsgId());
				
				msg.setCreateTime(msgtemp.getCreateTime());
				msg.setMsgSort(msgtemp.getMsgSort());
				msg.setMsgType(msgtemp.getMsgType());
				msg.setSenderId(msgtemp.getSenderId());
				msg.setSenderName(msgtemp.getSenderName());
				msg.setSenderType(msgtemp.getSenderType());
				
				messageService.updateMessage(msg);//更新
			}
		}catch(Exception ex){
			logger.error("MsgAction.updateMsg", ex);
			return ERROR;
		}
		
		return "updateMsg";
	}
	
	/**
	 * @author chenshuai
	 * 功能：查看消息
	 * @param args
	 * @return
	 */
	public String viewMsg(){
		try{
			if(msg != null && msg.getMsgId() != 0){
				msg = messageService.getMessageById(msg.getMsgId());
			}
		}catch(Exception ex){
			logger.error("MsgAction.viewMsg", ex);
			return ERROR;
		}
		
		return "viewMsg";
	}
	
	/**
	 * @author cxs
	 * 功能：查询用户
	 * @param args
	 * @return
	 */
	public String searchUserJSON(){
		try{
			List result = null;
			if(userName != null){
				QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
				queryCustomerCondition.setCusName(userName);
				result = customerService.getSimpleCustomerDTOListByCondition(queryCustomerCondition);
			}
			
			JSONArray jslist = JSONArray.fromObject(result);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		}catch(Exception ex){
			logger.error("MsgAction.searchUserJSON", ex);
			return ERROR;
		}
		return "json";
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Message getMsg() {
		return msg;
	}

	public void setMsg(Message msg) {
		this.msg = msg;
	}

	public Map<String, String> getMsgTypeMap() {
		return msgTypeMap;
	}

	public void setMsgTypeMap(Map<String, String> msgTypeMap) {
		this.msgTypeMap = msgTypeMap;
	}
	
	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}
	
	public QueryMessageCondition getQueryMessageCondition() {
		if(queryMessageCondition == null){
			queryMessageCondition = new QueryMessageCondition();
		}
		return queryMessageCondition;
	}

	public void setQueryMessageCondition(QueryMessageCondition queryMessageCondition) {
		this.queryMessageCondition = queryMessageCondition;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<String, String> getMsgSortMap() {
		return msgSortMap;
	}

	public void setMsgSortMap(Map<String, String> msgSortMap) {
		this.msgSortMap = msgSortMap;
	}
}
