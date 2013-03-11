package com.shangde.edu.feed.web.action;

import java.util.Date;

import org.testng.log4testng.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.domain.SubscribeUser;
import com.shangde.edu.feed.service.ISubscribeUser;

/**
 * 
 * 
 * @author Libg
 * 
 */
public class SubscribeUserAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SubscribeUserAction.class);
	
	/** 服务接口 */
	private ISubscribeUser subscribeUserService;

	/** domain接口 */
	private SubscribeUser subscribeUser;
	private String gotoURL;// 跳转地址

	/** 常量 */
	private int id;

	/**
	 * 注册订阅用户
	 * 
	 * 需要提供SubscribeUser.email属性
	 * 
	 * @return
	 */
	public String register() {
		try{
			Date now = new Date();
	
			// 默认值
			subscribeUser.setStatus(0);
			subscribeUser.setPubdate(now);
			subscribeUser.setModified(now);
	
			int id = subscribeUserService.addSubscribeUser(subscribeUser);
	
			if (id > 0) {
				gotoURL = SUCCESS;
			} else {
				gotoURL = ERROR;
			}
		}catch(Exception e){
			logger.error("SubscribeUserAction.register", e);
		}

		return gotoURL;
	}

	public ISubscribeUser getSubscribeUserService() {
		return subscribeUserService;
	}

	public void setSubscribeUserService(ISubscribeUser subscribeUserService) {
		this.subscribeUserService = subscribeUserService;
	}

	public SubscribeUser getSubscribeUser() {
		return subscribeUser;
	}

	public void setSubscribeUser(SubscribeUser subscribeUser) {
		this.subscribeUser = subscribeUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
