package com.shangde.edu.feed.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.condition.QueryAppStatCondition;
import com.shangde.edu.feed.dto.AppStatDTO;
import com.shangde.edu.feed.service.IAppStat;
import com.shangde.edu.feed.service.IDownload;
import com.shangde.edu.feed.service.IShare;
import com.shangde.edu.feed.utils.Utils;

public class AppStatAction extends CommonAction {

	private static Logger logger = Logger.getLogger(AppStatAction.class);
	
	/** 服务接口 */
	private IDownload downloadService;
	private IShare shareService;
	private IAppStat appStatService;

	/** domain接口 */

	/** 查询domain接口 */
	private QueryAppStatCondition queryAppStatCondition;

	/** 常量 */
	private String downFileURL = "/back/jsp/feed/export/appStatStat.csv";//下载文件地址
	private String msg;

	/** 集合对象 */
	private List<AppStatDTO> appStatList = new ArrayList<AppStatDTO>();

	/**
	 * 应用统计列表查询
	 * 
	 * 不会涉及到分页,统计微学习中各个小应用的统计
	 * 
	 * 1、下载 点击数、用户数 2、提问 点击数、用户数 3、分享 点击数、用户数
	 * 
	 * @return
	 */
	public String appStatList() {

		// 小应用统计开始

		getQueryAppStatCondition();
		if(queryAppStatCondition.getStartTime() == null || queryAppStatCondition.getStartTime().equals("")){
			queryAppStatCondition.setStartTime(null);
		}else{
			setSession("startTimeExport", queryAppStatCondition.getStartTime());
		}
		if(queryAppStatCondition.getEndTime() == null || queryAppStatCondition.getEndTime().equals("")){
			queryAppStatCondition.setEndTime(null);
		}else{
			setSession("endTimeExport", queryAppStatCondition.getEndTime());
		}
		
		// 下载统计
		AppStatDTO appStat_down = new AppStatDTO();
		appStat_down.setName("下载");
		appStat_down.setClickNum(downloadService.getDownloadCount(queryAppStatCondition));
		appStat_down.setUseUserNum(downloadService.getDownloadUserGroupCount(queryAppStatCondition));
		appStatList.add(appStat_down);
		
		// 分享
		AppStatDTO appStat_share = new AppStatDTO();
		appStat_share.setName("分享");
		appStat_share.setClickNum(shareService.getShareCount(queryAppStatCondition));
		appStat_share.setUseUserNum(shareService.getShareUserGroupCount(queryAppStatCondition));
		appStatList.add(appStat_share);
		
		// 提问
		AppStatDTO appStat_reply = new AppStatDTO();
		appStat_reply.setName("提问");
		queryAppStatCondition.setType(1);//表示提问
		appStat_reply.setClickNum(appStatService.getAppStatCount(queryAppStatCondition));
		appStat_reply.setUseUserNum(appStatService.getAppStatUserCount(queryAppStatCondition));
		appStatList.add(appStat_reply);

		setSession("appStatList", appStatList);
		
		return "app_list";
	}
	
	
	/**
	 * 导出数据
	 * 下载导出的数据(任务详情统计数据)
	 * 
	 * @author Libg
	 * @return
	 */
	public String downStatisticsData(){
		File file = null;
		String fileURL = null;
		try{
			boolean bool = exportAppStatPageData();
			if(bool){
				fileURL = getRealPath(downFileURL);
				file = new File(fileURL);
				//校验是否存在
				if(file != null && !file.exists()){
					msg = "下载文件不存在,还未导出!";
					return "msg";
				}
			}else{
					msg = "数据导出异常!";
					return "msg";
			}
		}catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "downFile";
	}
	
	/**
	 * 导出当前页统计数据
	 * 
	 * @return
	 */
	public boolean exportAppStatPageData(){
		boolean bool = false;
		int count = 0;
		int size = 0;
		String fileURL = null;//文件路径
		String[] title = null;
		String[][] body = null;//
		
		String startTimeExport = null;
		String endTimeExport = null;
		
		List<AppStatDTO> appStatDTOList = null;
		try{
			appStatDTOList = getSession("appStatList");
			size = appStatDTOList.size();
			startTimeExport = getSession("startTimeExport");
			endTimeExport = getSession("endTimeExport");
		}catch (Exception e) {
			logger.error("获取导出数据异常",e);
		}
		
		//没有数据，返回提示信息
		if(size == 0){
			msg = "没有数据，无法导出!";
			logger.error(msg);
		}
		
		fileURL = getRealPath(downFileURL);//设置保存路径
		//14个大小
		title = new String[] { "应用名称","点击次数","使用人数","开始时间","结束时间"};
		body = new String[size][title.length];
		for(AppStatDTO as : appStatDTOList){
			
			body[count][0] = as.getName();
			body[count][1] = Utils.stringValueOf(as.getClickNum());
			body[count][2] = Utils.stringValueOf(as.getUseUserNum());
			body[count][3] = startTimeExport;
			body[count][4] = endTimeExport;
			count++;
		}
		try {
			//创建文件
			Utils.writeCSV(title, body, fileURL);
			bool = true;
		} catch (Exception e) {
			logger.error("微学习--应用统计--导出数据-写文件错误->" ,e);
		}
		return bool;
	}
	

	/**
	 * @return the downloadService
	 */
	public IDownload getDownloadService() {
		return downloadService;
	}

	/**
	 * @param downloadService
	 *            the downloadService to set
	 */
	public void setDownloadService(IDownload downloadService) {
		this.downloadService = downloadService;
	}

	/**
	 * @return the shareService
	 */
	public IShare getShareService() {
		return shareService;
	}

	/**
	 * @param shareService
	 *            the shareService to set
	 */
	public void setShareService(IShare shareService) {
		this.shareService = shareService;
	}

	/**
	 * @return the queryAppStatCondition
	 */
	public QueryAppStatCondition getQueryAppStatCondition() {
		if(queryAppStatCondition == null){
			queryAppStatCondition = new QueryAppStatCondition();
		}
		return queryAppStatCondition;
	}

	/**
	 * @param queryAppStatCondition
	 *            the queryAppStatCondition to set
	 */
	public void setQueryAppStatCondition(
			QueryAppStatCondition queryAppStatCondition) {
		this.queryAppStatCondition = queryAppStatCondition;
	}

	/**
	 * @return the appStatList
	 */
	public List<AppStatDTO> getAppStatList() {
		return appStatList;
	}

	/**
	 * @param appStatList
	 *            the appStatList to set
	 */
	public void setAppStatList(List<AppStatDTO> appStatList) {
		this.appStatList = appStatList;
	}

	/**
	 * @return the appStatService
	 */
	public IAppStat getAppStatService() {
		return appStatService;
	}

	/**
	 * @param appStatService the appStatService to set
	 */
	public void setAppStatService(IAppStat appStatService) {
		this.appStatService = appStatService;
	}


	/**
	 * @return the downFileURL
	 */
	public String getDownFileURL() {
		return downFileURL;
	}


	/**
	 * @param downFileURL the downFileURL to set
	 */
	public void setDownFileURL(String downFileURL) {
		this.downFileURL = downFileURL;
	}


	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}


	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
