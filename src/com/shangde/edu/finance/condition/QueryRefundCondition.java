package com.shangde.edu.finance.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能条件查询,用与客服人员查看 。
 * 		     客服人员对申请商品退费，编辑退费信息以及撤消商品退费的操作
 *       人员对申请要退费的商品进行审核，以及对不满足退费要求的商品进行撤消退费的操作
 *
 *
 * @author		Yangning
 * @date		2011-11-21
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class QueryRefundCondition extends PageQuery{
	
	private Integer id;
	/**用户邮箱**/
	private String email;
	/**商品名称**/
	private String sellname;
	/**商品id**/
	private Integer sellid;
	/**退费状态 0,未退费,1已退费,2,已撤销**/
	private Integer status;
	/**申请开始时间**/
	private Date starttime;
	/**申请结束时间**/
	private Date endtime;
	/**审核开始时间**/
	private Date starttimechk;
	/**审核结束时间**/
	private Date endtimechk;
	/**开户行帐号**/
	private String bankcode;
	/**开户人姓名**/
	private String cusbankname;
	/**订单编号**/
	private String contractno;
	
	/**商品ID**/
	private Integer packid;
	
	public Integer getPackid() {
		return packid;
	}
	public void setPackid(Integer packid) {
		this.packid = packid;
	}
	
	public String getSellname() {
		return sellname;
	}
	public void setSellname(String sellname) {
		this.sellname = sellname;
	}
	public void setEndtimechk(Date endtimechk) {
		this.endtimechk = endtimechk;
	}
	
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellid() {
		return sellid;
	}
	public void setSellid(Integer sellid) {
		this.sellid = sellid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCusbankname() {
		return cusbankname;
	}
	public void setCusbankname(String cusbankname) {
		this.cusbankname = cusbankname;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Date getStarttimechk() {
		return starttimechk;
	}
	public void setStarttimechk(Date starttimechk) {
		this.starttimechk = starttimechk;
	}
	public Date getEndtimechk() {
		return endtimechk;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
}
