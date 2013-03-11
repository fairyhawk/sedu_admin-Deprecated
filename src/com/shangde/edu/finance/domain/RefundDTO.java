package com.shangde.edu.finance.domain;

import java.util.Date;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能实体转化类,用与客服人员人员查看 。
 * 		     客服人员对申请商品退费，编辑退费信息以及撤消商品退费的操作
 *        客服人员对申请要退费的商品进行审核，以及对不满足退费要求的商品进行撤消退费的操作
 *
 * @author		Yangning
 * @date		2011-11-22
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class RefundDTO extends Refund{
	/**商品名称**/
	private String sellname;
	/**成交价格**/
	private Float dealprice;
	/**支付时间**/
	private Date paytime;
	/**支付状态**/
	private Integer fstatus;
	/**写入操作员名字**/
	private String opername;
	/**审核操作员名字**/
	private String checkname;
	/**订单ID**/
	private Integer ctid;
	
	public Integer getCtid() {
		return ctid;
	}
	public void setCtid(Integer ctid) {
		this.ctid = ctid;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public String getCheckname() {
		return checkname;
	}
	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}
	public String getSellname() {
		return sellname;
	}
	public void setSellname(String sellname) {
		this.sellname = sellname;
	}
	
	public Float getDealprice() {
		return dealprice;
	}
	public void setDealprice(Float dealprice) {
		this.dealprice = dealprice;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public Integer getFstatus() {
		return fstatus;
	}
	public void setFstatus(Integer fstatus) {
		this.fstatus = fstatus;
	}
}
