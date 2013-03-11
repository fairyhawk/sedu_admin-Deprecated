package com.shangde.edu.feed.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.GetRequsetResponseUtil;
import com.shangde.edu.feed.condition.QueryUserStatisticsCondition;
import com.shangde.edu.feed.domain.Course;
import com.shangde.edu.feed.dto.UserStatisticsDTO;
import com.shangde.edu.feed.service.ICourse;
import com.shangde.edu.feed.service.IUserStatistics;
import com.shangde.edu.sys.service.ISubject;

public class UserStatisticsAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	/*** 专业接口 */
	private ISubject subjectService;

	private ICourse feedCourseService;
	
	private IUserStatistics userStatisticsService;

	private QueryUserStatisticsCondition queryUserStatisticsCondition = new QueryUserStatisticsCondition();
	
	private List<Course> courseList = new ArrayList<Course>();
	private InputStream excelFileName;
	
	public InputStream getExcelFileName() {
		return excelFileName;
	}
	public void setExcelFileName(InputStream excelFileName) {
		this.excelFileName = excelFileName;
	}
	/**
	 * 去用户统计页面
	 * @return
	 */
	public String goUserStatisticsPage(){
		courseList = feedCourseService.getAllCourse();
		if(queryUserStatisticsCondition.getCourseId() != null){
			if(-1 == queryUserStatisticsCondition.getCourseId()){
				queryUserStatisticsCondition.setCourseId(null);
			}
		}
		
		setPage(userStatisticsService.getUserStatisticslist(queryUserStatisticsCondition));
		setPageUrlParms();
		queryUserStatisticsCondition.setPageSize(100000);
		setSession("userStatistics", userStatisticsService.getUserStatisticslist(queryUserStatisticsCondition).getPageResult());
		return "userStatisticsListPage";
	}
	/**
	 * 去用户操作详情页
	 * @return
	 */
	public String goUserUseInfoPage(){
		String what = GetRequsetResponseUtil.getRequest().getParameter("isWhat");
		if("1".equals(what)){
			setPage(userStatisticsService.getWatchCourseList(queryUserStatisticsCondition));
		}else if("2".equals(what)){
			setPage(userStatisticsService.getWatchVideoList(queryUserStatisticsCondition));
		}else if("3".equals(what)){
			setPage(userStatisticsService.getDownloadVideoList(queryUserStatisticsCondition));
		}else if("4".equals(what)){
			setPage(userStatisticsService.getCollectionVideoList(queryUserStatisticsCondition));
		}
		setPageUrlParms();
		if("1".equals(what)){
			return "watchCoursePage";
		}else{
			return "videoOptionPage";
		}
	}
	
	public String promtExcel(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<UserStatisticsDTO> userStisticsList = new ArrayList<UserStatisticsDTO>();
		try{
			userStisticsList = getSession("userStatistics");
			//size = courseStaticList.size();
		}catch (Exception e) {
		}
		try {
			// 创建新的Excel 工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 在Excel 工作簿中建一工作表
			HSSFSheet sheet = workbook.createSheet("Sheet1");
			// 设置单元格格式(文本)
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
			

			// 在索引0的位置创建行（第一行）
			HSSFRow row = sheet.createRow((short) 0);
			HSSFCell cell1 = row.createCell((short) 0);// 第一列
			HSSFCell cell2 = row.createCell((short) 1);
			HSSFCell cell3 = row.createCell((short) 2);
			HSSFCell cell4 = row.createCell((short) 3);// 第一列
			HSSFCell cell5 = row.createCell((short) 4);
			HSSFCell cell6 = row.createCell((short) 5);
			HSSFCell cell7 = row.createCell((short) 6);// 第一列
			HSSFCell cell8 = row.createCell((short) 7);
			HSSFCell cell9 = row.createCell((short) 8);
			HSSFCell cell10 = row.createCell((short) 9);// 第一列
			HSSFCell cell11 = row.createCell((short) 10);
			HSSFCell cell12 = row.createCell((short) 11);

			
			sheet.setColumnWidth((short)0, (short)9000);
			sheet.setColumnWidth((short)1, (short)6000);
			sheet.setColumnWidth((short)2, (short)6000);
			sheet.setColumnWidth((short)3, (short)9000);
			sheet.setColumnWidth((short)4, (short)6000);
			sheet.setColumnWidth((short)5, (short)6000);
			sheet.setColumnWidth((short)6, (short)9000);
			sheet.setColumnWidth((short)7, (short)6000);
			sheet.setColumnWidth((short)8, (short)6000);
			sheet.setColumnWidth((short)9, (short)9000);
			sheet.setColumnWidth((short)10, (short)6000);
			sheet.setColumnWidth((short)11, (short)6000);
			
			cell1.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell3.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell4.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell5.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell6.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell7.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell8.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell9.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell10.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell11.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell12.setCellType(HSSFCell.CELL_TYPE_STRING);
			
			cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell3.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell4.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell5.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell6.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell7.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell8.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell9.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell10.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell11.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell12.setEncoding(HSSFCell.ENCODING_UTF_16);
			
			cell1.setCellValue("用户id");
			cell2.setCellValue("用户名");
			cell3.setCellValue("注册专业");
			cell4.setCellValue("注册位置");
			cell5.setCellValue("注册时间");
			cell6.setCellValue("到达微学习次数");
			cell7.setCellValue("观看课程次数");
			cell8.setCellValue("观看视频数");
			cell9.setCellValue("总观看时间");
			cell10.setCellValue("下载视频数");
			cell11.setCellValue("收藏视频数");
			cell12.setCellValue("提问数");
			
			int j = 1;
			for (int i = 0; i < userStisticsList.size(); i++) {
				UserStatisticsDTO userStatistics = userStisticsList.get(i);
				row = sheet.createRow((short) j);
				for(int x=1;x<=12;x++){//设置列数
					 HSSFCell cell = row.createCell((short) (0)); // 设置单元格格式 
					 HSSFCell cell_1 = row.createCell((short) (1)); // 设置单元格格式 
					 HSSFCell cell_2 = row.createCell((short) (2)); // 设置单元格格式 
					 HSSFCell cell_3 = row.createCell((short) (3)); // 设置单元格格式 
					 HSSFCell cell_4 = row.createCell((short) (4)); // 设置单元格格式 
					 HSSFCell cell_5 = row.createCell((short) (5)); // 设置单元格格式 
					 HSSFCell cell_6 = row.createCell((short) (6)); // 设置单元格格式 
					 HSSFCell cell_7 = row.createCell((short) (7)); // 设置单元格格式 
					 HSSFCell cell_8 = row.createCell((short) (8)); // 设置单元格格式 
					 HSSFCell cell_9 = row.createCell((short) (9)); // 设置单元格格式 
					 HSSFCell cell_10 = row.createCell((short) (10)); // 设置单元格格式 
					 HSSFCell cell_11 = row.createCell((short) (11)); // 设置单元格格式 
					 cell.setCellStyle(cellStyle);
					 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell.setCellValue(userStatistics.getId());
					 
					 cell_1.setCellStyle(cellStyle);
					 cell_1.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_1.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_1.setCellValue(userStatistics.getUserName());
					 
					 cell_2.setCellStyle(cellStyle);
					 cell_2.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_2.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_2.setCellValue(userStatistics.getSubjectName());
					 
					 cell_3.setCellStyle(cellStyle);
					 cell_3.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_3.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_3.setCellValue(userStatistics.getRegFrom());
					 
					 cell_4.setCellStyle(cellStyle);
					 cell_4.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_4.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_4.setCellValue(sdf.format(userStatistics.getRegTime()));
					 
					 cell_5.setCellStyle(cellStyle);
					 cell_5.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_5.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_5.setCellValue(userStatistics.getComeFeedNum());
					
					 cell_6.setCellStyle(cellStyle);
					 cell_6.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_6.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_6.setCellValue(userStatistics.getWatchCourseNum());
					 
					 cell_7.setCellStyle(cellStyle);
					 cell_7.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_7.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_7.setCellValue(userStatistics.getWatchVideoNum());
					 
					 cell_8.setCellStyle(cellStyle);
					 cell_8.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_8.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_8.setCellValue(userStatistics.getWatchAllTime());
					 
					 cell_9.setCellStyle(cellStyle);
					 cell_9.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_9.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_9.setCellValue(userStatistics.getDownloadVideoNum());
					 
					 cell_10.setCellStyle(cellStyle);
					 cell_10.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_10.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_10.setCellValue(userStatistics.getCollectionVideoNum());
					 
					 cell_11.setCellStyle(cellStyle);
					 cell_11.setCellType(HSSFCell.CELL_TYPE_STRING);
					 cell_11.setEncoding(HSSFCell.ENCODING_UTF_16);
					 cell_11.setCellValue(userStatistics.getQuestionNum());
					 
				}
			    j++;	
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFileName(bais);
		} catch (Exception e) {
				
		}
		return "exportExcelUser";
	}
	
	
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}



	public ICourse getFeedCourseService() {
		return feedCourseService;
	}

	public void setFeedCourseService(ICourse feedCourseService) {
		this.feedCourseService = feedCourseService;
	}

	public IUserStatistics getUserStatisticsService() {
		return userStatisticsService;
	}

	public void setUserStatisticsService(IUserStatistics userStatisticsService) {
		this.userStatisticsService = userStatisticsService;
	}

	public QueryUserStatisticsCondition getQueryUserStatisticsCondition() {
		if(queryUserStatisticsCondition == null) {
			queryUserStatisticsCondition = new QueryUserStatisticsCondition();
		}
		return queryUserStatisticsCondition;
	}

	public void setQueryUserStatisticsCondition(
			QueryUserStatisticsCondition queryUserStatisticsCondition) {
		this.queryUserStatisticsCondition = queryUserStatisticsCondition;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

}
