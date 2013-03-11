package com.shangde.edu.sys.condition;

import java.util.Calendar;
import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryUserWebFromCondition extends PageQuery {

	/** 开始日期 */
	private Date startTime;
	/** 开始时间 */
	private String startHH;
	/** 结束日期 */
	private Date endTime;
	/** 结束时间 */
	private String endHH;
	/** webFrom */
	private String webFrom;
	/** webAgent */
	private String webAgent;
	/** 项目编号 */
	private Integer subjectId;
	/** 联系电话 */
	private String mobile;
	/** 电子邮件 */
	private String email;
	/** 是否导出Excel */
	private Boolean exportExcel;

	/**
	 * 合并日期。将时间部分的值（时、分、秒）合并到指定的Date对象中。
	 * 
	 * @param date
	 *            被设置时间部分的日期对象
	 * @param timePart
	 *            时间部分（包括时、分、秒），使用冒号（:）分隔每个部分
	 * @return 设置后的日期对象
	 */
	private Date mergeDate(Date date, String timePart) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		if (timePart != null) {
			String[] timeParts = timePart.split(":");
			if (timeParts.length >= 3) {
				int hour = Integer.parseInt(timeParts[0], 10);
				int min = Integer.parseInt(timeParts[1], 10);
				int sec = Integer.parseInt(timeParts[2], 10);
				c.set(Calendar.HOUR, hour);
				c.set(Calendar.MINUTE, min);
				c.set(Calendar.SECOND, sec);
				if (hour == 0 && min == 0 && sec == 0) {
					c.set(Calendar.MILLISECOND, 0);
				} else if (hour == 23 && min == 59 && sec == 59) {
					c.set(Calendar.MILLISECOND, 999);
				}
			}
		}
		return c.getTime();
	}

	public Date getStartTime() {
		return mergeDate(startTime, startHH);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return mergeDate(endTime, endHH);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getWebFrom() {
		return webFrom;
	}

	public void setWebFrom(String webFrom) {
		this.webFrom = webFrom;
	}

	public String getWebAgent() {
		return webAgent;
	}

	public void setWebAgent(String webAgent) {
		this.webAgent = webAgent;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		if (endHH == null) {
			endHH = "23:59:59";
		}
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public Boolean getExportExcel() {
		return exportExcel;
	}

	public void setExportExcel(Boolean exportExcel) {
		this.exportExcel = exportExcel;
	}

}
