package com.shangde.edu.cms.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryTmpHistory;
import com.shangde.edu.cms.domain.TemplateHistory;

public interface ITmpHistory {

	/**
	 * 添加模板记录
	 * @param templateHistory
	 * @return
	 */
	public Integer add(TemplateHistory templateHistory);
	
	/**
	 * 获取所有模板记录
	 * @param queryTmpHistory
	 * @return
	 */
	public PageResult getAll(QueryTmpHistory queryTmpHistory);
	
	/**
	 * 根据模板ID获取模板记录
	 * @param queryTmpHistory
	 * @return
	 */
	public PageResult getByTmpId(QueryTmpHistory queryTmpHistory);
	
	/**
	 * 根据模板记录ID获取模板记录
	 * @param id
	 * @return
	 */
	public TemplateHistory getById(int id);
	
	/**
	 * 搜索模板记录
	 * @param queryTmpHistory
	 * @return
	 */
	public PageResult search(QueryTmpHistory queryTmpHistory);
	
	/**
	 * 可批量删除模板记录
	 * @param tmpIdArray
	 * @return
	 */
	public int delete(String ids);
	
	/**
	 * 根据tmpId获取最新的历史记录
	 * @param tmpId
	 * @return
	 */
	public List<TemplateHistory> getBysds(String ids);
	
	/**
	 * 根据tmpId获取模板记录跳转到修改页
	 * @param queryTmpHistory
	 * @return
	 */
	public PageResult getByTmpIdToUpdate(QueryTmpHistory queryTmpHistory);
}
