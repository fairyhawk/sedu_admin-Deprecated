package com.shangde.edu.velocity.action;

import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryAreaCondition;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.service.IArea;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.velocity.condition.QueryVelocityCondition;
import com.shangde.edu.velocity.domain.Velocity;
import com.shangde.edu.velocity.service.IVelocity;

public class velocityAction extends CommonAction {

	private IVelocity velocityService; 

	private Velocity velocity; 

	private List<Velocity> velocityList; 

	private QueryVelocityCondition queryVelocityCondition;

	private List<Subject> subjectList;

	private ISubject subjectService;

	
	private List<Area> areaList;

	private IArea areaService;

	private QueryAreaCondition queryAreaCondition;

	private int parenId = -1;

	public String getAllVelocity() {
		this.getQueryVelocityCondition().setPageSize(30);
		PageResult pageResult = this.velocityService
		.getAllVelocity(this.getQueryVelocityCondition());
		velocityList = pageResult.getPageResult();
		setPage(pageResult);
		 setPageUrlParms();
		 if(getPage()!=null){
			   getPage().setPageSize(30);
		 }
		
		getAllSubjectList();
		getAllProvinceList();
		return "allVelocityList";
	}

	public String getVelocityListByCondition() {
			
		return "";
	}

	public void getAllSubjectList() {

		subjectList = this.subjectService.getAllSubject();

	}

	public String getAllProvinceList() {
		if (parenId == -1) {
			this.getQueryAreaCondition().setParentId(1);
		}else {
			this.getQueryAreaCondition().setParentId(parenId);
		}
		areaList = areaService.getAreaList(this.getQueryAreaCondition());
		
		this.setResult(new Result<List<Area>>(true,"",null,areaList));
		return "json";
	}

	public IVelocity getVelocityService() {
		return velocityService;
	}

	public void setVelocityService(IVelocity velocityService) {
		this.velocityService = velocityService;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}

	public List<Velocity> getVelocityList() {
		return velocityList;
	}

	public void setVelocityList(List<Velocity> velocityList) {
		this.velocityList = velocityList;
	}

	public QueryVelocityCondition getQueryVelocityCondition() {
		if(queryVelocityCondition==null){
			queryVelocityCondition = new QueryVelocityCondition();
		}
		return queryVelocityCondition;
	}

	public void setQueryVelocityCondition(
			QueryVelocityCondition queryVelocityCondition) {
		this.queryVelocityCondition = queryVelocityCondition;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public List<Area> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<Area> areaList) {
		this.areaList = areaList;
	}

	public IArea getAreaService() {
		return areaService;
	}

	public void setAreaService(IArea areaService) {
		this.areaService = areaService;
	}

	public QueryAreaCondition getQueryAreaCondition() {
		if(queryAreaCondition ==null){
			queryAreaCondition = new QueryAreaCondition();
		}
		return queryAreaCondition;
	}

	public void setQueryAreaCondition(QueryAreaCondition queryAreaCondition) {
		this.queryAreaCondition = queryAreaCondition;
	}

	public int getParenId() {
		return parenId;
	}

	public void setParenId(int parenId) {
		this.parenId = parenId;
	}
}
