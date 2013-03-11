package com.shangde.edu.feed.web.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.GetRequsetResponseUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.feed.condition.QueryMicroVideoCondition;
import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.feed.domain.MicroVideo;
import com.shangde.edu.feed.dto.MicroVideoDTO;
import com.shangde.edu.feed.dto.VideoDetailsDTO;
import com.shangde.edu.feed.service.ICourse;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.utils.Utils;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class FeedAddVideoAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(FeedAddVideoAction.class);
	
	private static final long serialVersionUID = 1L;
	
	/**服务接口*/
	private IMicroVideo microVideoService;
	private ISubject subjectService;
	private ICourse feedCourseService;//课程服务
	
	
	/**集合接口*/
	private MicroVideo microVideo = new MicroVideo();
	private QueryMicroVideoCondition queryMicroVideoCondition = new QueryMicroVideoCondition();
	private List<Subject> subjectList;
	private List<MicroVideo> microVideoList;
	private List<Course> courseList;//课程列表
	List<VideoDetailsDTO> videoDetailsList = null;//反查用户列表
	
	
	
	/**常量区域*/
	
	private Integer stageNum;//阶段
	private Integer orderList;//排序码
	private String msg;//消息提示信息
	private Integer courseId;
	private String downFileURL = "/back/jsp/feed/export/videoStat.csv";//下载文件地址
	/*
	 * 视频状态，反查时使用[1=观看人反查、2=观看完人反查、3=下载人反查、4=收藏人反查]
	 */
	private Integer videoStatus;
	
	
	/**
	 * 去显示所有视频首页
	 * 
	 * @author ...
	 * @author Libg
	 * @return
	 */
	public String goAllVideo(){
		try{
			int pageSize = 30;
			this.getQueryMicroVideoCondition().setPageSize(pageSize);
			setPage(microVideoService.getAllVideoAddress(queryMicroVideoCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("FeedAddVideoAction.goAllVideo", e);
			return ERROR;
		}
		
		return "video_list";
	}
	
	/**
	 * 去添加视频页面
	 * @return
	 */
	public String goAddVideo(){
		try{
			subjectList = subjectService.getAllSubject();
		}catch(Exception e){
			logger.error("FeedAddVideoAction.goAddVideo", e);
			return ERROR;
		}
		return "video_add";
	}
	
	/**
	 * 添加视频方法
	 * @return
	 */
	public String createVideo(){
		try{
			Date now = new Date();
			microVideo.setModified(now);
			microVideo.setPubDate(now);
			microVideo.setStageNum(stageNum);
			microVideo.setOrderList(orderList);
			microVideo.setPoinId(0);
			microVideo.setVideoImgUrl(null);
			microVideo.setAntilogNumber(0);
			microVideo.setSupportNumber(0);
			microVideo.setTotalWatchTime(0);
			
			microVideoService.addVideoAddress(microVideo);
			
			msg = "视频添加完成";
		}catch(Exception e){
			logger.error("FeedAddVideoAction.createVideo", e);
		}
		return "msg";
	}
	
	/**
	 * 进入修改视频页面
	 * 首先查询出要修改视频对象
	 * 
	 * @return
	 */
	public String editorVideo(){
		try{
			//取得所有专业
			subjectList = subjectService.getAllSubject();
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			int id = Integer.parseInt(request.getParameter("id"));
			//根据视频id查询对象
			microVideo = microVideoService.getVideoAddressById(id);
			//int k1Time = microVideo.getKnowledge1Time();
			//int k2Time = microVideo.getKnowledge2Time();
			//int k3Time = microVideo.getKnowledge3Time();
			
			
		}catch(Exception e){
			logger.error("FeedAddVideoAction.editorVideo", e);
			return ERROR;
		}
		return "video_add";
	}
	/**
	 * 更新视频方法
	 * 
	 * @return
	 */
	public String updateVideo(){
		
		Date now = new Date();
		microVideo.setModified(now);
		microVideo.setStageNum(stageNum);
		microVideo.setOrderList(orderList);
		microVideo.setPoinId(0);
		microVideo.setVideoImgUrl(null);
		
		microVideoService.updateVideoAddress(microVideo);
		
		msg = "视频修改完成";
		
		return "msg";
	}
	
	/**
	 * 删除视频方法
	 * @return
	 */
	public String deleteVideo(){
		try{
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			int id = Integer.parseInt(request.getParameter("id"));
			microVideoService.deleteVideoAddress(id);
		}catch(Exception e){
			logger.error("FeedAddVideoAction.deleteVideo", e);
			return ERROR;
		}
		return "redirect_video";
	}
	
	/**
	 * 根据sujectId查找视频地址
	 * @return
	 */
	public String getVideoBySubjectId(){
		try{
			HttpServletRequest request = GetRequsetResponseUtil.getRequest();
			Integer subjectId = null;
			if(!"0".equals(request.getParameter("subjectId"))){
				subjectId = Integer.parseInt(request.getParameter("subjectId"));
				queryMicroVideoCondition.setSubjectId(subjectId);
			}else{
				subjectId = null ;
				queryMicroVideoCondition.setSubjectId(null);
			}
			
			int pageSize = 10;
			queryMicroVideoCondition.setPageSize(pageSize);
			setPage(microVideoService.getVideoAddressBySubjectIdBack(queryMicroVideoCondition));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
		}catch(Exception e){
			logger.error("FeedAddVideoAction.getVideoBySubjectId", e);
			return ERROR;
		}
		return "task_video_list";
	}
	
	public String toUploadPage(){
		
		return "uploadPage";
	}
	
	/**
	 * 视频统计查询
	 * 
	 * @return
	 */
	public String videoStatSearch(){
		
		courseList = feedCourseService.getAllCourse();
		
		int pageSize = 10;
		getQueryMicroVideoCondition().setPageSize(pageSize);
		
		
		if(queryMicroVideoCondition.getCourseId() == null || queryMicroVideoCondition.getCourseId().intValue() == -1){
			queryMicroVideoCondition.setCourseId(null);
		}else{
			try{
				//搜索结果导出时使用
				setSession("courseNameExport", feedCourseService.getCourseById(queryMicroVideoCondition.getCourseId()).getName());
			}catch (Exception e) {
				logger.error("查询课程失败",e);
			}
		}
		setSession("courseIdExport", queryMicroVideoCondition.getCourseId());
		if(queryMicroVideoCondition.getStartTime() == null || queryMicroVideoCondition.getStartTime().equals("")){
			queryMicroVideoCondition.setStartTime(null);
		}else{
			//搜索结果导出时使用
			//setSession("startTimeExport", queryMicroVideoCondition.getStartTime());
		}
		setSession("startTimeExport", queryMicroVideoCondition.getStartTime());
		if(queryMicroVideoCondition.getEndTime() == null || queryMicroVideoCondition.getEndTime().equals("")){
			queryMicroVideoCondition.setEndTime(null);
		}else{
			//搜索结果导出时使用
			//setSession("endTimeExport", queryMicroVideoCondition.getEndTime());
		}
		setSession("endTimeExport", queryMicroVideoCondition.getEndTime());
		
		setPage(microVideoService.videoStatSearch(queryMicroVideoCondition));
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(pageSize);
		}
		setPageUrlParms();
		
		//搜索结果导出时使用
		setSession("videoStatExportList", microVideoService.videoStatSearchExport(queryMicroVideoCondition));
		
		return "video_stat_list";
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
			boolean bool = exportVideoStatPageData();
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
	public boolean exportVideoStatPageData(){
		boolean bool = false;
		int count = 0;
		int size = 0;
		String fileURL = null;//文件路径
		String[] title = null;
		String[][] body = null;//
		
		String courseNameExport = null;
		String startTimeExport = null;
		String endTimeExport = null;
		
		List<MicroVideoDTO> videoStatExportList = null;
		try{
			videoStatExportList = getSession("videoStatExportList");
			size = videoStatExportList.size();
			courseNameExport = getSession("courseNameExport");
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
		title = new String[] { "视频ID","视频名称","点击数","观看人数","观看完人数","支持数","反对数","下载数","收藏数","本次课程","开始时间","结束时间"};
		body = new String[size][title.length];
		for(MicroVideoDTO mv : videoStatExportList){
			
			body[count][0] = Utils.stringValueOf(mv.getId());
			body[count][1] = Utils.stringValueOf(mv.getName());
			body[count][2] = Utils.stringValueOf(mv.getClickNum());
			body[count][3] = Utils.stringValueOf(mv.getClickUserNum());
			body[count][4] = Utils.stringValueOf(mv.getClickDoneUserNum());
			body[count][5] = Utils.stringValueOf(mv.getSupportNumber());
			body[count][6] = Utils.stringValueOf(mv.getAntilogNumber());
			body[count][7] = Utils.stringValueOf(mv.getDownNum());
			body[count][8] = Utils.stringValueOf(mv.getCollectNum());
			body[count][9] = courseNameExport;
			body[count][10] = startTimeExport;
			body[count][11] = endTimeExport;
			count++;
		}
		try {
			//创建文件
			Utils.writeCSV(title, body, fileURL);
			bool = true;
		} catch (Exception e) {
			logger.error("微学习--视频统计--导出数据-写文件错误->" ,e);
		}
		return bool;
	}
	
	
	public String videoWatchDownCollectDetailsSearch(){
		
		String courseIdExport = getSession("courseIdExport");
		String startTimeExport = getSession("startTimeExport");
		String endTimeExport = getSession("endTimeExport");
		
		if(courseIdExport == null || courseIdExport.equals("")){
			queryMicroVideoCondition.setCourseId(null);
		}else{
			queryMicroVideoCondition.setCourseId(new Integer(courseIdExport));
		}
		if(startTimeExport == null || startTimeExport.equals("")){
			queryMicroVideoCondition.setStartTime(null);
		}else{
			queryMicroVideoCondition.setStartTime(startTimeExport);
		}
		if(endTimeExport == null || endTimeExport.equals("")){
			queryMicroVideoCondition.setEndTime(null);
		}else{
			queryMicroVideoCondition.setEndTime(endTimeExport);
		}
		
		if(videoStatus == 1){
			videoDetailsList = microVideoService.videoReverseWatchSearch(queryMicroVideoCondition);
			
		}else if(videoStatus == 2){
			videoDetailsList = microVideoService.videoReverseWatchDoneSearch(queryMicroVideoCondition);
			
		}else if(videoStatus == 3){
			videoDetailsList = microVideoService.videoReverseDownSearch(queryMicroVideoCondition);
			
		}else if(videoStatus == 4){
			videoDetailsList = microVideoService.videoReverseCollectSearch(queryMicroVideoCondition);
		}
		
		PageResult pr = new PageResult();
		pr.setPageResult(videoDetailsList);
		setPage(pr);
		
		
		return "video_watch_down_collect_details";
	}
	
	
	/**
	 * 查看单个视频的方法
	 * @return
	
	public String getVideoById(){
		HttpServletRequest request = GetRequsetResponseUtil.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		microVideo = microVideoService.getVideoAddressById(id);
		return "video_add";
	} */
	
	public IMicroVideo getMicroVideoService() {
		return microVideoService;
	}
	public void setMicroVideoService(IMicroVideo microVideoService) {
		this.microVideoService = microVideoService;
	}
	
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public QueryMicroVideoCondition getQueryMicroVideoCondition() {
		return queryMicroVideoCondition;
	}

	public void setQueryMicroVideoCondition(
			QueryMicroVideoCondition queryMicroVideoCondition) {
		this.queryMicroVideoCondition = queryMicroVideoCondition;
	}

	public MicroVideo getMicroVideo() {
		return microVideo;
	}

	public void setMicroVideo(MicroVideo microVideo) {
		this.microVideo = microVideo;
	}
	public List<MicroVideo> getMicroVideoList() {
		return microVideoList;
	}

	public void setMicroVideoList(List<MicroVideo> microVideoList) {
		this.microVideoList = microVideoList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getStageNum() {
		return stageNum;
	}

	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}

	public Integer getOrderList() {
		return orderList;
	}

	public void setOrderList(Integer orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the courseId
	 */
	public Integer getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseList
	 */
	public List<Course> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	/**
	 * @return the feedCourseService
	 */
	public ICourse getFeedCourseService() {
		return feedCourseService;
	}

	/**
	 * @param feedCourseService the feedCourseService to set
	 */
	public void setFeedCourseService(ICourse feedCourseService) {
		this.feedCourseService = feedCourseService;
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
	 * @return the videoStatus
	 */
	public Integer getVideoStatus() {
		return videoStatus;
	}

	/**
	 * @param videoStatus the videoStatus to set
	 */
	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}

	/**
	 * @return the videoDetailsList
	 */
	public List<VideoDetailsDTO> getVideoDetailsList() {
		return videoDetailsList;
	}

	/**
	 * @param videoDetailsList the videoDetailsList to set
	 */
	public void setVideoDetailsList(List<VideoDetailsDTO> videoDetailsList) {
		this.videoDetailsList = videoDetailsList;
	}
	
}
