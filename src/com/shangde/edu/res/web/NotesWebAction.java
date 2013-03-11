package com.shangde.edu.res.web;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.res.condition.QueryNotesCondition;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.service.INotes;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
@SuppressWarnings("serial")
public class NotesWebAction extends CommonAction {
	
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
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	
	/**
	 * 课程列表
	 */
	private List<Course> courseList;
	
	/**
	 * 用户服务对象
	 */
	
	private ICustomer customerService;
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 课程分类listDTOList
	 */
	private List<CourseSortListDTO> courseSortListDTOList;
	/*-----------------宁肖添加变量-----------------------*/
	/**
	 * 笔记实体
	 */
	private Notes videonotes;
	 /**
	  * 笔记的ID来修改指定笔记
	  */
    public int noteId;
    /*-----------------宁肖添加变量-----------------------*/
	/**
	 * 根据知识点获取笔记
	 * @param pointId
	 * @return
	 */
	public Notes getNoteByPoint(int pointId) {
		getQueryNotesCondition().setCusId(this.getLoginUserId());
//		getQueryNotesCondition().setCusId(347);
		getQueryNotesCondition().setPointId(pointId);
		return notesService.getNotesByPoint(getQueryNotesCondition());
	}

	/**
	 * 根据知识点获取笔记
	 * @param pointId
	 * @return
	 */
	public Map<String, Object> getNotesListByPoint(int pointId, int pageNo, int pageSize) {
		getQueryNotesCondition().setPageSize(pageSize);
		getQueryNotesCondition().setCurrentPage(pageNo);
		getQueryNotesCondition().setPointId(pointId);
		getQueryNotesCondition().setCusId(getLoginUserId());
//		getQueryNotesCondition().setCusId(347);
		
		PageResult pr = notesService.getNotesListByPointId(getQueryNotesCondition());
		for(int i=pr.getPageResult().size()-1; i>=0; i--) {
			((Notes)pr.getPageResult().get(i)).setFlexIndex(i+1);
			if(i != 0) {
				((Notes)pr.getPageResult().get(i-1)).setNextNotes((Notes)pr.getPageResult().get(i));
			}
		}
		
		Kpoint kPoint = kpointService.getKpointById(pointId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageResult", pr);
		map.put("kPoint", kPoint);
		return map;
	}
	
	/**
	 * 增加或修改Notes
	 * @param notes
	 * @author ZhangDong
	 * @return Note ID
	 */
	public void saveOrUpdateNotes() throws Exception{
		Integer id = null;
		try {
			if(getVideonotes() != null){
				if(getVideonotes().getNoteId() == 0){
					getVideonotes().setUpdateTime(new Date());
					// getVideonotes().setCusId(347);
					 getVideonotes().setCusId(getLoginUserId());
					id = notesService.addNotes(getVideonotes());
				}else{
					getVideonotes().setUpdateTime(new Date());
					notesService.updateNotes(getVideonotes());
					id = getVideonotes().getNoteId();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Post_outObj(id.toString());
	}
	
	/**
	 * 根据id获取笔记
	 * @param notes
	 * @return Note
	 */
	public void getNotesById(){
		try {
			JSONObject jo = JSONObject.fromObject(this.notesService.getNotesById(getNoteId()));
			System.out.println(jo.toString());
			Post_outObj(jo.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据id获取笔记
	 * @param notes
	 * @return Note
	 */
	public Notes getNotesWithNextById(Map<String, Integer> flexParam){
		Notes notes = null;
		try {
			if(flexParam != null){
				int noteId = flexParam.get("noteId");
				int pointId = flexParam.get("pointId");
				getQueryNotesCondition().setPointId(pointId);
				getQueryNotesCondition().setCusId(getLoginUserId());
//				getQueryNotesCondition().setCusId(347);
				List<Notes> notesList = notesService.getNotesList(getQueryNotesCondition());
				for(int i=0; i<notesList.size(); i++) {
					if(notesList.get(i).getNoteId() == noteId) {
						notes = notesList.get(i);
						if(i<notesList.size()-1) {
							notes.setNextNotes(notesList.get(i+1));
							return notes;
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return notes;
	}
	
	/**
	 * 
	 * @param notes
	 * @author ZhangDong
	 * @return Note ID
	 */
	public List<Notes> getNotesByPointId(int pointId){
		try {
			getQueryNotesCondition().setPointId(pointId);
			getQueryNotesCondition().setCusId(getLoginUserId());
			return notesService.getNotesList(getQueryNotesCondition());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//2010-08-27 Del By ZhangDong Start
//	/**
//	 * 修改笔记
//	 * @return String
//	 * @thorows Exception
//	 */
//	public String updateNotes(){
//		try {
//			notes.setUpdateTime(new Date());
//			notesService.updateNotes(notes);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return ERROR;
//		}
//		return "changeSuccess";
//	}
	
//	/**
//	 * 添加笔记
//	 * @return String
//	 * @thorows Exception
//	 */
//	public Integer addNotes(Notes notes){
//		Integer id = null;
//		try {
//			//添加学员主键，待登陆做完后补齐
//			if(notes != null){
//				notes.setUpdateTime(new Date());
//				notes.setCusId(getLoginUserId());
//				id = notesService.addNotes(notes);
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return id;
//	}
//2010-08-27 Del By ZhangDong End
	
	/**
	 * 删除笔记
	 * @return String
	 * @thorows Exception
	 */
	public void deleteNotes() throws Exception{
		String jo ;
		try {
			if(ids != null) {
				for(int i=0; i<ids.length; i++) {
					notesService.delNotesById(ids[i]);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		 jo = "false";
		}
		 jo = "true";
		Post_outObj(jo.toString());
	}
	
	public void deleteNotes(int noteId) {
		try {
			notesService.delNotesById(noteId);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询
	 * @return String
	 * @thorows Exception
	 */
	public String showNotesList() {
		try {
			setPage(notesService.getNotesListByCondition(getQueryNotesCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "list";
	}

	/**
	 * 我的笔记
	 * @return String
	 * @thorows Exception
	 */
	public String showMyNotes() {
		try {
//			notesService.getNotesListByCus(getLoginUserId());
			int userId = getLoginUserId();
			this.setCourseSortListDTOList(userId);
			
			getQueryNotesCondition().setCusId(getLoginUserId());
			if(notes.getNoteId()!=0){
				getQueryNotesCondition().setNoteId(notes.getNoteId());
			}
			setPage(notesService.getNotesListByCondition(getQueryNotesCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "showMyNotes";
	}
	
	/**
	 * 设置左侧工具栏
	 * @param userId
	 */
	protected void setCourseSortListDTOList(int userId){
		Customer customer = customerService.getCustomerById(userId);
		
		if(customer.getIsComplete() != 1 ||(customer.getIsComplete() == 1 && customerService.isComplete(userId) >= 0)){
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}
		
		if(userId != 0){
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}
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
			e.printStackTrace();
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

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public INotes getNotesService() {
		return notesService;
	}
	/*-----宁肖修改-----------------发送流的方式-------------------------------------------------*/
    /**
    * 将封装好的JSON数据以流的形式发送到客户端
    */
	public void Post_outObj(String json) throws Exception{
    	try {
    	getServletResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out = getServletResponse().getWriter();//获取页面输出流
		out.println(json.toString());
		out.flush();
		out.close();
    	} catch (Exception e) {
		}
    }
	/*-----宁肖修改-----------------Get/Set变量值-------------------------------------------------*/
	public Notes getVideonotes() {
		return videonotes;
	}

	public void setVideonotes(Notes videonotes) {
		this.videonotes = videonotes;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	
}

