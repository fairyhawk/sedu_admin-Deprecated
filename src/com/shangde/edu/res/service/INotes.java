package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.condition.QueryNotesCondition;

/**
 * Notes管理接口
 * User: guoqiang.liu
 * Date: 2010-08-09
 */
public interface INotes {
    /**
     * 添加Notes
     * @param notes 要添加的Notes
     * @return id
     */
    public java.lang.Integer addNotes(Notes notes);

    /**
     * 根据id删除一个Notes
     * @param noteId 要删除的id
     */
    public void delNotesById(int noteId);

    /**
     * 修改Notes
     * @param notes 要修改的Notes
     */
    public void updateNotes(Notes notes);

    /**
     * 根据id获取单个Notes对象
     * @param noteId 要查询的id
     * @return 年级
     */
    public Notes getNotesById(int noteId);

    /**
     * 根据条件获取Notes列表
     * @param queryNotesCondition 查询条件
     * @return 查询结果
     */
    public List<Notes> getNotesList(QueryNotesCondition queryNotesCondition);

    /**
     * 分页查询
     * @param queryNotesCondition
     * @return
     */
	public PageResult getNotesListByCondition(PageQuery queryNotesCondition);

	/**
	 * 根据学员id和知识点id查询笔记
	 * @param queryNotesCondition 学员id和知识点id
	 * @return
	 */
	public Notes getNotesByPoint(QueryNotesCondition queryNotesCondition);
	
	/**
	 * 根据课程和用户id分页查询笔记
	 */
	public PageResult getNotesListByCourse(PageQuery queryNotesCondition);
	   /**
     * 根据id删除一个Notes
     * @param noteId 要删除的id
     */
    public void delNotesByCusId(int cusId);

	public PageResult getNotesListByPointId(
			PageQuery queryNotesCondition);
	
	/**
	 * Yangning 通过cusId批量删除用户
	 * @param cusIds
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-12-6
	 */
	public Integer delBathNotesByCusIds(String cusIds);
}