package com.shangde.edu.res.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.res.condition.QueryNotesCondition;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.service.INotes;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NotesAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(NotesAction.class);
	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	
	/**
	 * 笔记实体
	 */
	private Notes notes = new Notes();
	
	/**
	 * 笔记查询条件
	 */
	private QueryNotesCondition queryNotesCondition;
	
	/**
	 * 笔记列表
	 */
	private List<Notes> noteList = new ArrayList<Notes>();
	
	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 添加笔记
	 * @return String
	 * @thorows Exception
	 */
	public String addNotes(){
		try {
			//添加学员主键，待登陆做完后补齐
			
			notes.setUpdateTime(new Date());
			notesService.addNotes(notes);
		} catch(Exception e) {
			logger.error("NotesAction.addNotes", e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 修改笔记
	 * @return String
	 * @thorows Exception
	 */
	public String updateNotes(){
		try {
			notes.setUpdateTime(new Date());
			notesService.updateNotes(notes);
		} catch(Exception e) {
			logger.error("NotesAction.updateNotes", e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 删除笔记
	 * @return String
	 * @thorows Exception
	 */
	public String deleteNotes(){
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					notesService.delNotesById(ids[i]);
				}
			}
		} catch(Exception e) {
			logger.error("NotesAction.deleteNotes", e);
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showNotesList(){
		try {
			setPage(notesService.getNotesListByCondition(getQueryNotesCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			logger.error("NotesAction.showNotesList", e);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 初始化添加页面
	 * @return String
	 * @thorows Exception
	 */
	public String initAddNotes() {
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateNotes() {
		try {
			notes = notesService.getNotesById(notes.getNoteId());
		} catch(Exception e) {
			logger.error("NotesAction.initUpdateNotes", e);
			return ERROR;
		}
		return "updatePage";
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

	public QueryNotesCondition getQueryNotesCondition() {
		if(queryNotesCondition == null) {
			queryNotesCondition = new QueryNotesCondition();
		}
		return queryNotesCondition;
	}

	public void setQueryNotesCondition(QueryNotesCondition queryNotesCondition) {
		this.queryNotesCondition = queryNotesCondition;
	}

	public List<Notes> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<Notes> noteList) {
		this.noteList = noteList;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}
}
