package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.TcCouRecord;
import com.shangde.edu.cou.domain.TcSub;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.TeacherCookieDTO;
import com.shangde.edu.cusmgr.dto.CourseDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * Teacher教师服务接口
 * User: guoqiang.liu
 * Date: 2010-08-05
 */
@SuppressWarnings("unchecked")
public class TeacherImpl extends BaseService implements ITeacher{
    public java.lang.Integer addTeacher(Teacher teacher) {
    	return simpleDao.createEntity("Teacher_NS.createTeacher",teacher);
    }

    public void delTeacherById(int tcId){
    	//删除教师与课程关系，暂时不在教师一方维护 ，交由课程维护
       // simpleDao.deleteEntity("TcCouRecord_NS.deleteTcCouRecordByTcId",tcId);
        simpleDao.deleteEntity("Teacher_NS.deleteTeacherById",tcId);
    }

    public void updateTeacher(Teacher teacher) {
        simpleDao.updateEntity("Teacher_NS.updateTeacher",teacher);
    }

    public Teacher getTeacherById(int tcId) {
        return simpleDao.getEntity("Teacher_NS.getTeacherById",tcId);
    }

    public List<Teacher> getTeacherList(QueryTeacherCondition queryTeacherCondition) {
        return simpleDao.getForList("Teacher_NS.getTeacherList",queryTeacherCondition);
    }

	public PageResult getTeacherListByCondition(
			PageQuery queryTeacherCondition) {
		return simpleDao.getPageResult("Teacher_NS.getTeacherListByCondition", 
				   "Teacher_NS.getTeacherCountByCondition", 
				   queryTeacherCondition);
	}

	public PageResult getNomalCourseListBySortId(
			QueryTeacherCondition queryTeacherCondition) {
		return simpleDao.getPageResult("Teacher_NS.getNomalCourseListBySortId",
				"Teacher_NS.getNomalCourseCountBySortId",
				queryTeacherCondition);
	}

	public void addchooseCourse(int tcId, int[] ids) {
		if(ids == null) {
			return;
		}
		for(int i=0; i<ids.length; i++) {
			TcCouRecord tcCouRecord = new TcCouRecord();
			tcCouRecord.setTcId(tcId);
			tcCouRecord.setCourseId(ids[i]);
			simpleDao.createEntity("TcCouRecord_NS.createTcCouRecord",tcCouRecord);
		}
	}

	public void delTcCouRecord(QueryTcCouRecordCondition queryTcCouRecordCondition) {
		simpleDao.deleteEntity("TcCouRecord_NS.deleteTcCouRecordByTcCou", queryTcCouRecordCondition);
	}

	public List<CourseDTO> getCourseListByTcId(int tcId) {
		return simpleDao.getForList("Teacher_NS.getCourseListByTcId", tcId);
	}

	public List<Teacher> getStarTeacherListBySubject(Integer subjectId) {
		return simpleDao.getForList("Teacher_NS.getStarTeacherListBySubject", subjectId);
	}

	
	public String getTcShowVedioURL(Integer tcId) {
		return simpleDao.getEntity("Teacher_NS.getTcShowVedioURL",tcId);
	}
	
	/**
	 * 根据教师ID获取教师TeacherCookieDTO
	 * @param tcId 教师id
	 * @return
	 */
	public TeacherCookieDTO getTeacherCookieDTOById(int tcId){
		return simpleDao.getEntity("Teacher_NS.getTeacherCookieDTOById",tcId);
	}
	
	
	/**
	 * 获取教师列表
	 */
	 
	public List<Teacher> getTeacherListForTag(
			QueryTeacherCondition queryTeacherCondition) {
		return simpleDao.getForList("Teacher_NS.getTeacherListForTag",queryTeacherCondition);
	}
	
	
	/**
	 * 根据项目id查询下边老师
	 * @param subjectId
	 * @return
	 */
	public List<Teacher> getTeacherBySubjectId(int subjectId){
		return simpleDao.getForList("Teacher_NS.getTeacherListBySubjectId", subjectId);
	}
	/**
	 * 获取项目分类
	 * @param status
	 * @return
	 */
	public List<Subject> getSubjectListByStatus(int status) {							
		return simpleDao
				.getForList("Subject_NS.getSubjectListByStatus", status);
	}
	
	/**
	 * 添加老师对应类别
	 * @param tcSub
	 */
	public void addCouTcSub(TcSub tcSub){
		 simpleDao.createEntity("TcSub_NS.createTcSub", tcSub);
	}
	
	/**
	 * 根据老师id获取记录
	 * @return
	 */
	public TcSub getTcSubBytcId(int teacherId){
		return simpleDao.getEntity("TcSub_NS.getTcSubBytcId", teacherId);
	}
	
	/**
	 * 更新老师分类列表
	 * @param tcSub
	 */
	public void updateTcSub(TcSub tcSub){
		 simpleDao.updateEntity("TcSub_NS.updateTcSub", tcSub);
	}
}
