package com.shangde.edu.cms.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.common.action.RandomCodeAction;
import com.shangde.common.exception.CommException;
import com.shangde.common.util.KeyWordFilter;
import com.shangde.common.util.PreventInfusion;
import com.shangde.common.util.Result;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.tk.condition.QueryTaskCusCondition;
import com.shangde.edu.tk.domain.Task;
import com.shangde.edu.tk.domain.TaskCus;
import com.shangde.edu.tk.service.ITaskCus;

/**  
 * 前台评论接口，支持静态html的ajax调用
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CommentWebAction extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 评论服务对象
	 */
	private IComment commentService;
	
	private ICourse courseService;
	
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;
	
	private ITaskCus taskCusService;
	
	/**
	 * 评论实体
	 */
	private Comment comment = new Comment();
	
	/**
	 * 评论查询条件
	 */
	private QueryCommentCondition queryCommentCondition;
	
	/**
	 * 评论来源list
	 */
	private List<Commentfrom> commentfromList = new ArrayList<Commentfrom>();
	
	/**
	 * 评论list
	 */
	private List<Comment> commentList=new ArrayList<Comment>();
	
	private String sourceURL;
	
	private String sourceName;
	
	private String isLogin = "false";
	
	private String confirmCode;
	
	private String returnMessage;
	
	private boolean needConfirmCode = true;
	
	/**
	 * 保存评论
	 * @return String
	 * @thorows Exception
	 */
	public String saveComment(){
		try {
			String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
			if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
				returnMessage = "confirmCommentFail";
				return showCommentList();
			}
			if(checkSqlInj(comment)) {
				returnMessage = "cmtDangerWord";
				return showCommentList();
			}
			
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			
			int userId = getLoginUserId();
			if(userId == 0) {
				comment.setCheckmanType(0);
			} else {
				Customer customer = customerService.getCustomerById(userId);
				comment.setVisitorName(customer.getEmail());
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCmtInfo(comment.getCmtInfo().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			comment.setGrade(3);
			comment.setCheckState(0);
			comment.setCmtInfo(KeyWordFilter.doFilter(comment.getCmtInfo()));
			commentService.addComment(comment);
			
			//第一次评论课程任务
			if(comment.getCfId() == 1){
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_EVALUATECOURSE);
				
				TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
				
				if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
			}
			//第一次评论课程任务结束
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "reshowCommentList";
	}
	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String showAdviceList() {
		if(getLoginUserId() != 0) {
			isLogin = "true";
		}
		setPage(commentService.getAdviceList(getQueryCommentCondition()));
		List<Comment> list = getPage().getPageResult();
		for(int i=0; i<list.size(); i++) {
			Comment cmt = commentService.getReplyById(list.get(i).getCmtId());
			list.get(i).setSourceObject(cmt);
		}
		setPageUrlParms();
		return "answer";
	}
	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String getAdviceList4ajax() {
		setPage(commentService.getAdviceList(getQueryCommentCondition()));
		this.setResult(new Result(false, JsonUtil.getJsonString4JavaCollection(getPage().getPageResult()), "", null));
		return "json";
	}
	
	/**
	 * 显示意见建议列表
	 * @return
	 */
	public String getLastAdviceByCus() {
		comment = commentService.getLastAdviceByCus(getLoginUserId());
		if(comment != null) {
			setResult(new Result(false, "success", "", comment.getCmtInfo()));
		} else {
			setResult(new Result(false, "failure", "", null));
		}
		return "json";
	}
	
	/**
	 * 添加意见建议列表
	 * 意见建议评论来源ID为5
	 * 资源ID为1
	 * @return
	 */
	public String saveAdvice() {
		String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
		if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
			returnMessage = "confirmAdviceFail";
			return showAdviceList();
		}
		int userId = getLoginUserId();
		if(userId != 0) {
			Customer customer = customerService.getCustomerById(userId);
			comment.setVisitorName(customer.getEmail());
			comment.setCheckmanId(userId);
			comment.setCheckmanType(1);
		}
		comment.setCfId(5);
		comment.setSourceId(1);
		comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
		comment.setGrade(3);
		commentService.addComment(comment);
		return "reanswer";
	}
	
	/**
	 * 保存评论
	 * @return String
	 * @thorows Exception
	 */
	public String saveComment4ajax(){
		try {
			String sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME).toString();
			if(confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode)) {
				setResult(new Result(false, "confirmCommentFail", "", null));
				return "json";
			}
			if(checkSqlInj(comment)) {
				setResult(new Result(false, "CommentDangerWord", "", null));
				return "json";
			}
			
			comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
			comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
			
			int userId = getLoginUserId();
			if(userId == 0) {
				comment.setCheckmanType(0);
			} else {
				Customer customer = customerService.getCustomerById(userId);
				comment.setVisitorName(customer.getEmail());
				comment.setCheckmanId(userId);
				comment.setCheckmanType(1);
			}
			comment.setCmtInfo(comment.getCmtInfo().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>"));
			comment.setGrade(3);
			comment.setCheckState(0);
			comment.setCmtInfo(KeyWordFilter.doFilter(comment.getCmtInfo()));
			commentService.addComment(comment);
			
			//第一次评论课程任务
			if(comment.getCfId() == 1){
				QueryTaskCusCondition queryTaskCusCondition = new QueryTaskCusCondition();
				queryTaskCusCondition.setCusId(userId);
				queryTaskCusCondition.setKeyWord(Task.TASK_KEY_EVALUATECOURSE);
				
				TaskCus tc = taskCusService.getTaskCusByKey(queryTaskCusCondition);
				
				if(tc != null && tc.getIsComplete() == 0){//若果未完成则设置完成
					tc.setIsComplete(1);
					taskCusService.updateTaskCus(tc);
				}
			}
			//第一次评论课程任务结束
			
			setResult(new Result(false, "success", "", null));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 添加意见建议列表
	 * 意见建议评论来源ID为5
	 * 资源ID为1
	 * @return
	 */
	public String saveAdvice4ajax() {
		Object sessionConfirmCode = servletRequest.getSession().getAttribute(RandomCodeAction.RAND_CODE_NAME);
		if(needConfirmCode && sessionConfirmCode != null && (sessionConfirmCode != null && (confirmCode == null  ||  !confirmCode.equals(sessionConfirmCode.toString())))) {
			setResult(new Result(false, "confirmAdviceFail", "", null));
			return "json";
		}
		if(checkSqlInj(comment)) {
			setResult(new Result(false, "adviceDangerWord", "", null));
			return "json";
		}
		comment.setCmtInfo(PreventInfusion.xssEncode(comment.getCmtInfo()));
		comment.setVisitorName(PreventInfusion.xssEncode(comment.getVisitorName()));
		int userId = getLoginUserId();
		if(userId != 0) {
			Customer customer = customerService.getCustomerById(userId);
			comment.setVisitorName(customer.getEmail());
			comment.setCheckmanId(userId);
			comment.setCheckmanType(1);
		}
		comment.setCfId(5);
		comment.setSourceId(1);
		comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
		comment.setGrade(3);
		commentService.addComment(comment);
		setResult(new Result(false, "success", "", null));
		return "json";
	}
	
	private boolean checkSqlInj(Comment cmt) {
		if(PreventInfusion.sql_inj(cmt.getCmtInfo()) || 
				PreventInfusion.sql_inj(cmt.getVisitorName())) {
			return true;
		}
		return false;
	}

	/**
	 * 根据资源id分页列表
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String getCommentListBySource()throws CommException, IOException{
		try {
			getQueryCommentCondition().setPageSize(7);
			setPage(commentService.getCommentListBySource(getQueryCommentCondition()));
			List<Integer> countList = commentService.getGradeCountBySource(getQueryCommentCondition());
			String newComment = commentService.getNewCommentBySource(getQueryCommentCondition());
			
			if(getPage() != null) {
				commentList = getPage().getPageResult();
			}
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			getPage().setPageSize(7);
			map.put("cfId", "" + queryCommentCondition.getCfId());
			map.put("sourceId", "" + queryCommentCondition.getSourceId());
			
			if(getPage() != null) {
				map.put("totalRecord", "" + getPage().getTotalRecord());
				map.put("totalPage", "" + getPage().getTotalPage());
				map.put("currentPage", "" + getPage().getCurrentPage());
				map.put("pageSize", "" + getPage().getPageSize());
			}
			if(countList != null && countList.size() > 2) {
				map.put("niceGrade", "" + countList.get(2));
				map.put("normalGrade", "" + countList.get(1));
				map.put("badGrade", "" + countList.get(0));
				map.put("newComment", newComment);
			}
			list.add(map);
			
			if(commentList != null) {
				for(int i=0; i < commentList.size(); i++) {
					map = new HashMap<String, String>();
					Comment comment = commentList.get(i);
					map.put("cmtId", "" + comment.getCmtId());
					map.put("cmtParentId", "" + comment.getCmtParentId());
					map.put("cmtInfo", comment.getCmtInfo());
					map.put("cfId", "" + comment.getCfId());
					map.put("checkState", "" + comment.getCheckState());
					map.put("createTime", format.format(comment.getCreateTime()));
					map.put("updateTime", format.format(comment.getUpdateTime()));
					map.put("grade", "" + comment.getGrade());
					if(comment.getCheckmanType() == 1) {
						Customer customer = customerService.getCustomerById(comment.getCheckmanId());
						if(customer != null) {
							map.put("checkmanName", customer.getCusName()==null?customer.getEmail():customer.getCusName());
							map.put("photo", customer.getPhoto()==null||customer.getPhoto()==""?"default.jpg":customer.getPhoto());
						} else {
							map.put("checkmanName", "嗨学网会员");
							map.put("photo", "default.jpg");
						}
					} else {
						map.put("checkmanName", comment.getVisitorName());
						map.put("photo", "default.jpg");
					}
					map.put("sourceId", "" + comment.getSourceId());
					list.add(map);
				}
			}
    		setResult(new Result<List<Map<String, String>>>(true, "返回成功!", null, list));
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}

	/**
	 * 根据资源id分页列表
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String showCommentList()throws CommException, IOException{
		try {
			if(getLoginUserId() != 0) {
				isLogin = "true";
			}
			getQueryCommentCondition().setCfId(comment.getCfId());
			getQueryCommentCondition().setSourceId(comment.getSourceId());
			
			getQueryCommentCondition().setPageSize(8);
			setPage(commentService.getCommentListBySource(getQueryCommentCondition()));
			setPageUrlParms();
			getPage().setPageSize(8);
			
			if(getQueryCommentCondition().getCfId() == 1) {
				Course course = courseService.getCourseById(getQueryCommentCondition().getSourceId());
				sourceName = course.getTitle(); 
			}
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "showCommentList";
	}
	
	public String getCmtCount() {
		try {
    		setResult(new Result<Integer>(true, "!", null, commentService.getCmtCount(getQueryCommentCondition())));
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	public void sendMessage(String message) throws IOException {
		getServletResponse().setCharacterEncoding("utf-8");
		getServletResponse().getWriter().write(message);
	}
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	public QueryCommentCondition getQueryCommentCondition() {
		if(queryCommentCondition == null) {
			queryCommentCondition = new QueryCommentCondition();
		}
		return queryCommentCondition;
	}
	public void setQueryCommentCondition(QueryCommentCondition queryCommentCondition) {
		this.queryCommentCondition = queryCommentCondition;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		this.sourceURL = sourceURL;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public String getConfirmCode() {
		return confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}

	public String getReturnMessage() {
		return returnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public String getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(String isLogin) {
		this.isLogin = isLogin;
	}

	public List<Commentfrom> getCommentfromList() {
		return commentfromList;
	}

	public void setCommentfromList(List<Commentfrom> commentfromList) {
		this.commentfromList = commentfromList;
	}

	public boolean isNeedConfirmCode() {
		return needConfirmCode;
	}

	public void setNeedConfirmCode(boolean needConfirmCode) {
		this.needConfirmCode = needConfirmCode;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public ITaskCus getTaskCusService() {
		return taskCusService;
	}

	public void setTaskCusService(ITaskCus taskCusService) {
		this.taskCusService = taskCusService;
	}
}
