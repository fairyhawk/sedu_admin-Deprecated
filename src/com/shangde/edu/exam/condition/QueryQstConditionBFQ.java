package com.shangde.edu.exam.condition;

/**
 * 查询试题条件
 * @author chenshuai
 *
 */
public class QueryQstConditionBFQ {
    private int qstId;
    private int qstIndex;
    private int epId;
    private String qstContent;
    private String isAsr;//试题正确答案 
    private String wrongJude;//试题分析
    private String aoptContent;//a选项内容
    private String aoptOrder;//选项a
    private String boptContent;
    private String boptOrder;
    private String coptContent;
    private String coptOrder;
    private String doptContent;
    private String doptOrder;
    
    public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId;
    }

	public String getQstContent() {
		return qstContent;
	}

	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}

	public String getIsAsr() {
		return isAsr;
	}

	public void setIsAsr(String isAsr) {
		this.isAsr = isAsr;
	}

	public String getWrongJude() {
		return wrongJude;
	}

	public void setWrongJude(String wrongJude) {
		this.wrongJude = wrongJude;
	}

	public String getAoptContent() {
		return aoptContent;
	}

	public void setAoptContent(String aoptContent) {
		this.aoptContent = aoptContent;
	}

	public String getAoptOrder() {
		return aoptOrder;
	}

	public void setAoptOrder(String aoptOrder) {
		this.aoptOrder = aoptOrder;
	}

	public String getBoptContent() {
		return boptContent;
	}

	public void setBoptContent(String boptContent) {
		this.boptContent = boptContent;
	}

	public String getBoptOrder() {
		return boptOrder;
	}

	public void setBoptOrder(String boptOrder) {
		this.boptOrder = boptOrder;
	}

	public String getCoptContent() {
		return coptContent;
	}

	public void setCoptContent(String coptContent) {
		this.coptContent = coptContent;
	}

	public String getCoptOrder() {
		return coptOrder;
	}

	public void setCoptOrder(String coptOrder) {
		this.coptOrder = coptOrder;
	}

	public String getDoptContent() {
		return doptContent;
	}

	public void setDoptContent(String doptContent) {
		this.doptContent = doptContent;
	}

	public String getDoptOrder() {
		return doptOrder;
	}

	public void setDoptOrder(String doptOrder) {
		this.doptOrder = doptOrder;
	}

	public int getQstIndex() {
		return qstIndex;
	}

	public void setQstIndex(int qstIndex) {
		this.qstIndex = qstIndex;
	}
}