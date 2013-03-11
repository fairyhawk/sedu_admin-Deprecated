package com.shangde.edu.cou.webdto;

import java.util.Date;
import java.util.List;

import com.shangde.edu.cou.domain.Teacher;

/**
 * 用于用户中心课程展示
 * @author chenshuai
 * Modified By baiang.zhao, Date: 2011-5-10 10:59:31
 * 
 */
public class UserCenterCourseDTO {
	/**
	 * 课程ID
	 */
	private int courseId;

	/**
	 * 标题
	 */
	private String title;

	/* 课程简介 现在用来存储上传或者未上传的信息*/
	private String introduce;
	
	/**
	 * 视频图片
	 */
	private String vedioPicUrl;
	
    /**
     * 课时
     */
    private float lessionTime;
    
    /**
     * 教师列表
     */
    private List<Teacher> teacherList;
    
    /**
     * 最后观看时间
     */
    private Date lastTime;
    
    /**
     * 最后观看的视频ID
     */
    private int  lastKpointId;
    
    /**
     * 专业ID
     */
    private int subjectId;
    
    /**
     * 包ID
     */
    private int sellId;
    
    /**
     * 用户前台知识点列表
     */
    private List<UserCenterKpointDTO> kpointList ;
    
    /**
     * 学习统计对象
     */
    private StudyStatisticsDTO studyStatisticsDTO;
    /**
     * 已上传视频数量
     */
    private int uploadedSize;
    
    /**
     * 视频数量
     */
    private int vedioSize;
    
    /**
     * 用户中心 -> 我的课程 -> 下载PDF讲义，下载链接
     */
    private String dpdfUrl;
    
    /**
     * 用户中心 -> 我的课程 -> 下载PPT讲义，下载链接
     */
    private String dpptUrl;

    private String dmp3Url;
    
	public String getDmp3Url() {
        return dmp3Url;
    }

    public void setDmp3Url(String dmp3Url) {
        this.dmp3Url = dmp3Url;
    }

    public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVedioPicUrl() {
		return vedioPicUrl;
	}

	public void setVedioPicUrl(String vedioPicUrl) {
		this.vedioPicUrl = vedioPicUrl;
	}

	public float getLessionTime() {
		return lessionTime;
	}

	public void setLessionTime(float lessionTime) {
		this.lessionTime = lessionTime;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getLastKpointId() {
		return lastKpointId;
	}

	public void setLastKpointId(int lastKpointId) {
		this.lastKpointId = lastKpointId;
	}

	public List<UserCenterKpointDTO> getKpointList() {
		return kpointList;
	}

	public void setKpointList(List<UserCenterKpointDTO> kpointList) {
		this.kpointList = kpointList;
	}

	public StudyStatisticsDTO getStudyStatisticsDTO() {
		return studyStatisticsDTO;
	}

	public void setStudyStatisticsDTO(StudyStatisticsDTO studyStatisticsDTO) {
		this.studyStatisticsDTO = studyStatisticsDTO;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getUploadedSize() {
		return uploadedSize;
	}

	public void setUploadedSize(int uploadedSize) {
		this.uploadedSize = uploadedSize;
	}

	public int getVedioSize() {
		return vedioSize;
	}

	public void setVedioSize(int vedioSize) {
		this.vedioSize = vedioSize;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getDpdfUrl() {
		return dpdfUrl;
	}

	public void setDpdfUrl(String dpdfUrl) {
		this.dpdfUrl = dpdfUrl;
	}

	public String getDpptUrl() {
		return dpptUrl;
	}

	public void setDpptUrl(String dpptUrl) {
		this.dpptUrl = dpptUrl;
	}

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
}
