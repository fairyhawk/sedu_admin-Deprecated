package com.shangde.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 配置服务类，在spring中配置为singleton域，提供没必要写在数据库中的各种配置参数
 * 
 * @author guoqiang.liu
 */
public class ConfigService extends BaseService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> paperStatusMap;
	private Map<Integer, String> mathThinking;
	private Map<Integer, String> examStatusMap;
	private Map<String, String> telephoneMap;
	private Map<Float, Float> fsdMap;
	private List<String> fontFilePath;
	private String base;
	private String rootRealPath;
	private String quizPathPrefix;
	private String reportPathPrefix;
	private Map<String, String> levelViewMap;
	private Map<String, Map> reportType;
	private Map<String, String> msgTypeMap;
	private List<Integer> subjectIdList;
	private String allowIpRange;
	
	/**
	 * 项目路径
	 */
	private String projectURL;
	
	/**
	 * 导入路径，用于导入图片，js，css文件
	 */
	private String importURL;
	private Map<String,String> msgSortMap;//消息分类
	
	/**
	 * 来源网站列表
	 */
	private List<String> webFromList;
	
	/**
	 * 代理商列表
	 */
	private List<String> webAgentList;
	
	/**
	 * 任务应用分类
	 */
	private Map applicationSortMap;
	
	private Map userLevelMap;
	/**
	 * 课程推荐模式
	 */
	private Map<Integer, String> courseMode;
	
	/**
	 * 考试难度
	 */
	private Map<Integer,String> examlevel;
	
    /**
     * @author 代长福 
     * 考试后台用到
     */
	public Map<Integer, String> getExamlevel() {
		return examlevel;
	}

	public void setExamlevel(Map<Integer, String> examlevel) {
		this.examlevel = examlevel;
	}

	public List<String> getFontFilePath() {
		return fontFilePath;
	}

	public void setFontFilePath(List<String> fontFilePath) {
		this.fontFilePath = fontFilePath;
	}

	public Map<Integer, String> getPaperStatusMap() {
		return paperStatusMap;
	}

	public void setPaperStatusMap(Map<Integer, String> paperStatusMap) {
		this.paperStatusMap = paperStatusMap;
	}

	public Map<Integer, String> getMathThinking() {
		return mathThinking;
	}

	public void setMathThinking(Map<Integer, String> mathThinking) {
		this.mathThinking = mathThinking;
	}

	public Map<Float, Float> getFsdMap() {
		return fsdMap;
	}

	public void setFsdMap(Map<Float, Float> fsdMap) {
		this.fsdMap = fsdMap;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getQuizPathPrefix() {
		return quizPathPrefix;
	}

	public void setQuizPathPrefix(String quizPathPrefix) {
		this.quizPathPrefix = quizPathPrefix;
	}

	public Map<Integer, String> getExamStatusMap() {
		return examStatusMap;
	}

	public void setExamStatusMap(Map<Integer, String> examStatusMap) {
		this.examStatusMap = examStatusMap;
	}

	public Map<String, String> getLevelViewMap() {
		return levelViewMap;
	}

	public void setLevelViewMap(Map<String, String> levelViewMap) {
		this.levelViewMap = levelViewMap;
	}

	public Map<String, Map> getReportType() {
		return reportType;
	}

	public void setReportType(Map<String, Map> reportType) {
		this.reportType = reportType;
	}

	public String getReportPathPrefix() {
		return reportPathPrefix;
	}

	public void setReportPathPrefix(String reportPathPrefix) {
		this.reportPathPrefix = reportPathPrefix;
	}

	public String getRootRealPath() {
		return rootRealPath;
	}

	public void setRootRealPath(String rootRealPath) {
		this.rootRealPath = rootRealPath;
	}

	public List<Integer> getSubjectIdList() {
		return subjectIdList;
	}

	public void setSubjectIdList(List<Integer> subjectIdList) {
		this.subjectIdList = subjectIdList;
	}

	public Map<Integer, String> getCourseMode() {
		return courseMode;
	}

	public void setCourseMode(Map<Integer, String> courseMode) {
		this.courseMode = courseMode;
	}

	public String getProjectURL() {
		return projectURL;
	}

	public void setProjectURL(String projectURL) {
		this.projectURL = projectURL;
	}

	public Map<String, String> getTelephoneMap() {
		return telephoneMap;
	}

	public void setTelephoneMap(Map<String, String> telephoneMap) {
		this.telephoneMap = telephoneMap;
	}

	public Map getApplicationSortMap() {
		return applicationSortMap;
	}

	public void setApplicationSortMap(Map applicationSortMap) {
		this.applicationSortMap = applicationSortMap;
	}

	public Map getUserLevelMap() {
		return userLevelMap;
	}

	public void setUserLevelMap(Map userLevelMap) {
		this.userLevelMap = userLevelMap;
	}

	public Map<String, String> getMsgTypeMap() {
		return msgTypeMap;
	}

	public void setMsgTypeMap(Map<String, String> msgTypeMap) {
		this.msgTypeMap = msgTypeMap;
	}

	public Map<String, String> getMsgSortMap() {
		return msgSortMap;
	}

	public void setMsgSortMap(Map<String, String> msgSortMap) {
		this.msgSortMap = msgSortMap;
	}

	public List<String> getWebFromList() {
		return webFromList;
	}

	public void setWebFromList(List<String> webFromList) {
		this.webFromList = webFromList;
	}

	public List<String> getWebAgentList() {
		return webAgentList;
	}

	public void setWebAgentList(List<String> webAgentList) {
		this.webAgentList = webAgentList;
	}

	public String getImportURL() {
		return importURL;
	}

	public void setImportURL(String importURL) {
		this.importURL = importURL;
	}

	public String getAllowIpRange() {
		return allowIpRange;
	}

	public void setAllowIpRange(String allowIpRange) {
		this.allowIpRange = allowIpRange;
	}
}
