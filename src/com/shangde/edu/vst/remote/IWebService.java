package com.shangde.edu.vst.remote;


/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-6
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public interface IWebService {
	
	/**
	 * 功能:获取统计信息
	 * @param params 格式 subjectid&begintime&endtime
	 * @return json 数组
	 * Author:Yangning
	 * CreateDate:2012-2-9
	 */
	public String getVStByAllSubject(String params);
	
	public String getVStBySingleSubject(String params);
	
	/**
	 * 功能:获取日志详情分页,
	 * @param params  参数格式---项目id,观看开始时间，观看结束时间，分页开始，分页结束(1&20&20110810&20110910&26)  0未传递任何参数
	 * @return json 数组 
	 * Author:Yangning
	 * CreateDate:2012-2-9
	 */
	public String getVStRecordDetail(String params);
	
	/**
	 *功能:日志记录数据数量接口 
	 *@param params  参数格式---项目id,观看开始时间，观看结束时间
	 * @return	String
	 * Author:Yangning
	 * CreateDate:2012-2-9
	 */
	public String getVStRecordCount(String params);
	
	/**
	 * 获得观看视频数量按项目区分
	 */
	public String getVideoWatchList(String param);
	
	/**
	 * 功能:统计视频打分接口  返回json串
	 * @param startDate 日期格式(yyyyMMddHHmmss)
	 * @param endDate   日期格式(yyyyMMddHHmmss)
	 * @param subjectId 项目id
	 * @param videoName 视频名称
	 * @param currentPage 当前页
	 * @param currentPage 分页大小
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-05-30
	 */
	public String getVlevelStatics(String startDate,String endDate,int subjectId,String videoName,int currentPage,int pageSize);
	
	/**
	 * 统计教师（购买，试听）人数及时长
	 * @param param 参数格式--项目ID,教师名称,开始时间,结束时间
	 * @return String
	 */
	public String getVStByAllTeacher(String param);
	
	/**
	 * 
	 * @Title: queryFinanceBuyLog 
	 * @Description: TODO(根据条件查询订单记录列表) 
	 * @param @param subjectId
	 * @param @param start_time
	 * @param @param end_time
	 * @param @return    设定文件 
	 * @return String    返回类型
	 * @author shixiaofeng@sunland.org.cn 
	 * @throws
	 */
	public String queryFinanceBuyLog(int subjectId,String start_time,String end_time);
	
	/**
	 * 插入用户视频统计数据总和
	 * @return
	 */
	public void addCusVideoRankToTable();
	
	/**
	 * 获得观看时长排名前500,按照观看条数排名
	 * @author liuqinggang
	 * @param type,查找类型，1为按项目，2为按商品
	 * @param watchId 根据type传所需项目id或商品id
	 * @return String 返回格式1,2,3
	 */
	public String getWatchRank(String type,String watchId) ;
}
