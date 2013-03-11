package com.shangde.edu.stu.service;

import java.util.Date;
import java.util.List;

import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.stu.domain.CourseSummary;
import com.shangde.edu.stu.condition.QueryCourseSummaryCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class CourseSummaryImpl extends BaseService implements ICourseSummary {

	public Integer addCourseSummary(CourseSummary courseSummary) {
		
		return simpleDao.createEntity("CourseSummary_NS.createCourseSummary",courseSummary);
	}

	public void delCourseSummaryById(int cSumId) {
		
		simpleDao.deleteEntity("CourseSummary_NS.deleteCourseSummaryById", cSumId);
		
	}

	public CourseSummary getCourseSummaryById() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
    *
    * @return 
    */
  public List<CourseSummary> getCourseSummaryListBySummarydate(QueryCourseSummaryCondition queryCourseSummaryCondition){
	  
	   return  simpleDao.getForList("CourseSummary_NS.getCourseSummaryListBySummarydate", queryCourseSummaryCondition);
   }	
	
	
	public CourseSummary getCourseSummaryLimt(
			QueryCourseSummaryCondition queryCourseSummaryCondition) {
		
		return simpleDao.getEntity("CourseSummary_NS.getCourseSummaryLimt",queryCourseSummaryCondition);
	}
	

    public  List<CourseSummary> getCourseSummaryLimtContent(QueryCourseSummaryCondition queryCourseSummaryCondition){
    	
    	return simpleDao.getForList("CourseSummary_NS.getCourseSummaryLimtContent",queryCourseSummaryCondition);
    }
    
    
	
	public void updateCourseSummary(CourseSummary courseSummary) {
		// TODO Auto-generated method stub
		
	}
	
	
    /**
     * 查询知识点
     * @param queryKpointCondition 查询条件
     * @return   
     * fanxin---2011-05-27
     * 
     */
    public List<Kpoint> getKpointListBypointIdList(List<String> list){
    	return simpleDao.getForList("CourseSummary_NS.getKpointListBypointIdList", list);
    }	
}
