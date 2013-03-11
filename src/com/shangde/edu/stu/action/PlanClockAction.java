package com.shangde.edu.stu.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.util.Result;
import com.shangde.edu.cms.action.CommentAction;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.edu.stu.domain.Planclock;
import com.shangde.edu.stu.domain.SubjectEntity;
import com.shangde.edu.stu.domain.SubjectIdBackEntity;
import com.shangde.edu.stu.service.IPlanclock;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
/**
 *<p> Description: 对闹钟的操作，目前需求没有提及删除的功能，所以没有实现该功能</p>
 * Author: baiang.zhao <br>
 * Date: May 18, 2011 <br>
 * Time: 4:07:36 PM <br>
 */
public class PlanClockAction extends CommentAction {
	
	private static final Logger logger = Logger.getLogger(PlanClockAction.class);
	/**
	 * 项目实体类；
	 */
	private Subject subject;
	/**
	 * UserMsg实体类
	 */
	private UserMsg userMsg;
	
	/**
	 * Customer管理接口
	 */
	private ICustomer customerService;
	
	private IsmsService smsService;
	/**
	 * 计划提醒Service层
	 */
	private IPlanclock planclockService;
	/**
	 * 全部的clock
	 */
	private List<Planclock> listPlanClock;
	/**
	 * 根据项目ID查询返回写的一个实体类；
	 */
	private SubjectIdBackEntity subjectIdBackEntity;
	
	private QueryPlanclockCondition queryPlanclockCondition;
	
	/**
	 * 检索结果集
	 */
	private List<Planclock> searcPlanClock;
	
	/**
	 * 单个的计划提醒对象
	 */
	private Planclock planClock ;
	
	/**
	 * 计划提醒的ID
	 */
	private Integer planclockId;
	
	/**
	 * 错误信息
	 */
	private String clockErrorMsgs;
	
	/**
	 * 批处理所有选择ID
	 */
	private String batchParamsId;
	
	/**
	 * 批处理状态
	 */
	private int batchStatus;
	
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList;
	/**
	 * 学科服务
	 */
	private ISubject subjectService;

	// 查询所有的计划提醒
	public String getPlanClockList() {
		try {
			// listPlanClock = planclockService.getPlanclockList(getQueryPlanclockCondition());
			// 分页设置 PageSize 是 该Condition 所继承的 PageQuery 中的属性
			subjectList = subjectService.getAllSubject();
			getQueryPlanclockCondition().setPageSize(20);
			setPage(planclockService.getPlanclockPaperByCondition(getQueryPlanclockCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();
			
		} catch (Exception e) {
			logger.error("PlanClockAction.getPlanClockList", e);
			return ERROR;
		}
		return "getPlanclockList";
		
	}
	
	// 查看一个“计划提醒”
	public String getPlanClockById() {
		try {
			subjectList = subjectService.getAllSubject();
			// 通过ID得到闹钟对象
			planClock = planclockService.getPlanclockById(planclockId);
		} catch (Exception e) {
			logger.error("PlanClockAction.getPlanClockById", e);
			return ERROR;
		}
		return "getPlanclockById";
		
	}
	// 更新一个计划提醒
	public String updatePlanClock() {
		Planclock pClock = null;
		int cId = queryPlanclockCondition.getClockId();
		pClock = planclockService.getPlanclockById(cId);
		
		try {
			// 封裝屬性
			pClock.setAlertdate(queryPlanclockCondition.getAlertdate());
			pClock.setCcontent(queryPlanclockCondition.getCcontent());
			pClock.setCoursename(queryPlanclockCondition.getCoursename());
			pClock.setCreatedate(queryPlanclockCondition.getCreatedate());
			pClock.setCstatus(queryPlanclockCondition.getCstatus());
			pClock.setCtitle(queryPlanclockCondition.getCtitle());
			pClock.setCtype(queryPlanclockCondition.getCtype());
			pClock.setEnddate(queryPlanclockCondition.getEnddate());
			pClock.setIssended(queryPlanclockCondition.getIssended());
			pClock.setStartdate(queryPlanclockCondition.getStartdate());
			pClock.setSubjectId(queryPlanclockCondition.getSubjectId());
			pClock.setSubjectnamne(queryPlanclockCondition.getSubjectnamne());
			pClock.setIsent(queryPlanclockCondition.getIsent());
			
			// 更新闹钟
			planclockService.updatePlanclock(pClock);
			
			planClock = planclockService.getPlanclockById(cId);
			
			return "updatePlanClock";
		} catch (Exception e) {
			logger.error("PlanClockAction.updatePlanClock", e);
			clockErrorMsgs = e.getMessage();
			return "errorClock";
		}
		
	}
	
	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	// 增加一个计划提醒
	public String addPlanClock() {
		try {
			Planclock pClock = new Planclock();
			// 封裝屬性
			pClock.setAlertdate(queryPlanclockCondition.getAlertdate());
			pClock.setCcontent(queryPlanclockCondition.getCcontent());
			pClock.setCoursename(queryPlanclockCondition.getCoursename());
			pClock.setCreatedate(queryPlanclockCondition.getCreatedate());
			pClock.setCstatus(queryPlanclockCondition.getCstatus());
			pClock.setCtitle(queryPlanclockCondition.getCtitle());
			pClock.setCtype(queryPlanclockCondition.getCtype());
			pClock.setEnddate(queryPlanclockCondition.getEnddate());
			pClock.setIssended(queryPlanclockCondition.getIssended());
			pClock.setStartdate(queryPlanclockCondition.getStartdate());
			pClock.setSubjectId(queryPlanclockCondition.getSubjectId());
			pClock.setSubjectnamne(queryPlanclockCondition.getSubjectnamne());
			pClock.setIsent(queryPlanclockCondition.getIsent());
			// 添加
			planclockService.addPlanclock(pClock);
			
			return "addPlanClock";
		} catch (Exception e) {
			logger.error("PlanClockAction.addPlanClock", e);
			this.clockErrorMsgs = e.getMessage();
			return "errorClock";
		}
		
	}
	
	// 跳转到添加页面
	public String toAddPlanClock() {
		try {
			subjectList = subjectService.getAllSubject();
			
			return "toAddPlanClock";
		} catch (Exception e) {
			logger.error("PlanClockAction.toAddPlanClock", e);
			return "errorClock";
		}
		
	}
	
	// 按条件检索计划提醒
	public String searchPlanClock() {
		try {
			subjectList = subjectService.getAllSubject();
			// listPlanClock =  planclockService.searchPlanclockListByParam(queryPlanclockCondition);
			// 同样的，做分页设置
			getQueryPlanclockCondition().setPageSize(20);
			setPage(planclockService.searchPlanclockListByParamCondition(getQueryPlanclockCondition()));
			getPage().setPageSize(20);
			setPageUrlParms();
			return "searchPlanClock";
		} catch (Exception e) {
			logger.error("PlanClockAction.searchPlanClock", e);
			return "errorClock";
		}
	}
	
	// 批处理
	public String batchProcessPlanClock() {
		try{
			// 对字符串进行拆分，得到所有ID的集合
			String[] batchIds = batchParamsId.split(",");
			// 遍历该集合，通过ID得到该闹钟，然后设置它的状态，再去Update该闹钟
			for(int i = 0; i < batchIds.length; i++) {
				 Planclock pClock = planclockService.getPlanclockById(Integer.parseInt(batchIds[i]));
				 pClock.setCstatus(this.batchStatus);
				 planclockService.updatePlanclock(pClock);
			}
		}catch(Exception e){
			logger.error("PlanClockAction.batchProcessPlanClock", e);
			return ERROR;
		}
		
		return "bathProcessPlanClock";
	}
	//action：发短信and邮件
	public void deliverNOteMail(){
		int ji=0;
		try {
			SimpleDateFormat   sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat   sdff=new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat   sdfff=new SimpleDateFormat("yyyy-MM-dd");
			//根据当天时间查询要操作的项目；
			getQueryPlanclockCondition().setDateFind(sdfff.format(new Date()));
			listPlanClock =planclockService.getSubjectId(queryPlanclockCondition);
			for (int i = 0; i < listPlanClock.size(); i++) {
				planClock=new Planclock();
				planClock=listPlanClock.get(i);
				planclockService.updatePlanclockIsent(planClock);
				//根据SubjectID查询所要的值；
				List<SubjectIdBackEntity> list=	planclockService.getSubjectIdOutInfo(planClock.getSubjectId());
				//根据项目ID 查询项目表ID所对应的项目；
				/*
				subject=new Subject();
				subject = planclockService.getSubject(planClock.getSubjectId());
				*/
				 for (int j = 0; j < list.size(); j++) {
					/*
					 try {
						 if(ji>1){
							 //设置延迟3秒，就是没发送一个邮件或者信息都延迟3秒
							Thread.sleep(3000);
						 }
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
					subjectIdBackEntity =new SubjectIdBackEntity();
					 subjectIdBackEntity=list.get(j);
					String sendDate=null;
					String	enddatee=null;
					String	startdate=sdff.format(planClock.getStartdate());
					Date  enddate=planClock.getEnddate();
					if(enddate!=null && !"".equals(enddate))
					{
						enddatee= sdff.format(enddate);
						sendDate=startdate+"至"+enddatee;
					}else{
						sendDate=startdate;
					}
					subjectIdBackEntity.setSubjectName(planClock.getSubjectnamne());
					System.out.println("!!!"+planClock.getSubjectnamne());
					subjectIdBackEntity.setSendDate(sendDate);
					subjectIdBackEntity.setSubjectName(planClock.getSubjectnamne());
					subjectIdBackEntity.setCusEmail(subjectIdBackEntity.getCusEmail());
				//	subjectIdBackEntity.setCusEmail("boliwawa128@sina.com");
					subjectIdBackEntity.setNonceDate(sdf.format(new Date()));
					subjectIdBackEntity.setCcontent(planClock.getCcontent());
				/*
					if(subjectIdBackEntity.getCusMobile()!=null && !"".equals(subjectIdBackEntity.getCusMobile())){
							//发送短信
						 smsService.sendEx("嗨学网提示您:" +planClock.getSubjectnamne()+"将于"+subjectIdBackEntity.getSendDate()+"开始"+planClock.getCcontent()+"。",subjectIdBackEntity.getCusMobile(), "", "", "");
					}
					//发送邮件
					planclockService.sendForgotPwdEmail(subjectIdBackEntity);
					*/
					//添加信息提醒；
					String msgContent="嗨学网提示您:" +subjectIdBackEntity.getSubjectName()+"将于"+ subjectIdBackEntity.getSendDate()+"开始"+subjectIdBackEntity.getCcontent();
					Message message_t
					=new Message();
					message_t.setMsgContent(msgContent);
					message_t.setKeyWord(planClock.getSubjectnamne());
					message_t.setMsgType(5);
					message_t.setCreateTime(new Date());
					message_t.setMsgSort(1);
					message_t.setSenderType(2);
					message_t.setSenderId(1);
					int msgId=planclockService.addTSSO(message_t);
					userMsg=new UserMsg();
					userMsg.setMsgId(msgId);
					userMsg.setSenderId(1);
					userMsg.setSenderType(2);
					userMsg.setReceiverType(1);
					userMsg.setReceiverId(subjectIdBackEntity.getCusId());
					userMsg.setSendTime(new Date());
					planclockService.addtSSOuserInfo(getUserMsg());
				}
			}
		} catch (Exception e) {
			logger.error("PlanClockAction.deliverNOteMail", e);
		}
	}

	public List<Planclock> getListPlanClock() {
		return listPlanClock;
	}

	public void setListPlanClock(List<Planclock> listPlanClock) {
		this.listPlanClock = listPlanClock;
	}

	public QueryPlanclockCondition getQueryPlanclockCondition() {
		if (queryPlanclockCondition == null) {
			queryPlanclockCondition = new QueryPlanclockCondition();
		}
		return queryPlanclockCondition;
	}

	public void setQueryPlanclockCondition(
			QueryPlanclockCondition queryPlanclockCondition) {
		this.queryPlanclockCondition = queryPlanclockCondition;
	}

	public IPlanclock getPlanclockService() {
		return planclockService;
	}

	public void setPlanclockService(IPlanclock planclockService) {
		this.planclockService = planclockService;
	}

	

	public Integer getPlanclockId() {
		return planclockId;
	}

	public void setPlanclockId(Integer planclockId) {
		this.planclockId = planclockId;
	}

	public void setPlanClock(Planclock planClock) {
		this.planClock = planClock;
	}

	public Planclock getPlanClock() {
		return planClock;
	}

	public String getClockErrorMsgs() {
		return clockErrorMsgs;
	}

	public void setClockErrorMsgs(String clockErrorMsgs) {
		this.clockErrorMsgs = clockErrorMsgs;
	}

	public List<Planclock> getSearcPlanClock() {
		return searcPlanClock;
	}

	public void setSearcPlanClock(List<Planclock> searcPlanClock) {
		this.searcPlanClock = searcPlanClock;
	}

	public String getBatchParamsId() {
		return batchParamsId;
	}

	public void setBatchParamsId(String batchParamsId) {
		this.batchParamsId = batchParamsId;
	}

	public int getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(int batchStatus) {
		this.batchStatus = batchStatus;
	}

	public SubjectIdBackEntity getSubjectIdBackEntity() {
		return subjectIdBackEntity;
	}

	public void setSubjectIdBackEntity(SubjectIdBackEntity subjectIdBackEntity) {
		this.subjectIdBackEntity = subjectIdBackEntity;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}


	public UserMsg getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(UserMsg userMsg) {
		this.userMsg = userMsg;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the subjectList
	 */
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList the subjectList to set
	 */
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	/**
	 * @return the subjectService
	 */
	public ISubject getSubjectService() {
		return subjectService;
	}

	/**
	 * @param subjectService the subjectService to set
	 */
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}


}
