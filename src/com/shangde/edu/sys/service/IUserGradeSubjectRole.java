package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QueryUserGradeSubjectRoleCondition;
import com.shangde.edu.sys.domain.UserGradeSubjectRole;

/**
 * UserGradeSubjectRole管理接口
 * User: guoqiang.liu
 * Date: 2010-01-14
 */
public interface IUserGradeSubjectRole {
	/**
	 * 添加UserGradeSubjectRole
	 * @param userGradeSubjectRole 要添加的UserGradeSubjectRole
	 * @return id
	 */
	public void addUserGradeSubjectRole(
			UserGradeSubjectRole userGradeSubjectRole);

	/**
	 * 根据id删除一个UserGradeSubjectRole
	 * @param roleId 要删除的id
	 * @param userId 要删除的id
	 * @param gradeId 要删除的id
	 * @param subjectId 要删除的id
	 */
	public void delUserGradeSubjectRoleById(int roleId, int userId,
			int gradeId, int subjectId);

	/**
	 * 修改UserGradeSubjectRole
	 * @param userGradeSubjectRole 要修改的UserGradeSubjectRole
	 */
	public void updateUserGradeSubjectRole(
			UserGradeSubjectRole userGradeSubjectRole);

	/**
	 * 根据id获取单个UserGradeSubjectRole对象
	 * @param roleId 要查询的id
	 * @param userId 要查询的id
	 * @param gradeId 要查询的id
	 * @param subjectId 要查询的id
	 * @return 年级
	 */
	public UserGradeSubjectRole getUserGradeSubjectRoleById(int roleId,
			int userId, int gradeId, int subjectId);

	/**
	 * 根据条件获取UserGradeSubjectRole列表
	 * @param queryUserGradeSubjectRoleCondition 查询条件
	 * @return 查询结果
	 */
	public List<UserGradeSubjectRole> getUserGradeSubjectRoleList(
			QueryUserGradeSubjectRoleCondition queryUserGradeSubjectRoleCondition);
	/**
	 * 根据用户id查询UserGradeSubjectRole列表
	 * @param userId
	 * @return
	 */
	public List<UserGradeSubjectRole> getUGSRByUserId(int userId);
}