package com.shangde.edu.ad.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.MD5;
import com.shangde.edu.ad.condition.QueryCHANETOrderQueryCondition;
import com.shangde.edu.ad.dto.CHANETOrderQueryDTO;
import com.shangde.edu.ad.service.CHANETService;

/**
 * <p>
 * Description:
 * </p>
 * Author: . <br>
 * Date: Jul 11, 2012 <br>
 * Time: 3:45:58 PM <br>
 */
public class ChaNetAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(ChaNetAction.class);
	// 推广来源
	private static String WEB_FROM = "chanet";
	// 服务类
	private CHANETService chanetservice;
	// 接受请求超时时间（单位秒）
	private static long TIMEOUT = 600;
	// 返回给成果网的错误信息
	private static String ERROR_TIMEOUT = "检查超时";
	// 返回给成果网的错误信息
	private static String ERROR_INCORRECT_SIGN = "签名验证失败";
	// 密钥 - 由成果网提供
	private final static String CP_KEY = "4v1CujWCsiKlBpAI";
	//成果网约定产品分类
	private final static String PRODUCT_TYPE = "GOODS1";
	// --------------成果网传递的参数列表开始--------------------
	// B2C商户分配给成果网的用户名 ，必填
	private String user;
	// 开始时间（非必须）
	private String start;
	// 结束时间（非必须）
	private String end;
	// 订单号（非必须）
	private String orderid;
	// 订单状态（非必须）
	private String orderstatus;
	// 成果网unix时间戳
	private int unixtime;
	// 签名
	private String sig;

	// -----------成果网传递参数列表结束------------------------
	/**
	 * 用于成果网订单查询的接口 @ after milk pizza
	 */
	public String orderQuery() {
		long now = System.currentTimeMillis() / 1000; // 成果网使用Unix时间戳，单位是秒
		// 响应流 用于对查询结果的响应
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		// 查询条件
		QueryCHANETOrderQueryCondition queryCHANETOrderQueryCondition = new QueryCHANETOrderQueryCondition();
		queryCHANETOrderQueryCondition.setWebFrom(WEB_FROM);
		
		logger.debug("the orderQuery Method has entered……the request has been handle……please waiting");
		// 1.超时判断
		if (!checkActiveTime(unixtime, now)) {
			try {
				processError(response, ERROR_TIMEOUT);
			} catch (IOException e) {
				logger.error("ChaNetActionError", e);
			}
			return null;
		}
		// 需要进行判断，没有参数值的参数不需要参与签名。
		StringBuilder sb = new StringBuilder("user="+user);
		if(null!=start && !start.equals("")){
			sb.append("&start="+start);
			queryCHANETOrderQueryCondition.setStart(start);
		}
		if(null!=end && !end.equals("")){
			sb.append("&end="+end);
			queryCHANETOrderQueryCondition.setEnd(end);
		}
		if(null!=orderid && !orderid.equals("")){
			sb.append("&orderid="+orderid);
			queryCHANETOrderQueryCondition.setOrderid(orderid);
		}
		if(null!=orderstatus && !orderstatus.equals("")){
			sb.append("&orderstatus="+orderstatus);
			/**因成果网的需要查询的订单状态与本地不匹配所以只提供四种状态查询
			 * 成功下单	0	用户成功下单（未支付）
			   已支付	    1	用户成功支付订单（含所有支付方式）
			   已退货	    4	用户收到商品后提出退货
			   已取消	    5	用户支付后，但未收到商品时，提出取消订单
			   本地状态为：
			   0未付　1已付　3退费,4取消;
			 */
			if(orderstatus.equals("4")){
				queryCHANETOrderQueryCondition.setOrderstatus("3");
			}else if(orderstatus.equals("5")){
				queryCHANETOrderQueryCondition.setOrderstatus("4");
			}else if(orderstatus.equals("0") || orderstatus.equals("1")){
				queryCHANETOrderQueryCondition.setOrderstatus(orderstatus);
			}else{
				queryCHANETOrderQueryCondition.setOrderstatus("9");
			}
		}
		sb.append("&unixtime="+unixtime+"&key="+CP_KEY);
		String signCheck = MD5.getMD5(sb.toString());
		// 2.签名判断
		if (!signCheck.equals(sig)) {
			try {
				processError(response, ERROR_INCORRECT_SIGN);
			} catch (IOException e) {
				logger.error("ChaNetActionError", e);
			}
			return null;
		}
		// 执行查询
		List<CHANETOrderQueryDTO> list = chanetservice.orderQuery(queryCHANETOrderQueryCondition);
		// 转换数据
		try {
			list = processOrderQueryList(list);
		} catch (Exception e) {
			logger.error("ChaNetActionError", e);
		}
		
		// 将订单结果集拼装为String 格式
		String xmlResult = generateString(list);
		// 将数据写入到流
		response.setHeader("Content-type", "text/html; charset=utf-8");
		try {
			response.getWriter().write(xmlResult);
		} catch (IOException e) {
			logger.error("ChaNetActionError", e);
		}
		return null;
	}

	/** 处理订单数据，转化为符合成果网定义的形式（订单查询） */
	private List<CHANETOrderQueryDTO> processOrderQueryList(List<CHANETOrderQueryDTO> orderList) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (orderList != null && orderList.size() > 0) {
			for (CHANETOrderQueryDTO dto : orderList) {
				dto.setOrderTime(sdf.format(dto.getCreateTime()));
				dto.setSid(dto.getWi());
				dto.setOrderId(dto.getContractId());
				dto.setProductPrice(dto.getContractSumMoney().toString());
				// 订单类型转换
				dto.setStatus(calcStatus(dto.getLocalStatus()));
				// 支付类型转换
				dto.setPayType(calcPayType(dto.getPayTypez()));
			}
		}
		return orderList;
	}

	/** 返回错误信息 */
	private void processError(HttpServletResponse response, String msg) throws IOException {
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.getWriter().write(msg);
	}

	/** 有效时间验证 */
	private boolean checkActiveTime(int unixtime, long now) {
		long actTime = Long.parseLong(unixtime + "");
		if (Math.abs(now - actTime) > TIMEOUT) {
			return false;
		}
		return true;
	}

	private String generateString(List<CHANETOrderQueryDTO> list) {
		StringBuffer sb = new StringBuffer(1000);
		for (CHANETOrderQueryDTO dto : list) {
			dto.setProductCount(1);
			dto.setProductType(PRODUCT_TYPE);
			sb.append(dto.getOrderTime());
			sb.append("\t");
			sb.append(dto.getSid());
			sb.append("\t");
			sb.append(dto.getOrderId());
			sb.append("\t");
			sb.append(dto.getProductType());
			sb.append("\t");
			sb.append(dto.getProductCount());
			sb.append("\t");
			sb.append(dto.getProductPrice());
			sb.append("\t");
			sb.append(dto.getProductName());
			sb.append("\t");
			sb.append(dto.getPayType());
			sb.append("\t");
			sb.append(dto.getStatus());
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * 计算订单的状态 本地订单状态： 未支付:0; 已支付:1; 赠送:2; 取消:3; 退费:4。 
	 * 成果网订单状态： 0 用户成功下单（未支付）； 1用户成功支付订单（含所有支付方式） 2 商家发出商品 3 用户收到并签收商品4 用户收到商品后提出退货 
	 * 5 用户支付后，但未收到商品时，提出取消订单 6 用户收到商品，并过退换货保障期，订单完成7 订单商品缺货，无法发货 8 订单商品含预售商品，无法发货
	 * @param localStatus 本地状态
	 * 
	 * @return 成果网订单状态
	 */

	private String calcStatus(Integer localStatus) {
		String result = "0";
		switch (localStatus) {
		case 0:
			result = "0"; // 本地0表示“未支付”，返回值0表示“新订单”
			break;
		case 1:
			result = "1"; // 本地1表示“已支付”，返回值1表示“用户成功支付”
			break;
		case 2:
			result = "5"; // 本地2表示“赠送”，返回值5表示“已作废(已取消)”
			break;
		case 3:
			result = "5"; // 本地3表示“退费(取消)”，返回值5表示“已作废(已取消)”
			break;
		case 4:
			result = "5";// 本地4表示“退费(取消)”，返回值5表示“已作废(已取消)”
			break;
		}
		return result;
	}

	/**
	 * 支付类型的转换 本地支付类型： 0：赠送 ；1 ：网上支付； 2：货到付款； 3：网银在线；4：块钱； 5:代理商开通
	 * 合作方（成果网）支付方式说明： 货到付款: 1 在线支付-支付宝 2 在线支付-财付通 3 在线支付-快钱 4 在线支付-其他 5 邮局汇款 6
	 * 银行转帐 7 礼券支付 8 其他 9
	 */
	private String calcPayType(int payTypez) {
		String result = "0";
		switch (payTypez) {
		case 0:
			result = "9"; // 本地0表示赠送，返回值9表示其他
			break;
		case 1:
			result = "5";// 本地0表示网上支付，返回值5表示在线支付
			break;
		case 2:
			result = "1";// 本地2表示货到付款，返回值1表示货到付款
			break;
		case 3:
			result = "4";// 本地3表示网银在线，返回值4表示在线支付其他
			break;
		case 4:
			result = "4";// 本地4表示块钱支付，返回值4表示块钱支付
			break;
		case 5:
			result = "9";// 本地5表示货到付款，返回值9表示货到付款
			break;
		}
		return result;
	}
	/**
	 * @return the chanetservice
	 */
	public CHANETService getChanetservice() {
		return chanetservice;
	}

	/**
	 * @param chanetservice the chanetservice to set
	 */
	public void setChanetservice(CHANETService chanetservice) {
		this.chanetservice = chanetservice;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the start
	 */
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * @return the orderid
	 */
	public String getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the orderstatus
	 */
	public String getOrderstatus() {
		return orderstatus;
	}

	/**
	 * @param orderstatus the orderstatus to set
	 */
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	/**
	 * @return the unixtime
	 */
	public int getUnixtime() {
		return unixtime;
	}

	/**
	 * @param unixtime the unixtime to set
	 */
	public void setUnixtime(int unixtime) {
		this.unixtime = unixtime;
	}

	/**
	 * @return the sig
	 */
	public String getSig() {
		return sig;
	}

	/**
	 * @param sig the sig to set
	 */
	public void setSig(String sig) {
		this.sig = sig;
	}
	
/*	本地测试代码
 * public static void main(String args[]){
		//sig=md5(user=chanet&start=20110310090000&end=20110315090000&unixtime=1300260174&key=B213oFmuoig)
		String wMD5 = "user=chanet&start=20120101090000&end=20121231090000&orderid=4730301343205340840&unixtime="+System.currentTimeMillis()/1000+"&key=8E8889EBB2DDD534E9E6BEE667FA9F77";
		System.out.println(System.currentTimeMillis()/1000);
	    String result = MD5.getMD5(wMD5);
	    System.out.println(result);
	    clientTest();
	}
	
	public static void clientTest(){
		HttpClient client = new HttpClient();
		long unixtime = System.currentTimeMillis()/1000;
		String wMD5 = "user=chanet&start=20120101090000&end=20121231090000&orderid=4730301343205340840&unixtime="+unixtime+"&key="+CP_KEY;
		String sid = MD5.getMD5(wMD5);
		String url="http://172.16.122.55:8080/seduadmin/ad/cpschanet!orderQuery.action?user=chanet&start=20120101090000&end=20121231090000&orderid=4730301343205340840&unixtime="+unixtime+"&key="+CP_KEY+"&sig="+sid;
		System.out.println(url);
		GetMethod method = new GetMethod(url);
		try {
		client.executeMethod(method);
		System.out.println(method.getResponseBodyAsString());
		} catch (HttpException e) {
			logger.error("CustomerWebAction.HttpException", e);
		} catch (IOException e) {
			logger.error("CustomerWebAction.IOException", e);
		} catch (StringIndexOutOfBoundsException e){
			logger.error("CustomerWebAction.StringIndexOutOfBoundsException", e);
		} 
	}*/
}
