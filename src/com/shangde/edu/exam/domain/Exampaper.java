package com.shangde.edu.exam.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.databinding.types.soapencoding.DateTime;

import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.kb.domain.Knowledge;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.domain.StudyCourse;
import com.shangde.edu.sys.domain.Subject;

/**
 * 试卷
 * @author chenshuai
 *
 */
public class Exampaper implements Serializable{
	public static final int EXAMPAPER_DEFAULT_STATUS = 0;
	public static final int EXAMPAPER_FREEZE_STATUS = 1;
	public static final int EXAMPAPER_DELETE_STATUS = 2;
	/**
	 * 试卷ID
	 */
    private int epId;
    /**
     * 所属专业ID
     */
    private int subjectId;
    /**
     * 试卷分数
     */
    private int userScore;
    /**
     * 试卷平均分数
     */
    private float avgScore;
    /**
     * 最大分
     */
    private double maxfen;
    /**
     * 最小分
     */
    private double minfen;
    
    /**
     * 班型id
     */
    private int courseId;
    
    private int korcouId;
    /**
     * 试卷名称
     */
    private String epName;
    /**
     * 考试描述
     */
    private String epInfo;
    /**
     * 默认值“key”考试关键字
     */
    private String epKeyword;
    /**
     * 答题考试时间
     */
    private int replyTime;
    /**
     * 试卷总分数
     */
    private float epTotelScore;
    /**
     * 统计考试次数
     */
    private int epNum;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 试卷状态 0正常。冻结
     */
    private int epState;
    /**
     * 发布人id（管理员id）
     */
    private int userId;
    /**
     * 考试难度系数 1星，2星，3星，4星，...
     */
    private int coeffcient;
    /**
     * 参加考试人数
     */
    private int joinNum;
    /**
     * 通过人数
     */
    private int passNum;
    /**
     * 课程或知识点id
     */
    private int kOrCouId;
    /**
     * 关节点类型
     */
    private int type;
    /**
     * 试卷难度
     */
    private int level;
    /**
     * 考试的测试类型关键字
     */
    private String eptypekeyword;
    /**
     * 考试的测试类型
     */
    private int eptype;
    /**
     * 知识库科目id
     */
    private int cid;
    /**
     * 知识库的知识点id
     */
    private int zkId;
    /**
     * 知识库的专业id
     */
    private int zzhuanyeId;
    /**
     * 试卷截止时间
     */
    private java.util.Date endTime;
    /**
     * 试卷积分
     */
    private int jifen;
    /**
     * 试卷题型
     */
    private String examqsttype; 
    /**
     * 课程ID
     */
    private int sortId; 
    
    /**
     * 专业名称 // 非数据库字段
     */
    private String subjectName; 
    
    /**
     * 试卷所属项目 // 非数据库字段
     */
    private String coursesortName;
    
    /**
     * 所属课程 title // 非数据库字段
     */
    private String title;
    
    private List<Qst> qst;
    private List<Qstmiddle> qstmiddle;
    
    private Knowledge knowledge;
    
    private Course course;
    
    private Course courseNew;
    
    private Coursesort coursesort;//课程表
    
    private Professional professional;
    
    private StudyCourse studycourse;
    
    private Subject subject;
    
    private Kpoint kpoint;
    /**
     * 录试卷作者
     */
    private String author;
    /**
     * 上次修改时间
     */
    private java.util.Date lastEditTime;
    
    private int qstmiddlecount;//统计此试卷里面的试题数量
    
    private int year;
    
   
    
    private List<Reviews> reviewsList = new ArrayList<Reviews>();

	public List<Reviews> getReviewsList() {
		return reviewsList;
	}

	public void setReviewsList(List<Reviews> reviewsList) {
		this.reviewsList = reviewsList;
	}



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getEpId(){
        return epId;
    }

    public void setEpId(Integer epId){
        this.epId = (epId!=null?epId:0); 
    }
        
    public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public String getEpName(){
        return epName;
    }

    public void setEpName(String epName){
        this.epName = epName; 
    }
        
    public String getEpInfo(){
        return epInfo;
    }

    public void setEpInfo(String epInfo){
        this.epInfo = epInfo; 
    }
        
    public String getEpKeyword(){
        return epKeyword;
    }

    public void setEpKeyword(String epKeyword){
        this.epKeyword = epKeyword; 
    }
        
    public int getReplyTime(){
        return replyTime;
    }

    public void setReplyTime(int replyTime){
        this.replyTime = replyTime; 
    }
        
    public float getEpTotelScore(){
        return epTotelScore;
    }

    public void setEpTotelScore(float epTotelScore){
        this.epTotelScore = epTotelScore; 
    }
        
    public int getEpNum(){
        return epNum;
    }

    public void setEpNum(int epNum){
        this.epNum = epNum; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public int getEpState(){
        return epState;
    }

    public void setEpState(int epState){
        this.epState = epState; 
    }
        
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId; 
    }
        
    public int getCoeffcient(){
        return coeffcient;
    }

    public void setCoeffcient(int coeffcient){
        this.coeffcient = coeffcient; 
    }
        
    public int getJoinNum(){
        return joinNum;
    }

    public void setJoinNum(int joinNum){
        this.joinNum = joinNum; 
    }
        
    public int getPassNum(){
        return passNum;
    }

    public void setPassNum(int passNum){
        this.passNum = passNum; 
    }
        
    public int getKOrCouId(){
        return kOrCouId;
    }

    public void setKOrCouId(int kOrCouId){
        this.kOrCouId = kOrCouId; 
    }
        
    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type; 
    }
        
    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level; 
    }

	public List<Qst> getQst() {
		return qst;
	}

	public void setQst(List<Qst> qst) {
		this.qst = qst;
	}

	public String getEptypekeyword() {
		return eptypekeyword;
	}

	public void setEptypekeyword(String eptypekeyword) {
		this.eptypekeyword = eptypekeyword;
	}

	public int getEptype() {
		return eptype;
	}

	public void setEptype(int eptype) {
		this.eptype = eptype;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public StudyCourse getStudycourse() {
		return studycourse;
	}

	public void setStudycourse(StudyCourse studycourse) {
		this.studycourse = studycourse;
	}

	public int getZkId() {
		return zkId;
	}

	public void setZkId(int zkId) {
		this.zkId = zkId;
	}

	public int getZzhuanyeId() {
		return zzhuanyeId;
	}

	public void setZzhuanyeId(int zzhuanyeId) {
		this.zzhuanyeId = zzhuanyeId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public java.util.Date getEndTime() {
		
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public String getExamqsttype() {
		return examqsttype;
	}

	public void setExamqsttype(String examqsttype) {
		this.examqsttype = examqsttype;
	}

	public List<Qstmiddle> getQstmiddle() {
		return qstmiddle;
	}

	public void setQstmiddle(List<Qstmiddle> qstmiddle) {
		this.qstmiddle = qstmiddle;
	}

	public Course getCourseNew() {
		return courseNew;
	}

	public void setCourseNew(Course courseNew) {
		this.courseNew = courseNew;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public Coursesort getCoursesort() {
		return coursesort;
	}

	public void setCoursesort(Coursesort coursesort) {
		this.coursesort = coursesort;
	}

	public int getKorcouId() {
		return korcouId;
	}

	public void setKorcouId(int korcouId) {
		this.korcouId = korcouId;
	}

	

	public int getQstmiddlecount() {
		return qstmiddlecount;
	}

	public void setQstmiddlecount(int qstmiddlecount) {
		this.qstmiddlecount = qstmiddlecount;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}





	public double getMaxfen() {
		return maxfen;
	}

	public double getMinfen() {
		return minfen;
	}

	public void setMaxfen(double maxfen) {
		this.maxfen = maxfen;
	}

	public void setMinfen(double minfen) {
		this.minfen = minfen;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public java.util.Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(java.util.Date lastEditTime) {
		
		this.lastEditTime = lastEditTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCoursesortName() {
		return coursesortName;
	}

	public void setCoursesortName(String coursesortName) {
		this.coursesortName = coursesortName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
