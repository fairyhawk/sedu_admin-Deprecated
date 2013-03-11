package com.shangde.edu.cms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.exception.CommException;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.IArticle;
import com.shangde.edu.cms.service.IColumns;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class ColumnAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(ColumnAction.class);
	private User user;
	private IColumns columnsService;
	private ICoursesort coursesortService;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Columns tempColumn;
	private Columns UtilColumn;
	private QueryColumnsCondition queryColumnsCondition;
	private List<Columns> columnList = new ArrayList<Columns>();
	private int id;
	private PageResult pageResult;
	private int Npid = 0;
	private int Nindex = 1;
	private String delIds;
	private List<Columns> pathList = new ArrayList<Columns>();
	private IArticle articleService;
	private ITemplate templateService;
	private List<Template> indexTemplateList=new ArrayList<Template>();
	private List<Template> articleTemplateList=new ArrayList<Template>();
	private QueryTemplateCondition queryTemplateCondition;
	private ISubject subjectService ;
	private List<Subject> subjectInfo ;

	public List<Subject> getSubjectInfo() {
		return subjectInfo;
	}
	

	public void setSubjectInfo(List<Subject> subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	/**
	 * 创建栏目
	 * 
	 * @return String
	 */
	public String createNewColumn() {
		try {
			if (tempColumn.getParentId() != 1) {
				Columns temp = columnsService.getColumnsById(tempColumn
						.getParentId());
				tempColumn.setPath(temp.getPath() + ","
						+ temp.getColumnId());
				tempColumn.setSubjectId(temp.getSubjectId());
			} else {
				tempColumn.setPath("1");
			}
			String catalog = getRealPath("/CATALOG");
			columnsService.addColumns(tempColumn, catalog);
			return "changeSuccess";
		} catch (Exception ex) {
			logger.error("ColumnAction.createNewColumn", ex);
			return ERROR;
		}

	}

	/**
	 * 转向创建栏目页
	 * 
	 * @return String
	 */
	public String toCreateColumn() {
		try {
			this.getQueryTemplateCondition().setTmpType("0");
			indexTemplateList=templateService.getTemplateList(queryTemplateCondition);
			queryTemplateCondition.setTmpType("1");
			articleTemplateList=templateService.getTemplateList(queryTemplateCondition);
			columnList = columnsService.getColumnsList(queryColumnsCondition);
			subjectInfo = subjectService.getAllSubject() ;
			//获取登录用户user
			user=getLoginedUser();
			return "toaddColumn";
		} catch (Exception e) {
			logger.error("ColumnAction.toCreateColumn", e);
			return ERROR;
		}
	}

	/**
	 * 显示栏目列表
	 * 
	 * @param columnsService
	 */
	public String showColumnList() {
		try {
			user=getLoginedUser();
			if (Npid != 0) {
				tempColumn = columnsService.getColumnsById(Npid);
			}
			queryColumnsCondition = new QueryColumnsCondition();
			queryColumnsCondition.setParentId(Npid);
			queryColumnsCondition.setCurrentPage(Nindex);
			queryColumnsCondition.setPageSize(100);
			pageResult = columnsService
					.getColumnsListByParentId(queryColumnsCondition);
			// 设置根节点
			Columns HomeColumn = new Columns();
			HomeColumn.setColumnId(0);
			HomeColumn.setColumnName("HOME");
			pathList.add(HomeColumn);
			if (tempColumn != null) {
				if (tempColumn.getPath() != null && tempColumn.getPath() != "") {
					String path[] = tempColumn.getPath().split(",");
					for (int i = 0; i < path.length; i++) {
						if(!path[i].trim().equals("")) {
							int id = Integer.parseInt(path[i]);
							UtilColumn = columnsService.getColumnsById(id);
							pathList.add(UtilColumn);
						}
					}
					pathList.add(tempColumn);
				} else {
					pathList.add(tempColumn);
				}
			}

		} catch (Exception ex) {
			logger.error("ColumnAction.showColumnList", ex);
			return ERROR;
		}
		return "showList";
	}

	/**
	 * 转向栏目修改页面
	 * 
	 * @return
	 */
	public String toEditColumn() {
		try {
			this.getQueryTemplateCondition().setTmpType("0");
			indexTemplateList=templateService.getTemplateList(queryTemplateCondition);
			queryTemplateCondition.setTmpType("1");
			articleTemplateList=templateService.getTemplateList(queryTemplateCondition);
			columnList = columnsService.getColumnsList(queryColumnsCondition);
			tempColumn = columnsService.getColumnsById(id);
			UtilColumn = columnsService.getColumnsById(this.tempColumn
					.getParentId());
			subjectInfo = subjectService.getAllSubject() ;
			return "showColumn";
		} catch (Exception ex) {
			logger.error("ColumnAction.toEditColumn", ex);
			return ERROR;
		}

	}

	/**
	 * 栏目修改
	 * 
	 * @return
	 * @throws IOException
	 */
	public String editColumn() throws IOException {
		this.response = ServletActionContext.getResponse();
		this.request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String url = request.getHeader("REFERER");
			UtilColumn = columnsService
					.getColumnsById(tempColumn.getColumnId());
			if (UtilColumn.getIsFinally() == 1
					&& tempColumn.getIsFinally() == 0) {
				List articleList = articleService.getArticleListByColumn(tempColumn.getColumnId());
				if (articleList.size() > 0) {
					tempColumn = UtilColumn;
					out
							.print("<script>alert('此栏目下有文章，不可以修改为非终极栏目');window.location.href='"
									+ url + "'</script>");
					return null;
				}
			}
			if (UtilColumn.getIsFinally() == 0
					&& tempColumn.getIsFinally() == 1) {
				QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
				queryColumnsCondition.setParentId(tempColumn.getColumnId());
				List columns = columnsService
						.getColumnListByParentId(queryColumnsCondition);
				if (columns.size() > 0) {
					tempColumn = UtilColumn;
					out
							.print("<script>alert('此栏目下有子栏目，不可以修改为终极栏目');window.location.href='"
									+ url + "'</script>");
					return null;
				}
			}
			Columns temp = columnsService.getColumnsById(tempColumn
					.getParentId());
			tempColumn.setPath(temp.getPath() + "," + temp.getColumnId());
			columnsService.updateColumns(tempColumn);
			return "changeSuccess";
		} catch (Exception ex) {
			logger.error("ColumnAction.editColumn", ex);
			return ERROR;
		}
	}

	/**
	 * 栏目删除
	 * 
	 * @param columnsService
	 * @throws IOException
	 */
	public String delColumn() throws IOException {
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 获取传过来的路径
			String url = request.getHeader("REFERER");
			if (delIds != null) {
				Integer[] result = columnsService.delColumnsByIds(delIds);
				if (result[0] == 1) {
					out.print("<script>alert('部分栏目中包含子栏目确认删除吗？');window.location.href='"
									+ url + "'</script>");
				} else if (result[1] == 1) {
					out.print("<script>alert('部分栏目中包含文章确认删除吗？');window.location.href='"
									+ url + "'</script>");
				} else {
					out.print("<script>window.location.href='" + url
							+ "'</script>");
				}
			} else {
				out.print("<script>alert('请选中要删除的栏目');window.location.href='" + url + "'</script>");
			}
			return "changeSuccess";
		} catch (CommException ex) {
			logger.error("ColumnAction.delColumn", ex);
			return ERROR;
		}
	}
	
	/**
	 * 将一个栏目及其子孙栏目下的文章静态化
	 * @return
	 * @throws IOException 
	 */
	public String genericColumn() throws Exception {
		try {
			String staticPath = getRealPath("/static/web/");
			Columns columns = columnsService.getColumnsById(getQueryColumnsCondition().getColumnId());
			if(getQueryColumnsCondition().getColumnId() != 1) {
				columnsService.genericColumn(staticPath, columns);	//静态化此栏目
			}
			if(columns.getIsFinally() == 0) {
				columnsService.recursionGenericColumn(staticPath, columns);//递归静态化栏目下的所有子孙节点，但不包括此栏目本身
			}
			return "changeSuccess";
		} catch (CommException ex) {
			logger.error("ColumnAction.genericColumn", ex);
			return ERROR;
		}
	}
	

	/**
	 * 将一个栏目及其子孙栏目下的文章静态化
	 * @return
	 * @throws IOException 
	 */
	public String genericArticle() throws Exception {
		try {
			String staticPath = getRealPath("/static/web/article");
			Columns columns = columnsService.getColumnsById(getQueryColumnsCondition().getColumnId());
			if(columns.getIsFinally() == 1) {
				columnsService.genericArticle(staticPath, columns);
			} else {
				columnsService.recursionGenericArticle(staticPath, columns);
			}
			return "changeSuccess";
		} catch (CommException ex) {
			logger.error("ColumnAction.genericArticle", ex);
			return ERROR;
		}
	}
	
	/**
	 * 将课程,课程分类，与教师静态化到json文件
	 * @return
	 * @throws IOException 
	 */
	public String genericJsonData() throws Exception {
		try {
			String staticPath = getRealPath("/static/web");
			Columns columns = columnsService.getColumnsById(getQueryColumnsCondition().getColumnId());
			
//			columnsService.genericCourse(staticPath + "/course");//栏目
//			columnsService.genericTeacher(staticPath + "/teacher");//课程
//			columnsService.generateCourseSort(staticPath + "/coursesort");//课程分类
//			columnsService.generateClazz(staticPath + "/clazz");//课程分类
//			columnsService.genericCoupon(staticPath+"/coupon");//生成优惠券种类
//			columnsService.genericAnswer(staticPath+"/comment/data/advice");//生成反馈信息
//			columnsService.genericComment(staticPath+"/comment/data/comment");//生成评论
			
			if(columns != null) {
				//文章数据生成
				//FileUtils.clearFile(staticPath + "/article");
				if(columns.getIsFinally() == 1) {
					columnsService.genericArticle(staticPath + "/article", columns);
				} else {
					columnsService.recursionGenericArticle(staticPath + "/article", columns);
				}
			}
			return "changeSuccess";
		} catch (CommException ex) {
			logger.error("ColumnAction.genericJsonData", ex);
			return ERROR;
		}
	}

	
	//谢添加开始
	/**
	 * 预览栏目
	 * @return
	 * @throws IOException 
	 */
	public void PreGenericColumn() throws Exception {
		try {
			String returnPaht="";
			String staticPath = getRealPath("/static/web/");
			Columns columns = columnsService.getColumnsById(getQueryColumnsCondition().getColumnId());
			if(getQueryColumnsCondition().getColumnId() != 1) {
				returnPaht=columnsService.PreGenericColumn(staticPath, columns);	//静态化此栏目
			}
			if(columns.getIsFinally() == 0&& returnPaht.equals("")) {
				returnPaht=columnsService.PrerecursionGenericColumn(staticPath, columns);//递归静态化栏目下的所有子孙节点，但不包括此栏目本身
			}
			ServletActionContext.getResponse().getWriter().write("/static/web"+returnPaht);
		} catch (CommException ex) {
			logger.error("ColumnAction.PreGenericColumn", ex);
		}
	}
	
	/**
	 * 预览文章
	 * @return
	 * @throws IOException 
	 */
	public void PreGenericArticle() throws Exception {
		try {
			String returnPaht="";
			String staticPath = getRealPath("/static/web/");
			Columns columns = columnsService.getColumnsById(getQueryColumnsCondition().getColumnId());
			returnPaht=columnsService.PreGenericColumn(staticPath, columns);	//静态化此栏目
			ServletActionContext.getResponse().getWriter().write("/static/web"+returnPaht);
		} catch (CommException ex) {
			logger.error("ColumnAction.PreGenericColumn", ex);
		}
	}
	
	//谢添加结束
	public QueryTemplateCondition getQueryTemplateCondition() {
		if(queryTemplateCondition==null){
			queryTemplateCondition=new QueryTemplateCondition();
		}
		return queryTemplateCondition;
	}

	public void setQueryTemplateCondition(
			QueryTemplateCondition queryTemplateCondition) {
		this.queryTemplateCondition = queryTemplateCondition;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
	}

	public Columns getTempColumn() {
		return tempColumn;
	}

	public void setTempColumn(Columns tempColumn) {
		this.tempColumn = tempColumn;
	}

	public QueryColumnsCondition getQueryColumnsCondition() {
		return queryColumnsCondition;
	}

	public void setQueryColumnsCondition(
			QueryColumnsCondition queryColumnsCondition) {
		this.queryColumnsCondition = queryColumnsCondition;
	}

	public List<Columns> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Columns> columnList) {
		this.columnList = columnList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public int getNpid() {
		return Npid;
	}

	public void setNpid(int npid) {
		Npid = npid;
	}

	public int getNindex() {
		return Nindex;
	}

	public void setNindex(int nindex) {
		Nindex = nindex;
	}

	public IColumns getColumnsService() {
		return columnsService;
	}

	public Columns getUtilColumn() {
		return UtilColumn;
	}

	public void setUtilColumn(Columns utilColumn) {
		UtilColumn = utilColumn;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public List<Columns> getPathList() {
		return pathList;
	}

	public void setPathList(List<Columns> pathList) {
		this.pathList = pathList;
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

	public List<Template> getIndexTemplateList() {
		return indexTemplateList;
	}

	public void setIndexTemplateList(List<Template> indexTemplateList) {
		this.indexTemplateList = indexTemplateList;
	}

	public List<Template> getArticleTemplateList() {
		return articleTemplateList;
	}

	public void setArticleTemplateList(List<Template> articleTemplateList) {
		this.articleTemplateList = articleTemplateList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}