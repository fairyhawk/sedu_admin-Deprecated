package com.shangde.edu.stu.domain;

import java.io.Serializable;


/**
 * 试卷记录
 * @author chenshuai
 *
 */
public class ExapaRe implements Serializable{

	/**
	 * 用户ID
	 */
    private int cusId;
    
    /**
     * 试卷ID
     */
    private int epId;
    
    private java.util.Date addtime;
    
    /**
     * 试卷
     */
    private Exapa exapa;
    

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	/**
	 * @return the exapa
	 */
	public Exapa getExapa() {
		return exapa;
	}

	/**
	 * @param exapa the exapa to set
	 */
	public void setExapa(Exapa exapa) {
		this.exapa = exapa;
	}






	
}
