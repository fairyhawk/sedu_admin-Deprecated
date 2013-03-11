package com.shangde.edu.vst.domain;

import java.io.Serializable;

/**
 * 视频打分实体
 * @author Yangning
 *
 */
public class VideoLevelStastics  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pointId;
	/**一定时间内用户评论数量**/
	private Integer cusAll;
	/**一定时间内用户评论数量，去重**/
	private Integer cusDistinctAll;
	/**一定时间内用户打开的视频数量**/
	private Integer openedCount; 
	/**视频名称**/
	private String videoName;
	
	/**项目名称**/
	private String subjectName;
	
	private Integer opendUser;
	
	private Integer pointSoldCount;
	
	/**评价总数*/
	private int evaluateCount;
	
	/**好评总数*/
	private int goodevaluateCount;
	
	/**差评总数*/
	private int badevaluateCount;
	
	public Integer getCusAll() {
		return cusAll;
	}

	public void setCusAll(Integer cusAll) {
		this.cusAll = cusAll;
	}

	public Integer getCusDistinctAll() {
		return cusDistinctAll;
	}

	public void setCusDistinctAll(Integer cusDistinctAll) {
		this.cusDistinctAll = cusDistinctAll;
	}

	public Integer getOpenedCount() {
		return openedCount;
	}

	public void setOpenedCount(Integer openedCount) {
		this.openedCount = openedCount;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getOpendUser() {
		return opendUser;
	}

	public void setOpendUser(Integer opendUser) {
		this.opendUser = opendUser;
	}

	public Integer getPointSoldCount() {
		return pointSoldCount;
	}

	public void setPointSoldCount(Integer pointSoldCount) {
		this.pointSoldCount = pointSoldCount;
	}

	public int getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(int evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public int getGoodevaluateCount() {
		return goodevaluateCount;
	}

	public void setGoodevaluateCount(int goodevaluateCount) {
		this.goodevaluateCount = goodevaluateCount;
	}

	public int getBadevaluateCount() {
		return badevaluateCount;
	}

	public void setBadevaluateCount(int badevaluateCount) {
		this.badevaluateCount = badevaluateCount;
	}
	
}
