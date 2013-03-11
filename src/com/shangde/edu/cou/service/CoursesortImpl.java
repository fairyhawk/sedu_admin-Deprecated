package com.shangde.edu.cou.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;

/**
 * Coursesort课程分类服务类
 * ICoursesort接口的实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class CoursesortImpl extends BaseService implements ICoursesort{
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	/**
	 *添加课程分类
	 *@param coursersort 课程分类 
	 *@return id 
	 */
    public java.lang.Integer addCoursesort(Coursesort coursesort) {
    	return simpleDao.createEntity("Coursesort_NS.createCoursesort",coursesort);
    }
    
    /**
     * 删除课程通过课程ID
     * @param coursesortId 课程ID
     */
    public void delCoursesortById(int coursesortId){
        simpleDao.deleteEntity("Coursesort_NS.deleteCoursesortById",coursesortId);
    }
    
    /**
     * 更新课程分类
     * @param coursesort 要更新的课程分类
     */
    public void updateCoursesort(Coursesort coursesort) {
        simpleDao.updateEntity("Coursesort_NS.updateCoursesort",coursesort);
    }
    
    /**
     * 通过课程分类ID获取课程分类
     * @param coursesortId 课程分类ID
     * @return 课程分类
     */
    public Coursesort getCoursesortById(int coursesortId) {
        return simpleDao.getEntity("Coursesort_NS.getCoursesortById",coursesortId);
    }
    
    /**
     *根据查询条件查询课程集合
     *@param queryCoursesortCondition
     *@return 结果集合 
     */
    public List<Coursesort> getCoursesortList(QueryCoursesortCondition queryCoursesortCondition) {
        return simpleDao.getForList("Coursesort_NS.getCoursesortList",queryCoursesortCondition);
    }

    /**
     * 获取子课程集合	--  考试后台用到
     * @return 结果集合׷
     */
	public List<Coursesort> getChildCoursesortList(QueryCoursesortCondition queryCoursesortCondition) {
		
		List result = new ArrayList();
		List result1 = new ArrayList();
		Coursesort sortTemp = new Coursesort();
		
		sortTemp.setCoursesortId(-2);
		sortTemp.setCoursesortName("请选择");
		result.add(sortTemp);
		
		result1 = simpleDao.getForList("Coursesort_NS.getCoursesortListByPid", queryCoursesortCondition);
		result.addAll(result1);
		return result;
	}
	
	/**
	 * 
	 * 批量删除课程分类
	 * @param srtIds 课程分类ID集合
	 * 
	 */
	
	public void deleteCourseSorts(String sortIds) {
		sortIds = sortIds.replaceAll(" ", "");
		String[] groupIdsArray = sortIds.split(",");
		for(int i = 0 ; i < groupIdsArray.length;i++){
			Coursesort coursesort = simpleDao.getEntity("Coursesort_NS.getCoursesortById",new Integer(groupIdsArray[i]));
			coursesort.setStatus(Coursesort.COURSESORT_DELETE_STATUS);
			this.updateCoursesort(coursesort);
		}
	}
	
	/**
	 * 
	 * 获取课程分类树
	 * @param queryCoursesortCondition 查询条件
	 * 
	 */
	public List<Coursesort> getCoursesortTreeList(QueryCoursesortCondition queryCoursesortCondition) {
        return simpleDao.getForList("Coursesort_NS.getCourseSortListForTechTree",queryCoursesortCondition);
    }
	
	
	/**
	 * 
	 * 获取所有有效课程分类集合
	 * @param queryCoursesortCondition 查询条件
	 * @return 课程集合
	 * 
	 */
	public PageResult getNomalCoursesortList(
			QueryCoursesortCondition queryCoursesortCondition) {
		return simpleDao.getPageResult("Coursesort_NS.getCourseSortList", "Coursesort_NS.getCoursesortListCount", queryCoursesortCondition);
	}
	
	/**
	 * 课程列表
	 */
	public List<CourseSortListDTO> getCourseSortListDTOList() {
		List<CourseSortListDTO> result = new ArrayList<CourseSortListDTO>();
		List subjectList = simpleDao.getForList("Subject_NS.getSubjectList", new QuerySubjectCondition());
		
		Subject subjecttemp = null;
		CourseSortListDTO dtotemp = null;
		List courseList = null;
		
		QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
		
		for(int i = 0 ; i < subjectList.size(); i ++){
			dtotemp = new CourseSortListDTO();
			
			subjecttemp = (Subject)subjectList.get(i);
			dtotemp.setSubject(subjecttemp);
			
			queryCourseCondition.setSubjectId(subjecttemp.getSubjectId());
			queryCourseCondition.setNum(6);
			courseList = simpleDao.getForList("Course_NS.getCourseListtBySortIdLimit", queryCourseCondition);
			
			dtotemp.setCourseList(courseList);
			
			result.add(dtotemp);
		}
		return result;
	}
	
	/**
	 * 获得三级分类（即主页显示分类）
	 * @param queryCoursesortCondition
	 * @return
	 */
	public List<Coursesort> getLastCourseSortList(QueryCoursesortCondition queryCoursesortCondition){
		return simpleDao.getForList("Coursesort_NS.getLastCourseSortList", queryCoursesortCondition);
	}
	
	/**
	 * 为Tag查询课程分类集合
	 * @param queryCoursesortCondition
	 * @return
	 */
	public List getCoursesortListForTag(QueryCoursesortCondition queryCoursesortCondition){
		return simpleDao.getForList("Coursesort_NS.getCoursesortListForTag", queryCoursesortCondition);
	}
}
