package com.shangde.edu.vst.action;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.vst.condition.VideoStatisticsCondition;
import com.shangde.edu.vst.domain.VideoStatisticsCountDTO;
import com.shangde.edu.vst.domain.VideoStatisticsRecord;
import com.shangde.edu.vst.domain.VideoStatisticsSingleDTO;
import com.shangde.edu.vst.domain.VideoWatchLog;
import com.shangde.edu.vst.service.IVideoStaticstics;
import com.shangde.edu.crm.action.CrmChanceAction;

public class VideoStatisticsAction extends CommonAction {
	
	private static final int DEFALUT_SUBJECT_ID = 1;
	private static final long serialVersionUID = 1L;
	
	
	
	private static final Logger logger = Logger.getLogger(VideoStatisticsAction.class);
	
	private IVideoStaticstics iVideoStaticsticsService;
	
	private List<VideoStatisticsCountDTO> vstList;
	
	private List<VideoStatisticsSingleDTO> vstSingleList;
	
	private VideoStatisticsCountDTO vstCondition;
	
	private VideoStatisticsCondition reCondtion;
	
	

	private ISubject subjectService;
	
	private List<Subject> subList;
	
	private List<VideoStatisticsRecord> recordList;
	
	
	private int subjectId;

	public String getVSCount(){
		try{
			setVstList(iVideoStaticsticsService.getVStByAllSubject(vstCondition));
			subList = subjectService.getAllSubject();
		}catch(Exception e){
			logger.error("VideoStatisticsAction.getVSCount", e);
		}
		return "vsCount";
	}
	
	public String getVRPage(){
		try{
			//this.getPage().setPageSize(30);
			PageResult page = new PageResult();
			page.setCurrentPage(this.getReCondtion().getCurrentPage());
			reCondtion.setPageSize(30);
			int result = iVideoStaticsticsService.getVideoStatisticsRecordCount(reCondtion);
			page.setTotalRecord(result);
			page.setPageResult(iVideoStaticsticsService.getVideoStatisticsRecordList(reCondtion));
			page.setPageSize(30);
			this.setPage(page);
			setPageUrlParms();
			subList = subjectService.getAllSubject();
		}catch(Exception e){
			logger.error("VideoStatisticsAction.getVSCount", e);
		}
		return "vrPage";
	}
	
	public String getVSSingle(){
		try{
			vstSingleList = iVideoStaticsticsService.getVStBySingalSubject(getSubjectId());
			subList = subjectService.getAllSubject();
		}catch(Exception e){
			logger.error("VideoStatisticsAction.getVSSingle", e);
		}
		return "vsSingle";
	}
	
	
	/**
	 * 导出EXCEL表格
	 * @return void
	 * fanxin
	 */
	public String createExcel(){
		try{
			//定义表头信息
			String[] headName={"用户Id","用户注册邮箱","所属商品(售卖方式)","课程","视频节点ID","视频名称","任课教师","开始观看时间","结束观看时间","观看时长（单位/分钟）","所属项目"};
			
			getReCondtion().setPageSize(5000); //设置查询数量
			List<VideoStatisticsRecord> listVSR = iVideoStaticsticsService.getVideoStatisticsRecordList(reCondtion);
			//格式化时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);			
			HSSFCell cell = row.createCell((short) 0);
			
			for(int i=0;i<headName.length;i++){
				cell = row.createCell((short) i);  
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(headName[i]);
			}
			
			for (int i = 0,j = 1;listVSR!=null && i < listVSR.size(); i++,j++) {
				row = sheet.createRow(j);
				//用户Id
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getCusId());
				//电子邮箱
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getEmail());
				//所属商品(售卖方式)
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getSellWayTitle());
				//课程	
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getCourseName());
				//视频节点ID 	
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getVideoPointId());
				//视频名称 
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getVideoName());
				//任课教师 	 
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getTeacher());
				//开始观看时间 	
				cell = row.createCell((short) 7);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(sdf.format(listVSR.get(i).getStartTime()));
				//结束观看时间
				cell = row.createCell((short) 8);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(sdf.format(listVSR.get(i).getEndTime()));
				//观看时长（单位/分钟）
				cell = row.createCell((short) 9);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getWatchAlltime());
				//所属项目（售卖方式所属的专业）
				cell = row.createCell((short) 10);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getSubjectName());
				
			}
			
			/*
			File file=new File("D:/"+fileName);
			FileOutputStream fos = new FileOutputStream(file); 
			workbook.write(fos);          
			fos.close();
			*/
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			 try{              
				 workbook.write(baos);          
			 }catch(IOException e){
				 logger.error("执行createExcel错误!", e);
			 }          
			 byte[] ba = baos.toByteArray();          
			 ByteArrayInputStream bais = new ByteArrayInputStream(ba);          
			 this.setExcelFile(bais) ; 
			 
			 return "createExcel";
			
		}catch(Exception e){
			logger.error("执行createExcel错误!", e);
			e.printStackTrace();
			
			return "error";
		}
	}

	
	/**
	 * 下载EXCEL表格 .zip 包
	 * @return String
	 * fanxin
	 */
	public String donloadExcel(){
		try{
			//删除之前的压缩文件
			File zipfile1 = new File(dir +"/"+"watchRecod"+".rar");
			zipfile1.delete();
			
			//定义表头信息
			String[] headName={"用户Id","用户注册邮箱","所属商品(售卖方式)","课程","视频节点ID","视频名称","任课教师","开始观看时间","结束观看时间","观看时长（单位/分钟）","所属项目"};
			
			getReCondtion().setPageSize(100000); //设置查询数量
			
			int subId = reCondtion.getSubjectId();
			Date sTime = reCondtion.getStart();
			Date enTime = reCondtion.getEnd();
			if(subId==0 && sTime == null && enTime==null){
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				Date today = new Date();
				Date tomor = new Date(today.getTime() +  1000 * 60 * 60 * 24);  
				reCondtion.setStart(f.parse(f.format(today)));
				reCondtion.setEnd(f.parse(f.format(tomor)));
			}
			
			List<VideoStatisticsRecord> listVSR = iVideoStaticsticsService.getVideoStatisticsRecordList(reCondtion);
			//格式化时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);			
			HSSFCell cell = row.createCell((short) 0);
			
			for(int i=0;i<headName.length;i++){
				cell = row.createCell((short) i);  
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(headName[i]);
			}
			
			for (int i = 0,j = 1;listVSR!=null && i < listVSR.size(); i++,j++) {
				row = sheet.createRow(j);
				//用户Id
				cell = row.createCell((short) 0);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getCusId());
				//电子邮箱
				cell = row.createCell((short) 1);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getEmail());
				//所属商品(售卖方式)
				cell = row.createCell((short) 2);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getSellWayTitle());
				//课程 	
				cell = row.createCell((short) 3);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getCourseName());
				//视频节点ID 	
				cell = row.createCell((short) 4);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getVideoPointId());
				//视频名称 
				cell = row.createCell((short) 5);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getVideoName());
				//任课教师 	 
				cell = row.createCell((short) 6);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getTeacher());
				try{
					//开始观看时间 	
					cell = row.createCell((short) 7);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
					cell.setCellValue(sdf.format(listVSR.get(i).getStartTime()));
					//结束观看时间
					cell = row.createCell((short) 8);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
					cell.setCellValue(sdf.format(listVSR.get(i).getEndTime()));
				}catch(Exception e){
					e.printStackTrace();
				}
				//观看时长（单位/分钟）
				cell = row.createCell((short) 9);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getWatchAlltime());
				//所属项目（售卖方式所属的专业）
				cell = row.createCell((short) 10);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16); 
				cell.setCellValue(listVSR.get(i).getSubjectName());
				
			}
			//创建要下载的excel文件
			File file = new File(dir +"/"+ fileName);
			FileOutputStream fos = new FileOutputStream(file); 
			workbook.write(fos); 
			
			List<File> srcfile = new ArrayList<File>();
			srcfile.add(file);
			fos.close();
			//把要下载的excel文件打包成压缩文件
			File zipfile = new File(dir+"/"+"watchRecod"+".rar");
			CrmChanceAction.class.newInstance().zipFiles(srcfile, zipfile);
			//删除之前的生成的excel文件
			file.delete();
			
			
			this.setResult(new Result(true,null, null, null));
			return "json";
		}catch(Exception e){
			logger.error("执行donloadExcel错误!", e);
			return "error";
		}
	}
	
	
	private String dir = ServletActionContext.getServletContext().getRealPath( "/excelfile/");
	//定义下载时的文件名
	private String fileName = "用户观看视屏记录_" + DateUtil.getCurrentDate() + ".xls";
	
	private InputStream excelFile; 
	
	public String getFileName() {
	    try {
	    	//中文文件名也是需要转码为 ISO8859-1，否则乱码    
			return new String(fileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "ERROR";
		}      
	}
	
	/**
	 * 观看用户视频信息，以项目区分
	 * @author liuqinggang
	 * @return String
	 */
	public QuerySubjectCondition  subjectcondition;
	 List<VideoWatchLog> videoWatchLogslist;
	public String  getVideoWatchLogList() {
		if (getSubjectcondition().getStartTime()==null && getSubjectcondition().getEndTime()==null){
			SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
			String today=dateFm.format(new Date());
			getSubjectcondition().setStartTime(today+" 00:00:00");
			getSubjectcondition().setEndTime(today+" 23:59:59");
		}
		videoWatchLogslist = iVideoStaticsticsService.getVideoWatchLogList(getSubjectcondition());
		subList = subjectService.getAllSubject();
		return "vsuserCount";
	}
	
	/**
	 * 观看视频excel导出
	 * @param fileName
	 */
	
	public String doWatchListExcel(){
		try {
			if (getSubjectcondition().getStartTime()==null && getSubjectcondition().getEndTime()==null){
				SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
				String today=dateFm.format(new Date());
				getSubjectcondition().setStartTime(today+" 00:00:00");
				getSubjectcondition().setEndTime(today+" 23:59:59");
			}
			videoWatchLogslist = iVideoStaticsticsService.getVideoWatchLogList(getSubjectcondition());
			if(videoWatchLogslist==null){
				return "createExcel";
			}
			//定义表头信息
			String[] headName={"项目名称","登录的有效购买用户数","登录看视频的有效购买用户数","看收费视频的总时长","截至到目前有效购买用户的总数","无购买课程登录的用户总数","无购买课程登录看试听课程的用户总数","登录看试听课程的总时长","平均观看收费视频的时长","平均观看试听课程的时长","观看收费视频的比例","观看试听课程的比例"};
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 创建表头
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			int j=1;
			for(VideoWatchLog videoWatchLog :videoWatchLogslist){
				row = sheet.createRow(j++);
				int i=0;
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getSubjectName());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getBuylogincount());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getWatchusercount());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getBuywatchtime());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getBuyallcount());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getNobuylogincount());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getShitingusercount());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getShitingwatchtime());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getAve_buywatchtime().toString());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getAve_shitingwatchtime().toString());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getScale_buy().toString());
				cell = row.createCell((short) i++);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(videoWatchLog.getScale_shiting().toString());
				
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.setFileName("视频观看统计(用户).xls");
		return "createExcel";
	}
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public void setiVideoStaticsticsService(
			IVideoStaticstics iVideoStaticsticsService) {
		this.iVideoStaticsticsService = iVideoStaticsticsService;
	}

	public List<VideoStatisticsCountDTO> getVstList() {
		return vstList;
	}

	public void setVstList(List<VideoStatisticsCountDTO> vstList) {
		this.vstList = vstList;
	}

	public List<VideoStatisticsSingleDTO> getVstSingleList() {
		return vstSingleList;
	}

	public void setVstSingleList(List<VideoStatisticsSingleDTO> vstSingleList) {
		this.vstSingleList = vstSingleList;
	}

	public VideoStatisticsCountDTO getVstCondition() {
		return vstCondition;
	}

	public void setVstCondition(VideoStatisticsCountDTO vstCondition) {
		this.vstCondition = vstCondition;
	}

	public List<Subject> getSubList() {
		return subList;
	}

	public void setSubList(List<Subject> subList) {
		this.subList = subList;
	}
	
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public int getSubjectId() {
		if(subjectId == 0){
			subjectId = DEFALUT_SUBJECT_ID;
		}
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public List<VideoStatisticsRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<VideoStatisticsRecord> recordList) {
		this.recordList = recordList;
	}
	
	public VideoStatisticsCondition getReCondtion() {
		if(reCondtion == null){
			reCondtion = new VideoStatisticsCondition();
			reCondtion.setPageSize(30);
		}
		return reCondtion;
	}

	public void setReCondtion(VideoStatisticsCondition reCondtion) {
		this.reCondtion = reCondtion;
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	public QuerySubjectCondition getSubjectcondition() {
		if(subjectcondition==null){
			subjectcondition = new QuerySubjectCondition();
		}
		return subjectcondition;
	}

	public void setSubjectcondition(QuerySubjectCondition subjectcondition) {
		this.subjectcondition = subjectcondition;
	}

	public List<VideoWatchLog> getVideoWatchLogslist() {
		return videoWatchLogslist;
	}

	public void setVideoWatchLogslist(List<VideoWatchLog> videoWatchLogslist) {
		this.videoWatchLogslist = videoWatchLogslist;
	}
	


}
