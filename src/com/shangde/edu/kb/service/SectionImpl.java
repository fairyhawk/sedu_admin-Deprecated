package com.shangde.edu.kb.service;

import java.util.List;
import com.shangde.edu.kb.domain.Section;
import com.shangde.edu.kb.condition.QuerySectionCondition;
import com.shangde.common.service.BaseService;

/**
 * Section操作对象实现
 * User: guoqiang.liu
 * Date: 2010-12-27
 */
@SuppressWarnings("unchecked")
public class SectionImpl extends BaseService implements ISection{
    public java.lang.Integer addSection(Section section) {
return simpleDao.createEntity("Section_NS.createSection",section);
    }

    public void delSectionById(int sId){
        simpleDao.deleteEntity("Section_NS.deleteSectionById",sId);
    }

    public void updateSection(Section section) {
        simpleDao.updateEntity("Section_NS.updateSection",section);
    }

    public Section getSectionById(int sId) {
        return simpleDao.getEntity("Section_NS.getSectionById",sId);
    }

    public List<Section> getSectionList(QuerySectionCondition querySectionCondition) {
        return simpleDao.getForList("Section_NS.getSectionList",querySectionCondition);
    }

	public List<Section> getSectionByList(QuerySectionCondition querySectionCondition) {
	    return simpleDao.getForList("Section_NS.getSectionByList",querySectionCondition);
	}
    
}
