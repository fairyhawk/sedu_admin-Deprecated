package com.shangde.edu.cus.condition;

import java.util.Calendar;
import java.util.Date;
import com.shangde.common.vo.PageQuery;

public class QueryAbnormalCustomerCondition extends PageQuery {

	private Date startTime;
	private String startHH;
	private Date endTime;
	private String endHH;
	private String email;
	private Integer abnormalTimes;

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
		if (startTime == null) {
			startTime = new Date(new Date().getTime() - 7 * 24 * 60 * 60 * 1000);
		}
		return mergeDate(startTime, startHH);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		if (endTime == null) {
			endTime = new Date();
		}
		return mergeDate(endTime, endHH);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAbnormalTimes() {
		if (abnormalTimes == null || abnormalTimes == 0) {
			abnormalTimes = 3;
		}
		return abnormalTimes;
	}

	public void setAbnormalTimes(Integer abnormalTimes) {
		this.abnormalTimes = abnormalTimes;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndHH() {
		if (endHH == null || "".equals(endHH)) {
			endHH = "23:59:59";
		}
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

}
