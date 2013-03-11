package com.shangde.edu.dis.condition;

public class QueryDisAreaCondition implements java.io.Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5928962997643738186L;
	private int id;
    private int disId;
    private String name;
    private int sort;
    
    /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param disId the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	private String introduction;

	/**
	 * @return the order
	 */
	public int getSort() {
		return sort;
	}
	/**
	 * @param order the order to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}
}