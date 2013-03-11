package com.shangde.edu.sys.domain;

import java.io.Serializable;

public class RoleFunction implements Serializable{
    /**RoleFunction的默认状态*/
    public static int ROLE_FUNCTION_STATUS_DEFAULT = 1;
	/**角色id*/
    private int roleId;
    /**功能id*/
    private int functionId;
    /**功能*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
        
    public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId; 
    }
        
    public int getFunctionId(){
        return functionId;
    }

    public void setFunctionId(int functionId){
        this.functionId = functionId; 
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
}
