package com.shangde.edu.iphone.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.shangde.edu.cou.domain.Coursepic;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.CourseIsAuditionDTO;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;

/**
 * 课程表
 * @author chenshuai
 */
public class CourseIPhone implements Serializable,Comparable{
	
	public static final int COURSE_DEFAULT_STATUS = 0;
	public static final int COURSE_FREEZE_STATUS = 1;
	public static final int COURSE_DELETE_STATUS = 2;
	/**
	 * 课程ID
	 */
    private int courseId;
    
    /**
     *  分类ID
     */
    private int sortId;
    
    /**
     * 教师ID
     */
    private int teacherId;
    
    /**
     * 价格
     */
    private float price;
    
    /**
     * 推荐模式
     */
    private int tjMode;
    
    /**
     * 课时
     */
    private float lessionTime;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 简介
     */
    private String introduce;
    
    /**
     * 前置课程
     */
    private int precourseId;
    
    /**
     * 后置课程
     */
    private int flowcourseId;
    
    /**
     * 状态
     */
    private int status;
    
    /**
     * 购买数量
     */
    private int gmNum;
    
    /**
     * 收藏数量
     */
    private int scNum;
    
    /**
     * 退购数量
     */
    private int tgNum;
    
    /**
     * 点击量
     */
    private int clickNum;
    
    /**
     * 有效时间
     */
    private int validTime;
    
    /**
     * 年份
     */
    private int gradeId;
    
    /**
     * 专业ID
     */
    private int subjectId;
    
    /**
     * 专业
     */
    private Subject subject;
    
    /**
     * 年份
     */
    private Grade grade;
    
    /**
     * 教师
     */
    private Teacher teacher;
    
    /**
     * 原始价格
     * @return
     */
    
    private float oldPrice;
    
    /**
     * 保证通过
     */
    private int passFlag;
    
    /**
     * 答疑
     */
    private int QAFlag;
    
    /**
     * 学习资料
     */
    private int learnResFlag;
    
    /**
     * ppt课件
     */
    private int pptFlag;
    
    /**
     * 核心考试材料
     */
    private int examResFlag;
    
    /**
     * 推荐视频ID
     */
    private int tjVedioId;
    
    /**
     * 推荐视频
     */
    private Vedio tjvedio;
    
    /**
     * 试卷集合
     */
    private List<Exampaper> exampapers;
    
    /**
     * 教师推荐课程
     */
    private int teacherTjCourse;
    
    /**
     * 首推视频地址
     */
    private String vedioPicUrl;
    
    /**
     * 知识点json
     */
    private String kpointJson;
    
    /**
     * 思维导图
     */
    private String thinkMap;
    
    /**
     * 学习建议
     */
    private String learnAdvice;
    
    /**
     * 顶的次数
     */
    private int supportNum;
    
    /**
     * 踩的次数
     */
    private int unsupportNum;
    
    /**
     * 试用人群
     */
    private String forPeople;
    
    /**
     * 包含模块
     */
    private String includeModule;
    
    /**
     * 包含图片，为图片
     */
    private String includeService;
    
    /**
     * 课程图片
     */
    private List<Coursepic> courseMainPicList;
    
    /**
     * 班级IDs
     */
    private String dpptUrl;
    private String dpdfUrl;
    private String dmp3Url;
    
    private int newCourse;
    
    private List<Integer> clazzIds = new ArrayList();
    
    private List<CourseIsAuditionDTO> auditionVedio;
    
    private List<Teacher> teacherList;
    
	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public String getKpointJson() {
		return kpointJson;
	}

	public void setKpointJson(String kpointJson) {
		this.kpointJson = kpointJson;
	}

	public float getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(float oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public int getSortId(){
        return sortId;
    }

    public void setSortId(int sortId){
        this.sortId = sortId; 
    }
        
    public int getTeacherId(){
        return teacherId;
    }

    public void setTeacherId(int teacherId){
        this.teacherId = teacherId; 
    }
        
    public float getPrice(){
        return price;
    }

    public void setPrice(float price){
        this.price = price; 
    }
        
    public int getTjMode(){
        return tjMode;
    }

    public void setTjMode(int tjMode){
        this.tjMode = tjMode; 
    }
        
    public float getLessionTime(){
        return lessionTime;
    }

    public void setLessionTime(float lessionTime){
        this.lessionTime = lessionTime; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
    
    @JSON(format = "yyyy-MM-dd")
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }
        
    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title; 
    }
        
    public String getIntroduce(){
        return introduce;
    }

    public void setIntroduce(String introduce){
        this.introduce = introduce; 
    }
        
    public int getPrecourseId(){
        return precourseId;
    }

    public void setPrecourseId(int precourseId){
        this.precourseId = precourseId; 
    }
        
    public int getFlowcourseId(){
        return flowcourseId;
    }

    public void setFlowcourseId(int flowcourseId){
        this.flowcourseId = flowcourseId; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public int getGmNum(){
        return gmNum;
    }

    public void setGmNum(int gmNum){
        this.gmNum = gmNum; 
    }
        
    public int getScNum(){
        return scNum;
    }

    public void setScNum(int scNum){
        this.scNum = scNum; 
    }
        
    public int getTgNum(){
        return tgNum;
    }

    public void setTgNum(int tgNum){
        this.tgNum = tgNum; 
    }
        
    public int getClickNum(){
        return clickNum;
    }

    public void setClickNum(int clickNum){
        this.clickNum = clickNum; 
    }
        
    public int getValidTime(){
        return validTime;
    }

    public void setValidTime(int validTime){
        this.validTime = validTime; 
    }
    
    /**
     * 重写equals方法
     */
	public boolean equals(Object obj) {//重写比较函数
		CourseIPhone sorttemp = (CourseIPhone)obj;
		if(sorttemp != null && sorttemp.getCourseId() == this.getCourseId()){
			return true;
		}else{
			return false;
		}
	}
    
	/**
	 * 重写hashCode方法
	 */
	public int hashCode() {
		int result = 15;
		result = result*37 + this.getCourseId();
		
		return result;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public int getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(int passFlag) {
		this.passFlag = passFlag;
	}

	public int getQAFlag() {
		return QAFlag;
	}

	public void setQAFlag(int flag) {
		QAFlag = flag;
	}

	public int getLearnResFlag() {
		return learnResFlag;
	}

	public void setLearnResFlag(int learnResFlag) {
		this.learnResFlag = learnResFlag;
	}

	public int getExamResFlag() {
		return examResFlag;
	}

	public void setExamResFlag(int examResFlag) {
		this.examResFlag = examResFlag;
	}

	public int getTjVedioId() {
		return tjVedioId;
	}

	public void setTjVedioId(int tjVedioId) {
		this.tjVedioId = tjVedioId;
	}

	public int getTeacherTjCourse() {
		return teacherTjCourse;
	}

	public void setTeacherTjCourse(int teacherTjCourse) {
		this.teacherTjCourse = teacherTjCourse;
	}

	public int getPptFlag() {
		return pptFlag;
	}

	public void setPptFlag(int pptFlag) {
		this.pptFlag = pptFlag;
	}

	public String getVedioPicUrl() {
		return vedioPicUrl;
	}

	public void setVedioPicUrl(String vedioPicUrl) {
		this.vedioPicUrl = vedioPicUrl;
	}

	public Vedio getTjvedio() {
		return tjvedio;
	}

	public void setTjvedio(Vedio tjvedio) {
		this.tjvedio = tjvedio;
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Exampaper> getExampapers() {
		return exampapers;
	}

	public void setExampapers(List<Exampaper> exampapers) {
		this.exampapers = exampapers;
	}

	public String getThinkMap() {
		return thinkMap;
	}

	public void setThinkMap(String thinkMap) {
		this.thinkMap = thinkMap;
	}

	public String getLearnAdvice() {
		return learnAdvice;
	}

	public void setLearnAdvice(String learnAdvice) {
		this.learnAdvice = learnAdvice;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getUnsupportNum() {
		return unsupportNum;
	}

	public void setUnsupportNum(int unsupportNum) {
		this.unsupportNum = unsupportNum;
	}

	public String getForPeople() {
		return forPeople;
	}

	public void setForPeople(String forPeople) {
		this.forPeople = forPeople;
	}

	public String getIncludeModule() {
		return includeModule;
	}

	public void setIncludeModule(String includeModule) {
		this.includeModule = includeModule;
	}

	public List<Integer> getClazzIds() {
		return clazzIds;
	}

	public void setClazzIds(List<Integer> clazzIds) {
		this.clazzIds = clazzIds;
	}

	public List<CourseIsAuditionDTO> getAuditionVedio() {
		return auditionVedio;
	}

	public void setAuditionVedio(List<CourseIsAuditionDTO> auditionVedio) {
		this.auditionVedio = auditionVedio;
	}

	public List<Coursepic> getCourseMainPicList() {
		return courseMainPicList;
	}

	public void setCourseMainPicList(List<Coursepic> courseMainPicList) {
		this.courseMainPicList = courseMainPicList;
	}

	public String getIncludeService() {
		return includeService;
	}

	public void setIncludeService(String includeService) {
		this.includeService = includeService;
	}

	public String getDpptUrl() {
		return dpptUrl;
	}

	public void setDpptUrl(String dpptUrl) {
		this.dpptUrl = dpptUrl;
	}

	public String getDpdfUrl() {
		return dpdfUrl;
	}

	public void setDpdfUrl(String dpdfUrl) {
		this.dpdfUrl = dpdfUrl;
	}

    public String getDmp3Url() {
        return dmp3Url;
    }

    public void setDmp3Url(String dmp3Url) {
        this.dmp3Url = dmp3Url;
    }

	public int getNewCourse() {
		return newCourse;
	}

	public void setNewCourse(int newCourse) {
		this.newCourse = newCourse;
	}	
}
