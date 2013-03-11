package com.shangde.edu.cou.webdto;

import java.io.Serializable;
import java.util.List;

/**
 * 用户后台知识点,知识叶子节点
 * 视频观看，用户前台用到
 * @author chenshuai
 */
public class UserCenterKpointDTO implements Serializable, Comparable<Object> {
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
	 * 是否已经听过（0为未听过，1为听过）
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
	
	
	
	/**
	 * 重写compareTo方法
	 */
	public int compareTo(Object arg0) {
		UserCenterKpointDTO kpointtemp = (UserCenterKpointDTO)arg0;
		return new Integer(sortNum).compareTo(new Integer(kpointtemp.getSortNum()));
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

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getIsAudition() {
		return isAudition;
	}

	public void setIsAudition(int isAudition) {
		this.isAudition = isAudition;
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

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLeaf() {
		return leaf;
	}

	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}

	public int getVoId() {
		return voId;
	}

	public void setVoId(int voId) {
		this.voId = voId;
	}

	public String getIsWatch() {
		return isWatch;
	}

	public void setIsWatch(String isWatch) {
		this.isWatch = isWatch;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


}
