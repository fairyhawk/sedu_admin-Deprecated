package com.shangde.edu.stu.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.util.DateUtil;
import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.domain.Kpoint;

import com.shangde.edu.stu.condition.QueryCourseSummaryCondition;
import com.shangde.edu.stu.condition.QueryExamSummaryCondition;
import com.shangde.edu.stu.condition.QueryExapaReCondition;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.edu.stu.domain.CourseSummary;
import com.shangde.edu.stu.domain.ExamSummary;
import com.shangde.edu.stu.domain.Exapa;
import com.shangde.edu.stu.domain.ExapaRe;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.domain.Planclock;
import com.shangde.edu.stu.domain.Planitem;
import com.shangde.edu.stu.service.ICourseSummary;
import com.shangde.edu.stu.service.IExamSummary;
import com.shangde.edu.stu.service.IPlan;
import com.shangde.edu.stu.service.IPlanclock;
import com.shangde.edu.stu.service.IPlanitem;
import com.shangde.edu.stu.service.IVideoStatistics;
import com.shangde.common.util.CookieHandler;


import com.shangde.edu.exam.domain.Exampaper;



/**
 *<p>
 *	  更新说明：大量方法重新编写，解决日历翻月时Bug，2011年6月1日。<br>
 *	  添加注释。2011年6月3日。<br>
 *</p>
 * Author: baiang.zhao <br>
 * Date: May 24, 2011 <br>
 * Time: 4:01:29 PM <br>
 * Modified By baiang.zhao , 2011-6-1 <br>
 * Modified By baiang.zhao , 2011-6-3 <br>
 */
public class CalendarWebAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日期的查询条件 用到opendate属性
	 */
	private QueryPlanCondition queryPlanCondition;

	/**
	 * 学习计划集合
	 */
	private List<Plan> listPlan;

	/**
	 * service层
	 */
	private IPlan planService;

	/**
	 * 日历的缓冲流
	 */
	private  String calendarBuffer;

	/**
	 * 日历的Title
	 */
	private String titleCalendar;

	/**
	 * 按照次日期检索 学习计划、提醒、总结 最终的格式为 2011-05-25,在ibatiS中，使用模糊查询，直接匹配
	 */
	private String currentDay;

	/**
	 * 翻月，下个月
	 */
	private int next;

	/**
	 * 翻月，上个月
	 */
	private int prev;

	/**
	 * 当前的年，用于action和jsp互相传递数据
	 */
	private int year;

	/**
	 * 当前的月，用于action和jsp相互传递数据
	 */
	private int month;

	private static String UIDEFAULT = "<a class='ui-state-default ";

	private static String UIHIGHLIGHT = "ui-state-highlight ";

	private static String UIACTIVE = "ui-state-active ";

  
	//在本地时要加上 sedu ，上传服务器后要把它删除
	//private final String HREF = " href='/sedu/stu/calendar!getListPlanByCalendardGoto.action?checkDay=";
	private final String HREF = " href='/stu/calendar!getListPlanByCalendardGoto.action?checkDay=";

	private final String AEND = "</a>";

	public String getListPlanByCalendardGoto() {
		getListPlanByCalendard();
		getCurrentDayMsg();

		return "getListPlanByCalendardGoto";
	}

	public String getListPlanByCalendardGotoUpdate() {
		getListPlanByCalendard();
		getCurrentDayMsg();

		return "getListPlanByCalendardGotoUpdate";
	}

	/**
			初始化-----------------------------
			year:0 , month:0 , next:0 , prev:0
			checkDay: 2011-06-02

			当月的某天-------------------------
			year:0 , month:0 , next:0 , prev:0
			checkDay: 2011-06-09

			切换月份------------------------------
			year:2011 , month:6 , next:1 , prev:0
			checkDay: null

			切换月后的点击某天--------------------
			year:0 , month:0 , next:0 , prev:0
			checkDay: 2011-07-14

	*/

	/**
	 *   说明：
	 * 		先取得year和month，如果year ==0 且 month == 0 ，那么有两种情况：1、初始化的时候，2、切换月份后点击某天的时候；
	 * 	 如果有一个不为0，则说明在切换月份，需要对月份的增大减小所影响到年的增大减小做判断，比例2011-12 ，如果翻下一月，则为2012-1
	 * 		然后根据处理过的日期字符串，设置到queryPlanCondition中，使用模糊查询，匹配出当月所有的计划，
	 *   再将所有计划的“天”提取出来，存入一个List中，以便在getCalendar方法中，对日期和计划的“天”做匹配，如果日期和“天”相等，则说明改天有计划，并修改class样式。
	 *   	在getCalendar中，首先取得当前的年月日，然后判断是否是切换月，并且切换月后，点击某天，一定要停留在改天。
	 *   然后将日历进行拼接，包含HTML代码和CSS样式。最后将拼接的日历结果和日历Title封装到Map中，返回。
	 *
	 */

	 /**
	 * 		生成日历的主方法！
	 * 日历生成，需要用到如下几个参数（与jsp交互）：
	 * year:当前的年。例如：当前年为2011-5-12，则year为2011
	 * month:当前的月。例如：当前年为2011-5-12，则month为5
	 * next:下一个月。int类型，该值始终为1 。初始化时为0，当>0时，表示跳转下个月。
	 * prev:上一个月。int类型，该值始终为-1。初始化时为0，当<0时，表示跳转上个月。
	 * chackDay:关键参数！在切换月的时候，该值为null，所以此时将checkDay设置成每月的第一天。当点击切换后的月中的某一天时，也应该对齐进行重置。
	 *
	 * @return getListPlanByCalendard
	 */
	public String getListPlanByCalendard() {
		queryPlanCondition = getQueryPlanCondition();
		// 如果opendate 的值为空，那么取当天的值，如 2011-5-25 00:00:00
		if (queryPlanCondition.getCalendar() == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

			String tmpSetCalendar = "";
			int _tempYear = this.year;
			int _tempMonth = this.month;

			// 如果的当前月，即index=0，直接new一个Date对象
			if (next == 0 && prev == 0) {
				String tempCheck = this.checkDay;
				tempCheck = tempCheck.substring(0, 7);
				tmpSetCalendar = sdf.format(new Date());

				if (!tempCheck.equals(tmpSetCalendar)) {
					tmpSetCalendar = tempCheck;
				}
			} else {
				// 如果月增大
				if (next > 0) {
					if (_tempMonth == 12) {
						_tempYear++;
						_tempMonth = 1;
					} else
						_tempMonth++;
				}
				// 如果月减小
				else if (prev < 0) {
					if (_tempMonth == 1) {
						_tempYear--;
						_tempMonth = 12;
					} else
						_tempMonth--;
				}
				// 对日期进行处理
				try {
					tmpSetCalendar = sdf.format(sdf.parse(_tempYear + "-" + _tempMonth));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// 设置属性，例如 2011-05
			queryPlanCondition.setCalendar(tmpSetCalendar);
			// 设置用户ID
			queryPlanCondition.setCusId(getLoginUserId());

			// 查询到所有当月的学习计划
			listPlan = planService.getPlanListByCalendar(queryPlanCondition);
			List<String> tempDatePlan = new ArrayList<String>();
			if (listPlan.size() > 0) {
				try {
					for (int i = 0; i<listPlan.size(); i++) {
						Plan tPlan = listPlan.get(i);
						tempDatePlan.add(new SimpleDateFormat("d").format(tPlan.getOpendate()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 学习计划和日历进行匹配
			Map<String, String> map = getCalendar(tmpSetCalendar, tempDatePlan);

			// 转换成字符串
			calendarBuffer = map.get("calendar");
			titleCalendar = map.get("title");
		}
		
		return "getListPlanByCalendard";
	}
	
	
	//切换月份
	public String getListPlanByCalendardChangMonth() {
		queryPlanCondition = getQueryPlanCondition();
		// 如果opendate 的值为空，那么取当天的值，如 2011-5-25 00:00:00
		if (queryPlanCondition.getCalendar() == null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

			String tmpSetCalendar = "";
			int _tempYear = this.year;
			int _tempMonth = this.month;

			// 如果的当前月，即index=0，直接new一个Date对象
			if (next == 0 && prev == 0) {
				String tempCheck = this.checkDay;
				tempCheck = tempCheck.substring(0, 7);
				tmpSetCalendar = sdf.format(new Date());

				if (!tempCheck.equals(tmpSetCalendar)) {
					tmpSetCalendar = tempCheck;
				}
			} else {
				// 如果月增大
				if (next > 0) {
					if (_tempMonth == 12) {
						_tempYear++;
						_tempMonth = 1;
					} else
						_tempMonth++;
				}
				// 如果月减小
				else if (prev < 0) {
					if (_tempMonth == 1) {
						_tempYear--;
						_tempMonth = 12;
					} else
						_tempMonth--;
				}
				// 对日期进行处理
				try {
					tmpSetCalendar = sdf.format(sdf.parse(_tempYear + "-" + _tempMonth));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			// 设置属性，例如 2011-05
			queryPlanCondition.setCalendar(tmpSetCalendar);
			// 设置用户ID
			queryPlanCondition.setCusId(getLoginUserId());

			// 查询到所有当月的学习计划
			listPlan = planService.getPlanListByCalendar(queryPlanCondition);
			List<String> tempDatePlan = new ArrayList<String>();
			if (listPlan.size() > 0) {
				try {
					for (int i = 0; i<listPlan.size(); i++) {
						Plan tPlan = listPlan.get(i);
						tempDatePlan.add(new SimpleDateFormat("d").format(tPlan.getOpendate()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 学习计划和日历进行匹配
			Map<String, String> map = getCalendar(tmpSetCalendar, tempDatePlan);

			// 转换成字符串
			calendarBuffer = map.get("calendar");
			titleCalendar = map.get("title");
		}
		
		String  firtDay ;
		if(month<=9){
			firtDay = year+"-0"+month+"-01";
		}else{
			firtDay = year+"-"+month+"-01";
		}
		
		getCurrentPlanByDate(firtDay);
		getCurrenPlanclockByDate(firtDay);
		getCurrenExamSummaryByDate(firtDay);
		getCourseSummaryListBySummarydate(firtDay);
		
		
		
		return "getListPlanByCalendardChangMonth";
	}

	/**
	 * 得到日历和标题的字符串
	 * @author baiang.zhao , 2011-6-1 10:25:31
	 * @param 	date 一个时间的字符串，精确到月，例如：2011-5
	 * @param 	datePlanList 当月所有计划的日期的集合，例如：2011-5的25号好计划，那么将25放入该集合
	 * @return 	返回一个Map,包含Key为title和calendar
	 */
	public Map<String, String> getCalendar(String date, List<String> datePlanList) {
		// 得到一个日历对象
		Calendar calendar = Calendar.getInstance();
		// 当前年
		//int currentYear = calendar.get(Calendar.YEAR);
		// 当前月
		int currentMonth = calendar.get(Calendar.MONTH);
		// 今天
		int today = calendar.get(Calendar.DAY_OF_MONTH);

		SimpleDateFormat sdfDD = new SimpleDateFormat("dd");
		//SimpleDateFormat sdfMM = new SimpleDateFormat("MM");
		SimpleDateFormat sdfYyyyMM = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdfYyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

		// 获取点击的时间，然后将该时间进行转换，等到日期，再将次日期和月份的每天相比较
		String _checkDay = null;

		// 切换月后，点击该月的某一天，使日历还停留在该月，而不是跳会实际当前月
		if (this.checkDay != null) {
			_checkDay = this.checkDay;
			String subChenckDay = this.checkDay;
			// 截取到日，例如2011-05
			subChenckDay = subChenckDay.substring(0, 7);
			// 截取的日期与该方法的参数date不相等，那么修改date的值
			if (!subChenckDay.equals(date)) {
				date = subChenckDay;
			}
		}
		else {
			// 切换月时，checkDay 为 null,所以此时将date修改成所需要切换的月的1号
			_checkDay = date + "-01";
			//if(Integer.parseInt(sdfMM.format(new Date())) != Integer.parseInt(sdfMM.format(sdfYyyyMMdd.parse(_checkDay)))) {
				this.checkDay = date + "-01";
			//}

		}
		// 锁定某月点击的那天
		int checkDayFormat = 0;
		if (_checkDay != null) {
			try {
				// 将处理过的_checkDay进行format，得到日
				checkDayFormat = Integer.parseInt(sdfDD.format(sdfYyyyMMdd.parse(_checkDay)));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		// 初始化
		StringBuffer titleBuffer = new StringBuffer();
		StringBuffer nullBuffer = new StringBuffer();
		StringBuffer calendarBuffer = new StringBuffer();

		Map<String, String> mapCalendar = new HashMap<String, String>();

		try {
			/*使用给定的 Date 设置此 Calendar 的时间*/
			calendar.setTime(sdfYyyyMM.parse(date));

			// 遍历得到所有日期
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH);
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);

			this.year = year;
			this.month = month + 1 ;

			// 日历Title赋值
			titleBuffer.append(year + "-" + (month + 1 ));

			calendar.set(Calendar.DAY_OF_MONTH, 1);

			// 设置每月前的空缺日期
			for (int i = Calendar.SUNDAY; i < weekday; i++) {
				nullBuffer.append("<td class='ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled'>&nbsp;</td>");
			}
			do {
				if ((weekday - 1) % 7 == 0 || weekday == 1) {
					calendarBuffer.append("<tr>");
				}

				// 如果有空缺日期，则和calendarBufferT拼接，之后，将nullBufferT重置！
				if (nullBuffer != null && !"".equals(nullBuffer.toString())) {
					calendarBuffer.append(nullBuffer.toString());
					nullBuffer = null;
				}
				// 实际日期
				int day = calendar.get(Calendar.DAY_OF_MONTH);

				// 设定查询日期
				try {
					SimpleDateFormat sdfForCurrentDay = new SimpleDateFormat("yyyy-MM-dd");
					currentDay = sdfForCurrentDay.format(sdfForCurrentDay.parse(date + "-" + day));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				String tempDateTime = currentDay;
				// 是否是当前月
				if (month == currentMonth) {
					// 是否是当前天
					if (day == today) {
						// day这个日期，有学习计划
						calendarBuffer.append("<td>").append(UIDEFAULT + UIHIGHLIGHT );

						// 是否是选中的那天
						if (checkDayFormat == day) {
							calendarBuffer.append(UIACTIVE);
						}
						calendarBuffer.append("' " + HREF + tempDateTime + "'>");

						// 该天是否有计划
						if (datePlanList.contains(day + "")) {
							calendarBuffer.append("<em class='markTip tip'>").append(day).append("</em>").append(AEND);
						} else {
							calendarBuffer.append(day).append(AEND);
						}

						calendarBuffer.append("</td>");
					} else {
						// day这个日期，有学习计划
						calendarBuffer.append("<td>").append(UIDEFAULT);

						// 是否是选中的那天
						if (checkDayFormat == day) {
							calendarBuffer.append(UIACTIVE);
						}
						calendarBuffer.append("' " + HREF + tempDateTime + "'>");

						// 该天是否有计划
						if (datePlanList.contains(day + "")) {
							calendarBuffer.append("<em class='markTip tip'>").append(day).append("</em>").append(AEND);
						} else {
							calendarBuffer.append(day).append(AEND);
						}

						calendarBuffer.append("</td>");
					}
				} else {
					// day这个日期，有学习计划
					calendarBuffer.append("<td>").append(UIDEFAULT);

					// 是否是选中的那天
					if (checkDayFormat == day) {
						calendarBuffer.append(UIACTIVE);
					}
					calendarBuffer.append("' " + HREF + tempDateTime + "'>");

					// 该天是否有计划
					if (datePlanList.contains(day + "")) {
						calendarBuffer.append("<em class='markTip tip'>").append(day).append("</em>").append(AEND);
					} else {
						calendarBuffer.append(day).append(AEND);
					}

					calendarBuffer.append("</td>");
				}

				if (weekday == Calendar.SATURDAY) {
					calendarBuffer.append("</tr>");
				}

				calendar.add(Calendar.DAY_OF_MONTH, 1);
				weekday = calendar.get(Calendar.DAY_OF_WEEK);

			} while (calendar.get(Calendar.MONTH) == month);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 存值
		mapCalendar.put("title", titleBuffer.toString());
		mapCalendar.put("calendar", calendarBuffer.toString());

		return mapCalendar;
	}

	public QueryPlanCondition getQueryPlanCondition() {
		if(queryPlanCondition == null)
			queryPlanCondition = new QueryPlanCondition();
		return queryPlanCondition;
	}

	public void setQueryPlanCondition(QueryPlanCondition queryPlanCondition) {
		this.queryPlanCondition = queryPlanCondition;
	}

	public List<Plan> getListPlan() {
		return listPlan;
	}

	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}

	public IPlan getPlanService() {
		return planService;
	}

	public void setPlanService(IPlan planService) {
		this.planService = planService;
	}

	public String getCalendarBuffer() {
		return calendarBuffer;
	}

	public void setCalendarBuffer(String calendarBuffer) {
		this.calendarBuffer = calendarBuffer;
	}

	public String getTitleCalendar() {
		return titleCalendar;
	}

	public void setTitleCalendar(String titleCalendar) {
		this.titleCalendar = titleCalendar;
	}

	public String getCurrentDAy() {
		return currentDay;
	}

	public void setCurrentDAy(String currentDAy) {
		this.currentDay = currentDAy;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * 按照次日期检索 学习计划、提醒、总结 最终的格式为 2011-05-25,在ibatiS中，使用模糊查询，直接匹配
	 *
	 * 用以接收从日历中传递过来的参数
	 */
	private String checkDay;

	/**
	 * 当天计划
	 */
	private Plan currentPlan;

	/**
	 * 当天计划内容
	 */
	private Planitem currentPlanitem;

	/**
	 *   学习计划内容的详情数组
	 */
	private String[] currentstrArr;

	/**
	 * 当天的闹钟
	 */
	private Planclock currentPlanclock;

	/**
	 * 当天的考试总结
	 */
	private ExamSummary currentExamSumm;

	/**
	 *   学习计划内容的详情数组
	 */
	private String[] strArr;

	/**
	 *   学习计划内容
	 */
	private Planitem planitem;

	/**
	 *	今日总结  课程服务
	 */
	private List<Planclock> planclockListByDay;

	/**
	 *	今日考试总结  课程服务
	 */
	private List<ExamSummary> examSummaryListByDay;

	private List<Exampaper> exampaperList ;


	private IPlanitem  planitemService;


	private IPlanclock planclockService;

	/**
	 *   考试记入服务
	 */
	private IExamSummary examSummaryService;

	/**
	 *   考试总结条件
	 */
	private QueryExamSummaryCondition queryExamSummaryCondition;

	/**
	 *   学习提醒条件
	 */
	private QueryPlanclockCondition queryPlanclockCondition;


	public QueryPlanclockCondition getQueryPlanclockCondition() {
		if (queryPlanclockCondition ==null){
			queryPlanclockCondition = new QueryPlanclockCondition();
		}
		return queryPlanclockCondition;
	}

	public void setQueryPlanclockCondition(
			QueryPlanclockCondition queryPlanclockCondition) {
		this.queryPlanclockCondition = queryPlanclockCondition;
	}

	public QueryExamSummaryCondition getQueryExamSummaryCondition() {
		if (queryExamSummaryCondition ==null){
			queryExamSummaryCondition = new QueryExamSummaryCondition();
		}
		return queryExamSummaryCondition;
	}

	public void setQueryExamSummaryCondition(
			QueryExamSummaryCondition queryExamSummaryCondition) {
		this.queryExamSummaryCondition = queryExamSummaryCondition;
	}

	public Plan getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(Plan currentPlan) {
		this.currentPlan = currentPlan;
	}

	public Planitem getCurrentPlanitem() {
		return currentPlanitem;
	}

	public void setCurrentPlanitem(Planitem currentPlanitem) {
		this.currentPlanitem = currentPlanitem;
	}

	public String[] getCurrentstrArr() {
		return currentstrArr;
	}

	public void setCurrentstrArr(String[] currentstrArr) {
		this.currentstrArr = currentstrArr;
	}

	public Planclock getCurrentPlanclock() {
		return currentPlanclock;
	}

	public void setCurrentPlanclock(Planclock currentPlanclock) {
		this.currentPlanclock = currentPlanclock;
	}

	public ExamSummary getCurrentExamSumm() {
		return currentExamSumm;
	}

	public void setCurrentExamSumm(ExamSummary currentExamSumm) {
		this.currentExamSumm = currentExamSumm;
	}

	public String[] getStrArr() {
		return strArr;
	}

	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	public Planitem getPlanitem() {
		return planitem;
	}

	public void setPlanitem(Planitem planitem) {
		this.planitem = planitem;
	}

	public List<Planclock> getPlanclockListByDay() {
		return planclockListByDay;
	}

	public void setPlanclockListByDay(List<Planclock> planclockListByDay) {
		this.planclockListByDay = planclockListByDay;
	}

	public IPlanitem getPlanitemService() {
		return planitemService;
	}

	public void setPlanitemService(IPlanitem planitemService) {
		this.planitemService = planitemService;
	}

	public IPlanclock getPlanclockService() {
		return planclockService;
	}

	public void setPlanclockService(IPlanclock planclockService) {
		this.planclockService = planclockService;
	}

	public IExamSummary getExamSummaryService() {
		return examSummaryService;
	}

	public void setExamSummaryService(IExamSummary examSummaryService) {
		this.examSummaryService = examSummaryService;
	}

	//得到日期下的学习计划和今日提醒
	private String getCurrentDayMsg() {

		getCurrentPlanByDate(checkDay);
		getCurrenPlanclockByDate(checkDay);
		getCurrenExamSummaryByDate(checkDay);
		getCourseSummaryListBySummarydate(checkDay);
		return "getCurrentDayMsg";
	}

	// 通过日期，得到当前日期下的  计划
	private void getCurrentPlanByDate(String checkDate) {
		try{
			getQueryPlanCondition().setCalendar(checkDate);
			queryPlanCondition.setCusId(getLoginUserId());
			currentPlan = planService.getPlanByDate(queryPlanCondition);

			if(currentPlan!=null){
				planitem = planitemService.getPlanitemByPlanId(currentPlan.getPlanId());

				String str = planitem.getItemtitle();
				strArr = str.split(",");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
//============== 闹钟与 课程 关联	=============================
	
	private int userId;
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	// if session 存有已购买专业的信息ID
	List<Integer> buySubjects = new ArrayList<Integer>();
	

	
	// 通过日期，得到当前日期下的  闹钟
	private void getCurrenPlanclockByDate(String checkDate) {
		try{
			QueryPlanclockCondition queryPlanclockCondition = getQueryPlanclockCondition();
			// 取登陆用户ID
			userId = getLoginUserId();
			
			//用户注册时所选择的  专业Id subjectId
			Integer subjectId = Integer.valueOf(CookieHandler.getCookieValueByName(servletRequest, "subjectId"));
			
			// ~ 用户是否购买过专业
			buySubjects = planclockService.getMyBuySubject(userId);
			
			//~ 已购买专业
			if (buySubjects.size() > 0) {
				// 第一个专业ID
				subjectId = buySubjects.get(0);
				queryPlanclockCondition.setSubjectId(subjectId);
				
			}else{
				
				queryPlanclockCondition.setSubjectId(subjectId);
			}
			
			Date calendar = new SimpleDateFormat("yyyy-MM-dd").parse(checkDate);
			//状态为发布   （1发布、2新稿、0删除）
			queryPlanclockCondition.setCstatus(1);
			queryPlanclockCondition.setCalendar(calendar);
			planclockListByDay = planclockService.getPlanclockListByDate(queryPlanclockCondition);
			
			if(planclockListByDay.size()==0||planclockListByDay == null){
				planclockListByDay = null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	// 通过日期，得到当前日期下的  考试总结
	private void getCurrenExamSummaryByDate(String checkDate) {
		try{
			QueryExamSummaryCondition qExamsummaryCondition = getQueryExamSummaryCondition();
        	qExamsummaryCondition.setCusId(getLoginUserId());
        	
    		Date summarydate = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
    		qExamsummaryCondition.setSummarydate(summarydate);
        	
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today = DateUtil.formatDate(new java.util.Date());
    		Date dt1 = df.parse(today);
	        Date dt2 = df.parse(checkDate);
	        boolean istoday=false;
	        if (dt1.getTime() < dt2.getTime()) {
	        	examSummaryListByDay = null;
	        	return;
	        }
    		
        	examSummaryListByDay =  examSummaryService.getExamSummaryList(queryExamSummaryCondition);
        	
        	//日期是今天，并且已经存在总结，重新插入今天的总结(先删除，后追加)
        	if (examSummaryListByDay != null && examSummaryListByDay.size()>0 && (dt1.getTime() == dt2.getTime()) ){
        		//删除今天的
        		for(int i=0;i<examSummaryListByDay.size();i++){
        			ExamSummary examSum = examSummaryListByDay.get(i);
        			if(examSum!= null){
        				examSummaryService.delExamSummaryById(examSum.getESumId());
        			}
        		}
        		
        		//重新插入
        		istoday = true;
        		
        	}
        	
        	//总结为空，并且是今天之前的日期，自动插入总结 
        	if(examSummaryListByDay == null || examSummaryListByDay.size() == 0){
        		
        		 //传入日期（dt2==checkDate） 在今天（today==dt1） 之前
        		 if (dt1.getTime() > dt2.getTime()  ) {
        			   istoday = true;
    	     	 }
        	}
        	 
        	//总结为空，并且是今天之前的日期，自动插入总结 ---结束
        	
        	//******
		 	if (istoday){
        		 List<ExapaRe> exampaperRecordList = null;
    			 Date addtime = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
    			 getQueryExapaReCondition().setCusId(getLoginUserId());
				 getQueryExapaReCondition().setAddtime(addtime);
	    		 exampaperRecordList = examSummaryService.getExampaperRecordList(queryExapaReCondition);
	    		 if(exampaperRecordList!=null && exampaperRecordList.size()>0){
	    			 
	         		//有计入 -- 将计入插入 ----> 今日考试总结表
	         		for(int i =0;i<exampaperRecordList.size();i++){
	         			ExapaRe er = exampaperRecordList.get(i);

	         			ExamSummary  exSummary = new ExamSummary();
	         			exSummary.setCusId(er.getCusId());
	         			exSummary.setExamId(er.getEpId());
	         			
	         			//根据试卷Id 找到试卷，为考试总结设置 试卷名称
	         			Exapa examper = examSummaryService.getExampaperById(er.getEpId());
	         			if(examper!=null){
	         				exSummary.setExamname(examper.getEpName());
	         			}
	         			exSummary.setSummarydate(er.getAddtime());
	         			
	         			//插入 ----> 今日考试总结表
	         			examSummaryService.addExamSummary(exSummary);
	         		}
	         		
	    		 } else{
	         		ExamSummary examSummarytemp = new ExamSummary();
 	    			Date examSummarydate = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
 	    			examSummarytemp.setSummarydate(examSummarydate);
 	    			examSummarytemp.setCusId(getLoginUserId());
 	    			examSummarytemp.setExamId(-1);
 	    			examSummarytemp.setExamname("没有做");
 	    			examSummaryService.addExamSummary(examSummarytemp);
	         			
	         	}
	    		 //插入完数据后重新查询一次数据
	    		 examSummaryListByDay =  examSummaryService.getExamSummaryList(queryExamSummaryCondition);
		 	}
         	//******
     		
			 
		 	
		 	
        	
        	//为了前台页面的判断，设置examSummaryListByDay
        	if(examSummaryListByDay == null || examSummaryListByDay.size()==0){
        		examSummaryListByDay = null;
    		}

		}catch(Exception e){
			e.printStackTrace();
		}
	}

    /**
     * 通过条件获取试卷记录集合
     * @param queryExampaperRecordCondition 试卷记录查询条件
     * @return 试卷查询集合
     * @author fanxin
     * @time   2011.05.24
     * @version 1.0
     */
/*	private void getExampaperRecordList(String checkDate){
		List<ExapaRe> exampaperRecordList = null;
		try{
			   最近三天
		    String befordate = DateUtil.addDays2(checkDate, -2);
			Date addtime = DateUtil.toSqlDate(befordate, "yyyy-MM-dd");
			Date bftime = DateUtil.toSqlDate(DateUtil.addDays2(checkDate, 1), "yyyy-MM-dd");
			getQueryExampaperRecordCondition().setBftime(bftime);
			
			
			Date addtime = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
			//getQueryExampaperRecordCondition().setCusId(getLoginUserId());
			//getQueryExampaperRecordCondition().setAddtime(addtime);
			
			getQueryExapaReCondition().setCusId(getLoginUserId());
			getQueryExapaReCondition().setAddtime(addtime);
    		

    		exampaperRecordList = examSummaryService.getExampaperRecordList(queryExapaReCondition);

    		if(exampaperRecordList!=null && exampaperRecordList.size()>0){
        		//有计入 -- 将计入插入 ----> 今日考试总结表
        		for(int i =0;i<exampaperRecordList.size();i++){
        			ExapaRe er = exampaperRecordList.get(i);

        			ExamSummary  exSummary = new ExamSummary();
        			exSummary.setCusId(er.getCusId());
        			exSummary.setExamId(er.getEpId());
        			
        			//根据试卷Id 找到试卷，为考试总结设置 试卷名称
        			Exapa examper = examSummaryService.getExampaperById(er.getEpId());
        			if(examper!=null){
        				exSummary.setExamname(examper.getEpName());
        			}
        			exSummary.setSummarydate(er.getAddtime());
        			
        			//插入 ----> 今日考试总结表
        			examSummaryService.addExamSummary(exSummary);

        		}
    		}
		}catch(Exception e){
			e.printStackTrace();
		}

	}*/



	/**
	 * 查找课程的今日总结计入
	 * @author fanxin
     * @time   2011.05.23
     * @version 1.0
	 * @return String
	 */
	private void getCourseSummaryListBySummarydate(String checkDate) {
		courseSummaryList = new ArrayList<CourseSummary>();
		try{
			QueryCourseSummaryCondition qCourseSummaryCondition = getQueryCourseSummaryCondition();

    		Date summarydate = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
    		qCourseSummaryCondition.setSummarydate(summarydate);
    		qCourseSummaryCondition.setCusId(getLoginUserId());
    		
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String today = DateUtil.formatDate(new java.util.Date());
			 boolean istodayCouSumm=false;
    		Date dt1 = df.parse(today);
	        Date dt2 = df.parse(checkDate);
	        if (dt1.getTime() < dt2.getTime()) {
	        	courseSummaryList = null;
	        	return;
	        }
	        
    		courseSummaryList = courseSummaryService.getCourseSummaryListBySummarydate(queryCourseSummaryCondition);
    		
           	//日期是今天，并且已经存在总结，重新插入今天的总结(先删除，后追加)
        	if (courseSummaryList != null && courseSummaryList.size()>0 && (dt1.getTime() == dt2.getTime()) ){
        		//删除今天的
        		for(int i=0;i<courseSummaryList.size();i++){
        			CourseSummary courseSum = courseSummaryList.get(i);
        			if(courseSum!= null){
        				courseSummaryService.delCourseSummaryById(courseSum.getCSumId());
        			}
        		}
        		//重新插入
        		istodayCouSumm=true;
        		
        	}
        	
    		if(courseSummaryList.size()== 0 ||courseSummaryList == null){
    			
    			if (dt1.getTime() > dt2.getTime()) {
    				istodayCouSumm = true;
    			}
    		}  
	            
			//*******//
            if(istodayCouSumm){
	            List<Kpoint> kpointList = null;
                String fmcheckDate = checkDate.replace("-", "");//20110508 
    			//今天看过的课程的节点  list 中存的是 知识点的Id
    			List<String> list = videoStatisticsService.getUserLearnKpointByDate(fmcheckDate, getLoginUserId());
    			
    			if(list!= null && list.size()>0){
    				kpointList = courseSummaryService.getKpointListBypointIdList(list);
    				System.out.println("今天看过的课程的节点  kpointList.size="+kpointList.size());
    				
    				if(kpointList!= null && kpointList.size()>0){
    					for(int i=0;i<kpointList.size();i++){
    						Kpoint kpoint = kpointList.get(i);
    						
    						CourseSummary courseSummarytem = new CourseSummary();
    			    		Date courseSummarydate = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
    			    		
    			    		courseSummarytem.setSummarydate(courseSummarydate);
    			    		courseSummarytem.setCusId(getLoginUserId());
    			    		courseSummarytem.setPointId(kpoint.getPointId());
    			    		courseSummarytem.setPointName(kpoint.getName());
    						
    						courseSummaryService.addCourseSummary(courseSummarytem);
    					}
    				}
    			 }else{
    				Date courseSummarydate = DateUtil.toSqlDate(checkDate, "yyyy-MM-dd");
    				CourseSummary courseSummarytmp = new CourseSummary();
    				courseSummarytmp.setSummarydate(courseSummarydate);
    				courseSummarytmp.setCusId(getLoginUserId());
    				courseSummarytmp.setPointId(-1);
    				courseSummarytmp.setPointName("没有学习");
    				courseSummaryService.addCourseSummary(courseSummarytmp);
    				
    			 }
				   //插入完数据后重新查询一次数据
	        	   courseSummaryList = courseSummaryService.getCourseSummaryListBySummarydate(queryCourseSummaryCondition);  
		       }
              //*******//
            
	    		//为了前台页面的判断，设置courseSummaryList
	    	
	    		if(courseSummaryList.size()== 0 ||courseSummaryList == null){
	    			courseSummaryList = null;
	    		}
    			
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	private IVideoStatistics   videoStatisticsService;

	/**
	 *	今日总结  课程
	 */
	private CourseSummary courseSummary;

	/**
	 *	今日总结  课程 集合
	 */
	private List<CourseSummary> courseSummaryList;

	/**
	 *	今日总结  课程服务
	 */
	private ICourseSummary courseSummaryService;


	public ICourseSummary getCourseSummaryService() {
		return courseSummaryService;
	}

	public void setCourseSummaryService(ICourseSummary courseSummaryService) {
		this.courseSummaryService = courseSummaryService;
	}



	public CourseSummary getCourseSummary() {
		return courseSummary;
	}

	public void setCourseSummary(CourseSummary courseSummary) {
		this.courseSummary = courseSummary;
	}

	public String getCheckDay() {
		SimpleDateFormat sdfForCheckDay = new SimpleDateFormat("yyyy-MM-dd");
		// 一个简单的容错处理，如果checkDay的格式不正确，那么将checkDay重新赋值为当前的日期
		if(checkDay != null) {
			try {
				checkDay = sdfForCheckDay.format(sdfForCheckDay.parse(this.checkDay));
			} catch (ParseException e) {
				checkDay = sdfForCheckDay.format(new Date());
			}
		}
		return checkDay;
	}

	public void setCheckDay(String checkDay) {
		this.checkDay = checkDay;
	}

	/**
	 * @return the currentDay
	 */
	public String getCurrentDay() {
		return currentDay;
	}

	/**
	 * @param currentDay the currentDay to set
	 */
	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}

	/**
	 * @return the next
	 */
	public int getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(int next) {
		this.next = next;
	}

	public int getPrev() {
		return prev;
	}

	
	public void setPrev(int prev) {
		this.prev = prev;
	}

	public List<ExamSummary> getExamSummaryListByDay() {
		return examSummaryListByDay;
	}

	public void setExamSummaryListByDay(List<ExamSummary> examSummaryListByDay) {
		this.examSummaryListByDay = examSummaryListByDay;
	}

	public List<Exampaper> getExampaperList() {
		return exampaperList;
	}

	public void setExampaperList(List<Exampaper> exampaperList) {
		this.exampaperList = exampaperList;
	}



	/**
	 *   考试记入条件
	 */
	private QueryExapaReCondition queryExapaReCondition;

	/**
	 *   课程记入条件
	 */
	private QueryCourseSummaryCondition queryCourseSummaryCondition;

	public QueryCourseSummaryCondition getQueryCourseSummaryCondition() {
		if (queryCourseSummaryCondition ==null){
			queryCourseSummaryCondition = new QueryCourseSummaryCondition();
		}
		return queryCourseSummaryCondition;
	}

	public void setQueryCourseSummaryCondition(
			QueryCourseSummaryCondition queryCourseSummaryCondition) {
		this.queryCourseSummaryCondition = queryCourseSummaryCondition;
	}

	public QueryExapaReCondition getQueryExapaReCondition() {
		if (queryExapaReCondition ==null){
			queryExapaReCondition = new QueryExapaReCondition();
		}
		return queryExapaReCondition;
	}

	public void setQueryExapaReCondition(
			QueryExapaReCondition queryExapaReCondition) {
		this.queryExapaReCondition = queryExapaReCondition;
	}
	
	public List<CourseSummary> getCourseSummaryList() {
		return courseSummaryList;
	}

	public void setCourseSummaryList(List<CourseSummary> courseSummaryList) {
		this.courseSummaryList = courseSummaryList;
	}

	public IVideoStatistics getVideoStatisticsService() {
		return videoStatisticsService;
	}

	public void setVideoStatisticsService(IVideoStatistics videoStatisticsService) {
		this.videoStatisticsService = videoStatisticsService;
	}

	
//==========plan_list.jsp 页面==============	
	
	/**
	 * 当前月 
	*/
	private String currMonth;
	
	/**
	 * plan_list页面用到的monthPlanList
	 */
	private List<Plan>  monthPlanList;

	public String getCurrMonth() {
		return currMonth;
	}

	public void setCurrMonth(String currMonth) {
		this.currMonth = currMonth;
	}

	public List<Plan> getMonthPlanList() {
		return monthPlanList;
	}

	public void setMonthPlanList(List<Plan> monthPlanList) {
		this.monthPlanList = monthPlanList;
	}

	private void getPlanList() throws ParseException{
 		try{
 			
 			QueryPlanCondition queryPlanCondition = getQueryPlanCondition();
 			queryPlanCondition.setCusId(getLoginUserId());
 			queryPlanCondition.setPageSize(15);
 			setPage(planService.getPlanPaperByCondition(getQueryPlanCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(15);
			setPageUrlParms();
			
			currMonth = DateUtil.formatDate(new java.util.Date()).substring(0, 7);
			QueryPlanCondition queryPlanCondition2 = getQueryPlanCondition();
			queryPlanCondition2.setCalendar(currMonth);
			queryPlanCondition2.setCusId(getLoginUserId());
			monthPlanList = planService.getPlanListByCalendar(queryPlanCondition2);
 			
 		}catch(Exception e){
 			logger.error("getPlanList()方法出错了！",e);
 		}
 	}
	
	//得到日期下的学习计划list和今日提醒
	private String getCurrentDayPlanListMsg() throws ParseException {
		try{
			//getPlanList();
			QueryPlanCondition queryPlanCondition = getQueryPlanCondition();
 			queryPlanCondition.setCusId(getLoginUserId());
 			queryPlanCondition.setPageSize(15);
 			setPage(planService.getPlanPaperByCondition(getQueryPlanCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(15);
			setPageUrlParms();
			
			currMonth = DateUtil.formatDate(new java.util.Date()).substring(0, 7);
			QueryPlanCondition queryPlanCondition2 = getQueryPlanCondition();
			queryPlanCondition2.setCalendar(currMonth);
			queryPlanCondition2.setCusId(getLoginUserId());
			monthPlanList = planService.getPlanListByCalendar(queryPlanCondition2);
			
		getCurrenPlanclockByDate(currMonth+"-01");
		getCurrenExamSummaryByDate(currMonth+"-01");
		getCourseSummaryListBySummarydate(currMonth+"-01");
		
		return "getCurrentDayPlanListMsg";
		
		}catch(Exception e){
			logger.error("getCurrentDayPlanListMsg()方法出错了！",e);
			return "error";
		}
		
	}
	
	public String getPlanListByCalendardList() {
		try {
			getListPlanByCalendard();
			getCurrentDayPlanListMsg();
			
			return "getPlanListByCalendardList";
			
		} catch (ParseException e) {
			logger.error("getPlanListByCalendardList()方法出错了！",e);
			return "error";
		}
	}
	
	//plan_list.jsp 切换月份
	public String getPlanListByCalendardChangMonth() {
		try{
			queryPlanCondition = getQueryPlanCondition();
			// 如果opendate 的值为空，那么取当天的值，如 2011-5-25 00:00:00
			if (queryPlanCondition.getCalendar() == null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
	
				String tmpSetCalendar = "";
				int _tempYear = this.year;
				int _tempMonth = this.month;
	
				// 如果的当前月，即index=0，直接new一个Date对象
				if (next == 0 && prev == 0) {
					String tempCheck = this.checkDay;
					tempCheck = tempCheck.substring(0, 7);
					tmpSetCalendar = sdf.format(new Date());
	
					if (!tempCheck.equals(tmpSetCalendar)) {
						tmpSetCalendar = tempCheck;
					}
				} else {
					// 如果月增大
					if (next > 0) {
						if (_tempMonth == 12) {
							_tempYear++;
							_tempMonth = 1;
						} else
							_tempMonth++;
					}
					// 如果月减小
					else if (prev < 0) {
						if (_tempMonth == 1) {
							_tempYear--;
							_tempMonth = 12;
						} else
							_tempMonth--;
					}
					// 对日期进行处理
					try {
						tmpSetCalendar = sdf.format(sdf.parse(_tempYear + "-" + _tempMonth));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				// 设置属性，例如 2011-05
				queryPlanCondition.setCalendar(tmpSetCalendar);
				// 设置用户ID
				queryPlanCondition.setCusId(getLoginUserId());
	
				// 查询到所有当月的学习计划
				listPlan = planService.getPlanListByCalendar(queryPlanCondition);
				List<String> tempDatePlan = new ArrayList<String>();
				if (listPlan.size() > 0) {
					try {
						for (int i = 0; i<listPlan.size(); i++) {
							Plan tPlan = listPlan.get(i);
							tempDatePlan.add(new SimpleDateFormat("d").format(tPlan.getOpendate()));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	
				// 学习计划和日历进行匹配
				Map<String, String> map = getCalendar(tmpSetCalendar, tempDatePlan);
	
				// 转换成字符串
				calendarBuffer = map.get("calendar");
				titleCalendar = map.get("title");
			}
			
			String  firtDay ;
			if(month<=9){
				firtDay = year+"-0"+month+"-01";
			}else{
				firtDay = year+"-"+month+"-01";
			}
			
			//getCurrentPlanByDate(firtDay);
			try {
				getPlanList();
				getCurrenPlanclockByDate(firtDay);
				getCurrenExamSummaryByDate(firtDay);
				getCourseSummaryListBySummarydate(firtDay);
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
			
			return "getPlanListByCalendardChangMonth";
		
		}catch(Exception e){
			logger.error("getPlanListByCalendardChangMonth()方法出错了！",e);
			return "error";
		}
	}
	
	/** log对象 */
	private Log logger = LogFactory.getLog(getClass());

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}
	


}
