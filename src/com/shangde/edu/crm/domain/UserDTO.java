package com.shangde.edu.crm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Role;

public class UserDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户id*/
    private int userId;
    /**登录名称*/
    private String loginName;
    /**
     * 姓名
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 机会id
     */
    private int chanceId;
    /**
     * 用户跟踪状态
     */
    private int followStatus;
    private int groupId;
    /**
     * 成单量
     */
    private int payCount;
    /**权限id*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getFollowStatus() {
		return followStatus;
	}
	public void setFollowStatus(int followStatus) {
		this.followStatus = followStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getChanceId() {
		return chanceId;
	}
	public void setChanceId(int chanceId) {
		this.chanceId = chanceId;
	}
	public int getPayCount() {
		return payCount;
	}
	public void setPayCount(int payCount) {
		this.payCount = payCount;
	}
}
