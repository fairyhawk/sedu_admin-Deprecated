package com.shangde.edu.feed.service;

import java.util.List;


import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryCourseCondition;
import com.shangde.edu.feed.condition.QueryTemplateCondition;
import com.shangde.edu.feed.domain.Template;

/**
 * Template管理接口
 * 
 * @author Libg
 * 
 */
public interface ITemplate {
	/**
	 * 添加Template
	 * 
	 *
     * @param template
     *            要添加的Template
     * @return id
	 */
	public Integer addTemplate(Template template);

	/**
	 * 根据id删除一个Template
	 * 
	 *
     * @param id
     * @return int
	 */
	public Integer delTemplateById(int id);

	/**
	 * 修改Template
	 * 
	 *
     * @param template
     *            要修改的对象
     * @return int
	 */
	public Integer updateTemplate(Template template);

	/**
	 * 根据id获取单个Template对象
	 * 
	 * @param id
	 * @return Template
	 */
	public Template getTemplateById(int id);

	/**
	 * 根据条件获取Template列表
	 * 
	 * @return 查询结果
	 */
	public List<Template> getTemplateList();
	
	
	/**
	 * 后台列表查询
	 * 
	 * @param queryCourseCondition
	 * @return
	 */
	public PageResult getTemplatePageList(QueryTemplateCondition queryTemplateCondition);
}