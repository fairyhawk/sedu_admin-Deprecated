package com.shangde.edu.feed.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.edu.feed.condition.QueryEntranceCondition;
import com.shangde.edu.feed.condition.QueryStudentStatCondition;
import com.shangde.edu.feed.condition.QueryUserStatLogCondition;
import com.shangde.edu.feed.condition.QueryUserUseCondition;
import com.shangde.edu.feed.domain.From;
import com.shangde.edu.feed.domain.StudentStat;
import com.shangde.edu.feed.dto.StudentStatDTO;
import com.shangde.edu.feed.service.IFrom;
import com.shangde.edu.feed.service.IStudentStat;
import com.shangde.edu.feed.utils.Utils;

public class StudentStatAction  extends CommonAction{
	private static Logger logger = LoggerFactory.getLogger(StudentStatAction.class);
	
	/*** 接口*/
	private IStudentStat studentStatService;
	private IFrom fromService;
	/*** 微学习 学员统计表*/
	private StudentStat studentStat;
	/*** 学员统计列表*/
	private List<StudentStat> studentStatList;
	/*** 来源*/
	List<From> listForm;
	/*** 导出数据路径*/
	private String downFileURL = "/back/jsp/feed/export/weixuexi.csv";//下载文件地址
	private String msg;//提示信息[成功/失败]等信息提示
	private String fromIds;//来源id
	private String jsonResult;//json返回值
	
	
	/**
	 * 查詢條件
	 * 微学习 注册人数统计  
	 * 微学习 登录人数统计 
	 * return  queryUserUseCondition
	 */
	private QueryUserUseCondition queryUserUseCondition;
	/**
	 * 查詢條件
	 * 页面流量统计
	 */
	private QueryEntranceCondition queryEntranceCondition;
	/**
	 * 首次用户 非重复用户   重复用户 条件
	 */
	private QueryUserStatLogCondition queryUserStatLogCondition;
	/**
	 * 时间条件查询
	 */
	private QueryStudentStatCondition queryStudentStatCondition;
	
	/**
	 * 微学习 学员统计
	 * @author longjl
	 */
	public void queryStudentStat(){
		System.out.println("-------------------------------------");
		try {
			//昨天的时间
			String searchDate = DateUtil.addDays2(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), -1);
			
			//String searchDate = getServletRequest().getParameter("date");
			//来源 Integer[] froms = {1,2,3,4};
			Integer[] froms = queryFroms();
			if(froms == null || froms.length==0){
				return;
			}
			
			//初始化
			queryEntranceCondition = new QueryEntranceCondition();
			//初始化
			queryUserUseCondition = new QueryUserUseCondition();
			//初始化
			queryUserStatLogCondition = getQueryUserStatLogCondition();
			
			//注册/登录统计条件 2
			List<StudentStatDTO> registerList2 = new ArrayList<StudentStatDTO>();
			//注册/登录统计条件 4
			List<StudentStatDTO> LoginList4 = new ArrayList<StudentStatDTO>();
			//注册 登录的总人数
			List<StudentStatDTO> regLoginList24 = new ArrayList<StudentStatDTO>();
			
			for(Integer fs : froms ){//循环来源
					//页面流量统计条件
					queryEntranceCondition.setFromId(fs);
					queryEntranceCondition.setPubDate(searchDate);
					int pageFlowCount = studentStatService.getPageFlowCount(queryEntranceCondition);
					
					//注册/登录统计条件
					queryUserUseCondition.setFromId(fs);
					queryUserUseCondition.setPubDate(searchDate);
					regLoginList24 =  studentStatService.getStudentUseCount(queryUserUseCondition);//注册 登录的总人数
					registerList2 = studentStatService.queryStudentUseRegister(queryUserUseCondition);//注册人数
					LoginList4 = studentStatService.queryStudentUseLogin(queryUserUseCondition);//登录人数
					
				
					studentStat = new StudentStat();
					studentStat.setFromId(fs);  //来源id  
					studentStat.setPageFlow(pageFlowCount);//页流量
					if(registerList2 != null && registerList2.size()>0){
						//registerCount 赋值
						studentStat.setRegisterCount(registerList2.size());//注册人数
						
						String registerIds = "";
						for(StudentStatDTO so : registerList2){
							 	registerIds += so.getCusId()+",";
						}
						//registerids 赋值
						studentStat.setRegisterIds(registerIds);//注册用户id
					}else{
						studentStat.setRegisterCount(0);
						studentStat.setRegisterIds("");
					}
					
					if(LoginList4 != null && LoginList4.size()>0){
						studentStat.setLoginCount(LoginList4.size());//登录人数
						String LoginIds = "";
						for(StudentStatDTO so : LoginList4){
								LoginIds += so.getCusId()+",";
						}
						studentStat.setLoginIds(LoginIds);
					}else{
						studentStat.setLoginCount(0);
						studentStat.setLoginIds("");
					}
				
					//订单用户
					String orderCusIds = "";
				
					//跳出人数
					int outCount = pageFlowCount - (regLoginList24.size());
					studentStat.setOutCount(outCount);
					
					//首次用户 非重复用户   重复用户 条件
					queryUserStatLogCondition.setFromId(fs);
					queryUserStatLogCondition.setPubDate(searchDate);
					
					//到达微学习页的数量
					if(regLoginList24 != null && regLoginList24.size()>0){
						studentStat.setArriveWxxCount(regLoginList24.size());
						orderCusIds = getOrderCusIds(regLoginList24);
						studentStat.setArriveWxxIds(orderCusIds);
					}else{
						studentStat.setArriveWxxCount(0);
						orderCusIds = getOrderCusIds(regLoginList24);
						studentStat.setArriveWxxIds(orderCusIds);
					}
					
					Map<String,Object> cusIdsMap = new HashMap<String, Object>();
					if(regLoginList24 != null && regLoginList24.size()>0){
						cusIdsMap.put("idsMap", regLoginList24);
					}
					cusIdsMap.put("fromIds", fs);
					cusIdsMap.put("pubDate",searchDate);
					//首次用户数
					List<StudentStatDTO> firstUserCusIdList= studentStatService.getFirstUserCount(cusIdsMap);
					//非重复用户 数
					List<StudentStatDTO> nonRepeatUserCusIdList = studentStatService.getNonRepeatUserCount(cusIdsMap);
					//重复用户数
					List<StudentStatDTO> repeatUserCusIdList = studentStatService.getRepeatUserCount(cusIdsMap);
					
					//首次用户
					studentStat.setFirstUserCount(firstUserCusIdList.size());
					if(firstUserCusIdList != null && firstUserCusIdList.size()>0){
						String firstUserIds="";
						for(StudentStatDTO so : firstUserCusIdList){
							firstUserIds += so.getCusId() +",";
						}
						studentStat.setFirstUserIds(firstUserIds);
					}else{
						studentStat.setFirstUserIds("");
					}
					//不重复用户
					studentStat.setNonRepeatUserCount(nonRepeatUserCusIdList.size());
					if(nonRepeatUserCusIdList != null && nonRepeatUserCusIdList.size()>0){
						String nonRepeatUserIds= "";
						for(StudentStatDTO so : nonRepeatUserCusIdList){
							nonRepeatUserIds += so.getCusId() +",";
						}
						studentStat.setNonRepeatUserIds(nonRepeatUserIds);
					}else{
						studentStat.setNonRepeatUserIds("");
					}
					//重复用户
					studentStat.setRepeatUserCount(repeatUserCusIdList.size());
					if(repeatUserCusIdList != null && repeatUserCusIdList.size()>0){
						String repeatUserIds="";
						for(StudentStatDTO so : repeatUserCusIdList){
							repeatUserIds += so.getCusId() +",";
						}
						studentStat.setRepeatUserIds(repeatUserIds);
					}else{
						studentStat.setRepeatUserIds("");
					}
					
					//订单数量
					int orderCount = studentStatService.queryOrderCount(cusIdsMap);
					studentStat.setOrderCount(orderCount);
					studentStat.setOrderIds(orderCusIds);
					
					if(cusIdsMap != null && !cusIdsMap.isEmpty()){
						//订单流水
						String money = studentStatService.getBringWater(cusIdsMap);
						if(money != null && money.length()>0){
							studentStat.setRunningWaterCount(Double.parseDouble(money));//钱
						}else{
							studentStat.setRunningWaterCount(0.0);//钱
						}
					}else{
						studentStat.setRunningWaterCount(0.0);//钱
					}
					studentStat.setSearchDate(searchDate);//查询日期，表示，这条记录查询的时间点
					studentStat.setCreateTime(new Date());
					
					//插入微学习 学员统计表
					studentStatService.addStudentStat(studentStat);
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
	}
	
	/**
	 * 微学习 学员统计,测试专用
	 * @author longjl
	 */
	public void queryStudentStat_test(){
		try {
			//昨天的时间
			//String searchDate = DateUtil.addDays2(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), -1);
			
			String searchDate = getServletRequest().getParameter("date");
			//来源 Integer[] froms = {1,2,3,4};
			Integer[] froms = queryFroms();
			if(froms == null || froms.length==0){
				return;
			}
			
			//初始化
			queryEntranceCondition = new QueryEntranceCondition();
			//初始化
			queryUserUseCondition = new QueryUserUseCondition();
			//初始化
			queryUserStatLogCondition = getQueryUserStatLogCondition();
			
			//注册/登录统计条件 2
			List<StudentStatDTO> registerList2 = new ArrayList<StudentStatDTO>();
			//注册/登录统计条件 4
			List<StudentStatDTO> LoginList4 = new ArrayList<StudentStatDTO>();
			//注册 登录的总人数
			List<StudentStatDTO> regLoginList24 = new ArrayList<StudentStatDTO>();
			
			for(Integer fs : froms ){//循环来源
					//页面流量统计条件
					queryEntranceCondition.setFromId(fs);
					queryEntranceCondition.setPubDate(searchDate);
					int pageFlowCount = studentStatService.getPageFlowCount(queryEntranceCondition);
					
					//注册/登录统计条件
					queryUserUseCondition.setFromId(fs);
					queryUserUseCondition.setPubDate(searchDate);
					regLoginList24 =  studentStatService.getStudentUseCount(queryUserUseCondition);//注册 登录的总人数
					registerList2 = studentStatService.queryStudentUseRegister(queryUserUseCondition);//注册人数
					LoginList4 = studentStatService.queryStudentUseLogin(queryUserUseCondition);//登录人数
					
				
					studentStat = new StudentStat();
					studentStat.setFromId(fs);  //来源id  
					studentStat.setPageFlow(pageFlowCount);//页流量
					if(registerList2 != null && registerList2.size()>0){
						//registerCount 赋值
						studentStat.setRegisterCount(registerList2.size());//注册人数
						
						String registerIds = "";
						for(StudentStatDTO so : registerList2){
							 	registerIds += so.getCusId()+",";
						}
						//registerids 赋值
						studentStat.setRegisterIds(registerIds);//注册用户id
					}else{
						studentStat.setRegisterCount(0);
						studentStat.setRegisterIds("");
					}
					
					if(LoginList4 != null && LoginList4.size()>0){
						studentStat.setLoginCount(LoginList4.size());//登录人数
						String LoginIds = "";
						for(StudentStatDTO so : LoginList4){
								LoginIds += so.getCusId()+",";
						}
						studentStat.setLoginIds(LoginIds);
					}else{
						studentStat.setLoginCount(0);
						studentStat.setLoginIds("");
					}
				
					//订单用户
					String orderCusIds = "";
				
					//跳出人数
					int outCount = pageFlowCount - (regLoginList24.size());
					studentStat.setOutCount(outCount);
					
					//首次用户 非重复用户   重复用户 条件
					queryUserStatLogCondition.setFromId(fs);
					queryUserStatLogCondition.setPubDate(searchDate);
					
					//到达微学习页的数量
					if(regLoginList24 != null && regLoginList24.size()>0){
						studentStat.setArriveWxxCount(regLoginList24.size());
						orderCusIds = getOrderCusIds(regLoginList24);
						studentStat.setArriveWxxIds(orderCusIds);
					}else{
						studentStat.setArriveWxxCount(0);
						orderCusIds = getOrderCusIds(regLoginList24);
						studentStat.setArriveWxxIds(orderCusIds);
					}
					
					Map<String,Object> cusIdsMap = new HashMap<String, Object>();
					if(regLoginList24 != null && regLoginList24.size()>0){
						cusIdsMap.put("idsMap", regLoginList24);
					}
					cusIdsMap.put("fromIds", fs);
					cusIdsMap.put("pubDate",searchDate);
					//首次用户数
					List<StudentStatDTO> firstUserCusIdList= studentStatService.getFirstUserCount(cusIdsMap);
					//非重复用户 数
					List<StudentStatDTO> nonRepeatUserCusIdList = studentStatService.getNonRepeatUserCount(cusIdsMap);
					//重复用户数
					List<StudentStatDTO> repeatUserCusIdList = studentStatService.getRepeatUserCount(cusIdsMap);
					
					//首次用户
					studentStat.setFirstUserCount(firstUserCusIdList.size());
					if(firstUserCusIdList != null && firstUserCusIdList.size()>0){
						String firstUserIds="";
						for(StudentStatDTO so : firstUserCusIdList){
							firstUserIds += so.getCusId() +",";
						}
						studentStat.setFirstUserIds(firstUserIds);
					}else{
						studentStat.setFirstUserIds("");
					}
					//不重复用户
					studentStat.setNonRepeatUserCount(nonRepeatUserCusIdList.size());
					if(nonRepeatUserCusIdList != null && nonRepeatUserCusIdList.size()>0){
						String nonRepeatUserIds= "";
						for(StudentStatDTO so : nonRepeatUserCusIdList){
							nonRepeatUserIds += so.getCusId() +",";
						}
						studentStat.setNonRepeatUserIds(nonRepeatUserIds);
					}else{
						studentStat.setNonRepeatUserIds("");
					}
					//重复用户
					studentStat.setRepeatUserCount(repeatUserCusIdList.size());
					if(repeatUserCusIdList != null && repeatUserCusIdList.size()>0){
						String repeatUserIds="";
						for(StudentStatDTO so : repeatUserCusIdList){
							repeatUserIds += so.getCusId() +",";
						}
						studentStat.setRepeatUserIds(repeatUserIds);
					}else{
						studentStat.setRepeatUserIds("");
					}
					
					//订单数量
					int orderCount = studentStatService.queryOrderCount(cusIdsMap);
					studentStat.setOrderCount(orderCount);
					studentStat.setOrderIds(orderCusIds);
					
					if(cusIdsMap != null && !cusIdsMap.isEmpty()){
						//订单流水
						String money = studentStatService.getBringWater(cusIdsMap);
						if(money != null && money.length()>0){
							studentStat.setRunningWaterCount(Double.parseDouble(money));//钱
						}else{
							studentStat.setRunningWaterCount(0.0);//钱
						}
					}else{
						studentStat.setRunningWaterCount(0.0);//钱
					}
					studentStat.setSearchDate(searchDate);//查询日期，表示，这条记录查询的时间点
					studentStat.setCreateTime(new Date());
					
					//插入微学习 学员统计表
					studentStatService.addStudentStat(studentStat);
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
	}
	
	/**
	 * 查询微学习 学员统计
	 * @author longjl
	 * @return 
	 */
	public String queryStudentStatList(){
		try {
			//queryStuStut();
			//加载来源id
			queryFroms();
			//昨天的时间
			String searchDate = DateUtil.addDays2(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), -1);
			studentStatList = studentStatService.queryStudentStatList(searchDate);
			//取出当前页数据保存到session中,为导出功能提供数据
			setSession("stuStatList", studentStatList);
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "student_stat";
	}
	
	/**
	 * 订单用户编号CusIds
	 */
	public String getOrderCusIds(List<StudentStatDTO> cusIds){
		String str = "";//存放拼接的用户编号(22,33,333)
		try {
			if(cusIds != null && cusIds.size()>0){
				for(int i=0;i<cusIds.size();i++){
					if(i<(cusIds.size()-1)){
						str += cusIds.get(i).getCusId()+",";
					}else{
						str += cusIds.get(i).getCusId();
					}
				}
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return str;
	}
	
	/**
	 * 查询所有的来源
	 */
	public Integer[] queryFroms(){
		try {
			listForm = fromService.queryFromList();
			if(listForm != null && listForm.size()>0){
				Integer[] fromIds = new Integer[listForm.size()];
				for(int i=0;i<listForm.size();i++){
					fromIds[i]=listForm.get(i).getId();
				}
				if(fromIds != null){
					return fromIds;
				}
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return null;
	}
	
	
    /**
     * 根据formids进行查询
     */
	public String searchStuStatByFromIds(){
		try {
				//加载来源id
				queryFroms();
			
				//来源id
				Map<String,Object> fromIdsMap = new HashMap<String,Object>();
				List<From> idsList = new ArrayList<From>();
				String[] ids = fromIds.split(",");
				if(ids != null && ids.length>0){
					for(String id : ids){
						From newFrom = new From();
						if(id != null && id.length()>0){
							newFrom.setId(new Integer(id));
							idsList.add(newFrom);
						}
					}
				}
				fromIdsMap.put("idsList", idsList);
				//时间
				if(queryStudentStatCondition.getSearchDate() != null && queryStudentStatCondition.getSearchDate().length()>0){
					fromIdsMap.put("searchDate", queryStudentStatCondition.getSearchDate());
				}
				
				studentStatList = studentStatService.searchStuStatByFromIds(fromIdsMap);
				//取出当前页数据保存到session中,为导出功能提供数据
				setSession("stuStatList", studentStatList);
		
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "student_stat";
	}
	

	
	/**
	 * 此方法已经暂停使用
	 * 按照时间查询
	 * @author longjl
	 * @return
	 */
	public String searchStudentStat(){
		try {
			//加载来源id
			queryFroms();
			if(getQueryStudentStatCondition() != null){
				if(getQueryStudentStatCondition().getSearchDate() != null && getQueryStudentStatCondition().getSearchDate().length()>0){
					studentStatList = studentStatService.searchStudentStat(getQueryStudentStatCondition().getSearchDate());
				}else{
					//昨天的时间
					String searchDate = DateUtil.addDays2(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), -1);
					studentStatList = studentStatService.searchStudentStat(searchDate);
					//取出当前页数据保存到session中,为导出功能提供数据
					setSession("stuStatList", studentStatList);
				}
			}
		} catch (Exception e) {
			logger.error("糟糕!程序错误--->",e);
		}
		return "student_stat";
	}


	
	/**
	 * 下载导出的数据
	 * 导出微学习用户统计数据
	 * @author  longjl
	 * @return
	 */
	public String downData(){
		File file = null;
		String fileURL = null;
		try{
			boolean bool = exportData();
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
	public boolean exportData(){
		boolean bool = false;
		int count = 0;
		int size = 0;
		String fileURL = null;//文件路径
		String[] title = null;
		String[][] body = null;//
		
		List<StudentStat> stuStatList = null;
		try{
			stuStatList = getSession("stuStatList");
			size = stuStatList.size();
		}catch (Exception e) {
			logger.error("获取导出数据异常",e);
		}
		
		//没有数据，返回提示信息
		if(size == 0){
			msg = "没有数据，无法导出!";
			logger.error(msg);
			//return "msg";
			return bool;
		}
		
		fileURL = getRealPath(downFileURL);//设置保存路径
		//14个大小
		title = new String[] { "来源","页流量","注册人数 ","登录人数 ","跳出人数 ","到达微学习页","首次用户","非重复用户 ","重复用户","带来订单","带来流水","统计日期"};
		body = new String[size][title.length];
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<Integer,String> map = new HashMap<Integer, String>();
		queryFroms();//查询所有来源
		if(listForm != null && listForm.size()>0){
			for(From lf : listForm) {
				map.put(lf.getId(), lf.getName());
			}
		}
		for(StudentStat ss : stuStatList){
			if(map != null && map.keySet().size()>0){//来源
				body[count][0] = map.get(ss.getFromId());
			}else{
				body[count][0] ="";
			}
			body[count][1] = String.valueOf(ss.getPageFlow());//页流量
			body[count][2] = String.valueOf(ss.getRegisterCount());//注册人数
			body[count][3] = String.valueOf(ss.getLoginCount());//登录人数
			body[count][4] = String.valueOf(ss.getOutCount());//跳出人数
			body[count][5] = String.valueOf(ss.getArriveWxxCount());//到达微学习页
			body[count][6] = String.valueOf(ss.getFirstUserCount());//首次用户
			body[count][7] = String.valueOf(ss.getNonRepeatUserCount());//非重复用户
			body[count][8] = String.valueOf(ss.getRepeatUserCount());//重复用户
			body[count][9] = String.valueOf(ss.getOrderCount());//带来订单
			body[count][10] = String.valueOf(ss.getRunningWaterCount());//带来流水
			if(ss.getSearchDate() != null && ss.getSearchDate().length()>0 && !ss.getSearchDate().equals("null")){
				body[count][11] = ss.getSearchDate();
			}else{
				body[count][11] = "";
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
	
	public QueryUserUseCondition getQueryUserUseCondition() {
		return queryUserUseCondition;
	}
	public void setQueryUserUseCondition(QueryUserUseCondition queryUserUseCondition) {
		this.queryUserUseCondition = queryUserUseCondition;
	}
	public QueryEntranceCondition getQueryEntranceCondition() {
		return queryEntranceCondition;
	}
	public void setQueryEntranceCondition(
			QueryEntranceCondition queryEntranceCondition) {
		this.queryEntranceCondition = queryEntranceCondition;
	}
	public IStudentStat getStudentStatService() {
		return studentStatService;
	}
	public void setStudentStatService(IStudentStat studentStatService) {
		this.studentStatService = studentStatService;
	}
	public QueryUserStatLogCondition getQueryUserStatLogCondition() {
		if (queryUserStatLogCondition == null)
		{
			queryUserStatLogCondition = new QueryUserStatLogCondition();
		}
		return queryUserStatLogCondition;
	}
	public void setQueryUserStatLogCondition(
			QueryUserStatLogCondition queryUserStatLogCondition) {
		this.queryUserStatLogCondition = queryUserStatLogCondition;
	}
	public void setStudentStat(StudentStat studentStat) {
		this.studentStat = studentStat;
	}
	public StudentStat getStudentStat() {
		return studentStat;
	}
	public List<StudentStat> getStudentStatList() {
		return studentStatList;
	}
	public void setStudentStatList(List<StudentStat> studentStatList) {
		this.studentStatList = studentStatList;
	}
	public QueryStudentStatCondition getQueryStudentStatCondition() {
		return queryStudentStatCondition;
	}
	public void setQueryStudentStatCondition(
			QueryStudentStatCondition queryStudentStatCondition) {
		this.queryStudentStatCondition = queryStudentStatCondition;
	}
	public String getDownFileURL() {
		return downFileURL;
	}
	public void setDownFileURL(String downFileURL) {
		this.downFileURL = downFileURL;
	}
	public List<From> getListForm() {
		return listForm;
	}
	public void setListForm(List<From> listForm) {
		this.listForm = listForm;
	}
	public String getFromIds() {
		return fromIds;
	}
	public void setFromIds(String fromIds) {
		this.fromIds = fromIds;
	}
	public String getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public IFrom getFromService() {
		return fromService;
	}
	public void setFromService(IFrom fromService) {
		this.fromService = fromService;
	}
}
