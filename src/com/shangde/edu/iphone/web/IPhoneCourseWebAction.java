package com.shangde.edu.iphone.web;

import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;
import com.shangde.edu.iphone.condition.QueryKpointIPhoneCondition;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;
import com.shangde.edu.iphone.service.ICourseIPhone;
import com.shangde.edu.iphone.service.IKpointIPhone;
import com.shangde.edu.iphone.service.ISellWayIPhone;
import com.shangde.edu.res.service.IVedio;


/**
 * 当前类  为张栋iphone端提供课程
 * @author wangzheng
 *
 */

public class IPhoneCourseWebAction extends CommonAction {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1882320843971351964L;

	/**
	 * 课程节点服务接口
	 */
	private IKpointIPhone kpointIPhoneService;
	
	/**
	 * 课程服务接口
	 */
	private ICourseIPhone courseIPhoneService;
	
	/**
	 * 售卖方式服务接口
	 */
	private ISellWayIPhone sellWayIPhoneService;
	
	/**
	 * 视频服务接口
	 */
	private IVedio vedioService;
	
	/**
	 * iphone端 课程查询附加条件
	 */
	private QueryCourseIPhoneCondition queryCourseIPhoneCondition;
	
	/**
	 * iphone端 课程节点查询附加条件
	 */
	private QueryKpointIPhoneCondition queryKpointIPhoneCondition;
	
	/**
	 * iphone端 售卖方式查询附加条件
	 */
	private QuerySellWayIPhoneCondition querySellWayIPhoneCondition;
	

	private int pageId;
	
	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	/**
	 * 售卖方式ID
	 */	
	public String getCourseBySellWayId(){
		String returnMessage = "";
		PageResult pageResult = null;
		if(pageId != 0){
			this.getQueryCourseIPhoneCondition().setSellWayId(pageId);  //添加查询条件  当前售卖方式ID
//			this.getQueryCourseCondition().setPageSize(10);
			pageResult = this.courseIPhoneService.getCourseBySellWayId(this.getQueryCourseIPhoneCondition());
			List<IphoneModel> couList = pageResult.getPageResult();  //遍历课程  添加当前课程下  课程节点数量
			if (couList != null && !couList.isEmpty()) {
				for (IphoneModel iphoneModel : couList) {
					iphoneModel.setVideoURL(this.kpointIPhoneService.getKpointVoUrlById(iphoneModel.getVideoId()));
					iphoneModel.setCourseNum(getKpointSumByCourseId(iphoneModel.getVideoId()));
				}
			}
			pageResult.setPageResult(couList);
			setPage(pageResult);
			setPageUrlParms();
			if(getPage()!=null){
				getPage().setPageSize(10);
			}
			returnMessage = "success";
			
		}
		else{
			returnMessage = "error";
		}
		setResult(new Result<PageResult>(false, returnMessage, null, pageResult));
		return "json";
	}

	/**
	 * 得到当前课程  包含讲数 为张栋提供iphone端数据
	 * @param courseId
	 * @return
	 */
	private int getKpointSumByCourseId(int courseId){
		return this.kpointIPhoneService.getKpointSumByCourseId(courseId); //得到当前课程课程节点数
	}
	
	
	/**
	 * 得到最新推荐课程
	 * @return
	 */
	
	public String newCourse(){
		String returnMessage = "";
		PageResult newCourse = null;
		this.getQueryCourseIPhoneCondition().setNewCourse(1);
		if(this.getQueryCourseIPhoneCondition()!=null){
			
			newCourse = this.courseIPhoneService.getNewCourse(queryCourseIPhoneCondition);
			returnMessage = "success";
			
		}else {
			returnMessage ="error";
		}
		
		setResult(new Result<PageResult>(false, returnMessage, null, newCourse));
		return "json";
	}


	public IKpointIPhone getKpointIPhoneService() {
		return kpointIPhoneService;
	}
 
	public void setKpointIPhoneService(IKpointIPhone kpointIPhoneService) {
		this.kpointIPhoneService = kpointIPhoneService;
	}

	public ICourseIPhone getCourseIPhoneService() {
		return courseIPhoneService;
	}

	public void setCourseIPhoneService(ICourseIPhone courseIPhoneService) {
		this.courseIPhoneService = courseIPhoneService;
	}

	public ISellWayIPhone getSellWayIPhoneService() {
		return sellWayIPhoneService;
	}

	public void setSellWayIPhoneService(ISellWayIPhone sellWayIPhoneService) {
		this.sellWayIPhoneService = sellWayIPhoneService;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public QueryCourseIPhoneCondition getQueryCourseIPhoneCondition() {
		if(this.queryCourseIPhoneCondition==null){
			this.queryCourseIPhoneCondition = new QueryCourseIPhoneCondition();
		}
		return queryCourseIPhoneCondition;
	}

	public void setQueryCourseIPhoneCondition(
			QueryCourseIPhoneCondition queryCourseIPhoneCondition) {
		this.queryCourseIPhoneCondition = queryCourseIPhoneCondition;
	}

	public QueryKpointIPhoneCondition getQueryKpointIPhoneCondition() {
		return queryKpointIPhoneCondition;
	}

	public void setQueryKpointIPhoneCondition(
			QueryKpointIPhoneCondition queryKpointIPhoneCondition) {
		this.queryKpointIPhoneCondition = queryKpointIPhoneCondition;
	}

	public QuerySellWayIPhoneCondition getQuerySellWayIPhoneCondition() {
		return querySellWayIPhoneCondition;
	}

	public void setQuerySellWayIPhoneCondition(
			QuerySellWayIPhoneCondition querySellWayIPhoneCondition) {
		this.querySellWayIPhoneCondition = querySellWayIPhoneCondition;
	}

}
