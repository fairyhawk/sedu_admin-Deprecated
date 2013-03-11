package com.shangde.edu.ass.service;

import java.util.List;
import com.shangde.edu.ass.domain.Assess;
import com.shangde.edu.ass.condition.QueryAssessCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.stu.service.IVideoStatistics;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


	/**
	 * User:guoqiang.liu
	 * Date:2011-05-23
	 * 
	 * */
@SuppressWarnings("unchecked")
public class AssessImpl extends BaseService implements IAssess{
	private ISubject subjectService;	
	private ICourse courseService;
	private ICustomer customerService;
	private IKpoint kpointService;
	private ISellWay sellWayService;
	private IVideoStatistics   videoStatisticsService;
	private QueryKpointCondition qkCondition;
	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public java.lang.Integer  addAssess(Assess assess) {
    	return simpleDao.createEntity("Assess_NS.createAssess",assess);
    }

    public void delAssessById(int assessId){
    	simpleDao.deleteEntity("Assess_NS.deleteAssessById", assessId);
    }

    public void updateAssess(Assess assess) {
        simpleDao.updateEntity("Assess_NS.updateAssess",assess);
    }

    public Assess getAssessById(int assessId) {
    	 return simpleDao.getEntity("Assess_NS.getAssessById",assessId);
    }
    /**
     * 查询好评和已评的共用方法
     * by zhangjuqiang
     */
    public List<Assess> getAssessListByCondition(QueryAssessCondition queryAssessCondition) {
    	//videoStatisticsService.getUserLearnKpointBySubjectId();
    	List<Assess> assList=simpleDao.getForList("Assess_NS.getAssessListByCondition",queryAssessCondition);
    	QueryAssessCondition condition=new QueryAssessCondition();
    	for (int i = 0; i < assList.size(); i++) {
    		Kpoint kpoint=kpointService.getKpointById(assList.get(i).getKpointId());
    		condition.setKpointId(assList.get(i).getKpointId());
    		condition.setSellwayId(assList.get(i).getSellwayId());
    		kpoint.setAssCount(getAssessCountByKpointId(condition));
			assList.get(i).setKpoint(kpoint);
		}
    	if(queryAssessCondition.getCusId()==0){//查好评
    		for (int i = 0; i < assList.size(); i++) {
				if(assList.get(i).getLevelAvg()<2)assList.remove(i);
			}
    	}
    	return assList;
    }
    /**
     * 查询用户注册时所选专业，首次进入知识中心设为默认专业
     * by zhangjuqiang
     */
	public Subject getDefaultSubject(int cusId){
		Customer cus=customerService.getCustomerById(cusId);
		return subjectService.getSubjectById(cus.getSubjectId()) ;
	}
	public Subject getCurrSubject(int subId){
		return subjectService.getSubjectById(subId);
	}
	/**
	 * 查询用户购买包所属专业list,用于右侧导航
	 * by zhangjuqiang
	 */
	public List<Subject> getBuySubjectListByCusId(int cusId){
		return subjectService.getSubjectListForUnAss(cusId);
	}
	/**
	 * 查询未评价知识点
	 * by张聚强
	 */
	public List<Kpoint> getKpointListForUnAss(QueryAssessCondition aCondition) {		
		qkCondition.setCusId(aCondition.getCusId());
		//qkCondition.setSubjectId(aCondition.getSubId());
		//List<String> watchKids=getWatchedKpointList(aCondition.getSubId(), aCondition.getCusId());		
		qkCondition.setWatchKids(aCondition.getWatchKids());
		qkCondition.setSubjectId(aCondition.getSubId());
		qkCondition.setAssKids(aCondition.getAssKids());
		List<Kpoint> kList=kpointService.getKpointListForUnAss(qkCondition);
		QueryAssessCondition condition=new QueryAssessCondition();
		for(Kpoint kpoint:kList){
			condition.setKpointId(kpoint.getPointId());
			condition.setSellwayId(kpoint.getSellWayId());
			kpoint.setAssCount(getAssessCountByKpointId(condition));
		}
		return kList;
	}
	/**
	 * 查询用户已看过知识点
	 * 调用刘庆刚的方法by zhangjuqiang
	 */
	public List<String> getWatchedKpointList(int subId,int cusId){
		return videoStatisticsService.getUserLearnKpointBySubjectId(subId, cusId);
	}
	public int getKpointCountByLevel(QueryAssessCondition condition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Assess_NS.getKpointCountByLevel", condition);
	}
	/**
	 * 查询已评价知识点IDS
	 * by zhangjuqiang
	 */
	public List<Integer> getAssKpointIdsByCusId(QueryAssessCondition aCondition){
		return simpleDao.getForList("Assess_NS.getAssKpointIdsByCusId", aCondition);
	}
	
	public int getSellWayIdsByKpointId(QueryAssessCondition condition) {
		// TODO Auto-generated method stub
		qkCondition.setPointId(condition.getKpointId());
		qkCondition.setCusId(condition.getCusId());
		List<Integer> sids=kpointService.getSellWayIdsByKpointId(qkCondition);
		if(sids==null||sids.size()<1){
			return 0;
		}else{
		//如果有多种售卖方式，取第一个
			return sids.get(0);
		}
	}
	
	public int getAssessCountByKpointId(QueryAssessCondition condition){
		return simpleDao.getEntity("Assess_NS.getAssessCountByKpointId", condition);
	}
	
	/**
	 * 得到全部评论信息  或根据专业ID 课程ID 查询
	 */
	public List<Assess> getAllAssessList(
			QueryAssessCondition queryAssessCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Assess_NS.getAllAssessList", queryAssessCondition);
	}
	
	public PageResult getMoreAssessByCondition(QueryAssessCondition condition) {
		// TODO Auto-generated method stub
		PageResult result=simpleDao.getPageResult("Assess_NS.getMoreAssessByCondition","Assess_NS.getMyAssessCount", condition);
		List<Assess> assList=result.getPageResult();
		QueryAssessCondition aCondition=new QueryAssessCondition();
		for (int i = 0; i < assList.size(); i++) {
			Kpoint kpoint=kpointService.getKpointById(assList.get(i).getKpointId());
			aCondition.setKpointId(assList.get(i).getKpointId());
			aCondition.setSellwayId(assList.get(i).getSellwayId());
    		kpoint.setAssCount(getAssessCountByKpointId(aCondition));
			assList.get(i).setKpoint(kpoint);
		}
		result.setPageResult(assList);
		return result;		
	}
	public PageResult getMoreKpointListForUnAss(QueryAssessCondition condition){
		QueryKpointCondition kCondition=new QueryKpointCondition();
		kCondition.setCusId(condition.getCusId());
		kCondition.setSubjectId(condition.getSubId());
		kCondition.setAssKids(condition.getAssKids());
		kCondition.setWatchKids(condition.getWatchKids());
		kCondition.setCurrentPage(condition.getCurrentPage());
		kCondition.setPageSize(condition.getPageSize());
		PageResult result=kpointService.getMoreKpointListForUnAss(kCondition);
		List<Kpoint> kList=result.getPageResult();
		QueryAssessCondition aCondition=new QueryAssessCondition();
		for(Kpoint kpoint:kList){
			aCondition.setKpointId(kpoint.getPointId());
			aCondition.setSellwayId(kpoint.getSellWayId());
			kpoint.setAssCount(getAssessCountByKpointId(aCondition));
		}
		return result;
	}

//	public int getMyAssessCount(QueryAssessCondition condition) {
//		// TODO Auto-generated method stub
//		return simpleDao.getEntity("Assess_NS.getMyAssessCount", condition);
//	}
	
	public PageResult getPageAssessList(QueryAssessCondition queryAssessCondition){
		return simpleDao.getPageResult("Assess_NS.getAssessDtoList", "Assess_NS.getPageAssessList", queryAssessCondition);
	}
    
    
    public List<Subject> getUnBuySubject(List<Integer> sids){
    	return subjectService.getUnBuySubject(sids);
    }

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	

	public ISellWay getSellWayService() {
		return sellWayService;
	}


	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

	


	public int getAssessCount(QueryAssessCondition queryAssessCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Assess_NS.getAssessCount", queryAssessCondition);
	}

	/**
	 * 根据用户ID 查询当前用户的评论
	 */
	public List<Assess> getAssessByCusId(QueryAssessCondition queryAssessCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Assess_NS.getAssessByCusId", queryAssessCondition);
	}

	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	public QueryKpointCondition getQkCondition() {
		return qkCondition;
	}

	public void setQkCondition(QueryKpointCondition qkCondition) {
		this.qkCondition = qkCondition;
	}

	public PageResult getBackPageAssessList(
			QueryAssessCondition queryAssessCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Assess_NS.getBackAssessDtoList", "Assess_NS.getBackPageAssessList", queryAssessCondition);
	}

	

	

}
