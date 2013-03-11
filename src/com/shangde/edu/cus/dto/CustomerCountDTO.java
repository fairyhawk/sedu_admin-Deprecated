package com.shangde.edu.cus.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;


@SuppressWarnings("serial")
public class CustomerCountDTO implements Serializable{
	/**
	 * 专业名
	 */
	private String subjectName;
	/**
	 * 全部注册数
	 */
	private int allRegCustomer;
	/**
	 * 内部注册数
	 */
	private int nbRegCustomer;
	/**
	 * 外部注册数
	 */
	private int wbRegCustomer;
	
	/**
	 * 临时注册数
	 * 
	 */
	private int lsRegCustomer;
	
	/**
	 * 订单总数
	 */
	private int subjectContractSum;
	
	
	/**
	 *已付订单总数
	 */
	private int yfSubjectContractSum;
	
	/**
	 * 临时注册数2
	 */
	private int lsCustomer;
	
	/**
	 * 总计注册数量
	 */
	private int allSum;
	/**
	 * 外部注册数量
	 * @return
	 */
	private int wbSum;
	/**
	 * 内部注册数量
	 * @return
	 */
	private int nbSum;
	/**
	 * 临时注册数量
	 * @return
	 */
	private int lsSum;
	
	/**
	 * 订单总数
	 * @return
	 */
	private int contractSum;
	
	/**
	 * 订单已付总数
	 * @return
	 */
	private int yfContractSum;
	
//	0 为赠送　1支付宝 　2货到付款 3 网银在线 4 块钱
	
	private int zsSum;
	
	private int zfbSum;
	
	private int hdfkSum;
	
	private int wyzxSum;
	
	private int kqSum;

	private int yfZsSum;
	
	private int yfZfbSum;
	
	private int yfHdfkSum;
	
	private int yfWyzxSum;
	
	private int yfKqSum;
	
	private int tfZfb;
	
	private int tfPOST;
	
	private int tfCb;
	
	private int tfKq;
	
	private int tfSum;
	/**银行汇款总数**/
	private int tyhhk;
	/**单项银行汇款**/
	private int yhhk;
	/**已付单项银行汇款**/
	private int yfyhhk;
	/**银行汇款已付总数**/
	private int tyfyhhk;
	/**全部真友支付**/
	private int tzy = 0;
	/**单个项目真友支付**/
	private int zy = 0;
	/**单个项目已付真友**/
	private int yfzy = 0;
	/**所有项目已付真友**/
	private int tyfzy = 0;
	
	public int getTzy() {
		return tzy;
	}
	public void setTzy(int tzy) {
		this.tzy = tzy;
	}
	public int getZy() {
		return zy;
	}
	public void setZy(int zy) {
		this.zy = zy;
	}
	public int getYfzy() {
		return yfzy;
	}
	public void setYfzy(int yfzy) {
		this.yfzy = yfzy;
	}
	public int getTyfzy() {
		return tyfzy;
	}
	public void setTyfzy(int tyfzy) {
		this.tyfzy = tyfzy;
	}
	/**
	 * @author 王超
	 * 用户取消订单数
	 */
	private int cancelCount;
	/**
	 * @author 王超
	 * 管理员取消订单数
	 */
	private int backCancelCount;
	/**
	 * 已付总金额
	 */
	private float totelPrice;
	
	private int dlskt;//代理商开通订单
	
	public int getTfSum() {
		return tfSum;
	}
	public void setTfSum(int tfSum) {
		this.tfSum = tfSum;
	}
	public int getTfZfb() {
		return tfZfb;
	}
	public void setTfZfb(int tfZfb) {
		this.tfZfb = tfZfb;
	}
	public int getTfPOST() {
		return tfPOST;
	}
	public void setTfPOST(int tfPOST) {
		this.tfPOST = tfPOST;
	}
	public int getTfCb() {
		return tfCb;
	}
	public void setTfCb(int tfCb) {
		this.tfCb = tfCb;
	}
	public int getTfKq() {
		return tfKq;
	}
	public void setTfKq(int tfKq) {
		this.tfKq = tfKq;
	}
	public int getLsSum() {
		return lsSum;
	}
	public void setLsSum(int lsSum) {
		this.lsSum = lsSum;
	}
	public int getAllRegCustomer() {
		return allRegCustomer;
	}
	public void setAllRegCustomer(int allRegCustomer) {
		this.allRegCustomer = allRegCustomer;
	}
	public int getNbRegCustomer() {
		return nbRegCustomer;
	}
	public void setNbRegCustomer(int nbRegCustomer) {
		this.nbRegCustomer = nbRegCustomer;
	}
	public int getWbRegCustomer() {
		return wbRegCustomer;
	}
	public void setWbRegCustomer(int wbRegCustomer) {
		this.wbRegCustomer = wbRegCustomer;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getAllSum() {
		return allSum;
	}
	public void setAllSum(int allSum) {
		this.allSum = allSum;
	}
	public int getWbSum() {
		return wbSum;
	}
	public void setWbSum(int wbSum) {
		this.wbSum = wbSum;
	}
	public int getNbSum() {
		return nbSum;
	}
	public void setNbSum(int nbSum) {
		this.nbSum = nbSum;
	}
	public int getLsRegCustomer() {
		return lsRegCustomer;
	}
	public void setLsRegCustomer(int lsRegCustomer) {
		this.lsRegCustomer = lsRegCustomer;
	}
	public int getLsCustomer() {
		return lsCustomer;
	}
	public void setLsCustomer(int lsCustomer) {
		this.lsCustomer = lsCustomer;
	}
	public int getSubjectContractSum() {
		return subjectContractSum;
	}
	public void setSubjectContractSum(int subjectContractSum) {
		this.subjectContractSum = subjectContractSum;
	}
	public int getYfSubjectContractSum() {
		return yfSubjectContractSum;
	}
	public void setYfSubjectContractSum(int yfSubjectContractSum) {
		this.yfSubjectContractSum = yfSubjectContractSum;
	}
	public int getContractSum() {
		return contractSum;
	}
	public void setContractSum(int contractSum) {
		this.contractSum = contractSum;
	}
	public int getYfContractSum() {
		return yfContractSum;
	}
	public void setYfContractSum(int yfContractSum) {
		this.yfContractSum = yfContractSum;
	}
	public int getZsSum() {
		return zsSum;
	}
	public void setZsSum(int zsSum) {
		this.zsSum = zsSum;
	}
	public int getZfbSum() {
		return zfbSum;
	}
	public void setZfbSum(int zfbSum) {
		this.zfbSum = zfbSum;
	}
	public int getHdfkSum() {
		return hdfkSum;
	}
	public void setHdfkSum(int hdfkSum) {
		this.hdfkSum = hdfkSum;
	}
	public int getWyzxSum() {
		return wyzxSum;
	}
	public void setWyzxSum(int wyzxSum) {
		this.wyzxSum = wyzxSum;
	}
	public int getKqSum() {
		return kqSum;
	}
	public void setKqSum(int kqSum) {
		this.kqSum = kqSum;
	}
	public int getYfZsSum() {
		return yfZsSum;
	}
	public void setYfZsSum(int yfZsSum) {
		this.yfZsSum = yfZsSum;
	}
	public int getYfZfbSum() {
		return yfZfbSum;
	}
	public void setYfZfbSum(int yfZfbSum) {
		this.yfZfbSum = yfZfbSum;
	}
	public int getYfHdfkSum() {
		return yfHdfkSum;
	}
	public void setYfHdfkSum(int yfHdfkSum) {
		this.yfHdfkSum = yfHdfkSum;
	}
	public int getYfWyzxSum() {
		return yfWyzxSum;
	}
	public void setYfWyzxSum(int yfWyzxSum) {
		this.yfWyzxSum = yfWyzxSum;
	}
	public int getYfKqSum() {
		return yfKqSum;
	}
	public void setYfKqSum(int yfKqSum) {
		this.yfKqSum = yfKqSum;
	}
    public int getDlskt() {
        return dlskt;
    }
    public void setDlskt(int dlskt) {
        this.dlskt = dlskt;
    }
	public int getCancelCount() {
		return cancelCount;
	}
	public void setCancelCount(int cancelCount) {
		this.cancelCount = cancelCount;
	}
	public int getBackCancelCount() {
		return backCancelCount;
	}
	public void setBackCancelCount(int backCancelCount) {
		this.backCancelCount = backCancelCount;
	}
	public float getTotelPrice() {
		return totelPrice;
	}
	public void setTotelPrice(float totelPrice) {
		this.totelPrice = totelPrice;
	}
	public int getTyhhk() {
		return tyhhk;
	}
	public void setTyhhk(int tyhhk) {
		this.tyhhk = tyhhk;
	}
	public int getYhhk() {
		return yhhk;
	}
	public void setYhhk(int yhhk) {
		this.yhhk = yhhk;
	}
	public int getYfyhhk() {
		return yfyhhk;
	}
	public void setYfyhhk(int yfyhhk) {
		this.yfyhhk = yfyhhk;
	}
	public int getTyfyhhk() {
		return tyfyhhk;
	}
	public void setTyfyhhk(int tyfyhhk) {
		this.tyfyhhk = tyfyhhk;
	}
	
}

	
   
	
    
    
