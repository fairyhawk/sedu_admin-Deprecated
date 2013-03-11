package com.shangde.edu.iphone.web;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.iphone.condition.QueryCourseIPhoneCondition;
import com.shangde.edu.iphone.condition.QueryKpointIPhoneCondition;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;
import com.shangde.edu.iphone.service.ICourseIPhone;
import com.shangde.edu.iphone.service.IKpointIPhone;
import com.shangde.edu.iphone.service.ISellWayIPhone;
import com.shangde.edu.iphone.domain.*;
import com.shangde.edu.iphone.dto.IphoneModel;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVedio;

/**
 * 当前类 为张栋iphone端提供课程节点类
 * 
 * @author wangzheng
 * 
 */
public class IPhoneKpointWebAction extends CommonAction {

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

	/**
	 * 课程节点集合
	 */
	private List<IphoneModel> KpointIPhoneList;

	private int pageId;

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	/**
	 * 
	 * 为张栋提供iphone端数据，得到当前课程根节点 也就是得到当前课程的 章节点
	 * 
	 * @return 得到课程下 根节点集合
	 * 
	 */
	public String getKpointByPid() {
		PageResult pageResult = null;
		List<IphoneModel> kList = null;
		Object obj = null;
		String returnMessage = "";

		if (pageId != 0) {
			this.getQueryKpointIPhoneCondition().setCourseId(pageId);
			this.getQueryKpointIPhoneCondition().setPid(-2); // 如果pId为0 获取当前课程
																// 根节点 -2为跟节点
			// this.getQueryKpointCondition().setPageSize(10);
			pageResult = this.kpointIPhoneService.getIphoneKpointListByPid(this
					.getQueryKpointIPhoneCondition()); // 得到根节点章节信息
			kList = pageResult.getPageResult();
			if (kList != null && !kList.isEmpty()) {
				for (IphoneModel iphoneModel : kList) {
					Vedio vo = this.vedioService.getVedioByPointid(iphoneModel
							.getVideoId());
					if (vo != null && vo.getVoUrl() != null && !"".equals(vo.getVoUrl())) {
						iphoneModel.setVideoURL(vo.getVoUrl());// 如果当前根节点章节
																// 有视频路径 则添加路径
					}
				}
			}
			pageResult.setPageResult(kList);
			setPage(pageResult);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(10);
			}
			JSONArray jsonArray = JSONArray.fromObject(pageResult);
			if (jsonArray.size() > 0) {
				returnMessage = "success";
				obj = jsonArray.get(0);
			} else {
				returnMessage = "error";
			}
		} else {
			returnMessage = "error";
		}
		this.setResult(new Result<Object>(false, returnMessage, null, obj));
		return "json";
	}

	/**
	 * 
	 * 为张栋提供iphone端数据， 如果课程有章/节一层次 节点的情况下 使用递归算法获取 当前章节点下 所有带视频路径的子节点
	 * 
	 * @param pId
	 *            父节点ID
	 * 
	 */
	public void getKpointListByPid(int pId) { // 查询根节点 章下有视频URL的课程
		if (pId != 0) { // 判断节点是否为0
			this.getQueryKpointIPhoneCondition().setPid(pId);
			List<IphoneModel> kpList = this.kpointIPhoneService
					.getKpointListByPid(this.getQueryKpointIPhoneCondition()); // 查询当前节点下所有子节点
			
			if(kpList != null && !kpList.isEmpty()){
				for (IphoneModel iphoneModel : kpList) {
					Vedio vo = this.vedioService.getVedioByPointid(iphoneModel.getVideoId());
					if (vo != null && vo.getVoUrl() != null && !"".equals(vo.getVoUrl())) {
						iphoneModel.setVideoURL(vo.getVoUrl());
						this.getKpointIPhoneList().add(iphoneModel); // 向最终结果集中添加数据
						getKpointListByPid(iphoneModel.getVideoId()); // 查询循环当前子节点
																		// 是否还有下级节点
					}
				}
			}
		}
	}

	/**
	 * 
	 * 为张栋提供iphone端数据 得到当前节点下的下一级 所有有视频URL的子节点
	 * 
	 */
	public String getKpointAndVoListByPid() {
		PageResult pageResult = null;
		String returnMessage = "";
		Object obj = null;
		if (this.pageId != 0) {
			pageResult = new PageResult();
			this.getQueryKpointIPhoneCondition().setPid(this.pageId); // 赋值当前
																		// 章节点
			List<IphoneModel> kListByPid = this.kpointIPhoneService
					.getKpointListByPid(this.getQueryKpointIPhoneCondition()); // 查询当前节点下所有子节点
			
			for (IphoneModel iphoneModel : kListByPid) {
				getKpointListByPid(iphoneModel.getVideoId()); // 掉用查询章 下
				// 所有带视频URL
				// 地址的子节点，数据添加到kpointList中
			}
			
			pageResult.setPageResult(this.getKpointIPhoneList());
			JSONArray jsonArray = JSONArray.fromObject(pageResult);
			if (jsonArray.size() > 0) {
				obj = jsonArray.get(0);
				returnMessage = "success";
			} else {
				returnMessage = "faile";
			}
		} else {
			returnMessage = "faile";
		}
		setResult(new Result(false, returnMessage, null, obj));
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

	public QueryCourseIPhoneCondition getQueryCourseIPhoneCondition() {
		if (this.queryCourseIPhoneCondition == null) {
			this.queryCourseIPhoneCondition = new QueryCourseIPhoneCondition();
		}
		return queryCourseIPhoneCondition;
	}

	public void setQueryCourseIPhoneCondition(
			QueryCourseIPhoneCondition queryCourseIPhoneCondition) {
		this.queryCourseIPhoneCondition = queryCourseIPhoneCondition;
	}

	public QueryKpointIPhoneCondition getQueryKpointIPhoneCondition() {
		if (this.queryKpointIPhoneCondition == null) {
			this.queryKpointIPhoneCondition = new QueryKpointIPhoneCondition();
		}
		return queryKpointIPhoneCondition;
	}

	public void setQueryKpointIPhoneCondition(
			QueryKpointIPhoneCondition queryKpointIPhoneCondition) {
		this.queryKpointIPhoneCondition = queryKpointIPhoneCondition;
	}

	public QuerySellWayIPhoneCondition getQuerySellWayIPhoneCondition() {
		if (this.querySellWayIPhoneCondition == null) {
			this.querySellWayIPhoneCondition = new QuerySellWayIPhoneCondition();
		}
		return querySellWayIPhoneCondition;
	}

	public void setQuerySellWayIPhoneCondition(
			QuerySellWayIPhoneCondition querySellWayIPhoneCondition) {
		this.querySellWayIPhoneCondition = querySellWayIPhoneCondition;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public List<IphoneModel> getKpointIPhoneList() {
		if (this.KpointIPhoneList == null) {
			this.KpointIPhoneList = new ArrayList<IphoneModel>();
		}
		return KpointIPhoneList;
	}

	public void setKpointIPhoneList(List<IphoneModel> kpointIPhoneList) {
		KpointIPhoneList = kpointIPhoneList;
	}

}
