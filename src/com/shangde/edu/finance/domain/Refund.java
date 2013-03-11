package com.shangde.edu.finance.domain;

import java.util.Date;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能实体类,用与客服人员或财务人员查看 。
 * 		     客服人员对申请商品退费，编辑退费信息以及撤消商品退费的操作
 *        财务人员对申请要退费的商品进行审核，以及对不满足退费要求的商品进行撤消退费的操作
 *
 *
 * @author		Yangning
 * @date		2011-11-21
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class Refund {
	
	private Integer id;
	/**流水id**/
	private Integer cashid;
	/**订单编号**/
	private String contractno;
	/**退费状态 0,未退费,1已退费,2,已撤销**/
	private Integer status;
	/**退费说明，客服填写**/
	private String remark;
	/**用户ID**/
	private Integer cusid;
	/**退费申请时间**/
	private Date createtime;
	/**退费时间**/
	private Date refundtime;
	/**最后更新时间**/
	private Date canceltime;
	/**审核说明**/
	private String checkremark;
	/**开户行**/
	private String bankregname;
	/**用户邮箱**/
	private String useremail;
	/**联系人电话**/
	private String usermobile;
	/**联系人姓名**/
	private String conname;
	/**操作人ID**/
	private Integer operuserid;
	/**审核人ID**/
	private Integer confirmuid;
	/**是否全额退?**/
	private Boolean isfull;
	/**是否支付宝?**/
	private Boolean iszfb;
	/**银行名**/
	private String bankname;
	/**开户行帐号**/
	private String bankcode;
	/**开户人姓名**/
	private String cusbankname;
	/**退还金额**/
	private Float price;
	/**记录人姓名**/
	private String operusername;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCusid() {
		return cusid;
	}
	public void setCusid(Integer cusid) {
		this.cusid = cusid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(Date refundtime) {
		this.refundtime = refundtime;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getCusbankname() {
		return cusbankname;
	}
	public void setCusbankname(String cusbankname) {
		this.cusbankname = cusbankname;
	}
	public String getCheckremark() {
		return checkremark;
	}
	public void setCheckremark(String checkremark) {
		this.checkremark = checkremark;
	}
	public Integer getCashid() {
		return cashid;
	}
	public void setCashid(Integer cashid) {
		this.cashid = cashid;
	}
	public Date getCanceltime() {
		return canceltime;
	}
	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}
	public String getBankregname() {
		return bankregname;
	}
	public void setBankregname(String bankregname) {
		this.bankregname = bankregname;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}
	public String getConname() {
		return conname;
	}
	public void setConname(String conname) {
		this.conname = conname;
	}
	public Integer getOperuserid() {
		return operuserid;
	}
	public void setOperuserid(Integer operuserid) {
		this.operuserid = operuserid;
	}
	public Boolean getIsfull() {
		return isfull;
	}
	public void setIsfull(Boolean isfull) {
		this.isfull = isfull;
	}
	public Boolean getIszfb() {
		return iszfb;
	}
	public void setIszfb(Boolean iszfb) {
		this.iszfb = iszfb;
	}
	public String getContractno() {
		return contractno;
	}
	public void setContractno(String contractno) {
		this.contractno = contractno;
	}
	public Integer getConfirmuid() {
		return confirmuid;
	}
	public void setConfirmuid(Integer confirmuid) {
		this.confirmuid = confirmuid;
	}
	public String getOperusername() {
		return operusername;
	}
	public void setOperusername(String operusername) {
		this.operusername = operusername;
	}
}
