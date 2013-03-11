package com.shangde.edu.cou.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 用户知识点DTO
 * 用于正式听课及试听课程，获取课程的所有知识点相关信息
 * 
 * @author chenshuai
 */
public class UserKpointDTO implements Serializable, Comparable<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4733928870206504246L;

	/**
	 * 父ID
	 */
	private int pId;
	
	/**
	 * ID
	 */
	private int id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 视频地址
	 */
	private String vedioUrl;
	/**
	 * 主讲老师
	 */
	private String vedioTeacher;
	/**
	 * 排序值
	 */
	private int sortNum;
	
	/**
	 * 是否能试听(0：不能, 1:能)
	 */
	private int isAudition;
	
	/**
	 * 用户课程知识点ID
	 */
	private int cusCouKpointId;
	
	/**
	 * 是否能试听
	 */
	private int rdState;
	
	/**
	 * 是购买后用，还是试听时用的标示
	 */
	private String type;
	
	private List<String> urls;
	
	/**
	 * 讲义
	 */
	private String lecture;
	
	/**
	 * 级别
	 */
	private int level;
	
	/**
	 * 叶子节点
	 */
	private int leaf;
	
	/**
	 * 视频ID
	 */
	private int voId;
	/**
	 * 判断是否已观看视频
	 */
	private String isWatch="0";
	private String ccUrl;// ccURL
	private Integer playType;//播放类型 0为原来播放方式，1为CC播放方式
	
	public String getCcUrl() {
		return ccUrl;
	}

	public void setCcUrl(String ccUrl) {
		this.ccUrl = ccUrl;
	}

	public Integer getPlayType() {
		return playType;
	}

	public void setPlayType(Integer playType) {
		this.playType = playType;
	}

	public String getIsWatch() {
        return isWatch;
    }

    public void setIsWatch(String isWatch) {
        this.isWatch = isWatch;
    }

    public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public int compareTo(Object arg0) {
		UserKpointDTO kpointtemp = (UserKpointDTO)arg0;
		return new Integer(sortNum).compareTo(new Integer(kpointtemp.getSortNum()));
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public int getIsAudition() {
		return isAudition;
	}

	public void setIsAudition(int isAudition) {
		this.isAudition = isAudition;
	}

	public int getCusCouKpointId() {
		return cusCouKpointId;
	}

	public void setCusCouKpointId(int cusCouKpointId) {
		this.cusCouKpointId = cusCouKpointId;
	}

	public int getRdState() {
		return rdState;
	}

	public void setRdState(int rdState) {
		this.rdState = rdState;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	public int getVoId() {
		return voId;
	}

	public void setVoId(int voId) {
		this.voId = voId;
	}
	public String getVedioTeacher() {
		return vedioTeacher;
	}

	public void setVedioTeacher(String vedioTeacher) {
		this.vedioTeacher = vedioTeacher;
	}
}
