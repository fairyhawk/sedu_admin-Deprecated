package com.shangde.edu.sys.domain;

public class Model {
	
	private int id;
	
	private String model_name;  //模块名称
	
	private String url;  //模块对应URL
	
	private String content; //模块内容
	
	private Integer m_id; //模块所属模板id
	
	private Integer sort; //排序
	
	private Integer is_enabled;  //是否可用
	
	private String remark; //备注
	
	private String author; //创建人
	
	private String create_time; //创建时间
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel_name() {
		return model_name;
	}

	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIs_enabled() {
		return is_enabled;
	}

	public void setIs_enabled(Integer is_enabled) {
		this.is_enabled = is_enabled;
	}
	
}
