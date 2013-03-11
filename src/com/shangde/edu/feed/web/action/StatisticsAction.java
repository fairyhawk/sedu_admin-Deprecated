package com.shangde.edu.feed.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.DESCoder;
import com.shangde.common.util.DateUtil;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.feed.condition.QueryUserStatCondition;
import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.domain.AdStat;
import com.shangde.edu.feed.domain.BrowseLog;
import com.shangde.edu.feed.domain.Entrance;
import com.shangde.edu.feed.domain.UniqueRecord;
import com.shangde.edu.feed.domain.UserStat;
import com.shangde.edu.feed.domain.UserStatLog;
import com.shangde.edu.feed.domain.UserUse;
import com.shangde.edu.feed.service.IAdStat;
import com.shangde.edu.feed.service.IBrowseLog;
import com.shangde.edu.feed.service.IEntrance;
import com.shangde.edu.feed.service.IFrom;
import com.shangde.edu.feed.service.IReview;
import com.shangde.edu.feed.service.ITaskList;
import com.shangde.edu.feed.service.ITaskLog;
import com.shangde.edu.feed.service.IUniqueRecord;
import com.shangde.edu.feed.service.IUserStat;
import com.shangde.edu.feed.service.IUserStatLog;
import com.shangde.edu.feed.service.IUserUse;
import com.shangde.edu.feed.service.IVideoLog;
import com.shangde.edu.feed.utils.Utils;

/**
 * 数据统计,前台
 * 
 * @author Libg
 *
 */
public class StatisticsAction extends CommonAction {

	private static Logger logger = Logger.getLogger(StatisticsAction.class);
	
	/** 服务接口 */
	private ITaskList taskListService;
	private ITaskLog taskLogService;
	private IUniqueRecord feedUniqueRecordService;
	private IVideoLog videoLogService;
	private ICustomer customerService;
	private IAdStat adStatService;
	private IUserUse userUseService;
	private IUserStat userStatService;
	private IBrowseLog browseLogService;
	private IFrom fromService;//来源服务
	private IEntrance entranceService;//微学习，入口接口
	private IUserStatLog userStatLogService;//使用用户log
	private IReview reviewService;//提问，留言服务

	/** domain接口 */
	
	
	/** 查询domain接口 */
	private QueryUserStatCondition queryUserStatCondition;
	private QueryUserStatLogCondition queryUserStatLogCondition;
	
	
	/** 常量 */
	private Integer id;//id
	private String taskId;// 任务id
	private String e;// 加密后的邮件值
	private String jsonResult;// json返回值
	private String videoId;// 视频id
	private String subjectId;//专业id
	private String adId;//广告id
	private Integer type;//类型[1,2,3,4],详情请看;com.shangde.edu.feed.domain.UserUse[type]属性
	private String email;//邮箱
	private Integer videoSecond;//视频秒数
	private String from;//from名称，当点击左侧菜单“微学习”时,该值有效
	private String fromId;//来源请查看feed_from_tbl表
	private String fromIdDefault = "4";//来源默认值,[4=其他来源]
	
	private int reviewSign;//提问（回复）状态表示：1=支持,2=反对

	/** 集合对象 */

	/**
	 * 获取
	 */
	private int getTaskFromIdCookie(){
		
		String sedu_feed_task_fromId_cookie = CookieHandler.getCookieValueByName(getServletRequest(), "sedu_feed_task_fromId_cookie");//任务taskId按Cookie名称取值
		int taskFromId = -1;
		if(sedu_feed_task_fromId_cookie == null || sedu_feed_task_fromId_cookie.equals("")){
			
			try{
				/*
				//只要进入该页面，如果获取不到cookie值默认值是“其他”
				if(getServletRequest().getHeader("referer").indexOf("register_mg.html") != -1){
					sedu_feed_task_fromId_cookie = "4";//4表示，其他来源到达入口页面
				}else{
					sedu_feed_task_fromId_cookie = "5";//5表示，不是从微学习入口页过来的流量,网站其他入口	
				}*/
				sedu_feed_task_fromId_cookie = "4";
			}catch (Exception e) {
				logger.error("校验来源cookie值失败-->",e);
			}
		}
		
		taskFromId = Integer.parseInt(sedu_feed_task_fromId_cookie);
		
		return taskFromId;
	}
	
	/**
	 * 统计通过邮箱中的链接请求数据
	 * 
	 * 激活统计，只统计用户第一次访问
	 * 
	 * 将任务id放入cookie中,提供其他部分使用
	 * 
	 * @return
	 */
	public String activationStat() {
		try{
		boolean flag = true;
		String email = null;
		Map<String,String> map = new HashMap<String,String>();
		String sedu_feed_taskId_cookie = CookieHandler.getCookieValueByName(getServletRequest(), "sedu_feed_taskId_cookie");//任务taskId按Cookie名称取值
		
		// 将视频id放到cookie中
		CookieHandler.createCookie(getServletResponse(),"sedu_feed_task_videoId_cookie", videoId, 1);
		
		
		//TODO: 这里查询校验 fromId值是否有效,无效采用默认值4(其他)
		//如果校验失败采用默认值
		try{
			//条件成立，校验这个id是否在DB中已经存在,不存在采用默认值
			if(fromId != null){
				if(fromService.getFromCount(Integer.parseInt(fromId)) == 0){
					fromId = getFromIdDefault();
				}
			}else{
				fromId = getFromIdDefault();
			}
		}catch (Exception e) {
			fromId = getFromIdDefault();
			logger.error("来源id值校验失败",e);
		}
		// 将来源id放到cookie中
		CookieHandler.createCookie(getServletResponse(),"sedu_feed_task_fromId_cookie", fromId, 1);
		
		
		
		//错误处理,不满足条件，直接返回
		try{
			if(taskId == null || videoId == null || e == null){
				return "microStudy";
			}
		}catch (Exception e) {
			return "microStudy";
		}
		
		
		/**校验：如果该cookie已存在，并且等于当前请求任务id值,则则终止执行*/
		if(sedu_feed_taskId_cookie != null && sedu_feed_taskId_cookie.equals(taskId)){
			return "microStudy";
		}
		
		
		logger.info("--StatisticsAction-------------param-e-->" + e);
		logger.info("--StatisticsAction-------------param-taskId-->" + taskId);
		try {
			e = e.replaceAll(" ", "+");
			logger.info("--StatisticsAction---char-replace-------------param-e-->" + e);
			email = DESCoder.decrypt(e);// 解密后的值
			logger.info("--StatisticsAction---char-replace-------------param-email-->" + email);
			map.put("email", email);
			map.put("encryptEmail", e);
			map.put("objectId", taskId);
			map.put("type", "1");
		} catch (Exception e1) {
			flag = false;
			logger.error("编码解析出错",e1);
		}
		//解析正常时，才继续执行业务
		if(flag){
			Map<String,String> idEmailMap = new HashMap<String,String>();
			idEmailMap.put("id", taskId);
			idEmailMap.put("email", email);
			// 如果条件成立，说明当前邮件在当前任务中
			if (taskListService.getTaskListByIdEmail(idEmailMap) > 0) {
				int count = feedUniqueRecordService.getUniqueRecord(map);
				// 如果等于0说明还未激活，则进行统计
				if (count == 0) {
					UniqueRecord uniqueRecord = new UniqueRecord();
					uniqueRecord.setEmail(email);
					uniqueRecord.setEncryptEmail(e);
					uniqueRecord.setObjectId(Integer.parseInt(taskId));//任务id
					uniqueRecord.setType(1);
					uniqueRecord.setPubTime(new Date());
					feedUniqueRecordService.addUniqueRecord(uniqueRecord);
	
					// 这里写修改任务激活次数字段业务
					Map<String,String> urlClickNumMap = new HashMap<String,String>();
					urlClickNumMap.put("incremental", "1");
					urlClickNumMap.put("taskListId", taskId);
					taskLogService.updateTaskLogUrlClickNum(urlClickNumMap);
				}
			}
			// 将任务id放到cookie中
			CookieHandler.createCookie(getServletResponse(),"sedu_feed_taskId_cookie", taskId, 1);
		}
		}catch(Exception e){
			logger.error("StatisticsAction.activationStat", e);
			return ERROR;
		}
		// 这里写跳转到前台微学习页面
		return "microStudy";
	}
	
	/**
	 * 修改激活次数,修改点击次数(观看次数)
	 * 
	 * @return
	 */
	public String doUpdateActiveNum() {
		
		int cusId = getLoginUserId();
		int vId = Integer.parseInt(videoId);//类型转换，避免下面多次转换

		String email = customerService.getCustomerById(cusId).getEmail();

		Map<String,String> map = new HashMap<String,String>();
		try{
			try {
				map.put("email", email);
				map.put("encryptEmail", DESCoder.encrypt(email));
				map.put("objectId", videoId);
				map.put("type", "2");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonResult = "1";//成功
			//修改点击次数(观看次数)
			videoLogService.updateClickNum(vId);
			// 查询当前用户是否已经激活过了
			//记录当前点击视频
			String sedu_feed_videoId_cookie = CookieHandler.getCookieValueByName(getServletRequest(), "sedu_feed_videoId_cookie");//任务taskId按Cookie名称取值
			
			/**校验：如果该cookie不存在，或者不等于当前请求视频id值,则继续执行*/
			if(sedu_feed_videoId_cookie == null || !sedu_feed_videoId_cookie.equals(videoId)){
				if (feedUniqueRecordService.getUniqueRecord(map) == 0) {
					videoLogService.updateActiveNum(vId);
					
					//添加记录
					UniqueRecord uniqueRecord = new UniqueRecord();
					uniqueRecord.setEmail(email);
					uniqueRecord.setEncryptEmail(DESCoder.encrypt(email));
					uniqueRecord.setObjectId(vId);
					uniqueRecord.setType(2);
					uniqueRecord.setPubTime(new Date());
					
					feedUniqueRecordService.addUniqueRecord(uniqueRecord);
					// 将视频id放到cookie中,记录当前点击视频id值
					CookieHandler.createCookie(getServletResponse(),"sedu_feed_videoId_cookie", videoId, 1);
				} else {
					jsonResult = "3";// 该用户已激活
				}
			}else{
				jsonResult = "3";// 该用户已激活
			}
		}catch (Exception e) {
			jsonResult = "0";// 失败
			logger.error("StatisticsAction.doUpdateActiveNum", e);
		}
		
		return "jsonResult";
	}

	/**
	 * 修改点击购买数
	 * 
	 * 根据专业id修改，
	 * 
	 * @return
	 */
	public String updateClickBuyNumBySubjectId(){
		
		try{
			if(subjectId != null && !subjectId.equals("")){
				if(videoLogService.updateClickBuyNum(Integer.parseInt(subjectId)) > 0){
					jsonResult = "1";//成功
				}else{
					jsonResult = "2";//修改失败
				}
			}
		}catch (Exception e) {
			jsonResult = "0";//异常
			logger.error("微学习->根据专业id修改点击购买数--异常", e);
		}
		
		return "jsonResult";
	}
	
	/**
	 * 修改点击购买成功次数
	 * 
	 * @return
	 */
	public String doUpdateBuyNum() {
		try{
		if (videoLogService.updateBuyNum(Integer.parseInt(videoId)) > 0) {
			jsonResult = "1";// 成功
		} else {
			jsonResult = "0";// 失败
		}
		}catch(Exception e){
			logger.error("StatisticsAction.doUpdateBuyNum", e);
		}
		return "jsonResult";
	}
	
	/**
	 * adStat，广告统计记录
	 * 
	 * 统计完成后，跳转到微学习入口页面（注册/登录）页面
	 * 
	 * 需要提供参数：adId=(num),fromId=(num)
	 * 
	 * @return
	 */
	public String adStat_Stat(){
		
		String ua = null;//ua,浏览器头部信息
		AdStat adStat = null;//广告统计数据模型
		int cusId = 0;//用户id
		int ad_id = 0;//广告id
		
		//TODO: 这里查询校验 fromId值是否有效,无效采用默认值4(其他)
		//如果校验失败采用默认值
		try{
			if(fromId != null){
				if(fromService.getFromCount(Integer.parseInt(fromId)) == 0){
					fromId = getFromIdDefault();
				}
			}else{
				fromId = getFromIdDefault();
			}
		}catch (Exception e) {
			fromId = getFromIdDefault();
			logger.error("来源id值校验失败",e);
		}
		
		// 将来源id放到cookie中
		CookieHandler.createCookie(getServletResponse(),"sedu_feed_task_fromId_cookie", fromId, 1);
		
		try{
			cusId = getLoginUserId();
		}catch (Exception e) {
			cusId = 0;
			logger.error("读取cookie-cusId出错,说明是未登录用户",e);
		}
		try{
			ua = Utils.getHeadUserAgent(getServletRequest());
			adStat = new AdStat();
			try{
				ad_id = Integer.parseInt(adId);
			}catch (Exception er) {
				ad_id = 0;
			}
			if(ad_id != 0){
				
				// 广告id放到cookie中
				CookieHandler.createCookie(getServletResponse(),"sedu_feed_task_adId_cookie", String.valueOf(ad_id), 1);
				
				adStat.setAdId(ad_id);
				adStat.setCusId(cusId);
				adStat.setIp(Utils.getIpAddr(getServletRequest()));
				adStat.setUa(ua);
				adStat.setPubDate(new Date());	
				adStatService.addAd(adStat);
			}
		}catch (Exception e) {
			logger.error("ad统计错误--->", e);
		}
		
		return "microStudy";
	}
	
	
	
	/**
	 * 注册，登录统计
	 * 
	 * 需要参数：type=[1=注册点击,2=注册成功,3=登录点击,4=登录成功]
	 * 
	 * @return
	 */
	public String regLogin_Stat() {
		
		UserUse userUse = null;
		
		int taskFromId = getTaskFromIdCookie();
		String utmaCookie = null;//市场部门-提供参数{每当用户登录/注册成功}该字段才有值
		
		try {
			utmaCookie = CookieHandler.getCookieValueByName(getServletRequest(), "__utma");
			//用户使用/注册/登录数据模型
			userUse = new UserUse();
			userUse.setIp(Utils.getIpAddr(getServletRequest()));
			userUse.setType(type);
			userUse.setPubDate(new Date());
			userUse.setUa(Utils.getHeadUserAgent(getServletRequest()));
			
			//这里设置fromId属性
			userUse.setFromId(taskFromId);
			
			if(type == 2 || type == 4){
				
				userUse.setUtma(utmaCookie);
				//TODO: 这部分监控一下数据是否正常
				try{
					logger.info("task_regLogin_Stat_date--->" + DateUtil.getCurrentDate());
					logger.info("StatisticsAction------>type-->" + type + "user reg_loginStat--->email--->" + email);
				}catch (Exception e) {
					logger.error("regLogin_Stat--->error", e);
				}

				Customer customer = customerService.getCustomerByEmail(email);
				logger.info("task_regLogin_Stat_customer_Object" + customer);
				if(customer != null){
					userUse.setCusId(customer.getCusId());
					userUse.setEmail(customer.getEmail());
				}
			}
			
			if (userUseService.addUserUse(userUse) > 0) {
				jsonResult = "1";// 成功
			} else {
				jsonResult = "0";// 失败
			}
		}catch (Exception e) {
			jsonResult = "0";
			logger.error("注册，登录接口统计出错--->",e);
		}
		
		return "jsonResult";
	}
	
	
	/**
	 * 用户统计,注册/登录,后针对用户跳转到微学习页面
	 * 
	 * @return
	 */
	public String userUse_Stat(){
		boolean flag = false;
		
		int taskFromId = getTaskFromIdCookie();
		int cusId = 0;
		try {
			cusId = getLoginUserId();
			if(email != null && !email.equals("")){
				if(type == 2){//注册成功
					executeRegUser(cusId, email, Integer.parseInt(subjectId),0,"reg",0,taskFromId);
				}else if(type == 4){//登录成功
					
					//根据cusId,email条件查询
					getQueryUserStatCondition().setCusId(cusId);
					getQueryUserStatCondition().setEmail(email);
					UserStat userStat = userStatService.getUserStatCountByCusIdEmail(queryUserStatCondition);
					//如果条件成立，说明这个用户已经存在
					if(userStat != null){
						//TODO: 这里不需要校验，用户是否在同一天内重复登录，在页面中登录成功之前已经校验是否cookie已失效
						
						userStat.setUseNum(userStat.getUseNum() + 1);
						userStat.setModified(new Date());
						
						if(userStatService.updateUserStat(userStat) > 0){
							flag = true;
						}
						//执行添加，每一次使用微学习，就记录一次
						createUserStatLog(cusId, email, taskFromId,1);
						
					}else{
						flag = executeRegUser(cusId, email, Integer.parseInt(subjectId),0,null,0,taskFromId);	
					}
				}
			}
		} catch (Exception e) {
			flag = false;
			logger.error("{注册/登录}--出错--》",e);
		}
		
		if (flag) {
			jsonResult = "1";// 成功
		} else {
			jsonResult = "0";// 失败
		}

		return "jsonResult";
	}
	
	/**
	 * 
	 * @param cusId 用户id
	 * @param email 邮箱
	 * @param subjectId 专业id
	 * @param totalLength 视频秒数
	 * @param sign 标记[reg,login/null]
	 * @param useClickNum 用户点击左边菜单“微学习”该值就是1
	 * @param fromId 来源标示
	 * 
	 * @return
	 */
	private boolean executeRegUser(int cusId,String email,int subjectId,int totalLength,String sign,int useClickNum,int fromId){
		boolean flag = false;
		UserStat userStat = new UserStat();
		Date now = new Date();
		try{
			userStat.setCusId(cusId);
			userStat.setEmail(email);
			userStat.setSubjectId(subjectId);
			//当等于reg时，才给regTime属性赋值
			
			if(sign != null && sign.equals("reg")){
				userStat.setRegTime(now);
				userStat.setRegLocation(1);//1=表示微学习
				userStat.setTotalLength(0);//观看视频时常默认0
				userStat.setUseNum(1);
			}else{
				//取得用户注册时间
				userStat.setRegTime(customerService.getCustomerById(cusId).getRegTime());
				userStat.setTotalLength(totalLength);//观看视频时常默认0
			}
			
			//如果条件成立说明，点击“左边菜单微学习”,这里需要重新替换
			if(useClickNum > 0){
				userStat.setUseNum(0);	
			}else {
				userStat.setUseNum(1);
			}
			
			userStat.setUseClickNum(useClickNum);
			userStat.setPubDate(now);
			userStat.setModified(now);
			
			if(userStatService.addUserStat(userStat) > 0){
				flag = true;
			}
			
			
			createUserStatLog(cusId, email, fromId,2);
		}catch (Exception e) {
			flag = false;
			logger.error("添加-使用用户出错-->", e);
		}
		return flag;
	}
	
	/**
	 * 添加使用用户记录,每当重新登录/点击左侧菜单“微学习”同一天只记录一次
	 * 
	 * @param cusId 用户id
	 * @param email 邮箱
	 * @param fromId 来源id
	 * @param status 状态[1=重新登录,2=左侧菜单]
	 */
	private void createUserStatLog(int cusId,String email,int fromId,int status){
		
		UserStatLog userStatLog = new UserStatLog();
		
		userStatLog.setCusId(cusId);
		userStatLog.setEmail(email);
		userStatLog.setFromId(fromId);
		userStatLog.setStatus(status);
		userStatLog.setPubDate(new Date());
		
		//TODO:这里少一个执行添加的操作
		
		userStatLogService.addUserStatLog(userStatLog);
	}
	
	/**
	 * 修改视频观看时长
	 * @return
	 */
	public String useVideoLookNum_Stat(){
		
		boolean flag = false;
		
		int cusId = getLoginUserId();
		int taskFromId = getTaskFromIdCookie();
		
		try{
			Customer customer = customerService.getCustomerById(cusId);
			if(customer != null){
				getQueryUserStatCondition().setCusId(customer.getCusId());
				getQueryUserStatCondition().setEmail(customer.getEmail());
				UserStat userStat = userStatService.getUserStatCountByCusIdEmail(getQueryUserStatCondition());
				//如果存在则修改相关属性，否则新增
				if(userStat != null){
					if(videoSecond > 0){
						userStat.setTotalLength(userStat.getTotalLength() + videoSecond);
						userStat.setModified(new Date());
						
						if(userStatService.updateUserStat(userStat) > 0){
							flag = true;
						}
					}
				}else{
					//这里需要配置totalLength参数值=videoSecond
					flag = executeRegUser(customer.getCusId(), customer.getEmail(), Integer.parseInt(subjectId),videoSecond, null,0,taskFromId);
				}
			}
		}catch (Exception e) {
			logger.error("修改用户视频观看时常--错误-->" ,e);
		}
		
		
		if (flag) {
			jsonResult = "1";// 成功
		} else {
			jsonResult = "0";// 失败
		}

		return "jsonResult";
	}
	
	
	/**
	 * 左侧‘微学习’点击数 统计
	 * 
	 * 需要参数subjectId,必须提供
	 * 
	 * @return
	 */
	private String useClickNum_Stat(){
		
		boolean flag = false;
		int cusId = getLoginUserId();
		
		int taskFromId = getTaskFromIdCookie();
		try{
			//浏览统计模块
			pageBrowse();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		try{
			Customer customer = customerService.getCustomerById(cusId);
			if(customer != null){
				getQueryUserStatCondition().setCusId(customer.getCusId());
				getQueryUserStatCondition().setEmail(customer.getEmail());
				UserStat userStat = userStatService.getUserStatCountByCusIdEmail(getQueryUserStatCondition());
				//如果存在则修改相关属性，否则新增
				if(userStat != null){
					//TODO: 这里判断是否在同一天内重复使用*****************************************
					getQueryUserStatLogCondition().setCusId(customer.getCusId());
					getQueryUserStatLogCondition().setEmail(customer.getEmail());
					getQueryUserStatLogCondition().setDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
					getQueryUserStatLogCondition().setFromId(taskFromId);
					//如果存在，修改使用次数，否则添加一条记录，当前使用的人
					if(userStatLogService.getByCusIdEmailDateCount(getQueryUserStatLogCondition()) == 0){
						//执行添加，每一次使用微学习，就记录一次
						createUserStatLog(customer.getCusId(), customer.getEmail(), taskFromId,2);
						
						userStat.setUseClickNum(userStat.getUseClickNum() + 1);
						userStat.setModified(new Date());
						
						if(userStatService.updateUserStat(userStat) > 0){
							flag = true;
						}
					}
				}else{
					//判断来源，如果是点击左侧菜单“微学习”则视为一次使用
					int useClickNum = 0;
					if(from != null && from.equals("weixuexi")){
						useClickNum = 1;
					}else{//这里表示，表示从其他部跳转而来,所以使用数为0
						useClickNum = 0;
					}
					flag = executeRegUser(customer.getCusId(), customer.getEmail(), Integer.parseInt(subjectId),0, null,useClickNum,taskFromId);
				}
			}
		}catch (Exception e) {
			flag = false;
			logger.error("修改点击左侧‘微学习’点击数错误-->",e);
		}

		if (flag) {
			jsonResult = "1";// 成功
		} else {
			jsonResult = "0";// 失败
		}

		return jsonResult;
	}
	
	/**
	 * 页面访问统计
	 * 
	 * 需要参数：type=(num)
	 * 
	 * @return
	 */
	private String pageBrowse() throws Exception{
		
		try {
			BrowseLog browseLog = new BrowseLog();
			browseLog.setCusId(getLoginUserId());
			browseLog.setIp(Utils.getIpAddr(getServletRequest()));
			browseLog.setPubDate(new Date());
			browseLog.setType(type);// 1=表示微学习,
			browseLog.setUa(Utils.getHeadUserAgent(getServletRequest()));
			browseLog.setFromId(getTaskFromIdCookie());
			
			browseLogService.addBrowseLog(browseLog);
		} catch (Exception e) {
			throw new Exception("页面浏览统计-错误-->");
		}
		
		return jsonResult;
	}
	
	/**
	 * 页面统计+点击左边菜单“微学习”
	 * @return
	 */
	public String pageBrowseOrUseClickNum_Stat(){
		
		jsonResult = useClickNum_Stat();		
		
		return "jsonResult";
	}
	
	/**
	 * 微学习，入口页统计
	 * @return
	 */
	public String entranceBrowse_Stat(){
		
		//TODO:这里从cookie中获取 adId,fromId值
		String adId = CookieHandler.getCookieValueByName(getServletRequest(), "sedu_feed_task_adId_cookie");//任务taskId按Cookie名称取值;
		int fromId = getTaskFromIdCookie();
		String ip = Utils.getIpAddr(getServletRequest());
		
		//默认值，无效广告
		if(adId == null || adId.equals("")){
			adId = "0";
		}
		try {
			
			Entrance entrance = new Entrance();
			
			entrance.setAdId(Integer.parseInt(adId));
			entrance.setFromId(fromId);
			entrance.setIp(ip);
			entrance.setPubDate(new Date());
			
			if(entranceService.addEntrance(entrance) > 0){
				jsonResult = "1";
			}else{
				jsonResult = "0";
			}
		} catch (Exception e) {
			logger.error("微学习，入口页面访问统计-出错", e);
		}
		
		return "jsonResult";
	}
	
	
	/**
	 * 修改留言基本信息，根据reviewSign参数判断
	 * 
	 * reviewSign = [1=支持,2=反对数]
	 * 
	 * @return
	 */
	public String updateReviewInfo(){
		
		try{
			jsonResult = "0";//执行失败
			
			if(reviewSign == 1){//修改提问，支持
				if(reviewService.updateSupportNumber(id) > 0){
					jsonResult = "1";//执行成功
				}
			}else if(reviewSign == 2){//修改提问，反对
				if(reviewService.updateAntilogNumber(id) > 0){
					jsonResult = "1";//执行成功
				}
			}/*else if(){
				
			}*/
			//有可以在其中
			
		}catch (Exception e) {
			logger.error("提问修改失败",e);
		}
		
		return "jsonResult";
	}
	
	
	
	public void d(){
		System.out.println("getServletRequest--->getQueryString-->"+getServletRequest().getQueryString());
		System.out.println("getServletRequest--->getRequestURL--->"+getServletRequest().getRequestURL());
		System.out.println("getServletRequest--->getRequestURI--->"+getServletRequest().getRequestURI());
	}
	
	public String test(){
		return "microStudy";
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public ITaskLog getTaskLogService() {
		return taskLogService;
	}

	public void setTaskLogService(ITaskLog taskLogService) {
		this.taskLogService = taskLogService;
	}

	public IUniqueRecord getFeedUniqueRecordService() {
		return feedUniqueRecordService;
	}

	public void setFeedUniqueRecordService(IUniqueRecord feedUniqueRecordService) {
		this.feedUniqueRecordService = feedUniqueRecordService;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public IVideoLog getVideoLogService() {
		return videoLogService;
	}

	public void setVideoLogService(IVideoLog videoLogService) {
		this.videoLogService = videoLogService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public ITaskList getTaskListService() {
		return taskListService;
	}

	public void setTaskListService(ITaskList taskListService) {
		this.taskListService = taskListService;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the adStatService
	 */
	public IAdStat getAdStatService() {
		return adStatService;
	}

	/**
	 * @param adStatService the adStatService to set
	 */
	public void setAdStatService(IAdStat adStatService) {
		this.adStatService = adStatService;
	}

	/**
	 * @return the adId
	 */
	public String getAdId() {
		return adId;
	}

	/**
	 * @param adId the adId to set
	 */
	public void setAdId(String adId) {
		this.adId = adId;
	}

	/**
	 * @return the userUseService
	 */
	public IUserUse getUserUseService() {
		return userUseService;
	}

	/**
	 * @param userUseService the userUseService to set
	 */
	public void setUserUseService(IUserUse userUseService) {
		this.userUseService = userUseService;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userStatService
	 */
	public IUserStat getUserStatService() {
		return userStatService;
	}

	/**
	 * @param userStatService the userStatService to set
	 */
	public void setUserStatService(IUserStat userStatService) {
		this.userStatService = userStatService;
	}

	/**
	 * @return the queryUserStatCondition
	 */
	public QueryUserStatCondition getQueryUserStatCondition() {
		
		if(queryUserStatCondition == null){
			queryUserStatCondition = new QueryUserStatCondition();
		}
		return queryUserStatCondition;
	}

	/**
	 * @param queryUserStatCondition the queryUserStatCondition to set
	 */
	public void setQueryUserStatCondition(
			QueryUserStatCondition queryUserStatCondition) {
		this.queryUserStatCondition = queryUserStatCondition;
	}

	/**
	 * @return the videoSecond
	 */
	public Integer getVideoSecond() {
		return videoSecond;
	}

	/**
	 * @param videoSecond the videoSecond to set
	 */
	public void setVideoSecond(Integer videoSecond) {
		this.videoSecond = videoSecond;
	}

	/**
	 * @return the browseLogService
	 */
	public IBrowseLog getBrowseLogService() {
		return browseLogService;
	}

	/**
	 * @param browseLogService the browseLogService to set
	 */
	public void setBrowseLogService(IBrowseLog browseLogService) {
		this.browseLogService = browseLogService;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the fromId
	 */
	public String getFromId() {
		return fromId;
	}

	/**
	 * @param fromId the fromId to set
	 */
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	/**
	 * @return the fromService
	 */
	public IFrom getFromService() {
		return fromService;
	}

	/**
	 * @param fromService the fromService to set
	 */
	public void setFromService(IFrom fromService) {
		this.fromService = fromService;
	}

	/**
	 * @return the entranceService
	 */
	public IEntrance getEntranceService() {
		return entranceService;
	}

	/**
	 * @param entranceService the entranceService to set
	 */
	public void setEntranceService(IEntrance entranceService) {
		this.entranceService = entranceService;
	}

	/**
	 * @return the userStatLogService
	 */
	public IUserStatLog getUserStatLogService() {
		return userStatLogService;
	}

	/**
	 * @param userStatLogService the userStatLogService to set
	 */
	public void setUserStatLogService(IUserStatLog userStatLogService) {
		this.userStatLogService = userStatLogService;
	}

	/**
	 * @return the queryUserStatLogCondition
	 */
	public QueryUserStatLogCondition getQueryUserStatLogCondition() {
		if(queryUserStatLogCondition == null){
			queryUserStatLogCondition = new QueryUserStatLogCondition();
		}
		return queryUserStatLogCondition;
	}

	/**
	 * @param queryUserStatLogCondition the queryUserStatLogCondition to set
	 */
	public void setQueryUserStatLogCondition(
			QueryUserStatLogCondition queryUserStatLogCondition) {
		this.queryUserStatLogCondition = queryUserStatLogCondition;
	}

	/**
	 * @return the fromIdDefault
	 */
	public String getFromIdDefault() {
		return fromIdDefault;
	}

	/**
	 * @return the reviewService
	 */
	public IReview getReviewService() {
		return reviewService;
	}

	/**
	 * @param reviewService the reviewService to set
	 */
	public void setReviewService(IReview reviewService) {
		this.reviewService = reviewService;
	}

	/**
	 * @return the reviewSign
	 */
	public int getReviewSign() {
		return reviewSign;
	}

	/**
	 * @param reviewSign the reviewSign to set
	 */
	public void setReviewSign(int reviewSign) {
		this.reviewSign = reviewSign;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}


}
