package com.shangde.edu.cou.domain;

import java.io.Serializable;

/**
 * 历史价格
 * @author
 *
 */
public class HistoryPrice implements Serializable{
    private int id;
    
    /**
     * 课程ID
     */
    private int courseId;
    
    /**
     * 价格
     */
    private java.lang.Object price;
    
    /**
     * 创建时间
     */
    private java.util.Date creadeTime;
    
    /**
     * 终止时间
     */
    private java.util.Date stopTime;
    
    /**
     * 购买数量
     */
    private int buyNum;
    
    /**
     * 修改人ID
     */
    private int umanId;
    /**
     * 售卖方式ID
     */
    private int sellId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public java.lang.Object getPrice(){
        return price;
    }

    public void setPrice(java.lang.Object price){
        this.price = price; 
    }
        
    public java.util.Date getCreadeTime(){
        return creadeTime;
    }

    public void setCreadeTime(java.util.Date creadeTime){
        this.creadeTime = creadeTime; 
    }
        
    public java.util.Date getStopTime(){
        return stopTime;
    }

    public void setStopTime(java.util.Date stopTime){
        this.stopTime = stopTime; 
    }
        
    public int getUmanId(){
        return umanId;
    }

    public void setUmanId(int umanId){
        this.umanId = umanId; 
    }

	public int getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}
}
