package com.shangde.edu.dis.domain;

import java.io.Serializable;

/**
 * 
 * @author Libg
 * 
 * @category 标签关联表
 */
public class TagsRelation implements Serializable {

	private int id;
	private int objectTypeId;// 分类
	private int ObjectId;// 对象记录id
	private int tagId;// 标签id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(int objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public int getObjectId() {
		return ObjectId;
	}

	public void setObjectId(int objectId) {
		ObjectId = objectId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
}
