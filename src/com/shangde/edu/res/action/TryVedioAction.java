package com.shangde.edu.res.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.res.condition.QueryTryvedioCondition;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.service.ITryvedio;


public class TryVedioAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(TryVedioAction.class);
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
	private List<Coursesort> courseSortList;
	private ICoursesort coursesortService;
	
	private List<File> tryPicList=new ArrayList<File>();

	private List<String> tryPicListFileName =new ArrayList<String>();
	
	
	
	

	
	/**
	 * 打开添加推荐视频页面
	 * @return
	 */
	public String toAddTryVedio()
	{
		try{
			try{
				QueryCoursesortCondition queryCoursesortCondition = new QueryCoursesortCondition();				
				courseSortList = coursesortService.getChildCoursesortList(queryCoursesortCondition);
				return "toAddTryVedio";
			}catch(Exception ex){
				logger.error("TryVedioAction.toAddTryVedio", ex);
				return ERROR;
			}
			
		}catch (Exception e) {
			logger.error("TryVedioAction.toAddTryVedio", e);
			return ERROR;
		}
	}
	/**
	 * 添加推荐视频
	 * @return
	 */
	public String addTjVedio()
	{		
		try
		{			
			tryPicList.add(super.getFiles()[0]);
			String name=System.currentTimeMillis()+ super.getFilesFileName()[0].substring(super.getFilesFileName()[0].lastIndexOf(".")) ;
			tryPicListFileName.add(name);
			this.setSavePath("/static/images/st");
			tryvedio.setAddTime(new Date());
			tryvedio.setPicPath(this.getSavePath()+"/"+name);
			this.uploadForWeb(tryPicListFileName, tryPicList);
			tryvedioService.addTryvedio(tryvedio);		
			
			return "changeSuccess";
		}catch(Exception ex)
		{
			logger.error("TryVedioAction.addTjVedio", ex);
			return ERROR;
		}
		
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
		if(queryTryvedioCondition==null)
		{
			queryTryvedioCondition=new QueryTryvedioCondition();
		}
		return queryTryvedioCondition;
	}
	public void setQueryTryvedioCondition(
			QueryTryvedioCondition queryTryvedioCondition) {
		this.queryTryvedioCondition = queryTryvedioCondition;
	}
	public QueryTvdTvdCondition getQueryTvdTvdCondition() {
		if(queryTvdTvdCondition==null)
		{
			queryTvdTvdCondition=new QueryTvdTvdCondition();
		}
		return queryTvdTvdCondition;
	}
	public void setQueryTvdTvdCondition(QueryTvdTvdCondition queryTvdTvdCondition) {
		this.queryTvdTvdCondition = queryTvdTvdCondition;
	}
	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}
	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}
	public ICoursesort getCoursesortService() {
		return coursesortService;
	}
	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}
	List<File> getTryPicList() {
		return tryPicList;
	}
	void setTryPicList(List<File> tryPicList) {
		this.tryPicList = tryPicList;
	}
	List<String> getTryPicListFileName() {
		return tryPicListFileName;
	}
	void setTryPicListFileName(List<String> tryPicListFileName) {
		this.tryPicListFileName = tryPicListFileName;
	}




	
}
