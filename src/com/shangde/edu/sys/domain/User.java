package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	private static final long serialVersionUID = 4773758518239195423L;
	public static int  USER_DEFAULT_STATUS = 0;
	public static int  USER_FREEZE_STATUS = 1;
	public static int  USER_DELETE_STATUS = 2;
	public static int  USER_STU = 0;
	public static int  USER_TECH = 1;
	/**用户id*/
    private int userId;
    /**登录名称*/
    private String loginName;
    /**登录密码*/
    private String loginPwd;
    /**用户姓名*/
    private String userName;
    /**用户性别id*/
    private int userTypeId;
    /**状态*/
    private int status;
    /**最后登录时间*/
    private java.util.Date lastLoginTime;
    /**最后登录ip*/
    private String lastLoginIp;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
    private String email;
    private String tel;
    private Group group;
    private String userContentRole;
    private List<Role> roleList = new ArrayList<Role>();
    private int userType;
    private String address;
    private String zip;
    
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}


	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	private int groupId;
        
    public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public String getLoginName(){
        return loginName;
    }

    public void setLoginName(String loginName){
        this.loginName = loginName; 
    }
        
    public String getLoginPwd(){
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd){
        this.loginPwd = loginPwd; 
    }
        
    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName; 
    }
        
    public int getUserTypeId(){
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId){
        this.userTypeId = userTypeId; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getLastLoginTime(){
        return lastLoginTime;
    }

    public void setLastLoginTime(java.util.Date lastLoginTime){
        this.lastLoginTime = lastLoginTime; 
    }
        
    public String getLastLoginIp(){
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp = lastLoginIp; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }

	public String getUserContentRole() {
		return userContentRole;
	}

	public void setUserContentRole(String userContentRole) {
		this.userContentRole = userContentRole;
	}

}
