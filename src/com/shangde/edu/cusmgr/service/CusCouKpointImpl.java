package com.shangde.edu.cusmgr.service;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterKpointDTO;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.condition.QueryCusMgrCondition;
import com.shangde.edu.cusmgr.domain.CusCouKpoint;
import com.shangde.edu.cusmgr.dto.WatchRankDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * CusCouKpoint
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
@SuppressWarnings("unchecked")
public class CusCouKpointImpl extends BaseService implements ICusCouKpoint{
	
    public java.lang.Integer addCusCouKpoint(CusCouKpoint cusCouKpoint) {
    	return simpleDao.createEntity("CusCouKpoint_NS.createCusCouKpoint",cusCouKpoint);
    }

    public void delCusCouKpointById(int id){
        simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointById",id);
    }

    public void updateCusCouKpoint(CusCouKpoint cusCouKpoint) {
        simpleDao.updateEntity("CusCouKpoint_NS.updateCusCouKpoint",cusCouKpoint);
    }

    public CusCouKpoint getCusCouKpointById(int id) {
        return simpleDao.getEntity("CusCouKpoint_NS.getCusCouKpointById",id);
    }

    public List<CusCouKpoint> getCusCouKpointList(QueryCusCouKpointCondition queryCusCouKpointCondition) {
        return simpleDao.getForList("CusCouKpoint_NS.getCusCouKpointList",queryCusCouKpointCondition);
    }



	public void delCusCouKpointByCus(QueryCusMgrCondition queryCusMgrCondition) {
		simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByCus",queryCusMgrCondition);
	}
	
	/**
	 * 根据用户ID获取课程集合
	 */
	public List<Course> getCusCouKpointListByCusId(int cusId) {
		List<Course> result = new ArrayList<Course>();
		//根据学员ID 通过流水取得购买成功的课程list
		result = simpleDao.getForList("Course_NS.getAllCourseInfoByIdAndFinance", cusId);
		return result;
	}

	/**
	 * 获取用户知识树
	 */
	public List<UserKpointDTO> getKpointListByCusIdAndCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition) {
		
		return simpleDao.getForList("CusCouKpoint_NS.getKpointListByCusIdAndCourseId", queryCusCouKpointCondition);
	}
	
	/**
	 * 通过课程ID删除用户知识点
	 */
	public void deleteCusCouKpointByCourseId(int courseId){
		simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByCourseId", courseId);
	}
	
	/**
	 * 根据用户ID删除用户知识点
	 */
	public void delCusCouKpointByCusId(int cusId) {
		simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByCusId",cusId);
	}
	
	/**
	 * @author chenshuai
	 * 功能：根据用户ID及课程ID获取该课程表中已经学完的知识点数
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getReadedCusCouKpointCountByCondition(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getReadedCusCouKpointCountByCondition", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：判断用户课程的知识点是否都已经看完 等于0时则看完
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer isAllReadByCondition(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.isAllReadByCondition", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：通过课程ID及用户ID查找所有叶子节点为1的所有知识点
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public List<UserCenterKpointDTO> getUserCenterKpointDTOByCusIdAndCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getForList("CusCouKpoint_NS.getUserCenterKpointDTOByCusIdAndCourseId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：获取用户最后学习的视频ID
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getUserLastPointIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getUserLastPointIdByCusId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：根据课程ID及用户ID获取用户该课程最后学习的知识点ID
	 * 若全部未观看，则返回该课程的第一个知识点ID
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getUserCourseLastIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getUserCourseLastIdByCusId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：根据课程ID获取该学员今天听视频的数量
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getCountUserStudyTodayByCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getCountUserStudyTodayByCourseId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量多指定数量视频的用户数
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getCountLargerThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getCountLargerThanUserStudyToday", queryCusCouKpointCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量小于指定数量视频的用户数
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getCountlessThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getCountlessThanUserStudyToday", queryCusCouKpointCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：查询比该用户学习数量等于指定数量视频的用户数
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getCountEqualNumThanUserStudyToday(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getCountEqualNumThanUserStudyToday", queryCusCouKpointCondition);
	}
	
	/**
	 * @author cxs
	 * 功能：获取学员数量，通过课程ID
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getCusSizeByCourseId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		Integer re =simpleDao.getEntity("CusCouKpoint_NS.getCusSizeByCourseId", queryCusCouKpointCondition);
		if (null==re){
			return 0;
		}else{
			return re.intValue();
		}
		
	}
	
	/**
	 * @author chenshuai
	 * 功能：根据用户ID查询所购买的专业集合
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public List<Subject> getUserCenterSubjectListByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getForList("CusCouKpoint_NS.getUserCenterSubjectListByCusId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：用户是否看过课程，0表示没有
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer isReadCourse(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.isReadCourse", queryCusCouKpointCondition);	
	}
	
	/**
	 * @author chenshuai
	 * 功能：获取用户购买最早的课程的专业ID
	 * @param queryCusCouKpointCondition
	 * @return
	 */
	public Integer getEarliestSubjectIdByCusId(QueryCusCouKpointCondition queryCusCouKpointCondition){
		return simpleDao.getEntity("CusCouKpoint_NS.getEarliestSubjectIdByCusId", queryCusCouKpointCondition);
	}
	
	/**
	 * @author chenshuai
	 * 功能：获取课程已经上传视频的数量
	 * @param courseId
	 * @return
	 */
	public Integer getCourseVedioSizeByCourseId(int courseId){
		return simpleDao.getEntity("CusCouKpoint_NS.getCourseVedioSizeByCourseId", courseId);
	}

	public List getLearningInfoInTime(QueryCusCouKpointCondition queryCusCouKpointCondition) {
		return simpleDao.getForList("CusCouKpoint_NS.getLearningInfoInTime", queryCusCouKpointCondition);
	}
	/**
	 * @author liuqinggang
	 * 功能：获取在几分钟内学习了课程的信息 ,所有的课程
	 * @return
	 */ 
	public List<WatchRankDTO> getLearningInfoInTimeAllSubject() {
		return simpleDao.getForList("CusCouKpoint_NS.getLearningInfoInTimeAllSubject", null);
	}
	/**
	 * @author liuqinggang
	 * 功能：通过包ID查找所有课程
	 * @param sellId 包ID
	 * @return
	 */
	public List<UserCenterCourseDTO> getCouserListBySellId(String sellId){
		return simpleDao.getForList("Course_NS.getCouserListBySellId", sellId);
	}
	
	/**
	 * 根据用户ID获取课程集合
	 */
	public List<Integer> getCusCouIdKpointListByCusId(int cusId) {
		
		//根据学员ID 通过流水取得购买成功的课程list
		List<Integer> result = simpleDao.getForList("Course_NS.getCourseIdAllCourseInfoByIdAndFinance", cusId);
		return result;
	}
}
