package com.shangde.edu.purse.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.purse.condition.QueryRecordCondition;
import com.shangde.edu.purse.domain.Record;
import com.shangde.edu.purse.dto.RecordDTO;
import com.shangde.edu.purse.service.IPurseRecordTbl;
import com.shangde.edu.sys.service.DicCodeService;

public class IPurseRecordAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(IPurseRecordAction.class);
	private Record record;
	private List<Record> recordList;
	private IPurseRecordTbl purseRecordTblService;
	private QueryRecordCondition queryRecordCondition;
	private DicCodeService dicCodeService;
	private Map<String,String> recordTypeMap=new HashMap<String,String>();
	private Map<String,String> payTypeMap=new HashMap<String,String>();
	private Map<String,String> szStatusMap=new HashMap<String,String>();
	private String exportName;
	private InputStream excelFile;
	private BigDecimal paidMoneySum;
	private BigDecimal dropCourseSum;
	private BigDecimal refundSum;
	private BigDecimal paidOrderSum;
	public BigDecimal getPaidMoneySum() {
		return paidMoneySum;
	}
	public void setPaidMoneySum(BigDecimal paidMoneySum) {
		this.paidMoneySum = paidMoneySum;
	}
	public BigDecimal getDropCourseSum() {
		return dropCourseSum;
	}
	public void setDropCourseSum(BigDecimal dropCourseSum) {
		this.dropCourseSum = dropCourseSum;
	}
	public BigDecimal getRefundSum() {
		return refundSum;
	}
	public void setRefundSum(BigDecimal refundSum) {
		this.refundSum = refundSum;
	}
	public BigDecimal getPaidOrderSum() {
		return paidOrderSum;
	}
	public void setPaidOrderSum(BigDecimal paidOrderSum) {
		this.paidOrderSum = paidOrderSum;
	}
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
	public IPurseRecordTbl getPurseRecordTblService() {
		return purseRecordTblService;
	}
	public void setPurseRecordTblService(IPurseRecordTbl purseRecordTblService) {
		this.purseRecordTblService = purseRecordTblService;
	}
	public Map<String, String> getSzStatusMap() {
		return szStatusMap;
	}
	public void setSzStatusMap(Map<String, String> szStatusMap) {
		this.szStatusMap = szStatusMap;
	}
	public Map<String, String> getPayTypeMap() {
		return payTypeMap;
	}
	public void setPayTypeMap(Map<String, String> payTypeMap) {
		this.payTypeMap = payTypeMap;
	}
	public Map<String, String> getRecordTypeMap() {
		return recordTypeMap;
	}
	public void setRecordTypeMap(Map<String, String> recordTypeMap) {
		this.recordTypeMap = recordTypeMap;
	}
	public DicCodeService getDicCodeService() {
		return dicCodeService;
	}
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}
	public QueryRecordCondition getQueryRecordCondition() {
		return queryRecordCondition;
	}
	public void setQueryRecordCondition(QueryRecordCondition queryRecordCondition) {
		this.queryRecordCondition = queryRecordCondition;
	}
	public Record getRecord() {
		return record;
	}
	public void setRecord(Record record) {
		this.record = record;
	}
	public List<Record> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	
	/**
	 * 查询交易记录
	 * @return
	 */
	public String getIPurseRecordList(){
		try{
			//取得交易类型
			this.setRecordTypeMap(dicCodeService.getDicByType("RECORD_TYPE"));
			//取得交易方式
			this.setPayTypeMap(dicCodeService.getDicByType("PAY_TYPE"));
			//取得收支方向
			this.setSzStatusMap(dicCodeService.getDicByType("SZ_STATUS"));
			//查询 充值金额，退课金额，退费金额，支付订单金额
			this.getMoneySum();
			setPage(getPurseRecordTblService().getTransactionRecordList(this.getQueryRecordCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("IPurseRecordAction.getTransactionRecordList", e);
			return ERROR;
		}
		
		return "getIPurseRecordList";
	}
	/**
	 * 查询金额合计
	 * type（1：充值，2：退课，3：退费，4：支付订单）
	 * @param type
	 * @return
	 */
	public void getMoneySum(){
		//充值金额
		this.setPaidMoneySum(getPurseRecordTblService().getMoneySum(1, this.getQueryRecordCondition()));
		//退课金额
		this.setDropCourseSum(getPurseRecordTblService().getMoneySum(2, this.getQueryRecordCondition()));
		//退费金额
		this.setRefundSum(getPurseRecordTblService().getMoneySum(3, this.getQueryRecordCondition()).abs());
		//支付订单金额
		this.setPaidOrderSum(getPurseRecordTblService().getMoneySum(4, this.getQueryRecordCondition()).abs());
	}
	/**
	 * 获取交易记录信息
	 * @return
	 */
	public 	String getPurseRecordInfo(){
		this.setRecord(this.getPurseRecordTblService().getPurseRecordInfo(record));
		return "purseRecordInfo";
	}
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("交易记录查询列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(this.purseRecordList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportPurseRecord";
		}catch(Exception e){
			logger.error("IPurseRecordAction.exportCSV",e);
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
		this.getQueryRecordCondition().setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("交易编号");
		headList.add("用户账号");
		headList.add("创建时间");
		headList.add("交易说明");
		headList.add("金额");
		headList.add("交易方式");
		headList.add("收/支");
		headList.add("状态");
		headList.add("操作人");
		headList.add("交易备注");
		return headList;
	}
	private List<RecordDTO> purseRecordList(){
		PageResult result = this.getPurseRecordTblService().getTransactionRecordList(queryRecordCondition);
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<RecordDTO> recordList,List<List<String>> list){
		List<String> tempList=null;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(RecordDTO recordDTO:recordList){
			tempList=new ArrayList<String>();
			tempList.add(recordDTO.getRecordId());
			tempList.add(recordDTO.getUserAccount());
			tempList.add(df.format(recordDTO.getCreateTime()));
			tempList.add(recordDTO.getRecordTypeName());
			if(recordDTO.getMoney().doubleValue()>0){
				tempList.add("+"+recordDTO.getMoney().toString());
			}else{
				tempList.add(recordDTO.getMoney().toString());
			}
			tempList.add(recordDTO.getPayTypeName());
			tempList.add(recordDTO.getSzStatusName());
			tempList.add(recordDTO.getPayStatusName());
			tempList.add(recordDTO.getCreator());
			tempList.add(recordDTO.getRemark());
			list.add(tempList);
		}
		return list;
		
	}
}
