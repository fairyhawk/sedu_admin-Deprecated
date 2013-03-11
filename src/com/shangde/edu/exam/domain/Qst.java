package com.shangde.edu.exam.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;

/**
 * 试卷试题
 * @author chenshuai
 *
 */
public class Qst implements Serializable{
	/**
	 * 试题ID
	 */
    private int qstId;
    /**
     * 试题索引id
     */
    private int qstindexId;
    /**
     * 试题内容
     */
    private int epId;
    /**
     * 试题内容
     */
    private String qstContent;
    /**
     * 正确选项
     */
    private String isAsr;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 分数
     */
    private float score;
    /**
     * 出题人
     */
    private String ctPerson;
    /**
     * 试题难度
     */
    private int level;
    /**
     * 添加时间
     */
    private java.util.Date addtime;
    /**
     * 录试卷作者
     */
    private String author;
    /**
     * 上次修改时间
     */
    private Date lastEditTime;
    private List<Options> options;
    private QstPic qstPic;
    /**
     * 错误评语
     */
    private String wrongJude;
    /**
     * 用户选择答案
     */
    private String wrongAsr;
    /**
     * 备注
     */
    private String note;
    /**
     * 判断是否有节点
     */
    private int qsttype;
    /**
     * 课程节点id
     */
    private int korcouId;
    /**
     * 课程班型id
     */
    private int courseId;
    private int sortId; 
    private Exampaper exampaper;
    private int quretype;
    private List<Qst> qstziti;//简答题子题
    private int qstsize;//次试题在中间表里的个数
    
    /**
     * 试题所属项目 // 非数据库字段
     */
    private String coursesortName ;
    
    /**
     * 所属课程 title // 非数据库字段
     */
    private String title;
    
    private Course course;
    
    private Coursesort coursesort;//课程表
    
	public String getWrongJude() {
		return wrongJude;
	}

	public void setWrongJude(String wrongJude) {
		this.wrongJude = wrongJude;
	}

	public QstPic getQstPic() {
		return qstPic;
	}

	public void setQstPic(QstPic qstPic) {
		this.qstPic = qstPic;
	}

	public int getQstId(){
        return qstId;
    }

    public void setQstId(int qstId){
        this.qstId = qstId; 
    }
        
    public int getEpId(){
        return epId;
    }

    public void setEpId(int epId){
        this.epId = epId; 
    }
        
    public String getQstContent(){
        return qstContent;
    }

    public void setQstContent(String qstContent){
        this.qstContent = qstContent; 
    }
        
    public String getIsAsr(){
        return isAsr;
    }

    public void setIsAsr(String isAsr){
        this.isAsr = isAsr; 
    }
        
    public int getQstType(){
        return qstType;
    }

    public void setQstType(Integer qstType){
    	try{
        this.qstType = qstType;
    	}catch(NumberFormatException e){
    	this.qstType=0;
    	}
    }
    
        
    public float getScore(){
        return score;
    }

   
	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public void setScore(float score){
        this.score = score; 
    }
        
    public String getCtPerson(){
        return ctPerson;
    }

    public void setCtPerson(String ctPerson){
        this.ctPerson = ctPerson; 
    }
        
    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level; 
    }
        
    public java.util.Date getAddtime(){
        return addtime;
    }

    public void setAddtime(java.util.Date addtime){
        this.addtime = addtime; 
    }

	public Exampaper getExampaper() {
		return exampaper;
	}

	public void setExampaper(Exampaper exampaper) {
		this.exampaper = exampaper;
	}

	public String getWrongAsr() {
		return wrongAsr;
	}

	public void setWrongAsr(String wrongAsr) {
		this.wrongAsr = wrongAsr;
	}


	public int getQuretype() {
		return quretype;
	}

	public void setQuretype(int quretype) {
		this.quretype = quretype;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getQsttype() {
		return qsttype;
	}

	public void setQsttype(int qsttype) {
		this.qsttype = qsttype;
	}

	public int getKorcouId() {
		return korcouId;
	}

	public void setKorcouId(Integer korcouId) {
		this.korcouId = (korcouId!=null?korcouId:0);
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public List<Qst> getQstziti() {
		return qstziti;
	}

	public void setQstziti(List<Qst> qstziti) {
		this.qstziti = qstziti;
	}

	public int getQstindexId() {
		return qstindexId;
	}

	public void setQstindexId(int qstindexId) {
		this.qstindexId = qstindexId;
	}

	public int getQstsize() {
		return qstsize;
	}

	public void setQstsize(int qstsize) {
		this.qstsize = qstsize;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Coursesort getCoursesort() {
		return coursesort;
	}

	public void setCoursesort(Coursesort coursesort) {
		this.coursesort = coursesort;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
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
}
