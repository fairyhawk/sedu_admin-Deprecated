package com.shangde.edu.cms.service;

import java.io.File;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.FileUtilException;
import com.shangde.common.util.FileUtils;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryArticleCondition;
import com.shangde.edu.cms.domain.Article;


/**
 * Article对象操作实现类 User: guoqiang.liu Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class ArticleImpl extends BaseService implements IArticle {
	private FileUtils FileTools = new FileUtils();
	public java.lang.Integer addArticle(Article article, String articleCatalog,
			File file, String fileName) {
		try {
			String imagePath = articleCatalog + "/article/Images/";
			String articlePath = articleCatalog + "/article";
			this.FileTools.createPath(articlePath);// 创建文章根文件夹
			if(file!=null && !fileName.equals("")){
			this.FileTools.createPath(imagePath);// 创建图片根文件夹
			this.FileTools.copyFile(file, imagePath, fileName);
			}
		} catch (FileUtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return simpleDao.createEntity("Article_NS.createArticle", article);
	}

	public void delArticleById(int articleId) {
		simpleDao.deleteEntity("Article_NS.deleteArticleById", articleId);
	}

	public void updateArticle(Article article) {
		simpleDao.updateEntity("Article_NS.updateArticle", article);
	}

	public Article getArticleById(int articleId) {
		return simpleDao.getEntity("Article_NS.getArticleById", articleId);
	}

	public List<Article> getArticleList(
			QueryArticleCondition queryArticleCondition) {
		return simpleDao.getForList("Article_NS.getArticleList",
				queryArticleCondition);
	}

	public List<Article> getArticleListByColumn(
			int columnId) {
		return simpleDao.getForList("Article_NS.getArticleListByColumnId",
				columnId);
	}

	public PageResult getArticleListByQuery(
			QueryArticleCondition queryArticleCondition) {
		return simpleDao.getPageResult("Article_NS.getArticleListByCondition",
				"Article_NS.getArticleCountByCondition",queryArticleCondition);
	}

	public void delArticleByIds(String ids) {
		 String [] Ids=ids.split(",");
		 if(Ids.length==1){
			 this.delArticleById(Integer.parseInt(Ids[0].trim()));
		 }else{
			 for(int i=0;i<Ids.length;i++){
				 int id=Integer.parseInt(Ids[i].trim());
				 this.delArticleById(id);
			 }
		 }
	}

	public void updateArticleType(Article article) {		
		simpleDao.updateEntity("Article_NS.updateArticleType", article);
	}

	public List getArticleListForTag(QueryArticleCondition queryArticleCondition) {
		return simpleDao.getForList("Article_NS.getArticleListForTag",
				queryArticleCondition);
	}

	public Long getType(String[] type) {
		StringBuffer sb = new StringBuffer() ;
		for(int i =0 ;i<type.length ;i++){
			if(type[i].trim().equals("推荐")){
				sb.append(3) ;
			}
			if(type[i].trim().equals("置顶")){
				sb.append(9) ;
			}
			if(type[i].trim().equals("热门")){
				sb.append(6) ;
			}
		}
		Long lo = Long.parseLong(sb.toString()) ;
		return lo;
	}
	/**
	 * 查询单个的文章
	 * @param queryArticleCondition
	 * @return
	 * Liming
	 */
	public List getArticleListForTagSingle(
			QueryArticleCondition queryArticleCondition) {
		return simpleDao.getForList("Article_NS.getArticleListForTagSingle",
				queryArticleCondition);
	}
}
