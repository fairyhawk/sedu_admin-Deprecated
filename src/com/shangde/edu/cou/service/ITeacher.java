package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cou.condition.QueryTcCouRecordCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.TcSub;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.TeacherCookieDTO;
import com.shangde.edu.cusmgr.dto.CourseDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * Teacher管理接口
 * User: guoqiang.liu
 * Date: 2010-08-05
 */
public interface ITeacher {
    /**
     * 添加Teacher
     * @param teacher 要添加的Teacher
     * @return id
     */
    public java.lang.Integer addTeacher(Teacher teacher);

    /**
     * 根据id删除一个Teacher
     * @param tcId 要删除的id
     */
    public void delTeacherById(int tcId);

    /**
     * 修改Teacher
     * @param teacher 要修改的Teacher
     */
    public void updateTeacher(Teacher teacher);

    /**
     * 根据id获取单个Teacher对象
     * @param tcId 要查询的id
     * @return 年级
     */
    public Teacher getTeacherById(int tcId);

    /**
     * 根据条件获取Teacher列表
     * @param queryTeacherCondition 查询条件
     * @return 查询结果
     */
    public List<Teacher> getTeacherList(QueryTeacherCondition queryTeacherCondition);

    /**
     * 分页查询
     * @param queryTeacherCondition 查询条件
     * @return 查询结果
     */
	public PageResult getTeacherListByCondition(
			PageQuery queryTeacherCondition);

	/**
	 * 查询待选课程列表
	 * @param QueryTeacherCondition 查询条件
	 * @return 分页结果
	 */
	public PageResult getNomalCourseListBySortId(
			QueryTeacherCondition queryTeacherCondition);

	/**
	 * 添加教师与课程关系，选课
	 * @param tcId 教师id
	 * @param ids 课程id数组
	 */
	public void addchooseCourse(int tcId, int[] ids);

	/**
	 * 删除教师与课程关系，取消选课
	 * @param queryTcCouRecordCondition 删除条件，教师id与课程id
	 */
	public void delTcCouRecord(QueryTcCouRecordCondition queryTcCouRecordCondition);

	/**
	 * 根据教师id查询所选课程
	 * @param tcId 教师id
	 * @return 
	 */
	public List<CourseDTO> getCourseListByTcId(int tcId);

	/**
	 * 根据专业id查询明星老师列表
	 * @param subjectId 专业id
	 * @return 明星教师列表
	 */
	public List<Teacher> getStarTeacherListBySubject(Integer subjectId);
	
	/**
	 * 根据教师id获取推荐视频的url
	 * @param tcId 教师id
	 * @return
	 */
	public String getTcShowVedioURL(Integer tcId);
	
	/**
	 * 根据教师ID获取教师TeacherCookieDTO
	 * @param tcId 教师id
	 * @return
	 */
	public TeacherCookieDTO getTeacherCookieDTOById(int tcId);
	
	/**
	 * 获取教师列表
	 * @param queryTeacherCondition
	 * @return
	 */
	public List<Teacher> getTeacherListForTag(QueryTeacherCondition queryTeacherCondition);
	
	/**
	 * 根据项目id查询下边老师
	 * @param subjectId
	 * @return
	 */
	public List<Teacher> getTeacherBySubjectId(int subjectId);
	
	/**
	 * 获取项目分类
	 * @param status
	 * @return
	 */
	public List<Subject> getSubjectListByStatus(int status);
	/**
	 * 添加老师对应类别
	 * @param tcSub
	 */
	public void addCouTcSub(TcSub tcSub);
	/**
	 * 根据老师id获取项目id
	 * @return
	 */
	public TcSub getTcSubBytcId(int teacherId);
	/**
	 * 更新老师分类列表
	 * @param tcSub
	 */
	public void updateTcSub(TcSub tcSub);
}