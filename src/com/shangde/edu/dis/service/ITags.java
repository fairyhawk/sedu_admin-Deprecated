package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.edu.dis.domain.Tags;

/**
 * 标签
 * 
 * @author Libg
 * 
 */
public interface ITags {

	/**
	 * 添加
	 * 
	 * @param tags
	 * @return
	 */
	public Integer addTags(Tags tags);

	/**
	 * 查询出所有可用状态列表
	 * 
	 * @return
	 */
	public List<Tags> getTagsList();

}
