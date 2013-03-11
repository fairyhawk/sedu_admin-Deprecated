package com.shangde.edu.feed.dto;

import java.io.Serializable;

/**
 * DTO 任务统计模型(任务详细统计结果,精确到某天)...
 * 
 * @author Libg
 * 
 */
public class TaskListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 任务id
	private String name;// 任务名称
	private int planTotal;// 计划发送总数
	private int day24Count;// 第一天发出数
	private int day48Count;// 第二天发出数
	private int day72Count;// 第三天发出数
	private int uac24Count;// 第一天激活数
	private int uac48Count;// 第二天激活数
	private int uac72Count;// 第三天激活数
	private int totals;// 激活总数量

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlanTotal() {
		return planTotal;
	}

	public void setPlanTotal(int planTotal) {
		this.planTotal = planTotal;
	}

	public int getDay24Count() {
		return day24Count;
	}

	public void setDay24Count(int day24Count) {
		this.day24Count = day24Count;
	}

	public int getDay48Count() {
		return day48Count;
	}

	public void setDay48Count(int day48Count) {
		this.day48Count = day48Count;
	}

	public int getDay72Count() {
		return day72Count;
	}

	public void setDay72Count(int day72Count) {
		this.day72Count = day72Count;
	}

	public int getUac24Count() {
		return uac24Count;
	}

	public void setUac24Count(int uac24Count) {
		this.uac24Count = uac24Count;
	}

	public int getUac48Count() {
		return uac48Count;
	}

	public void setUac48Count(int uac48Count) {
		this.uac48Count = uac48Count;
	}

	public int getUac72Count() {
		return uac72Count;
	}

	public void setUac72Count(int uac72Count) {
		this.uac72Count = uac72Count;
	}

	public int getTotals() {
		return totals;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}

}
