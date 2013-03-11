package com.shangde.edu.vst.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.shangde.common.service.BaseService;
import com.shangde.edu.vst.domain.VideoTeacherStaticsCountDTO;
import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;

public class VideoTeacherStaticsticsImpl extends BaseService implements IVideoTeacherStaticstics {

	public List<VideoTeacherStaticsCountDTO> getVStByAllTeacher(
			VideoTeacherStaticsCountDTO condition) {
		List<VideoTeacherStaticsCountDTO> list = Collections.emptyList();
		StringBuffer params = new StringBuffer();
		String dateFormat = "yyyyMMddHHmmss";
		if(condition != null){
			if(StringUtils.isNotEmpty(condition.getTeacherName())){
				params.append(condition.getTeacherName());
				params.append("&");
			}else{
				params.append("0");
				params.append("&");
			}
			if(condition.getProjectId() > 0){
				params.append(condition.getProjectId());
				params.append("&");
			}else{
				params.append("0");
				params.append("&");
			}
			if(null != condition.getStartTime()){
				params.append(date2String(condition.getStartTime(),dateFormat));
				params.append("&");
			}else{
				params.append("0");
				params.append("&");
			}
			if(null != condition.getEndTime()){
				params.append(date2String(condition.getEndTime(),dateFormat));
				params.append("&");
			} else {
				params.append("0");
				params.append("&");
			}
		}else{
			params.append("0&0&0&0");
		}
		IWebService webService = WebServiceFactory.getVideoService();//获取视频服务
		//获取远端webService数据
		String str = webService.getVStByAllTeacher(params.toString());
		
		if(StringUtils.isNotEmpty(str)){
			JSONArray jsonArray = JSONArray.fromObject(str);
			list = new ArrayList<VideoTeacherStaticsCountDTO>();
			for (int i=0; i < jsonArray.size(); i++){
					VideoTeacherStaticsCountDTO dto = new VideoTeacherStaticsCountDTO();
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					dto.setTeacherName(jsonObj.getString("teacherName"));
					dto.setProjectName(jsonObj.getString("projectName"));
					dto.setBuyCusAllWatchTime(jsonObj.getLong("buyCusAllWatchTime"));
					dto.setBuyCusCount(jsonObj.getLong("buyCusCount"));
					dto.setTryCusCount(jsonObj.getLong("tryCusCount"));
					dto.setTryCusAllWatchTime(jsonObj.getLong("tryCusAllWatchTime"));
					if(jsonObj.getLong("buyCusAllWatchTime") > 0 && jsonObj.getLong("buyCusCount") > 0){
						dto.setBuyCusAllWatchTimeAvg(jsonObj.getLong("buyCusAllWatchTime") / jsonObj.getLong("buyCusCount") );
					}else{
						dto.setBuyCusAllWatchTimeAvg(0l);
					}
					if(jsonObj.getLong("tryCusAllWatchTime") > 0 && jsonObj.getLong("tryCusCount") > 0){
						dto.setTryCusAllWatchTimeAvg(jsonObj.getLong("tryCusAllWatchTime") / jsonObj.getLong("tryCusCount") );
					}else{
						dto.setTryCusAllWatchTimeAvg(0l);
					}
					list.add(dto);
			}
			
		}
		return list;
	}
	
	
	public static String date2String(Date date,String exp){
		String str = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(exp);
			str = sdf.format(date);
		}
		return str;
	}
}
