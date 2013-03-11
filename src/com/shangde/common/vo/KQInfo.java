package com.shangde.common.vo;

public class KQInfo {

	/**
	 * 人民币网关账户号
	 * 请登录快钱系统获取用户编号，用户编号后加01即为人民币网关账户号。
	 */
	private String merchantAcctId = "1002098391201";
	
	/**
	 * 人民币网关密钥
	 * 区分大小写.请与快钱联系索取
	 */
	private String key = "G95WAE26LLTY98IS";

	/**
	 * 字符集.固定选择值。可为空。
	 * 只能选择1、2、3.
	 * 1代表UTF-8; 2代表GBK; 3代表gb2312
	 * 默认值为1
	 */
	private String inputCharset = "1";

	/**
	 * 接受支付结果的页面地址.与[bgUrl]不能同时为空。必须是绝对地址。
	 * 如果[bgUrl]为空，快钱将支付结果Post到[pageUrl]对应的地址。
	 * 如果[bgUrl]不为空，并且[bgUrl]页面指定的<redirecturl>地址不为空，则转向到<redirecturl>对应的地址
	 */
	private String pageUrl = "http://highso.net.cn/alipay/KQ!returnURL.action";

	/**
	 * 服务器接受支付结果的后台地址.与[pageUrl]不能同时为空。必须是绝对地址。
	 * 快钱通过服务器连接的方式将交易结果发送到[bgUrl]对应的页面地址，在商户处理完成后输出的<result>如果为1，页面会转向到<redirecturl>对应的地址。
	 * 如果快钱未接收到<redirecturl>对应的地址，快钱将把支付结果post到[pageUrl]对应的页面。
	 */
	private String bgUrl = "http://highso.net.cn/alipay/KQ!returnURL.action";
		
	/**
	 * 网关版本.固定值
	 * 快钱会根据版本号来调用对应的接口处理程序。
	 * 本代码版本号固定为v2.0
	 */
	private String version = "v2.0";

	/**
	 * 语言种类.固定选择值。
	 * 只能选择1、2、3
	 * 1代表中文；2代表英文
	 * 默认值为1
	 */
	private String language = "1";

	/**
	 * 签名类型.固定值
	 * 1代表MD5签名
	 * 当前版本固定为1
	 */
	private String signType = "1";
	   
	/**
	 * 支付人姓名
	 * 可为中文或英文字符
	 */
	private String payerName;

	/**
	 * 支付人联系方式类型.固定选择值
	 * 只能选择1
	 * 1代表Email
	 */
	private String payerContactType = "1";

	/**
	 * 支付人联系方式
	 * 只能选择Email或手机号
	 */
	private String payerContact = "";

	/**
	 * 商户订单号
	 * 由字母、数字、或[-][_]组成
	 */
	private String orderId;

	/**
	 * 订单金额
	 * 以分为单位，必须是整型数字
	 * 比方2，代表0.02元
	 */
	private String orderAmount;
		
	/**
	 * 订单提交时间
	 * 14位数字。年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位]
	 * 如；20080101010101
	 */
	private String orderTime = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());

	/**
	 * 商品名称
	 * 可为中文或英文字符
	 */
	private String productName = "productName";

	/**
	 * 商品数量
	 * 可为空，非空时必须为数字
	 */
	private String productNum = "1";

	/**
	 * 商品代码
	 * 可为字符或者数字
	 */
	private String productId;

	/**
	 * 商品描述
	 */
	private String productDesc;
		
	/**
	 * 扩展字段1
	 * 在支付结束后原样返回给商户
	 */
	private String ext1;

	/**
	 * 扩展字段2
	 * 在支付结束后原样返回给商户
	 */
	private String ext2;
		
	/**
	 * 支付方式.固定选择值
	 * 只能选择00、10、11、12、13、14
	 * 00：组合支付（网关支付页面显示快钱支持的各种支付方式，推荐使用）10：银行卡支付（网关支付页面只显示银行卡支付）.11：电话银行支付（网关支付页面只显示电话支付）.12：快钱账户支付（网关支付页面只显示快钱账户支付）.13：线下支付（网关支付页面只显示线下支付方式）.14：B2B支付（网关支付页面只显示B2B支付，但需要向快钱申请开通才能使用）
	 */
	private String payType = "00";

	/**
	 * 银行代码
	 * 实现直接跳转到银行页面去支付,只在payType=10时才需设置参数
	 * 具体代码参见 接口文档银行代码列表
	 */
	private String bankId;

	/**
	 * 同一订单禁止重复提交标志
	 * 固定选择值： 1、0
	 * 1代表同一订单号只允许提交1次；0表示同一订单号在没有支付成功的前提下可重复提交多次。默认为0建议实物购物车结算类商户采用0；虚拟产品类商户采用1
	 */
	private String redoFlag = "0";

	/**
	 * 快钱的合作伙伴的账户号
	 * 如未和快钱签订代理合作协议，不需要填写本参数
	 */
	private String pid;
	
	/**
	 * 加密签名串
	 */
	private String signMsg;
	
	/**
	 * 交易号
	 */
	private String dealId;
	
	/**
	 * 银行交易号
	 */
	private String bankDealId;
	
	/**
	 * 交易时间
	 */
	private String dealTime;
	
	/**
	 * 实际交易金额
	 */
	private String payAmount;
	
	/**
	 * 交易手续费
	 */
	private String fee;
	
	/**
	 * 处理结果
	 */
	private String payResult;
	
	/**
	 * 错误代码
	 */
	private String errCode;
	
	/**
	 * 返回加密串
	 */
	private String merchantSignMsgVal;
	
	/**
	 * 返回结果
	 */
	private int rtnOk;
	
	/**
	 * 返回地址
	 */
	private String rtnUrl;

	public String getMerchantAcctId() {
		return merchantAcctId;
	}

	public void setMerchantAcctId(String merchantAcctId) {
		this.merchantAcctId = merchantAcctId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerContactType() {
		return payerContactType;
	}

	public void setPayerContactType(String payerContactType) {
		this.payerContactType = payerContactType;
	}

	public String getPayerContact() {
		return payerContact;
	}

	public void setPayerContact(String payerContact) {
		this.payerContact = payerContact;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNum() {
		return productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getRedoFlag() {
		return redoFlag;
	}

	public void setRedoFlag(String redoFlag) {
		this.redoFlag = redoFlag;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getSignMsg() {
		return signMsg;
	}

	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getBankDealId() {
		return bankDealId;
	}

	public void setBankDealId(String bankDealId) {
		this.bankDealId = bankDealId;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getPayResult() {
		return payResult;
	}

	public void setPayResult(String payResult) {
		this.payResult = payResult;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getMerchantSignMsgVal() {
		return merchantSignMsgVal;
	}

	public void setMerchantSignMsgVal(String merchantSignMsgVal) {
		this.merchantSignMsgVal = merchantSignMsgVal;
	}

	public int getRtnOk() {
		return rtnOk;
	}

	public void setRtnOk(int rtnOk) {
		this.rtnOk = rtnOk;
	}

	public String getRtnUrl() {
		return rtnUrl;
	}

	public void setRtnUrl(String rtnUrl) {
		this.rtnUrl = rtnUrl;
	}
}
