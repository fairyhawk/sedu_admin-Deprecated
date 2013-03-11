/**
 * 为第三方站点提供订单查询的接口
 */
package com.shangde.edu.finance.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.domain.CashRecord;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IContract;

/**
 * @author	caowei
 * @date	2011-8-2
 * @desc	
 */
public class OtherSiteQueryAction extends CommonAction{

	private static final long serialVersionUID = 3030072007981067644L;

	//订单对象
	private Contract contract=new Contract();
	//订单查询
	private QueryContractCondition queryContractCondition = new QueryContractCondition();
	//订单服务
	private IContract contractService;
	//流水服务
	private ICashRecord cashRecordService;
	//订单集合
	private List<Contract> cList = new ArrayList<Contract>();
	//流水条件查询
	private QueryCashRecordCondition queryCashRecordCondition = new QueryCashRecordCondition();
	//流水集合
	private List<CashRecord> crList = new ArrayList<CashRecord>();
	//售卖方式服务
	private ISellWay sellWayService;
	//售卖方式
	private SellWay sellWay;
	//声明logger
	private Log logger = LogFactory.getLog(getClass());

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public List<CashRecord> getCrList() {
		return crList;
	}

	public void setCrList(List<CashRecord> crList) {
		this.crList = crList;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(
			QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}

	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public List<Contract> getcList() {
		return cList;
	}

	public void setcList(List<Contract> cList) {
		this.cList = cList;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public QueryContractCondition getQueryContractCondition() {
		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	/**
	 * 为返利网提供的订单查询接口
	 * 
	 * @return
	 * 			以文本的形式打在页面上
	 */
	public String getDataForFanli(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String dateTime = request.getParameter("date");
		String beginDate = request.getParameter("begin_date");
		String endDate = request.getParameter("end_date");
		String unionid = request.getParameter("unionid");
		queryContractCondition.setWebFrom(unionid);
		boolean vDate = false;
		String writeText = "";
		if(dateTime != null && beginDate == null && endDate == null){
			vDate = this.validateDate(dateTime);
		}else if(dateTime == null && beginDate != null && endDate != null){
			if(this.validateDate(beginDate) && this.validateDate(endDate)){
				vDate = true;
			}
		}else{
			writeText = "参数不正确！";
		}
		
		boolean paramFlag = false;
		if(vDate){
			if(dateTime == null && unionid.equals("51fanli") && beginDate != null && endDate != null){
				queryContractCondition.setStartTime(beginDate);
				queryContractCondition.setEndTime(getSpecifiedDayAfter(endDate));
				paramFlag = true;
			}else if(dateTime != null && unionid.equals("51fanli") && beginDate == null && endDate == null){
				queryContractCondition.setStartTime(dateTime);
				queryContractCondition.setEndTime(getSpecifiedDayAfter(dateTime));
				paramFlag = true;
			}else{
				writeText = "参数错误！";
			}			
		}else{
			writeText = "日期格式错误！";
		}
			
		if(paramFlag){
			cList = contractService.getContractListForFanli(queryContractCondition);
			
			
			for(Contract ct : cList){
				queryCashRecordCondition.setContractId(ct.getContractId());	
				List<? extends Object> sellList = this.getSellList(ct.getContractId());
				String pks = "";
				String prices = "";
				for(int i=0;i<sellList.size();i++){
					pks = sellList.get(i)+"|";
					SellWay sw = sellWayService.getSellWayById((Integer) sellList.get(i));
					prices = sw.getSellPrice()+"|";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					writeText += sdf.format(ct.getCreateTime()) + "|"
							+ ct.getContractId() + "|" + ct.getWebAgent() + "|"
							+ pks + "0|1|" + prices + "0|highso|"
							+ ct.getWebAgent() + "@51fanli|_|";
				}
			}			
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().print(writeText);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据订单编号查询售卖方式的集合
	 * 
	 * @param contractId
	 * 				订单编号
	 * @return
	 * 				售卖方式集合
	 */
	public List<Object> getSellList(String contractId){
		
		List<CashRecord> crInnerList = new ArrayList<CashRecord>();
		//crInnerList = cashRecordService.getCashRecordByList(queryCashRecordCondition);
		crInnerList = cashRecordService.getCashRecordByCtIdForFanli(contractId);
		List<Object> sellList = new ArrayList<Object>();
		for(CashRecord cr : crInnerList){
			sellList.add(cr.getPackId());		
		}
		sellList = this.filterList(sellList);
		return sellList;
	}
	
	
	/**
	 * 获取指定日期的下一天
	 * 
	 * @param specifiedDay
	 * 			指定的日期
	 * @return
	 * 			下一天
	 */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }
    
	/**
	 * 工具方法-用于对List中的元素进行剔重
	 * @param list
	 * @return
	 */
	public List filterList(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}
	
	/**
	 * 验证日期的格式是否正确，其中包含不正确的日期，如非闰月无29
	 * 
	 * @param checkValue
	 * 			要验证的日期
	 * @return
	 * 			是否正确
	 */
	public boolean validateDate(String checkValue){
		String eL = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(checkValue);
		boolean b = m.matches();
		
		return b; 
	}
}
