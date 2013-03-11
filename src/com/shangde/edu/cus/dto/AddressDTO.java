package com.shangde.edu.cus.dto;

import java.io.Serializable;
import java.util.Date;

public class AddressDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int cusId;
    private String receiver;
    private String address;
    private String postCode;
    private String mobile;
    private Date createTime;
    
    private int provinceId;
    
    private int cityId;
    
    private int townId;
    
    private String provinceName;
    
    private String cityName;
    
    private String townName;
    
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
