package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.domain.Tags;

public class TagsImpl extends BaseService implements ITags {

	public Integer addTags(Tags tags) {
		return simpleDao.createEntity("Tags_NS.createTags", tags);
	}

	public List<Tags> getTagsList() {
		return simpleDao.getForList("Tags_NS.getTagsList", null);
	}

}
