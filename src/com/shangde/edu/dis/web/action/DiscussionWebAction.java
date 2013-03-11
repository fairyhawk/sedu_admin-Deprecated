/**
 * ClassName  DiscussionWebAction
 *
 * History
 * Create User: liuqinggang
 * Create Date: May 16, 2011
 * Update User:
 * Update Date:
 */
package com.shangde.edu.dis.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.messageRemind.MessageRemindUtil;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.dis.condition.QueryCusUserDisCondition;
import com.shangde.edu.dis.condition.QueryDiscussionCondition;
import com.shangde.edu.dis.condition.QueryTopicCondition;
import com.shangde.edu.dis.domain.CusUserDis;
import com.shangde.edu.dis.domain.Discussion;
import com.shangde.edu.dis.domain.Tags;
import com.shangde.edu.dis.domain.Topic;
import com.shangde.edu.dis.domain.TopicReply;
import com.shangde.edu.dis.domain.Vote;
import com.shangde.edu.dis.domain.VoteDetail;
import com.shangde.edu.dis.dto.DisCustomerDTO;
import com.shangde.edu.dis.dto.DisDTO;
import com.shangde.edu.dis.dto.DisReTopicDTO;
import com.shangde.edu.dis.dto.SubjectDTO;
import com.shangde.edu.dis.service.ICusUserDis;
import com.shangde.edu.dis.service.IDiscussion;
import com.shangde.edu.dis.service.ITags;
import com.shangde.edu.dis.service.ITagsRelation;
import com.shangde.edu.dis.service.ITopic;
import com.shangde.edu.dis.service.ITopicReply;
import com.shangde.edu.dis.service.IVote;
import com.shangde.edu.dis.service.IVoteDetail;
import com.shangde.edu.dis.service.IVoteLog;
import com.shangde.edu.sys.service.ISubject;

/**
 * 讨论小组用
 * 
 * @author liuqinggang
 * @author Libg
 * @author ...
 */

public class DiscussionWebAction extends CommonAction {

	private static final long serialVersionUID = -8565950685572664192L;
	private static Logger log = Logger.getLogger(DiscussionWebAction.class);

	private List<UserCenterCourseDTO> userCourseList;// 用户课程集合

	/** action 动作类型标识 */

	private Topic topic;
	// 课程查询条件
	private QueryCourseCondition queryCourseCondition;
	/** 话题区域映射表 */
	private QueryCusUserDisCondition queryCusUserDisCondition;
	/* 区域映射业务接口 */
	private ICusUserDis cusUserDisService;

	private ISubject subjectService;

	private ICustomer customerService;

	private int disId;
	
	
	/** 讨论组业务 */
	private IDiscussion discussionService;
	/* 讨论组查询条件 */
	private QueryDiscussionCondition queryDiscussionCondition;

	/* 话题业务 */
	private ITopic topicService;
	/* 话题查询条件 */
	private QueryTopicCondition queryTopicCondition;

	/* 话题列表集合Map用于首页，包装最新话题、发布的话题 */
	private Map<String, List<Topic>> topicMap = new HashMap<String, List<Topic>>();
	/* 话题列表 */
	private List<Topic> topicList = new ArrayList<Topic>();
	/* 热门话题列表 */
	private List<Topic> hotList = new ArrayList<Topic>();

	/* 话题表回复业务对象 */
	private ITopicReply replyService;

	/* 话题回复列表 */
	private List<TopicReply> replyList = new ArrayList<TopicReply>();


	// 组成员用户信息列表
	List<DisCustomerDTO> disCusList = new ArrayList<DisCustomerDTO>();

	

	// 投票服务
	private IVote voteService;
	private IVoteDetail voteDetailService;
	private IVoteLog voteLogService;
	private Vote vote;// 投票
	// 投票详细信息
	private List<VoteDetail> voteDetailList;
	private List<String> voteDetailIds;// 存储voteDetailId集合

	private Integer voteMaxCount = 0;
	private Integer voteCusFlag = 0;// 当前登录用户是否对当前所浏览的话题且为投票贴，进行投票 非0表示 以投票
	private SubjectDTO subjectDTO;

	private String jsonResult;
	
	/*****V1.1属性声明区域 开始*****/
	
	
	//用户id
	private int userId;
	//客户
	private Customer customer;
	// 组关系对象
	private CusUserDis cusUserDis = null;
	private List<CusUserDis> cusUserDisList = new ArrayList<CusUserDis>();
	// if session 存有已购买专业的信息ID
	List<SubjectDTO> buySubjects = new ArrayList<SubjectDTO>();
	/* 所有小组列表 */
	private List<Discussion> disAll = new ArrayList<Discussion>();
	/* 小组列表 */
	private List<Discussion> disList = new ArrayList<Discussion>();
	//讨论组
	private Discussion discussion;
	
	//我回复的话题列表
	private List<DisReTopicDTO> disReTopicList = new ArrayList<DisReTopicDTO>();
	
	//所在的组织列表（+已加入的小组）
	private List<DisDTO> disOrganizeList;
	//标签服务
	private ITags tagsService;
	//标签关联服务
	private ITagsRelation tagsRelationService;
	
	/**
	 * 显示回复默认项
	 */
	private int replyDisCountDefault = com.shangde.edu.dis.utils.Utils.REPLY_DIS_COUNT;
	
	//用户在小组中所有消息提示数量
	private int userDisMessageCount = 0;
	
	
	/*****V1.1属性声明区域 结束*****/
	
	


	/*****V1.1方法(method)声明区域 开始*****/

	
	/**
	 * 跳转到讨论组首页
	 * @return
	 */
	public String toDisHomepage() {
		
		String disId = null;//小组id
		try{
			// 取登陆用户ID
			userId = getLoginUserId();
			//购买的专业列表
			buySubjects = this.getCusUserDisService().getMyBuySubject(userId);
			//未购买课程，不开发讨论小组功能
			if(buySubjects.size() == 0){
				return "no_auth";
			}
			
			//从session中取得该用户默认小组id
			disId = getSession("session_disId");
			// 根据小组ID初始化首页数据信息
			initInfo(Integer.parseInt(disId));
		}catch (Exception e) {
			log.error("进入小组首页-错误（类型转换）", e);
		}
		//进入首页
		return "toDisHomepage";
	}
	
	/**
	 * 跳转到讨论组首页
	 * 
	 * @return
	 */
	public String toDisHomepage_bak() {
		
		// 取登陆用户ID
		userId = getLoginUserId();
		// 初始化用户
		if (getSession("customer") == null) {
			customer = customerService.getCustomerById(userId);
			setSession("customer", customer);
		}

		Integer subjectId = Integer.valueOf(CookieHandler.getCookieValueByName(
				servletRequest, "subjectId"));
		Integer disId = null;

		// this.getSession("cus_user_dis_list");
		cusUserDisList = new ArrayList<CusUserDis>();
		// 查询出用户所在的小组列表
		cusUserDisList = this.cusUserDisService.findCusUserDisByCusId(userId);
		// buySubjects = (List<SubjectDTO>) this.getSession("buy_subjects");

		// ~ 用户是否购买过专业
		buySubjects = this.getCusUserDisService().getMyBuySubject(userId);
		// 需要操作的关系映射
		List<CusUserDis> himCudList = new ArrayList<CusUserDis>();
		// 根据购买的专业需要加入的所有小组
		List<Discussion> himDis = new ArrayList<Discussion>();

		// 小组信息
		try {

			// ~ 已购买专业
			if (buySubjects.size() > 0) {
				log.info("in--buySubjects");
				this.setSubjectDTO(buySubjects.get(0));// 首页
				// 第一个专业ID
				subjectId = buySubjects.get(0).getSubjectId();

				// 查询所有小组信息
				disAll = this.getDiscussionService().getAllDiscussion();
				log.info("查询所有小组信息");
				// 将用户添加到所购买的专业中不同的小组中
				// 取到用户需要加入的所有小组
				for (SubjectDTO dto : buySubjects) {
					for (Discussion dis : disAll) {
						if (dto.getSubjectId().equals(dis.getSubjectId())) {
							himDis.add(dis);
						}
					}
				}
				// 第一个购买的权限组
				disId = himDis.get(0).getId();
				log.info("one buy dis" + this.disId);
				// 取现在用户的小组映射关系表
				// cusUserDisList = this.cusUserDisService.findCusUserDisByCusId(userId);
				log.info("get cus UserDisList " + cusUserDisList.size());
				if (cusUserDisList.size() > 0) {

					// 用户应该加入的小组，关系映射
					List<CusUserDis> tempCusList = new ArrayList<CusUserDis>();
					log.info("in to temp_cus_list");
					// 根据用户购买的专业判断：
					// 1.用户是否已加入该专业下的小组
					// 2.用户是否已有权限
					// 3.将没有权限的加入待更新

					for (Discussion dis : himDis) {
						CusUserDis cTemp = new CusUserDis();
						cTemp.setAuth(0);
						cTemp.setCusId(userId);
						cTemp.setDisId(dis.getId());
						tempCusList.add(cTemp);
					}

					// 判断现有映射的权限
					for (CusUserDis c : tempCusList) {
						for (CusUserDis cNow : cusUserDisList) {
							if (c.getDisId().equals(cNow.getDisId())) {
								if (c.getAuth().equals(0)) {
									c.setId(cNow.getId());
									c.setCusId(cNow.getCusId());//
									c.setAuth(1);
									c.setDisId(cNow.getDisId());//
								}
							}
						}
						// 待添加更新的关系List
						himCudList.add(c);
					}

					// 初始或更新小组查询条件
					for (CusUserDis himCud : himCudList) {
						this.cusUserDisService.saveOrUpdate(himCud);
						cusUserDisList.add(himCud);
					}

					this.setSession("cus_user_dis_list", cusUserDisList);

					// this.getDiscussion().setIntroduction(StringUtil.htmlspecialchars(this.getDiscussion().getIntroduction()));
					// 当前组信息列表（用于界面展示）后期修改
					// disList = new ArrayList<Discussion>();
					// disList.add(this.getDiscussion());
					// this.setDisList(disList);
					// 将组信息设置到Session中
					// this.setSession("dis_list", disList);
					setSession("session_auth", 1);
				} else {// ~将用户与小组的映射添加到数据库中
					
					// 购买过的专业，加入小组后是有权限发表文章的
					// TODO: 为了前期减少不必要的查询，此处为临时解决方案
					if (buySubjects.size() > 1) {
						// 设置小组查询条件
						for (SubjectDTO dto : buySubjects) {
							disId = initCusUserDis(dto.getSubjectId(), 1);
						}
					} else {
						disId = initCusUserDis(subjectId, 1);
					}
				}
			} else {/* 用户未购买课程，使用默认专业 */

				/**
				 * 这里临时注释，目前业务是：购买过课程进入讨论小组，否则进入 尚未购买课程提示页面
				 */
				/**
				if (cusUserDisList.size() == 0) {
					disId = initCusUserDis(subjectId, 0); 
				} else {
					disId = cusUserDisList.get(0).getDisId();
					this.setSession("cus_user_dis_list", cusUserDisList);
					//本业务属于未购买课程范围，所以设置为0
					setSession("session_auth", 0);
				}
				*/
				//部署正式上线时 如下注释要释放
				return "no_auth";
			}

			// 取到当前专业下的组列表
			disList = discussionService.getDisBySubjectId(subjectId);
			// log.info("get dis list ...");

			this.setSession("dis_list", disList);

			// 根据小组ID初始化首页数据信息
			initInfo(disId);

		} catch (Exception e) {
			log.error("跳转到讨论小组错误", e);
		}

		// 当前用户权限值
		// int auth = Utils.topicRoleVerify(userId,(List<CusUserDis>)getSession("cus_user_dis_list"));
		// setSession("session_auth", auth);
		log.info("return  dis_home");

		return "toDisHomepage";
	}

	/**
	 * 初始化相关数据并将数据设置到Session当中
	 * 
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	private void initInfo(int disId) {
		log.info("init info....");
		
		// 取登陆用户ID
		userId = getLoginUserId();
		// 取小组下的热闹话题
		initHotTopic(disId);
		//查询小组话题数量
		initDisTopicCount(disId);
		//查询已加入的小组列表
		initDisOrganizeList(disId,userId);
		//初始化标签列表（只加载一次）
		initTags();
		//获取个人在所有小组中的消息数
		initMessageCount(userId);
		
		// 初始化组信息
		this.setDiscussion(this.discussionService.getDiscussionById(disId));
		this.setSession("dis_info", this.getDiscussion());
		// 根据 disId 查询专业
		disList = new ArrayList<Discussion>();
		disList.add(this.getDiscussion());
		this.setSession("dis_list", disList);
		
		// 话题查询条件
		// TODO: 现在组ID与专业ID一对一，后期如果每个专业下有多组的需求，这里将进行条件查询获取
		this.getQueryTopicCondition().setDisId(disId);
		this.getQueryTopicCondition().setCusId(userId);
		this.getQueryTopicCondition().setPageSize(10);
		
		topicMap = new HashMap<String, List<Topic>>();
		// 查询最新话题
		topicMap.put("new", topicService.getNewTopic(this.getQueryTopicCondition()));
		// 查询我发布的话题
		topicMap.put("post", topicService.getMyTopic(this.getQueryTopicCondition()));
		// 查询我回复的话题
		disReTopicList = topicService.getReTopic(this.getQueryTopicCondition());
		//查询推荐的话题
		topicMap.put("rec", topicService.getRecommend(queryTopicCondition));
		
		setPageUrlParms();
		
		if (this.getSession("dis_all_list") == null || this.getDisAll().size() <= 0) {
			// 查询所有小组信息
			this.setDisAll(discussionService.getDiscussionList(queryDiscussionCondition));
			log.info("query all dis...");
			this.setSession("dis_all_list", this.getDisAll());
		} else {
			this.setDisAll((List<Discussion>) this.getSession("dis_all_list"));
		}
		
		// 活跃成员列表
		if (this.getSession("dis_cus_list") == null || this.getDisCusList().size() <= 0) {
			// 取组下的所有成员
			disCusList = this.getDiscussionService().findCustomerByDisId(disId);
			log.info("dis cus List" + disCusList.size());
			this.setSession("dis_cus_list", disCusList);
		}
	}
	/**
	 * 获取最热话题
	 * 
	 * @param disId
	 */
	private void initHotTopic(int disId) {
		hotList = this.getTopicService().getHotTopicByDisId(disId);
		
		try{
			if(hotList != null && hotList.size() > 0){
				//特殊标签过滤
				for(Topic topic : hotList){
					topic.setContent(com.shangde.edu.dis.utils.StringUtil.getTxtWithoutHTMLElement(topic.getContent()));
				}	
			}
		}catch (Exception e) {
			log.error("热门话题-特殊字符过滤出错", e);
		}
		
		this.setSession("hot_list", hotList);
	}
	/**
	 * 获取小组话题数量
	 * @param disId
	 */
	private void initDisTopicCount(int disId){
		this.setSession("topic_count", topicService.getDisTopicCount(disId));
	}
	
	/**
	 * 获取自己的组织列表（+已加入的小组）
	 * 
	 * @param disId
	 * @param cusId
	 */
	private void initDisOrganizeList(int disId,int cusId){
		disOrganizeList = discussionService.getDisOrganizeList(cusId);
		
		int disStatus = 0;//在小组的状态【0=未加入、1=已加入】
		if(disOrganizeList != null){
			for(DisDTO dis : disOrganizeList){
				//校验当前浏览的小组是否已加入某个小组(判断业务)
				if(dis.getDiscussion().getId() == disId){
					disStatus = 1;
					break;
				}
			}
			//将已加入的小组列表储存在session范围内
			setSession("disOrganizeList", disOrganizeList);
		}
		setSession("disStatus", disStatus);
	}
	
	/**
	 * 初始化所有可用标签
	 */
	private void initTags(){
		ActionContext context = ActionContext.getContext();
		
		String key = "app_tags_list";
		List<Tags> value = null;
		
		List<Tags> tags_list = (List<Tags>) context.get(key);
		if(tags_list == null){
			value = tagsService.getTagsList();
			
			
			Map application = context.getApplication();
			application.put(key, value);
		}
	}
	
	/**
	 * 初始化用户在所有小组中消息数
	 * @param cusId
	 */
	private void initMessageCount(int cusId){
		try{
			userDisMessageCount = MessageRemindUtil.getMessageCount(cusId, 3);
		}catch (Exception e) {
			log.error("初始化用户消息个数出错", e);
		}
	}
	
	/**
	 * 初始化 CusUserDis数据
	 * 
	 * 将用户添加到小组中
	 * 
	 */
	private Integer initCusUserDis(int subjectId, int auth) {
		setSession("session_auth", auth);
		// 根据专业查询组
		// this.getQueryDiscussionCondition().setSubjectid(subjectId);
		// 取到当前专业下的组列表
		List<Discussion> dList = discussionService.getDisBySubjectId(subjectId);

		// 遍历当前专业组，将用户关系添加
		for (Discussion d : dList) {
			// 将用户加入到话题组中
			cusUserDis = new CusUserDis();
			cusUserDis.setCusId(userId);
			cusUserDis.setDisId(d.getId());
			cusUserDis.setAuth(auth);

			this.cusUserDisService.addCusUserDis(cusUserDis);
			
			// 更新小组用户数据
			d.setMember(d.getMember() + 1);
			this.discussionService.updateDiscussion(d);
			cusUserDisList.add(cusUserDis);
		}
		this.setSession("cus_user_dis_list", cusUserDisList);
		
		return cusUserDisList.get(0).getDisId();
	}

	/**
	 * 跳转到其它小组
	 * @throws IOException 
	 */
	public String goDis() throws IOException {

		this.getServletResponse().getWriter().print(getSession("session_auth").toString());		
		return null;
	}
	
	/**
	 * 校验 session_auth 对象是否有效
	 * @return
	 */
	public String verifySessionAuth(){
		
		if(getSession("session_auth") == null){
			jsonResult = "-1";//表示无效
		}else{
			jsonResult = "1";
		}
		
		return "jsonResult";
	}
	
	/**
	 * 检查小组权限验证
	 */
	public String toDis()
	{
		initInfo(disId);
		return "other_dis_home";
	}
	
	
	
	
	
	
	
	
	
	
	public int getReplyDisCountDefault() {
		return replyDisCountDefault;
	}


	public void setReplyDisCountDefault(int replyDisCountDefault) {
		this.replyDisCountDefault = replyDisCountDefault;
	}
	
	
	public List<DisReTopicDTO> getDisReTopicList() {
		return disReTopicList;
	}


	public void setDisReTopicList(List<DisReTopicDTO> disReTopicList) {
		this.disReTopicList = disReTopicList;
	}
	
	
	public List<DisDTO> getDisOrganizeList() {
		return disOrganizeList;
	}


	public void setDisOrganizeList(List<DisDTO> disOrganizeList) {
		this.disOrganizeList = disOrganizeList;
	}
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
	
	/*****V1.1方法(method)声明区域 结束*****/
	
	
	private String disPicUrl;
	public String getDisPicUrl() {
		return disPicUrl;
	}

	public void setDisPicUrl(String disPicUrl) {
		this.disPicUrl = disPicUrl;
	}
	
	public List<UserCenterCourseDTO> getUserCourseList() {
		return userCourseList;
	}

	public void setUserCourseList(List<UserCenterCourseDTO> userCourseList) {
		this.userCourseList = userCourseList;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IDiscussion getDiscussionService() {
		return discussionService;
	}

	public void setDiscussionService(IDiscussion disService) {
		this.discussionService = disService;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

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
	 * 获取 TopicService
	 * 
	 * @return the topicService
	 */
	public ITopic getTopicService() {
		return topicService;
	}

	/**
	 * 设置TopicService
	 * 
	 * @param topicService
	 *            the topicService to set
	 */
	public void setTopicService(ITopic topicService) {
		this.topicService = topicService;
	}

	/**
	 * @return the queryTopicCondition
	 */
	public QueryTopicCondition getQueryTopicCondition() {
		if (this.queryTopicCondition == null) {
			this.queryTopicCondition = new QueryTopicCondition();
		}
		return queryTopicCondition;
	}

	/**
	 * @param queryTopicCondition
	 *            the queryTopicCondition to set
	 */
	public void setQueryTopicCondition(QueryTopicCondition queryTopicCondition) {
		this.queryTopicCondition = queryTopicCondition;
	}

	/**
	 * @return the replyService
	 */
	public ITopicReply getReplyService() {
		return replyService;
	}

	/**
	 * @param replyService
	 *            the replyService to set
	 */
	public void setReplyService(ITopicReply replyService) {
		this.replyService = replyService;
	}

	/**
	 * @return the topicList
	 */
	public List<Topic> getTopicList() {
		return topicList;
	}

	/**
	 * @param topicList
	 *            the topicList to set
	 */
	public void setTopicList(List<Topic> topicList) {
		this.topicList = topicList;
	}

	/**
	 * @return the topicMap
	 */
	public Map<String, List<Topic>> getTopicMap() {
		return topicMap;
	}

	/**
	 * @param topicMap
	 *            the topicMap to set
	 */
	public void setTopicMap(Map<String, List<Topic>> topicMap) {
		this.topicMap = topicMap;
	}

	/**
	 * @return the replyList
	 */
	public List<TopicReply> getReplyList() {
		return replyList;
	}

	/**
	 * @param replyList
	 *            the replyList to set
	 */
	public void setReplyList(List<TopicReply> replyList) {
		this.replyList = replyList;
	}

	/**
	 * @return the disList
	 */
	public List<Discussion> getDisList() {
		return disList;
	}

	/**
	 * @param disList
	 *            the disList to set
	 */
	public void setDisList(List<Discussion> disList) {
		this.disList = disList;
	}

	/**
	 * @return the queryCourseCondition
	 */
	public QueryCourseCondition getQueryCourseCondition() {
		if (queryCourseCondition == null) {
			this.queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	/**
	 * @param queryCourseCondition
	 *            the queryCourseCondition to set
	 */
	public void setQueryCourseCondition(QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	/**
	 * @return the queryCusUserDisCondition
	 */
	public QueryCusUserDisCondition getQueryCusUserDisCondition() {
		if (this.queryCusUserDisCondition == null) {
			this.queryCusUserDisCondition = new QueryCusUserDisCondition();
		}
		return queryCusUserDisCondition;
	}

	/**
	 * @param queryCusUserDisCondition
	 *            the queryCusUserDisCondition to set
	 */
	public void setQueryCusUserDisCondition(QueryCusUserDisCondition queryCusUserDisCondition) {
		this.queryCusUserDisCondition = queryCusUserDisCondition;
	}

	/**
	 * @return the cusUserDisService
	 */
	public ICusUserDis getCusUserDisService() {
		return cusUserDisService;
	}

	/**
	 * @param cusUserDisService
	 *            the cusUserDisService to set
	 */
	public void setCusUserDisService(ICusUserDis cusUserDisService) {
		this.cusUserDisService = cusUserDisService;
	}

	/**
	 * @return the cusUserDis
	 */
	public CusUserDis getCusUserDis() {
		return cusUserDis;
	}

	/**
	 * @param cusUserDis
	 *            the cusUserDis to set
	 */
	public void setCusUserDis(CusUserDis cusUserDis) {
		this.cusUserDis = cusUserDis;
	}

	/**
	 * @return the cusUserDisList
	 */
	public List<CusUserDis> getCusUserDisList() {
		return cusUserDisList;
	}

	/**
	 * @param cusUserDisList
	 *            the cusUserDisList to set
	 */
	public void setCusUserDisList(List<CusUserDis> cusUserDisList) {
		this.cusUserDisList = cusUserDisList;
	}

	/**
	 * @return the disAll
	 */
	public List<Discussion> getDisAll() {
		return disAll;
	}

	/**
	 * @param disAll
	 *            the disAll to set
	 */
	public void setDisAll(List<Discussion> disAll) {
		this.disAll = disAll;
	}

	/**
	 * @return the disCusList
	 */
	public List<DisCustomerDTO> getDisCusList() {
		return disCusList;
	}

	/**
	 * @param disCusList
	 *            the disCusList to set
	 */
	public void setDisCusList(List<DisCustomerDTO> disCusList) {
		this.disCusList = disCusList;
	}

	// ~ 获取最热帖子
	/**
	 * @return the hotList
	 */
	public List<Topic> getHotList() {
		return hotList;
	}

	/**
	 * @param hotList
	 *            the hotList to set
	 */
	public void setHotList(List<Topic> hotList) {
		this.hotList = hotList;
	}

	/**
	 * @return the buySubjects
	 */
	public List<SubjectDTO> getBuySubjects() {
		return buySubjects;
	}

	/**
	 * @param buySubjects
	 *            the buySubjects to set
	 */
	public void setBuySubjects(List<SubjectDTO> buySubjects) {
		this.buySubjects = buySubjects;
	}

	/**
	 * @return the disId
	 */
	public int getDisId() {
		return disId;
	}

	/**
	 * @param disId
	 *            the disId to set
	 */
	public void setDisId(int disId) {
		this.disId = disId;
	}

	public IVote getVoteService() {
		return voteService;
	}

	public void setVoteService(IVote voteService) {
		this.voteService = voteService;
	}

	public IVoteDetail getVoteDetailService() {
		return voteDetailService;
	}

	public void setVoteDetailService(IVoteDetail voteDetailService) {
		this.voteDetailService = voteDetailService;
	}

	public IVoteLog getVoteLogService() {
		return voteLogService;
	}

	public void setVoteLogService(IVoteLog voteLogService) {
		this.voteLogService = voteLogService;
	}

	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List<VoteDetail> getVoteDetailList() {
		return voteDetailList;
	}

	public void setVoteDetailList(List<VoteDetail> voteDetailList) {
		this.voteDetailList = voteDetailList;
	}

	public List<String> getVoteDetailIds() {
		return voteDetailIds;
	}

	public void setVoteDetailIds(List<String> voteDetailIds) {
		this.voteDetailIds = voteDetailIds;
	}

	public Integer getVoteMaxCount() {
		return voteMaxCount;
	}

	public void setVoteMaxCount(Integer voteMaxCount) {
		this.voteMaxCount = voteMaxCount;
	}

	public Integer getVoteCusFlag() {
		return voteCusFlag;
	}

	public void setVoteCusFlag(Integer voteCusFlag) {
		this.voteCusFlag = voteCusFlag;
	}

	public int getUserId() {
		return userId;
	}

	/**
	 * @return the subjectDTO
	 */
	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	/**
	 * @param subjectDTO
	 *            the subjectDTO to set
	 */
	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public int getUserDisMessageCount() {
		return userDisMessageCount;
	}
	public void setUserDisMessageCount(int userDisMessageCount) {
		this.userDisMessageCount = userDisMessageCount;
	}
}
