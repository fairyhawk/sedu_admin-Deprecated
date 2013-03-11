package com.shangde.edu.sys.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.action.SellWayAction;
import com.shangde.edu.sys.condition.QuerySignUpCondition;
import com.shangde.edu.sys.domain.SignUp;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.SignUpService;

public class SignUpAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SellWayAction.class);
	private SignUp signUp;
	
	private QuerySignUpCondition querySignUpCondition;
	private SignUpService signUpService;
	/**
	 * 专业集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	/**
	 * 专业服务
	 */
	private ISubject subjectService; 
	private String exportName;
	private InputStream excelFile;

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

	public SignUpService getSignUpService() {
		return signUpService;
	}

	public void setSignUpService(SignUpService signUpService) {
		this.signUpService = signUpService;
	}

	public QuerySignUpCondition getQuerySignUpCondition() {
		if(querySignUpCondition==null){
			querySignUpCondition=new QuerySignUpCondition();
		}
		return querySignUpCondition;
	}

	public void setQuerySignUpCondition(QuerySignUpCondition querySignUpCondition) {
		this.querySignUpCondition = querySignUpCondition;
	}

	public SignUp getSignUp() {
		return signUp;
	}

	public void setSignUp(SignUp signUp) {
		this.signUp = signUp;
	}
	
	public String showSignUpList() {
		try {
			subjectList = subjectService.getAllSubject();
			this.getQuerySignUpCondition().setPageSize(20);
			setPage(this.getSignUpService().getSignUpInfoList(this.getQuerySignUpCondition()));
			setPageUrlParms();
			getPage().setPageSize(20);
			return "listSignUp";
		} catch (Exception e) {
			logger.error("SignUpAction.showSignUpList",e);
			return ERROR;
		}

	}
	public String exportCSV(){
		try{
			//设置导出excel文件名称
			this.addExpName("注会录入查询列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(this.signUpList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportSignUp";
		}catch(Exception e){
			logger.error("SignUpAction.exportCSV",e);
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
		this.getQuerySignUpCondition().setPageSize(size);
		if(this.getQuerySignUpCondition().getSubjectId()==-1){
			this.getQuerySignUpCondition().setSubjectId(0);
		}
	}
	private List<SignUp> signUpList(){
		PageResult result = this.getSignUpService().getSignUpInfoList(this.getQuerySignUpCondition());
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<SignUp> signUpList,List<List<String>> list){
		List<String> tempList=null;
		for(SignUp signUp:signUpList){
			tempList=new ArrayList<String>();
			tempList.add(signUp.getSubjectName());
			tempList.add(this.getEducationInfo(signUp.getEducation()));
			tempList.add(this.getAgeInfo(signUp.getAge()));
			tempList.add(signUp.getPhoneNumber());
			tempList.add(DateUtil.formatDate(signUp.getCreateDate(), "yyyy-MM-dd HH:mm:ss"));
			list.add(tempList);
		}
		return list;
		
	}
	private String getAgeInfo(Integer age){
		String ageStr="";
		switch(age){
		case 1 : ageStr="18-25岁"; break;
		case 2 : ageStr="25-35岁"; break;
		case 3 : ageStr="35-45岁"; break;
		default : ageStr="45岁以上"; break;
		}
		return ageStr;
	}
	private String getEducationInfo(String educationId){
		String educationStr="";
		 switch(Integer.parseInt(educationId)){
			case 1 : educationStr="博士"; break;
			case 2 : educationStr="硕士"; break;
			case 3 : educationStr="本科"; break;
			case 4 : educationStr="专科"; break;
			default : educationStr="其它";
		}
		 return educationStr;
	}
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("项目专业");
		headList.add("学历");
		headList.add("年龄");
		headList.add("手机号码");
		headList.add("创建时间");
		return headList;
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
}
