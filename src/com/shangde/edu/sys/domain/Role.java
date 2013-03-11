package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -3688599003273044642L;
    /**角色适用范围：全部用户*/
    public static int ROLE_APPLY_SCOPE_ALL = 1;
    /**角色默认类型*/
    public static int ROLE_TYPE_DEFAULT = 1;
    /**角色默认状态*/
    public static int ROLE_STATUS_DEFAULT = 1;
	/**角色id*/
    private int roleId;
    /**角色名称*/
    private String roleName;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
    /**功能列表*/
    private List<Function> functionList = new ArrayList<Function>();
    /**角色类别*/
    private int roleTypeId;
    /**角色适用范围id*/
    private int roleApplyScopeId;
    
    private List<Subject> subjectList = new ArrayList<Subject>();
        
    public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId; 
    }
        
    public String getRoleName(){
        return roleName;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
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

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public int getRoleApplyScopeId() {
        return roleApplyScopeId;
    }

    public void setRoleApplyScopeId(int roleApplyScopeId) {
        this.roleApplyScopeId = roleApplyScopeId;
    }
}
