package com.shangde.edu.finance.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryCashRecordCondition;
import com.shangde.edu.finance.condition.QueryRefundCondition;
import com.shangde.edu.finance.domain.CashRecordDTO;
import com.shangde.edu.finance.domain.Refund;
import com.shangde.edu.finance.domain.RefundDTO;
import com.shangde.edu.finance.service.ICashRecord;
import com.shangde.edu.finance.service.IRefund;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:退费功能action,客服人员对申请商品退费，编辑退费信息以及撤消商品退费的操作
 *					     客服人员对申请要退费的商品进行审核，以及对不满足退费要求的商品进行撤消退费的操作
 *
 * @author		Yangning
 * @date		2011-11-21
 * @version 	修改人:
 * 				修改日期:2011-11-28
 */
public class RefundAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(RefundAction.class);
	
	private String message;
	
	private static final long serialVersionUID = 1L;
	/**商品信息**/
	private CashRecordDTO cashRecordDTO;
	/**商品信息查询条件**/
	private QueryCashRecordCondition queryCashRecordCondition;
	
	private ICashRecord cashRecordService;
	
	private Refund refund;
	
	private IRefund refundService;

	private List<RefundDTO> refundList;
	
	private QueryRefundCondition refundCondition;
	
	private RefundDTO refundDTO;
	
	private String codename;
	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	private String exportname;
	/**项目列表服务，用于list查询下拉菜单**/
	private ISubject subjectService;
	
	List<Subject> subjectList;
	
	/**导出excel流文件**/
	private InputStream excelFile;
	
	/**客服输入退费信息**/
	public String toInput(){
		String forward = "new";
		try{
			getQueryCashRecordCondition().setId(Integer.parseInt(getQueryCashRecordCondition().getPcId()));
			cashRecordDTO=cashRecordService.getSingleCommodity(getQueryCashRecordCondition());
			if(cashRecordDTO == null){
				forward = "invalid";
				message =  "请输入正确用户";
			}
			refundDTO = refundService.getRefundBySellId(cashRecordDTO.getId());
			if(refundDTO != null){
				forward = "view";
			}
		}catch(Exception e){
			forward = ERROR;
			logger.error("RefundAction.toInput", e);
			
		}
		return forward;
	}
	
	/**客服人员增加退费信息**/
	public String addRef(){
		String forward = "success";
		int result = 0;
		try{
			User user = getLoginedUser();
			if(user != null){
				refund.setOperuserid(user.getUserId());
			}
			if(refund.getCreatetime() == null){
				refund.setCreatetime(new Date());
			}
			result = refundService.addRefund(refund);
			if(result <= 0){
				forward = ERROR;
			}
		}catch(Exception e){
			logger.error("RefundAction.addRef", e);
			forward = ERROR;
		}
		return forward;
	}
	
	public String getList(){
		String forward = "success";
		try{
			subjectList = subjectService.getAllSubject();
			refundList = refundService.getRefundList(this.getRefundCondition());
		}catch(Exception e){
			logger.error("RefundAction.getList", e);
			forward = ERROR;
		}
		return forward;
	}
	/**分页列表**/
	public String getRPage(){
		String forward = "list";
		try{
			subjectList = subjectService.getAllSubject();
			getRefundCondition().setPageSize(20);
			this.setPage(refundService.getRefundByPage(this.getRefundCondition()));
			setPageUrlParms();
			this.getPage().setPageSize(20);
		}catch(Exception e){
			logger.error("RefundAction.getRPage", e);
			forward = ERROR;
		}
		return forward;
	}
	
	/**查看退费信息**/
	public String getOne(){
		String froward = "refund";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					froward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.getOne", e);
			froward = ERROR;
		}
		return froward;
	}
	
	/**查看单个退费信息**/
	public String view(){
		String froward = "view";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					froward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.view", e);
			froward = ERROR;
		}
		return froward;
	}
	
	/**跳转确认页面**/
	public String toConfirm(){
		String froward = "confirm";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					froward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.view", e);
			froward = ERROR;
		}
		return froward;
	}
	
	
	/**跳转修改退费信息**/
	public String toModify(){
		String froward = "modify";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					froward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.view", e);
			froward = ERROR;
		}
		return froward;
	}
	
	/**确认修改**/
	public String modify(){
		String froward = "success";
		try{
			if(refund.getId() > 0){
				User user = getLoginedUser();
				refund.setOperuserid(user.getUserId());
				Integer result  = refundService.updateRefund(refund);
				if(result <= 0){
					froward = ERROR;
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.view", e);
			froward = ERROR;
		}
		return froward;
	}
	
	/**客服人员查看详细信息**/
	public String viewDetail(){
		String froward = "detail";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					froward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				froward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.viewDetail", e);
			froward = ERROR;
		}
		return froward;
	}
	
	/**客服人员更新**/
	public String updateOne(){
		String forward = "upSuc";
		try{
			if( refund != null || refund.getId() > 0){
				int result = refundService.updateRefund(refund);
				if(result < 0){
					forward = ERROR;
				}
			}else{
				forward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.updateOne", e);
			forward = ERROR;
		}
		return forward;
	}
	
	public String delOne(){
		String forward = "delSuccess";
		try{
			if(refund != null && refund.getId() > 0){
				Integer result = refundService.delRefundById(refund.getId());
				if(result <= 0){
					forward = ERROR;
				}else{
					forward = "invalid";
					message = "请输入正确ID";
				}
			}
		}catch(Exception e){
			logger.error("RefundAction.delOne",e);
			forward = ERROR;
		}
		return forward;
	}
	
	/**导出Excel表格**/
	public String exportCSV() {
		String forward = "excel";
		try {
			/*-----生成文件名-------*/
			exportname="退费列表_" + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
			/*-------针对浏览器下载乱码处理------*/
				setExportname(URLEncoder.encode(exportname, "UTF-8"));
			 }else{
				setExportname(new String((exportname).getBytes(),"iso-8859-1"));
			 }
			this.getQueryCashRecordCondition().setPageSize(5000);
			PageResult result = refundService.getRefundByPage(getRefundCondition());
			List<RefundDTO> refundList = result.getPageResult();
			List<List<String>> list = new ArrayList<List<String>>();
			/*-------表头------*/
			List<String> headList = new ArrayList<String>();
			headList.add("序号");
			headList.add("付款时间");
			headList.add("隶属订单编号");
			headList.add("申请退费时间");
			headList.add("实际退费时间");
			headList.add("用户email");
			headList.add("联系人");
			headList.add("联系方式");
			headList.add("退费商品");
			headList.add("是否全额退款");
			headList.add("退费银行");
			headList.add("开户行名称");
			headList.add("开户人姓名");
			headList.add("退费账户");
			headList.add("支付宝退款");
			headList.add("成交价格");
			headList.add("退费金额");
			headList.add("退费原因");
			headList.add("是否符合24小时");
			headList.add("记录人");
			headList.add("操作员");
			headList.add("审核人");
			headList.add("退费状态");
			list.add(headList);
			/*----表身----*/
			for (int i = 0; i < refundList.size(); i++) {
				RefundDTO refund = refundList.get(i);
				List<String> strList = fromatDTO(refund,i);
				list.add(strList);
			}
			/*---导出---*/
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
		} catch (Exception ex) {
			logger.error("RefundAction.exportCSV",ex);
			return ERROR;
		}
		return forward;
	}
	
	/**格式化RefundDTO**/
	private List<String> fromatDTO(RefundDTO refund,int i){
		List<String> list = new ArrayList<String>();
		list.add(String.valueOf(i+1));
		list.add(DateUtil.formatDate(refund.getPaytime(), "yyyy-MM-dd hh:mm:ss"));
		list.add(refund.getContractno());
		list.add(DateUtil.formatDate(refund.getCreatetime(), "yyyy-MM-dd hh:mm:ss"));
		list.add(DateUtil.formatDate(refund.getRefundtime(),"yyyy-MM-dd hh:mm:ss"));
		list.add(refund.getUseremail());
		list.add(refund.getConname());
		list.add(refund.getUsermobile());
		list.add(refund.getSellname());
		list.add(refund.getIsfull() == null ? "" : refund.getIsfull() == true ? "是" : "否");
		list.add(refund.getBankname());
		list.add(refund.getBankregname());
		list.add(refund.getCusbankname());
		list.add(refund.getIszfb() == null ? "" : refund.getIszfb() == false ? refund.getBankcode() : "");
		list.add(refund.getIszfb() == null ? "" : refund.getIszfb() == true ? refund.getBankcode() : "");
		list.add(refund.getDealprice() == null ? "0.0" : String.valueOf(refund.getDealprice()));
		list.add(refund.getPrice() == null ? "0.0" : String.valueOf(refund.getPrice()));
		list.add(refund.getRemark());
		String is24hours = "否";
		/**是否24小时判断**/
		if(refund.getPaytime() != null && refund.getCreatetime() != null){
			long intervar = (refund.getCreatetime().getTime() - refund.getPaytime().getTime() );
			if (intervar < 86400000){
				is24hours = "是";
			}
		}
		list.add(is24hours);
		list.add(refund.getOperusername());
		list.add(refund.getOpername());
		list.add(refund.getCheckname());
		list.add(refund.getStatus() == null ? "" : refund.getStatus() == 0 ? "未退费" : refund.getStatus() == 2 ? "已撤销" : "已退费");
		return list;
	}
	
	/**跳转取消**/
	public String toCancel(){
		String forward = "cancel";
		try{
			if(getRefundCondition().getId() != null && getRefundCondition().getId() > 0){
				refundDTO = refundService.getRefundById(getRefundCondition().getId());
				if(refundDTO == null){
					forward = "invalid";
					message =  "请输入正确用户id";
				}
			}else{
				forward = "invalid";
				message =  "请输入正确用户id";
			}
		}catch(Exception e){
			logger.error("RefundAction.toCancel", e);
			forward = ERROR;
		}
		return forward;
	}
	
	/**取消退费**/
	public String cancel(){
		String forward = "success";
		try{
			if(refund != null && refund.getId() != null){
				User user = getLoginedUser();
				refund.setOperuserid(user.getUserId());
				refund.setStatus(2);
				/*--防止重复取消--*/
				if(!refundService.getRefundByIdStatus(refund)){
					Integer result = refundService.updateRufundCancel(refund);
					if(result < 0){
						forward = ERROR;
					}
				}else{
					message =  "已取消，请不要重复取消";
					forward = "invalid";
				}
			}else{
				message =  "请输入一个ID";
				forward = "invalid";
			}
		}catch(Exception e){
			logger.error("RefundAction.cancelRefd", e);
			forward = ERROR;
		}
		return forward;
	}
	/**确认取消**/
	public String confirm(){
		String forward = "success";
		try{
			if(refund != null && refund.getId() != null && refund.getCashid() != null && refund.getContractno() != null){
				User user = getLoginedUser();
				refund.setConfirmuid(user.getUserId());
				refundDTO = refundService.getRefundById(refund.getId());
				refund.setIsfull(refundDTO.getIsfull());
				refund.setStatus(1);
				/*--防止重复确认--*/
				if(!refundService.getRefundByIdStatus(refund)){
					Integer result = refundService.updateRefundConfirm(refund);
					if(result < 0){
						forward = ERROR;
					}
				}else{
					message =  "已确认，请不要重复确认";
					forward = "invalid";
				}
			}else{
				message =  "输入参数不合法";
				forward = "invalid";
			}
		}catch(Exception e){
			logger.error("RefundAction.confirmRefd",e);
			return ERROR;
		}
		return forward;
	}
	
	
	public List<RefundDTO> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<RefundDTO> refundList) {
		this.refundList = refundList;
	}

	public QueryRefundCondition getRefundCondition() {
		if(refundCondition == null){
			refundCondition = new QueryRefundCondition();
		}else{
			/*-截取条件查询空白字符-*/
			refundCondition.setBankcode(refundCondition.getBankcode() == null ? null : refundCondition.getBankcode().trim());
			refundCondition.setEmail(refundCondition.getEmail()== null ? null : refundCondition.getEmail().trim()); 
			refundCondition.setContractno((refundCondition.getContractno()== null ? null : refundCondition.getContractno().trim())); 
			refundCondition.setBankcode((refundCondition.getBankcode()== null ? null : refundCondition.getBankcode().trim()));
			refundCondition.setSellname((refundCondition.getSellname()== null ? null : refundCondition.getSellname().trim()));
		}
		return refundCondition;
	}

	public void setRefundCondition(QueryRefundCondition refundCondition) {
		this.refundCondition = refundCondition;
	}
	
	public CashRecordDTO getCashRecordDTO() {
		return cashRecordDTO;
	}

	public void setCashRecordDTO(CashRecordDTO cashRecordDTO) {
		this.cashRecordDTO = cashRecordDTO;
	}

	public QueryCashRecordCondition getQueryCashRecordCondition() {
		return queryCashRecordCondition;
	}

	public void setQueryCashRecordCondition(QueryCashRecordCondition queryCashRecordCondition) {
		this.queryCashRecordCondition = queryCashRecordCondition;
	}
	
	public ICashRecord getCashRecordService() {
		return cashRecordService;
	}

	public void setCashRecordService(ICashRecord cashRecordService) {
		this.cashRecordService = cashRecordService;
	}

	public Refund getRefund() {
		return refund;
	}

	public void setRefund(Refund refund) {
		this.refund = refund;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public IRefund getRefundService() {
		return refundService;
	}

	public void setRefundService(IRefund refundService) {
		this.refundService = refundService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	
	public RefundDTO getRefundDTO() {
		return refundDTO;
	}

	public void setRefundDTO(RefundDTO refundDTO) {
		this.refundDTO = refundDTO;
	}

	public String getExportname() {
		return exportname;
	}

	public void setExportname(String exportname) {
		this.exportname = exportname;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
}
