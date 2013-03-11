package com.shangde.edu.sys.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 功能对象
 * @author guoqiang.liu
 */
public class Function implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -8009678401694895985L;
    /**功能类型：目录*/
    public static int FUNCTION_TYPE_CATALOG = 1;
    /**功能类型：实际功能*/
    public static int FUNCTION_TYPE_ITEM = 2;
	/**功能Id*/
    private int functionId;
    /**父Id*/
    private int parentFunctionId;
    /**功能名称*/
    private String functionName;
    /**功能类型s*/
    private int functionTypeId;
    /**功能对应Url*/
    private String functionUrl;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
    /**下级功能列表*/
    private java.util.List<Function> subFunctionList;
    /**功能适用范围id*/
    private int functionApplyScopeId;
    /**权限级别*/
    private int level;
        
    public int getFunctionId(){
        return functionId;
    }

    public void setFunctionId(int functionId){
        this.functionId = functionId; 
    }
        
    public int getParentFunctionId(){
        return parentFunctionId;
    }

    public void setParentFunctionId(int parentFunctionId){
        this.parentFunctionId = parentFunctionId; 
    }
        
    public String getFunctionName(){
        return functionName;
    }

    public void setFunctionName(String functionName){
        this.functionName = functionName; 
    }
        
    public int getFunctionTypeId(){
        return functionTypeId;
    }

    public void setFunctionTypeId(int functionTypeId){
        this.functionTypeId = functionTypeId; 
    }
        
    public String getFunctionUrl(){
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl){
        this.functionUrl = functionUrl; 
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

    public List<Function> getSubFunctionList() {
        return subFunctionList;
    }

    public void setSubFunctionList(List<Function> subFunctionList) {
        this.subFunctionList = subFunctionList;
    }

    public int getFunctionApplyScopeId() {
        return functionApplyScopeId;
    }

    public void setFunctionApplyScopeId(int functionApplyScopeId) {
        this.functionApplyScopeId = functionApplyScopeId;
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
