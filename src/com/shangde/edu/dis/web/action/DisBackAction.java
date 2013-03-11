package com.shangde.edu.dis.web.action;

import java.io.File;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.dis.condition.QueryDisAreaCondition;
import com.shangde.edu.dis.condition.QueryDiscussionCondition;
import com.shangde.edu.dis.condition.QueryTopicCondition;
import com.shangde.edu.dis.condition.QueryTopicReplyCondition;
import com.shangde.edu.dis.domain.DisArea;
import com.shangde.edu.dis.domain.DisWord;
import com.shangde.edu.dis.domain.Discussion;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.domain.Vote;
import com.shangde.edu.dis.service.IDisArea;
import com.shangde.edu.dis.service.IDisWord;
import com.shangde.edu.dis.service.IDiscussion;
import com.shangde.edu.dis.service.ITagsRelation;
import com.shangde.edu.dis.service.ITopic;
import com.shangde.edu.dis.service.ITopicReply;
import com.shangde.edu.dis.service.IUniqueRecord;
import com.shangde.edu.dis.service.IVote;
import com.shangde.edu.dis.service.IVoteLog;
import com.shangde.edu.dis.utils.SelectModel;
import com.shangde.edu.dis.utils.Utils;
import com.shangde.edu.dis.web.interceptor.KeyWordFilter;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class DisBackAction extends CommonAction{
	
	private static Logger log = Logger.getLogger(DisBackAction.class);
	
	/**
	 * 上传照片
	 */
	private List<File> newPhoto = new ArrayList<File>();

	private List<String> newPhotoFileName = new ArrayList<String>();

	private Map<String, String> photoPrams = new HashMap<String, String>();

	private String userInfoState = "";

	private String updateMessage = "";

	private String updateError = "";
	
	private static String DEFAULT_PIC = "http://import.highso.org.cn/test/upload/dis/discussion.gif";
	
	/* 讨论组业务 */
	private IDiscussion discussionService;
	private Discussion dis;
	private List<Discussion> disList;
	/* 讨论组区域业务 */
	private IDisArea disAreaService;
	private DisArea disArea;
	
	/* 标签关联业务 */
	private ITagsRelation tagsRelationService;
	
	/* 推荐唯一记录业务 */
	private IUniqueRecord uniqueRecordService;
	
	/* 讨论组查询条件 */
	private QueryDiscussionCondition queryDiscussionCondition;
	/* Subject */
	private ISubject subjectService;
	private List<Subject> subjectList;
	
	/*敏感词过滤*/
	private IDisWord disWordService;
	
	/* 话题 topic */
	private Topic topic;
	private ITopic topicService;
	private QueryTopicCondition queryTopicCondition;
	private List<Topic> topicList;
	
	/* 话题回复 */
	private ITopicReply replyService;
	
	private String srcCreateTime;
	private int id;
	private Integer disId;
	private Integer disAreaId;
	private Integer canReply;
	private int status;
	private int topicCusId;//发帖用户人id
	private int replyCusId;//回复用户人id
	
	/*敏感词*/
	private String word;

	private String createTime;
	private String replyTime;
	private Integer voteStatus;
	private Integer topStatus;
	private String content;
	private Customer customer;
	private Discussion discussion;
	/* 区域查询条件 */
	private QueryDisAreaCondition queryDisAreaCondition;
	/* 区域列表 */
	private List<DisArea> disAreaList = new ArrayList<DisArea>();
	
	private List<Discussion> discussionList;
	
	private ICustomer customerService;
	private IVote voteService;
	private IVoteLog voteLogService;
	private String jsonResult;
	private String ids;
	private int replyId;
	private String resMsg;
	
	
	private String startHH;//开始时间后缀 时分秒
	private String endHH;//开始结束时间后缀 时分秒
	private String modifyStartHH;//修改时间后缀 时分秒
	private String modifyEndHH;//修改结束时间后缀 时分秒
	
	private Utils utils;

	/**
	 * 后台管理员添加文章时指定customer.id
	 */
	private int cusId;
	
	/**
	 * 话题状态选项
	 */
	private List<SelectModel> statusTopicList;
	
	/**
	 * 话题类型选项
	 */
	private List<SelectModel> typeTopicList;
	
	/**
	 * 检索类型
	 */
	private List<SelectModel> searchCriteriaList;
	
	/**
	 * 小组类型选项
	 */
	private List<SelectModel> typeDisList;
	
	/**
	 * 小组状态
	 */
	private List<SelectModel> statusDisList;
	
	
	/**
	 * 检索条件选项,小组搜索时使用
	 */
	private List<SelectModel> searchCriteriaByDisList;
	
	
	
	
	/* 用户查询 */
	
	//~TODO: 临时 为初始化数据使用
	private static Map<Integer, String> areaMap = new HashMap<Integer, String>();
	static{
		areaMap.put(0, "学习交流与经验分享");
		areaMap.put(1, "灌水专区");
		areaMap.put(2, "意见与建议");
	}
	
	//~ 小组管理 ~
	
	/**
	 * 查询小组列表
	 * 
	 * @return
	 */
	public String getDisList() {
		
		//初始化专业（课程）
		initSubject();
		
		subjectList = subjectService.getAllSubject();
		this.setSession("subjectList", this.getSubjectList());
		
		this.getQueryDiscussionCondition().setPageSize(30);
		setPage(this.discussionService.getPageDiscussionList(this.getQueryDiscussionCondition()));
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(30);
		}
		return "dis_list";
	}
	

	/**
	 * 添加小组
	 * 
	 * @return
	 */
	public String toAddDis()
	{
		setSession("kindeditorSavePath", "dis/photo/");
		
		if (this.getSession("subjectList") == null)
		{
			subjectList = subjectService.getAllSubject();
			this.setSession("subjectList", this.getSubjectList());
		}
		return "dis_add";
	}


	public String addDis()
	{
		if (this.getSession("subjectList") == null)
		{
			subjectList = subjectService.getAllSubject();
			this.setSession("subjectList", this.getSubjectList());
		}
		this.subjectList = this.getSession("subjectList");
		for (Subject s : subjectList)
		{
			if (dis.getSubjectId() == s.getSubjectId())
			{
				dis.setSubject(s.getSubjectName());
			}
		}
		
		dis.setCreatetime(new Date());
		int disId = this.discussionService.addDiscussion(dis);
		dis.setId(disId);
		
		if (dis.getPicurl() == null && dis.getPicurl().equals(""))
		{
			dis.setPicurl(DEFAULT_PIC);
		}
		
		initDisArea(dis);
		
		return "success";
	}
	
	
	/**
	 * 修改小组
	 * 
	 * @return
	 */
	public String toEditDis()
	{
		setSession("kindeditorSavePath", "dis/photo/" + dis.getId());
		dis = this.getDiscussionService().getDiscussionById(dis.getId());
		return "dis_edit";
	}

	/**
	 * 修改小组信息
	 * @return
	 */
	public String editDis(){
		if (this.getSession("subjectList") == null)
		{
			subjectList = subjectService.getAllSubject();
			this.setSession("subjectList", this.getSubjectList());
		}
		this.subjectList = this.getSession("subjectList");
		for (Subject s : subjectList)
		{
			if (dis.getSubjectId() == s.getSubjectId())
			{
				dis.setSubject(s.getSubjectName());
			}
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//修改记录时，还保留原来的创建时间s
		try {
			dis.setCreatetime(sdf.parse(srcCreateTime));
		} catch (ParseException e) {
			log.error("后台修改小组基本信息--->类型解析格式化->出错", e);
		}
		
		this.discussionService.updateDiscussion(dis);
		dis.setId(dis.getId());
		
		return "to_dis_list";
	}
	
	/**
	 * 删除小组
	 * 
	 * @return
	 */
	public String delDis()
	{
		//TODO 根据小组类型删除小组
		return "topic_list";
	}
	
	
	//~ 话题管理 ~/
	/**
	 * 全部话题列表
	 */
	public String topicList()
	{
		//初始化专业（课程）
		initSubject();
		
		this.getQueryTopicCondition().setPageSize(30);
		setPage(topicService.getTopicDTOList(this.getQueryTopicCondition()));
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(30);
		}
		return "topic_list";
	}
	
	/**
	 * 初始化课程，并放入session中
	 */
	private void initSubject(){
		
		if(getSession("session_subjectList") == null){
			setSession("session_subjectList", subjectService.getAllSubject());
		}
	}
	
	
	//~ 初始化数据 ~

	/**
	 * 初始化小组
	 * @return
	 */
	public String initDis()
	{
		subjectList = subjectService.getAllSubject();
		for (Subject s : subjectList)
		{
			dis = new Discussion();
			dis.setCreatuser("highso");
			dis.setInflag(0);
			dis.setPicurl("discussion.gif");
			dis.setName(s.getSubjectName());
			dis.setFeatures(s.getSubjectName());
			dis.setIntroduction(s.getSubjectName());
			dis.setSubject(s.getSubjectName());
			dis.setSubjectId(s.getSubjectId());
			dis.setType(0);
			dis.setStatus(0);
			dis.setCreatetime(new Date());
			
			discussionService.addDiscussion(dis);
		}
		
		return null;
		
	}

	/**
	 * 根据小组初始化小组区域
	 * @param dis
	 */
	public void initDisArea(Discussion dis)
	{
		User user = this.getLoginedUser();
		for (Map.Entry<Integer, String> m : areaMap.entrySet())
		{
			disArea = new DisArea();
			disArea.setDisId(dis.getId());
			disArea.setIntroduction(dis.getName());
			disArea.setName(m.getValue());
			disArea.setSort(m.getKey());
			disArea.setCreateTime(new Date());
			disArea.setSubjectId(dis.getSubjectId());
			disArea.setUserId(user.getUserId());
			disAreaService.addDisArea(disArea);
		}
	}

	/**
	 * 初始化区域
	 * @return
	 */
	public String initArea()
	{
		disList = discussionService.getAllDiscussion();
		for (Discussion dis : disList)
		{
			for (Map.Entry<Integer, String> m : areaMap.entrySet())
			{
				disArea = new DisArea();
				disArea.setDisId(dis.getId());
				disArea.setIntroduction(dis.getName());
				disArea.setName(m.getValue());
				disArea.setSort(m.getKey());
				disArea.setCreateTime(new Date());
				disArea.setSubjectId(dis.getSubjectId());
				disAreaService.addDisArea(disArea);
				disArea.setUserId(-1);
			}

		}
		
		return null;
	}
	

	
	/**
	 * 有后台管理调用
	 * @return
	 */
	public String showTopic(){
		
		topic = topicService.getBackTopicById(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			createTime = sdf.format(topic.getCreateTime());
			replyTime = sdf.format(topic.getReplyTime());
		}catch (Exception e) {
			log.error("后台查看话题时，时间类型格式转换出错", e);
		}
		
		discussion = discussionService.getDiscussionById(topic.getDisId());
		this.getQueryDisAreaCondition().setDisId(topic.getDisId());
		this.setDisAreaList(disAreaService.getDisAreaList(this.getQueryDisAreaCondition()));
		
		
		return "topic_show";
	}
	
	
	/**
	 * 由后台管理调用
	 * @return
	 */
	public String editTopic(){
		
		topic = topicService.getBackTopicById(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try{
			createTime = sdf.format(topic.getCreateTime());
			replyTime = sdf.format(topic.getReplyTime());
		}catch (Exception e) {
			log.error("后台修改话题时，时间类型格式转换出错", e);
		}
		
		discussion = discussionService.getDiscussionById(topic.getDisId());
		this.getQueryDisAreaCondition().setDisId(topic.getDisId());
		this.setDisAreaList(disAreaService.getDisAreaList(this.getQueryDisAreaCondition()));
		
		
		return "topic_edit";
	}
	
	/**
	 * 有后台管理调用
	 * @return
	 */
	public String doEditTopic(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		KeyWordFilter keyWordFilter = KeyWordFilter.getInstance(disWordService);
		try {
			
			/**
			 * 关键词过滤
			 */
			if(topic.getTitle() != null && topic.getTitle().length() > 0){
				topic.setTitle(keyWordFilter.doFilter(topic.getTitle()));
			}
			if(content != null && content.length() > 0){
				content = keyWordFilter.doFilter(content);
			}
			
			topic.setCanReply(canReply);
			topic.setDisId(disId);
			topic.setCreateTime(sdf.parse(createTime));
			topic.setReplyTime(sdf.parse(replyTime));
			topic.setIsVote(voteStatus);// 是否投票
			topic.setContent(content);// 内容
			topic.setIsTop(topStatus);// 是否置顶
			topic.setCustomer(customer);

			topicService.updateTopic(topic);
		} catch (Exception e) {
			log.error("后台修改话题时出错", e);
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除帖子(话题)
	 * 
	 * 修改话题状态，改为3 表示删除状态
	 * 
	 * @return
	 */
	public String delTopic(){
		
		//实际删除
		try {
			int i = topicService.delTopicById(id);
			//帖子删除成功，删除与之先关的所有信息，包括回复，投票
			if(i > 0){
				// 查询出本帖是否为投票贴
				Vote vote = voteService.getVoteByTopicId(id);
				//条件成立说明有投票
				if(vote != null){
					// 首先删除投票过的记录
					voteLogService.delVoteLogByVoteId(vote.getId());
					// 其次删除投票以及投票关联信息
					voteService.delVoteById(vote.getId());
				}
				Map<String, Integer> delTagsMap = new HashMap<String, Integer>();
				delTagsMap.put("objectId", id);
				delTagsMap.put("objectTypeId", 1);
				tagsRelationService.delTagsRelationByTopicId(delTagsMap);		//删除标签关联表的关联信息
				uniqueRecordService.delUniqueRecord(delTagsMap);				//删除推荐关系表的关联信息
				
				MessageRemindBean mrb = new MessageRemindBean(); //设置发送新消息的内容
				User sender = new User();			
				sender.setUserId(1);
				sender.setUserName("超级管理员");
				mrb.setTid(id);								//设置话题ID
				mrb.setType(3);								//设置消息类型
				mrb.setText("返回小组");
				mrb.setUrl("您在小组发布的话题被删除，[<a href=\"../dis/discussion!toDisHomepage.action\">");
				mrb.setSender(sender);
				Customer receiver = new Customer();
				receiver.setCusId(topicCusId);
				mrb.setReceiver(receiver); 																				//设置消息的接受者

				MessageRemindUtil.sendMessageRemind(mrb);
				
			}
			jsonResult = "1";
		} catch (Exception e) {
			jsonResult = "0";
			log.error("后台删除话题出错", e);
		}
		
		return "jsonResult";
	}
	

	/**
	 * 批量修改,话题状态 by id
	 * @return
	 */
	public String batchUpdateStatus(){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", status);
		
		List<Topic> idsList = new ArrayList<Topic>();
		String []idArr = ids.split(",");
		Topic topic = null;
		for(String s : idArr){
			topic = new Topic();
			topic.setId(Integer.parseInt(s));
			idsList.add(topic);
		}
		map.put("idsList", idsList);
		try{
			if(topicService.updateTopicStatus(map) > 0){
				jsonResult = "1";
			}
		}catch (Exception e) {
			jsonResult = "0";
			log.error("后台批量修改话题状态出错", e);
		}
		return "jsonResult";
	}
	
	/**
	 * 进入话题添加页面
	 * @return
	 */
	public String addTopic(){
		
		
		cusId = Integer.parseInt(utils.getSysParam().get("cusId"));
		if(getSession("sys_customer") == null){
			setSession("sys_customer", customerService.getCustomerById(cusId));
		}
		
		discussionList = discussionService.getAllDiscussion();
		
		return "topic_add";
	}
	
	/**
	 * 执行话题修改
	 * @return
	 */
	public String doAddTopic(){
		
		KeyWordFilter keyWordFilter = KeyWordFilter.getInstance(disWordService);
		Date now = new Date();
		try {
			
			/**
			 * 关键词过滤
			 */
			if(topic.getTitle() != null && topic.getTitle().length() > 0){
				topic.setTitle(keyWordFilter.doFilter(topic.getTitle()));
			}
			if(content != null && content.length() > 0){
				content = keyWordFilter.doFilter(content);
			}
			/**
			 * 添加话题
			 */
			topic.setCanReply(canReply);
			topic.setDisId(disId);
			topic.setCreateTime(now);
			topic.setReplyTime(now);
			topic.setContent(content);// 内容
			topic.setIsTop(topStatus);// 是否置顶
			topic.setIsVote(voteStatus);// 是否投票
			topic.setCustomer(customer);//设置用户id
			
			topicService.addTopic(topic);
		} catch (Exception e) {
			log.error("后台添加话题出错", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据组id查询改组下所有的板块（区域）
	 * @return
	 */
	public String getDisAreaByDisId(){
		
		this.getQueryDisAreaCondition().setDisId(disId);
		this.setDisAreaList(disAreaService.getDisAreaList(this.getQueryDisAreaCondition()));
		
		
		JSONArray array = JSONArray.fromObject(getDisAreaList());

		this.jsonResult = array.toString();
		
		
		return "jsonResult";
	}
	
	private QueryTopicReplyCondition queryTopicReplyCondition;
	public QueryTopicReplyCondition getQueryTopicReplyCondition() {
		if(queryTopicReplyCondition == null){
			queryTopicReplyCondition = new QueryTopicReplyCondition();
		}
		
		return queryTopicReplyCondition;
	}


	public void setQueryTopicReplyCondition(
			QueryTopicReplyCondition queryTopicReplyCondition) {
		this.queryTopicReplyCondition = queryTopicReplyCondition;
	}


	/**
	 * 回复列表ByTopicId查询
	 * @return
	 */
	public String getReplyList(){

		int pageSize = 20;// 每页个数
		try {

			topic = this.getTopicService().getTopicById(topic.getId());
			
			// 根据帖子ID查询帖子下面的所有回复
			this.getQueryTopicReplyCondition().setTopicId(topic.getId());
			getQueryTopicReplyCondition().setPageSize(pageSize);
			getQueryTopicReplyCondition().setTopicId(topic.getId());
			setPage(this.getReplyService().getPageTopicReplyList(this.getQueryTopicReplyCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
		}catch(Exception er){
			log.error("后台查询话题回复列表出错", er);
		}
		
		return "topic_reply_list";
	}
	
	/**
	 * 删除话题回复 
	 */
	public String delTopicReplyById()
	{
		try {
			this.getReplyService().delTopicReplyById(replyId);
			
			// 成功后，删除关联话题表的回复个数字段"-1"
			Map<String, Integer> topicMap = new HashMap<String, Integer>();
			topicMap.put("incremental", -1);
			topicMap.put("id", topic.getId());
			topicService.updateTopicReplyCounts(topicMap);
			
			MessageRemindBean mrb = new MessageRemindBean(); //设置发送新消息的内容
			User sender = new User();			
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			mrb.setTid(id);								//设置话题ID
			mrb.setType(3);								//设置消息类型
			mrb.setText("返回小组");
			mrb.setUrl("您在小组发布的话题回复被删除，[<a href=\"../dis/discussion!toDisHomepage.action\">");
			mrb.setSender(sender);
			Customer receiver = new Customer();
			receiver.setCusId(replyCusId);
			mrb.setReceiver(receiver); 																				//设置消息的接受者

			MessageRemindUtil.sendMessageRemind(mrb);
			
			jsonResult = "1";
		}catch(Exception er){
			jsonResult = "0";
			log.error("删除回复出错,回复id--》" + replyId, er);
		}
		//return "topic_reply_list";
		return "jsonResult";
	}
	
	
	/**
	 * 话题查询
	 * @return
	 */
	public String searchTopic(){
		
		
		int pageSize = 30;// 每页个数
		try {
			
			
			getQueryTopicCondition().setReplyCounts(replyService.getCount());//获取回复表所有记录数
			getQueryTopicCondition().setPageSize(pageSize);
			
			getQueryTopicCondition().setDisAreaId(-1);
			if(getQueryTopicCondition().getCreateTimeStart().length() == 0){
				getQueryTopicCondition().setCreateTimeStart("-1");
			}else{
				queryTopicCondition.setCreateTimeStart(queryTopicCondition.getCreateTimeStart() + startHH);
			}
			
			if(getQueryTopicCondition().getCreateTimeEnd().length() == 0){
				getQueryTopicCondition().setCreateTimeEnd("-1");
			}else{
				queryTopicCondition.setCreateTimeEnd(queryTopicCondition.getCreateTimeEnd() + endHH);
			}
			
			if(getQueryTopicCondition().getModifiedStart().length() == 0){
				getQueryTopicCondition().setModifiedStart("-1");
			}else{
				queryTopicCondition.setModifiedStart(queryTopicCondition.getModifiedStart() + modifyStartHH);
			}
			
			if(getQueryTopicCondition().getModifiedEnd().length() == 0){
				getQueryTopicCondition().setModifiedEnd("-1");
			}else{
				queryTopicCondition.setModifiedEnd(queryTopicCondition.getModifiedEnd() + modifyEndHH);
			}
			
			//在客户端将搜索关键字进行了编码，所以这里需要解析
			getQueryTopicCondition().setKeyWorld(URLDecoder.decode(getQueryTopicCondition().getKeyWorld(), "UTF-8"));
			
			/**
			 * 如果根据回复查询,此时查询sql以帖子+回复表为主,否则帖子表为主
			 */
			if(getQueryTopicCondition().getSearchCriteria().equals("3")){
				setPage(this.getTopicService().searchReplyTopicList(this.getQueryTopicCondition()));
			}else{
				setPage(this.getTopicService().searchTopicList(this.getQueryTopicCondition()));
			}
			
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			
		}catch(Exception er){
			log.error("后台话题高级查询出错", er);
		}
		return "topic_list";
	}
	
	/**
	 * 小组查询
	 * @return
	 */
	public String searchDis(){
		
		
		int pageSize = 20;// 每页个数
		try {
			
			getQueryDiscussionCondition().setPageSize(pageSize);
			
			if(getQueryDiscussionCondition().getCreateTimeStart().length() == 0){
				getQueryDiscussionCondition().setCreateTimeStart("-1");
			}else{
				getQueryDiscussionCondition().setCreateTimeStart(getQueryDiscussionCondition().getCreateTimeStart() + startHH);
			}
			
			if(getQueryDiscussionCondition().getCreateTimeEnd().length() == 0){
				getQueryDiscussionCondition().setCreateTimeEnd("-1");
			}else{
				getQueryDiscussionCondition().setCreateTimeEnd(getQueryDiscussionCondition().getCreateTimeEnd() + endHH);
			}
			
			//在客户端将搜索关键字进行了编码，所以这里需要解析
			getQueryDiscussionCondition().setKeyWorld(URLDecoder.decode(getQueryDiscussionCondition().getKeyWorld(), "UTF-8"));
			
			setPage(this.getDiscussionService().searchDisList(this.getQueryDiscussionCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			
		}catch(Exception er){
			log.error("后台高级查询小组信息出错", er);
		}
		return "dis_list";
	}
	
	/**
	 * 添加敏感词
	 * @return
	 */
	public String addSensWord(){
		if(this.getWord()==null||this.getWord().trim().equals("")){
			return "error";
		}
		DisWord disWord = new DisWord();
		disWord.setCreateTime(new Date());
		disWord.setWord(this.getWord());
		disWordService.add(disWord);
		KeyWordFilter.getInstance(disWordService).init();
		return "findSensWord" ;
	}
	
	/**
	 * 分页查看敏感词
	 * @return
	 */
	public String findSensWord(){
		getPage().setPageSize(20);
		setPage(disWordService.getDisWordList(getPage()));
		setPageUrlParms();
		if(getPage()!=null) getPage().setPageSize(20);
		return "sensitive_word" ;
	}
	
	/**
	 * 删除敏感词
	 * @return
	 */
	public String delDisWordById(){
		disWordService.delDisWordById(id);
		KeyWordFilter.getInstance(disWordService).init();
		return "findSensWord" ;
	}
	
	//~~Getters&Setters
	
	/*--------- service s/g */
	/**
	 * @return the discussionService
	 */
	public IDiscussion getDiscussionService() {
		return discussionService;
	}
	/**
	 * @return the disAreaService
	 */
	public IDisArea getDisAreaService() {
		return disAreaService;
	}


	/**
	 * @param disAreaService the disAreaService to set
	 */
	public void setDisAreaService(IDisArea disAreaService) {
		this.disAreaService = disAreaService;
	}


	/**
	 * @return the disArea
	 */
	public DisArea getDisArea() {
		return disArea;
	}


	/**
	 * @param disArea the disArea to set
	 */
	public void setDisArea(DisArea disArea) {
		this.disArea = disArea;
	}


	/**
	 * @param discussionService the discussionService to set
	 */
	public void setDiscussionService(IDiscussion discussionService) {
		this.discussionService = discussionService;
	}
	
	
	/**
	 * @return the topicService
	 */
	public ITopic getTopicService() {
		return topicService;
	}


	/**
	 * @param topicService the topicService to set
	 */
	public void setTopicService(ITopic topicService) {
		this.topicService = topicService;
	}


	/**
	 * @return the queryTopicCondition
	 */
	public QueryTopicCondition getQueryTopicCondition() {
		if (queryTopicCondition == null)
		{
			queryTopicCondition = new QueryTopicCondition();
		}
		return queryTopicCondition;
	}


	/**
	 * @param queryTopicCondition the queryTopicCondition to set
	 */
	public void setQueryTopicCondition(QueryTopicCondition queryTopicCondition) {
		this.queryTopicCondition = queryTopicCondition;
	}


	/*--------- query s/g */
	/** 获取查询条件 */
	public QueryDiscussionCondition getQueryDiscussionCondition() {
		if (queryDiscussionCondition == null) {
			queryDiscussionCondition = new QueryDiscussionCondition();
		}
		return queryDiscussionCondition;
	}

	/** 设置查询条件 */
	public void setQueryDiscussionCondition(QueryDiscussionCondition queryDiscussionCondition) {
		this.queryDiscussionCondition = queryDiscussionCondition;
	}


	/**
	 * @return the topicList
	 */
	public List<Topic> getTopicList() {
		return topicList;
	}


	/**
	 * @param topicList the topicList to set
	 */
	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}


	/**
	 * @return the subjectService
	 */
	public ISubject getSubjectService() {
		return subjectService;
	}


	/**
	 * @param subjectService the subjectService to set
	 */
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}


	/**
	 * @return the subjectList
	 */
	public List<Subject> getSubjectList() {
		return subjectList;
	}


	/**
	 * @param subjectList the subjectList to set
	 */
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}


	/**
	 * @return the dis
	 */
	public Discussion getDis() {
		return dis;
	}


	/**
	 * @param dis the dis to set
	 */
	public void setDis(Discussion dis) {
		this.dis = dis;
	}


	/**
	 * @param disList the disList to set
	 */
	public void setDisList(List<Discussion> disList) {
		this.disList = disList;
	}


	/**
	 * @return the newPhoto
	 */
	public List<File> getNewPhoto() {
		return newPhoto;
	}


	/**
	 * @param newPhoto the newPhoto to set
	 */
	public void setNewPhoto(List<File> newPhoto) {
		this.newPhoto = newPhoto;
	}


	/**
	 * @return the newPhotoFileName
	 */
	public List<String> getNewPhotoFileName() {
		return newPhotoFileName;
	}


	/**
	 * @param newPhotoFileName the newPhotoFileName to set
	 */
	public void setNewPhotoFileName(List<String> newPhotoFileName) {
		this.newPhotoFileName = newPhotoFileName;
	}


	/**
	 * @return the photoPrams
	 */
	public Map<String, String> getPhotoPrams() {
		return photoPrams;
	}


	/**
	 * @param photoPrams the photoPrams to set
	 */
	public void setPhotoPrams(Map<String, String> photoPrams) {
		this.photoPrams = photoPrams;
	}


	/**
	 * @return the userInfoState
	 */
	public String getUserInfoState() {
		return userInfoState;
	}


	/**
	 * @param userInfoState the userInfoState to set
	 */
	public void setUserInfoState(String userInfoState) {
		this.userInfoState = userInfoState;
	}


	/**
	 * @return the updateMessage
	 */
	public String getUpdateMessage() {
		return updateMessage;
	}


	/**
	 * @param updateMessage the updateMessage to set
	 */
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}


	/**
	 * @return the updateError
	 */
	public String getUpdateError() {
		return updateError;
	}


	/**
	 * @param updateError the updateError to set
	 */
	public void setUpdateError(String updateError) {
		this.updateError = updateError;
	}


	public String getSrcCreateTime() {
		return srcCreateTime;
	}


	public void setSrcCreateTime(String srcCreateTime) {
		this.srcCreateTime = srcCreateTime;
	}

	public Topic getTopic() {
		return topic;
	}


	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Integer getDisId() {
		return disId;
	}


	public void setDisId(Integer disId) {
		this.disId = disId;
	}


	public Integer getCanReply() {
		return canReply;
	}


	public void setCanReply(Integer canReply) {
		this.canReply = canReply;
	}


	public String getCreateTime() {
		return createTime;
	}


	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	public String getReplyTime() {
		return replyTime;
	}


	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}


	public Integer getVoteStatus() {
		return voteStatus;
	}


	public void setVoteStatus(Integer voteStatus) {
		this.voteStatus = voteStatus;
	}


	public Integer getTopStatus() {
		return topStatus;
	}


	public void setTopStatus(Integer topStatus) {
		this.topStatus = topStatus;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Discussion getDiscussion() {
		return discussion;
	}


	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}


	public QueryDisAreaCondition getQueryDisAreaCondition() {
		if (this.queryDisAreaCondition == null) {
			this.queryDisAreaCondition = new QueryDisAreaCondition();
		}
		return queryDisAreaCondition;
	}


	public void setQueryDisAreaCondition(QueryDisAreaCondition queryDisAreaCondition) {
		this.queryDisAreaCondition = queryDisAreaCondition;
	}


	public List<DisArea> getDisAreaList() {
		return disAreaList;
	}


	public void setDisAreaList(List<DisArea> disAreaList) {
		this.disAreaList = disAreaList;
	}


	public Integer getDisAreaId() {
		return disAreaId;
	}


	public void setDisAreaId(Integer disAreaId) {
		this.disAreaId = disAreaId;
	}


	public List<SelectModel> getStatusTopicList() {
		return statusTopicList;
	}


	public void setStatusTopicList(List<SelectModel> statusTopicList) {
		this.statusTopicList = statusTopicList;
	}

	public IVote getVoteService() {
		return voteService;
	}


	public void setVoteService(IVote voteService) {
		this.voteService = voteService;
	}


	public void setVoteLogService(IVoteLog voteLogService) {
		this.voteLogService = voteLogService;
	}


	public String getJsonResult() {
		return jsonResult;
	}


	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}


	public IVoteLog getVoteLogService() {
		return voteLogService;
	}


	public List<SelectModel> getTypeTopicList() {
		return typeTopicList;
	}


	public void setTypeTopicList(List<SelectModel> typeTopicList) {
		this.typeTopicList = typeTopicList;
	}


	public String getIds() {
		return ids;
	}


	public void setIds(String ids) {
		this.ids = ids;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public List<Discussion> getDiscussionList() {
		return discussionList;
	}


	public void setDiscussionList(List<Discussion> discussionList) {
		this.discussionList = discussionList;
	}


	public ITopicReply getReplyService() {
		return replyService;
	}


	public void setReplyService(ITopicReply replyService) {
		this.replyService = replyService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}


	public int getCusId() {
		return cusId;
	}


	public void setCusId(int cusId) {
		this.cusId = cusId;
	}


	public void setUtils(Utils utils) {
		this.utils = utils;
	}


	public List<SelectModel> getSearchCriteriaList() {
		return searchCriteriaList;
	}


	public void setSearchCriteriaList(List<SelectModel> searchCriteriaList) {
		this.searchCriteriaList = searchCriteriaList;
	}
	
	public String getResMsg() {
		return resMsg;
	}
	
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	
	public int getReplyId() {
		return replyId;
	}
	
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}


	public String getStartHH() {
		return startHH;
	}


	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}


	public String getEndHH() {
		return endHH;
	}


	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}


	public String getModifyStartHH() {
		return modifyStartHH;
	}


	public void setModifyStartHH(String modifyStartHH) {
		this.modifyStartHH = modifyStartHH;
	}


	public String getModifyEndHH() {
		return modifyEndHH;
	}


	public void setModifyEndHH(String modifyEndHH) {
		this.modifyEndHH = modifyEndHH;
	}


	public List<SelectModel> getSearchCriteriaByDisList() {
		return searchCriteriaByDisList;
	}


	public void setSearchCriteriaByDisList(List<SelectModel> searchCriteriaByDisList) {
		this.searchCriteriaByDisList = searchCriteriaByDisList;
	}


	public List<SelectModel> getTypeDisList() {
		return typeDisList;
	}


	public void setTypeDisList(List<SelectModel> typeDisList) {
		this.typeDisList = typeDisList;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
	}


	public void setDisWordService(IDisWord disWordService) {
		this.disWordService = disWordService;
	}


	public List<SelectModel> getStatusDisList() {
		return statusDisList;
	}


	public void setStatusDisList(List<SelectModel> statusDisList) {
		this.statusDisList = statusDisList;
	}


	public ITagsRelation getTagsRelationService() {
		return tagsRelationService;
	}


	public void setTagsRelationService(ITagsRelation tagsRelationService) {
		this.tagsRelationService = tagsRelationService;
	}


	public IUniqueRecord getUniqueRecordService() {
		return uniqueRecordService;
	}


	public void setUniqueRecordService(IUniqueRecord uniqueRecordService) {
		this.uniqueRecordService = uniqueRecordService;
	}


	public int getTopicCusId() {
		return topicCusId;
	}


	public void setTopicCusId(int topicCusId) {
		this.topicCusId = topicCusId;
	}


	public int getReplyCusId() {
		return replyCusId;
	}


	public void setReplyCusId(int replyCusId) {
		this.replyCusId = replyCusId;
	}
	
}
