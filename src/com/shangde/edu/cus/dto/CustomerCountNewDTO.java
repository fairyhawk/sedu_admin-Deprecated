package com.shangde.edu.cus.dto;

import java.io.Serializable;
public class CustomerCountNewDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dt;//时间
	private Integer totalAmount; //总金额
	private Integer ordersPaid; //已付订单
	private Integer allOrders; //全部订单
	private Integer allStudent; //注册学员
	public Integer getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getOrdersPaid() {
		return ordersPaid;
	}
	public void setOrdersPaid(Integer ordersPaid) {
		this.ordersPaid = ordersPaid;
	}
	public Integer getAllOrders() {
		return allOrders;
	}
	public void setAllOrders(Integer allOrders) {
		this.allOrders = allOrders;
	}
	public Integer getAllStudent() {
		return allStudent;
	}
	public void setAllStudent(Integer allStudent) {
		this.allStudent = allStudent;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
}

	
   
	
    
    
