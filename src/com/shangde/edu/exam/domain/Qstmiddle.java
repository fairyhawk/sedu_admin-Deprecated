package com.shangde.edu.exam.domain;

import java.io.Serializable;

public class Qstmiddle implements Serializable{
	/**
	 * 表id
	 */
    private int eqId;
    /**
     * 试卷id
     */
    private int epId;
    /**
     * 试题id
     */
    private int qstId;
    /**
     * 分数
     */
    private float qstScore;
    /**
     * 试题类型
     */
    private int qstType;
    
    /**
     * 试卷内容 //非数据字段
     */
    private String qstContent;
    
    private Qst qst;
    private Exampaper exam;
        
    public int getEqId(){
        return eqId;
    }

    public void setEqId(int eqId){
        this.eqId = eqId; 
    }
        
    public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
    public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public float getQstScore(){
        return qstScore;
    }

    public void setQstScore(float qstScore){
        this.qstScore = qstScore; 
    }

	public int getQstType() {
		return qstType;
	}

	public void setQstType(int qstType) {
		this.qstType = qstType;
	}

	public Qst getQst() {
		if(qst==null){
			qst=new Qst();
		}
		return qst;
	}

	public void setQst(Qst qst) {
		this.qst = qst;
	}

	public Exampaper getExam() {
		if(exam==null){
			exam=new Exampaper();
		}
		return exam;
	}

	public void setExam(Exampaper exam) {
		this.exam = exam;
	}

	public String getQstContent() {
		return qstContent;
	}

	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}
			
}
