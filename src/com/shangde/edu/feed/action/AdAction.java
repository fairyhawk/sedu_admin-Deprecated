package com.shangde.edu.feed.action;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.condition.QueryAdCondition;
import com.shangde.edu.feed.domain.Ad;
import com.shangde.edu.feed.service.IAd;

public class AdAction extends CommonAction {

	private static Logger logger = LoggerFactory.getLogger(AdAction.class);

	/** 服务接口 */
	private IAd adService;

	/** domain接口 */
	private Ad ad;

	/** 查询domain接口 */
	private QueryAdCondition queryAdCondition;

	/** 常量 */
	private Integer id;
	private boolean flag = false;// 成功失败,true/false,默认false
	private String gotoURL;// 跳转地址
	private String msg;// 提示信息[成功/失败]等信息提示

	/** 集合对象 */
	private List<Ad> adList;

	public String toAddPage() {
		return "toAddPage";
	}

	/**
	 * 添加ad
	 * 
	 * @return
	 */
	public String doAdd() {

		try {
			Date now = new Date();
			ad.setModified(now);
			ad.setPubDate(now);
			ad.setStatus(1);
			if (adService.addAd(ad) > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = true;
			logger.error("添加ad错误-->", e);
		}

		/**
		 * 成功跳转
		 */
		if (flag) {
			gotoURL = "do_ad_list";
		} else {
			msg = "添加失败";
			gotoURL = "msg";
		}

		return gotoURL;
	}

	/**
	 * 根据id进入修改页面
	 * 
	 * @return
	 */
	public String toUpdatePage() {

		try {
			ad = adService.getAdById(id);
			if (ad != null) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			logger.error("根据id查询ad记录失败-->", e);
		}

		/**
		 * 成功跳转
		 */
		if (flag) {
			gotoURL = "to_ad_update";
		} else {
			msg = "查询失败";
			gotoURL = "msg";
		}

		return gotoURL;
	}

	/**
	 * 执行修改ad对象
	 * 
	 * @return
	 */
	public String doUpdate() {

		try {
			Date now = new Date();
			ad.setModified(now);
			if (adService.updateAd(ad) > 0) {
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			logger.error("修改ad错误-->", e);
		}

		/**
		 * 成功跳转
		 */
		if (flag) {
			gotoURL = "do_ad_list";
		} else {
			msg = "修改失败";
			gotoURL = "msg";
		}

		return gotoURL;
	}

	/**
	 * 查询ad列表
	 * 
	 * @return
	 */
	public String doAdList() {

		this.getQueryAdCondition().setPageSize(30);
		setPage(adService.getAdList(queryAdCondition));
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(30);
		}
		setPageUrlParms();
		
		return "ad_list";
	}

	/**
	 * @return the adService
	 */
	public IAd getAdService() {
		return adService;
	}

	/**
	 * @param adService
	 *            the adService to set
	 */
	public void setAdService(IAd adService) {
		this.adService = adService;
	}

	/**
	 * @return the ad
	 */
	public Ad getAd() {
		return ad;
	}

	/**
	 * @param ad
	 *            the ad to set
	 */
	public void setAd(Ad ad) {
		this.ad = ad;
	}

	/**
	 * @return the queryAdCondition
	 */
	public QueryAdCondition getQueryAdCondition() {
		if (queryAdCondition == null) {
			queryAdCondition = new QueryAdCondition();
		}
		return queryAdCondition;
	}

	/**
	 * @param queryAdCondition
	 *            the queryAdCondition to set
	 */
	public void setQueryAdCondition(QueryAdCondition queryAdCondition) {
		this.queryAdCondition = queryAdCondition;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the adList
	 */
	public List<Ad> getAdList() {
		return adList;
	}

	/**
	 * @param adList
	 *            the adList to set
	 */
	public void setAdList(List<Ad> adList) {
		this.adList = adList;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
