package com.shangde.edu.cus.domain;

import java.io.Serializable;
public class CustomerNewDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer allStudent; //全部学员
	private Integer regStudent; //注册学员
	private Integer interStudent; //内部学员
	private Integer tempStudent; //临时学员
	private Integer leyuStudent; //乐语学员
	private Integer liuyanStudent;//留言学员
	private Integer callInStudent;//callin学员
	private Integer allOrder; //全部订单
	private Integer bankOrder; //网银订单
	private Integer dtoOrder; //货到付款订单
	private Integer insideOrder; //内部开通订单 
	private Integer cancelOrder; //取消订单
	private Integer invalidOrder; //无效订单
	private Integer agentsOrder; //代理商订单
	private Integer bankOrderPaid; //网银订单(已付)
	private Integer dtoOrderPaid; //货到付款订单(已付)
	private Integer afterAmount; //打折后金额
	private Integer Refund; //退费
	private String subName;//专业名称
	private Integer transferOrder;//银行汇款订单
	private Integer transferOrderPaid;//银行汇款订单（已付）
	private Integer subjectId;
	public Integer getTransferOrder() {
		return transferOrder;
	}
	public void setTransferOrder(Integer transferOrder) {
		this.transferOrder = transferOrder;
	}
	public Integer getTransferOrderPaid() {
		return transferOrderPaid;
	}
	public void setTransferOrderPaid(Integer transferOrderPaid) {
		this.transferOrderPaid = transferOrderPaid;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Integer getAllStudent() {
		return allStudent;
	}
	public void setAllStudent(Integer allStudent) {
		this.allStudent = allStudent;
	}
	public Integer getRegStudent() {
		return regStudent;
	}
	public void setRegStudent(Integer regStudent) {
		this.regStudent = regStudent;
	}
	public Integer getInterStudent() {
		return interStudent;
	}
	public void setInterStudent(Integer interStudent) {
		this.interStudent = interStudent;
	}
	public Integer getTempStudent() {
		return tempStudent;
	}
	public void setTempStudent(Integer tempStudent) {
		this.tempStudent = tempStudent;
	}
	public Integer getAllOrder() {
		return allOrder;
	}
	public void setAllOrder(Integer allOrder) {
		this.allOrder = allOrder;
	}
	public Integer getBankOrder() {
		return bankOrder;
	}
	public void setBankOrder(Integer bankOrder) {
		this.bankOrder = bankOrder;
	}
	public Integer getDtoOrder() {
		return dtoOrder;
	}
	public void setDtoOrder(Integer dtoOrder) {
		this.dtoOrder = dtoOrder;
	}
	public Integer getInsideOrder() {
		return insideOrder;
	}
	public void setInsideOrder(Integer insideOrder) {
		this.insideOrder = insideOrder;
	}
	public Integer getCancelOrder() {
		return cancelOrder;
	}
	public void setCancelOrder(Integer cancelOrder) {
		this.cancelOrder = cancelOrder;
	}
	public Integer getInvalidOrder() {
		return invalidOrder;
	}
	public void setInvalidOrder(Integer invalidOrder) {
		this.invalidOrder = invalidOrder;
	}
	public Integer getAgentsOrder() {
		return agentsOrder;
	}
	public void setAgentsOrder(Integer agentsOrder) {
		this.agentsOrder = agentsOrder;
	}
	public Integer getBankOrderPaid() {
		return bankOrderPaid;
	}
	public void setBankOrderPaid(Integer bankOrderPaid) {
		this.bankOrderPaid = bankOrderPaid;
	}
	public Integer getDtoOrderPaid() {
		return dtoOrderPaid;
	}
	public void setDtoOrderPaid(Integer dtoOrderPaid) {
		this.dtoOrderPaid = dtoOrderPaid;
	}
	public Integer getAfterAmount() {
		return afterAmount;
	}
	public void setAfterAmount(Integer afterAmount) {
		this.afterAmount = afterAmount;
	}
	public Integer getRefund() {
		return Refund;
	}
	public void setRefund(Integer refund) {
		Refund = refund;
	}
	public Integer getLeyuStudent() {
		return leyuStudent;
	}
	public void setLeyuStudent(Integer leyuStudent) {
		this.leyuStudent = leyuStudent;
	}
	public Integer getLiuyanStudent() {
		return liuyanStudent;
	}
	public void setLiuyanStudent(Integer liuyanStudent) {
		this.liuyanStudent = liuyanStudent;
	}
	public Integer getCallInStudent() {
		return callInStudent;
	}
	public void setCallInStudent(Integer callInStudent) {
		this.callInStudent = callInStudent;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	
}
