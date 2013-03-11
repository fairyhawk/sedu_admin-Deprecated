package com.shangde.edu.cms.condition;

import com.shangde.common.vo.PageResult;

public class QueryTemplateCondition extends PageResult{
    private Integer tmpId;
    private String tmpName;
    private String tmpType;
    private String tmpContent;
    private Integer isUse;
    private String tmpContent_bak ;
    
	public String getTmpContent_bak() {
		return tmpContent_bak;
	}
	public void setTmpContent_bak(String tmpContent_bak) {
		this.tmpContent_bak = tmpContent_bak;
	}
	public Integer getTmpId() {
		return tmpId;
	}
	public void setTmpId(Integer tmpId) {
		this.tmpId = tmpId;
	}
	public String getTmpName() {
		return tmpName;
	}
	public void setTmpName(String tmpName) {
		this.tmpName = tmpName;
	}
	public String getTmpType() {
		return tmpType;
	}
	public void setTmpType(String tmpType) {
		this.tmpType = tmpType;
	}
	public String getTmpContent() {
		return tmpContent;
	}
	public void setTmpContent(String tmpContent) {
		this.tmpContent = tmpContent;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
        
}