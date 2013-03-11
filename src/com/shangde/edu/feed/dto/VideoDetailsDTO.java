package com.shangde.edu.feed.dto;

import java.io.Serializable;
import java.util.Date;

import com.shangde.edu.cus.domain.Customer;

/**
 * DTO 视频统计，各项数据反查DTO
 * 
 * @author Libg
 * 
 */
public class VideoDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;// 记录id
	private Integer userId;// 用户id
	private Integer courseId;// 课程id
	private Customer customer;// 用户对象
	private Date statisticsTime;// 统计日期

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the statisticsTime
	 */
	public Date getStatisticsTime() {
		return statisticsTime;
	}

	/**
	 * @param statisticsTime
	 *            the statisticsTime to set
	 */
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}
