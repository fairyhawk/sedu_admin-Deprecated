package com.shangde.edu.feed.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import com.shangde.common.action.CommonAction;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.feed.condition.QueryReviewCondition;
import com.shangde.edu.feed.domain.Review;
import com.shangde.edu.feed.service.IReview;

/**
 * 留言模块action
 * 
 * @author Libg
 * 
 */
public class ReviewAction extends CommonAction {

	private static Logger logger = Logger.getLogger(ReviewAction.class);

	/** 服务接口 */
	private IReview reviewService;

	/** domain接口 */
	private Review review;

	/** 查询domain接口 */
	private QueryReviewCondition queryReviewCondition;

	/** 常量 */
	private Integer id;// 记录id
	private int resStatus;// 状态[1=成功，0=失败]
	private Integer resType;// 返回类型【1=列表、2=单项】
	private String jsonResult;// json内容[该字符串可以任意组装，每一个action组装的格式与要与前台模板页面对应上]
	private String content;// 内容
	private Integer subjectId;// 专业id
	private Integer courseId;// 微学习课程id
	private Integer targetCustomerId;// 回复目标人id
	private Integer targetReviewId;// 回复目标记录id
	private String replyCusName;// 回复目标人名称[name/email]

	/** 集合对象 */
	private List<Review> reviewList;

	
	public String toString(){
		
		StringBuffer s = new StringBuffer();
		
		s.append("subjectId->").append(getSubjectId()).append("\n");
		s.append("courseId->").append(getCourseId()).append("\n");
		s.append("targetCustomerId->").append(getTargetCustomerId()).append("\n");
		s.append("targetReviewId->").append(getTargetReviewId()).append("\n");
		s.append("replyCusName->").append(getReplyCusName()).append("\n");
		
		return s.toString();
	}
	
	/**
	 * 添加业务
	 * 
	 * 分为两个分支业务：1、自己回复，不针对任何人，2、针对某个人的回复进行回复
	 * 
	 * 当jsonResult值不等于 0时，表示为记录id
	 * 
	 * @return
	 */
	public String doAdd() {

		try {
			if (review == null) {
				review = new Review();
			}
			
			System.out.println("============================");
			System.out.println(toString());
			System.out.println("============================");

			try {

				/**
				 * 过滤回复内容,“回复@用户名”
				 * 
				 * 回复 @ replyCusName :
				 * 
				 */
				if (targetCustomerId != null
						&& targetCustomerId.intValue() != 0) {
					StringBuffer splitC = new StringBuffer();
					try {
						// 回复某人格式：= "回复@"+replyCusName+":";
						splitC.append("回复@").append(replyCusName).append(":");
						// 从内容中过滤掉“回复某人格式”
						if (content.indexOf(splitC.toString()) != -1) {
							content = content.replaceAll(splitC.toString(), "");
							setContent(content);
						} else {
							// 如果格式配破坏了，则当做直接针对主帖回复
							setTargetCustomerId(0);
						}
					} catch (Exception e) {
						logger.error("过滤回复内容时出错", e);
					}
				}
				/**
				 * 关键词过滤
				 */

				/*
				 * if(getContent() != null && getContent().length() > 0){
				 * //关键词过滤 setContent(keyWordFilter.doFilter(getContent()));
				 * topicReply.setContent(getContent()); }
				 */
			} catch (Exception e) {
				logger.error("回复时，关键字过滤try-catch", e);
			}

			Customer targetCustomer = new Customer();
			if (targetCustomerId != null && targetCustomerId.intValue() != 0) {
				targetCustomer.setCusId(targetCustomerId);
				review.setTargetReviewId(targetReviewId);
			} else {
				targetCustomer.setCusId(0);
				review.setTargetReviewId(0);
			}

			review.setSubjectId(subjectId);
			review.setCourseId(courseId);
			review.setContent(content);
			review.setCounts(0);
			review.setStatus(1);
			Customer customer = new Customer();
			customer.setCusId(getLoginUserId());

			review.setCustomer(customer);
			review.setTargetCustomer(targetCustomer);

			Date now = new Date();
			review.setPubDate(now);
			review.setModified(now);

			id = reviewService.addReview(review);
			if (id > 0) {
				jsonResult = String.valueOf(id);
				try {
					if(getTargetReviewId() != null && getTargetReviewId().intValue() != 0){
						//修改目标帖counts递增
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("id", getTargetReviewId());
						map.put("incremental", 1);
						reviewService.updateReviewCounts(map);
					}
				}catch (Exception e) {
					logger.error("修改记录counts递增失败,异常", e);
				}
			} else {
				jsonResult = "0";
			}
		} catch (Exception e) {
			jsonResult = "0";
			logger.error("留言回复添加-业务失败", e);
		}
		return "jsonResult";
	}

	/**
	 * 根据id查询数据
	 * 
	 * @return
	 */
	public String getReviewById() {
		try{
			// 根据id查询数据
			review = reviewService.getReviewById(id);
	
			if (review != null) {
				resStatus = 1;
			} else {
				resStatus = 0;
			}
			resType = 2;
		}catch(Exception e){
			logger.error("ReviewAction.getReviewById", e);
			return ERROR;
		}
		return "review_item";
	}

	/**
	 * 查询所有留言数据,微学习页面调用
	 * 
	 * @return
	 */
	public String getAll() {

		int pageSize = 10;
		try {
			this.getQueryReviewCondition().setPageSize(pageSize);
			setPage(reviewService.getReviewList(queryReviewCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
			resStatus = 1;
			
		} catch (Exception e) {
			resStatus = 0;
			logger.error("微学习-留言列表查询业务失败", e);
		}

		resType = 1;

		return "review_item_list";
	}

	/**
	 * 删除记录
	 * 
	 * by id,targetReviewId
	 * 
	 * id				要删除的记录id
	 * targetReviewId	删除记录id对应的目标id(根据该id修改counts字段递减)
	 * 
	 * @return
	 */
	public String doDel() {
		
		try {
			//删除留言byID
			if(reviewService.delReviewById(id) > 0){
				
				//修改该贴针对某回复内容进行回复的id记录
				if(getTargetReviewId() != null && getTargetReviewId().intValue() != 0){
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("id", getTargetReviewId());
					map.put("incremental", -1);
					if(reviewService.updateReviewCounts(map) > 0){
						jsonResult = "2";//修改目标counts字段递减失败
					}
				}
				jsonResult = "1";//执行成功
			}
		} catch (Exception e) {
			jsonResult = "0";//系统异常
			logger.error("删除留言异常-->" + getId(), e);
		}
		
		return "jsonResult";
	}
	
	public String test() {
		return "test";
	}

	public IReview getReviewService() {
		return reviewService;
	}

	public void setReviewService(IReview reviewService) {
		this.reviewService = reviewService;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public QueryReviewCondition getQueryReviewCondition() {
		if (queryReviewCondition == null) {
			queryReviewCondition = new QueryReviewCondition();
		}
		return queryReviewCondition;
	}

	public void setQueryReviewCondition(
			QueryReviewCondition queryReviewCondition) {
		this.queryReviewCondition = queryReviewCondition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public int getResStatus() {
		return resStatus;
	}

	public void setResStatus(int resStatus) {
		this.resStatus = resStatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * @return the targetCustomerId
	 */
	public Integer getTargetCustomerId() {
		return targetCustomerId;
	}

	/**
	 * @param targetCustomerId
	 *            the targetCustomerId to set
	 */
	public void setTargetCustomerId(Integer targetCustomerId) {
		this.targetCustomerId = targetCustomerId;
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
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the targetReviewId
	 */
	public Integer getTargetReviewId() {
		return targetReviewId;
	}

	/**
	 * @param targetReviewId
	 *            the targetReviewId to set
	 */
	public void setTargetReviewId(Integer targetReviewId) {
		this.targetReviewId = targetReviewId;
	}

	/**
	 * @return the replyCusName
	 */
	public String getReplyCusName() {
		return replyCusName;
	}

	/**
	 * @param replyCusName
	 *            the replyCusName to set
	 */
	public void setReplyCusName(String replyCusName) {
		this.replyCusName = replyCusName;
	}

}
