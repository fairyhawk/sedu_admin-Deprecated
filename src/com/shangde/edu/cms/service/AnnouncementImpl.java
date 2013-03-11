package com.shangde.edu.cms.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.util.StringUtil;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryAcmentCondition;
import com.shangde.edu.cms.domain.Announcement;

public class AnnouncementImpl extends BaseService implements IAnnouncement {

	public Integer add(Announcement announcement) {
		return simpleDao.createEntity("Announcement_NS.add", announcement);
	}

	public int delete(String ids) {
		return simpleDao.delete("Announcement_NS.delete", ids);
	}

	public PageResult getAll(PageQuery pageQuery) {
		return simpleDao.getPageResult("Announcement_NS.getAll",
				"Announcement_NS.getAllCount", pageQuery);
	}

	public List<Announcement> getTop10(String subjectIds) {
		List<Announcement> acmList = simpleDao.getForList("Announcement_NS.getTop10", subjectIds);
		for (Announcement announcement : acmList) {
			announcement.setTitle(StringUtil.chop(announcement.getTitle(), 25, "..."));
		}
		return acmList;
	}

	public int update(Announcement announcement) {
		return simpleDao.update("Announcement_NS.update", announcement);
	}
	
	public Announcement getById(int id) {
		return simpleDao.getEntity("Announcement_NS.getById", id);
	}
	
	public PageResult search(QueryAcmentCondition queryAcmentCondition) {
		return simpleDao.getPageResult("Announcement_NS.search",
				"Announcement_NS.searchCount", queryAcmentCondition);
	}
	
	public int updateStatus(Map map) {
		return simpleDao.update("Announcement_NS.editStatus", map);
	}
	
	public Announcement getUp(Map map) {
		Announcement announcement = simpleDao.getEntity("Announcement_NS.getUp", map);
		if(announcement!=null){
			announcement.setTitle(StringUtil.chop(announcement.getTitle(), 25, "..."));
		}
		return announcement;
	}
	
	public Announcement getDown(Map map) {
		Announcement announcement = simpleDao.getEntity("Announcement_NS.getDown", map);
		if(announcement!=null){
			announcement.setTitle(StringUtil.chop(announcement.getTitle(), 25, "..."));
		}
		return announcement;
	}
	
	public int updateClickNum(int id) {
		return simpleDao.update("Announcement_NS.updateClickNum", id);
	}
}
