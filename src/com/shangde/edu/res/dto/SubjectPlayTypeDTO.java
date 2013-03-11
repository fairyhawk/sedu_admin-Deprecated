package com.shangde.edu.res.dto;
/**
 *<p> 封装专业的id和类型 </p>
 * Author: joqk<br>
 * Date: Jun 28, 2012 <br>
 * Time: 4:18:33 PM <br>
 */
public class SubjectPlayTypeDTO {
	/**专业id*/
    private int subjectId;
    /**专业名称*/
    private String subjectName;
    /**播放类型*/
	private Integer playType;
	
	/**
	 * @return the subjectId
	 */
	public int getSubjectId() {
		return subjectId;
	}
	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @return the playType
	 */
	public Integer getPlayType() {
		return playType;
	}
	/**
	 * @param playType the playType to set
	 */
	public void setPlayType(Integer playType) {
		this.playType = playType;
	}
	
	

}
