package com.shangde.edu.res.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.res.condition.QueryTryvedioCondition;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;
import com.shangde.edu.res.domain.Tryvedio;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.service.ITryvedio;
import com.shangde.edu.res.service.ITvdTvd;

public class TjVedioAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(TjVedioAction.class);
	/**推荐视频**/
	private TvdTvd tvdtvd;
	/**试听视频服务**/
	private ITryvedio tryvedioService;
	/**推荐视频服务**/
	private ITvdTvd tvdTvdService;
	/**查询对象**/
	private QueryTryvedioCondition queryTryvedioCondition;
	/**试听视频集合**/
	private List<Tryvedio> tryVedioList;
	
	/**
	 * 打开添加推荐视频页面
	 * @return
	 */
	public String toAddTjVedio()
	{
		try
		{		
			tryVedioList=tryvedioService.getTryvedioList(queryTryvedioCondition);		
			
			return "toAddTjVedio";
		}catch (Exception e) {
			logger.error("TjVedioAction.toAddTjVedio", e);
			return ERROR;
		}
	}
	/**
	 * 添加推荐关系
	 * @return
	 */
	public String addTvdTvd(){
		try{
			QueryTvdTvdCondition queryTvdTvdCondition=new QueryTvdTvdCondition();
			queryTvdTvdCondition.setTjvdid(tvdtvd.getTjvdid());
			queryTvdTvdCondition.setVdid(tvdtvd.getVdid());
			
			
			
			System.out.print("a");
			if(tvdtvd.getTjvdid()==tvdtvd.getVdid()){
				return ERROR;
			}
			if(tvdTvdService.getTvdTvdList(queryTvdTvdCondition).size()!=0){
				return ERROR;
			}
			
			tvdTvdService.addTvdTvd(tvdtvd);
			return "changeSuccess";
			
		}catch (Exception e){
			logger.error("TjVedioAction.addTvdTvd", e);
			return ERROR;
		}		
		
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

	public ITvdTvd getTvdTvdService() {
		return tvdTvdService;
	}
	public void setTvdTvdService(ITvdTvd tvdTvdService) {
		this.tvdTvdService = tvdTvdService;
	}
	public QueryTryvedioCondition getQueryTryvedioCondition() {
		if(queryTryvedioCondition==null){
			queryTryvedioCondition=new QueryTryvedioCondition();
		}
		return queryTryvedioCondition;
	}
	public void setQueryTryvedioCondition(
			QueryTryvedioCondition queryTryvedioCondition) {
		this.queryTryvedioCondition = queryTryvedioCondition;
	}
	public List<Tryvedio> getTryVedioList() {
		return tryVedioList;
	}
	public void setTryVedioList(List<Tryvedio> tryVedioList) {
		this.tryVedioList = tryVedioList;
	}
}
