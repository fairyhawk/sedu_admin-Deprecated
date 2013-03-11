package com.shangde.common.util.messageRemind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.msg.service.MessageImpl;
import com.shangde.edu.msg.service.UserMsgImpl;
import com.shangde.edu.sys.domain.User;
/**
 * 
 * @author juqiang.zhang
 * 
 * for when have new message get here then remind user.
 *
 */
public class MessageRemindUtil {
	private MessageRemindUtil(){}
	
	private static IMessage messageService;//消息操作服务对象
	
	private static IUserMsg userMsgService;//用户提醒消息服务对象
	
	static List<Integer> ids=null;//存放消息ID的集合，批量操作时使用
	/**
	 * @param mrb:封装了发送提醒消息时，需要的一些信息。详见MessageRemindBean属性说明。
	 */
	public static void sendMessageRemind(MessageRemindBean mrb){		
		String text=mrb.getText();
		if(text!=null&&text.length()>32){
			text=text.substring(0,32);
			text+="…";
		}
		Message msg=new Message();
		StringBuffer content=new StringBuffer("");
		content.append(mrb.getUrl());
		if(text!=null)content.append(text).append("</a>]");
		msg.setMsgContent(content.toString());
		msg.setMsgType(mrb.getType());
		msg.setCmtId(mrb.getTid());		
		msg.setRepId(mrb.getRid());
		try{
			int mid=messageService.addMessage(msg);
			//确保信息已插入后，发送
			if(mid>0)userMsgService.adminerSendMsgToCutomer(mrb.getSender(), mid, mrb.getReceiver());
		}catch(Exception ex){}
	}
	
	
	/**
	 * 删除提醒消息，删除回贴时可以用 by zhangjuqiang
	 * @param
	 * type:来自哪个版块。1：建议;2:问答；3：讨论；4：学习计划；5：课程相关；6：平台欢迎词
	 * @param rid:回贴的ID
	 */
	public static void delMessageRemindForReply(int type,int rid){	
		Map args=new HashMap();
		args.put("rid", rid);
		args.put("type", type);
		int msgId=messageService.getMsgIdByRepIdAndType(args);
		ids=new ArrayList<Integer>();
		ids.add(msgId);
		userMsgService.delUserMsgByMsgId(ids);
		messageService.delMessageById(msgId);
	}
	
	/**
	 * 删除提醒消息，删除主贴时可以用 by zhangjuqiang
	 * @param
	 * type:来自哪个版块。1：建议;2:问答；3：讨论；4：学习计划；5：课程相关；6：平台欢迎词
	 * @param tid:主贴的ID
	 */
	public static void delMessageRemindForTopic(int type,int tid){	
		Map args=new HashMap();
		args.put("tid", tid);
		args.put("type", type);
		List<Message> msgs=messageService.getMsgIdByCmtIdAndType(args);
		List<Integer> ids=new ArrayList<Integer>();
		for(Message msg:msgs){
			ids.add(msg.getMsgId());
		}		
		userMsgService.delUserMsgByMsgId(ids);
		messageService.deleteMessageByCmtIdAndType(args);
	}
	/**
	 * @author zhang juqiang
	 * @param receiverId:接收用户的ID
	 * @param type:版块来源标识。1：建议;2:问答；3：讨论；4：学习计划；5：课程相关；6：平台欢迎词
	 * @return 消息的数量
	 */
	public static int getMessageCount(int receiverId,int type){
		Map args=new HashMap();
		args.put("receiverId", receiverId);
		args.put("type", type);
		return messageService.getMsgCountByUserIdAndType(args);
	}
	public IMessage getMessageService() {
		return messageService;
	}
	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}
	public IUserMsg getUserMsgService() {
		return userMsgService;
	}
	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}
	public static void main(String[] args) {
		ApplicationContext appc=new ClassPathXmlApplicationContext("/config/spring/applicationContext.xml");
		//MessageRemindUtil mru=(MessageRemindUtil)appc.getBean("messageRemindUtil");
		System.out.println(MessageRemindUtil.getMessageCount(347, 5));
	}
}
