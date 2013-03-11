package com.shangde.edu.vst.action;


import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.vst.condition.VideoLevelCondition;
import com.shangde.edu.vst.domain.VideoLevelStastics;
import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;
import com.shangde.edu.vst.service.IVideoStaticstics;

/**
 * 视频打分统计
 * @author Yangning
 *
 */
public class VideoLevelStasticsAction  extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(VideoLevelStasticsAction.class);
	
	private static final long serialVersionUID = 1L;
	private VideoLevelCondition condition;
	
	private static final int DEFAULTPAGESIZE = 30;
	
	private ISubject subjectService;
	
	private List<Subject> subList;
	
	private String exportname;
	
	private Integer timeType;
	
	private String json;
	
	private IVideoStaticstics iVideoStaticsticsService; 
	
	/**导出excel流文件**/
	private InputStream excelFile;
	
	public String getVlevelPage(){
		try{
			IWebService webService = WebServiceFactory.getVideoService();
			String startDate = null;
			String endDate = null;
			Calendar cal = Calendar.getInstance();
			VideoLevelCondition localCondition = new VideoLevelCondition();
			if(getCondition().getStartDate() == null && getCondition().getEndDate() == null && timeType == null){
				cal.add(Calendar.DAY_OF_MONTH, -1);
				startDate = date2String(cal.getTime(),"yyyyMMddHHmmss");
				endDate = date2String(new Date(),"yyyyMMddHHmmss");
				localCondition.setStartDate(cal.getTime());
				localCondition.setEndDate(new Date());
			}else{
				if(timeType == null || timeType <= 0){
					startDate = getCondition().getStartDate() == null ? null : date2String(getCondition().getStartDate(),"yyyyMMddHHmmss");
					endDate = getCondition().getEndDate() == null ? null : date2String(getCondition().getEndDate(),"yyyyMMddHHmmss");
					localCondition.setStartDate(cal.getTime());
					localCondition.setEndDate(new Date());
				}else{
					switch (timeType){
						case  1:
							//设置时间
							cal.add(Calendar.DAY_OF_MONTH, -1);
							startDate = date2String(cal.getTime(),"yyyyMMddHHmmss");
							endDate = date2String(new Date(),"yyyyMMddHHmmss");
							localCondition.setStartDate(cal.getTime());
							localCondition.setEndDate(new Date());
						break;
						case  2:
							//设置时间
							cal.add(Calendar.DAY_OF_MONTH, -7);
							startDate = date2String(cal.getTime(),"yyyyMMddHHmmss");
							endDate = date2String(new Date(),"yyyyMMddHHmmss");
							localCondition.setStartDate(cal.getTime());
							localCondition.setEndDate(new Date());
						break;
						case  3:
							//设置时间
							cal.add(Calendar.DAY_OF_MONTH, -30);
							startDate = date2String(cal.getTime(),"yyyyMMddHHmmss");
							endDate = date2String(new Date(),"yyyyMMddHHmmss");
							localCondition.setStartDate(cal.getTime());
							localCondition.setEndDate(new Date());
						break;
					}
				}
			}
			localCondition.setSubjectId(getCondition().getSubjectId());
			localCondition.setVideoName(getCondition().getVideoName());
			List list = iVideoStaticsticsService.getVideoSelledCount(localCondition);
			Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
			if(list != null && list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					Map resultMap = (Map)list.get(i);
					if(resultMap != null){
						hashMap.put(((Integer)resultMap.get("POINTID")).intValue(),((BigDecimal)resultMap.get("PointVideoTotal")).intValue());
					}
				}
			}
			condition.setPageSize(DEFAULTPAGESIZE);
			String videoName = getCondition().getVideoName() == null ? null : URLDecoder.decode(getCondition().getVideoName(), "utf-8");
			String resultJson =  webService.getVlevelStatics(startDate, endDate, getCondition().getSubjectId(), videoName , getCondition().getCurrentPage(), DEFAULTPAGESIZE);
			if(resultJson!= null){
				if(resultJson.equals("novalue")){
					this.setMessage("此阶段没有数据");
					return "invalid";
				}
				if(resultJson.equals("invalidparam")){
					this.setMessage("输入查询参数非法");
					return "invalid";
				}
				PageResult page = json2VlevelPage(resultJson);
				
				if(!page.getPageResult().isEmpty()){
					for(int i=0;i<page.getPageResult().size();i++){
						VideoLevelStastics v = (VideoLevelStastics)page.getPageResult().get(i);
						v.setPointSoldCount(hashMap.get(v.getPointId()));
					}
				}
				this.setPage(page);
				this.getPage().setCurrentPage(getCondition().getCurrentPage());
				this.getPage().setPageSize(DEFAULTPAGESIZE);
				setPageUrlParms();
				subList = subjectService.getAllSubject();
			}
			
		}catch(Exception e){
			logger.error("VideoLevelStasticsAction.getVlevelPage",e);
			return ERROR;
		}
		return "vlevelPage";
	}
	/**
	 * ajax 统计数据
	 * @return
	 */
	public String ajaxStatistics(){
		try{
			float staticstotal = 0f;
			List locallist = iVideoStaticsticsService.getVideoSelledCount(this.getCondition());
			Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
			if(locallist != null && locallist.size() > 0){
				for (int i = 0; i < locallist.size(); i++) {
					Map resultMap = (Map)locallist.get(i);
					if(resultMap != null){
						hashMap.put(((Integer)resultMap.get("POINTID")).intValue(),((BigDecimal)resultMap.get("PointVideoTotal")).intValue());
					}
				}
			}
			
			IWebService webService = WebServiceFactory.getVideoService();
			String startDate = getCondition().getStartDate() == null ? null : date2String(getCondition().getStartDate(),"yyyyMMddHHmmss");
			String endDate = getCondition().getEndDate() == null ? null : date2String(getCondition().getEndDate(),"yyyyMMddHHmmss");
			condition.setPageSize(8000);
			String videoName = getCondition().getVideoName() == null ? null : URLDecoder.decode(getCondition().getVideoName(), "utf-8");
			String resultJson =  webService.getVlevelStatics(startDate, endDate, getCondition().getSubjectId(), videoName , 1, condition.getPageSize());
			if(resultJson!= null){
				if(resultJson.equals("novalue")){
					setJson("false");
					return "json";
				}
				if(resultJson.equals("invalidparam")){
					setJson("false");
					return "json";
				}
			PageResult result = json2VlevelPage(resultJson);
			
			List<VideoLevelStastics> videoLevelList = result.getPageResult();
			if(videoLevelList != null && videoLevelList.size() > 0){
				for(int i=0;i< videoLevelList.size();i++){
					VideoLevelStastics v = videoLevelList.get(i);
						v.setPointSoldCount(hashMap.get((v).getPointId()));
						float count = calculate(v.getPointSoldCount() == null ? 0 : v.getPointSoldCount(),
								v.getOpendUser() == null ? 0 : v.getOpendUser(),
								v.getGoodevaluateCount(),
								v.getBadevaluateCount(),
								v.getOpenedCount() == null ? 0 : v.getOpenedCount());
						staticstotal += count;
					}
				}
			}
			setJson(String.valueOf(staticstotal));
			return "json";
		}catch (Exception ex) {
			setJson("false");
			return "json";
		}
		
		
	}
	
	/**导出Excel表格**/
	public String chkOutExcel() {
		String forward = "excel";
		try {
			/*-----生成文件名-------*/
			exportname="评价列表_" + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
			/*-------针对浏览器下载乱码处理------*/
				setExportname(URLEncoder.encode(exportname, "UTF-8"));
			 }else{
				setExportname(new String((exportname).getBytes(),"iso-8859-1"));
			 }
			
			List locallist = iVideoStaticsticsService.getVideoSelledCount(this.getCondition());
			Map<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
			if(locallist != null && locallist.size() > 0){
				for (int i = 0; i < locallist.size(); i++) {
					Map resultMap = (Map)locallist.get(i);
					if(resultMap != null){
						hashMap.put(((Integer)resultMap.get("POINTID")).intValue(),((BigDecimal)resultMap.get("PointVideoTotal")).intValue());
					}
				}
			}
			
			
			IWebService webService = WebServiceFactory.getVideoService();
			String startDate = getCondition().getStartDate() == null ? null : date2String(getCondition().getStartDate(),"yyyyMMddHHmmss");
			String endDate = getCondition().getEndDate() == null ? null : date2String(getCondition().getEndDate(),"yyyyMMddHHmmss");
			condition.setPageSize(8000);
			String videoName = getCondition().getVideoName() == null ? null : URLDecoder.decode(getCondition().getVideoName(), "utf-8");
			String resultJson =  webService.getVlevelStatics(startDate, endDate, getCondition().getSubjectId(), videoName , 1, condition.getPageSize());
			if(resultJson!= null){
				if(resultJson.equals("novalue")){
					this.setMessage("此阶段没有数据");
					return "invalid";
				}
				if(resultJson.equals("invalidparam")){
					this.setMessage("输入查询参数非法");
					return "invalid";
				}
			PageResult result = json2VlevelPage(resultJson);
			
			
			
			List<VideoLevelStastics> videoLevelList = result.getPageResult();
			if(videoLevelList != null && videoLevelList.size() > 0){
				for(int i=0;i< videoLevelList.size();i++){
					VideoLevelStastics v = videoLevelList.get(i);
						v.setPointSoldCount(hashMap.get((v).getPointId()));
					}
				}
			List<List<String>> list = new ArrayList<List<String>>();
			/*-------表头------*/
			List<String> headList = new ArrayList<String>();
			headList.add("项目");
			headList.add("视频ID");
			headList.add("视频名称");
			headList.add("付费人数");
			headList.add("观看率");
			headList.add("评价率");
			headList.add("好评");
			headList.add("坏评");
			headList.add("课程权数");
			headList.add("课程改善指数");
			list.add(headList);
			/*----表身----*/
			for (int i = 0; i < videoLevelList.size(); i++) {
				VideoLevelStastics videlLevel = videoLevelList.get(i);
				List<String> strList = fromatDTO(videlLevel,i);
				
				list.add(strList);
			}
			/*---导出---*/
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			}
		} catch (Exception ex) {
			logger.error("RefundAction.exportCSV",ex);
			return ERROR;
		}
		return forward;
	}
	
	/**格式化DTO**/
	private List<String> fromatDTO(VideoLevelStastics videoLevel,int i){
		List<String> list = new ArrayList<String>();
		list.add(videoLevel.getSubjectName());
		list.add(videoLevel.getPointId() == null ? "0" : videoLevel.getPointId().toString());
		list.add(videoLevel.getVideoName());
		list.add(videoLevel.getPointSoldCount() == null ? "0" :videoLevel.getPointSoldCount().toString());
		
		if(videoLevel.getPointSoldCount()!= null && 
				videoLevel.getPointSoldCount()> 0 && videoLevel.getOpendUser() != null && 
				videoLevel.getOpendUser() > 0){
			if(Float.parseFloat(videoLevel.getOpendUser().toString()) /videoLevel.getPointSoldCount() > 1){
			  list.add("");
			}else{
				float re = videoLevel.getOpendUser().floatValue() /videoLevel.getPointSoldCount();
				list.add((round(re * 100,2,BigDecimal.ROUND_HALF_UP) )+"%");
			}
		}else{
			list.add("");
		}
		
		if(videoLevel.getCusAll() !=null &&  videoLevel.getCusAll() >0
				&& videoLevel.getOpenedCount() != null && videoLevel.getOpenedCount() >0){
			if(videoLevel.getCusAll()/videoLevel.getOpenedCount() >1){
				list.add("100%");
			}else{
				list.add((round( Float.parseFloat(videoLevel.getCusAll().toString()) /videoLevel.getOpenedCount() ,2,BigDecimal.ROUND_HALF_UP) * 100)+"%");
			}
		}else{
			list.add("0.0%");
		}
		
		if(videoLevel.getGoodevaluateCount() >0 && videoLevel.getOpenedCount() != null 
				&& videoLevel.getOpenedCount() >0){
			if(videoLevel.getGoodevaluateCount()/videoLevel.getOpenedCount() >1){
				list.add("100%");
			}else{
				list.add((round( Float.parseFloat(videoLevel.getGoodevaluateCount()+"") /videoLevel.getOpenedCount() ,2,BigDecimal.ROUND_HALF_UP) * 100)+"%");
			}
		}else{
			list.add("0.0%");
		}
		
		if(videoLevel.getBadevaluateCount() >0 && videoLevel.getOpenedCount() != null 
				&& videoLevel.getOpenedCount() >0){
			if(videoLevel.getBadevaluateCount()/videoLevel.getOpenedCount() >1){
				list.add("100%");
			}else{
				list.add((round( Float.parseFloat(videoLevel.getBadevaluateCount()+"") /videoLevel.getOpenedCount() ,2,BigDecimal.ROUND_HALF_UP) * 100)+"%");
			}
		}else{
			list.add("0.0%");
		}
		
		list.add("1.0");
		
		float result = calculate(videoLevel.getPointSoldCount() == null ? 0 : videoLevel.getPointSoldCount(),
				videoLevel.getOpendUser() == null ? 0 : videoLevel.getOpendUser(),
				videoLevel.getGoodevaluateCount(),
				videoLevel.getBadevaluateCount(),
				videoLevel.getOpenedCount() == null ? 0 : videoLevel.getOpenedCount());
		
		if(result >0){
			list.add(round(result,2,BigDecimal.ROUND_HALF_UP)+"");
		}else{
			list.add("0");
		}
		return list;
	}
	
	private float calculate(int pointSoldCount,int openUser,
			int goodevaluateCount,int badevaluateCount,int count){
		float weights = 1.0f;//权数
		int buyCount = 0;//购买人数
		if(pointSoldCount >0){
			buyCount = pointSoldCount;
		}
		int openCount = 0;//观看人数
		if(openUser >0){
			openCount = openUser;
		}
		float seeRate = 0f;
		if(openCount>0 && buyCount >0){
			seeRate = Float.parseFloat( openCount + "") / buyCount;
		}
		float good = 0f;
		if(goodevaluateCount >0 && count >0){
			good = Float.parseFloat( goodevaluateCount + "") / count ;
		}
		float bad = 0f;
		if(badevaluateCount >0 && count >0){
			bad = Float.parseFloat( badevaluateCount + "") / count;
		}
		float result = (weights * buyCount * seeRate * good) - (weights * buyCount * seeRate * bad);
		return result;
	}

	
	private  float round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		float d = bd.floatValue();
		bd = null;
		return d;
	}
	
	/**
	 * json转为Page对象
	 * @param json
	 * @return
	 */
	private PageResult json2VlevelPage(String json){
		JSONObject object = JSONObject.fromObject(json);
		PageResult paResult = new PageResult();
		if(object != null){
				paResult.setTotalRecord((Integer)object.get("totalRecord"));
				paResult.setPageSize(DEFAULTPAGESIZE);
				List<VideoLevelStastics> videlLevelList = new ArrayList<VideoLevelStastics>();
				JSONArray jsonArray = JSONArray.fromObject((String)object.getString("pageResult"));
				for(int i=0; i<jsonArray.size();i++){
					JSONObject innerObj = (JSONObject)jsonArray.get(i);
					if(!innerObj.isNullObject()){
						VideoLevelStastics videoStastics = new VideoLevelStastics();
						if(innerObj.get("cusAll") == null || ((String)innerObj.get("cusAll")).equals("")){
							videoStastics.setCusAll(0);
						}else{
							videoStastics.setCusAll(Integer.parseInt((String)innerObj.get("cusAll")));
						}
						if(innerObj.get("cusDistinctAll") == null || ((String)innerObj.get("cusDistinctAll")).equals("")){
							videoStastics.setCusDistinctAll(0);
						}else{
							videoStastics.setCusDistinctAll(Integer.parseInt((String)innerObj.get("cusDistinctAll")));
						}
						if(innerObj.get("openedCount") == null || ((String)innerObj.get("openedCount")).equals("")){
							videoStastics.setOpenedCount(0);
						}else{
							videoStastics.setOpenedCount(Integer.parseInt((String)innerObj.get("openedCount")));
						}
						if(innerObj.get("opendUser") == null || ((String)innerObj.get("opendUser")).equals("")){
							videoStastics.setOpendUser(0);
						}else{
							videoStastics.setOpendUser(Integer.parseInt((String)innerObj.get("opendUser")));
						}
						videoStastics.setGoodevaluateCount(Integer.parseInt((String)innerObj.get("goodevaluateCount")));
						videoStastics.setEvaluateCount(Integer.parseInt((String)innerObj.get("evaluateCount")));
						videoStastics.setBadevaluateCount(Integer.parseInt((String)innerObj.get("badevaluateCount")));
						videoStastics.setPointId(Integer.parseInt((String)innerObj.get("pointId")));
						videoStastics.setVideoName((String)innerObj.get("videoName"));
						videoStastics.setSubjectName((String)innerObj.get("subjectName"));
						videlLevelList.add(videoStastics);
					}
				}
				paResult.setPageResult(videlLevelList);
			}
			return paResult;
	}
	
	public int getTimeType() {
		return timeType;
	}

	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public static String date2String(Date date,String exp){
		String str = null;
		if(date != null){
			SimpleDateFormat sdf = new SimpleDateFormat(exp);
			str = sdf.format(date);
		}
		return str;
	}
	
	public VideoLevelCondition getCondition() {
		if(condition == null){
			condition = new VideoLevelCondition();
		}
		return condition;
	}

	public void setCondition(VideoLevelCondition condition) {
		this.condition = condition;
	}
	
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}
	
	public String getExportname() {
		return exportname;
	}

	public void setExportname(String exportname) {
		this.exportname = exportname;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public IVideoStaticstics getiVideoStaticsticsService() {
		return iVideoStaticsticsService;
	}

	public void setiVideoStaticsticsService(IVideoStaticstics iVideoStaticsticsService) {
		this.iVideoStaticsticsService = iVideoStaticsticsService;
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
