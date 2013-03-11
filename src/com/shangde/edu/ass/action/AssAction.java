package com.shangde.edu.ass.action;

import java.util.Date;
import java.util.List;
import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.ass.condition.QueryAssessCondition;
import com.shangde.edu.ass.domain.Assess;
import com.shangde.edu.ass.domain.Reassess;
import com.shangde.edu.ass.dto.AssessDto;
import com.shangde.edu.ass.service.IAssess;
import com.shangde.edu.ass.service.IReassess;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVedio;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class AssAction extends CommonAction {

	private IAssess assessService;

	private IReassess reassessService;

	private ICustomer customerService;

	private Assess assess;

	private Kpoint kpoint;

	private Customer customer;

	private Reassess reassess;

	private AssessDto assessDto;

	private List<Assess> assList;

	private List<AssessDto> assDtoList;

	private ISubject subjectService;

	private IKpoint kpointService;

	private List<Subject> subList;

	private List<Kpoint> kpointList;

	private String subjectId;
	
	private Subject subject;

	private QueryAssessCondition queryAssessCondition;

	private String startTime;

	private String endTime;

	private String startHH = " 00:00:00";

	private String endHH = " 23:59:59";

	private String verifyEndTime;
	private String verifyEndHH;

	private String verifyStartTime;
	private String verifyStartHH;
	
	private String assTime;

	private int sta = -1;
	private int sta1 =-1;

	private String voSize;

	private IVedio vedioService;

	private int[] cks;
	
	private int aType=0;

	public String getVoSize() {
		return voSize;
	}

	public void setVoSize(String voSize) {
		this.voSize = voSize;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public String getPageAssList() {

		this.subList = this.subjectService.getAllSubject();
		this.getQueryAssessCondition().setPageSize(20);
		this.getQueryAssessCondition().setStatus(-1);
		PageResult result = this.assessService.getPageAssessList(this
				.getQueryAssessCondition());
		this.assDtoList = result.getPageResult();
		getSubjectName();
		result.setPageResult(assDtoList);
		this.setPage(result);
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(20);
		}
		return "assList";
	}

	public void getSubjectName() {
		for (int i = 0; i < this.assDtoList.size(); i++) {

			this.kpoint = this.kpointService.getKpointById(this.assDtoList.get(
					i).getKpointId());
			if (kpoint != null && kpoint.getName() != "") {
				this.assDtoList.get(i).setKpointName(kpoint.getName());
			}

			for (int j = 0; j < subList.size(); j++) {
				if (this.assDtoList.get(i).getSubjectId() == this.subList
						.get(j).getSubjectId()) {
					this.assDtoList.get(i).setSubjectName(
							this.subList.get(j).getSubjectName());
				}
			}
		}
	}

	public String getAssessById() {

		String returnSer;
		if (this.assess.getId() != 0) {
			assess = this.assessService.getAssessById(this.assess.getId());
			subject= this.subjectService.getSubjectById(assess.getSubjectId());
			this.kpoint = this.kpointService
					.getKpointById(assess.getKpointId());
			this.customer = this.customerService.getCustomerById(assess
					.getCusId());
			this.reassess = this.reassessService.getReassessByAssId(this.assess
					.getId());

			Vedio vedio = this.vedioService.getVedioByPointid(assess
					.getKpointId()); // 得到当前节点 视频信息
			this.voSize = vedio.getVoSize(); // 得到当前视频长度
			if (this.kpoint != null && this.kpoint.getName() != "") {
				this.getAssessDto().setKpointName(this.kpoint.getName());
			}
		}
		if(aType==1){
			returnSer = "ViewAssess";
		}else {
			returnSer ="toAssess";
		}
		return returnSer;
	}

	public String getAssessByCondition() {
		getTimeStart(); // 调用添加时间条件参数
		if (!this.assess.getAssTitle().equals("")) {
			this.getQueryAssessCondition().setAssTitle(
					this.assess.getAssTitle());
		}
		if (this.assess.getSubjectId() != 0) {
			this.getQueryAssessCondition().setSubId(this.assess.getSubjectId());
		}
		this.subList = this.subjectService.getAllSubject();
		this.getQueryAssessCondition().setPageSize(20);
		if(sta!=-1){
			this.getQueryAssessCondition().setStatus(sta);
		}
		PageResult result = this.assessService.getBackPageAssessList(this
				.getQueryAssessCondition());
		this.assDtoList = result.getPageResult();
		getSubjectName();
		result.setPageResult(assDtoList);
		this.setPage(result);
		getTimeOver(); // 时间参数用完之后 做处理 把时分秒截掉
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(20);
		}
		return "assList";
	}

	private void getTimeStart() { // 赋值时间参数

		if (!startTime.equals("")) {
			startTime += startHH;
			this.getQueryAssessCondition().setStartTime(startTime);
		}
		if (!endTime.equals("")) {
			endTime += endHH;
			this.getQueryAssessCondition().setEndTime(endTime);
		}
		if (!verifyStartTime.equals("")) {
			verifyStartTime += verifyStartHH;
			this.getQueryAssessCondition().setVerifyStartTime(verifyStartTime);
		}
		if (!verifyEndTime.equals("")) {
			verifyEndTime += verifyEndHH;
			this.getQueryAssessCondition().setVerifyEndTime(verifyEndTime);
		}

	}

	private void getTimeOver() { // 时间参数处理，截掉时分秒部分

		if (endHH != null && !endHH.equals("") && endTime != null
				&& !"".equals(endTime)) {

			endTime = endTime.substring(0, endTime.indexOf(endHH));

		}
		if (startHH != null && !startHH.equals("") && startTime != null
				&& !"".equals(startTime)) {

			startTime = startTime.substring(0, startTime.indexOf(startHH));
		}

		if (verifyEndHH != null && !verifyEndHH.equals("")
				&& verifyEndTime != null && !"".equals(verifyEndTime)) {

			verifyEndTime = verifyEndTime.substring(0, verifyEndTime
					.indexOf(verifyEndHH));

		}
		if (verifyStartTime != null && !verifyStartTime.equals("")
				&& verifyStartTime != null && !"".equals(verifyStartTime)) {

			verifyStartTime = verifyStartTime.substring(0, verifyStartTime
					.indexOf(verifyStartHH));
		}
	}

	public String updateAssess() {

		if (this.assess != null && this.assess.getKpointId() != 0) {


			this.assess.setVerifyTime(new Date());
			if(subject!=null){
				this.assess.setSubjectId(subject.getSubjectId());
			}
			if (!this.reassess.getReassContext().equals("")
					&& this.reassess != null) {
				Reassess reassess = this.reassessService
						.getReassessByAssId(this.assess.getId());

				if (reassess != null) {
					reassess.setAssId(this.assess.getId());
					reassess.setReassTime(new Date());
					reassess.setReCusId(this.assess.getCusId());
					reassess.setReassContext(this.reassess.getReassContext());
					this.reassessService.updateReassess(reassess);
				} else {
					this.reassess.setAssId(this.assess.getId());
					this.reassess.setReassTime(new Date());
					this.reassessService.addReassess(this.reassess);
				}
			}

			if (sta != -1) {
				this.assess.setStatus(sta);
			}
			this.assessService.updateAssess(this.assess);
		}
		return "success";
	}

	public String updateAssStatus() {
		if (cks != null) {
			if (cks.length != 0) {
				for (int i = 0; i < cks.length; i++) {
					Assess assess = this.assessService.getAssessById(cks[i]);
					if (assess != null) {
						if (assess.getStatus() != sta1) {
							assess.setStatus(sta1);
							this.assessService.updateAssess(assess);
						}
					}
				}
			}
		}
		return "getPageAssList";
	}

	public int getSta() {
		return sta;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public String getVerifyEndTime() {
		return verifyEndTime;
	}

	public void setVerifyEndTime(String verifyEndTime) {
		this.verifyEndTime = verifyEndTime;
	}

	public String getVerifyEndHH() {
		return verifyEndHH;
	}

	public void setVerifyEndHH(String verifyEndHH) {
		this.verifyEndHH = verifyEndHH;
	}

	public String getVerifyStartTime() {
		return verifyStartTime;
	}

	public void setVerifyStartTime(String verifyStartTime) {
		this.verifyStartTime = verifyStartTime;
	}

	public String getVerifyStartHH() {
		return verifyStartHH;
	}

	public void setVerifyStartHH(String verifyStartHH) {
		this.verifyStartHH = verifyStartHH;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public String addAssess() {

		this.assessService.addAssess(this.assess);
		return "success";
	}

	public IAssess getAssessService() {
		return assessService;
	}

	public void setAssessService(IAssess assessService) {
		this.assessService = assessService;
	}

	public Assess getAssess() {
		return assess;
	}

	public void setAssess(Assess assess) {
		this.assess = assess;
	}

	public QueryAssessCondition getQueryAssessCondition() {
		if (this.queryAssessCondition == null) {
			this.queryAssessCondition = new QueryAssessCondition();
		}
		return queryAssessCondition;
	}

	public void setQueryAssessCondition(
			QueryAssessCondition queryAssessCondition) {
		this.queryAssessCondition = queryAssessCondition;
	}

	public void setAssList(List<Assess> assList) {
		this.assList = assList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}

	public List<Kpoint> getKpointList() {
		return kpointList;
	}

	public void setKpointList(List<Kpoint> kpointList) {
		this.kpointList = kpointList;
	}

	public List<Assess> getAssList() {
		return assList;
	}

	public List<AssessDto> getAssDtoList() {
		return assDtoList;
	}

	public void setAssDtoList(List<AssessDto> assDtoList) {
		this.assDtoList = assDtoList;
	}

	public AssessDto getAssessDto() {
		if (this.assessDto == null) {
			this.assessDto = new AssessDto();
		}
		return assessDto;
	}

	public void setAssessDto(AssessDto assessDto) {
		this.assessDto = assessDto;
	}

	public Kpoint getKpoint() {
		if (this.kpoint == null) {
			this.kpoint = new Kpoint();
		}
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public IReassess getReassessService() {
		return reassessService;
	}

	public void setReassessService(IReassess reassessService) {
		this.reassessService = reassessService;
	}

	public Reassess getReassess() {
		return reassess;
	}

	public void setReassess(Reassess reassess) {
		this.reassess = reassess;
	}

	public int[] getCks() {
		return cks;
	}

	public void setCks(int[] cks) {
		this.cks = cks;
	}

	public int getSta1() {
		return sta1;
	}

	public void setSta1(int sta1) {
		this.sta1 = sta1;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getAType() {
		return aType;
	}

	public void setAType(int type) {
		aType = type;
	}
}
