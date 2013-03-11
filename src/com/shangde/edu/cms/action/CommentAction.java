package com.shangde.edu.cms.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.condition.QueryCommentCondition;
import com.shangde.edu.cms.domain.Comment;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.dto.ExportCommentDTO;
import com.shangde.edu.cms.service.IComment;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.msg.service.IMessage;
import com.shangde.edu.msg.service.IUserMsg;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CommentAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(CommentAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 评论服务对象
	 */
	private IComment commentService;
	
	/**
	 * 评论实体
	 */
	private Comment comment = new Comment();
	
	private Comment reComment=new Comment();
	
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

	/**
	 * id
	 */
	private String id;
	
	private IMessage messageService;// 消息服务

	private IUserMsg userMsgService;// 用户信息服务
	
	private ICustomer customerService;
	/**
	 * 是否隐藏
	 */
	private int isFreeze;
	/**
	 * 专业列表
	 */
	private List<Subject> subjectList=new ArrayList<Subject>();
	/**
	 * 专业service
	 */
	private ISubject subjectService;
	/**
	 * 导出excel名称
	 */
	private String excelName;
	/**
	 * 输入流
	 */
	private InputStream excelFile;
	
	/**
	 * 删除评论
	 * @return String
	 * @thorows Exception
	 */
	public String deleteComment(){
		try {
			commentService.delCommentById(comment.getCmtId());
			//userMsgService.delUserMsgById(id);
		}catch(Exception e) {
			logger.error("ColumnAction.deleteComment", e);
			return ERROR;
		}
		return "research";
	}

	

	/**
	 * 审核评论
	 * @return String
	 * @thorows Exception
	 */
	public String auditComment(){
		try {
			//根据id获取要通过审核的评论
			comment = commentService.getCommentById(comment.getCmtId());
			if(comment != null) {
				//将审核状态置为通过
				comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			}
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			commentService.updateComment(comment);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 根据查询条件查询评论列表并分页
	 * @return String
	 * @thorows Exception
	 */
	public String getCommentListBySearch() {
		try {
			//获取评论来源列表
			commentfromList = commentService.getCommentFromList();
			//根据评论内容搜索评论
			String info = getQueryCommentCondition().getCmtInfo();
			if(info != null&&!"".equals(info)) {	
				getQueryCommentCondition().setCmtInfo((URLDecoder.decode(info, "UTF-8")).trim());
			}
			setPage(commentService.getCommentListByCondition(getQueryCommentCondition()));
			setPageUrlParms();
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "search";
	}
	
	/**
	 * 显示建议列表
	 * @return
	 */
	public String showAdviceList() {
		try {
			//准备查询条件
			String info = getQueryCommentCondition().getCmtInfo();
			if(info != null&&!"".equals(info)) {	
				getQueryCommentCondition().setCmtInfo((URLDecoder.decode(info, "UTF-8")).trim());
			}
			setPage(commentService.getAdviceListByCondition(getQueryCommentCondition()));
			setPageUrlParms();
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "showAdviceList";
	}
	
	/**
	 * 回复建议
	 * @return
	 */
	public String replyAdvice() {
		try {
			//回复建议的来源id是6 回复回复信息的来源id是5
			if(comment.getCmtInfo().indexOf("回复@")!=-1){
				comment.setCfId(5);
			}else{
				comment.setCfId(6);
			}
			//设置一些非空数据，无意义
			comment.setGrade(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			comment.setTopTime(new java.util.Date());
			comment.setCreateTime(new java.util.Date());
			comment.setUpdateTime(new java.util.Date());
			comment.setIsTop(0);
			comment.setIsFreeze(0);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			comment.setVisitorName(users.getUserName());
			int reId=commentService.addComment(comment);
			//将被回复的建议的审核状态置为通过。
			comment = commentService.getCommentById(comment.getSourceId());
			users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			comment.setIsTop(1);
			commentService.updateComment(comment);
			String abs="";
			
			//给提意見者發送信息

			User sender = new User();			
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			Customer cus=customerService.getCustomerById(comment.getCheckmanId());
			MessageRemindBean mrb=new MessageRemindBean();
			mrb.setUrl("管理员回复了您的意见[<a href=\"../cus/pblimit!getMyAdvice.action?comment.cmtId="
							+ comment.getCmtId()+ "&rid="+reId+"\">");
			mrb.setText(comment.getCmtInfo());
			mrb.setReceiver(cus);
			mrb.setRid(reId);
			mrb.setTid(comment.getCmtId());
			mrb.setSender(sender);
			mrb.setType(1);
			MessageRemindUtil.sendMessageRemind(mrb);
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	//管理员删除用户提出的建议
	public String deleteAdvice(){
		try {
			//先删除建议的回复，再删除建议
			commentService.delAdviceReply(comment.getCmtId());
			commentService.delCommentById(comment.getCmtId());
			//删除消息提醒
			MessageRemindUtil.delMessageRemindForTopic(1, comment.getCmtId());
		}catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "reshowAdviceList";
	}
	/**
	 * 初始化回复意见页面
	 * @return
	 */
	public String initReplyAdvice() {
		comment = commentService.getCommentById(comment.getCmtId());
		return "initReplyAdvice";
	}
	
	/**
	 * 初始化修改回复页面
	 * @return
	 */
	//public String initReplyUpdate() {
	//	comment = commentService.getReplyById(comment.getCmtId());
	//	comment.setSourceObject(commentService.getCommentById(comment.getSourceId()));
	//	return "initReplyUpdate";
	//}
	/**
	 * 
	 */
	public String showReplyList(){
		comment=commentService.getCommentById(comment.getCmtId());
		this.getQueryCommentCondition().setCfId(6);
		this.getQueryCommentCondition().setSourceId(comment.getCmtId());
		this.getQueryCommentCondition().setPageSize(10);
		PageResult pr=commentService.getCommentListBySource(queryCommentCondition);
		List<Comment> recomments=pr.getPageResult();
		for(Comment recomment:recomments){
			recomment.setCmtInfo(StringUtil.filterHTML(recomment.getCmtInfo()));
		}
		setPage(pr);
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(10);
		 }
		return "showReplyList";		
	}
	
	
	/**
	 * 修改回复
	 * @return
	 */
	public String replyUpdate() {
		//修改回复
		Comment rply = commentService.getCommentById(comment.getCmtId());
		rply.setCmtInfo(comment.getCmtInfo());
		User users = this.getLoginedUser();
		if(users==null) return "loginOut";
		rply.setAuthor(users.getLoginName());
		commentService.updateComment(rply);
		return "changeSuccess";
	}
	//管理员删除自己的回复
	public String deleteReply() {
		servletRequest=ServletActionContext.getRequest();
		String t=servletRequest.getParameter("t");
		reComment = commentService.getCommentById(comment.getCmtId());		
		
		commentService.delCommentById(reComment.getCmtId());
		if(reComment.getCfId()==6){
		//删除提醒消息
		MessageRemindUtil.delMessageRemindForReply(1, reComment.getCmtId());
		}
		this.getQueryCommentCondition().setCfId(6);
		this.getQueryCommentCondition().setSourceId(reComment.getSourceId());
		int count=commentService.getCmtCountBySource(this.getQueryCommentCondition());
		if(count<1){
			comment = commentService.getCommentById(reComment.getSourceId());
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			commentService.updateComment(comment);
		}		
		if(t!=null&&t.equals("2")){
			return "reshowReplyList";
		}else{
			return "reshowAdviceList";
		}		
	}
	
	/**  王超  留言开始*/
	
	/**
	 * 留言查询
	 * @author 王超
	 * @return
	 */
	public String showAdviceMsgList(){
		try {
			if(this.getQueryCommentCondition().getCmtInfo()!=null&&!this.getQueryCommentCondition().getCmtInfo().equals("")){
				this.getQueryCommentCondition().setCmtInfo(URLDecoder.decode(getQueryCommentCondition().getCmtInfo(), "UTF-8"));
			}
			if(this.getQueryCommentCondition().getStartTime()==null) {
				this.getQueryCommentCondition().setIsTop(-1);
				this.getQueryCommentCondition().setCheckState(-1);
			}
			subjectList = subjectService.getAllSubject();
			setPage(commentService.getAdviceMsgList(getQueryCommentCondition()));
			setPageUrlParms();
			// TODO
			return "adviceMsg";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("执行showAdviceMsgList错误！", e);
			return ERROR;
		}
	}
	
	/**
	 * 置顶建议
	 * @return
	 */
	public String topAdvice(){
		try {
			//修改回复
			int cmtId=Integer.parseInt(id);
			Comment rply = commentService.getCommentById(cmtId);
			rply.setIsTop(1);
			rply.setTopTime(new java.util.Date());
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			rply.setAuthor(users.getLoginName());
			commentService.updateComment(rply);
			this.setResult(new Result(true,null,null,null));
			return "json";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行topAdvice错误！", e);
			return "json";
		}
	}
	
	/**
	 * 隐藏建议
	 * @return
	 */
	public String freezeAdvice(){
		try {
			//修改回复
			int cmtId=Integer.parseInt(id);
			Comment rply = commentService.getCommentById(cmtId);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			rply.setAuthor(users.getLoginName());
			rply.setIsFreeze(isFreeze);
			commentService.updateComment(rply);
			return showAdviceMsgList();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行freezeAdvice错误！", e);
			return ERROR;
		}
	}
	
	/**
	 * 删除建议
	 * @return
	 */
	public String delAdvice(){
		try {
			//删除回复
			int cmtId=Integer.parseInt(id);
			//先删除建议的回复，再删除建议
			commentService.delAdviceReply(cmtId);
			commentService.delCommentById(cmtId);
			//删除消息提醒
			MessageRemindUtil.delMessageRemindForTopic(1,cmtId);
			return showAdviceMsgList();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行freezeAdvice错误！", e);
			return ERROR;
		}
	}
	
	public String adviceDetail(){
		try {
			comment = commentService.getCommentById(comment.getCmtId());
			this.getQueryCommentCondition().setSourceId(comment.getCmtId());
			this.getQueryCommentCondition().setPageSize(10);
			commentList=commentService.getCommentMsgListBySource(queryCommentCondition);
			 
			for(Comment recomment:commentList){
				recomment.setCmtInfo(StringUtil.filterHTML(recomment.getCmtInfo()));
			}
			return "adviceDetail";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行adviceDetail错误！", e);
			return ERROR;
		}
	}
	/**
	 * 删除建议回复
	 * @return
	 */
	public String delAdviceMsg(){
		try {
			comment = commentService.getCommentById(comment.getCmtId());
			deleteReply();
			if(comment.getCfId()==6)
			comment=commentService.getCommentById(comment.getSourceId());
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			this.commentService.updateComment(comment);
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行delAdviceMsg错误！", e);
			return ERROR;
		}
	}
	/**
	 * 添加回复
	 * @return
	 */
	public String addAdvice(){
		try {
			Comment com=null;
			if(comment.getCmtInfo().indexOf("回复@")==-1){
				com=this.commentService.getCommentById(Integer.parseInt(id));
			}
			if(com!=null&&com.getCheckState()==1){
				System.out.println(com.getCmtId()+"该建议已被其他客服回复");
			}else{
				comment.setCmtId(null);
				replyAdvice();
			}
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行addAdvice错误！", e);
			return ERROR;
		}
	}
	
	public String replyReply(){
		try {
			comment.setCmtId(null);
			//回复建议的来源id是6 回复回复信息的来源id是5
			if(comment.getCmtInfo().indexOf("回复@")!=-1){
				comment.setCfId(5);
			}else{
				comment.setCfId(6);
			}
			//设置一些非空数据，无意义
			comment.setGrade(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			comment.setTopTime(new java.util.Date());
			comment.setCreateTime(new java.util.Date());
			comment.setUpdateTime(new java.util.Date());
			comment.setIsTop(0);
			comment.setIsFreeze(0);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			comment.setVisitorName(users.getUserName());
			int reId=commentService.addComment(comment);
			
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行replyReply错误！", e);
			return ERROR;
		}
	}
	/*
	public String replyReply(){
		try {
			comment.setCmtId(null);
			//回复建议的来源id是6
			comment.setCfId(6);
			//设置一些非空数据，无意义
			comment.setGrade(1);
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			comment.setTopTime(new java.util.Date());
			comment.setCreateTime(new java.util.Date());
			comment.setUpdateTime(new java.util.Date());
			comment.setIsTop(0);
			comment.setIsFreeze(0);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			comment.setVisitorName(users.getUserName());
			int reId=commentService.addComment(comment);
			
			//将被回复的建议的审核状态置为通过。
			comment = commentService.getCommentById(comment.getSourceId());
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			comment.setCheckState(Comment.CMT_CHECK_STATE_PASS);
			commentService.updateComment(comment);
			String abs="";
			
			//给提意見者發送信息
			if(comment.getCheckmanId()!=null){
				User sender = new User();			
				sender.setUserId(1);
				sender.setUserName("超级管理员");
				Customer cus=customerService.getCustomerById(comment.getCheckmanId());
				MessageRemindBean mrb=new MessageRemindBean();
				mrb.setUrl("管理员回复了您的意见[<a href=\"../cus/pblimit!getMyAdvice.action?comment.cmtId="
								+ comment.getCmtId()+ "&rid="+reId+"\">");
				mrb.setText(comment.getCmtInfo());
				mrb.setReceiver(cus);
				mrb.setRid(reId);
				mrb.setTid(comment.getCmtId());
				mrb.setSender(sender);
				mrb.setType(1);
				MessageRemindUtil.sendMessageRemind(mrb);
			}
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行replyReply错误！", e);
			return ERROR;
		}
	}*/
	/**
	 * 修改回复
	 * @return
	 */
	public String updateAdvice(){
		try {
			replyUpdate();
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行updateAdvice错误！", e);
			return ERROR;
		}
	}
	public String getReplyBySourceId(){
		try {
			commentList=this.commentService.getReplyBySourceId(Integer.parseInt(id));
			if(commentList!=null&&commentList.size()==1)
			this.setResult(new Result(true,null,null,commentList));
			else if(commentList==null||commentList.size()==0)
			this.setResult(new Result(false,"该评论没有回复,出现bug，请与技术人员联系",null,null));
			else if(commentList!=null&&commentList.size()>1)
			this.setResult(new Result(true,"该评论回复过多,请删除多余回复，请与技术人员联系",null,commentList));
			return "json";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行getReplyBySourceId错误！", e);
			this.setResult(new Result(false,"出现bug，请与技术人员联系",null,null));
			return "json";
		}
	}
	
	public String delReplyAdvice(){
		try { 
			commentService.delAdviceReply(comment.getCmtId());
			comment = commentService.getCommentById(comment.getCmtId());
			comment.setCheckState(Comment.CMT_CHECK_STATE_NEW);
			User users = this.getLoginedUser();
			if(users==null) return "loginOut";
			comment.setAuthor(users.getLoginName());
			commentService.updateComment(comment);
			comment.setCmtId(Integer.parseInt(id));
			return adviceDetail();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行getReplyBySourceId错误！", e);
			return ERROR;
		}
	}
	
	public String exportComment(){
		try {
			List<ExportCommentDTO> exportCommentList=new ArrayList<ExportCommentDTO>();
			exportCommentList=this.commentService.exportComment(queryCommentCondition);
			createExportExcel(exportCommentList);
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				setExcelName(URLEncoder.encode("留言查询导出"+sdf.format(new Date())+".xls", "UTF-8"));//IE浏览器
			 }else{
				 setExcelName(new String(("留言查询导出"+sdf.format(new Date())+".xls").getBytes(),"iso-8859-1"));
			 }
			return "exportComment";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error("执行exportComment错误！", e);
			return ERROR;
		}
	}
	
	public void createExportExcel(List <ExportCommentDTO>exportCommentList){
		
		try {
			// 设置表头
			String[] headName = { "序号","提问时间","提问者","提问内容","所属项目","是否置顶","是否回复","回复日期","回复时间","回复账号","回复内容"};
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			for(int i=0;i<exportCommentList.size();i++){
				row = sheet.createRow(i + 1);
				//序号
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getCmtId());
				//提问时间
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if(exportCommentList.get(i).getCreateTime()==null)
					cell.setCellValue("");
				else
				cell.setCellValue(sdf1.format(exportCommentList.get(i).getCreateTime()));
				//提问者
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getVisitorName());
				//提问内容
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getCmtInfo());
				//所属项目
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getSubjectName());
				//是否置顶
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getIsTop());
				//是否回复
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getIsReview());
				//回复日期
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if(exportCommentList.get(i).getReviewTime()==null)
				cell.setCellValue("");
				else
				cell.setCellValue(sdf.format(exportCommentList.get(i).getReviewTime()));
				//回复时间
				cell = row.createCell((short) 8);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if(exportCommentList.get(i).getReviewTime()==null)
					cell.setCellValue("");
				else
				cell.setCellValue(sdf1.format(exportCommentList.get(i).getReviewTime()));
				//回复账号
				cell = row.createCell((short) 9);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getReviewName());
				//回复内容
				cell = row.createCell((short) 10);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(exportCommentList.get(i).getReviewInfo());
			}
			
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("执行createExportExcel错误！", e);
		}
		
	}
	
	/**  王超  留言结束*/
	
	
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCommentService(IComment commentService) {
		this.commentService = commentService;
	}

	public List<Commentfrom> getCommentfromList() {
		return commentfromList;
	}

	public void setCommentfromList(List<Commentfrom> commentfromList) {
		this.commentfromList = commentfromList;
	}

	public IComment getCommentService() {
		return commentService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IMessage getMessageService() {
		return messageService;
	}

	public void setMessageService(IMessage messageService) {
		this.messageService = messageService;
	}

	public IUserMsg getUserMsgService() {
		return userMsgService;
	}

	public void setUserMsgService(IUserMsg userMsgService) {
		this.userMsgService = userMsgService;
	}

	public Comment getReComment() {
		return reComment;
	}

	public void setReComment(Comment reComment) {
		this.reComment = reComment;
	}



	public int getIsFreeze() {
		return isFreeze;
	}



	public void setIsFreeze(int isFreeze) {
		this.isFreeze = isFreeze;
	}



	public List<Subject> getSubjectList() {
		return subjectList;
	}



	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}



	public ISubject getSubjectService() {
		return subjectService;
	}



	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}



	public String getExcelName() {
		return excelName;
	}



	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}



	public InputStream getExcelFile() {
		return excelFile;
	}



	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
}
