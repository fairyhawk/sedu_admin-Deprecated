package com.shangde.edu.sp.action;

import java.util.List;
import org.apache.log4j.Logger;
import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sp.condition.QuerySentPersonCondition;
import com.shangde.edu.sp.domain.SentPerson;
import com.shangde.edu.sp.domain.SentPersonInfo;
import com.shangde.edu.sp.service.ISentPerson;
import com.shangde.edu.sp.service.ISentPersonInfo;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;





public class SentPersonAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(SentPersonAction.class);
	
	private SentPerson  sentPerson;
	private int sentPersonId;
	/**
	 * 已选项目
	 */
	private String subjectIds = "";
	private QuerySentPersonCondition querySentPersonCondition;
	/**
	 * 新项目
	 */
	private String subIds;
	
	/** 分页结果对象 */
	private PageResult page;
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList;
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 * SentPerson Service层
	 */
	private ISentPerson sentPersonService;
	
	/**
	 * SentPersonInfo Service层
	 */
	private ISentPersonInfo sentPersonInfoService;
	
	
	
	private List<SentPersonInfo> sentPersonInfoList;
	
	public SentPerson getSentPerson() {
		return sentPerson;
	}

	public void setSentPerson(SentPerson sentPerson) {
		this.sentPerson = sentPerson;
	}

	
	public ISentPerson getSentPersonService() {
		return sentPersonService;
	}

	public void setSentPersonService(ISentPerson sentPersonService) {
		this.sentPersonService = sentPersonService;
	}

	/**
	 * 跳转到添加页面
	 */
	public String toAddSentPerson() {
		try {
			return "toAddSentPerson";
		} catch (Exception e) {
			logger.error("SentPersonAction.toAddSentPerson", e);
			return "error";
		}
	}
	
	/**
	 * 增加一个接收人
	 */
	public String addSentPerson() {
		try {
			SentPerson sPerson = new SentPerson();
			// 封裝屬性
			sPerson.setPersonName(querySentPersonCondition.getPersonName());
			sPerson.setPersonPhone(querySentPersonCondition.getPersonPhone());
			
			// 添加
			sentPersonService.addSentPerson(sPerson);
			return "addSentPerson";
		} catch (Exception e) {
			logger.error("SentPersonAction.addSentPerson", e);
			return "error";
		}
	}

	/**
	 * 获取全部列表
	 * @author 范昕 
	 * @return
	 */
	public String getAllSentPersonList() {
		try {
			getQuerySentPersonCondition().setPageSize(20);
			setPage(sentPersonService.getSentPersonBackPaperByCondition(getQuerySentPersonCondition()));
					
			// 需要再设置一次分页
			setPageUrlParms();
			getPage().setPageSize(20);
			return "toSentPersonList";

		} catch (Exception e) {
			logger.error("getAllSentPersonList()方法出错了！", e);
			return "error";
		}
	}
	
	/**
	 * 按条件搜索列表
	 * @author 范昕 
	 * @return
	 */
	public String seachSentPersonList(){
		try {
			getQuerySentPersonCondition().setPageSize(20);
			setPage(sentPersonService.searchSentPersonListByParamCondition(querySentPersonCondition));
					
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();
			
			return "seachSentPersonList";

		} catch (Exception e) {
			logger.error("seachSentPersonList()方法出错了！", e);
			return "error";
		}
		
	}
	
	
	/**
	 * 依据Id 取得接收人
	 */
	public String getSentPersonById(){
		try{
			sentPerson = sentPersonService.getSentPersonById(sentPersonId);
			if(sentPerson!=null){
				
				return "getSentPersonById";
			}else{
				logger.error("getSentPersonById()方法 sentPerson对象为空！");
				return "error";
			}
		}catch(Exception e){
			logger.error("getSentPersonById()方法出错了！", e);
			return "error";
		}
	}
	
	/**
	 * 更新一个接收人
	 */
	public String updateSentPerson(){
		try{
			SentPerson upSentPerson = null;
			//获取更新对象的Id
			int spId = querySentPersonCondition.getId();
			upSentPerson = sentPersonService.getSentPersonById(spId);
			
			// 封装属性
			if(upSentPerson != null){
				upSentPerson.setPersonName(querySentPersonCondition.getPersonName());
				upSentPerson.setPersonPhone(querySentPersonCondition.getPersonPhone());
				// 更新
				sentPersonService.updateSentPerson(upSentPerson);
				
				return "updateSentPerson";
			}else{
				logger.error("updateSentPerson()方法 upSentPerson对象为空！");
				return "error";
			}
		}catch(Exception e){
			logger.error("updateSentPerson()方法出错了！", e);
			return "error";
		}
		
	}
	
	
	/**
	 * 删除一个接收人
	 */
	public String delSentPersonById(){
		try{
			//有主外间关联 ，先删除从表 再删主表
			sentPersonInfoService.delSPInfoByspId(sentPersonId);
			
			sentPersonService.delSentPersonById(sentPersonId);
			
			return "delSentPersonById";
		}catch(Exception e){
			logger.error("delSentPersonById()方法出错了！", e);
			return "error";
		}
	}
	
	/**
	 * 获取信息接收人详情
	 */
	public String getSentPersonInfo(){
		try{
			//获取接收人
			sentPerson = sentPersonService.getSentPersonById(sentPersonId);
			subjectList = subjectService.getAllSubject();
			
			//获取接收人接收的项目集合，并把所有项目的Id 组合成字符窜 返回给页面
			sentPersonInfoList = sentPersonInfoService.getSPInfoByspId(sentPersonId);
			//组合字符窜
			if(sentPersonInfoList!=null && sentPersonInfoList.size()>0){
				for(int i=0;i<sentPersonInfoList.size();i++){
					SentPersonInfo spInfo = sentPersonInfoList.get(i);
					int subId = spInfo.getSubjectId();
					subjectIds += subId+",";
				}
			}
			
			return "getSentPersonInfo";
		}catch(Exception e){
			logger.error("getSentPersonInfo()方法出错了！", e);
			return "error";
		}
		
	}
	
	/**
	 * 更新信息接收人详情
	 */
	public String updSentPersonInfo(){
		try{
			//删除所有spId为指定值的SentPersonInfo
			int spId = querySentPersonCondition.getId();
			sentPersonInfoService.delSPInfoByspId(spId);
			
			//重新插入新数据
			int instSubId = 0;
			if(subIds!=null && subIds.length()>0){
				String[] operateIds = subIds.trim().split(",");
				for(int i=0;i<operateIds.length;i++){
					instSubId = Integer.parseInt(operateIds[i].trim());
					
					SentPersonInfo spInfoTemp = new SentPersonInfo();
					spInfoTemp.setSpId(spId);
					spInfoTemp.setSubjectId(instSubId);
					
					sentPersonInfoService.addSentPersonInfo(spInfoTemp);
				}
			}
			return "updSentPersonInfo";
		}catch(Exception e){
			logger.error("updSentPersonInfo()方法出错了！", e);
			return "error";
		}
		
	}	
	
	
	public QuerySentPersonCondition getQuerySentPersonCondition() {
		if (querySentPersonCondition == null) {
			querySentPersonCondition = new QuerySentPersonCondition();
		}
		
		return querySentPersonCondition;
	}

	public void setQuerySentPersonCondition(
			QuerySentPersonCondition querySentPersonCondition) {
		this.querySentPersonCondition = querySentPersonCondition;
	}
	
	public PageResult getPage() {
		return page;
	}


	public void setPage(PageResult page) {
		this.page = page;
	}
	
	public int getSentPersonId() {
		return sentPersonId;
	}

	public void setSentPersonId(int sentPersonId) {
		this.sentPersonId = sentPersonId;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	public List<SentPersonInfo> getSentPersonInfoList() {
		return sentPersonInfoList;
	}
	public void setSentPersonInfoList(List<SentPersonInfo> sentPersonInfoList) {
		this.sentPersonInfoList = sentPersonInfoList;
	}
	public ISentPersonInfo getSentPersonInfoService() {
		return sentPersonInfoService;
	}
	public void setSentPersonInfoService(ISentPersonInfo sentPersonInfoService) {
		this.sentPersonInfoService = sentPersonInfoService;
	}
	public String getSubjectIds() {
		return subjectIds;
	}

	public void setSubjectIds(String subjectIds) {
		this.subjectIds = subjectIds;
	}

	public String getSubIds() {
		return subIds;
	}

	public void setSubIds(String subIds) {
		this.subIds = subIds;
	}

}
