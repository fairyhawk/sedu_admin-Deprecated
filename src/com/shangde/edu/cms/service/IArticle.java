package com.shangde.edu.cms.service;

import java.io.File;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.domain.Article;
import com.shangde.edu.cms.condition.QueryArticleCondition;

/**
 * Article管理接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface IArticle {
    /**
     * 添加Article
     * @param article 要添加的Article
     * @return id
     */
	 public java.lang.Integer addArticle(Article article,String articleCatalog,File file,String fileName);

    /**
     * 根据id删除一个Article
     * @param articleId 要删除的id
     */
    public void delArticleById(int articleId);

    /**
     * 修改Article
     * @param article 要修改的Article
     */
    public void updateArticle(Article article);

    /**
     * 根据id获取单个Article对象
     * @param articleId 要查询的id
     * @return 年级
     */
    public Article getArticleById(int articleId);

    /**
     * 根据条件获取Article列表
     * @param queryArticleCondition 查询条件
     * @return 查询结果
     */
    public List<Article> getArticleList(QueryArticleCondition queryArticleCondition);
    /**
     * 根据栏目查询文章列表
     * @param queryArticleCondition 查询条件
     * @return 文章列表
     */
    public List<Article> getArticleListByColumn(int columnId);
    
    /**
     * 根据条件进行分页查询
     * @param queryArticleCondition
     * @return 分页结果集
     */
    public PageResult getArticleListByQuery(QueryArticleCondition queryArticleCondition);
    
    /**
     * 根据多个id删除
     * @param ids
     * @return
     */
    public void delArticleByIds(String ids);
    /**
     * 修改文章状态
     * @param article
     */
    public void updateArticleType(Article article);

    /**
     * 为静态化准备查询方法
     * @param queryArticleCondition
     * @return
     */
	public List getArticleListForTag(QueryArticleCondition queryArticleCondition);
	/**
	 * 文章状态判断
	 */
	public Long getType(String[] type) ;

	/**
	 * 查询单个的文章
	 * @param queryArticleCondition
	 * @return
	 * Liming
	 */
	public List getArticleListForTagSingle(
			QueryArticleCondition queryArticleCondition);
}