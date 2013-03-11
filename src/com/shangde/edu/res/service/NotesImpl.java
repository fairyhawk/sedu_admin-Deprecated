package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.condition.QueryNotesCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * Notes对象操作实现类
 * User: guoqiang.liu
 * Date: 2010-08-09
 */
@SuppressWarnings("unchecked")
public class NotesImpl extends BaseService implements INotes{
    public java.lang.Integer addNotes(Notes notes) {
return simpleDao.createEntity("Notes_NS.createNotes",notes);
    }

    public void delNotesById(int noteId){
        simpleDao.deleteEntity("Notes_NS.deleteNotesById",noteId);
    }

    public void updateNotes(Notes notes) {
        simpleDao.updateEntity("Notes_NS.updateNotes",notes);
    }

    public Notes getNotesById(int noteId) {
        return simpleDao.getEntity("Notes_NS.getNotesById",noteId);
    }

    public List<Notes> getNotesList(QueryNotesCondition queryNotesCondition) {
        return simpleDao.getForList("Notes_NS.getNotesList",queryNotesCondition);
    }

	public PageResult getNotesListByCondition(
			PageQuery queryNotesCondition) {
		return simpleDao.getPageResult("Notes_NS.getNotesListByCondition", "Notes_NS.getNotesCountByCondition", queryNotesCondition);
	}

	public Notes getNotesByPoint(QueryNotesCondition queryNotesCondition) {
		return simpleDao.getEntity("Notes_NS.getNotesByPoint",queryNotesCondition);
	}

	public PageResult getNotesListByCourse(PageQuery queryNotesCondition) {
		return simpleDao.getPageResult("Notes_NS.getNotesListByCourse", "Notes_NS.getNotesCountByCourse", queryNotesCondition);
	}

	public void delNotesByCusId(int cusId) {
		 simpleDao.deleteEntity("Notes_NS.deleteNotesByCusId",cusId);
	}
	
	public void deleteNotesByPointId(int pointId){
		simpleDao.deleteEntity("Notes_NS.deleteNotesByPointId", pointId);
	}

	public PageResult getNotesListByPointId(PageQuery queryNotesCondition) {
		return simpleDao.getPageResult("Notes_NS.getNotesListByPointId", "Notes_NS.getNotesCountByPointId", queryNotesCondition);
	}

	public Integer delBathNotesByCusIds(String cusIds) {
		 return simpleDao.delete("Notes_NS.deleteBathNotesByCusIds",cusIds);
	}
}
