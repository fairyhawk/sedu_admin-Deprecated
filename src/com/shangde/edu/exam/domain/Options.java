package com.shangde.edu.exam.domain;

import java.io.Serializable;

/**
 * 试卷选项
 * @author chenshuai
 *
 */
public class Options implements Serializable{
	/**
	 * 选项ID
	 */
    private int optId;
    /**
     * 试卷ID
     */
    private int qstId;
    /**
     * 选项内容
     */
    private String optContent;
    /**
     * 答案序号
     */
    private String optOrder;
    /**
     * 错误解析
     */
    private String wrongJudement;
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    private Qst qst;    
    public int getOptId(){
        return optId;
    }

    public void setOptId(int optId){
        this.optId = optId; 
    }
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public String getOptContent(){
        return optContent;
    }

    public void setOptContent(String optContent){
        this.optContent = optContent; 
    }
        
    public String getOptOrder(){
        return optOrder;
    }

    public void setOptOrder(String optOrder){
        this.optOrder = optOrder; 
    }
        
    public String getWrongJudement(){
        return wrongJudement;
    }

    public void setWrongJudement(String wrongJudement){
        this.wrongJudement = wrongJudement; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	public Qst getQst() {
		return qst;
	}

	public void setQst(Qst qst) {
		this.qst = qst;
	}
}
