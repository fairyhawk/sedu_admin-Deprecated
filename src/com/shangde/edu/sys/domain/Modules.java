package com.shangde.edu.sys.domain;

import java.util.Date;
import java.util.List;
/**
 * 模板类
 * @author cjx
 */
public class Modules {
	
	private int id;
	/**
	 * 模板名称
	 */
	private String name; 
	/**
	 * 模板内容
	 */
	private String content;
	/**
	 * 模板保存的url
	 */
	private String url;
	/**
	 * 模板说明
	 */
	private String desc;
	/**
	 * 模板类型
	 */
	private int type;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 子模块id串
	 */
	private String mIds;
	/**
	 * 保存模块顺序
	 */
	private String[] orderMIds;
	/**
	 * 子模块列表
	 */
	private List<Model> modelList;
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<Model> getModelList() {
		return modelList;
	}
	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}
	public String getmIds() {
		return mIds;
	}
	public void setmIds(String mIds) {
		this.mIds = mIds;
	}
	public String[] getOrderMIds() {
		return orderMIds;
	}
	public void setOrderMIds(String[] orderMIds) {
		this.orderMIds = orderMIds;
	}
	
}
