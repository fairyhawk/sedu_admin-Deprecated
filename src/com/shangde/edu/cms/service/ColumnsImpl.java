package com.shangde.edu.cms.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shangde.common.exception.CommException;
import com.shangde.common.service.BaseService;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.common.util.page.PageInfo;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryArticleCondition;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Article;
import com.shangde.edu.cms.domain.ArticleDTO;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryCoursepicCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.domain.Clazz;
import com.shangde.edu.cou.domain.ClazzCou;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.service.IClazz;
import com.shangde.edu.cou.service.IClazzCou;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursepic;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ITeacher;


/**
 * Columns对象操作实现类 User: guoqiang.liu Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class ColumnsImpl extends BaseService implements IColumns {
	private IArticle articleService;
	private ITemplate templateService;
	private ICourse courseService;
	private ITeacher teacherService;
	private ICoursesort coursesortService;
	private IClazz clazzService;
	private IClazzCou clazzCouService;
	private ICoupon couponService;
	private ICoursepic coursepicService;
	private IComment commentService;
	
	public java.lang.Integer addColumns(Columns columns, String catalog) {
		FileUtils.createPath(catalog);
		FileUtils.createPath(catalog+"/"+columns.getCatalog());
		
		String templateStr="";
		if(columns.getIndexTemplateId()!=0){
			templateStr=this.templateService.getTemplateById(columns.getIndexTemplateId()).getTmpContent();
			//读取模板内容写入静态文件
		}
		if(columns.getArticleTemplateId()!=0){
			templateStr=this.templateService.getTemplateById(columns.getArticleTemplateId()).getTmpContent();
			//读取模板内容写入静态文件
		}
		return simpleDao.createEntity("Columns_NS.createColumns", columns);
	}

	public void delColumnsById(int columnId) {
		simpleDao.deleteEntity("Columns_NS.deleteColumnsById", columnId);
	}

	public void updateColumns(Columns columns) {
		simpleDao.updateEntity("Columns_NS.updateColumns", columns);
	}

	public Columns getColumnsById(int columnId) {
		return simpleDao.getEntity("Columns_NS.getColumnsById", columnId);
	}

	public List<Columns> getColumnsList(
			QueryColumnsCondition queryColumnsCondition) {
		return simpleDao.getForList("Columns_NS.getColumnsList",
				queryColumnsCondition);
	}

	public Columns getColumnsByKeyWord(String keyWord) {
		return simpleDao.getEntity("Columns_NS.getColumnsByKeyWord", keyWord);
	}

	/**
	 * 分页查询
	 */
	public PageResult getColumnsListByParentId(
			QueryColumnsCondition queryColumnsCondition) {
		return simpleDao.getPageResult("Columns_NS.getColumnListByParentId",
				"Columns_NS.getColumnCountByParentId", queryColumnsCondition);
	}

	/**
	 * 按parentid查询
	 * 
	 * @param queryColumnsCondition
	 *            查询条件
	 * @return 结果集合
	 */
	public List<Columns> getColumnListByParentId(
			QueryColumnsCondition queryColumnsCondition) {
		return simpleDao.getForList("Columns_NS.getColumnsByParentId",
				queryColumnsCondition);
	}
	
	public List<Columns> getColumnsListForTag(
			QueryColumnsCondition queryColumnsCondition) {
		return simpleDao.getForList("Columns_NS.getColumnsListForTag",
				queryColumnsCondition);
	}

	/**
	 * 根据id删除
	 */
	public Integer[] delColumnsByIds(String ids) {
		Integer[] result = new Integer[] { 0, 0 };
		try {
			String[] conditionId = ids.split(",");
			QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
			for (int i = 0; i < conditionId.length; i++) {
				int columId = Integer.parseInt((conditionId[i].trim()));
				queryColumnsCondition.setParentId(columId);
				List<Columns> tempListC = this
						.getColumnListByParentId(queryColumnsCondition);
				List<Article> tempListA = this.articleService
						.getArticleListByColumn(columId);
				if (tempListC.size() > 0) {
					result[0] = 1;
				} else if (tempListA.size() > 0) {
					result[1] = 1;
				} else {
					simpleDao.deleteEntity("Columns_NS.deleteColumnsById",
							columId);
				}
			}
		} catch (CommException ex) {
			ex.printStackTrace();
		}

		return result;
	}
	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public String getColumnNameById(int columnId) {
		return simpleDao.getEntity("Columns_NS.getColumnNameById", columnId);
	}

	public IArticle getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticle articleService) {
		this.articleService = articleService;
	}

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}
	
	/**
	 * 递归生成栏目
	 */
	public void recursionGenericColumn(String staticPath, Columns columns)  throws Exception {
		QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
		queryColumnsCondition.setParentId(columns.getColumnId());
		List<Columns> list = getColumnListByParentId(queryColumnsCondition);
		
		if(list != null  &&  list.size() != 0) {//为空时递归结束
			for(int i=0; i<list.size(); i++) {
				Columns col = list.get(i);
				genericColumn(staticPath, col);	//静态化栏目
				if(col.getIsFinally() == 0) {
					recursionGenericColumn(staticPath, col);//静态化栏目下的子孙节点
				}
			}
		}
	}
	
	/**
	 * 功能：生成栏目
	 * @param staticPath 存储路径
	 * @param columns 栏目对象
	 */
	public void genericColumn(String staticPath, Columns columns) throws Exception {
		//生成类型为0时，直接返回
		if(columns.getStaticType() == 0) {
			return;
		} else {
			//取出模板
			Template temp = templateService.getTemplateById(columns.getIndexTemplateId());//根据栏目的栏目模板ID ，获取栏目模板内容
			if(columns.getStaticType() == 1) {
				//根据栏目生成，这种类型为生成一个页面
				String columnPath = staticPath + File.separator + "column" + File.separator + columns.getColumnId();
				FileUtils.createPath(columnPath);//清空并生成目录
				
				if(temp != null  &&  !temp.getTmpContent().trim().equals("")) {
					columns.setLinkUrl("static/web/column/" + columns.getColumnId() + "/index.shtml");
					updateColumns(columns);//更新数据库
					templateService.processTag(columnPath + File.separator + "index_1.shtml", temp.getTmpContent(), columns);
				}
			} else {
				//取出关键字，根据关键字获取相应数据
				String keyWord = columns.getKeyWord();

				if(columns.getStaticType() == 2) {
					String saveDir = staticPath + File.separator + keyWord;
					
					Map dataMap = (Map)getDataList(keyWord, columns, "map");
					Set keySet = dataMap.keySet();
					for(Iterator iter = keySet.iterator(); iter.hasNext(); ) {//例子：将课程集合进行遍历
						Object key = iter.next();
						FileUtils.createPath(saveDir + File.separator + key.toString());//清空并生成目录
						templateService.processTag(saveDir + File.separator + key.toString() + File.separator + "index_1.shtml", temp.getTmpContent(), dataMap.get(key));
					}
				} else if(columns.getStaticType() == 3) {
					//根据数据分页
					String columnPath = staticPath + File.separator + "column" + File.separator + columns.getColumnId();
					FileUtils.createPath(columnPath);//清空并生成目录
					
					List dataList = (List)getDataList(keyWord, columns, "list");
					List pageList = new ArrayList();
					int pageSize = 6;
					PageInfo pageInfo = new PageInfo();
					pageInfo.setTotalPage((dataList.size() + pageSize - 1)/6);
					for(int i=1; i<=(dataList.size() + pageSize - 1)/6; i++) {
						pageList.clear();
						for(int j=0; j<pageSize; j++) {
							pageList.add(dataList.get((i - 1) * 6 + j));
						}
						pageInfo.setPageNo(i);
						templateService.processTag(columnPath + File.separator + "index_" + i + ".shtml", temp.getTmpContent(), pageList, pageInfo);
					}
				}
			}
		}
	}
	
	
	/**
	 * 
	 * @author cxs
	 * 功能：获取数据集合（Map,List）
	 * @param args
	 * @param keyWord 关键字
	 * @param columns 栏目
	 * @param returnType 返回类型
	 * @return
	 */
	private Object getDataList(String keyWord, Columns columns, String returnType) {
		Map dataMap = new HashMap();
		List dataList = null;
		if(keyWord != null && keyWord.equals("course")) {//课程
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			queryCourseCondition.setSubjectId(columns.getSubjectId());
			dataList = courseService.getCourseListForTag(queryCourseCondition);
			for(int i=0; i<dataList.size(); i++) {
				Course c = (Course)dataList.get(i);
				dataMap.put(c.getCourseId(), c);
			}
		} else if(keyWord != null && keyWord.equals("clazz")) {//班级
			QueryClazzCondition queryClazzCondition = new QueryClazzCondition();
			queryClazzCondition.setSubjectId(columns.getSubjectId());
			
			dataList = clazzService.getClazzListForTag(queryClazzCondition);
			
			for(int i=0; i<dataList.size(); i++) {
				Clazz c = (Clazz)dataList.get(i);
				dataMap.put(c.getClazzId(), c);//
			}
		} else if(keyWord != null && keyWord.equals("coupon")) {
			QueryCouponCondition queryCouponCondition = new QueryCouponCondition();
			dataList = couponService.getCouponListForTag(queryCouponCondition);
			
			for(int i=0; i<dataList.size(); i++) {
				Coupon c = (Coupon)dataList.get(i);
				dataMap.put(c.getId(), c);//
			}
		} else if(keyWord != null && keyWord.equals("article")) {
			QueryArticleCondition queryArticleCondition = new QueryArticleCondition();
			dataList = articleService.getArticleListForTag(queryArticleCondition);
			
			for(int i=0; i<dataList.size(); i++) {
				ArticleDTO dto = new ArticleDTO();
				Article a = (Article)dataList.get(i);
				if(i != 0 && ((Article)dataList.get(i - 1)).getColumnId() == a.getColumnId()) {
					dto.setPre((Article)dataList.get(i - 1));
				}
				dto.setCurrent(a);
				if(i != dataList.size() - 1 && ((Article)dataList.get(i + 1)).getColumnId() == a.getColumnId()) {
					dto.setAfter((Article)dataList.get(i + 1));
				}
				dataMap.put(a.getArticleId(), dto);//
			}
		}
		
		if("list".equals(returnType)) {
			return dataList;
		} else {
			return dataMap;
		}
	}

	/**
	 * 将一篇文章内容根据</p>分成数组
	 * @param content
	 * @return
	 */
	private List<String> splitArticle(String content) {
		List<String> contents = new ArrayList<String>();
		if(content.equals("")) {
			contents.add("");
			return contents;
		}
		int pageSize = 2000;
		String remain = content;
		while(remain.length()>0) {
			if(remain.length() <= pageSize) {
				contents.add(remain);
				break;
			} else {
				String temp = remain.substring(0, pageSize);
				if(temp.indexOf("</p>")==-1) {
					contents.add(remain.substring(0,remain.indexOf("</p>") + 4));
					remain = remain.substring(remain.indexOf("</p>") + 4);
				} else {
					int tp = temp.lastIndexOf("</p>");
					int rp = remain.substring(pageSize).indexOf("</p>");
					if((pageSize-tp)<(rp-pageSize)  ||  (pageSize-tp)<300) {
						contents.add(remain.substring(0,temp.lastIndexOf("</p>") + 4));
						remain = remain.substring(temp.lastIndexOf("</p>") + 4);
					} else {
						contents.add(remain.substring(0,remain.substring(pageSize).indexOf("</p>") + 4 + pageSize));
						remain = remain.substring(remain.substring(pageSize).indexOf("</p>") + 4);
					}
				}
			}
		}
		return contents;
	}

// 老版文章
//	/**
//	 * 生成文章信息
//	 */
//	public void genericArticle(String staticPath, Columns columns) throws Exception {
//		List<Article> articleList = articleService.getArticleListByColumn(columns.getColumnId());
//		if(articleList == null  ||  articleList.size() == 0) {
//			return;
//		}
//		String columnPath =  staticPath + File.separator + columns.getSubjectId() + File.separator;
//		FileUtils.clearFile(columnPath);
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("totalPage", articleList.size() + "");
//		FileUtils.writeFile(columnPath + "totalPage.shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//		
//		List<Map> list = new ArrayList<Map>();
//		for(int i=0; i<articleList.size(); i++) {
//			Article article = articleList.get(i);
//			List<String> contents = splitArticle(article.getContent());
//			article.setTotalPage(contents.size());
//			for(int j=0; j<contents.size(); j++) {
//				article.setContent(contents.get(j));
//				String jsonString = JsonUtil.getJsonString4JavaPOJO(article, "yyyy-MM-dd hh-mm-ss");
//				String dirPath = columnPath + format.format(article.getCreateDate()) + File.separator;
//				
//				FileUtils.createPath(dirPath);
//				
//				String articlePath = dirPath + article.getArticleId() + "_" + (j+1) + ".shtml";
//				FileUtils.writeFile(articlePath, jsonString, true);
//			}
//
//			map = new HashMap<String, String>();
//			map.put("title", article.getTitle());
//			map.put("time", DateUtil.formatDate(article.getCreateDate()));
//			String url = "static/web/article/" + columns.getSubjectId() + "/articleContent.shtml?id=" + format.format(article.getCreateDate()) + File.separator + article.getArticleId();
//			map.put("url", url);
//			list.add(map);
//			if(list.size() == 10  ||  i == articleList.size()-1) {
//				String pagePath = columnPath + "page_" + (i/10+1) + ".shtml";
//				FileUtils.writeFile(pagePath, JsonUtil.getJsonString4JavaCollection(list), true);
//				list = new ArrayList<Map>();
//			}
//		}
//		
//		Template articleTemp = templateService.getTemplateById(columns.getArticleTemplateId());
//		
//		if(articleTemp != null  &&  !articleTemp.getTmpContent().trim().equals("")) {
//			templateService.processTag(columnPath + "articleContent.shtml", articleTemp.getTmpContent(), columns);
//		}
//	}
	
	/**
	 * 生成文章信息
	 */
	public void genericArticle(String staticPath, Columns columns) throws Exception {
		List<Article> articleList = articleService.getArticleListByColumn(columns.getColumnId());
		if(articleList == null  ||  articleList.size() == 0) {
			return;
		}
		String columnPath =  staticPath + File.separator + "subject" + File.separator + columns.getSubjectId() + File.separator;
		FileUtils.clearFile(columnPath);
		
		int pageSize = 24;
		Map<String, String> map = new HashMap<String, String>();
		map.put("totalPage", articleList.size() + "");
		FileUtils.writeFile(columnPath + "totalPage.shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		List<Map> list = new ArrayList<Map>();
		for(int i=0; i<articleList.size(); i++) {
			Article article = articleList.get(i);
			map = new HashMap<String, String>();
			map.put("title", article.getTitle());
			map.put("time", DateUtil.formatDate(article.getCreateDate()));
			map.put("url", "/static/web/article/" + article.getArticleId() + "/index_1.shtml");
			list.add(map);
			if(list.size() == pageSize  ||  i == articleList.size()-1) {
				String pagePath = columnPath + "page_" + (i/pageSize+1) + ".shtml";
				FileUtils.writeFile(pagePath, JsonUtil.getJsonString4JavaCollection(list), true);
				list = new ArrayList<Map>();
			}
		}
	}

	/**
	 * 根据栏目递归生成文章信息
	 */
	public void recursionGenericArticle(String staticPath, Columns columns)  throws Exception{
		QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
		queryColumnsCondition.setParentId(columns.getColumnId());
		List<Columns> list = getColumnListByParentId(queryColumnsCondition);
		if(list != null  &&  list.size() != 0) {
			for(int i=0; i<list.size(); i++) {
				Columns col = list.get(i);
				if(col.getIsFinally() == 1) {
					genericArticle(staticPath, col);
				} else {
					recursionGenericArticle(staticPath, col);
				}
			}
		}
	}
	
	/**
	 * 生成课程静态文件
	 */
	public void genericCourse(String staticPath) throws Exception {
		FileUtils.clearFile(staticPath);
		List<Course> list = courseService.getAllCourseInfoList(new QueryCourseCondition());
		List<Integer> pageList = new ArrayList<Integer>();
		int pageSize = 8;
		List clazzList;
		
		int clazzId = 1;
		Course course;
		QueryCoursepicCondition queryCoursepicCondition;
		for(int i=0; i<list.size(); i++) {
			course = list.get(i);
			course.setAuditionVedio(courseService.getAuditionCourseListByCourseId(course.getCourseId()));
			
			queryCoursepicCondition = new QueryCoursepicCondition();
			queryCoursepicCondition.setCourseId(course.getCourseId());
			queryCoursepicCondition.setHomeFlag(1);
			
			course.setCourseMainPicList(coursepicService.getMyCoursepicList(queryCoursepicCondition));
			clazzList = clazzCouService.getClazzCouByCourseId(course.getCourseId());
			
			for(int j = 0; j < clazzList.size(); j ++){
				course.setClazzIds(new ArrayList());
				clazzId = ((ClazzCou)clazzList.get(j)).getClazzId();
				course.getClazzIds().add(clazzId);
			}
			
			course.setExampapers(null);
			course.getSubject().setExampapers(null);
			String jsonString = JsonUtil.getJsonString4JavaPOJO(course);
			FileUtils.writeFile(staticPath + File.separator + course.getCourseId() + ".shtml", jsonString, true);
			
			pageList.add(course.getCourseId());
			if(pageList.size() == pageSize  ||  i == list.size()-1) {
				FileUtils.writeFile(staticPath + File.separator + "page_" + (i/pageSize+1) + ".shtml", JsonUtil.getJsonString4JavaCollection(pageList), true);
				pageList = new ArrayList<Integer>();
			}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("totalPage", (list.size() + pageSize - 1) / pageSize + "");
		map.put("totalCount", list.size() + "");
		FileUtils.writeFile(staticPath + File.separator + "totalPage.shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
	}

	/**
	 * 生成教师信息
	 */
	public void genericTeacher(String staticPath) throws IOException {
		FileUtils.clearFile(staticPath);
		List<Teacher> list = teacherService.getTeacherList(new QueryTeacherCondition());
		for(int i=0; i<list.size(); i++) {
			Teacher teacher = list.get(i);
			String jsonString = JsonUtil.getJsonString4JavaPOJO(teacher);
			FileUtils.writeFile(staticPath + File.separator + teacher.getTcId() + ".shtml", jsonString, true);
		}
	}
	/**
	 * 产生优惠券静态化
	 */
	public void genericCoupon(String staticPath) throws IOException {
		FileUtils.clearFile(staticPath);
		List<Coupon> list = this.couponService.getCouponList(new QueryCouponCondition());
		List<Integer> pageList = new ArrayList<Integer>();
		int pageSize = 6;
		for(int i=0; i<list.size(); i++) {
			Coupon coupon = list.get(i);
			String jsonString = JsonUtil.getJsonString4JavaPOJO(coupon);
			FileUtils.writeFile(staticPath + File.separator + coupon.getId() + ".shtml", jsonString, true);
			pageList.add(coupon.getId());
			if(pageList.size() == pageSize  ||  i == list.size()-1) {
				FileUtils.writeFile(staticPath + File.separator + "page_" + (i/pageSize+1) + ".shtml", JsonUtil.getJsonString4JavaCollection(pageList), true);
				pageList = new ArrayList<Integer>();
			}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("totalPage", (list.size() + pageSize - 1) / pageSize + "");
		FileUtils.writeFile(staticPath + File.separator + "totalPage.shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
	}
	
	
	/**
	 * 产生课程分类静态化
	 */
	public void generateCourseSort(String staticPath) throws Exception{
		FileUtils.clearFile(staticPath);
		List<Coursesort> list = coursesortService.getLastCourseSortList(new QueryCoursesortCondition());
		
		for(int i=0; i<list.size(); i++) {
			Coursesort coursesort = list.get(i);
			
			String jsonString = JsonUtil.getJsonString4JavaPOJO(coursesort);
			FileUtils.writeFile(staticPath + File.separator + coursesort.getCoursesortId() + ".shtml", jsonString, true);
		}
	}
	
	/**
	 * 产生班级静态化
	 */
	public void generateClazz(String staticPath) throws Exception {
		FileUtils.clearFile(staticPath);
		QueryClazzCondition queryClazzCondition = new QueryClazzCondition();
		
		List<Clazz> list = clazzService.getClazzList(queryClazzCondition);
		
		for(int i=0; i<list.size(); i++) {
			Clazz clazz = list.get(i);
			
			String jsonString = JsonUtil.getJsonString4JavaPOJO(clazz);
			FileUtils.writeFile(staticPath + File.separator + clazz.getClazzId() + ".shtml", jsonString, true);
		}
		
	}

	/**
	 * 生成反馈信息
	 * @throws IOException 
	 */
	public void genericAnswer(String staticPath) throws IOException {
		FileUtils.clearFile(staticPath);
		
		QueryCommentCondition queryCommentCondition = new QueryCommentCondition();
		queryCommentCondition.setPageSize(100);
		queryCommentCondition.setCurrentPage(1);
		
		List<Comment> adviceList = commentService.getAdviceList(queryCommentCondition).getPageResult();
		List<Integer> pageList = new ArrayList<Integer>();
		int pageSize = 10;
		
		for(int i=0; i<adviceList.size(); i++) {
			Comment comment = adviceList.get(i);
			Comment reply = commentService.getReplyById(comment.getCmtId());
			comment.setSourceObject(reply);
			
			String jsonString = JsonUtil.getJsonString4JavaPOJO(comment);
			FileUtils.writeFile(staticPath + File.separator + comment.getCmtId() + ".shtml", jsonString, true);
			pageList.add(comment.getCmtId());
			if(pageList.size() == pageSize  ||  i == adviceList.size()-1) {
				FileUtils.writeFile(staticPath + File.separator + "page_" + (i/pageSize+1) + ".shtml", JsonUtil.getJsonString4JavaCollection(pageList), true);
				pageList = new ArrayList<Integer>();
			}
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("totalPage", (adviceList.size() + pageSize - 1) / pageSize + "");
		FileUtils.writeFile(staticPath + File.separator + "totalPage.shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
	}

	/**
	 * 生成评论信息
	 */
	public void genericComment(String staticPath) throws IOException {
		FileUtils.clearFile(staticPath);
		
		QueryCommentCondition queryCommentCondition = new QueryCommentCondition();
		queryCommentCondition.setPageSize(100000);
		queryCommentCondition.setCurrentPage(1);
		
		List<Comment> commentList = commentService.getCommentListByCondition(queryCommentCondition).getPageResult();
		Map<String, List<Integer>> pageInfoMap = new HashMap<String, List<Integer>>();
		
		for(int i=0; i<commentList.size(); i++) {
			Comment comment = commentList.get(i);
			String key = "_" + comment.getCfId() + "_" + comment.getSourceId();
			List list = pageInfoMap.get(key);
			if(list == null) {
				list = new ArrayList<Integer>();
				pageInfoMap.put(key, list);
			}
			list.add(comment.getCmtId());
			
			String jsonString = JsonUtil.getJsonString4JavaPOJO(comment);
			FileUtils.writeFile(staticPath + File.separator + comment.getCmtId() + ".shtml", jsonString, true);
		}
		processCommentPageInfo(pageInfoMap, staticPath);
	}

	/**
	 * 处理评论分页信息
	 * @param pageInfoMap
	 * @throws IOException 
	 */
	private void processCommentPageInfo(Map<String, List<Integer>> pageInfoMap, String staticPath) throws IOException {
		Set<String> keySet = pageInfoMap.keySet();
		int pageSize = 7;
		
		for(Iterator<String> iter = keySet.iterator(); iter.hasNext(); ) {
			String key = iter.next();
			List<Integer> list = pageInfoMap.get(key);
			List<Integer> pageList = new ArrayList<Integer>();
			
			for(int i=0; i<list.size(); i++) {
				pageList.add(list.get(i));
				if(pageList.size() == pageSize  ||  i == list.size()-1) {
					FileUtils.writeFile(staticPath + File.separator + "page_" + (i/pageSize+1) + key + ".shtml", JsonUtil.getJsonString4JavaCollection(pageList), true);
					pageList = new ArrayList<Integer>();
				}
			}
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("totalPage", (list.size() + pageSize - 1) / pageSize + "");
			FileUtils.writeFile(staticPath + File.separator + "totalPage_" + key + ".shtml", JsonUtil.getJsonString4JavaPOJO(map), true);
		}
	}
	//谢添加开始
	
	/**
	 * 功能：预栏目  谢添加
	 * @param staticPath 存储路径
	 * @param columns 栏目对象
	 */
	public String PreGenericColumn(String staticPath, Columns columns) throws Exception {
		String returnPath="";
		//生成类型为0时，直接返回
		if(columns.getStaticType() == 0) {
			return returnPath;
		} else {
			//取出模板
			Template temp = templateService.getTemplateById(columns.getIndexTemplateId());//根据栏目的栏目模板ID ，获取栏目模板内容
			if(columns.getStaticType() == 1) {
				//根据栏目生成，这种类型为生成一个页面
				String columnPath = staticPath + File.separator + "columnPre" + File.separator + columns.getColumnId();
				FileUtils.createPath(columnPath);//清空并生成目录
				
				if(temp != null  &&  !temp.getTmpContent().trim().equals("")) {
					columns.setLinkUrl("static/web/columnPre/" + columns.getColumnId() + "/indexPre.shtml");
					updateColumns(columns);//更新数据库
					templateService.processTag(columnPath + File.separator + "indexPre_1.shtml", temp.getTmpContent(), columns);
					returnPath=File.separator + "columnPre" + File.separator + columns.getColumnId() + File.separator + "indexPre_1.shtml";
				}
			} else {
				//取出关键字，根据关键字获取相应数据
				String keyWord = columns.getKeyWord();

				if(columns.getStaticType() == 2) {
					String saveDir = staticPath +File.separator + "ArticlePre" + File.separator + keyWord;
					
					Map dataMap = (Map)getDataList(keyWord, columns, "map");
					Set keySet = dataMap.keySet();
					for(Iterator iter = keySet.iterator(); iter.hasNext(); ) {//例子：将课程集合进行遍历
						Object key = iter.next();
						FileUtils.createPath(saveDir + File.separator + key.toString());//清空并生成目录
						templateService.processTag(saveDir + File.separator + key.toString() + File.separator + "indexPre_1.shtml", temp.getTmpContent(), dataMap.get(key));
						returnPath=File.separator + "ArticlePre" + File.separator + keyWord + File.separator + key.toString()+ "/indexPre_1.shtml";
						break;
					}
				} else if(columns.getStaticType() == 3) {
					//根据数据分页
					String columnPath = staticPath + File.separator + "columnPre" + File.separator + columns.getColumnId();
					FileUtils.createPath(columnPath);//清空并生成目录
					
					List dataList = (List)getDataList(keyWord, columns, "list");
					List pageList = new ArrayList();
					int pageSize = 6;
					PageInfo pageInfo = new PageInfo();
					pageInfo.setTotalPage((dataList.size() + pageSize - 1)/6);
					for(int i=1; i<=(dataList.size() + pageSize - 1)/6; i++) {
						pageList.clear();
						for(int j=0; j<pageSize; j++) {
							pageList.add(dataList.get((i - 1) * 6 + j));
							break;
						}
						pageInfo.setPageNo(i);
						templateService.processTag(columnPath + File.separator + "indexPre_" + i + ".shtml", temp.getTmpContent(), pageList, pageInfo);
						returnPath= File.separator + "columnPre" + File.separator + columns.getColumnId() + File.separator + "indexPre_" + i + ".shtml";
						break;
					}
				}
			}
		return returnPath;
		}
	}
	
/**
 * 递归生成 预览 
 * @param staticPath
 * @param columns
 * @throws Exception
 */
	public String PrerecursionGenericColumn(String staticPath, Columns columns)  throws Exception {
		String returnPath="";
		QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
		queryColumnsCondition.setParentId(columns.getColumnId());
		List<Columns> list = getColumnListByParentId(queryColumnsCondition);
		if(list != null  &&  list.size() != 0) {//为空时递归结束
			for(int i=0;i<list.size();i++)
			{
			Columns col = list.get(i);
			returnPath=PreGenericColumn(staticPath, col);	//静态化栏目
			if(returnPath.equals(""))
			{
				returnPath=PrerecursionGenericColumn( staticPath,  col);
			}
			else{
				break;
			}
			}
		}
		
		return returnPath;
	}
	
	//谢添加结束
	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public IClazz getClazzService() {
		return clazzService;
	}

	public void setClazzService(IClazz clazzService) {
		this.clazzService = clazzService;
	}

	public IClazzCou getClazzCouService() {
		return clazzCouService;
	}

	public void setClazzCouService(IClazzCou clazzCouService) {
		this.clazzCouService = clazzCouService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}

	public ICoupon getCouponService() {
		return couponService;
	}

	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}

	public ICoursepic getCoursepicService() {
		return coursepicService;
	}

	public void setCoursepicService(ICoursepic coursepicService) {
		this.coursepicService = coursepicService;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}
}
