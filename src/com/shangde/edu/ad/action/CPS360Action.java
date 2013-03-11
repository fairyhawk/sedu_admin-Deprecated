package com.shangde.edu.ad.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.MD5;
import com.shangde.common.util.StringUtil;
import com.shangde.edu.ad.condition.QueryCPS360OrderCheckCondition;
import com.shangde.edu.ad.condition.QueryCPS360OrderQueryCondition;
import com.shangde.edu.ad.dto.CPS360OrderCheckDTO;
import com.shangde.edu.ad.dto.CPS360OrderQueryDTO;
import com.shangde.edu.ad.service.CPS360Service;

public class CPS360Action extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CPS360Action.class);

	// 推广来源
	private static String WEB_FROM = "360cps";

	// 密钥 - 由360提供
	private static String CP_KEY = "8E8889EBB2DDD534E9E6BEE667FA9F77";

	// 合作编号 - 由360提供
	private static String BID = "20120458";

	// 接受请求超时时间（单位秒）
	private static long TIMEOUT = 900;

	// 佣金比率（40%）
	private static BigDecimal COMMISSION_RATIO = new BigDecimal("0.45");

	// 返回给360的错误信息
	private static String ERROR_TIMEOUT = "检查超时";

	// 返回给360的错误信息
	private static String ERROR_INCORRECT_SIGN = "签名验证失败";

	// 货币格式化
	private DecimalFormat decimalFormat = new DecimalFormat("###0.00");

	private CPS360Service cps360Service;

	// 360传递的值-----------开始----------------------------

	private String bid;
	private String order_ids;
	private String start_time;
	private String end_time;
	private String updstart_time;
	private String updend_time;
	private String last_order_id;
	private String active_time;
	private String sign;
	private String bill_month;

	// 360传递的值-----------结束----------------------------

	/** 订单查询接口 */
	public String orderQuery() {
		try {
			long now = System.currentTimeMillis() / 1000; // 360使用Unix时间戳，单位是秒
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			String orderIds = getOrder_ids();
			String startTime = getStart_time();
			String endTime = getEnd_time();
			String updstartTime = getUpdstart_time();
			String updendTime = getUpdend_time();
			String lastOrderId = getLast_order_id();
			String activeTime = getActive_time();
			String sign = getSign();
			// 有效期验证
			if (!checkActiveTime(activeTime, now)) {
				processError(response, ERROR_TIMEOUT);
				return null;
			}
			// 签名验证
			String signCheck = MD5.getMD5(BID + "#" + activeTime + "#" + CP_KEY);
			if (!signCheck.equals(sign)) {
				processError(response, ERROR_INCORRECT_SIGN);
				return null;
			}
			// 初始化查询对象
			QueryCPS360OrderQueryCondition condition = new QueryCPS360OrderQueryCondition();
			condition.setWebFrom(WEB_FROM);
			condition.setLastOrderId(lastOrderId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			// 通过订单id查询
			if (!StringUtil.isNullString(orderIds)) {
				if (orderIds.indexOf(",") != -1) {
					condition.setOrderIds(orderIds.split(","));
				} else {
					condition.setOrderIds(new String[] { orderIds });
				}
			}
			// 通过下单时间查询
			else if (!StringUtil.isNullString(startTime) && !StringUtil.isNullString(endTime)) {
				condition.setStartTime(sdf.parse(startTime));
				condition.setEndTime(sdf.parse(endTime));
			}
			// 通过最后更新时间查询
			else if (!StringUtil.isNullString(updstartTime) && !StringUtil.isNullString(updendTime)) {
				condition.setUpdendTime(sdf.parse(updstartTime));
				condition.setUpdendTime(sdf.parse(updendTime));
			}
			// 执行查询
			List<CPS360OrderQueryDTO> list = cps360Service.orderQuery(condition);
			// 处理订单数据，转化为符合360定义的形式
			list = processOrderQueryList(list);
			// 将订单结果集拼装为XML格式
			String xmlResult = generateOrderQueryListXML(list);
			// 将XML直接输出
			response.setHeader("Content-type", "text/xml; charset=utf-8");
			response.getWriter().write(xmlResult);
		} catch (Exception e) {
			logger.error("CPS360Action.orderQuery", e);
		}
		return null;
	}

	/** 对账查询接口 */
	public String orderCheck() {
		try {
			long now = System.currentTimeMillis() / 1000; // 360使用Unix时间戳，单位是秒
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			String activeTime = getActive_time();
			String billMonth = getBill_month();
			String lastOrderId = getLast_order_id();
			String sign = getSign();
			// 有效期验证
			if (!checkActiveTime(activeTime, now)) {
				processError(response, ERROR_TIMEOUT);
				return null;
			}
			// 签名验证
			String signCheck = MD5.getMD5(BID + "#" + activeTime + "#" + CP_KEY);
			if (!signCheck.equals(sign)) {
				processError(response, ERROR_INCORRECT_SIGN);
				return null;
			}
			// 通过月份和上次获取的最后一个订单id来获取对账订单
			QueryCPS360OrderCheckCondition condition = new QueryCPS360OrderCheckCondition();
			condition.setWebFrom(WEB_FROM);
			condition.setLastOrderId(lastOrderId);
			if (!StringUtil.isNullString(billMonth) && billMonth.indexOf("-") != -1) {
				String[] parts = billMonth.split("-");
				condition.setBillMonthYear(Integer.valueOf(parts[0]));
				condition.setBillMonthMonth(Integer.valueOf(parts[1]));
			}
			// 执行查询
			List<CPS360OrderCheckDTO> list = cps360Service.orderCheck(condition);
			// 处理订单数据，转化为符合360定义的形式
			list = processOrderCheckList(list);
			// 将订单结果集拼装为XML格式
			String xmlResult = generateOrderCheckListXML(list);
			// 将XML直接输出
			response.setHeader("Content-type", "text/xml; charset=utf-8");
			
			response.getWriter().write(xmlResult);
		} catch (Exception e) {
			logger.error("CPS360Action.orderCheck", e);
		}
		return null;
	}

	/** 有效时间验证 */
	private boolean checkActiveTime(String activeTime, long now) {
		long actTime = Long.parseLong(activeTime);
		if (Math.abs(now - actTime) > TIMEOUT) {
			return false;
		}
		return true;
	}

	/** 返回错误信息 */
	private void processError(HttpServletResponse response, String msg) throws IOException {
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.getWriter().write(msg);
	}

	/** 处理订单数据，转化为符合360定义的形式（订单查询） */
	private List<CPS360OrderQueryDTO> processOrderQueryList(List<CPS360OrderQueryDTO> orderList) throws Exception {
		if (orderList != null && orderList.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (CPS360OrderQueryDTO dto : orderList) {
				dto.setBid(BID);
				dto.setQid(dto.getWi());
				dto.setQihooId(dto.getCid());
				dto.setExt(dto.getSrc());
				dto.setOrderId(dto.getContractId());
				dto.setOrderTime(sdf.format(dto.getCreateTime()));
				dto.setOrderUpdtime(sdf.format(dto.getCreateTime()));
				dto.setTotalComm(calcTotalComm(dto.getContractCutSumMoney()));
				dto.setCommission(calcCommission(dto.getContractSumMoney(), dto.getContractCutSumMoney(), dto.getCouponMoney()));
				dto.setPInfo(calcPInfo(dto.getContractSumMoney()));
				dto.setServerPrice(calcServerPrice(dto.getFreight()));
				dto.setTotalPrice(calcTotalPrice(dto.getContractCutSumMoney()));
				dto.setCoupon(calcCoupon(dto.getCouponMoney()));
				dto.setStatus(calcStatus(dto.getLocalStatus()));
			}
		}
		return orderList;
	}

	/** 处理订单数据，转化为符合360定义的形式（对账查询） */
	private List<CPS360OrderCheckDTO> processOrderCheckList(List<CPS360OrderCheckDTO> list) {
		if (list != null && list.size() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for (CPS360OrderCheckDTO dto : list) {
				dto.setOrderId(dto.getContractId());
				dto.setOrderTime(sdf.format(dto.getCreateTime()));
				dto.setOrderUpdtime(sdf.format(dto.getCreateTime()));
				dto.setServerPrice(calcServerPrice(dto.getFreight()));
				dto.setTotalPrice(calcTotalPrice(dto.getContractSumMoney()));
				dto.setCoupon(calcCoupon(dto.getCouponMoney()));
				dto.setTotalComm(calcTotalComm(dto.getContractCutSumMoney()));
				dto.setCommission(calcCommission(dto.getContractSumMoney(), dto.getContractCutSumMoney(), dto.getCouponMoney()));
			}
		}
		return list;
	}

	/**
	 * 计算服务费用，如运送费等。单位:元。
	 * 
	 * @param freight 运费
	 * 
	 * @return 服务费用
	 */
	private String calcServerPrice(BigDecimal freight) {
		return decimalFormat.format(freight);
	}

	/**
	 * 计算订单应付金额。单位:元。
	 * 
	 * 订单应付金额 = 订单总价(不含服务费，运费等) - 优惠券金额
	 * 
	 * @param contractCutSumMoney 实付金额
	 * 
	 * @return 订单应付金额
	 */
	private String calcTotalPrice(BigDecimal contractCutSumMoney) {
		return decimalFormat.format(contractCutSumMoney);
	}

	/**
	 * 计算用户使用优惠卡或积分抵充商品的金额。单位:元。
	 * 
	 * @param couponMoney 优惠券金额
	 * 
	 * @return 抵充商品的金额
	 */
	private String calcCoupon(BigDecimal couponMoney) {
		return decimalFormat.format(couponMoney);
	}

	/**
	 * 计算总佣金。
	 * 
	 * 按照用户实际应支付的商品价格计算,即扣除优惠券和运费部分。
	 * 
	 * @param contractCutSumMoney 折后价
	 * 
	 * @return 总佣金
	 */
	private String calcTotalComm(BigDecimal contractCutSumMoney) {
		return decimalFormat.format(contractCutSumMoney.multiply(COMMISSION_RATIO));
	}

	/**
	 * 计算佣金明细。
	 * 
	 * @param contractSumMoney 总金额
	 * 
	 * @param contractCutSumMoney 实付金额
	 * 
	 * @param couponMoney 折扣金额
	 * 
	 * @return 佣金明细
	 * 
	 * 格式：
	 * 
	 * 商品分类id,分成比例,分成金额,商品单价,数量|商品分类id,分成比例,分成金额,商品单价,数量|优惠券扣除佣金额
	 * 
	 */
	private String calcCommission(BigDecimal contractSumMoney, BigDecimal contractCutSumMoney, BigDecimal couponMoney) {
		String commissionRatioPercent = new DecimalFormat("####").format(COMMISSION_RATIO.multiply(new BigDecimal("100"))) + "%";
		String couponCommission = decimalFormat.format(couponMoney.multiply(COMMISSION_RATIO));
		String result = "1," + commissionRatioPercent + "," + calcTotalComm(contractSumMoney) + ","
				+ decimalFormat.format(contractSumMoney) + "," + "1" + "|" + couponCommission;
		return result;
	}

	/**
	 * 计算订单商品的详细信息。
	 * 
	 * @param contractSumMoney 总金额
	 * 
	 * @return 订单商品的详细信息
	 * 
	 * 格式：
	 * 
	 * 商品分类id,商品名称,商品编号,商品单价,商品数量,商品一级分类名称_二级分类名称_商品当前分类名称,商品URL|
	 * 商品分类id,商品名称,商品编号,商品单价,商品数量,商品一级分类名称_二级分类名称_商品当前分类名称,商品URL
	 * 
	 * @throws UnsupportedEncodingException
	 */
	private String calcPInfo(BigDecimal contractSumMoney) throws UnsupportedEncodingException {
		String result = "1,highso视频课程,1," + decimalFormat.format(contractSumMoney) + ",1,视频课程,"
				+ URLEncoder.encode("http://highso.cn", "UTF-8");
		return result;
	}

	/**
	 * 计算订单状态。
	 * 
	 * 本地订单状态： 未支付:0; 已支付:1; 赠送:2; 取消:3; 退费:4。
	 * 
	 * 360订单状态： 新订单:1; 已确认(尚未发货和支付):2; 已发货:3; 已支付:4; 已完成:5; 已作废(已取消):6。
	 * 
	 * @param localStatus 本地状态
	 * 
	 * @return 360订单状态
	 */
	private String calcStatus(Integer localStatus) {
		String result = "0";
		switch (localStatus) {
		case 0:
			result = "1"; // 本地0表示“未支付”，返回值1表示“新订单”
			break;
		case 1:
			result = "5"; // 本地1表示“已支付”，返回值5表示“已完成”
			break;
		case 2:
			result = "6"; // 本地2表示“赠送”，返回值6表示“已作废(已取消)”
			break;
		case 3:
			result = "6"; // 本地3表示“退费(取消)”，返回值6表示“已作废(已取消)”
			break;
		case 4:
			result = "6";// 本地3表示“退费(取消)”，返回值6表示“已作废(已取消)”
			break;
		}
		return result;
	}

	/**
	 * 将订单信息集合转化为XML形式（订单查询）。
	 * 
	 * @param list 订单信息集合
	 * @return XML文本
	 */
	private String generateOrderQueryListXML(List<CPS360OrderQueryDTO> list) {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<orders>");
		for (CPS360OrderQueryDTO dto : list) {
			sb.append("<order>");
			appendSimpleXMLNode(sb, "bid", dto.getBid(), BID);
			appendSimpleXMLNode(sb, "qid", dto.getQid(), "0");
			appendSimpleXMLNode(sb, "qihoo_id", dto.getQihooId(), "0");
			appendSimpleXMLNode(sb, "ext", dto.getExt(), "");
			appendSimpleXMLNode(sb, "order_id", dto.getOrderId(), "");
			appendSimpleXMLNode(sb, "order_time", dto.getOrderTime(), "");
			appendSimpleXMLNode(sb, "order_updtime", dto.getOrderUpdtime(), "");
			appendSimpleXMLNode(sb, "server_price", dto.getServerPrice(), "");
			appendSimpleXMLNode(sb, "total_price", dto.getTotalPrice(), "");
			appendSimpleXMLNode(sb, "coupon", dto.getCoupon(), "");
			appendSimpleXMLNode(sb, "total_comm", dto.getTotalComm(), "");
			appendSimpleXMLNode(sb, "commission", dto.getCommission(), "");
			appendSimpleXMLNode(sb, "p_info", dto.getPInfo(), "");
			appendSimpleXMLNode(sb, "status", dto.getStatus(), "");
			sb.append("</order>");
		}
		sb.append("</orders>");
		return sb.toString();
	}

	/**
	 * 将订单信息集合转化为XML形式（对账查询）。
	 * 
	 * @param list 订单信息集合
	 * @return XML文本
	 */
	private String generateOrderCheckListXML(List<CPS360OrderCheckDTO> list) {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<orders>");
		for (CPS360OrderCheckDTO dto : list) {
			sb.append("<order>");
			appendSimpleXMLNode(sb, "order_id", dto.getOrderId(), "");
			appendSimpleXMLNode(sb, "order_time", dto.getOrderTime(), "");
			appendSimpleXMLNode(sb, "order_updtime", dto.getOrderUpdtime(), "");
			appendSimpleXMLNode(sb, "server_price", dto.getServerPrice(), "");
			appendSimpleXMLNode(sb, "total_price", dto.getTotalPrice(), "");
			appendSimpleXMLNode(sb, "coupon", dto.getCoupon(), "");
			appendSimpleXMLNode(sb, "total_comm", dto.getTotalComm(), "");
			appendSimpleXMLNode(sb, "commission", dto.getCommission(), "");
			sb.append("</order>");
		}
		sb.append("</orders>");
		return sb.toString();
	}

	/**
	 * 为指定的StringBuffer的尾部添加一个XML Node。只适用于没有属性的Node。
	 * 
	 * 例：若参数element为"node"，参数value为"123"， 则添加的内容为：<node>123</node>。
	 * 
	 * @param sb 指定的StringBuffer对象
	 * @param element Node名称
	 * @param value Node值
	 * @param defaultValue Node默认值（当value为null时）
	 * 
	 * @return StringBuffer对象
	 */
	private StringBuffer appendSimpleXMLNode(StringBuffer sb, String element, String value, String defaultValue) {
		sb.append("<");
		sb.append(element);
		sb.append(">");
		if (value == null) {
			sb.append(defaultValue);
		} else {
			sb.append(value);
		}
		sb.append("</");
		sb.append(element);
		sb.append(">");
		return sb;
	}

	// getters and setters.

	public void setCps360Service(CPS360Service cps360Service) {
		this.cps360Service = cps360Service;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getOrder_ids() {
		return order_ids;
	}

	public void setOrder_ids(String order_ids) {
		this.order_ids = order_ids;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getUpdstart_time() {
		return updstart_time;
	}

	public void setUpdstart_time(String updstart_time) {
		this.updstart_time = updstart_time;
	}

	public String getUpdend_time() {
		return updend_time;
	}

	public void setUpdend_time(String updend_time) {
		this.updend_time = updend_time;
	}

	public String getLast_order_id() {
		return last_order_id;
	}

	public void setLast_order_id(String last_order_id) {
		this.last_order_id = last_order_id;
	}

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBill_month() {
		return bill_month;
	}

	public void setBill_month(String bill_month) {
		this.bill_month = bill_month;
	}

}
