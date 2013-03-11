package com.shangde.edu.vst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.vst.condition.VideoLevelCondition;
import com.shangde.edu.vst.condition.VideoStatisticsCondition;
import com.shangde.edu.vst.domain.VideoStatisticsCountDTO;
import com.shangde.edu.vst.domain.VideoStatisticsRecord;
import com.shangde.edu.vst.domain.VideoStatisticsSingleDTO;
import com.shangde.edu.vst.domain.VideoWatchLog;
import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;


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
public class VideoStaticsticsImpl extends BaseService implements IVideoStaticstics{

	public List<VideoStatisticsCountDTO> getVStByAllSubject(VideoStatisticsCountDTO condition) {
		return adjustVSTCountDTO(getVStRemoteSubject(condition),getVStLocalSubject(condition));
	}
	/**
	 * 功能:得到远端WebService数据
	 * @param condition
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-7
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<VideoStatisticsCountDTO> getVStRemoteSubject(VideoStatisticsCountDTO condition){
		List videoCoutDtoList = new ArrayList();
		IWebService webService = WebServiceFactory.getVideoService();
		StringBuffer sb = new StringBuffer();
		//拼接查询条件
		if(condition != null){
			if(condition.getSubjectId() != null){
				sb.append(condition.getSubjectId());
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getStart() != null){
				sb.append(date2String(condition.getStart(),"yyyyMMddHHmmss"));
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getEnd() != null){
				sb.append(date2String(condition.getEnd(),"yyyyMMddHHmmss"));
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if (condition.isIncludeAudition()) {
				sb.append("1");
				sb.append("&");
			} else {
				sb.append("0");
				sb.append("&");
			}
		}else{
			sb.append("0&0&0&0");
		}
		//获取远端webService数据
		String str = webService.getVStByAllSubject(sb.toString());
		if(str != null && str.length() > 0){
			JSONArray jsonArray = JSONArray.fromObject(str);
			//Json数组转java bean List
			if (jsonArray != null && jsonArray.size() > 0){
				for (int i=0; i < jsonArray.size(); i++){
					VideoStatisticsCountDTO dto = new VideoStatisticsCountDTO();
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					dto.setSubjectId(Integer.parseInt((String)jsonObj.get("subjectId")));
					dto.setWatchFrAll(Integer.parseInt((String)jsonObj.get("watchFrAll")));
					dto.setWatchedTotal(Integer.parseInt((String)jsonObj.get("watchedTotal")));
					dto.setAllWatchTime(Integer.parseInt((String)jsonObj.get("allWatchTime")));
					String strCusNum = (String)jsonObj.get("watchedCusNum");
					if(strCusNum != null && strCusNum.length() > 0){
						dto.setWatchedCusNum(Integer.parseInt((String)jsonObj.get("watchedCusNum")));
					}else{
						dto.setWatchedCusNum(0);
					}
					dto.setWatchedTimeFr(Integer.parseInt((String)jsonObj.get("watchedTimeFr")));
					videoCoutDtoList.add(dto);
				}
			}
	}
		return videoCoutDtoList;
	}
	
	public static String date2String(Date date,String exp){
		String str = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(exp);
			str = sdf.format(date);
		}
		return str;
	}
	
	
	private static Date string2Date(String date,String exp) throws ParseException{
		Date tmpDate = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(exp);
			tmpDate = sdf.parse(date);
		}
		return tmpDate;
	}
	/**
	 * 功能:获取远端数据，与本地数据调整
	 * @param list
	 * @param mapList
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-7
	 */
	public List<VideoStatisticsCountDTO> adjustVSTCountDTO(List<VideoStatisticsCountDTO> remoteList,List<VideoStatisticsCountDTO> localList){
		if(remoteList != null && localList != null && remoteList.size() > 0 && localList.size() > 0){
			for (VideoStatisticsCountDTO local : localList){
				for (VideoStatisticsCountDTO remote : remoteList) {
					if(local.getSubjectId().equals(remote.getSubjectId())){
						local.setWatchFrAll(remote.getWatchFrAll());
						local.setWatchedTotal(remote.getWatchedTotal());
						local.setAllWatchTime(remote.getAllWatchTime());
						local.setWatchedCusNum(remote.getWatchedCusNum());
						local.setWatchedTimeFr(remote.getWatchedTimeFr());
						break;
					}
				}
			}
		}
		return localList;
	}
	
	/**
	 * 功能:查本地库，得到项目下所有课程节数
	 * @param condition
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-7
	 */
	private List<VideoStatisticsCountDTO> getVStLocalSubject(VideoStatisticsCountDTO condition){
		return simpleDao.getForList("VstCount_NS.getSubjectVideoCount", condition);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VideoStatisticsRecord> getVideoStatisticsRecordList(VideoStatisticsCondition condition) throws ParseException {
		List videoRecordList = new ArrayList();
		IWebService webService = WebServiceFactory.getVideoService();
		StringBuffer sb = new StringBuffer();
		if(condition != null){
			if(condition.getSubjectId() != 0){
				sb.append(condition.getSubjectId());
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getStart() != null){
				sb.append(date2String(condition.getStart(),"yyyyMMddHHmmss"));
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getEnd() != null){
				sb.append(date2String(condition.getEnd(),"yyyyMMddHHmmss"));
				sb.append("&");
			}else{
				sb.append("0&");
			}
				sb.append(condition.getStartRecord()+"&");
				System.out.println(condition.getStartRecord());
				sb.append(condition.getEndRecord());
				System.out.println(condition.getEndRecord());
				System.out.println("sb=="+sb);
		}else{
			sb.append("0&0&0&1&30");
		}
		//sb.append(condition.getStartRecord()).append("&");
		//sb.append(condition.getEndRecord());
		String str = webService.getVStRecordDetail(sb.toString());
		if(str != null && str.length() > 0){
			JSONArray jsonArray = JSONArray.fromObject(str);
			for(int i=0; i<jsonArray.size();i++){
				
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				VideoStatisticsRecord videoRecord = new VideoStatisticsRecord();
				videoRecord.setCourseId(Integer.parseInt((String)jsonObj.get("courseId")));
				videoRecord.setCusId(Integer.parseInt((String)jsonObj.get("cusId")));
				videoRecord.setEmail(((String)jsonObj.get("email")));
				
				try{
					videoRecord.setEndTime(string2Date((String)jsonObj.get("endTime"),"yyyyMMddHHmmss"));
					videoRecord.setStartTime(string2Date((String)jsonObj.get("startTime"),"yyyyMMddHHmmss"));
				}catch(Exception e){
					e.printStackTrace();
				}
				
				videoRecord.setId(Integer.parseInt((String)jsonObj.get("id")));
				videoRecord.setSellWayId(Integer.parseInt((String)jsonObj.get("sellWayId")));
				videoRecord.setSubjectId(Integer.parseInt((String)jsonObj.get("subjectId")));
				videoRecord.setTeacher(((String)jsonObj.get("teacher")));
				videoRecord.setVideoName(((String)jsonObj.get("videoName")));
				videoRecord.setVideoPointId(Integer.parseInt((String)jsonObj.get("videoPointId")));
				videoRecord.setWatchAlltime(Integer.parseInt((String)jsonObj.get("watchAlltime")));
				//fanxin
				
				videoRecord.setCourseName(((String)jsonObj.getString("courseName")));
				videoRecord.setSellWayTitle(((String)jsonObj.getString("sellWayTitle")));
				videoRecord.setSubjectName(((String)jsonObj.getString("subjectName")));
				
				videoRecordList.add(videoRecord);
				
				
			}
		}
		return videoRecordList;
	}
	
	public Integer getVideoStatisticsRecordCount(VideoStatisticsCondition condition) {
		IWebService webService = WebServiceFactory.getVideoService();
		StringBuffer sb = new StringBuffer();
		if(condition != null){
			if(condition.getSubjectId() != 0){
				sb.append(condition.getSubjectId());
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getStart() != null){
				sb.append(date2String(condition.getStart(),"yyyyMMddHHmmss"));
				sb.append("&");
			}else{
				sb.append("0");
				sb.append("&");
			}
			if(condition.getEnd() != null){
				sb.append(date2String(condition.getEnd(),"yyyyMMddHHmmss"));
			}else{
				sb.append("0&");
			}
		}else{
			sb.append("0&0&0&");
		}
		return Integer.parseInt(webService.getVStRecordCount(sb.toString()));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<VideoStatisticsSingleDTO> getVStBySingalSubject(int subjectId) {
		List videoCountSingleList = new ArrayList();
		IWebService webService = WebServiceFactory.getVideoService();
		String str = webService.getVStBySingleSubject(String.valueOf(subjectId));
		if(str != null && str.length() > 0){
			JSONArray jsonArray = JSONArray.fromObject(str);
			if (jsonArray != null && jsonArray.size() > 0){
				for (int i=0; i < jsonArray.size(); i++){
					VideoStatisticsSingleDTO dto = new VideoStatisticsSingleDTO();
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					dto.setMonth(Integer.parseInt((String)jsonObj.get("month")));
					dto.setLearningAvgTimes((String)jsonObj.get("learningAvgTimes"));
					dto.setLearningTotalTimes((String)jsonObj.get("learningTotalTimes"));
					dto.setLoginTimes(Integer.parseInt((String)jsonObj.get("loginTimes")));
					dto.setSubjectId(Integer.parseInt((String)jsonObj.get("subjectId")));
					dto.setSubjectName((String)jsonObj.get("subjectName"));
					dto.setWatchedPercent(Float.parseFloat((String)jsonObj.get("watchedPercent")));
					dto.setWatchedTimes((String)jsonObj.get("watchedTimes"));
					dto.setWatchedTimesAll((String)jsonObj.get("watchedTimesAll"));
					dto.setWatchedTotal((String)jsonObj.get("watchedTotal"));
					videoCountSingleList.add(dto);
				}
			}
		}
		return videoCountSingleList;
	}

	
	public static void main(String[] args) {
		VideoStaticsticsImpl impl = new VideoStaticsticsImpl();
		List<VideoStatisticsCountDTO> list = impl.getVStByAllSubject(null);
		//List<VideoStatisticsSingleDTO> list = impl.getVStBySingalSubject();
		System.out.println(list.size());
		//System.out.println(list.size());
	}
	/**
	 * 查询观看用户数，根据项目区分
	 */
	public List<VideoWatchLog> getVideoWatchLogList(QuerySubjectCondition condition) {
		
		//获取登录的用户数
		List<VideoWatchLog> list = simpleDao.getForList("VstCount_NS.getVideoWatchLogin", condition);
		
		if(list!=null && list.size()>0){
			IWebService webService = WebServiceFactory.getVideoService();
			
			//获取观看购买课程的人数和时间
			String querystr="";
			if(condition.getStartTime()==null|| "".equals(condition.getStartTime())){
				querystr+="0";
			}else{
				querystr+=condition.getStartTime();
			}
			if(condition.getEndTime()==null|| "".equals(condition.getEndTime())){
				querystr+=",0";
			}else{
				querystr+=","+condition.getEndTime();
			}
			if(condition.getSubjectId()==0){
				querystr+=",0";
			}else{
				querystr+=","+condition.getSubjectId();
			}
			String buystr =webService.getVideoWatchList(querystr+",0");//传0代表取观看购买课程的人数和时间
			JSONArray buyjsonArray= JSONArray.fromObject(buystr);
			HashMap<String,String> buyhashMap = new HashMap<String, String>();
			for (int i=0;i<buyjsonArray.size();i++){
				JSONObject jsonObject = (JSONObject) buyjsonArray.get(i);
				buyhashMap.put(jsonObject.getString("subjectId"), jsonObject.getString("watchtime")+","+jsonObject.getString("cusCount"));
			}
			
			String bobuystr =webService.getVideoWatchList(querystr+",1");//传1代表取观看试听课程的人数和时间
			JSONArray nojsonArray= JSONArray.fromObject(bobuystr);
			HashMap<String,String> nohashMap = new HashMap<String, String>();
			for (int i=0;i<nojsonArray.size();i++){
				JSONObject jsonObject = (JSONObject) nojsonArray.get(i);
				nohashMap.put(jsonObject.getString("subjectId"), jsonObject.getString("watchtime")+","+jsonObject.getString("cusCount"));
			}
			
			//将视频数据放到查询的list中
			for (VideoWatchLog userData:list){
				//购买的人数和时间
				String buysub =buyhashMap.get(""+userData.getSubjectId());
				if (buysub!=null && !".".equals(buysub)){
					try {
						userData.setBuywatchtime(Integer.valueOf(
								buysub.split(",")[0]//观看时间
								));
						userData.setWatchusercount(Integer.valueOf(
								buysub.split(",")[1]//观看人数
								));
					} catch (Exception e) {
						userData.setBuywatchtime(0);
						userData.setWatchusercount(0);
					}
					
				}else{
					userData.setBuywatchtime(0);
					userData.setWatchusercount(0);
				}
				
				//试听的人数和时间
				String nosub =nohashMap.get(""+userData.getSubjectId());
				if (nosub!=null && !".".equals(nosub)){
					try {
						userData.setShitingwatchtime(Integer.valueOf(
								nosub.split(",")[0]//观看时间
								));
						userData.setShitingusercount(Integer.valueOf(
								nosub.split(",")[1]//观看人数
								));
					} catch (Exception e) {
						userData.setShitingwatchtime(0);
						userData.setShitingusercount(0);
					}
				}else{
					userData.setShitingwatchtime(0);
					userData.setShitingusercount(0);
				}
				//设置比例
				if(userData.getWatchusercount()!=0){
					userData.setAve_buywatchtime(new BigDecimal(userData.getBuywatchtime()/userData.getWatchusercount()).setScale(0, RoundingMode.HALF_UP));// 平均观看收费视频的时长
				}else{
					userData.setAve_buywatchtime(new BigDecimal(0).setScale(0, RoundingMode.HALF_UP));// 平均观看收费视频的时长
				}
				if(userData.getShitingusercount()!=0){
					userData.setAve_shitingwatchtime(new BigDecimal(userData.getShitingwatchtime()/userData.getShitingusercount()).setScale(0, RoundingMode.HALF_UP));// 平均观看试听课程的时长
				}else{
					userData.setAve_shitingwatchtime(new BigDecimal(0).setScale(0, RoundingMode.HALF_UP));// 
				}
				if ((userData.getWatchusercount()+userData.getShitingusercount())!=0){
					userData.setScale_buy(
							new BigDecimal((userData.getWatchusercount()+0.00)/(userData.getWatchusercount()+userData.getShitingusercount()))
							.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));// 观看收费视频的比例
				}else{
					userData.setScale_buy(new BigDecimal(0).setScale(2, RoundingMode.HALF_UP));
				}
				
				userData.setScale_shiting(new BigDecimal(100).subtract(userData.getScale_buy()).setScale(2, RoundingMode.HALF_UP));// 观看试听课程的比例
			}
		}
		return list;
		
		
	}
	@Override
	public List getVideoSelledCount(VideoLevelCondition condtion) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("VstCount_NS.getVideoKpointCount", condtion);
	}
	
	

}
