package com.shangde.edu.cms.service;

import java.util.List;

import com.shangde.common.util.page.PageInfo;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.condition.QueryTemplateCondition;

/**
 * Template管理接口
 * User: guoqiang.liu
 * Date: 2010-07-20
 */
public interface ITemplate {
	
	/**
	 * 正则表达式查找标签
	 */
//	public static String TAG_REGEX = "<!--Tag(.*) parm='(.*?)'.*?>([\\s\\S]*?)</Tag\\1-->";
	public static String TAG_REGEX = "<!--Tag(.*) *(parm='(.*?)'.*?)*?>([\\s\\S]*?)</Tag\\1-->";
	
	/**
	 * 正则表达式查找标签输出标记
	 */
	public static String VALUE_REGEX = "\\$([\\w\\.]*)\\b";
	
    /**
     * 添加Template
     * @param template 要添加的Template
     * @return id
     */
    public java.lang.Integer addTemplate(Template template);

    /**
     * 根据id删除一个Template
     * @param tmpId 要删除的id
     */
    public void delTemplateById(int tmpId);

    /**
     * 修改Template
     * @param template 要修改的Template
     */
    public void updateTemplate(Template template);

    /**
     * 根据id获取单个Template对象
     * @param tmpId 要查询的id
     * @return 年级
     */
    public Template getTemplateById(int tmpId);

    /**
     * 根据条件获取Template列表
     * @param queryTemplateCondition 查询条件
     * @return 查询结果
     */
    public List<Template> getTemplateList(QueryTemplateCondition queryTemplateCondition);


    /**
     * 分页查询模板
     * @param queryTemplateCondition
     * @return 查询结果
     */
	public PageResult getTemplateListByCondition(PageQuery queryTemplateCondition);

    /**
     * 处理模板标签
     * @param queryTemplateCondition
     * @return 查询结果
     */
	public void processTag(String filePath, String tempContent, Object obj) throws Exception;
	
	public void processTag(String filePath, String tempContent, Object obj, PageInfo pageInfo) throws Exception ;

	public String processTag(String tempContent, Object obj) throws Exception;
}