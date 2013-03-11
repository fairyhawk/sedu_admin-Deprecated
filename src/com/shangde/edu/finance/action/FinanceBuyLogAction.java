package com.shangde.edu.finance.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;

/**
 * 
 * @ClassName: FinanceBuyLogAction
 * @Description: TODO(统计用户各个操作的访问量，放到缓存中)
 * @author shixiaofeng@sunland.org.cn
 * @date 2012-6-12 上午10:32:47
 * 
 */
public class FinanceBuyLogAction extends CommonAction {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger
			.getLogger(FinanceBuyLogAction.class);

	private List<Map<String, String>> financeBuyLogList;// 查询出来的用户支付优化统计列表
	private ISubject subjectService;// 查询项目的service接口
	private QuerySubjectCondition querySubjectCondition;// 查询项目列表的条件实体
	private List<Subject> subjectList;// 查询所有的项目列表
	private int subjectId;// 当前的项目id
	private String startTime; // 自定义查询 开始年月日
	private String startHH; // 自定义查询 开始时分秒
	private String endTime; // 自定义 结束年月日
	private String endHH; // 自定义 结束时分秒
	private String queryStartTime;// 查询开始时间
	private String queryEndTime;// 查询结束时间

	/**
	 * 导出流水表标题EXCL
	 */
	private String exportName;
	private InputStream excelFile;

	/**
	 * 
	 * @Title: queryFinanceBuyLogList
	 * @Description: TODO(获取前台用户点击按钮的事件，将信息放到缓存中，并通过json返回)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String queryFinanceBuyLogList() {
		try {
			logger
					.debug("=====进入FinanceBuyLogAction.queryFinanceBuyLogList方法==");
			/**
			 * 查询所有的项目列表
			 */
			getAllSubject();

			/**
			 * 将项目list转成map
			 */
			Map<String, Subject> subMap = this.list2map(subjectList);

			/**
			 * 组装时间选择框列表
			 */
			getSelectTime();

			/**
			 * 根据条件查询用户支付优化记录列表
			 */
			IWebService webService = WebServiceFactory.getVideoService();
			String beanjson = webService.queryFinanceBuyLog(subjectId,
					queryStartTime, queryEndTime);
			logger.debug("====出现出的记录json是：" + beanjson);
			financeBuyLogList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> sourList = new ArrayList<Map<String, String>>();

			JSONArray jsonArray = JSONArray.fromObject(beanjson);
			if (jsonArray != null && jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", (String) jsonObj.get("id"));
					map.put("subject_id", (String) jsonObj.get("subject_id"));
					map.put("click_type", (String) jsonObj.get("click_type"));
					map.put("click_count", (String) jsonObj.get("click_count"));
					sourList.add(map);
				}
			}

			Set<String> keyset = new HashSet<String>();
			for (Map<String, String> map : sourList) {
				keyset.add(map.get("subject_id"));
			}

			for (String subid : keyset) {
				Map<String, String> newmap = new HashMap<String, String>();
				newmap.put("subjectid", subid);
				newmap.put("subject_name", subMap.get(subid).getSubjectName());
				for (Map<String, String> map : sourList) {
					if (map.get("subject_id").equals(subid)) {
						if (map.get("click_type").equals("1")) {
							newmap.put("buy_clicked", map.get("click_count"));
						} else if (map.get("click_type").equals("2")) {
							newmap.put("register_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("3")) {
							newmap.put("login_clicked", map.get("click_count"));
						} else if (map.get("click_type").equals("4")) {
							newmap.put("next_step_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("5")) {
							newmap.put("create_third_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("6")) {
							newmap.put("create_recv_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("7")) {
							newmap.put("create_offline_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("8")) {
							newmap.put("pay_third_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("9")) {
							newmap.put("up_step_clicked", map
									.get("click_count"));
						}
					}
				}
				financeBuyLogList.add(newmap);
			}

		} catch (Exception ex) {
			logger.error("FinanceBuyLogAction.queryFinanceBuyLogList", ex);
			return ERROR;
		}
		return "listCashReturn";
	}

	public List<Map<String, String>> getFinanceBuyLogList() {
		return financeBuyLogList;
	}

	public void setFinanceBuyLogList(List<Map<String, String>> financeBuyLogList) {
		this.financeBuyLogList = financeBuyLogList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(
			QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	private List<Subject> getAllSubject() {
		subjectList = new ArrayList<Subject>();
		Subject xuanze = new Subject();
		xuanze.setSubjectId(0);
		xuanze.setSubjectName("--请选择--");
		subjectList.add(xuanze);
		subjectList.addAll(subjectService.getAllSubject());
		return subjectList;
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {

		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar todayDate = Calendar.getInstance(); // 当天时间对象
		Date today = todayDate.getTime(); // 获取时间
		String todayStrartTime = dateFm.format(today); // 格式化当天开始时间
		String todayEndTime = dateFm.format(today); // 格式化当天结束时间

		if (startTime == null) {
			startTime = todayStrartTime; // 完整开始时间
		}
		if (endTime == null) {
			endTime = todayEndTime; // 完结束始时间
		}
		if (startHH == null) {
			startHH = "00:00:00";
		}
		if (endHH == null) {
			endHH = "23:59:59";
		}
		queryStartTime = startTime + " " + startHH; // 完整开始时间
		queryEndTime = endTime + " " + endHH; // 完整结束时间
	}

	/**
	 * 导出EXCEL表格
	 * 
	 * @return String Liming
	 */
	public String exportCSV() {
		try {
			String expName = "支付流程数据统计_" + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent")
					.toUpperCase().indexOf("MSIE") > 0) {
				setExportName(URLEncoder.encode(expName, "UTF-8"));// IE浏览器
			} else {
				setExportName(new String((expName).getBytes(), "iso-8859-1"));
			}

			List<List<String>> list = new ArrayList<List<String>>();
			List<String> headList = new ArrayList<String>();
			headList.add("项目");
			headList.add("点购量");
			headList.add("注册量");
			headList.add("登录量");
			headList.add("点击下一步");
			headList.add("提交订单");
			headList.add("立即支付");
			headList.add("先下支付");
			headList.add("第三方支付");
			headList.add("上一步");
			headList.add("开始时间");
			headList.add("结束时间");
			list.add(headList);

			/**
			 * 查询所有的项目列表
			 */
			getAllSubject();

			/**
			 * 将项目list转成map
			 */
			Map<String, Subject> subMap = this.list2map(subjectList);

			/**
			 * 组装时间选择框列表
			 */
			getSelectTime();

			/**
			 * 根据条件查询用户支付优化记录列表
			 */
			IWebService webService = WebServiceFactory.getVideoService();
			String beanjson = webService.queryFinanceBuyLog(subjectId,
					queryStartTime, queryEndTime);
			logger.debug("====出现出的记录json是：" + beanjson);
			financeBuyLogList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> sourList = new ArrayList<Map<String, String>>();

			JSONArray jsonArray = JSONArray.fromObject(beanjson);
			if (jsonArray != null && jsonArray.size() > 0) {
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					Map<String, String> map = new HashMap<String, String>();
					map.put("id", (String) jsonObj.get("id"));
					map.put("subject_id", (String) jsonObj.get("subject_id"));
					map.put("click_type", (String) jsonObj.get("click_type"));
					map.put("click_count", (String) jsonObj.get("click_count"));
					sourList.add(map);
				}
			}

			Set<String> keyset = new HashSet<String>();
			for (Map<String, String> map : sourList) {
				keyset.add(map.get("subject_id"));
			}

			for (String subid : keyset) {
				Map<String, String> newmap = new HashMap<String, String>();
				newmap.put("subjectid", subid);
				newmap.put("subject_name", subMap.get(subid).getSubjectName());
				for (Map<String, String> map : sourList) {
					if (map.get("subject_id").equals(subid)) {
						if (map.get("click_type").equals("1")) {
							newmap.put("buy_clicked", map.get("click_count"));
						} else if (map.get("click_type").equals("2")) {
							newmap.put("register_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("3")) {
							newmap.put("login_clicked", map.get("click_count"));
						} else if (map.get("click_type").equals("4")) {
							newmap.put("next_step_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("5")) {
							newmap.put("create_third_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("6")) {
							newmap.put("create_recv_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("7")) {
							newmap.put("create_offline_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("8")) {
							newmap.put("pay_third_clicked", map
									.get("click_count"));
						} else if (map.get("click_type").equals("9")) {
							newmap.put("up_step_clicked", map
									.get("click_count"));
						}
					}
				}
				financeBuyLogList.add(newmap);
			}

			for (Map<String, String> map : financeBuyLogList) {
				List<String> small = new ArrayList<String>();
				small.add(map.get("subject_name"));
				small.add(map.get("click_type"));
				small.add(map.get("register_clicked"));
				small.add(map.get("login_clicked"));
				small.add(map.get("next_step_clicked"));
				small.add(map.get("create_third_clicked"));
				small.add(map.get("create_recv_clicked"));
				small.add(map.get("create_offline_clicked"));
				small.add(map.get("pay_third_clicked"));
				small.add(map.get("up_step_clicked"));
				small.add(queryStartTime);
				small.add(queryEndTime);
				list.add(small);
			}

			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportcoupon";
		} catch (Exception ex) {
			logger.error("CashRecordAction.getCashRecordList", ex);
			return ERROR;
		}
	}

	/**
	 * 
	 * @Title: list2map
	 * @Description: TODO(把list转成map)
	 * @param
	 * @param list
	 * @param
	 * @return 设定文件
	 * @return Map<Integer,Subject> 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	private Map<String, Subject> list2map(List<Subject> list) {
		Map<String, Subject> map = new HashMap<String, Subject>();
		for (Subject obj : list) {
			map.put(String.valueOf(obj.getSubjectId()), obj);
		}
		return map;
	}

	public String getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(String queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public String getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(String queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
}