package com.shangde.edu.stu.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.mail.service.IMail;
import com.shangde.edu.mail.service.MailImpl;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.domain.Planclock;
import com.shangde.edu.stu.domain.SubjectEntity;
import com.shangde.edu.stu.domain.SubjectIdBackEntity;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * 后台处理Action
 * @author baiang.zhao
 * Date：2011-5-19 15:06:07
 */
@SuppressWarnings("unchecked")
public class PlanclockImpl extends BaseService implements IPlanclock {
	
	
	
	/**
	 * 邮件服务对象
	 */
	private IMail mailService;

	public IMail getMailService() {
		return mailService;
	}

	public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public Integer addPlanclock(Planclock planclock) {
		return simpleDao.createEntity("Planclock_NS.createPlanclock", planclock);
	}

	public void updatePlanclock(Planclock planclock) {
		simpleDao.updateEntity("Planclock_NS.updatePlanclock", planclock);
	}

	public Planclock getPlanclockById(Integer planclockId) {
		return simpleDao.getEntity("Planclock_NS.getPlanclockById", planclockId);
	}

	public List<Planclock> getPlanclockList(QueryPlanclockCondition queryPlanclockCondition) {
		return simpleDao.getForList("Planclock_NS.getPlanclockList", queryPlanclockCondition);
	}

    /**
     * 
     * @param queryPlanclockCondition 
     * @return 
     */
    public List<Planclock> getPlanclockListByDate(QueryPlanclockCondition queryPlanclockCondition){
    	return simpleDao.getForList("Planclock_NS.getPlanclockListByDate",
				queryPlanclockCondition);
    }	


	public void delPlanclockById(Integer planclockId) {
		
	}

	public List<Planclock> searchPlanclockListByParam(QueryPlanclockCondition queryPlanclockCondition) {
		return simpleDao.getForList("Planclock_NS.searchPlanclockByParam", queryPlanclockCondition);
	}

	// 分页查询结果
	public PageResult getPlanclockPaperByCondition(QueryPlanclockCondition queryPlanclockCondition) {
		return simpleDao.getPageResult("Planclock_NS.getPlanclockList", "Planclock_NS.getPlanclockListCount", queryPlanclockCondition);
	}

	// 按条件检索结果，已做分页
	public PageResult searchPlanclockListByParamCondition(QueryPlanclockCondition queryPlanclockCondition) {
		return simpleDao.getPageResult("Planclock_NS.searchPlanclockByParam", "Planclock_NS.searchPlanclockByParamCount", queryPlanclockCondition);
	}
	
	public List<Integer> getMyBuySubject(Integer cusId) {
		return simpleDao.getForList("Planclock_NS.getMyBuySubject", cusId);
	}
	/**
	 * 根据时间查询所对应的项目名字；
	 * @return  List
	 */
	
	//
	public List<Planclock> getSubjectId(QueryPlanclockCondition queryPlanclockCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Planclock_NS.getSubjectId", queryPlanclockCondition);
	}
	/**
	 *  根据项目的ID查询所要发的信息的值；
	 * 
	 * @param subjectId
	 */
	public List<SubjectIdBackEntity> getSubjectIdOutInfo(int subjectId) {
		return simpleDao.getForList("Planclock_NS.getSubjectIdBackEntity", subjectId);
	}
	//发邮件
	public void sendForgotPwdEmail(SubjectIdBackEntity subjectIdBackEntity) throws IOException {
			Map<String, String> map = new HashMap<String, String>();
			
			if(subjectIdBackEntity.getCusName()!=null && !"".equals(subjectIdBackEntity.getCusName()))
			{
				map.put("cusName", subjectIdBackEntity.getCusName());
			}else{
				map.put("cusName", subjectIdBackEntity.getCusEmail());
			}
			map.put("cusEmail", subjectIdBackEntity.getCusEmail());
			map.put("subjectName", subjectIdBackEntity.getSubjectName());
			map.put("nonceDate", subjectIdBackEntity.getNonceDate());
			map.put("createdate", subjectIdBackEntity.getSendDate());
			map.put("ccontent", subjectIdBackEntity.getCcontent());
			
				mailService.getMail(IMail.STU_PLANCLOCK_MAIL, map);
			}
	/**
	 * 再msg_message_tbl中添加要显示的信息；
	 * @param subjectIdBackEntity 
	 */
	public int  addTSSO(Message message){
		return simpleDao.createEntity("Planclock_NS.createTSSO", message);
	}
	//添加用户信息；
	public void addtSSOuserInfo(UserMsg UserMsg) {
		 simpleDao.createEntity("Planclock_NS.createTSSOuserInfo", UserMsg);
	}
	 //根据项目ID查询项目表中所对应的值；
	public Subject getSubject(int subjectId){
		return simpleDao.getEntity("Planclock_NS.getSubject", subjectId);
	}
	
	public void updatePlanclockIsent(Planclock planClock){
		simpleDao.updateEntity("Planclock_NS.updatePlanclockIsend", planClock);
	}


}

	

