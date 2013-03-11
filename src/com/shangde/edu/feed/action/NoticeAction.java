/**
 * 
 */
package com.shangde.edu.feed.action;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.condition.QueryNoticeCondition;
import com.shangde.edu.feed.domain.CourseCategory;
import com.shangde.edu.feed.domain.Notice;
import com.shangde.edu.feed.service.ICourseCategory;
import com.shangde.edu.feed.service.INotice;

/**
 * 公告管理Action业务处理
 * 
 * @author Libg
 * 
 */
public class NoticeAction extends CommonAction {

	private static Logger logger = LoggerFactory.getLogger(NoticeAction.class);

	/** 服务接口 */
	private INotice feedNoticeService;
	private ICourseCategory courseCategoryService;

	/** domain接口 */
	private Notice notice;

	/** 查询domain接口 */
	private QueryNoticeCondition queryNoticeCondition;

	/** 常量 */
	private String jsonResult;// json返回值
	private Integer id;
	private Integer status;// 状态

	private String msg;// 消息提示信息

	/** 集合对象 */
	private List<Notice> noticeList;
	private List<CourseCategory> courseCategoryList;

	
	/**
	 * 初始化课程分类列表
	 */
	private void initCourseCategoryList(){
		courseCategoryList = courseCategoryService.queryList();
	}
	/**
	 * 到添加页面
	 * 
	 * @author Libg
	 * @return
	 */
	public String toNoticeAdd() {

		initCourseCategoryList();
		return "notice_add";
	}

	/**
	 * 执行添加操作
	 * 
	 * @return
	 */
	public String doNoticeAdd() {

		try {
			Date now = new Date();
			notice.setPubDate(now);
			notice.setModified(now);
			
			notice.setStatus(1);//默认值
			if (feedNoticeService.addNotice(notice) > 0) {
				msg = "公告添加成功";
			} else {
				msg = "公告添加失败";
			}
		} catch (Exception e) {
			logger.error("添加公告失败", e);
		}

		return "msg";
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String toUpdateById() {

		initCourseCategoryList();
		
		notice = feedNoticeService.getNoticeById(id);

		return "notice_edit";
	}

	/**
	 * 执行修改操作
	 * 
	 * @return
	 */
	public String doNoticeUpdate() {

		try {
			Date now = new Date();
			notice.setModified(now);
			if (feedNoticeService.updateNotice(notice) > 0) {
				msg = "公告修改成功";
			} else {
				msg = "公告修改失败";
			}
		} catch (Exception e) {
			logger.error("添加修改失败", e);
		}

		return "msg";
	}

	/**
	 * 执行修改操作,修改状态
	 * 
	 * @return
	 */
	public String doUpdateStatusById() {

		initCourseCategoryList();
		try {
			jsonResult = "0";
			this.getQueryNoticeCondition().setId(id);
			this.getQueryNoticeCondition().setStatus(status);
			
			if (feedNoticeService.updateStatusById(queryNoticeCondition) > 0) {
				jsonResult = "1";
			}
		} catch (Exception e) {
			logger.error("添加修改失败", e);
		}
		return "jsonResult";
	}

	/**
	 * 到公告列表页面
	 * 
	 * @author Libg
	 * @return
	 */
	public String toNoticeList() {

		initCourseCategoryList();
		int pageSize = 50;
		try {
			this.getQueryNoticeCondition().setPageSize(pageSize);
			//开始时间
			if(queryNoticeCondition.getStartTime() == null || queryNoticeCondition.getStartTime().equals("")){
				queryNoticeCondition.setStartTime(null);
			}
			//结束时间
			if(queryNoticeCondition.getEndTime() == null || queryNoticeCondition.getEndTime().equals("")){
				queryNoticeCondition.setEndTime(null);
			}
			
			setPage(feedNoticeService.getNoticePageList(queryNoticeCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入公告列表出错", e);
		}

		return "notice_list";
	}
	
	/**
	 * 删除公告
	 * @return
	 */
	public String doDelete(){

		try {
			jsonResult = "0";
			if (feedNoticeService.deleteNotice(id) > 0) {
				jsonResult = "1";
			}
		} catch (Exception e) {
			logger.error("公告删除失败", e);
		}

		return "jsonResult";
	}

	/**
	 * @return the feedNoticeService
	 */
	public INotice getFeedNoticeService() {
		return feedNoticeService;
	}

	/**
	 * @param feedNoticeService
	 *            the feedNoticeService to set
	 */
	public void setFeedNoticeService(INotice feedNoticeService) {
		this.feedNoticeService = feedNoticeService;
	}

	/**
	 * @return the notice
	 */
	public Notice getNotice() {
		return notice;
	}

	/**
	 * @param notice
	 *            the notice to set
	 */
	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	/**
	 * @return the noticeList
	 */
	public List<Notice> getNoticeList() {
		return noticeList;
	}

	/**
	 * @param noticeList
	 *            the noticeList to set
	 */
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	/**
	 * @return the queryNoticeCondition
	 */
	public QueryNoticeCondition getQueryNoticeCondition() {

		if (queryNoticeCondition == null) {
			queryNoticeCondition = new QueryNoticeCondition();
		}

		return queryNoticeCondition;
	}

	/**
	 * @param queryNoticeCondition
	 *            the queryNoticeCondition to set
	 */
	public void setQueryNoticeCondition(
			QueryNoticeCondition queryNoticeCondition) {
		this.queryNoticeCondition = queryNoticeCondition;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the courseCategoryService
	 */
	public ICourseCategory getCourseCategoryService() {
		return courseCategoryService;
	}

	/**
	 * @param courseCategoryService the courseCategoryService to set
	 */
	public void setCourseCategoryService(ICourseCategory courseCategoryService) {
		this.courseCategoryService = courseCategoryService;
	}

	/**
	 * @return the courseCategoryList
	 */
	public List<CourseCategory> getCourseCategoryList() {
		return courseCategoryList;
	}

	/**
	 * @param courseCategoryList the courseCategoryList to set
	 */
	public void setCourseCategoryList(List<CourseCategory> courseCategoryList) {
		this.courseCategoryList = courseCategoryList;
	}
	/**
	 * @return the jsonResult
	 */
	public String getJsonResult() {
		return jsonResult;
	}
	/**
	 * @param jsonResult the jsonResult to set
	 */
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

}
