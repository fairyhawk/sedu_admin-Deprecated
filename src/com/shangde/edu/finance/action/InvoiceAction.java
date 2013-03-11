package com.shangde.edu.finance.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryInvoiceCondition;
import com.shangde.edu.finance.domain.Invoice;
import com.shangde.edu.finance.dto.InvoiceDTO;
import com.shangde.edu.finance.service.InvoiceService;
import com.shangde.edu.sys.domain.DicCode;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.DicCodeService;

public class InvoiceAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(InvoiceAction.class);
	private static final long serialVersionUID = 1L;
	private Invoice invoice;
	private InvoiceService invoiceService;
	private QueryInvoiceCondition queryInvoiceCondition;
	private DicCodeService dicCodeService;
	private List<DicCode> invoiceContentList=new ArrayList<DicCode>();
	private List<DicCode> invoiceStatusList=new ArrayList<DicCode>();
	private final static String INVOICE_STATUS_TYPE="invoiceStatus";
	private final static String INVOICE_CONTENT_TYPE="invoiceContent";
	private String updateFlag;
	private String abolishInvoice;//作废标识
	private String exportName;
	private InputStream excelFile;
	
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public String getAbolishInvoice() {
		return abolishInvoice;
	}
	public void setAbolishInvoice(String abolishInvoice) {
		this.abolishInvoice = abolishInvoice;
	}
	public String getUpdateFlag() {
		return updateFlag;
	}
	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}
	public List<DicCode> getInvoiceStatusList() {
		return invoiceStatusList;
	}
	public void setInvoiceStatusList(List<DicCode> invoiceStatusList) {
		this.invoiceStatusList = invoiceStatusList;
	}
	public List<DicCode> getInvoiceContentList() {
		return invoiceContentList;
	}
	public void setInvoiceContentList(List<DicCode> invoiceContentList) {
		this.invoiceContentList = invoiceContentList;
	}
	public DicCodeService getDicCodeService() {
		return dicCodeService;
	}
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}
	public QueryInvoiceCondition getQueryInvoiceCondition() {
		return queryInvoiceCondition;
	}
	public void setQueryInvoiceCondition(QueryInvoiceCondition queryInvoiceCondition) {
		this.queryInvoiceCondition = queryInvoiceCondition;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public InvoiceService getInvoiceService() {
		return invoiceService;
	}
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * 添加或者更新发票信息
	 * @return
	 */
	public String addOrUpdateInvoice(){
		try{
			//判断需要处理变化的invoice中的属性值
			judgeOperate();
			this.getInvoiceService().addOrUpdateInvoice(getInvoice());
		}catch(Exception e){
			logger.error("InvoiceAction.addOrUpdateInvoice", e);
			return ERROR;
		}
		return "addOrUpdateInvoice";
	}
	public void judgeOperate(){
		/**
		 * getUpdateFlag()=“updateInvoice”表示在寄出时，更新运单号以及发票号码
		 * 在寄出发票时，需要将发票状态更新为已寄出
		 */
		if(getUpdateFlag()!=null&&getUpdateFlag().equals("updateInvoice")){
			getInvoice().setInvoiceStatus(invoiceStatusInfo(INVOICE_STATUS_TYPE,"1"));
		}else if(getAbolishInvoice()!=null&&getAbolishInvoice().equals("yes")){
			//废弃发票操作
			getInvoice().setInvoiceStatus(invoiceStatusInfo(INVOICE_STATUS_TYPE,"2"));
		}
		else{
			//新建时，发票初始状态（已出票）set到invoice对象中
			this.getInvoice().setInvoiceStatus(invoiceStatusInfo(INVOICE_STATUS_TYPE,"0"));
		}
		//用于将创建人，创建时间，更新人，更新时间,发票初始状态（已出票）set到invoice对象中
		this.setUserInfo();
	}
	public String invoiceStatusInfo(String invoiceStatusType,String invoiceStatusCode){
		return dicCodeService.getDicCodeList(invoiceStatusType,invoiceStatusCode).get(0).getDicContent();
	}
	/**
	 * 用于将创建人，创建时间，更新人，更新时间set到invoice对象中
	 * 说明：只有当第一次新建的时候，才set创建人和创建时间的值，其余
	 * 在修改情况下，都只能set更新时间和更新人
	 */
	public void setUserInfo(){
		User user=(User) servletRequest.getSession().getAttribute("sedu_user");
		if(getInvoice().getInvoiceId()==null){
			getInvoice().setCreateDate(new Date(System.currentTimeMillis()));
			getInvoice().setCreator(user.getLoginName());
		}else{
			getInvoice().setUpdateDate(new Date(System.currentTimeMillis()));
			getInvoice().setUpdateUser(user.getLoginName());
		}
	}
	/**
	 * 查询或者新建该条发票信息
	 * @return
	 */
	public String getInvoiceInfo(){
		try{
			//取得发票内容集合
			invoiceContentList=dicCodeService.getDicCodeList(INVOICE_CONTENT_TYPE,"");
			if(getInvoice()!=null){
				setInvoice(getInvoiceService().getInvoice(getInvoice()));
			}
		}catch(Exception e){
			logger.error("InvoiceAction.getInvoiceInfo", e);
			return ERROR;
		}
		return "getInvoiceInfo";
	}
	/**
	 * 查询发票列表，主要用于发票查询页面
	 * @return
	 */
	public String getInvoiceInfoList(){
		try{
			//取得发票状态列表，用于前台下拉框显示
			invoiceStatusList=dicCodeService.getDicCodeList(INVOICE_STATUS_TYPE, "");
			setPage(this.getInvoiceService().getInvoiceList(this.getQueryInvoiceCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("InvoiceAction.getInvoiceInfoList", e);
			return ERROR;
		}
		return "getInvoiceList";
	}
	
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("发票查询列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(this.invoiceList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportInvoice";
		}catch(Exception e){
			logger.error("InvoiceAction.exportCSV",e);
			return ERROR;
		}
		
	}
	
	private void addExpName(String expNameStr) throws UnsupportedEncodingException{
		String expName=expNameStr + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
		
	}
	private void setExportPageSize(int size){
		this.getQueryInvoiceCondition().setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("订单编号");
		headList.add("支付方式");
		headList.add("发票抬头");
		headList.add("发票内容");
		headList.add("发票状态");
		headList.add("发票金额");
		headList.add("发票信息");
		headList.add("手机号码");
		headList.add("操作人");
		headList.add("物流单号");
		headList.add("发票号");
		return headList;
	}
	private List<InvoiceDTO> invoiceList(){
		PageResult result = this.getInvoiceService().getInvoiceList(getQueryInvoiceCondition());
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<InvoiceDTO> invoiceList,List<List<String>> list){
		List<String> tempList=null;
		DecimalFormat  format=new DecimalFormat("##0.00");
		for(InvoiceDTO invoiceDTO:invoiceList){
			tempList=new ArrayList<String>();
			tempList.add(invoiceDTO.getOrderCode());
			tempList.add(getPayTypeName(invoiceDTO.getPayType()));
			tempList.add(returnInvoiceTitle(invoiceDTO.getInvoiceTitle()));
			tempList.add(invoiceDTO.getInvoiceContent());
			tempList.add(invoiceDTO.getInvoiceStatus());
			tempList.add(format.format(invoiceDTO.getInvoiceSum()));
			tempList.add(invoiceDTO.getInvoiceAddress());
			tempList.add(invoiceDTO.getMobile());
			tempList.add(invoiceDTO.getCreator());
			tempList.add(invoiceDTO.getWaybillCode());
			tempList.add(invoiceDTO.getInvoiceCode());
			list.add(tempList);
		}
		return list;
		
	}
	private String returnInvoiceTitle(String invoiceTitle){
		if(invoiceTitle.equals("0")){
			return "个人";
		}else{
			return "单位";
		}
	}
	private String getPayTypeName(String payType){
		String payTypeName="";
		if(!payType.equals("")&&payType!=null){
			switch(Integer.parseInt(payType)){
			case 0 : payTypeName="内部开通"; break;
			case 1 : payTypeName="支付宝/网银"; break;
			case 2 : payTypeName="货到付款"; break;
			case 3 : payTypeName="网银在线/网银"; break;
			case 4 : payTypeName="快钱"; break;
			case 5 : payTypeName="代理商"; break;
			case 6 : payTypeName="真友"; break;
			case 7 : payTypeName="银行汇款"; break;
			case 8 : payTypeName="银联在线/银联"; break;
			}
		}
	
		return payTypeName;
	}
	
	public String validateRepeat(){
		try{
			List<Map<String,String>> verfyList=new ArrayList<Map<String,String>>();
			Map<String,String> map=new HashMap<String,String>();
			//查询发票号码是否重复
			String invoiceCodeCount=this.getInvoiceService().isInvoiceCodeRepeat(this.getInvoice());
			map.put("invoiceCodeCount", invoiceCodeCount);
			String waybillCodeCount=this.getInvoiceService().isWaybillCodeRepeat(this.getInvoice());
			map.put("waybillCodeCount", waybillCodeCount);
			verfyList.add(map);
			setResult(new Result(false,"success",null,verfyList));
			return "verfyJson";
		}catch(Exception ex){
			logger.error("InvoiceAction.validateRepeat", ex);
			setResult(new Result(false, "error", null, null));
			return "verfyJson";
		}
		
	}
	
}
