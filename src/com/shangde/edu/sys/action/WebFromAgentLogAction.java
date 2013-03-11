package com.shangde.edu.sys.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.condition.QueryUserWebFromCondition;
import com.shangde.edu.sys.condition.QueryWebFromAgentLogsCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.dto.StatisticsWebFromDTO;
import com.shangde.edu.sys.dto.UserWebFromDTO;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IWebFromAgentLogService;

public class WebFromAgentLogAction extends CommonAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger
			.getLogger(WebFromAgentLogAction.class);

	// query conditions.

	/** 项目推广统计 - 查询条件 */
	private QueryWebFromAgentLogsCondition queryWebFromAgentLogsCondition;

	/** 学员推广查询 - 查询条件 */
	private QueryUserWebFromCondition queryUserWebFromCondition;

	// services.

	private IWebFromAgentLogService webFromAgentLogService;

	private ISubject subjectService;

	// the select list.

	/** subject select */
	private List<Subject> subjectList;

	/** domain select */
	private Map<String, String> domainMap;

	/** time select */
	private Map<String, String> hourSelect;

	// for exporting excel.

	/** excel name */
	private String exportName;

	/** excel stream */
	private InputStream excelFile;

	/** 项目推广统计 */
	public String queryWebFromLog() {
		try {
			// set date range to a week by default.
			if (queryWebFromAgentLogsCondition == null) {
				queryWebFromAgentLogsCondition = new QueryWebFromAgentLogsCondition();
				long today = new Date().getTime();
				long weekInMs = 1000 * 60 * 60 * 24 * 7;
				long sevenDaysAgo = today - weekInMs;
				queryWebFromAgentLogsCondition.setStartTime(new Date(
						sevenDaysAgo));
				queryWebFromAgentLogsCondition.setEndTime(new Date(today));
				queryWebFromAgentLogsCondition.setCurrentPage(1);
			}
			queryWebFromAgentLogsCondition.setPageSize(30);
			// response excel.
			if (queryWebFromAgentLogsCondition.getExportExcel() != null
					&& queryWebFromAgentLogsCondition.getExportExcel()) {
				queryWebFromAgentLogsCondition.setPageSize(5000);
				PageResult page = webFromAgentLogService
						.statisticWebFromLog(queryWebFromAgentLogsCondition);
				if (queryWebFromAgentLogsCondition.isShowOthers()) {
					StatisticsWebFromDTO dto = webFromAgentLogService
							.statisticWebFromLogOthers(queryWebFromAgentLogsCondition);
					page.getPageResult().add(dto);
				}
				return queryWebFromLogExportExcel(page);
			}
			// search via page.
			initHourSelect();
			initDomainMap();
			subjectList = subjectService.getAllSubject();
			Subject s = new Subject();
			s.setSubjectId(0);
			s.setSubjectName("首页");
			subjectList.add(0, s);
			PageResult page = webFromAgentLogService
					.statisticWebFromLog(queryWebFromAgentLogsCondition);
			if (queryWebFromAgentLogsCondition.isShowOthers()) {
				StatisticsWebFromDTO dto = webFromAgentLogService
						.statisticWebFromLogOthers(queryWebFromAgentLogsCondition);
				page.getPageResult().add(dto);
				page.setPageSize(30);
				page.setTotalRecord(page.getTotalRecord() + 1);
			}
			setPage(page);
			page.setPageSize(30);
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("WebFromAgentLogAction.queryWebFromLog", e);
		}
		return "webFromLog";
	}

	/** 学员推广查询 */
	public String queryUserWebFrom() {
		try {
			// set date range to a week by default.
			if (queryUserWebFromCondition == null) {
				queryUserWebFromCondition = new QueryUserWebFromCondition();
				long today = new Date().getTime();
				long weekInMs = 1000 * 60 * 60 * 24 * 7;
				long sevenDaysAgo = today - weekInMs;
				queryUserWebFromCondition.setStartTime(new Date(sevenDaysAgo));
				queryUserWebFromCondition.setEndTime(new Date(today));
				queryUserWebFromCondition.setCurrentPage(1);
			}
			queryUserWebFromCondition.setPageSize(30);
			// response excel.
			if (queryUserWebFromCondition.getExportExcel() != null
					&& queryUserWebFromCondition.getExportExcel()) {
				queryUserWebFromCondition.setPageSize(5000);
				PageResult page = webFromAgentLogService
						.statisticUserWebFrom(queryUserWebFromCondition);
				return queryUserWebFromExportExcel(page);
			}
			// search via page.
			initHourSelect();
			subjectList = subjectService.getAllSubject();
			PageResult page = webFromAgentLogService
					.statisticUserWebFrom(queryUserWebFromCondition);
			page.setPageSize(30);
			setPage(page);
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("WebFromAgentLogAction.queryUserWebFrom", e);
		}
		return "userWebFrom";
	}

	/** 初始化域名下拉列表 */
	private void initDomainMap() {
		domainMap = new LinkedHashMap<String, String>();
		domainMap.put("highso.cn", "highso.cn");
		domainMap.put("highso.com.cn", "highso.com.cn");
		domainMap.put("highso.net.cn", "highso.net.cn");
		domainMap.put("highso.org", "highso.org");
		domainMap.put("highso.org.cn", "highso.org.cn");
		domainMap.put("haixue.com", "haixue.com");
	}

	/** 初始化时间下拉列表 */
	private void initHourSelect() {
		hourSelect = new LinkedHashMap<String, String>();
		DecimalFormat df = new DecimalFormat("00");
		for (int i = 0; i <= 23; i++) {
			String time = df.format(i) + ":00:00";
			hourSelect.put(time, time);
		}
		hourSelect.put("23:59:59", "23:59:59");
	}

	/** 项目推广统计 - 导出Excel */
	private String queryWebFromLogExportExcel(PageResult page)
			throws UnsupportedEncodingException {
		List<String> headerList = new ArrayList<String>();
		List<List<String>> dataList = new ArrayList<List<String>>();
		headerList.add("推广渠道");
		headerList.add("点击量");
		headerList.add("注册量");
		headerList.add("点击注册率");
		headerList.add("总订单数");
		headerList.add("总订单金额");
		headerList.add("已付款订单数");
		headerList.add("已付款订单金额");
		dataList.add(headerList);
		List<StatisticsWebFromDTO> result = page.getPageResult();
		List<String> list = null;
		for (int i = 0; i < result.size(); i++) {
			StatisticsWebFromDTO dto = result.get(i);
			list = new ArrayList<String>();
			list.add(dto.getWebFrom());
			list.add(Integer.toString(dto.getClickCount()));
			list.add(Integer.toString(dto.getRegisterCount()));
			list.add(dto.getRegisterRate());
			list.add(Integer.toString(dto.getContractCount()));
			list.add(dto.getContractMoneySumStr());
			list.add(Integer.toString(dto.getPayedContractCount()));
			list.add(dto.getPayedContractMoneySumStr());
			dataList.add(list);
		}
		String expName = "项目推广统计_" + DateUtil.getCurrentDate() + ".xls";
		if (ServletActionContext.getRequest().getHeader("User-Agent")
				.toUpperCase().indexOf("MSIE") > 0) {
			// for IE browser.
			setExportName(URLEncoder.encode(expName, "UTF-8"));
		} else {
			// for the other browsers.
			setExportName(new String((expName).getBytes(), "ISO-8859-1"));
		}
		FileExportImportUtil fileExportImportUtil = new FileExportImportUtil();
		excelFile = fileExportImportUtil.export(dataList);
		return "exportExcel";
	}

	/** 学员推广查询 - 导出Excel */
	private String queryUserWebFromExportExcel(PageResult page)
			throws UnsupportedEncodingException {
		List<String> headerList = new ArrayList<String>();
		List<List<String>> dataList = new ArrayList<List<String>>();
		headerList.add("编号");
		headerList.add("手机号");
		headerList.add("注册Email");
		headerList.add("注册路径");
		headerList.add("推广来源");
		headerList.add("站长ID");
		headerList.add("注册时间");
		headerList.add("支付数/订单数");
		dataList.add(headerList);
		List<UserWebFromDTO> result = page.getPageResult();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> list = null;
		for (int i = 0; i < result.size(); i++) {
			UserWebFromDTO dto = result.get(i);
			list = new ArrayList<String>();
			list.add(Integer.toString(i + 1));
			list.add(dto.getMobile());
			list.add(dto.getEmail());
			list.add(dto.getSubjectName());
			list.add(dto.getWebFrom());
			list.add(dto.getWebAgent());
			list.add(sdf.format(dto.getRegDate()));
			list.add(dto.getPayedRecordCount() + "/" + dto.getRecordCount());
			dataList.add(list);
		}
		String expName = "学员推广查询_" + DateUtil.getCurrentDate() + ".xls";
		if (ServletActionContext.getRequest().getHeader("User-Agent")
				.toUpperCase().indexOf("MSIE") > 0) {
			// for IE browser.
			setExportName(URLEncoder.encode(expName, "UTF-8"));
		} else {
			// for the other browsers.
			setExportName(new String((expName).getBytes(), "ISO-8859-1"));
		}
		FileExportImportUtil fileExportImportUtil = new FileExportImportUtil();
		excelFile = fileExportImportUtil.export(dataList);
		return "exportExcel";
	}

	public QueryWebFromAgentLogsCondition getQueryWebFromAgentLogsCondition() {
		if (queryWebFromAgentLogsCondition == null) {
			queryWebFromAgentLogsCondition = new QueryWebFromAgentLogsCondition();
		}
		return queryWebFromAgentLogsCondition;
	}

	public void setQueryWebFromAgentLogsCondition(
			QueryWebFromAgentLogsCondition queryWebFromAgentLogsCondition) {
		this.queryWebFromAgentLogsCondition = queryWebFromAgentLogsCondition;
	}

	public IWebFromAgentLogService getWebFromAgentLogService() {
		return webFromAgentLogService;
	}

	public void setWebFromAgentLogService(
			IWebFromAgentLogService webFromAgentLogService) {
		this.webFromAgentLogService = webFromAgentLogService;
	}

	public Map<String, String> getDomainMap() {
		return domainMap;
	}

	public void setDomainMap(Map<String, String> domainMap) {
		this.domainMap = domainMap;
	}

	public QueryUserWebFromCondition getQueryUserWebFromCondition() {
		return queryUserWebFromCondition;
	}

	public void setQueryUserWebFromCondition(
			QueryUserWebFromCondition queryUserWebFromCondition) {
		this.queryUserWebFromCondition = queryUserWebFromCondition;
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

	public Map<String, String> getHourSelect() {
		return hourSelect;
	}

	public void setHourSelect(Map<String, String> hourSelect) {
		this.hourSelect = hourSelect;
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
