package com.shangde.common.vo;

public class ChinaBankInfo {
	
	/**
	 * 商户号，请设置自己的商户号(老版商户号为4位或5位,新版为8位)即可
	 */
	private String v_mid;
	
	/**
	 * 如果您还没有设置MD5密钥请登陆我们为您提供商户后台，地址：https://merchant3.chinabank.com.cn/
	 * 登陆后在上面的导航栏里可能找到“B2C”，在二级导航栏里有“MD5密钥设置”
	 * 建议您设置一个16位以上的密钥或更高，密钥最多64位，但设置16位已经足够了
	 */
	private String key;
	
	/**
	 * 商户自定义返回接收支付结果的页面 
	 */
	private String v_url;
	
	/**
	 * 推荐订单号构成格式为 年月日-商户号-小时分钟秒
	 */
	private String v_oid;
	
	/**
	 * 订单金额
	 */
	private String v_amount;
	
	/**
	 * 币种
	 */
	private String v_moneytype;
	
	/**
	 * 对拼凑串MD5私钥加密后的值
	 */
	private String v_md5info;
	
	/**
	 * 备注字段1
	 */
	private String remark1;
	
	/**
	 * 备注字段2
	 */
	private String remark2;
	
	/**
	 * 支付方式中文说明，如"中行长城信用卡"
	 */
	private String v_pmode;
	
	/**
	 * 支付结果，20支付完成；30支付失败；
	 */
	private String v_pstatus;
	
	/**
	 * 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
	 */
	private String v_pstring;
	
	/**
	 * 银行参数:
	 */
	private int pmode_id;
	
	public String getV_mid() {
		return v_mid;
	}
	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getV_url() {
		return v_url;
	}
	public void setV_url(String v_url) {
		this.v_url = v_url;
	}
	public String getV_oid() {
		return v_oid;
	}
	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}
	public String getV_amount() {
		return v_amount;
	}
	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}
	public String getV_moneytype() {
		return v_moneytype;
	}
	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}
	public String getV_md5info() {
		return v_md5info;
	}
	public void setV_md5info(String v_md5info) {
		this.v_md5info = v_md5info;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getV_pmode() {
		return v_pmode;
	}
	public void setV_pmode(String v_pmode) {
		this.v_pmode = v_pmode;
	}
	public String getV_pstatus() {
		return v_pstatus;
	}
	public void setV_pstatus(String v_pstatus) {
		this.v_pstatus = v_pstatus;
	}
	public String getV_pstring() {
		return v_pstring;
	}
	public void setV_pstring(String v_pstring) {
		this.v_pstring = v_pstring;
	}
	public int getPmode_id() {
		return pmode_id;
	}
	public void setPmode_id(int pmode_id) {
		this.pmode_id = pmode_id;
	}
}
