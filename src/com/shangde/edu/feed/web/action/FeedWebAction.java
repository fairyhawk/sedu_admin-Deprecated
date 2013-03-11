package com.shangde.edu.feed.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.feed.domain.SubscribeUser;
import com.shangde.edu.feed.dto.MicroVideoDTO;
import com.shangde.edu.feed.service.IBrowseLog;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.service.IReview;
import com.shangde.edu.feed.service.ISubscribeUser;
import com.shangde.edu.feed.utils.CompareByProperty;

/**
 * 微学习，前台业务处理
 * 
 * @author Libg
 * 
 */
public class FeedWebAction extends CommonAction {

	private static Logger logger = Logger.getLogger(FeedWebAction.class);
	
	/** 服务接口 */
	//private IQstRel qstRelService;
	private ISubscribeUser subscribeUserService;// 订阅用户
	private IBrowseLog browseLogService;
	private ICustomer customerService;
	private IMicroVideo microVideoService;
	private IReview reviewService;
	private com.shangde.edu.feed.service.ICourse feedCourseService;

	/** domain接口 */
	private SubscribeUser subscribeUser;

	/** 查询domain接口 */

	/** 常量 */
	private Integer ksnId;// 关联知识树
	private Integer resStatus;// 状态[1=成功，0=失败，2=无数据]
	private Integer resType;// 返回类型【1=列表、2=单项】
	private Integer qsLevel;// 试题难度值,【存列表中第一个试题的值】

	private String gotoURL;// 跳转地址
	private String jsonResult;// json返回值

	private Integer subjectId;
	private String email;

	private Integer type;// 微学习，type属性
	
	private Integer subjectIdForWxx;//专业id
	private Integer courseIdForWxx;//课程id
	private int totalReviewNum;//微学习留言总记录数---前台微学习页面使用
	
	/** 集合对象 */
	//public List<QstRelDTO> qstRelDTOList;// 试题列表
	
	private List<List<MicroVideoDTO>> microList;//微学习视频列表
	private MicroVideoDTO microVideoDTODefault;//默认播放视频对象
	
	
	
	
	
	/**
	 * 获取试题列表,根据ksnId
	 */
	/*
	public String getQstListByKsnId() {

		int limitNumber = 100;// 最多5条记录

		try {
			qstRelDTOList = qstRelService.getQstListByKsnIdAndLimNum(ksnId,
					limitNumber);

			if (qstRelDTOList == null || qstRelDTOList.size() == 0) {
				resStatus = 2;
			} else {
				resStatus = 1;
				qsLevel = qstRelDTOList.get(0).getLevel();
			}
			resType = 1;
		} catch (Exception e) {
			resStatus = 0;
		}
		*/
		/**
		 * for (QstRelDTO qr : qstRelDTOList) { System.out.print("id->" +
		 * qr.getQstId()); System.out.print("\t试题难度->" + qr.getLevel());
		 * System.out.print("\t正确选项->" + qr.getIsAsr());
		 * System.out.print("\t试题类型->" + qr.getQstType());
		 * System.out.print("\t解析->" + qr.getWrongJude());
		 * System.out.print("\t试题内容->" + qr.getQstContent());
		 * 
		 * System.out.println();
		 * 
		 * List<Options> optionsList = qr.getOptions(); for (Options o :
		 * optionsList) { System.out.print("答案序号->" + o.getOptOrder());
		 * System.out.println("\t选项内容->" + o.getOptContent()); }
		 * 
		 * System.out.println("==========================================="); }
		 */
/*
		return "questions_item";
	}
	*/

	/**
	 * 注册订阅用户,前台用户调用
	 * 
	 * 需要提供SubscribeUser.email属性
	 * 
	 * @return
	 */
	public String register() {
		try{
			Date now = new Date();
	
			if (subscribeUser == null) {
				subscribeUser = new SubscribeUser();
			}
	
			// 默认值
			subscribeUser.setSubjectId(subjectId);
			subscribeUser.setEmail(email);
			subscribeUser.setStatus(0);
			subscribeUser.setPubdate(now);
			subscribeUser.setModified(now);
	
			try {
				if (subscribeUserService.addSubscribeUser(subscribeUser) > 0) {
					jsonResult = "1";
				} else {
					jsonResult = "0";
				}
			} catch (Exception e) {
				jsonResult = "0";
			}
		}catch(Exception e){
			logger.error("FeedWebAction.register", e);
		}

		return "jsonResult";
	}

	/**
	 * 去微学习页面
	 * 
	 * @author chensong
	 * @author Libg
	 * 
	 * @return
	 */
	public String goWeiXX(){
		
		String cId = null;//课程id
		String subjectId = null;
		Customer cus = null;
		try{
			cus = customerService.getCustomerById(getLoginUserId());
			
			subjectId = CookieHandler.getCookieValueByName(servletRequest,"subjectId");//从cookie中取得subjectId值
			if(subjectId != null){
				subjectIdForWxx = Integer.parseInt(subjectId);
				//根据专业id取得一个课程id
				try{
					//查无数据会报错,表示没有该专业对应的课程
					cId = String.valueOf(feedCourseService.getCourseIdBySubjectId(subjectIdForWxx));
					if(cId != null){
						courseIdForWxx = Integer.parseInt(cId);	
					}else{
						//专业没有关联课程，则直接返回
						return "fromEmailToUserCenter";
					}
				}catch (Exception e) {
					
					courseIdForWxx = -1;
				}
			}
		}catch (Exception e) {
			logger.error("根据cookieUserId-获取Customer对象失败！",e);
		}
		//根据email,subjectId查询微学习视频数据列表
		Map<String, String> map = new HashMap<String, String>();
		try{
			map.put("email", cus.getEmail());
			map.put("subjectId", subjectId);
			List<MicroVideoDTO> list = microVideoService.getVideoAddressBySubjectId(map);
	        
	        if(list != null && list.size() > 0){
	        	microList = new ArrayList<List<MicroVideoDTO>>();
	        	
	        	//第一查询出，最大的阶段数,需要排序来计算
	        	String []properties = {"stageNum"};
	        	CompareByProperty.sortDesc(list, properties);
	        	int stageNum = list.get(0).getStageNum();
	        	//第二,阶段是从1开始的
	        	List<MicroVideoDTO> stateNumList = null;
	        	for(int i = 1;i <= stageNum;i++){
	        		
	        		stateNumList = new ArrayList<MicroVideoDTO>();
	        		
	        		for(MicroVideoDTO mv : list){
	        			if(mv.getStageNum() != null && mv.getStageNum().intValue() == i){
	        				stateNumList.add(mv);
	        			}
	        		}
	        		microList.add(stateNumList);
	        	}
	        	if(microList != null && microList.size() > 0){
	        		//从列表中取出第一个视频对象，设置为默认播放对象
		        	for(List<MicroVideoDTO> l : microList){
		        		if(l != null && l.size() > 0){
		        			microVideoDTODefault = l.get(0);		
		        		}
		        	}
	        	}
	        }
		}catch (Exception e) {
			logger.error("查询微学习视频列表失败!", e);
		}
		
		try{
			totalReviewNum = reviewService.getTotalReviewNum();//留言总记录
		}catch (Exception e) {
			logger.error("获取总留言列数失败",e);
		}
		
		
		/**这里从cookie中取出从邮件统计请求设置的视频id,cookie值，如果存在则将该设置为默认视频*/
		try{
			
			String sedu_feed_task_videoId_cookie = CookieHandler.getCookieValueByName(getServletRequest(), "sedu_feed_task_videoId_cookie");
			if(sedu_feed_task_videoId_cookie != null && !sedu_feed_task_videoId_cookie.equals("")){
				//空间换效率,将值放入Map对象中,方便取值
				Map<Integer,MicroVideoDTO> m = new HashMap<Integer, MicroVideoDTO>();
				for(List<MicroVideoDTO> l : microList){
					for(MicroVideoDTO mvDTO : l){
						m.put(mvDTO.getId(), mvDTO);
					}
				}
				microVideoDTODefault = m.get(Integer.parseInt(sedu_feed_task_videoId_cookie));	
			}
		}catch (Exception e) {
			logger.error("封装数据,出错", e);
		}
		
		/*
		System.out.println("--------------------------------------------------------------start----------------");
		//迭代格式
		for(List<MicroVideo> l : microList){
			System.out.println("=======================");
			for(MicroVideo mv : l){
				System.out.println(mv.getId() + "\t" + mv.getStageNum());
			}
		}
		System.out.println("--------------------------------------------------------------end----------------");
		*/
		
		//跳转页面
        return "toWeixuexi";
	}

	/**
	 * 测试使用
	 * 
	 * @return
	 */
	public String toUserRegPage() {

		return "toUserRegPage";
	}

	/*
	public IQstRel getQstRelService() {
		return qstRelService;
	}

	public void setQstRelService(IQstRel qstRelService) {
		this.qstRelService = qstRelService;
	}*/

	public Integer getKsnId() {
		return ksnId;
	}

	public void setKsnId(Integer ksnId) {
		this.ksnId = ksnId;
	}

	/*
	public List<QstRelDTO> getQstRelDTOList() {
		return qstRelDTOList;
	}

	public void setQstRelDTOList(List<QstRelDTO> qstRelDTOList) {
		this.qstRelDTOList = qstRelDTOList;
	}
	*/

	public Integer getResStatus() {
		return resStatus;
	}

	public void setResStatus(Integer resStatus) {
		this.resStatus = resStatus;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public Integer getQsLevel() {
		return qsLevel;
	}

	public void setQsLevel(Integer qsLevel) {
		this.qsLevel = qsLevel;
	}

	/**
	 * @return the subscribeUserService
	 */
	public ISubscribeUser getSubscribeUserService() {
		return subscribeUserService;
	}

	/**
	 * @param subscribeUserService
	 *            the subscribeUserService to set
	 */
	public void setSubscribeUserService(ISubscribeUser subscribeUserService) {
		this.subscribeUserService = subscribeUserService;
	}

	/**
	 * @return the subscribeUser
	 */
	public SubscribeUser getSubscribeUser() {
		return subscribeUser;
	}

	/**
	 * @param subscribeUser
	 *            the subscribeUser to set
	 */
	public void setSubscribeUser(SubscribeUser subscribeUser) {
		this.subscribeUser = subscribeUser;
	}

	/**
	 * @return the jsonResult
	 */
	public String getJsonResult() {
		return jsonResult;
	}

	/**
	 * @param jsonResult
	 *            the jsonResult to set
	 */
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId
	 *            the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the browseLogService
	 */
	public IBrowseLog getBrowseLogService() {
		return browseLogService;
	}

	/**
	 * @param browseLogService
	 *            the browseLogService to set
	 */
	public void setBrowseLogService(IBrowseLog browseLogService) {
		this.browseLogService = browseLogService;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the customerService
	 */
	public ICustomer getCustomerService() {
		return customerService;
	}

	/**
	 * @param customerService the customerService to set
	 */
	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	/**
	 * @return the courseIdForWxx
	 */
	public Integer getCourseIdForWxx() {
		return courseIdForWxx;
	}

	/**
	 * @param courseIdForWxx the courseIdForWxx to set
	 */
	public void setCourseIdForWxx(Integer courseIdForWxx) {
		this.courseIdForWxx = courseIdForWxx;
	}

	/**
	 * @return the microList
	 */
	public List<List<MicroVideoDTO>> getMicroList() {
		return microList;
	}

	/**
	 * @param microList the microList to set
	 */
	public void setMicroList(List<List<MicroVideoDTO>> microList) {
		this.microList = microList;
	}

	/**
	 * @return the totalReviewNum
	 */
	public int getTotalReviewNum() {
		return totalReviewNum;
	}

	/**
	 * @param totalReviewNum the totalReviewNum to set
	 */
	public void setTotalReviewNum(int totalReviewNum) {
		this.totalReviewNum = totalReviewNum;
	}

	/**
	 * @return the microVideoDTODefault
	 */
	public MicroVideoDTO getMicroVideoDTODefault() {
		return microVideoDTODefault;
	}

	/**
	 * @param microVideoDTODefault the microVideoDTODefault to set
	 */
	public void setMicroVideoDTODefault(MicroVideoDTO microVideoDTODefault) {
		this.microVideoDTODefault = microVideoDTODefault;
	}

	/**
	 * @return the microVideoService
	 */
	public IMicroVideo getMicroVideoService() {
		return microVideoService;
	}

	/**
	 * @param microVideoService the microVideoService to set
	 */
	public void setMicroVideoService(IMicroVideo microVideoService) {
		this.microVideoService = microVideoService;
	}

	/**
	 * @return the feedCourseService
	 */
	public com.shangde.edu.feed.service.ICourse getFeedCourseService() {
		return feedCourseService;
	}

	/**
	 * @param feedCourseService the feedCourseService to set
	 */
	public void setFeedCourseService(
			com.shangde.edu.feed.service.ICourse feedCourseService) {
		this.feedCourseService = feedCourseService;
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

}
