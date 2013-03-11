package com.shangde.edu.finance.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Cod;

public class ContractExcelDTO implements Serializable{
	public static String  CONTRACT_FROM ="前台用户";
	public static String CONTRACT_PAY_TYPE ="支付宝";

	/**
	 * 支付类型：
	 * 			0 : 赠送
	 * 			1 : 网上支付
	 * 			2 : 货到付款
	 * 			3 : 网银在线(ChinaBank)
	 * 			4 ：	快钱
	 */
	public static int CONTRACT_PAY_TYPE_GIVE = 0;
	public static int CONTRACT_PAY_TYPE_ZFB = 1;
	public static int CONTRACT_PAY_TYPE_POST = 2;
	public static int CONTRACT_PAY_TYPE_CB = 3;
	public static int CONTRACT_PAY_TYPE_KQ=4;

	/**
	 * 网上支付或赠送的状态
	 * 			0 : 未支付
	 * 			1 : 支付
	 * 			2 : 赠送
	 */
	public static int CONTRACT_STATUS_NOT_PAY = 0;
	public static int CONTRACT_STATUS_PAIED = 1;
	public static int CONTRACT_STATUS_GIVE = 2;
	
	/**
	 * 货到付款的状态
	 * 			0 : 未激活
	 * 			1 : 激活
	 * 			3 : 取消
	 */
	public static int CONTRACT_STATUS_ACT_NO = 0;
	public static int CONTRACT_STATUS_ACT_YES = 1;
	public static int CONTRACT_STATUS_ACT_CANCEL = 3;

	/**
	 * 是否用优惠券
	 * 			0 : 否
	 * 			1 : 是
	 */
	public static int CONTRACT_USE_COUPON_NO = 0;
	public static int CONTRACT_USE_COUPON_YES = 1;
	
	  /** 运费 */
    private int freight ;
    
    /** 特别减免 */
    private int relief ;
	
	 /** 注册用户id   */
    private int cusId;
    /** 订单id   */
    private int id;
    /** 订单号   */
    private String contractId;
    /** 订单来源  */
    private String contractFrom;
    /** 创建时间   */
    private java.util.Date createTime;
    /** 状态   */
    private int status; //赠送的包括　2赠送　4修复;	支付宝的包括　0未付　1已付　3退费;　 货到付款的包括 0未激活 1已激活 3已取消 4退费
    /** 支付类型 */
    private int payType; //0 为赠送　1支付宝 　2货到付款 3 网银在线 4 块钱
    /** 备注  */
    private String remark;
    /** 总金额  */
    private java.lang.Object contractSumMoney = 0;
    /** 折后总金额  */
    private java.lang.Object contractCutSumMoney = 0;
    /** 是否使用优惠券  */
    private int useCoupon;
    /** 优惠券金额　  */
    private java.lang.Object couponMoney;
    /** 付款时间　  */
    private java.util.Date payTime;
   /** 访问次数*/
    private int callSum;
    /** 激活码*/
    private String contractCDkey;
    /** 激活次数*/
    private String cdkeySum;
    /** 用户的地址id*/
    private int cusIdAddress; 
    /** 货到付款的list*/
    private List<Cod> codList=new ArrayList<Cod>();
    /** 用户对象*/
    private Customer customer;
    /** cps来源 */
    private String webFrom;
    /** 来源代理*/
    private String webAgent;
    /**　来自于哪个地址*/
    private String contractFromUrl;
    /**广告主为亿起发指定的标识*/
    private String src ;
    /**广告主在亿起发推广的标识*/
    private Integer cid ;
    /**亿起发下级网站信息*/
    private String wi ;
    /** 货到付款地址 */
    private AddressDTO addressDTO;
    private List<CashRecord> cashRecordList;
    /**取消来源*/
    private int cancelFrom;
    
    private java.util.Date cancelTime;
    private int couponId;
    public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public String getContractId(){
        return contractId;
    }

    public void setContractId(String contractId){
        this.contractId = contractId; 
    }
        
    public String getContractFrom(){
        return contractFrom;
    }

    public void setContractFrom(String contractFrom){
        this.contractFrom = contractFrom; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public int getPayType(){
        return payType;
    }

    public void setPayType(int payType){
        this.payType = payType; 
    }
        
    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark; 
    }

	public java.lang.Object getContractSumMoney() {
		return contractSumMoney;
	}

	public void setContractSumMoney(java.lang.Object contractSumMoney) {
		this.contractSumMoney = contractSumMoney;
	}

	public java.lang.Object getContractCutSumMoney() {
		return contractCutSumMoney;
	}

	public void setContractCutSumMoney(java.lang.Object contractCutSumMoney) {
		this.contractCutSumMoney = contractCutSumMoney;
	}

	public int getUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(int useCoupon) {
		this.useCoupon = useCoupon;
	}

	public java.lang.Object getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(java.lang.Object couponMoney) {
		this.couponMoney = couponMoney;
	}

	public java.util.Date getPayTime() {
		return payTime;
	}

	public void setPayTime(java.util.Date payTime) {
		this.payTime = payTime;
	}

	public int getCallSum() {
		return callSum;
	}

	public void setCallSum(int callSum) {
		this.callSum = callSum;
	}

	public String getContractCDkey() {
		return contractCDkey;
	}

	public void setContractCDkey(String contractCDkey) {
		this.contractCDkey = contractCDkey;
	}

	public String getCdkeySum() {
		return cdkeySum;
	}

	public void setCdkeySum(String cdkeySum) {
		this.cdkeySum = cdkeySum;
	}

	public int getCusIdAddress() {
		return cusIdAddress;
	}

	public void setCusIdAddress(int cusIdAddress) {
		this.cusIdAddress = cusIdAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Cod> getCodList() {
		return codList;
	}

	public void setCodList(List<Cod> codList) {
		this.codList = codList;
	}
	
	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}

	public String getContractFromUrl() {
		return contractFromUrl;
	}

	public void setContractFromUrl(String contractFromUrl) {
		this.contractFromUrl = contractFromUrl;
	}

	public String getWi() {
		return wi;
	}

	public void setWi(String wi) {
		this.wi = wi;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public List<CashRecord> getCashRecordList() {
		return cashRecordList;
	}

	public void setCashRecordList(List<CashRecord> cashRecordList) {
		this.cashRecordList = cashRecordList;
	}

	public int getFreight() {
		return freight;
	}

	public int getCancelFrom() {
		return cancelFrom;
	}

	public void setCancelFrom(int cancelFrom) {
		this.cancelFrom = cancelFrom;
	}

	public void setFreight(int freight) {
		this.freight = freight;
	}

	public int getRelief() {
		return relief;
	}

	public void setRelief(int relief) {
		this.relief = relief;
	}

	public java.util.Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(java.util.Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	
	
}
