package com.shangde.edu.cus.domain;

import java.io.Serializable;
import java.util.Date;

public class Address implements Serializable{
	public static int ADDRESS_IS_FIRST_YES = 1;
	public static int ADDRESS_IS_FIRST_NO = 0;
    private int id;
    private int cusId;
    private String receiver;   	//接收人
    private String address;		//地址
    private String postCode;   	//邮政编码
    private String mobile;		//联系电话
    private Date createTime;	//创建时间
    
    private int provinceId;
    
    private int cityId;
    
    private int townId;
    
    /**
     * 是否是常用地址
     */
    private int isFirst;
    
    /**
     * 送达时间
     * 1 时间不限
     * 2 周一至周五
     * 3 周六日及公众假期
     */
    private int sendTime;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public String getReceiver(){
        return receiver;
    }

    public void setReceiver(String receiver){
        this.receiver = receiver; 
    }
        
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address; 
    }
        
    public String getPostCode(){
        return postCode;
    }

    public void setPostCode(String postCode){
        this.postCode = postCode; 
    }
        
    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile; 
    }
        
    public int getIsFirst(){
        return isFirst;
    }

    public void setIsFirst(int isFirst){
        this.isFirst = isFirst; 
    }
        
    public int getSendTime(){
        return sendTime;
    }

    public void setSendTime(int sendTime){
        this.sendTime = sendTime; 
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getTownId() {
		return townId;
	}

	public void setTownId(int townId) {
		this.townId = townId;
	}
}
