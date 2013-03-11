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
import com.shangde.edu.purse.condition.QueryMoneyCondition;
import com.shangde.edu.purse.domain.Money;
import com.shangde.edu.purse.dto.MoneyDTO;
import com.shangde.edu.purse.dto.RecordDTO;
import com.shangde.edu.purse.service.IPurseMoneyTbl;
import com.shangde.edu.sys.service.DicCodeService;

public class IPurseMoneyAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(IPurseMoneyAction.class);
	private IPurseMoneyTbl purseMoneyTblService;
	private QueryMoneyCondition queryMoneyCondition;
	private Money money;
	private Map<String,String> accountTypeMap=new HashMap<String,String>();
	private String exportName;
	private InputStream excelFile;
	private DicCodeService dicCodeService;
	private BigDecimal accountDalanceSum;
	private BigDecimal freezeSum;
	public BigDecimal getAccountDalanceSum() {
		return accountDalanceSum;
	}

	public void setAccountDalanceSum(BigDecimal accountDalanceSum) {
		this.accountDalanceSum = accountDalanceSum;
	}

	public BigDecimal getFreezeSum() {
		return freezeSum;
	}

	public void setFreezeSum(BigDecimal freezeSum) {
		this.freezeSum = freezeSum;
	}

	public QueryMoneyCondition getQueryMoneyCondition() {
		return queryMoneyCondition;
	}

	public void setQueryMoneyCondition(QueryMoneyCondition queryMoneyCondition) {
		this.queryMoneyCondition = queryMoneyCondition;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Map<String, String> getAccountTypeMap() {
		return accountTypeMap;
	}

	public void setAccountTypeMap(Map<String, String> accountTypeMap) {
		this.accountTypeMap = accountTypeMap;
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

	public DicCodeService getDicCodeService() {
		return dicCodeService;
	}

	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}

	public IPurseMoneyTbl getPurseMoneyTblService() {
		return purseMoneyTblService;
	}

	public void setPurseMoneyTblService(IPurseMoneyTbl purseMoneyTblService) {
		this.purseMoneyTblService = purseMoneyTblService;
	}
	/**
	 * 查询账户管理列表
	 * @return
	 */
	public String getPurseMoneyList(){
		try{
			//获取累计账户余额及冻结账户余额
			this.getMoneySum();
			//获取账户状态
			this.setAccountTypeMap(this.getDicCodeService().getDicByType("ACCOUNT_STATUS"));
			setPage(this.getPurseMoneyTblService().getPurseMoneyList(this.getQueryMoneyCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("IPurseMoneyAction.getPurseMoneyList",e);
			return ERROR;
		}
		return "getPurseMoneyList";
	}
	
	/**
	 * 查询金额合计
	 * type（2：包含正常和冻结的，1：冻结）
	 * @param type
	 * @return
	 */
	public void getMoneySum(){
		//获取累计账户余额
		this.setAccountDalanceSum(this.getPurseMoneyTblService().getMoneySum(2,this.getQueryMoneyCondition()));
		//获取冻结账户余额
		if(this.getQueryMoneyCondition().getStatus()==null||this.getQueryMoneyCondition().getStatus()==1){
			this.setFreezeSum(this.getPurseMoneyTblService().getMoneySum(1,this.getQueryMoneyCondition()));
		}else{
			this.setFreezeSum(new BigDecimal("0.00"));
		}
	}
	
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("账户查询列表");
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
			return "exportPurseMoney";
		}catch(Exception e){
			logger.error("IPurseMoneyAction.exportCSV",e);
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
		this.getQueryMoneyCondition().setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("用户账号");
		headList.add("手机号");
		headList.add("账户余额");
		headList.add("状态");
		headList.add("创建时间");
		headList.add("最后充值时间");
		headList.add("最后操作人");
		return headList;
	}
	private List<MoneyDTO> purseRecordList(){
		PageResult result = this.getPurseMoneyTblService().getPurseMoneyList(queryMoneyCondition);
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<MoneyDTO> moneyList,List<List<String>> list){
		List<String> tempList=null;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(MoneyDTO moneyDTO:moneyList){
			tempList=new ArrayList<String>();
			tempList.add(moneyDTO.getUserAccount());
			tempList.add(moneyDTO.getMobile());
			tempList.add(moneyDTO.getMoney().toString());
			tempList.add(moneyDTO.getAccountStatus());
			tempList.add(df.format(moneyDTO.getCreateTime()));
			tempList.add(df.format(moneyDTO.getUpdateTime()));
			tempList.add(moneyDTO.getUpdateUser());
			list.add(tempList);
		}
		return list;
		
	}
}
