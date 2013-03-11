package com.shangde.edu.dis.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;
import com.shangde.edu.dis.condition.QueryTopicCondition;
import com.shangde.edu.dis.condition.QueryTopicReplyCondition;
import com.shangde.edu.dis.domain.TagsRelation;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.domain.UniqueRecord;
import com.shangde.edu.dis.domain.Vote;
import com.shangde.edu.dis.domain.VoteDetail;
import com.shangde.edu.dis.service.IDisWord;
import com.shangde.edu.dis.service.IDiscussion;
import com.shangde.edu.dis.service.ITags;
import com.shangde.edu.dis.service.ITagsRelation;
import com.shangde.edu.dis.service.ITopic;
import com.shangde.edu.dis.service.ITopicReply;
import com.shangde.edu.dis.service.IUniqueRecord;
import com.shangde.edu.dis.service.IVote;
import com.shangde.edu.dis.service.IVoteLog;
import com.shangde.edu.dis.web.interceptor.KeyWordFilter;
import com.shangde.edu.sys.domain.User;

/**
 * 
 * @author Libg
 * 
 */
public class TopicWebAction extends CommonAction {

	
	private static Logger logger = Logger.getLogger(TopicWebAction.class);
	
	private ITopic topicService;
	private IVote voteService;
	private IVoteLog voteLogService;
	private ITotolsScore totolsScoreService;
	private ITsRecord tsRecordService;
	private IDiscussion discussionService;
	private IDisWord disWordService;
	
	private List<VoteDetail> voteDetailList;
	private Topic topic;// 话题
	private Vote vote;// 投票
	private Integer isVote;
	private Integer voteStatus;
	private Integer topStatus;
	private String content;
	private Integer voteType;
	private String referenceResults;
	private String voteDetailStrs;// 投票选项信息格式[title,title,,]
	private String jsonResult;
	private String resMsg;// 相应信息
	private Integer resFlag = 0;// 非0表示成功
	private int voteId = 0;// 这里从voteService执行添加操作后,返回ID值
	private int id;
	private Integer disAreaId;
	private Integer recCount;
	private Integer canReply;
	private String createTime;
	private String replyTime;
	private Integer voteMaxCount = 0;// 投票总数
	private String from;// 来自
	private String updateError;
	private int userId;
	
	/*****V1.1属性声明区域 开始*****/
	private ITags tagsService;
	private ITagsRelation tagsRelationService;
	private IUniqueRecord uniqueRecordService;
	/* 话题表回复业务对象 */
	private ITopicReply replyService;
	
	/* 话题查询条件 */
	private QueryTopicCondition queryTopicCondition;
	private QueryTopicReplyCondition queryTopicReplyCondition;

	private Integer topicCusId;			//话题发布人ID
	private Integer disId;
	private String tagId;
	private String tagText;
	private boolean flag = false;
	private TagsRelation tagsRelation;
	
	/**
	 * 显示回复默认项
	 */
	private int replyDisCountDefault = com.shangde.edu.dis.utils.Utils.REPLY_DIS_COUNT;
	
	
	/*****V1.1属性声明区域 结束*****/
	
	
	
	
	/*****V1.1方法(method)声明区域 开始*****/
	
	public ITags getTagsService() {
		return tagsService;
	}

	public void setTagsService(ITags tagsService) {
		this.tagsService = tagsService;
	}

	public ITagsRelation getTagsRelationService() {
		return tagsRelationService;
	}

	public void setTagsRelationService(ITagsRelation tagsRelationService) {
		this.tagsRelationService = tagsRelationService;
	}
	
	public QueryTopicCondition getQueryTopicCondition() {
		return queryTopicCondition;
	}

	public void setQueryTopicCondition(QueryTopicCondition queryTopicCondition) {
		this.queryTopicCondition = queryTopicCondition;
	}
	
	public QueryTopicReplyCondition getQueryTopicReplyCondition() {
		if (this.queryTopicReplyCondition == null) {
			this.queryTopicReplyCondition = new QueryTopicReplyCondition();
		}
		return queryTopicReplyCondition;
	}

	public void setQueryTopicReplyCondition(
			QueryTopicReplyCondition queryTopicReplyCondition) {
		this.queryTopicReplyCondition = queryTopicReplyCondition;
	}

	public void setReplyService(ITopicReply replyService) {
		this.replyService = replyService;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public TagsRelation getTagsRelation() {
		return tagsRelation;
	}

	public void setTagsRelation(TagsRelation tagsRelation) {
		this.tagsRelation = tagsRelation;
	}

	/**
	 * 添加话题业务
	 * 
	 * 返回值说明： 0=失败，非零=话题id
	 * 
	 * @return
	 */
	public String doAdd() {
		
		// 取登陆用户ID
		userId = getLoginUserId();
		Date now = new Date();
		Customer customer = new Customer();
		TagsRelation tagsRelation = null;
		KeyWordFilter keyWordFilter = KeyWordFilter.getInstance(disWordService);
		try {
			
			if(topic == null){
				topic = new Topic();
			}
			/**
			 * 关键词过滤
			 */
			try{
				if(topic.getTitle() != null && topic.getTitle().length() > 0){
					topic.setTitle(keyWordFilter.doFilter(topic.getTitle()));
				}
				if(content != null && content.length() > 0){
					//内容关键字过滤
					content = keyWordFilter.doFilter(content);
				}
			}catch (Exception e) {
				logger.error("添加帖子时，关键字过滤try-catch", e);
			}
			
			topic.setVoteCount(0);//投票次数
			topic.setStatus(1);//状态 1=未申
			topic.setRecCount(0);//推荐
			topic.setIsVote(0);//是否投票
			topic.setCanReply(0);
			topic.setCreateTime(now);
			topic.setReplyTime(now);
			topic.setModified(now);
			topic.setDisId(disId);//小组
			topic.setContent(content);// 内容
			topic.setTags(tagText);
			
			customer.setCusId(userId);//设置当前登录用户id
			topic.setCustomer(customer);
			
			try{
				id = topicService.addTopic(topic);
				//属于什么标签（目前是单选）
				if(tagId != null && tagId.length() > 0){
					tagsRelation = new TagsRelation();
					tagsRelation.setTagId(Integer.valueOf(tagId));
					tagsRelation.setObjectTypeId(1);// 1=表示话题类型
					tagsRelation.setObjectId(id);
					
					//添加到数据库中
					tagsRelationService.addTagsRelation(tagsRelation);
				}
			}catch (Exception e) {
				logger.error("前台添加业务失败", e);
			}
			//成功将 话题id进行返回
			jsonResult = String.valueOf(id);
		} catch (Exception e) {
			jsonResult = "0";
			logger.error("添加话题业务失败", e);
			return ERROR;
		}
		
		return "jsonResult";
	}
	
	/**
	 * 根据话题id取得数据
	 * @return
	 */
	public String getTopicById(){
		
		topic = topicService.getTopicById(topic.getId());
		
		return "topic_item";
	}
	
	/**
	 * 返回空
	 * @return
	 */
	public String empty(){
		return "empty";
	}
	
	/**
	 * 最新话题点更多分页查询
	 * @return
	 */
	public String newtopic(){
		userId = getLoginUserId();
		this.getQueryTopicCondition().setDisId(disId);
		this.getQueryTopicCondition().setPageSize(25);
		setPage(topicService.getNewTopicList(queryTopicCondition));
		if(getPage()!=null) getPage().setPageSize(25);
		setPageUrlParms();
		return "dis_newtopic";
	}
	
	/**
	 * 我发布的话题点更多分页查询
	 * @return
	 */
	public String mytopic(){
		userId = getLoginUserId();
		this.queryTopicCondition.setCusId(userId);
		this.queryTopicCondition.setDisId(disId);
		this.queryTopicCondition.setPageSize(25);
		setPage(topicService.getMyTopicList(queryTopicCondition));
		if(getPage()!=null) getPage().setPageSize(25);
		setPageUrlParms();
		return "dis_mytopic";
	}
	
	/**
	 * 我回复的话题更多分页查询
	 * @return
	 */
	public String myRespond(){
		userId = getLoginUserId();
		this.queryTopicCondition.setDisId(disId);			//设置小组ID
		this.queryTopicCondition.setCusId(userId);			//设置登录用户的ID
		this.queryTopicCondition.setPageSize(25);			//设置页大小
		setPage(topicService.getReTopicList(queryTopicCondition));
		if(getPage()!=null) getPage().setPageSize(25);
		setPageUrlParms();
		return "myRespond";
	}
	
	/**
	 * 推荐的话题更多页面查询
	 * @return
	 */
	public String recommend(){
		this.queryTopicCondition.setDisId(disId);
		this.queryTopicCondition.setPageSize(25);
		setPage(topicService.getRecommendList(queryTopicCondition));
		if(getPage()!=null) getPage().setPageSize(25);
		setPageUrlParms();
		return "dis_recommend";
	}
	
	/**
	 * 话题详细内容
	 * 
	 * @return
	 */
	public String getTopicInfo() {
		userId = getLoginUserId();
		Map<String, Integer> map = new HashMap<String, Integer>();
		int pageSize = 20;// 每页个数
		try {
			topic = topicService.getTopicById(topic.getId());
			// 根据帖子ID查询帖子下面的所有回复
			if (topic != null) {
				this.getQueryTopicReplyCondition().setTopicId(topic.getId());
				this.getQueryTopicReplyCondition().setPageSize(pageSize);
				setPage(replyService.getFrontPageTopicReplyList(queryTopicReplyCondition));
				if (getPage() != null) {
					getPage().setPageSize(pageSize);
				}
				// 修改点击次数字段++
				map.put("incremental", 1);
				map.put("id", topic.getId());
				topicService.updateTopicClickCounts(map);
				setPageUrlParms();
			}else{
				return "dis_index";
			}

		} catch (Exception ex) {
			logger.error("话题详情查询业务失败", ex);
			return ERROR;
		}
		return "topic_info";
	}
	
	/**
	 * 删除话题
	 * 
	 * @return
	 */
	public String doDel() {

		try {
			// 最后删除话题贴以及针对话题的回复
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("id", id);
			map.put("cusId", getLoginUserId());
			int i = topicService.delTopic(map);
			if (i > 0) {
				// voteId 不等于-1时，说明当前贴是投票贴
				if (voteId != -1) {
					// 首先删除投票过的记录
					voteLogService.delVoteLogByVoteId(voteId);
					// 其次删除投票以及投票关联信息
					voteService.delVoteById(voteId);
				}
				Map<String, Integer> delTagsMap = new HashMap<String, Integer>();
				delTagsMap.put("objectId", id);
				delTagsMap.put("objectTypeId", 1);
				tagsRelationService.delTagsRelationByTopicId(delTagsMap);		//删除标签关联表的关联信息
				uniqueRecordService.delUniqueRecord(delTagsMap);				//删除推荐关系表的关联信息
			}
			jsonResult = "1";					//删除成功！
		} catch (Exception e) {
			jsonResult = "0";
			logger.error("删除话题出错", e);
		}

		return "jsonResult";
	}
	
	/**
	 * 修改话题查询
	 * @return
	 */
	public String findTopicUpdate(){
		userId = getLoginUserId();
		topic = topicService.getTopicById(id);
		if(topic.getCusId()==userId){
			flag = true ;
		}
		if(flag&&topic.getTags()!=null&&!"".equals(topic.getTags())){
			tagsRelation = tagsRelationService.getTagsRelationByTopicId(id);
		}
		return "topic_update";
	}
	
	/**
	 * 话题修改
	 * @return
	 */
	public String doEdit() {
		// 取登陆用户ID
		try {
			userId = getLoginUserId();
			topic = topicService.getTopicById(id);
			if(topic!=null&&userId==topic.getCusId()){								//话题信息不为空且话题必须是当前登录人的才可以编辑
				KeyWordFilter keyWordFilter = KeyWordFilter.getInstance(disWordService);
				content = keyWordFilter.doFilter(content);
				topic.setContent(content);
				topic.setModified(new Date());
				if (tagId != null && tagId.length() > 0 && tagText != null
						&& !"".equals(tagText) && topic.getTags() != null
						&& !"".equals(topic.getTags())
						&& !tagText.equals(topic.getTags())) { // 修改话题标签关联信息
					topic.setTags(tagText);
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("objectTypeId", 1);
					map.put("topicId", topic.getId());
					map.put("tagId", Integer.parseInt(tagId));
					tagsRelationService.updateByTopicId(map);
				} else if (tagId != null
						&& tagId.length() > 0
						&& tagText != null
						&& !"".equals(tagText)
						&& (topic.getTags() == null || "".equals(topic
								.getTags()))) {										//添加话题与标签的链接
					
					tagsRelation = new TagsRelation();
					tagsRelation.setTagId(Integer.valueOf(tagId));
					tagsRelation.setObjectTypeId(1);// 1=表示话题类型
					tagsRelation.setObjectId(topic.getId());
					topic.setTags(tagText);
					//添加到数据库中
					tagsRelationService.addTagsRelation(tagsRelation);
				}
				topicService.updateTopic(topic);
				content = com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(content);//去掉所有"<*>"符号
				content = com.shangde.edu.dis.utils.StringUtil.specialCharacterFiltering(content);//替换特殊字符
				jsonResult = "1";
			}else{
				jsonResult = "0";
			}
		} catch (NumberFormatException e) {
			jsonResult = "0";
			logger.error("修改回复出错", e);
		}
		return "topic_edit";
	}

	
	/*****V1.1方法(method)声明区域 结束*****/

	/**
	 * 积分扣除，以及校验是否 满足,并扣除 指定积分
	 * 
	 * 
	 * 推荐并积分扣除
	 * 
	 * @return
	 */
	public String recommended() {

		int lostValue = 2;//失去值
		userId = getLoginUserId();//当前用户id
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		/**
		 * 查询业务，并校验这个用户是否推荐过本帖
		 */
		Map<String, Integer> findMap = new HashMap<String, Integer>();
		findMap.put("cusId", userId);
		findMap.put("objectId", id);
		findMap.put("type", 1);//类型为1表示推荐分类
		if(uniqueRecordService.getUniqueRecord(findMap) > 0){
			
			jsonResult = "3";//表示：该用户对本帖已推荐
			return "jsonResult";
		}
		
		/**
		 * 查询出当前用户积分
		 */
		TotolsScore totolsScore = null;
		UniqueRecord uniqueRecord = null;
		try {
			//查询出当前用户积分信息
			totolsScore = totolsScoreService.getTotolsScore(userId);
			//校验积分是否在扣除积分的范围之内
			if (totolsScore.getTsCurrent() >= lostValue) {
				//执行扣积分运算
				totolsScore
						.setTsCurrent(totolsScore.getTsCurrent() - lostValue);
				//更新至数据库
				totolsScoreService.updateTotolsScore(totolsScore);

				/**
				 * 修改推荐次数字段++
				 */
				map.put("incremental", 1);
				map.put("id", id);
				topicService.updateTopicRecCount(map);
				
				//封装唯一请求记录
				uniqueRecord = new UniqueRecord();
				uniqueRecord.setCusId(userId);
				uniqueRecord.setObjectId(id);//记录id
				uniqueRecord.setPubTime(new Date());
				uniqueRecord.setType(1);//表示推荐类型
				uniqueRecordService.addUniqueRecord(uniqueRecord);
				jsonResult = "1";// 表示成功
				
				//当消息接收者不是本人的时候发送新消息
				if(userId != topicCusId){				
					MessageRemindBean mrb = new MessageRemindBean(); //设置发送新消息的内容
					User sender = new User();			
					sender.setUserId(1);
					sender.setUserName("超级管理员");
					mrb.setTid(id);								//设置话题ID
					mrb.setType(3);								//设置消息类型
					mrb.setText("查看");
					mrb.setSender(sender);
					Customer receiver = new Customer();
					receiver.setCusId(topicCusId);
					mrb.setReceiver(receiver); 																				//设置消息的接受者
					mrb.setUrl("您发布的话题被推荐了，请[<a href=\"../dis/topic!getTopicInfo.action?topic.id="
							+ id+ "&disId="+disId+"&queryTopicReplyCondition.currentPage=1\">");							//设置消息查看要连接的地址
	
					MessageRemindUtil.sendMessageRemind(mrb);
				}
				
				/**
				 * 积分交易记录,这里表示推荐扣除积分
				 */
				TsRecord tsRecord = new TsRecord();
				tsRecord.setCusId(userId);
				tsRecord.setTrType(TsRecord.TRTYPE_FOR);
				tsRecord.setUseType(TsRecord.USETYPE_RECOMMENDED);// 推荐类型
				tsRecord.setUseTime(new Date());
				tsRecord.setTsId(totolsScore.getTsId());
				tsRecord.setTrNum(lostValue);// 本次扣除数量
				//添加至数据库
				tsRecordService.addTsRecord(tsRecord);
			} else {
				jsonResult = "2";// 表示积分不足
			}
		} catch (Exception e) {
			jsonResult = "0";
			logger.error("话题推荐业务失败", e);
		}
		return "jsonResult";
	}

	public Vote getVote() {
		return vote;
	}

	public Integer getIsVote() {
		return isVote;
	}

	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}

	public Integer getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(Integer voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTopStatus() {
		return topStatus;
	}

	public void setTopStatus(Integer topStatus) {
		this.topStatus = topStatus;
	}

	public Integer getVoteType() {
		return voteType;
	}

	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}

	public String getReferenceResults() {
		return referenceResults;
	}

	public void setReferenceResults(String referenceResults) {
		this.referenceResults = referenceResults;
	}

	public int getVoteId() {
		return voteId;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public List<VoteDetail> getVoteDetailList() {
		return voteDetailList;
	}

	public void setVoteDetailList(List<VoteDetail> voteDetailList) {
		this.voteDetailList = voteDetailList;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public IVoteLog getVoteLogService() {
		return voteLogService;
	}

	public void setVoteLogService(IVoteLog voteLogService) {
		this.voteLogService = voteLogService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}

	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}

	/**
	 * @return the updateError
	 */
	public String getUpdateError() {
		return updateError;
	}

	/**
	 * @param updateError
	 *            the updateError to set
	 */
	public void setUpdateError(String updateError) {
		this.updateError = updateError;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public IDiscussion getDiscussionService() {
		return discussionService;
	}

	public void setDiscussionService(IDiscussion discussionService) {
		this.discussionService = discussionService;
	}

	public void setDisWordService(IDisWord disWordService) {
		this.disWordService = disWordService;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getVoteMaxCount() {
		return voteMaxCount;
	}

	public void setVoteMaxCount(Integer voteMaxCount) {
		this.voteMaxCount = voteMaxCount;
	}

	public Integer getRecCount() {
		return recCount;
	}

	public void setRecCount(Integer recCount) {
		this.recCount = recCount;
	}

	public Integer getCanReply() {
		return canReply;
	}

	public void setCanReply(Integer canReply) {
		this.canReply = canReply;
	}

	public Integer getDisId() {
		return disId;
	}

	public void setDisId(Integer disId) {
		this.disId = disId;
	}

	public Integer getDisAreaId() {
		return disAreaId;
	}

	public void setDisAreaId(Integer disAreaId) {
		this.disAreaId = disAreaId;
	}

	public String getResMsg() {
		return resMsg;
	}

	public Integer getResFlag() {
		return resFlag;
	}

	public String getVoteDetailStrs() {
		return voteDetailStrs;
	}

/*	public void setVoteDetailService(IVoteDetail voteDetailService) {
		this.voteDetailService = voteDetailService;
	}*/

	public void setVoteService(IVote voteService) {
		this.voteService = voteService;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopicService(ITopic topicService) {
		this.topicService = topicService;
	}

	public void setVoteDetailStrs(String voteDetailStrs) {
		this.voteDetailStrs = voteDetailStrs;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}



	


	public String getTagId() {
		return tagId;
	}



	public void setTagId(String tagId) {
		this.tagId = tagId;
	}



	public String getTagText() {
		return tagText;
	}



	public void setTagText(String tagText) {
		this.tagText = tagText;
	}



	public String getJsonResult() {
		return jsonResult;
	}



	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}


	public void setUniqueRecordService(IUniqueRecord uniqueRecordService) {
		this.uniqueRecordService = uniqueRecordService;
	}



	public IUniqueRecord getUniqueRecordService() {
		return uniqueRecordService;
	}


	public Integer getTopicCusId() {
		return topicCusId;
	}


	public void setTopicCusId(Integer topicCusId) {
		this.topicCusId = topicCusId;
	}

	public int getReplyDisCountDefault() {
		return replyDisCountDefault;
	}

}
