package com.shangde.edu.res.domain;

import java.io.Serializable;

public class Books implements Serializable{
    /** 书籍主键id   */
	private int bkId;
	/** 书籍组外键id   */
	private int bgId;
	/** 知识点外键id   */
	private int pointid;
	/** 书名   */
    private String bkName;
    /** 书的作者   */
    private String bkAuthor;
    /** 书的类型   */
    private String bkType;
    /** 书的标题   */
    private String bkTitle;
    /** 书的价格（原价）   */
    private String bkPrice;
    /** 书的图片路径   */
    private String bkUrl;
    /**Mp3下载地址**/
    private String ypUrl;
	/** 书的促销方式   */
    private String bkSales;
    /** 书的折扣价   */
    private String discPrice;
    /** 出版社  */
    private String publisher;
    /** 书的条码   */
    private String barcode;
    /** 图书预读   */
    private String part;
    /** 关联书籍  */
    private String reach;
    /** 排序   */
    private int bkOrder;
    /** 上传时间   */
    private java.util.Date createTime;
    /** 下架时间   */
    private java.util.Date downTime;
    /** 备注   */
    private String bkInfo;
    
    public int getBkId(){
        return bkId;
    }

    public void setBkId(int bkId){
        this.bkId = bkId; 
    }
        
    public int getBgId(){
        return bgId;
    }

    public void setBgId(int bgId){
        this.bgId = bgId; 
    }
        
    public int getPointid(){
        return pointid;
    }

    public void setPointid(int pointid){
        this.pointid = pointid; 
    }
        
    public String getBkName(){
        return bkName;
    }

    public void setBkName(String bkName){
        this.bkName = bkName; 
    }
        
    public String getBkAuthor(){
        return bkAuthor;
    }

    public void setBkAuthor(String bkAuthor){
        this.bkAuthor = bkAuthor; 
    }
        
    public String getBkType(){
        return bkType;
    }

    public void setBkType(String bkType){
        this.bkType = bkType; 
    }
        
    public String getBkTitle(){
        return bkTitle;
    }

    public void setBkTitle(String bkTitle){
        this.bkTitle = bkTitle; 
    }
    
    public String getYpUrl() {
		return ypUrl;
	}

	public void setYpUrl(String ypUrl) {
		this.ypUrl = ypUrl;
	}

    
    public String getBkPrice(){
        return bkPrice;
    }

    public void setBkPrice(String bkPrice){
        this.bkPrice = bkPrice; 
    }
        
   
    public String getBkSales(){
        return bkSales;
    }

    public void setBkSales(String bkSales){
        this.bkSales = bkSales; 
    }
        
    public String getDiscPrice(){
        return discPrice;
    }

    public void setDiscPrice(String discPrice){
        this.discPrice = discPrice; 
    }
        
    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher; 
    }
        
    public String getBarcode(){
        return barcode;
    }

    public void setBarcode(String barcode){
        this.barcode = barcode; 
    }
        
    public String getPart(){
        return part;
    }

    public void setPart(String part){
        this.part = part; 
    }
        
    public String getReach(){
        return reach;
    }

    public void setReach(String reach){
        this.reach = reach; 
    }
        
    public int getBkOrder(){
        return bkOrder;
    }

    public void setBkOrder(int bkOrder){
        this.bkOrder = bkOrder; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getDownTime(){
        return downTime;
    }

    public void setDownTime(java.util.Date downTime){
        this.downTime = downTime; 
    }
        
    public String getBkInfo(){
        return bkInfo;
    }

    public void setBkInfo(String bkInfo){
        this.bkInfo = bkInfo; 
    }

	public String getBkUrl() {
		return bkUrl;
	}

	public void setBkUrl(String bkUrl) {
		this.bkUrl = bkUrl;
	}

	
}
