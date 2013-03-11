package com.shangde.edu.dis.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.messageRemind.MessageRemindBean;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.domain.TopicReply;
import com.shangde.edu.dis.service.IDisWord;
import com.shangde.edu.dis.service.ITopic;
import com.shangde.edu.dis.service.ITopicReply;
import com.shangde.edu.dis.web.interceptor.KeyWordFilter;
import com.shangde.edu.sys.domain.User;

public class TopicReplyWebAction extends CommonAction {

												
	private Log logger = LogFactory.getLog(TopicReplyWebAction.class);													

	
	
	
	private ITopic topicService;
	private ITopicReply replyService;
	private TopicReply topicReply;
	private IDisWord disWordService;

	private Integer id;
	

	
	
	
	
	
	
	
	/*****V1.1属性声明区域 开始*****/
	
	
	private List<TopicReply> replyList;//回复列表
	
	private int resStatus;//状态[1=成功，0=失败]
	private int resType;//类型[1=列表，2=单项]
	
	private Integer disId;//小组id
	private Integer topicId;//话题id
	private String targetCusId;//目标用户id
	private String replyId;//回复某贴记录id
	private String replyCusName;//回复人名称
	private String content;//回复内容
	private String fromSign;//来源id，提供多方面的记录id加特殊标示(小组首页【我发起的话题，所有推荐的话题】)
	
	private String jsonResult;//json类型返回需要的结果参数值
	
	
	/*****V1.1属性声明区域 结束*****/
	
	
	
	
	/*****V1.1方法(method)声明区域 开始*****/

	/**
	 * 根据话题id取得该帖子回复 列表
	 * @return
	 */
	public String getTopicReplyByTopicId(){
		
		try{
			//校验话题记录是否还存在
			if(topicService.getTopicById(topicId) == null){
				resStatus = 0;
				logger.info("查询的话题不存在！");
			}else{
				replyList = replyService.getReplyCountList(topicId);
				resStatus = 1;
				resType = 1;
				if(fromSign == null){
					fromSign = String.valueOf(topicId);
				}
			}
		}catch (Exception e) {
			resStatus = 0;
			logger.error("根据话题id取得该帖子回复", e);
		}
		return "topic_reply_item";
	}
	
	/**
	 * 根据回复id查询 对象
	 * @return
	 */
	public String getTopicReplyById(){
		
		try{
			replyList = new ArrayList<TopicReply>();
			//获取单个信息，添加到list集合中,这样做的好处是可以共用一个jsp页面处理结果
			replyList.add(replyService.getTopicReplyById(id));
			resStatus = 1;
			resType = 2;
			if(fromSign == null){
				fromSign = String.valueOf(topicId);
			}
		}catch (Exception e) {
			resStatus = 0;
			logger.error("根据回复id查询 对象-出错", e);
		}
		return "topic_reply_item";
	}
	
	/**
	 * 根据回复id查询 对象
	 * @return
	 */
	public String getTopicReplyInfoById(){
		
		try{
			replyList = new ArrayList<TopicReply>();
			//获取单个信息，添加到list集合中,这样做的好处是可以共用一个jsp页面处理结果
			replyList.add(replyService.getTopicReplyById(id));
			resStatus = 1;
			resType = 2;
			if(fromSign == null){
				fromSign = String.valueOf(topicId);
			}
		}catch (Exception e) {
			resStatus = 0;
			logger.error("根据回复id查询 对象-出错", e);
		}
		return "topic_reply_info_item";
	}
	
	/**
	 * 添加回复
	 * 
	 * @return
	 */
	public String doAdd() {

		//校验结果（添加回复时校验：1、回复的话题是否存在，2、如果是回复某贴的回复内容进行回复，校验回复记录是否存在） 两者成立继续执行，否则返回失败消息
		boolean verifyResult = false;
		Date now = new Date();
		Map<String, Integer> map = new HashMap<String, Integer>();

		KeyWordFilter keyWordFilter = KeyWordFilter.getInstance(disWordService);
		TopicReply topicReply = new TopicReply();
		Topic topic = topicService.getTopicById(topicId);
		//校验话题记录是否还存在
		if(topic != null){
			verifyResult = true;
		}
		try{
			
			/**
			 * 过滤回复内容,“回复@用户名”
			 * 
			 * 回复
			 * @
			 * replyCusName
			 * :
			 * 
			 */
			if(getTargetCusId() != null && !getTargetCusId().equals("")){
				
				StringBuffer splitC = new StringBuffer();
				try{
					//回复某人格式：= "回复@"+replyCusName+":";
					splitC.append("回复@").append(replyCusName).append(":");
					//从内容中过滤掉“回复某人格式”
					if(content.indexOf(splitC.toString()) != -1){
						content = content.replaceAll(splitC.toString(), "");
						setContent(content);
					}else{
						//如果格式配破坏了，则当做直接针对主帖回复
						setTargetCusId("");
					}
				}catch (Exception e) {
					logger.error("过滤回复内容时出错",e);
				}
			}
			/**
			 * 关键词过滤
			 */
			if(getContent() != null && getContent().length() > 0){
				//关键词过滤
				setContent(keyWordFilter.doFilter(getContent()));
				topicReply.setContent(getContent());
			}
		}catch (Exception e) {
			logger.error("回复时，关键字过滤try-catch", e);
		}
		
		if(!verifyResult){
			logger.info("回复的帖子不存在");
			jsonResult = "0";
			return "jsonResult";
		}
		
		try{
			// 设置当前登录用户id
			int userId = getLoginUserId();
			Customer customer = new Customer();
			customer.setCusId(userId);
			
			Customer targetCustomer = new Customer();
			TopicReply targetTopicReply = new TopicReply();
			
			MessageRemindBean mrb = new MessageRemindBean(); //设置发送新消息的内容
			User sender = new User();			
			sender.setUserId(1);
			sender.setUserName("超级管理员");
			mrb.setTid(topicId);						//设置话题ID
			mrb.setType(3);								//设置消息类型
			mrb.setText("查看");
			mrb.setSender(sender);
			
			if(getTargetCusId() != null && !getTargetCusId().equals("")){
				targetCustomer.setCusId(new Integer(getTargetCusId()));
				targetTopicReply.setId(new Integer(getReplyId()));
				mrb.setReceiver(targetCustomer); //设置消息的接受者
				mrb.setUrl("您回复的话题有了新的回复，请[<a href=\"../dis/topic!getTopicInfo.action?topic.id="
						+ topicId+ "&disId="+disId+"&queryTopicReplyCondition.currentPage=1\">");							//设置消息查看要连接的地址
			}else{
				mrb.setReceiver(topic.getCustomer()); //设置消息的接受者
				mrb.setUrl("您发布的话题有了新回复，请[<a href=\"../dis/topic!getTopicInfo.action?topic.id="
						+ topicId+ "&disId="+disId+"&queryTopicReplyCondition.currentPage=1\">");							//设置消息查看要连接的地址
			}
			topicReply.setTargetCustomer(targetCustomer);
			topicReply.setTargetTopicReply(targetTopicReply);
			topicReply.setCustomer(customer);
			topicReply.setReplyTime(now);// 回复时间
			topicReply.setTopicId(topicId);
			topicReply.setDisId(disId);
			
			id = replyService.addTopicReply(topicReply);
			
			mrb.setRid(id);
			//当消息接收者不是本人的时候发送新消息
			if(userId != mrb.getReceiver().getCusId()){
				MessageRemindUtil.sendMessageRemind(mrb);
			}
			
			// 修改话题表,回复次数字段++
			map.put("incremental", 1);
			map.put("id", topicId);
			topicService.updateTopicReplyCounts(map);
	
			// 修改话题表,修改回复时间
			Map<String, Object> replyTimeMap = new HashMap<String, Object>();
			replyTimeMap.put("id", topicId);
			replyTimeMap.put("replyTime", new Date());
			topicService.updateTopicReplyTime(replyTimeMap);
		
			
			if(fromSign == null || fromSign.equals("") || fromSign.length() == 0){
				fromSign = String.valueOf(id);
			}
			//记录id,业务执行成功，将当前记录id返回到页面
			jsonResult = String.valueOf(id);
		}catch (Exception e) {
			jsonResult = "0";
			logger.error("添加回复业务-出错", e);
		}
		return "jsonResult";
	}

	
	/**
	 * 删除一条话题回复
	 * 
	 * @return
	 */
	public String doDel() {
		try {
			Map<String, Integer> replyMap = new HashMap<String, Integer>();
			replyMap.put("id", id);							//设置话题回复ID
			replyMap.put("cusId", getLoginUserId());		//设置用户ID
			int i = replyService.delTopicReply(replyMap);
			if ( i > 0) {
				// 成功后，删除关联话题表的回复个数字段"-1"
				Map<String, Integer> topicMap = new HashMap<String, Integer>();
				topicMap.put("incremental", -1);
				topicMap.put("id", topicId);
				topicService.updateTopicReplyCounts(topicMap);
			}
			jsonResult = "1";
		} catch (Exception e) {
			jsonResult = "0";
			logger.error("删除回复", e);
		}
		return "jsonResult";
	}
	
	public List<TopicReply> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<TopicReply> replyList) {
		this.replyList = replyList;
	}
	
	public int getResStatus() {
		return resStatus;
	}

	public void setResStatus(int resStatus) {
		this.resStatus = resStatus;
	}
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	/*****V1.1方法(method)声明区域 结束*****/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ITopic getTopicService() {
		return topicService;
	}

	public void setTopicService(ITopic topicService) {
		this.topicService = topicService;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	
	public void setDisWordService(IDisWord disWordService) {
		this.disWordService = disWordService;
	}
	
	public void setReplyService(ITopicReply replyService) {
		this.replyService = replyService;
	}

	public void setTopicReply(TopicReply topicReply) {
		this.topicReply = topicReply;
	}

	public TopicReply getTopicReply() {
		return topicReply;
	}

	public String getTargetCusId() {
		return targetCusId;
	}

	public void setTargetCusId(String targetCusId) {
		this.targetCusId = targetCusId;
	}

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getReplyCusName() {
		return replyCusName;
	}

	public void setReplyCusName(String replyCusName) {
		this.replyCusName = replyCusName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDisId() {
		return disId;
	}

	public void setDisId(Integer disId) {
		this.disId = disId;
	}

	public int getResType() {
		return resType;
	}

	public void setResType(int resType) {
		this.resType = resType;
	}

	public String getFromSign() {
		return fromSign;
	}

	public void setFromSign(String fromSign) {
		this.fromSign = fromSign;
	}

}
