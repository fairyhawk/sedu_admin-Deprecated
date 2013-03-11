package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Chapter;
import com.shangde.edu.kb.condition.QueryChapterCondition;
import com.shangde.common.service.BaseService;

/**
 * Chapter操作对象实现
 * User: guoqiang.liu
 * Date: 2010-10-27
 */
@SuppressWarnings("unchecked")
public class ChapterImpl extends BaseService implements IChapter{
	
    public java.lang.Integer addChapter(Chapter chapter) {
return simpleDao.createEntity("Chapter_NS.createChapter",chapter);
    }

    public void delChapterById(int chId){
        simpleDao.deleteEntity("Chapter_NS.deleteChapterById",chId);
    }

    public void updateChapter(Chapter chapter) {
        simpleDao.updateEntity("Chapter_NS.updateChapter",chapter);
    }

    public Chapter getChapterById(int chId) {
        return simpleDao.getEntity("Chapter_NS.getChapterById",chId);
    }

    public List<Chapter> getChapterList(QueryChapterCondition queryChapterCondition) {
        return simpleDao.getForList("Chapter_NS.getChapterList",queryChapterCondition);
    }

	public List<Chapter> getChapterByList(QueryChapterCondition queryChapterCondition) {
		return simpleDao.getForList("Chapter_NS.getChapterByList",queryChapterCondition);
	}
    
}
