package com.shangde.common.util.messageRemind;

import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.sys.domain.User;
/**
 * 
 * @author zhangjuqiang
 * 
 *
 */
public class MessageRemindBean {
	
	private String url;
	private String text;
	private User sender;
	private int type;
	private int tid;
	private int rid;
	private Customer receiver;
	public String getUrl() {
		return url;
	}
	/**
	 * 
	 * @param url:用户点击提醒消息后，将要链接到的地址。如问答，会跳至对应的我的当前问题的页面。
	 * 注：只需提供链接A标签的开始标签，不需提供结束标签
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getText() {
		return text;
	}
	/**
	 * 
	 * @param text:提醒消息时，要显示的用户所发主题的标题或内容
	 */
	public void setText(String text) {
		this.text = text;
	}
	public User getSender() {
		return sender;
	}
	/**
	 * 
	 * @param sender:消息发送者，一般均可设为“超级管理员”
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}
	public int getType() {
		return type;
	}
	/**
	 * 
	 * @param type:来自哪个版块。1：建议;2:问答；3：讨论；4：学习计划；5：课程相关；6：平台欢迎词
	 */
	public void setType(int type) {
		this.type = type;
	}
	public int getTid() {
		return tid;
	}
	/**
	 * 
	 * @param tid:主贴ID
	 */
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getRid() {
		return rid;
	}
	/**
	 * 
	 * @param rid:回贴ID
	 */
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Customer getReceiver() {
		return receiver;
	}
	/**
	 * 
	 * @param receiver:消息接收者，也即：主题提出者。
	 */
	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}
}
