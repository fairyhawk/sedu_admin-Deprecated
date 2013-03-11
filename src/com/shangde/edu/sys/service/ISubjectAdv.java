package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySubjectConditionAdv;
import com.shangde.edu.sys.domain.Subject;

public interface ISubjectAdv {

	/** 查询全部 */
	public List<Subject> getSubjectList();

	/** 更新 */
	public void updateSubject(Subject subject);

	/** 删除 */
	public void deleteSubject(int subjectId);

	/** 保存 */
	public void addSubject(Subject subject);

	/** 根据 subjectId 查询 */
	public Subject getSubjectById(int subjectId);

	/** 带查询条件的分页查询 */
	public PageResult querySubjectListByCondition(QuerySubjectConditionAdv querySubjectConditionAdv);

}
