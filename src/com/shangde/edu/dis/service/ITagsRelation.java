package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.dis.domain.TagsRelation;

public interface ITagsRelation {

	/**
	 * 添加
	 * 
	 * @param tagsRelation
	 * @return
	 */
	public Integer addTagsRelation(TagsRelation tagsRelation);

	/**
	 * 批量添加
	 * 
	 * @param tagsRelationList
	 * @return
	 */
	public boolean batchAdd(List<TagsRelation> tagsRelationList);

	/**
	 * 根据话题ID查询
	 * 
	 * @param topicId
	 * @return
	 */
	public TagsRelation getTagsRelationByTopicId(int topicId);

	/**
	 * 根据话题ID删除
	 * 
	 * @param topicId
	 * @return
	 */
	public int delTagsRelationByTopicId(Map map);

	/**
	 * 根据话题ID修改标签关联
	 * 
	 * @param map
	 * @return
	 */
	public int updateByTopicId(Map map);
}
