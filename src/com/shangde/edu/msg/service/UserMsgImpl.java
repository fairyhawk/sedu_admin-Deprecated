package com.shangde.edu.msg.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.msg.condition.QueryUserMsgCondition;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.sys.domain.User;


@SuppressWarnings("unchecked")
public class UserMsgImpl extends BaseService implements IUserMsg{
	
    public java.lang.Integer addUserMsg(UserMsg userMsg) {
    	return simpleDao.createEntity("UserMsg_NS.createUserMsg",userMsg);
    }

    public void delUserMsgById(int id){
        simpleDao.deleteEntity("UserMsg_NS.deleteUserMsgById",id);
    }

    public void updateUserMsg(UserMsg userMsg) {
        simpleDao.updateEntity("UserMsg_NS.updateUserMsg",userMsg);
    }

    public UserMsg getUserMsgById(int id) {
        return simpleDao.getEntity("UserMsg_NS.getUserMsgById",id);
    }

    public List<UserMsg> getUserMsgList(QueryUserMsgCondition queryUserMsgCondition) {
        return simpleDao.getForList("UserMsg_NS.getUserMsgList",queryUserMsgCondition);
    }
    
    /**
     * @author cxs
     * 功能：管理员群发信息
     * @param args
     * @param userList
     * @param msgId
     * @param senderId
     * @param senderType
     */
    public void sendMsgs(List<Customer> userList,int msgId,int senderId,String senderName){
    	if(userList != null){
    		UserMsg userMsg = null;
    		for(int i = 0; i < userList.size(); i ++){
    			userMsg = new UserMsg();
    			
    			userMsg.setMsgId(msgId);
    			userMsg.setSenderId(senderId);
    			userMsg.setSenderType(UserMsg.SENDER_TYPE_ADMIN);
    			userMsg.setSenderName(senderName);
    			userMsg.setReceiverId(userList.get(i).getCusId());
    			userMsg.setReceiverName(userList.get(i).getCusName());
    			userMsg.setReceiverType(UserMsg.SENDER_TYPE_CUSTOMER);
    			userMsg.setSendTime(new Date());
    			
    			addUserMsg(userMsg);
    		}
    	}
    }
    
    /**
     * @author chenshuai
     * 功能：网站管理员发送信息给指定网站用户
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void adminerSendMsgToCutomer(User sender,int msgId,Customer receiver){
    	if(sender != null && receiver != null){
    		UserMsg userMsg = null;
			userMsg = new UserMsg();
			
			userMsg.setMsgId(msgId);
			userMsg.setSenderId(sender.getUserId());
			userMsg.setSenderType(UserMsg.SENDER_TYPE_ADMIN);
			userMsg.setSenderName(sender.getUserName());
			
			userMsg.setReceiverId(receiver.getCusId());
			userMsg.setReceiverName(receiver.getCusName());
			userMsg.setReceiverType(UserMsg.SENDER_TYPE_CUSTOMER);
			userMsg.setSendTime(new Date());
			
			addUserMsg(userMsg);
    	}
    }
    
    /**
     * @author chenshuai
     * 功能：网站用户发送信息给指定网站用户
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void customerSendMsgToCutomer(Customer sender,int msgId,Customer receiver){
    	if(sender != null && receiver != null){
    		UserMsg userMsg = null;
			userMsg = new UserMsg();
			
			userMsg.setMsgId(msgId);
			userMsg.setSenderId(sender.getCusId());
			userMsg.setSenderType(UserMsg.SENDER_TYPE_CUSTOMER);
			userMsg.setSenderName(sender.getCusName());
			
			userMsg.setReceiverId(receiver.getCusId());
			userMsg.setReceiverName(receiver.getCusName());
			userMsg.setReceiverType(UserMsg.SENDER_TYPE_CUSTOMER);
			userMsg.setSendTime(new Date());
			
			addUserMsg(userMsg);
    	}
    }
    
    /**
     * @author chenshuai
     * 功能：网站用户发送信息给网站管理员
     * @param user 接收用户
     * @param msgId 消息ID
     * @param senderId 发送者类型
     * @param senderName 发送者姓名
     */
    public void customerSendMsgToAdminer(Customer sender,int msgId,User receiver){
    	if(sender != null && receiver != null){
    		UserMsg userMsg = null;
			userMsg = new UserMsg();
			userMsg.setMsgId(msgId);
			userMsg.setSenderId(sender.getCusId());
			userMsg.setSenderType(UserMsg.SENDER_TYPE_CUSTOMER);
			userMsg.setSenderName(sender.getCusName());
			userMsg.setReceiverId(receiver.getUserId());
			userMsg.setReceiverName(receiver.getUserName());
			userMsg.setReceiverType(UserMsg.SENDER_TYPE_ADMIN);
			userMsg.setSendTime(new Date());
			addUserMsg(userMsg);
    	}
    }

    /**
     * @author chenshuai----modify by zhang juqiang
     * 功能：消息分页管理
     * @param args
     * @param queryMessageCondition
     * @return
     */
    public PageResult getUserMsgListByConditon(QueryUserMsgCondition queryUserMsgCondition) {
		PageResult pr=simpleDao.getPageResult("UserMsg_NS.getUserMsgListByConditon", "UserMsg_NS.getUserMsgListByConditonCount", queryUserMsgCondition);
		List<UserMsg> list =pr.getPageResult();
		
		for(int i=0;list!=null&&i<list.size();i++){
			String originalMsgContent=list.get(i).getMsg().getMsgContent();
			String plainMsgContent=StringUtil.filterHTML(originalMsgContent);
			if(plainMsgContent.length()>60){
				String subMsgContent=StringUtil.chop(plainMsgContent, 50, "...]</a>");
				originalMsgContent.replaceAll(plainMsgContent, subMsgContent);
				list.get(i).getMsg().setMsgContent(originalMsgContent);
				//list.get(i).getMsg().setMsgContent(su.chop(list.get(i).getMsg().getMsgContent(), 60, "...]</a>"));
			}
		}
		return pr;
    }
    
    /**
     * @author chenshuai
     * 功能：根据用户ID及用户信息ID获取消息数量
     * @param args
     * @param queryUserMsgCondition
     * @return
     */
    public int getMsgSizeByUserIdAndUserMsgId(QueryUserMsgCondition queryUserMsgCondition){
    	return simpleDao.getEntity("UserMsg_NS.getMsgSizeByUserIdAndUserMsgId", queryUserMsgCondition);
    }
    
    /**
     * @author chenshuai
     * 功能：获取用户未读信息数量
     * @param args
     * @param userId
     * @return
     */
    public int getCountUnreadMsgs(int userId){
    	return simpleDao.getEntity("UserMsg_NS.getCountUnreadMsgs", userId);
    }
    
    /**
     * @author chenshuai
     * 功能：更新用户已读信息
     * @param args
     * @param userId
     */
    public void updateReadStatus(int userId){
    	simpleDao.updateEntity("UserMsg_NS.updateReadStatus", userId);
    }
    
    /**
     * @author chenshuai
     * 功能：获取第一条平台用户信息
     * @param args
     * @param queryUserMsgCondition
     * @return
     */
    public UserMsg getApplicationUserMsgByConditon(QueryUserMsgCondition queryUserMsgCondition){
    	return simpleDao.getEntity("UserMsg_NS.getApplicationUserMsgByConditon", queryUserMsgCondition);
    }

	public void delUserMsgByMsgId(List<Integer> ids) {
		// TODO Auto-generated method stub
		simpleDao.deleteEntity("UserMsg_NS.deleteUserMsgByMsgId", ids);
	}
}
