package com.shangde.edu.dis.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.domain.TagsRelation;

public class TagsRelationImpl extends BaseService implements ITagsRelation {

	public Integer addTagsRelation(TagsRelation tagsRelation) {
		return simpleDao.createEntity("TagsRelation_NS.createTagsRelation",
				tagsRelation);
	}

	public boolean batchAdd(List<TagsRelation> tagsRelationList) {

		if (tagsRelationList == null) {
			return false;
		}

		boolean flag = false;
		try {
			for (TagsRelation tagsRelation : tagsRelationList) {
				addTagsRelation(tagsRelation);
			}

			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 根据话题ID查询
	 * @param topicId
	 * @return
	 */
	public TagsRelation getTagsRelationByTopicId(int topicId){
		return simpleDao.getEntity("TagsRelation_NS.getTagsRelationByTopicId", topicId);
	}
	
	/**
	 * 根据话题ID删除
	 * @param topicId
	 * @return
	 */
	public int delTagsRelationByTopicId(Map map){
		return simpleDao.delete("TagsRelation_NS.delTagsRelationByTopicId", map);
	}
	
	/**
	 * 根据话题ID修改标签关联
	 * @param map
	 * @return
	 */
	public int updateByTopicId(Map map){
		return simpleDao.update("TagsRelation_NS.updateByTopicId", map);
	}
}
