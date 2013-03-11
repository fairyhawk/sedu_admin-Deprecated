package com.shangde.edu.res.web;

import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.res.condition.QueryTryvedioCondition;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.service.ITryvedio;


public class TryVedioWebAction extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**试听视频*/
	private Tryvedio tryvedio;
	/**推荐视频关系*/
	private TvdTvd tvdtvd;
	/**试听视频服务*/
	private ITryvedio tryvedioService;
	/**试听视频列表*/
	private List<Tryvedio> tryvedioList;
	/**推荐视频关系列表*/
	private List<TvdTvd> tvdtvdList;
	/** 
	 *查询用到的condition*
	 */
	private QueryTryvedioCondition queryTryvedioCondition; 
	private QueryTvdTvdCondition queryTvdTvdCondition;
	
	public String addTryTimes() {
		tryvedio = tryvedioService.getTryvedioById(getQueryTryvedioCondition().getId());
		tryvedio.setTryTimes(tryvedio.getTryTimes() + 1);
		tryvedioService.updateTryvedio(tryvedio);
		this.setResult(new Result(false, "success", null, null));
		return "json";
	}
	
	public Tryvedio getTryvedio() {
		return tryvedio;
	}
	public void setTryvedio(Tryvedio tryvedio) {
		this.tryvedio = tryvedio;
	}
	public TvdTvd getTvdtvd() {
		return tvdtvd;
	}
	public void setTvdtvd(TvdTvd tvdtvd) {
		this.tvdtvd = tvdtvd;
	}
	public ITryvedio getTryvedioService() {
		return tryvedioService;
	}
	public void setTryvedioService(ITryvedio tryvedioService) {
		this.tryvedioService = tryvedioService;
	}
	
	public List<Tryvedio> getTryvedioList() {
		return tryvedioList;
	}
	public void setTryvedioList(List<Tryvedio> tryvedioList) {
		this.tryvedioList = tryvedioList;
	}
	public List<TvdTvd> getTvdtvdList() {
		return tvdtvdList;
	}
	public void setTvdtvdList(List<TvdTvd> tvdtvdList) {
		this.tvdtvdList = tvdtvdList;
	}
	public QueryTryvedioCondition getQueryTryvedioCondition() {
		if(queryTryvedioCondition==null) {
			queryTryvedioCondition=new QueryTryvedioCondition();
		}
		return queryTryvedioCondition;
	}
	public void setQueryTryvedioCondition(
			QueryTryvedioCondition queryTryvedioCondition) {
		this.queryTryvedioCondition = queryTryvedioCondition;
	}
	public QueryTvdTvdCondition getQueryTvdTvdCondition() {
		if(queryTvdTvdCondition==null) {
			queryTvdTvdCondition=new QueryTvdTvdCondition();
		}
		return queryTvdTvdCondition;
	}
	public void setQueryTvdTvdCondition(QueryTvdTvdCondition queryTvdTvdCondition) {
		this.queryTvdTvdCondition = queryTvdTvdCondition;
	}
}
