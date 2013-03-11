package com.shangde.edu.book.domain;

import java.io.Serializable;
import java.util.Date;

public class BuyInfo implements Serializable{
    private int id;
    private String cusName;
    private String address;
    private String tel;
    private String mobilephone;
    private String postalcode;
    private String remark;
    private String bookname;
    private int buySum;
    private int cusId;
    private Date createtime;
        
    public int getBuySum() {
		return buySum;
	}

	public void setBuySum(int buySum) {
		this.buySum = buySum;
	}

	public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getCusName(){
        return cusName;
    }

    public void setCusName(String cusName){
        this.cusName = cusName; 
    }
        
    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address; 
    }
        
    public String getTel(){
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel; 
    }
        
    public String getMobilephone(){
        return mobilephone;
    }

    public void setMobilephone(String mobilephone){
        this.mobilephone = mobilephone; 
    }
        
    public String getPostalcode(){
        return postalcode;
    }

    public void setPostalcode(String postalcode){
        this.postalcode = postalcode; 
    }
        
    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark; 
    }
        
    public String getBookname(){
        return bookname;
    }

    public void setBookname(String bookname){
        this.bookname = bookname; 
    }

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
    
    
}
