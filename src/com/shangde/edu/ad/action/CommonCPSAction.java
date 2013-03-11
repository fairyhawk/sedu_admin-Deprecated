package com.shangde.edu.ad.action;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.shangde.edu.ad.condition.QueryCommonOrderQueryCondition;
import com.shangde.edu.ad.service.CommonCPSService;
import com.shangde.edu.finance.domain.Contract;

/**
 * <br>
 * <b>功能：</b>查询接口 <br>
 * <b>作者：</b>李志强 Kobe.Lee<br>
 * <b>日期：</b> 2012.07.27 <br>
 * 
 * @return
 */
public class CommonCPSAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CommonCPSAction.class);
	// 服务类
	private CommonCPSService commonCPSService;

	// 唯一传媒查询数据
	private String unionId; //
	private String pwd;
	private String starttime;
	private String endtime;

	// 51比购查询数据
	private String unionid;//与51返利通用
	private String sdate;
	private String edate;

	//51返利查询数据
	private String begin_date;
	private String end_date;
	
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 用于唯一传媒查询接口
	 */
	public String onlyOrderQuery() {
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		// 查询条件
		QueryCommonOrderQueryCondition queryCondition = loadData(unionId);
		logger.debug("the orderQuery Method has entered……the request has been handle……please waiting");
		// 执行查询
		List<Contract> list = commonCPSService.orderQuery(queryCondition);
		// 将订单结果集拼装为String 格式
		StringBuffer sb = new StringBuffer(1000);
		for (Contract contract : list) {
			sb.append(sdf.format(contract.getCreateTime()));
			sb.append("\t");
			sb.append(contract.getWi());
			sb.append("\t");
			sb.append("");
			sb.append("\t");
			sb.append(contract.getContractId());
			sb.append("\t");
			sb.append(contract.getContractId());
			sb.append("\t");
			sb.append("");
			sb.append("\t");
			sb.append(1);
			sb.append("\t");
			sb.append(contract.getContractSumMoney());
			sb.append("\t");
			sb.append(contract.getStatus());
			sb.append("\n");
		}
		String xmlResult = sb.toString();
		// 将数据写入到流
		response.setHeader("Content-type", "text/html; charset=utf-8");
		try {
			response.getWriter().write(xmlResult);
		} catch (IOException e) {
			logger.error("CommonCPSActionError", e);
		}
		return null;
	}

	//向Condition中装载数据
	private QueryCommonOrderQueryCondition loadData(String webFrom) {
		QueryCommonOrderQueryCondition queryCondition = new QueryCommonOrderQueryCondition();
		if(webFrom.equals("weiyi")){
			queryCondition.setWebFrom(webFrom);
			queryCondition.setStarttime(starttime);
			queryCondition.setEndtime(endtime);
		}else if(webFrom.equals("51bi")){
			queryCondition.setWebFrom(webFrom);
			queryCondition.setStarttime(sdate);
			queryCondition.setEndtime(edate);
		}else if(webFrom.equals("51fanli")){
			queryCondition.setWebFrom(webFrom);
			queryCondition.setStarttime(begin_date);
			queryCondition.setEndtime(end_date);
		}
		return queryCondition;
	}
	
	private String commRate = "0.35";
	/**
	 * 用于51比购查询接口
	 */
	public String biOrderQuery() {
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
		// 查询条件
		QueryCommonOrderQueryCondition queryCondition = loadData(unionid);
		logger.debug("the orderQuery Method has entered……the request has been handle……please waiting");
		// 执行查询
		List<Contract> list = commonCPSService.orderQuery(queryCondition);
		// 将订单结果集拼装为String 格式
		sdf.applyPattern("yyyyMMddHHmmss");
		StringBuffer sb = new StringBuffer(1000);
		for (Contract contract : list) {
			sb.append(sdf.format(contract.getCreateTime()));
			sb.append("|");
			sb.append(contract.getContractId());
			sb.append("|");
			sb.append(contract.getWi());
			sb.append("|");
			sb.append(contract.getContractSumMoney());
			sb.append("|");
			sb.append(multiply(contract.getContractSumMoney().toString(),commRate));
			sb.append("|");
			sb.append("_");
			sb.append("|");
		}
		String xmlResult = sb.toString();
		// 将数据写入到流
		response.setHeader("Content-type", "text/html; charset=utf-8");
		try {
			response.getWriter().write(xmlResult);
		} catch (IOException e) {
			logger.error("CommonCPSActionError", e);
		}
		return null;
	}
	
	/**
	 * 乘法运算保留两位小数。
	 * 
	 * @param v1
	 *            参数1
	 * @param v2
	 *            参数2
	 * @return 两个参数的积，以字符串格式返回
	 */
	private String multiply(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public CommonCPSService getCommonCPSService() {
		return commonCPSService;
	}

	public void setCommonCPSService(CommonCPSService commonCPSService) {
		this.commonCPSService = commonCPSService;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

//	public static void main(String args[]) {
//		HttpClient client = new HttpClient();
//		//51比购 测试路径
//		//String url = "http://172.16.122.55:8080/seduadmin/ad/commonCPS!biOrderQuery.action?unionid=51bi&sdate=2010-07-27 23:15:00&edate=2010-07-28 23:20:00";
//		//唯一传媒 测试路径
//		//String url = "http://172.16.122.55:8080/seduadmin/ad/commonCPS!onlyOrderQuery.action?unionId=weiyi&pwd=888888&starttime=2012-07-27&endtime=2012-07-28";
//		//51返利 测试路径
//		String url = "http://172.16.122.55:8080/seduadmin/ad/commonCPS!fanliOrderQuery.action?begin_date=2012-07-27 10:29:52&end_date=2012-07-27 10:29:52&unionid=51fanli";
//		System.out.println(url);
//		GetMethod method = new GetMethod(url);
//		try {
//			client.executeMethod(method);
//			System.out.println(method.getResponseBodyAsString());
//		} catch (HttpException e) {
//			logger.error("CustomerWebAction.HttpException", e);
//		} catch (IOException e) {
//			logger.error("CustomerWebAction.IOException", e);
//		} catch (StringIndexOutOfBoundsException e) {
//			logger.error("CustomerWebAction.StringIndexOutOfBoundsException", e);
//		}
//	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
