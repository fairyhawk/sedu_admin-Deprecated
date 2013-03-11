package com.shangde.edu.feed.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.feed.condition.QueryUserStatCondition;
import com.shangde.edu.feed.dto.UserStatDTO;
import com.shangde.edu.feed.service.IUserStat;
import com.shangde.edu.feed.utils.Utils;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 用户统计
 * 
 * @author Libg
 * @author ...
 * 
 */
public class UserStatAction extends CommonAction {
	private static Logger logger = LoggerFactory.getLogger(UserStatAction.class);
	/*** 统计接口*/
	private IUserStat userStatService;
	/*** 专业接口*/
	private ISubject subjectService;
	/*** 分页查询条件*/
	private QueryUserStatCondition queryUserStatCondition;
	/** 集合对象 */
	private List<Subject> subjectList;// 专业列表
	private String downFileURL = "/back/jsp/feed/export/userstat.csv";//下载文件地址
	private String msg;//提示信息[成功/失败]等信息提示
	/**
	 * 统计列表		分页
	 * creator longjl
	 * @return
	 */
	public String userStatlist(){
		try {
			//显示的数量(行数)
			int pageSize = 100;
			this.getQueryUserStatCondition().setPageSize(pageSize);
			setPage(this.getUserStatService().getUserStatList(queryUserStatCondition));			
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
			subjectList = subjectService.getAllSubject();// 专业列表
			
			//导出所有的用户统计信息
			List<UserStatDTO> userStatDaoList = this.getUserStatService().exportUserStatistics();
			//取出当前页数据保存到session中,为导出功能提供数据
			if(userStatDaoList != null && userStatDaoList.size()>0){
				//setSession("userStatDataList", getPage().getPageResult());
				setSession("userStatDataList", userStatDaoList);
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "userstat_list";
	}
	/**
	 * 统计列表     条件分页
	 * creator longjl
	 * @return
	 */
	public String userStatListBySearch(){
		try {
			//课程编号
			if(queryUserStatCondition.getSubjectId() == null || queryUserStatCondition.getSubjectId().intValue() == 0){
				queryUserStatCondition.setSubjectId(null);
			}
			//开始时间
			if(queryUserStatCondition.getStartTime() == null || queryUserStatCondition.getStartTime().equals("")){
				queryUserStatCondition.setStartTime(null);
			}
			//结束时间
			if(queryUserStatCondition.getEndTime() == null || queryUserStatCondition.getEndTime().equals("")){
				queryUserStatCondition.setEndTime(null);
			}
			
			int pageSize = 100;
			this.getQueryUserStatCondition().setPageSize(pageSize);
			setPage(this.getUserStatService().getUserStatListBYSreach(queryUserStatCondition));			
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(pageSize);
			}
			setPageUrlParms();
			subjectList = subjectService.getAllSubject();// 专业列表
			//导出 条件查询用户统计的信息
			List<UserStatDTO> userStatDaoList = this.userStatService.exportSearchUserStatList(queryUserStatCondition);
			if(userStatDaoList != null && userStatDaoList.size()>0){
				//取出当前页数据保存到session中,为导出功能提供数据
				setSession("userStatDataList", userStatDaoList);
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "userstat_list";
	}
	
	/**
	 * 导出数据
	 * 下载导出的数据(任务详情统计数据)
	 * @author longjl
	 * @return
	 */
	public String downStatisticsData(){
		File file = null;
		String fileURL = null;
		try{
			boolean bool = exportStatisticsPageData();
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
	 * @return
	 */
	public boolean exportStatisticsPageData(){
		boolean bool = false;
		int count = 0;
		int size = 0;
		String fileURL = null;//文件路径
		String[] title = null;
		String[][] body = null;//
		
		List<UserStatDTO> userStatDTOList = null;
		try{
			userStatDTOList = getSession("userStatDataList");
			size = userStatDTOList.size();
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
		title = new String[] { "ID","用户邮箱","专业 ","注册时间 ","注册位置 ","使用次数","未观看视频数","观看时长 ","最近使用时间"};
		body = new String[size][title.length];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(UserStatDTO ud : userStatDTOList){
			
			body[count][0] = String.valueOf(ud.getId());//ID
			if(ud.getEmail() != null && ud.getEmail().length()>0 && !ud.getEmail().equals("null")){
				body[count][1] = String.valueOf(ud.getEmail());//用户邮箱
			}else{
				body[count][1] = "";
			}
			
			if(ud.getSubject().getSubjectName() != null && ud.getSubject().getSubjectName().length()>0 && !ud.getSubject().getSubjectName().equals("null")){
				body[count][2] = String.valueOf(ud.getSubject().getSubjectName());//专业
			}else{
				body[count][2] = "";
			}
			
			if(ud.getRegTime() != null){
				body[count][3] = sdf.format(ud.getRegTime());//注册时间
			}else{
				body[count][3] = "";
			}
			
			if(ud.getRegLocation() != null && !ud.getRegLocation().equals("null")){
				if(ud.getRegLocation() == 1){
					body[count][4] = "微学习";//注册位置
				}else{
					body[count][4] = "其他";//注册位置
				}
			}else{
				body[count][4] = "其他";
			}
			
			if(ud.getUseClickNum() != null && ud.getUseNum() != null && !ud.getUseClickNum().equals("null") && !ud.getUseNum().equals("null")){
				body[count][5] =  String.valueOf(ud.getUseNum()+ud.getUseClickNum());//使用次数
			}else if(ud.getUseClickNum() != null && !ud.getUseClickNum().equals("null")){
				body[count][5] =  String.valueOf(ud.getUseClickNum());//使用次数
			}else if(ud.getUseNum() != null && !ud.getUseNum().equals("null")){
				body[count][5] =  String.valueOf(ud.getUseNum());//使用次数
			}
			
			body[count][6] = String.valueOf(ud.getNotWatchNum());//未观看视频数
			
			if(ud.getTotalLength() != null && !ud.getTotalLength().equals("null")){
				body[count][7] = String.valueOf(ud.getTotalLength());//观看时长
			}else{
				body[count][7] = "";
			}
			
			if(ud.getModified() != null && !ud.getModified().equals("null")){
				body[count][8] = sdf.format(ud.getModified());
			}else{
				body[count][8] = "";
			}
			
			count++;
		}
		try {
			//创建文件
			Utils.writeCSV(title, body, fileURL);
			bool = true;
		} catch (Exception e) {
			logger.error("微学习-导出数据-写文件错误->" ,e);
		}
		return bool;
	}
	
	public QueryUserStatCondition getQueryUserStatCondition() {
		return queryUserStatCondition;
	}
	public void setQueryUserStatCondition(
			QueryUserStatCondition queryUserStatCondition) {
		this.queryUserStatCondition = queryUserStatCondition;
	}
	public IUserStat getUserStatService() {
		return userStatService;
	}
	public void setUserStatService(IUserStat userStatService) {
		this.userStatService = userStatService;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public ISubject getSubjectService() {
		return subjectService;
	}
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	public String getDownFileURL() {
		return downFileURL;
	}
	public void setDownFileURL(String downFileURL) {
		this.downFileURL = downFileURL;
	}

}
