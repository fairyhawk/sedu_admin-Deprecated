package com.shangde.edu.finance.action;



import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.shangde.edu.finance.condition.QueryWaybillCondition;
import com.shangde.edu.finance.domain.Waybill;
import com.shangde.edu.finance.dto.WaybillDTO;
import com.shangde.edu.finance.service.WaybillService;
import com.shangde.edu.sys.domain.DicCode;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.DicCodeService;

public class WaybillAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(WaybillAction.class);
	private WaybillService waybillService;
	private Waybill waybill;
	private QueryWaybillCondition queryWaybillCondition;
	private List<DicCode> expressCompanyList=new ArrayList<DicCode>();
	private List<DicCode> goodsCategoryList=new ArrayList<DicCode>();
	private DicCodeService dicCodeService;
	private final static String  EXPRESS_COMPANY="expressCompany";
	private final static String  GOODS_CATEGORY="goodsCategory"; 
	private final static String  WAYBILL_STATUS="waybillStatus";
	private String abolishWaybill;
	private String finishWaybill;
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
	public String getAbolishWaybill() {
		return abolishWaybill;
	}
	public void setAbolishWaybill(String abolishWaybill) {
		this.abolishWaybill = abolishWaybill;
	}
	public String getFinishWaybill() {
		return finishWaybill;
	}
	public void setFinishWaybill(String finishWaybill) {
		this.finishWaybill = finishWaybill;
	}
	public DicCodeService getDicCodeService() {
		return dicCodeService;
	}
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}
	public List<DicCode> getExpressCompanyList() {
		return expressCompanyList;
	}
	public void setExpressCompanyList(List<DicCode> expressCompanyList) {
		this.expressCompanyList = expressCompanyList;
	}
	public List<DicCode> getGoodsCategoryList() {
		return goodsCategoryList;
	}
	public void setGoodsCategoryList(List<DicCode> goodsCategoryList) {
		this.goodsCategoryList = goodsCategoryList;
	}
	public QueryWaybillCondition getQueryWaybillCondition() {
		return queryWaybillCondition;
	}
	public void setQueryWaybillCondition(QueryWaybillCondition queryWaybillCondition) {
		this.queryWaybillCondition = queryWaybillCondition;
	}
	public Waybill getWaybill() {
		return waybill;
	}
	public void setWaybill(Waybill waybill) {
		this.waybill = waybill;
	}
	public WaybillService getWaybillService() {
		return waybillService;
	}
	public void setWaybillService(WaybillService waybillService) {
		this.waybillService = waybillService;
	}

	/**
	 * 添加或者更新运单
	 */
	public String addOrUpdateWaybill(){
		try{
			judgeOperate();
			this.getWaybillService().addOrUpdateWaybill(this.getWaybill());
		}catch(Exception e){
			logger.error("WaybillAction.addOrUpdateInvoice", e);
			return ERROR;
		}
		return "addOrUpdateWaybill";
	}
	/**
	 * 查询或者新建运单详细信息
	 * @return
	 */
	public String getWaybillInfo(){
		try{
			//取得发票内容集合
			expressCompanyList=dicCodeService.getDicCodeList(EXPRESS_COMPANY,"");
			goodsCategoryList=dicCodeService.getDicCodeList(GOODS_CATEGORY,"");
			if(getWaybill()!=null){
				setWaybill(getWaybillService().getWaybill(getWaybill()));
			}
		}catch(Exception e){
			logger.error("WaybillAction.getWaybillInfo", e);
			return ERROR;
		}
		return "getWaybillInfo";
	}
	/**
	 * 查询列表，主要用于发票查询页面
	 * @return
	 */
	public String getWaybillInfoList(){
		try{
			setPage(this.getWaybillService().getWaybillList(this.getQueryWaybillCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("WaybillAction.getWaybillInfoList", e);
			return ERROR;
		}
		return "getWaybillList";
	}
	
	/**
	 * 用于将创建人，创建时间，更新人，更新时间set到invoice对象中
	 * 说明：只有当第一次新建的时候，才set创建人和创建时间的值，其余
	 * 在修改情况下，都只能set更新时间和更新人
	 */
	public void setUserInfo(){
		User user=(User) servletRequest.getSession().getAttribute("sedu_user");
		if(getWaybill().getWaybillId()==null){
			getWaybill().setCreateDate(new Date(System.currentTimeMillis()));
			getWaybill().setCreator(user.getLoginName());
		}else{
			getWaybill().setUpdateDate(new Date(System.currentTimeMillis()));
			getWaybill().setUpdateUser(user.getLoginName());
		}
	}
	
	public void judgeOperate(){
	
		if(getAbolishWaybill()!=null&&getAbolishWaybill().equals("yes")){
			//废弃操作
			this.getWaybill().setWaybillStatus(waybillStatusInfo(WAYBILL_STATUS,"2"));
		}
		else if(getFinishWaybill()!=null&&getFinishWaybill().equals("yes")){
			//完成操作
			this.getWaybill().setWaybillStatus(waybillStatusInfo(WAYBILL_STATUS,"1"));
		}
		else{
			//新建时，初始状态（未完成）
			this.getWaybill().setWaybillStatus(waybillStatusInfo(WAYBILL_STATUS,"0"));
		}
		//判断是否保存创建人，创建时间，更新人，更新时间
		this.setUserInfo();
	}
	public String waybillStatusInfo(String invoiceStatusType,String invoiceStatusCode){
		return dicCodeService.getDicCodeList(invoiceStatusType,invoiceStatusCode).get(0).getDicContent();
	}
	
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("运单查询列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(this.waybillList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportWaybill";
		}catch(Exception e){
			logger.error("WaybillAction.exportCSV",e);
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
		this.getQueryWaybillCondition().setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("物流单号");
		headList.add("订单号");
		headList.add("收货人姓名");
		headList.add("手机号码");
		headList.add("物品类别");
		headList.add("物流公司");
		headList.add("物流状态");
		headList.add("物流时间");
		return headList;
	}
	private List<WaybillDTO> waybillList(){
		PageResult result = this.getWaybillService().getWaybillList(getQueryWaybillCondition());
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<WaybillDTO> invoiceList,List<List<String>> list){
		List<String> tempList=null;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for(WaybillDTO waybillDTO:invoiceList){
			tempList=new ArrayList<String>();
			tempList.add(waybillDTO.getWaybillCode());
			tempList.add(waybillDTO.getOrderCode());
			tempList.add(waybillDTO.getConsigneeName());
			tempList.add(waybillDTO.getConsigneePhone());
			tempList.add(waybillDTO.getGoodsCategory());
			tempList.add(waybillDTO.getExpressCompany());
			tempList.add(waybillDTO.getWaybillStatus());
			tempList.add(df.format(waybillDTO.getCreateDate()));
			list.add(tempList);
		}
		return list;
		
	}
	
	public String validateRepeat(){
		try{
			List<Map<String,String>> verfyList=new ArrayList<Map<String,String>>();
			Map<String,String> map=new HashMap<String,String>();
			String waybillCodeCount=this.getWaybillService().isWaybillCodeRepeat(this.getWaybill());
			map.put("waybillCodeCount", waybillCodeCount);
			verfyList.add(map);
			setResult(new Result(false,"success",null,verfyList));
			return "verfyJson";
		}catch(Exception ex){
			logger.error("WaybillAction.validateRepeat", ex);
			setResult(new Result(false, "error", null, null));
			return "verfyJson";
		}
		
	}
	
}
