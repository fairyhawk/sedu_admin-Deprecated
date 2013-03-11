package com.shangde.edu.cms.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shangde.common.service.BaseService;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.FileUtils;
import com.shangde.common.util.StringUtil;
import com.shangde.common.util.page.IPageUtil;
import com.shangde.common.util.page.PageInfo;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryArticleCondition;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.condition.QuerySurveyQstCondition;
import com.shangde.edu.cms.condition.QueryTemplateCondition;
import com.shangde.edu.cms.domain.Template;
import com.shangde.edu.cou.condition.QueryClazzCondition;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryTeacherCondition;
import com.shangde.edu.cou.service.IClazz;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ITeacher;

/**
 * Template对象操作实现类
 * User: guoqiang.liu
 * Date: 2010-07-20
 */
@SuppressWarnings("unchecked")
public class TemplateImpl extends BaseService implements ITemplate{

	private IColumns columnsService;
	private ICourse courseService;
	private ITeacher teacherService;
	private ISurveyQst surveyQstService;
	private ConfigService configurator;
	private IComment commentService;
	private IClazz clazzService;
	private ICoursesort coursesortService;
	private IArticle articleService;
	
	private IPageUtil pageUtil;
	
	public java.lang.Integer addTemplate(Template template) {
        return simpleDao.createEntity("Template_NS.createTemplate",template);
    }

    public void delTemplateById(int tmpId){
        simpleDao.deleteEntity("Template_NS.deleteTemplateById",tmpId);
    }

    public void updateTemplate(Template template) {
        simpleDao.updateEntity("Template_NS.updateTemplate",template);
    }

    public Template getTemplateById(int tmpId) {
        return simpleDao.getEntity("Template_NS.getTemplateById",tmpId);
    }

    public List<Template> getTemplateList(QueryTemplateCondition queryTemplateCondition) {
        return simpleDao.getForList("Template_NS.getTemplateList",queryTemplateCondition);
    }

	public PageResult getTemplateListByCondition(PageQuery queryTemplateCondition) {
		return simpleDao.getPageResult("Template_NS.getTemplateListByCondition", "Template_NS.getTemplateCountByCondition", queryTemplateCondition);
	}

	/**
	 * 为静态化准备数据
	 * @param queryTemplateCondition
	 * @return
	 */
	public Collection getTemplateListForTag(QueryTemplateCondition queryTemplateCondition) {
		return simpleDao.getForList("Template_NS.getTemplateListForTag",queryTemplateCondition);
	}
	
	/**
	 * 处理标签
	 * @param filePath     文件要写入的路径
	 * @param tempContent  模板内容
	 * @param obj          对象（里面存储着要替换的信息内容）
	 */
	public void processTag(String filePath, String tempContent, Object obj) throws Exception {
		processTag(filePath, tempContent, obj, null);
	}
	
	public void processTag(String filePath, String tempContent, Object obj, PageInfo pageInfo) throws Exception {
		if(tempContent == null){
			tempContent = "";
		}
		
		if(pageInfo != null) {
			tempContent = proccessPage(tempContent, pageInfo);
		}
		
		tempContent = replaceTag(tempContent, obj);
		
		FileUtils.writeFile(filePath, tempContent, true);
	}

	public String processTag(String tempContent, Object obj) throws Exception {
		if(tempContent == null){
			tempContent = "";
		}
		
		tempContent = replaceTag(tempContent, obj);
		
		return tempContent;
	}
	
	private String replaceTag(String tempContent, Object obj) throws Exception {
		String projectURL = configurator.getProjectURL();
		//String projectURL="..";
		String importURL = configurator.getImportURL();
		tempContent = recursionProcessTag(tempContent, obj);//标签处理
		tempContent = tempContent.replaceAll("@projectURL@", projectURL);//替换项目路径
		tempContent = tempContent.replaceAll("@importURL@", importURL);//替换图片、js、css路径
		return tempContent;
	}
	
	/**
	 * 处理分页信息
	 * @param tempContent
	 * @param obj
	 * @return
	 */
	private String proccessPage(String tempContent, PageInfo pageInfo) {
		if(tempContent.indexOf("<!--TagPage></TagPage-->")!= -1) {
			String pageHTML = pageUtil.pageHTML(pageInfo);
			tempContent = tempContent.replaceAll("<!--TagPage></TagPage-->", pageHTML);//替换分页信息
		}
		return tempContent;
	}

	/**
	 * 功能：循环处理标签，对模板中的所有相同标签进行处理
	 * @param args
	 * @param tempContent  模板内容
	 * @param obj          对象
	 * @return             标签替换完以后的内容
	 * @throws Exception
	 */
	private String recursionProcessTag(String tempContent, Object obj) throws Exception {
		Object ifelseObj = obj;
		String innerContent = "";//标签内的内容
		while(!"".equals((innerContent= StringUtil.returnRegexStr(tempContent, ITemplate.TAG_REGEX, 4)))){
			String tagName = StringUtil.returnRegexStr(tempContent, ITemplate.TAG_REGEX, 1);			//获取标签名称
			
			Map<String,Object> parmMap = StringUtil.getParm( StringUtil.returnRegexStr(tempContent, ITemplate.TAG_REGEX, 3));	//获取标签参数
			
			//处理参数，将obj中的对应属性将map中的object.开头的value替换掉；例子 courseId:Object.courseId 将Object.courseId 替换成4
			//然后将结果重新放入parmMap中
			if(!"date".equalsIgnoreCase(tagName)) {
				replaceParm(parmMap, obj);
			}
			
			StringBuffer sb = new StringBuffer();//用于存储标签替换完的内容 例如<--TagCourse>4</TagCourse>中的4
			
			String strIter = parmMap.get("iterator")==null?"false":parmMap.get("iterator").toString();//是否循环的标志
			parmMap.remove("iterator");
			
			//判断是哪个标签
			if("Object".equalsIgnoreCase(tagName)){//如果是column标签
				String temp = innerContent;
				
				if(obj instanceof Collection) {
					sb.append(iteratorContent((Collection)obj, temp));
				} else {
					sb.append(proccessContent(null, obj, temp, 0));
				}
			} else if("if".equalsIgnoreCase(tagName)  ||  "elseif".equalsIgnoreCase(tagName)) {
				if(ifelseObj != null && checkIf(parmMap, ifelseObj)) {
					sb.append(proccessContent(null, ifelseObj, innerContent, 0));
					ifelseObj = null;
				}
			}  else if("else".equalsIgnoreCase(tagName)) {
				if(ifelseObj != null) {
					sb.append(proccessContent(null, ifelseObj, innerContent, 0));
				}
			} else if("date".equalsIgnoreCase(tagName)) {
				String filed = parmMap.get("value").toString();
				filed = filed.startsWith("object")?filed.substring(7):filed;
				
				Object[] proDotResults = processDot(filed, obj);//
				Object filedObj = proDotResults[0];
				filed = (String)proDotResults[1];
				
				Date value = (Date)getValue(filedObj, filed);
				SimpleDateFormat format = new SimpleDateFormat(parmMap.get("format").toString());
				sb.append(innerContent.replaceAll("\\$value", format.format(value)));
			} else {
				sb.append(processObjectTag(tagName, parmMap, strIter, innerContent));
			}
			//先将$格式的标签替换，然后用得到的结果 替换<--TagClazz>形式的标签
			tempContent = tempContent.replaceFirst(ITemplate.TAG_REGEX, sb.toString().replaceAll(ITemplate.VALUE_REGEX, ""));
		}
		return tempContent;
	}
	
	/**
	 * 处理对象标签
	 * @param tagName 标签名称
	 * @param parmMap 参数Map
	 * @param strIter 
	 * @param innerContent
	 * @return
	 * @throws Exception
	 */
	public String processObjectTag (String tagName, Map<String,Object> parmMap, String strIter, String innerContent) throws Exception{
		StringBuffer sb = new StringBuffer();
		Collection collection = null;
		if("Column".equals(tagName)) {
			QueryColumnsCondition queryColumnsCondition = new QueryColumnsCondition();
			setParms(queryColumnsCondition, parmMap);
			collection = columnsService.getColumnsListForTag(queryColumnsCondition);
		} else if("Subject".equals(tagName)) {
			
		} else if("Teacher".equals(tagName)) {
			QueryTeacherCondition queryTeacherCondition =new QueryTeacherCondition();
			setParms(queryTeacherCondition,parmMap);
			collection=teacherService.getTeacherListForTag(queryTeacherCondition);
		} else if("Course".equals(tagName)) {
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			setParms(queryCourseCondition, parmMap);
			collection = courseService.getCourseListForTag(queryCourseCondition);
		} else if("Vedio".equals(tagName)) {
			
		} else if("SurveyQst".equals(tagName)) {
			QuerySurveyQstCondition querySurveyQstCondition = new QuerySurveyQstCondition();
			setParms(querySurveyQstCondition, parmMap);
			collection = surveyQstService.getSurveyQstListForTag(querySurveyQstCondition);
		} else if("Clazz".equals(tagName)) {//班级
			QueryClazzCondition queryClazzCondition = new QueryClazzCondition();
			setParms(queryClazzCondition, parmMap);
			collection = clazzService.getClazzListForTag(queryClazzCondition);
		} else if("Coursesort".equals(tagName)){//课程分类
			QueryCoursesortCondition queryCoursesortCondition = new QueryCoursesortCondition();
			setParms(queryCoursesortCondition, parmMap);
			collection = coursesortService.getCoursesortListForTag(queryCoursesortCondition);
		} else if("ClazzCourse".equals(tagName)){
			QueryCourseCondition queryCourseCondition = new QueryCourseCondition();
			setParms(queryCourseCondition, parmMap);
			collection = courseService.getCourseListByClazzIdForTag(queryCourseCondition);
		} else if("Comment".equals(tagName)){
			QueryCommentCondition queryCommentCondition = new QueryCommentCondition();
			setParms(queryCommentCondition, parmMap);
			collection = commentService.getCommentListByClazzIdForTag(queryCommentCondition);
		} else if("Template".equals(tagName)){
			QueryTemplateCondition queryTemplateCondition = new QueryTemplateCondition();
			setParms(queryTemplateCondition, parmMap);
			collection = getTemplateListForTag(queryTemplateCondition);
		} else if("Article".equals(tagName)){
			QueryArticleCondition queryArticleCondition = new QueryArticleCondition();
			setParms(queryArticleCondition, parmMap);
			collection = articleService.getArticleListForTag(queryArticleCondition);
		}
		
		if(collection == null) {
			collection = new ArrayList();
		}
		if(strIter.trim().equals("true")) {
			sb.append(iteratorContent(collection, innerContent));
		} else {
			Object tempObj = null;
			if(collection != null && collection.size() > 0) {
				tempObj = collection.iterator().next();
			}
			sb.append(proccessContent(collection, tempObj, innerContent, 0));
		}
		return sb.toString();
	}

	/**
	 * 将标签里的内容根据list循环
	 * @param list
	 * @param sb
	 * @param innerContent
	 * @throws Exception
	 */
	private String iteratorContent(Collection c, String innerContent) throws Exception {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for(Iterator it=c.iterator(); it.hasNext(); ) {
			Object obj = it.next();
			String temp = recursionProcessTag(innerContent, obj);
			sb.append(proccessContent(c, obj, temp, index));
			index ++;
		}
		return sb.toString();
	}

	/**
	 * 对标签里的内容进行处理
	 * @param c		集合
	 * @param obj   传入参数
	 * @param innerContent 标签内容
	 * @param index  obj 在 c中的索引
	 * @throws Exception
	 */
	private String proccessContent(Collection c, Object obj, String innerContent, int index) throws Exception {
		String temp = "";
		if(obj != null) {
			temp = recursionProcessTag(innerContent, obj);//递归
		}
		
		temp = processCollectionValue(temp, c);//替换$list.size 为c.size
		temp = processStatus(temp, index);// index
		
		
		if(obj != null) {
			temp = processEL(temp, obj);
		}
		return temp;
	}

	/**
	 * 替换标签内集合属性表达式$list.size
	 * @param t
	 * @param c
	 */
	private String processCollectionValue(String t, Collection c) {
		if(c == null) {
			return t;
		}
		String temp = "";
		temp = t.replaceAll("\\$list.size", c.size() + "");
		return temp;
	}

	/**
	 * 替换标签内集合属性表达式
	 * @param t
	 * @param c
	 */
	private String processStatus(String t, int index) {
		String temp = "";
		temp = t.replaceAll("\\$status.index", index + "");
		temp = temp.replaceAll("\\$status.count", (index + 1) + "");
		return temp;
	}

	/**
	 * 替换parm参数中，将value中的标签进行替换
	 * 如果参数值以object开头，那么说明是要从obj中取数据，
	 * 此方法将map中数据替换为obj中数据
	 * @param tempTagParmMap
	 * @param obj
	 * @throws Exception
	 */
	private void replaceParm(Map<String, Object> tempTagParmMap, Object obj) throws Exception {
		Set ks = tempTagParmMap.keySet();
		for(Iterator<String> iter=ks.iterator(); iter.hasNext();) {
			String key = iter.next();
			String value = tempTagParmMap.get(key).toString();
			if(value.startsWith("object.")) {
				value = value.substring(value.indexOf(".") + 1);
				
				Object[] proDotResults = processDot(value, obj);//
				value = (String)proDotResults[1];
				
				tempTagParmMap.put(key, getValue(proDotResults[0], value));//通过反射返回obj中响应属性的值
			}
		}
	}
	
	/**
	 * 处理并替换自定义表达式 ITemplate.VALUE_REGEX
	 * 对innerContent
	 * @param t
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private String processEL(String t, Object obj) throws Exception {
		String filed = "";
		while(!"".equals(filed = StringUtil.returnRegexStr(t, ITemplate.VALUE_REGEX, 1))) {
			
			Object[] proDotResults = processDot(filed, obj);
			Object filedObj = proDotResults[0];
			filed = (String)proDotResults[1];
			
			
			Object value = getValue(filedObj, filed.substring(filed.lastIndexOf(".")+1));//通过反射获取属性值
			t = t.replaceFirst(ITemplate.VALUE_REGEX, value==null?"":value.toString().replaceAll("\\$", "\\\\\\$"));
			t = recursionProcessTag(t, obj);
		}
		return t;
	}
	
	/**
	 * 向Query****Condition中设置数据
	 * @param obj
	 * @param tempTagParmMap
	 * @throws Exception
	 */
	private void setParms(Object obj,
			Map<String, Object> tempTagParmMap) throws Exception {
		Set ks = tempTagParmMap.keySet();
		for(Iterator<String> iter=ks.iterator(); iter.hasNext();) {
			String key = iter.next();
			Object value = tempTagParmMap.get(key);
			
			Object[] proDotResults = processDot(key, obj);
			key = (String)proDotResults[1];
			
			setValue(proDotResults[0], key, value);
		}
	}
	
	/**
	 * 通过反射向对象中设值
	 * @param obj
	 * @param filed
	 * @param value
	 * @throws Exception
	 */
	private void setValue(Object obj, String filed, Object value) throws Exception {
		if(obj == null || value == null) {
			return;
		}
		String getMethodName = "get" + filed.substring(0, 1).toUpperCase() + filed.substring(1);
		Method getMethod = obj.getClass().getMethod(getMethodName);
		Class cls = getMethod.getReturnType();
		value = changeType(value, cls);
		String methodName = "set" + filed.substring(0, 1).toUpperCase() + filed.substring(1);
		Method method = obj.getClass().getMethod(methodName, cls);
		method.invoke(obj, new Object[]{value});
	}
	
	/**
	 * 根据类型转换
	 * @param value
	 * @param cls
	 * @return
	 */
	private Object changeType(Object value, Class cls) {
		if(value == null) {
			return null;
		}
		if(cls.equals(byte.class)  ||  cls.equals(Byte.class)) {
			
			return Byte.valueOf(value.toString());
			
		} else if(cls.equals(short.class)  ||  cls.equals(Short.class)) {
			
			return Short.valueOf(value.toString());
			
		} else if(cls.equals(int.class)  ||  cls.equals(Integer.class)) {
			
			return Integer.valueOf(value.toString());
			
		} else if(cls.equals(long.class)  ||  cls.equals(Long.class)) {
			
			return Long.valueOf(value.toString());
			
		} else if(cls.equals(float.class)  ||  cls.equals(Float.class)) {
			
			return Float.valueOf(value.toString());
			
		} else if(cls.equals(double.class)  ||  cls.equals(Double.class)) {
			
			return Double.valueOf(value.toString());
			
		} else if(cls.equals(boolean.class)  ||  cls.equals(Boolean.class)) {
			
			return Boolean.valueOf(value.toString());
			
		} else if(cls.equals(char.class)  ||  cls.equals(Character.class)) {
			
			return value.toString().charAt(0);
		}
		return value;
	}

	/**
	 * 反射获取属性值
	 * @param obj
	 * @param filed
	 * @return
	 * @throws Exception
	 */
	private Object getValue(Object obj, String filed) throws Exception {
		if(obj == null) {
			return null;
		}
		String methodName = "get" + filed.substring(0, 1).toUpperCase() + filed.substring(1);
		Method method = obj.getClass().getMethod(methodName);
		return method.invoke(obj);
	}
	
	/**
	 * 处理带最后一个.的参数,将obj中的filed中最后一个点之前的对象取到，
	 * 比如filed为teacher.id
	 * 那么返回obj中的teacher属性
	 * @param filed
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private Object[] processDot(String filed, Object obj) throws Exception {
		while(filed.indexOf(".") != -1) {
			obj = getValue(obj, filed.substring(0, filed.indexOf(".")));
			filed = filed.substring(filed.indexOf(".") + 1);
		}
		return new Object[]{obj, filed};
	}

	/**
	 * 判断if和elseif中的条件是否满足，满足返回true，否则返回false;
	 * @param tempTagParmMap
	 * @param ifelseObj
	 * @return
	 * @throws Exception 
	 */
	private boolean checkIf(Map<String, Object> parmMap, Object ifelseObj) throws Exception {
		Set keySet = parmMap.keySet();
		for(Iterator<String> it = keySet.iterator(); it.hasNext(); ) {
			String key = it.next();
			String value = parmMap.get(key).toString();
			if(key.startsWith("object.")) {
				key = key.substring(7);
			}
			
			Object[] proDotResults = processDot(key, ifelseObj);
			Object obj = proDotResults[0];
			key = (String)proDotResults[1];
			
			if(key.indexOf(".") != -1) {
				key = key.substring(key.lastIndexOf(".") + 1);
			}
			obj = getValue(obj, key);
			if(obj != null) {
				obj = obj.toString();
			}
			System.out.println("obj=" + obj + ":value=" + value + obj.equals(value));
			if(value == null || obj == null || !obj.equals(value)) {
				return false;
			}
		}
		return true;
	}

	public IClazz getClazzService() {
		return clazzService;
	}

	public void setClazzService(IClazz clazzService) {
		this.clazzService = clazzService;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public IColumns getColumnsService() {
		return columnsService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public ITeacher getTeacherService() {
		return teacherService;
	}

	public ISurveyQst getSurveyQstService() {
		return surveyQstService;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}
	
	public void setSurveyQstService(ISurveyQst surveyQstService) {
		this.surveyQstService = surveyQstService;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}
	
    public void setTeacherService(ITeacher teacherService) {
		this.teacherService = teacherService;
	}

	public IPageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(IPageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}

	public IArticle getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticle articleService) {
		this.articleService = articleService;
	}

}

