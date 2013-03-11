/**
 * 
 */
package com.shangde.edu.dis.domain;

import java.util.Date;

/**
 * @author Libg
 * 
 * @category 用户只能针对某事做一次操作记录
 * 
 */
public class UniqueRecord implements java.io.Serializable {

	private int id;
	private int cusId;// 用户id
	private int objectId;// 对象记录id
	private int type;// 分类
	private Date pubTime;// 发布时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

}
