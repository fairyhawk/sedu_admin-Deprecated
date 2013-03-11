package com.shangde.edu.cms.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.GenericHTMLUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryArticleCondition;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.domain.Article;
import com.shangde.edu.cms.domain.ArticleDTO;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cms.service.IArticle;
import com.shangde.edu.cms.service.IColumns;
import com.shangde.edu.cms.service.ITemplate;
import com.shangde.edu.sys.domain.User;

public class ArticleAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ArticleAction.class);
	/**
	 * 序列号 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户
	 */
	private User user;
	
	/**
	 * 栏目服务类
	 */
	private IColumns columnsService;
	
	/**
	 * 文章服务类
	 */
	private IArticle articleService;
	
	/**
	 * 栏目list
	 */
	private List<Columns> columnList = new ArrayList<Columns>();
	
	/**
	 * 分页查询结果
	 */
	private PageResult pageResult;
	
	/**
	 * 栏目查询条件
	 */
	private QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
	
	/**
	 * 文章查询条件
	 */
	private QueryArticleCondition queryArticleCondition = new QueryArticleCondition();
	
	/**
	 * 颜色map
	 */
	private Map<String, String> colorMap = new HashMap<String, String>();
	
	/**
	 * 字体map
	 */
	private Map<String, String> fontMap = new HashMap<String, String>();
	
	/**
	 * 字体大小map
	 */
	private Map<Integer, String> fontSizeMap = new HashMap<Integer, String>();
	
	/**
	 * 文章状态map
	 */
	private Map<Integer, String> articleStateMap = new HashMap<Integer, String>();
	
	/**
	 * 临时文章
	 */
	private Article itemArticle;
	
	/**
	 * 工具文章
	 */
	private Article utilArticle;
	
	/**
	 * 展现用列表
	 */
	private List<Object[]> showList = new ArrayList<Object[]>();
	
	/**
	 * 上传文件
	 */
	private File upLoadPhoto;
	
	/**
	 * 上传文件名
	 */
	private String upLoadPhotoFileName;
	
	/**
	 * 上传文件类型
	 */
	private String upLoadPhotoContentType;
	
	/**
	 * 栏目服务类
	 */
	private Columns itemColumn;
	
	/**
	 * 文章id字符串
	 */
	private String delIds;
	
	/**
	 * 栏目名
	 */
	private String columnName;
	
	/**
	 * 文章类型
	 */
	private String articletype;

	/**
	 * 生成模板接口service
	 * Liming
	 */
	private ITemplate templateService;
	

	public ITemplate getTemplateService() {
		return templateService;
	}

	public void setTemplateService(ITemplate templateService) {
		this.templateService = templateService;
	}

	/**
	 * 包装前台select值
	 */
	public ArticleAction() {
		colorMap.put("red", "红");
		colorMap.put("bleck", "黑");

		fontMap.put("宋体", "宋体");

		for (int i = 7; i < 30; i++) {
			fontSizeMap.put(i, i + "px");
		}

		articleStateMap.put(0, "待审核");
		articleStateMap.put(1, "退稿");
		articleStateMap.put(2, "通过审核");
	}

	/**
	 * 转向添加文章页
	 * 
	 * @return String
	 */
	public String toAddArticle() {
		try {
			// 缺少权限控制
			setSession("fckSavePath","article");//fck文件上传
			columnList = columnsService.getColumnsList(queryColumnsCondition);
			//获取登录用户user
			user=getLoginedUser();
			return "toaddColumn";
		} catch (Exception e) {
			logger.error("ArticleAction.toAddArticle", e);
			return ERROR;
		}
	}

	/**
	 * 添加文章
	 * 
	 * @return
	 */
	public String addArticle() {
		try {
			servletRequest.getSession().removeAttribute("article");//移除article,保证其他模块fck上传文件好使
			Columns columns = columnsService.getColumnsById(itemArticle.getColumnId());
			String columnCatlog="";
			if(columns!=null){
				 columnCatlog = columns.getCatalog();
			}
			String serviceContextPath = getRealPath("/CATALOG/");
			
			// 创建图片存放的路径
			String articleCatalog = serviceContextPath + "/" + columnCatlog;
			
			//获取文章状态
			String type[]=null ;
			Long lo = 0l ;
			if(articletype!=null){
				type = articletype.split(",") ;
				lo = articleService.getType(type) ;
			}
			//List<Integer> types=new ArrayList<Integer>();
			//types.add(0);
			//for(int i=0;i<type.length;i++){
			//	int temp=Integer.parseInt(type[i].trim());
			//	types.add(temp);
			//}
			//itemArticle.setAticleType(StringUtil.StringToLong(types));
			itemArticle.setAticleType(lo);
			itemArticle.setPicture(upLoadPhotoFileName);
			articleService.addArticle(itemArticle, articleCatalog, upLoadPhoto, upLoadPhotoFileName);
			
			return "addArticleSuccess";
		} catch (Exception ex) {
			logger.error("ArticleAction.addArticle", ex);
			return ERROR;
		}
	}

	
	/**
	 *作者 何海强
	 * 文章阅览
	 */
	
	public String readArticle() {
		try {
			
			//servletRequest.getSession().removeAttribute("article");//移除article,保证其他模块fck上传文件好使
			//String columnCatlog = columnsService.getColumnsById(itemArticle.getColumnId()).getCatalog();
			
			itemArticle.setPicture(upLoadPhotoFileName);
			//articleService.addArticle(itemArticle, articleCatalog, upLoadPhoto, upLoadPhotoFileName);
			
			return "readArticleSuccess";
		} catch (Exception ex) {
			logger.error("ArticleAction.readArticle", ex);
			return ERROR;
		}
	}
	/**
	 * 显示文章列表
	 * 
	 * @return
	 */
	public String showArticleList() {
		try {
			// 缺少权限控制
			columnList = columnsService.getColumnsList(queryColumnsCondition);
			queryArticleCondition.setPageSize(50);
			pageResult = articleService.getArticleListByQuery(queryArticleCondition);
			setPageUrlParms();
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(50);
			}
			
			for (int i = 0; i < pageResult.getPageResult().size(); i++) {
				Article article = (Article) pageResult.getPageResult().get(i);
				String cName = columnsService.getColumnNameById(article.getColumnId());
				Object[] colunmAndArticle = new Object[2];
				colunmAndArticle[0] = cName;
				colunmAndArticle[1] = article;
				showList.add(colunmAndArticle);
			}
			setPage(pageResult);
			setPageUrlParms();
			return "showList";
		} catch (Exception ex) {
			logger.error("ArticleAction.showArticleList", ex);
			return ERROR;
		}

	}
	/**
	 * 文章生成模板
	 * Liming
	 * 2011-10-09
	 */
	
	public String generateArticleHTMl(){
		try {
			Columns columns=new Columns();
			columns.setIndexTemplateId(37);
			Template temp = templateService.getTemplateById(columns.getIndexTemplateId());//根据栏目的栏目模板ID ，获取栏目模板内容
			Map dataMap = new HashMap();
			List dataList = null;
			String staticPath = getRealPath("/static/web/");
			String columnPath = staticPath + File.separator + "column" + File.separator + queryColumnsCondition.getColumnId();
			String saveDir = staticPath + File.separator + "article";
			FileUtils.createPath(columnPath);//清空并生成目录
			dataList = articleService.getArticleListForTagSingle(getQueryArticleCondition());
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
				Set keySet = dataMap.keySet();
				Object key=null;
				for(Iterator iter = keySet.iterator(); iter.hasNext(); ) {//例子：将课程集合进行遍历
					 key = iter.next();
					FileUtils.createPath(saveDir + File.separator + key.toString());//清空并生成目录
					System.out.println();
					templateService.processTag(saveDir + File.separator + key.toString() + File.separator + "index_1.shtml", temp.getTmpContent(), dataMap.get(key));
				}
				String lujing =key.toString() +"/"+ "index_1.shtml";
				String basePath = ServletActionContext.getRequest().getScheme()+"://"+ServletActionContext.getRequest().getServerName()+":"+ServletActionContext.getRequest().getServerPort()+ServletActionContext.getRequest().getContextPath();
				System.out.println(basePath+"/static/web/article/"+lujing);
				  ServletActionContext.getResponse().sendRedirect(basePath+"/static/web/article/"+lujing);
			}
		} catch (Exception e) {
			logger.error("ArticleAction.generateArticleHTMl", e);
			return ERROR;
		}
		return null;
	}
	/**
	 * 批量生成文章模板
	 * Liming
	 * 2011-10-12
	 */
	public String generateArticleHTMlAll(){
		try {
			String [] Ids=delIds.split(",");
			 if(Ids.length==1){
				 this.generate(Integer.parseInt(Ids[0].trim()));
			 }else{
				 for(int i=0;i<Ids.length;i++){
					 int id=Integer.parseInt(Ids[i].trim());
					 this.generate(id);
				 }
			 }
		} catch (Exception e) {
			logger.error("ArticleAction.generateArticleHTMlAll", e);
			return ERROR;
		}
		return "changeSuccess";
	}
	
	public void generate(int courseId){
		try {
			getQueryArticleCondition().setArticleId(courseId);
			Columns columns=new Columns();
			columns.setIndexTemplateId(37);
			Template temp = templateService.getTemplateById(columns.getIndexTemplateId());//根据栏目的栏目模板ID ，获取栏目模板内容
			Map dataMap = new HashMap();
			List dataList = null;
			String staticPath = getRealPath("/static/web/");
			String columnPath = staticPath + File.separator + "column" + File.separator + queryColumnsCondition.getColumnId();
			String saveDir = staticPath + File.separator + "article";
			FileUtils.createPath(columnPath);//清空并生成目录
			dataList = articleService.getArticleListForTagSingle(getQueryArticleCondition());
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
				Set keySet = dataMap.keySet();
				Object key=null;
				for(Iterator iter = keySet.iterator(); iter.hasNext(); ) {//例子：将课程集合进行遍历
					 key = iter.next();
					FileUtils.createPath(saveDir + File.separator + key.toString());//清空并生成目录
					System.out.println();
					templateService.processTag(saveDir + File.separator + key.toString() + File.separator + "index_1.shtml", temp.getTmpContent(), dataMap.get(key));
				}
//				String lujing =key.toString() +"/"+ "index_1.shtml";
//				String basePath = ServletActionContext.getRequest().getScheme()+"://"+ServletActionContext.getRequest().getServerName()+":"+ServletActionContext.getRequest().getServerPort()+ServletActionContext.getRequest().getContextPath();
//				System.out.println(basePath+"/static/web/article/"+lujing);
//				  ServletActionContext.getResponse().sendRedirect(basePath+"/static/web/article/"+lujing);
			}
		} catch (Exception e) {
			logger.error("ArticleAction.generate", e);
		}
	}

	/**
	 * 转向文章修改页面
	 * 
	 * @return
	 */
	public String toEditArticle() {

		try {
			setSession("article","article");//添加article属性，用于fck上传文件路径确定
			columnList = columnsService.getColumnsList(queryColumnsCondition);
			itemArticle = articleService.getArticleById(itemArticle.getArticleId());
			String tr = itemArticle.getAticleType().toString() ;
			int[] nums = new int[tr.length()];
	        for(int i = 0; i < tr.length(); i++)
	        {
	            nums[i] = Integer.parseInt(tr.charAt(i) + "");
	        }
	        ActionContext.getContext().put("aticletype", nums);
			itemColumn = columnsService.getColumnsById(itemArticle.getColumnId());
			return "showEdit";
		} catch (Exception ex) {
			logger.error("ArticleAction.toEditArticle", ex);
			return ERROR;
		}

	}

	/**
	 * 修改文章
	 * 
	 * @return
	 */
	public String editArticle() {
		try {
			servletRequest.getSession().removeAttribute("article");
			// 是否修改图片
			// 缺少修改图片的处理
			if (upLoadPhoto == null) {
				utilArticle = this.articleService.getArticleById(itemArticle.getArticleId());
				itemArticle.setPicture(utilArticle.getPicture());
			}
			//修改文章
			String type[]=null ;
			Long lo = 0l ;
			if(articletype!=null){
				type = articletype.split(",") ;
				lo = articleService.getType(type) ;
			}
			//List<Integer> types=new ArrayList<Integer>();
			//for(int i=0;i<type.length;i++){
			//	int temp=Integer.parseInt(type[i].trim());
			//	types.add(temp);
			//}		
			//itemArticle.setAticleType(StringUtil.StringToLong(types));
			
			itemArticle.setAticleType(lo);
			articleService.updateArticle(itemArticle);
			return "changeSuccess";
		} catch (Exception ex) {
			logger.error("ArticleAction.editArticle", ex);
			return ERROR;
		}

	}

	/**
	 * 删除文章
	 * 
	 * @return
	 */
	public String delArticle() {
		try {
			articleService.delArticleByIds(delIds);
			return "changeSuccess";
		} catch (Exception ex) {
			logger.error("ArticleAction.delArticle", ex);
			return ERROR;
		}
	}
	/**
	 * 修改文章状态
	 * @return
	 */
	public String updateArticleType(){
		try{
			if(delIds!=null){
				String [] upId=delIds.split(",");
				for(int i=0;i<upId.length;i++){
					itemArticle.setArticleId(Integer.parseInt(upId[i].trim()));
					articleService.updateArticleType(itemArticle);
				}
			}
			return "changeSuccess";
		}catch(Exception ex){
			logger.error("ArticleAction.updateArticleType", ex);
			return ERROR;
		}
	}

	public String genericStaticAll() {
		try{
			genericHtmlAll();
		}catch(Exception ex){
			logger.error("ArticleAction.genericStaticAll", ex);
			return ERROR;
		}
		return "changeSuccess"; 
	}
	
	/**
	 * 执行生成html文件的入口方法
	 * @throws Exception
	 */
	private void genericHtmlAll() throws Exception {
		List<List<Article>> totalList = new ArrayList<List<Article>>();
		for(int i=2; i<8; i++) {
			genericByColumnId(i, totalList , getRealPath("/static/article/"));
		}
		FileUtils.writeFile(getRealPath("/static/article/") + "/", "column_list.shtml", GenericHTMLUtil.getColumnListHTML(totalList), true);
	}
	
	/**
	 * 根据栏目递归，生成文章
	 * @param columnId
	 * @param totalList
	 * @param path
	 * @throws Exception
	 */
	private void genericByColumnId(int columnId, List<List<Article>> totalList, String path) throws Exception {
		//准备生成目录
		String staticPath = path + "/" + GenericHTMLUtil.getDirName(columnId);
		//根据栏目id获取文章
		List<Article> articleList = articleService.getArticleListByColumn(columnId);
		StringBuffer index = new StringBuffer("");
		StringBuffer articleArray = new StringBuffer("");
		//新闻接口，显示前几个新闻
		List<Article> columnList = new ArrayList<Article>();
		//清空或新建生成目录
		FileUtils.clearFile(staticPath);
		
		//生成文章
		for(int j=0; j<articleList.size(); j++) {
			Article article = articleList.get(j);
			//将前10个新闻保存在一个文件中，需要调用新为接口时去这个文件获取数据。
			if(j < 10) {
				columnList.add(article);
			}
			addArticleArray(articleArray, article, j==articleList.size()-1);
			genericIndex(index, article, j+1);
			//文章分页
			List<String> contents = splitArticle(article.getContent());
			if(contents.size() > 0) {
				for(int i=0; i<contents.size(); i++) {
					article.setContent(contents.get(i));
					FileUtils.writeFile(staticPath + "/", article.getArticleId() + "_"+(i+1)+".shtml", GenericHTMLUtil.getContentFrame(article, i+1, contents.size()), true);
				}
			} else {
				FileUtils.writeFile(staticPath + "/", article.getArticleId() + "_1.shtml", GenericHTMLUtil.getContentFrame(article, 1, 1), true);
			}
		}
		FileUtils.writeFile(staticPath + "/", "index.txt", index.toString(), true);
		String listPath = getServletRequest().getContextPath()+"/static/article/"+GenericHTMLUtil.getDirName(columnId);
		String columnName = columnsService.getColumnNameById(columnId);
		FileUtils.writeFile(staticPath + "/", "article_list.shtml", GenericHTMLUtil.getArticleListHTML(articleArray.toString(), listPath, columnName), true);
		totalList.add(columnList);
	}

	/**
	 * 将一篇文章内容根据</p>分成数组
	 * @param content
	 * @return
	 */
	private List<String> splitArticle(String content) {
		int pageSize = 2000;
		List<String> contents = new ArrayList<String>();
		String remain = content;
		//只要有剩余内容，就继续分页
		while(remain.length()>0) {
			//如果剩余内容长度小于页面规定长度，那么就是最后一页，结束循环
			if(remain.length() <= pageSize) {
				contents.add(remain);
				break;
			} else {
				String temp = remain.substring(0, pageSize);
				//规定长度内有段落结尾</P>的，就取离规定长度近的</P>一方为本页结尾，如果没有则取下一个</P>为段落结尾
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

	/**
	 * 为文章列表页面准备数据方法
	 * @param articleArray
	 * @param article
	 */
	private void addArticleArray(StringBuffer articleArray, Article article, boolean end) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		articleArray.append("[\"");
		articleArray.append(format.format(article.getCreateDate()));
		articleArray.append("\", \"");
		articleArray.append(article.getArticleId());
		articleArray.append("_1\", \"");
		articleArray.append(article.getTitle());
		articleArray.append("\"]");
		if(!end) {
			articleArray.append(",");
		}
	}

	/**
	 * 生成索引文件，在个人中心中显示所用
	 * @param index
	 * @param article
	 * @param count
	 */
	private void genericIndex(StringBuffer index, Article article, int count) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		index.append("article");
		index.append(count);
		index.append("=");
		index.append(article.getTitle());
		index.append("=articleId=");
		index.append(article.getArticleId());
		index.append("_1=articleTime=");
		index.append(format.format(article.getCreateDate()));
		index.append("\r\n");
	}
	
	/**
	 * 预览文章
	 * @return
	 */
	public String PreArticle(){
		try{
		itemArticle = articleService.getArticleById(itemArticle.getArticleId());
		}catch(Exception ex){
			logger.error("ArticleAction.PreArticle", ex);
			return ERROR;
		}
		return "readArticleSuccess";
	}
	public Article getItemArticle() {
		return itemArticle;
	}

	public void setItemArticle(Article itemArticle) {
		this.itemArticle = itemArticle;
	}

	public IColumns getColumnsService() {
		return columnsService;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
	}

	public List<Columns> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Columns> columnList) {
		this.columnList = columnList;
	}

	public QueryArticleCondition getQueryArticleCondition() {
		
		return queryArticleCondition;
	}

	public void setQueryArticleCondition(
			QueryArticleCondition queryArticleCondition) {
		this.queryArticleCondition = queryArticleCondition;
	}

	public List<Object[]> getShowList() {
		return showList;
	}

	public void setShowList(List<Object[]> showList) {
		this.showList = showList;
	}

	public Columns getItemColumn() {
		return itemColumn;
	}

	public String getArticletype() {
		return articletype;
	}

	public void setArticletype(String articletype) {
		this.articletype = articletype;
	}

	public void setItemColumn(Columns itemColumn) {
		this.itemColumn = itemColumn;
	}

	public Map<String, String> getColorMap() {
		return colorMap;
	}

	public void setColorMap(Map<String, String> colorMap) {
		this.colorMap = colorMap;
	}

	public Map<String, String> getFontMap() {
		return fontMap;
	}

	public void setFontMap(Map<String, String> fontMap) {
		this.fontMap = fontMap;
	}

	public Map<Integer, String> getFontSizeMap() {
		return fontSizeMap;
	}

	public void setFontSizeMap(Map<Integer, String> fontSizeMap) {
		this.fontSizeMap = fontSizeMap;
	}

	public Map<Integer, String> getArticleStateMap() {
		return articleStateMap;
	}

	public void setArticleStateMap(Map<Integer, String> articleStateMap) {
		this.articleStateMap = articleStateMap;
	}

	public IArticle getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticle articleService) {
		this.articleService = articleService;
	}

	public QueryColumnsCondition getQueryColumnsCondition() {
		if (queryColumnsCondition == null) {
			queryColumnsCondition = new QueryColumnsCondition();
		}
		return queryColumnsCondition;
	}

	public void setQueryColumnsCondition(
			QueryColumnsCondition queryColumnsCondition) {
		this.queryColumnsCondition = queryColumnsCondition;
	}

	public File getUpLoadPhoto() {
		return upLoadPhoto;
	}

	public void setUpLoadPhoto(File upLoadPhoto) {
		this.upLoadPhoto = upLoadPhoto;
	}

	public String getUpLoadPhotoFileName() {
		return upLoadPhotoFileName;
	}

	public void setUpLoadPhotoFileName(String upLoadPhotoFileName) {
		this.upLoadPhotoFileName = upLoadPhotoFileName;
	}

	public String getUpLoadPhotoContentType() {
		return upLoadPhotoContentType;
	}

	public void setUpLoadPhotoContentType(String upLoadPhotoContentType) {
		this.upLoadPhotoContentType = upLoadPhotoContentType;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public String getDelIds() {
		return delIds;
	}

	public void setDelIds(String delIds) {
		this.delIds = delIds;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
