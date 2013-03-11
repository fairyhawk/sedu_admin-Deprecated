/**
 * 
 */
package com.shangde.common.util;

/**
 *
 * <p>Title: 尚德管理系统</p>
 * <p>Description:<b>日期处理公用类\uFFFD/b>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ZTEsoft</p>
 * @author guoqiang.liu
 * @version 1.0
 */

import java.text.SimpleDateFormat;

import java.util.*;

import java.text.ParseException;

public class DateUtil {

	public static String DATE_FMT = "yyyy-MM-dd";
	public static String TIME_FMT = "yyyy-MM-dd HH:mm:ss";

	public SimpleDateFormat dfForm = null;
	/**
	 * 
	 * @param sDate
	 * @param sFmtFrom
	 * @param sFmtTo
	 * @return
	 * @throws ParseException
	 */
	public static String formatDate(String sDate, String sFmtFrom, String sFmtTo) throws ParseException {

		SimpleDateFormat sdfFrom = new SimpleDateFormat(sFmtFrom);

		SimpleDateFormat sdfTo = new SimpleDateFormat(sFmtTo);

		return sdfTo.format(sdfFrom.parse(sDate));

	}
	/**
	 * 
	 * @param dt
	 * @param sFmt
	 * @return
	 */
	public static String formatDate(java.util.Date dt, String sFmt) {

		if (dt == null || sFmt == null)

			return "";

		SimpleDateFormat sdfFrom = new SimpleDateFormat(sFmt);

		return sdfFrom.format(dt).toString();

	}
	/**
	 * 
	 * @param dt
	 * @return
	 */
	public static String formatDate(java.util.Date dt) {

		return formatDate(dt, DATE_FMT);

	}
	/**
	 * 
	 * @param sDate
	 * @param sFmt
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date toSqlDate(String sDate, String sFmt) throws

	ParseException {

		String tmpDate = formatDate(sDate, sFmt, DATE_FMT);

		return java.sql.Date.valueOf(tmpDate);

	}

	//sDate 的格式必须和sFmt一样 如 2001.6.1  yyyy.MM.DD

	public static java.util.Date toDate(String sDate, String sFmt) throws ParseException {

		return new SimpleDateFormat(sFmt).parse(sDate);

	}
	/**
	 *增加天数，日期格式为yyyymmdd
	 * @param sDate
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String addDays(String sDate, int days) throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(DateUtil.toDate(sDate, "yyyyMMdd"));

		calendar.add(Calendar.DATE, days);

		return DateUtil.formatDate(calendar.getTime(), "yyyyMMdd");

	}
	/**
	 * 增加天数，日期格式yyyy-mm-dd
	 * @param sDate
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static String addDays2(String sDate, int days) throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(DateUtil.toDate(sDate, "yyyy-MM-dd"));

		calendar.add(Calendar.DAY_OF_MONTH, days);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

	}


	/**
	 *日期加月
	 */
	public static String addMonth(String sDate, int months) throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(DateUtil.toDate(sDate, "yyyy-MM-dd hh:mm:ss"));

		//calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + months);
		calendar.add(calendar.MONTH, months);
		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd hh:mm:ss");

	}

	public static String addMonth1(String sDate, int months) throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(DateUtil.toDate(sDate, "yyyy-MM-dd"));
		calendar.add(calendar.MONTH, months);
		// calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + months);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

	}

	/**
	 * 得到两个时间相差的天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDays(java.util.Date date1, java.util.Date date2) {

		//date2应大于date1

		int days = 0;

		days = (int) ( (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000));

		return days;

	}

	/**
	 * 得到两个时间相差的小时数
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static int getHours(java.util.Date date1, java.util.Date date2) {

		//date2应大于date1

		int hours = 0;

		hours = (int) ( (date2.getTime() - date1.getTime()) / (60 * 60 * 1000));

		return hours;

	}

	/**
	 * 得到两个时间相差的分钟数
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static int getMin(java.util.Date date1, java.util.Date date2) {

		//date2应大于date1

		int min = 0;

		min = (int) ( (date2.getTime() - date1.getTime()) / (60 * 1000));

		return min;

	}

	public static int getMin(String dtStr1, String dtStr2) {

		Date dt1 = null;
		Date dt2 = null;
		int a = 0;
		try {
			dt1 = toDate(dtStr1, TIME_FMT);
			dt2 = toDate(dtStr2, TIME_FMT);
			a = getMin(dt1, dt2);
		}
		catch (ParseException ex) {
		}
		return a;

	}

	/**
	 * 生成每月一号的日期，如2005-11-01
	 * @return String
	 */

	public static String getCurrentMonthDate() {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DATE, 1);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

	}

	/**
	 * 生成每月一号的日期，如2005-11-01
	 * @return String
	 */

	public static String getCurrentMonth() {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DATE, 1);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM");

	}

	/**
	 * 得到当月的月份
	 * @return String
	 */

	public static String getCurrentMonth1() {

		Calendar calendar = Calendar.getInstance();

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM");

	}

	/**
	 * 根据给定参数生成本月1号的日期
	 * @param date
	 * @return
	 */
	public static String getCurrentMonthDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

	}
	/**
	 * 取月份
	 * @return String
	 */

	public static String getMonth(String date) {

		String[] date_array = StringUtil.seperates(date, "-");

		return date_array[1];

	}
	/**
	 * 取日期
	 * @return String
	 */

	public static String getDay(String date) {

		String[] date_array = StringUtil.seperates(date, "-");

		return date_array[2];
	}
	/**
	 * 取年
	 * @return String
	 */

	public static String getYear(String date) {

		String[] date_array = StringUtil.seperates(date, "-");

		return date_array[0];
	}
	/*
	 * 得到当前时间
	 */
	public static Date getNow()
	{
		Date now = new Date();
		return now;
	}
	/**
	 * 生成下月一号的日期，如本日是2005-11-21，则结果为 2005-12-01
	 * @return String
	 */

	public static String getNextMonthDate() {

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + 1);

		calendar.set(Calendar.DATE, 1);

		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd");

	}

	/**
	 * 根据当前给的日期，得到下月一号的日期。例如：传入2005-11-21 ，怎结果是 2005-12-1
	 * @param curDate
	 * @return
	 */

	public static String getNextMonthDate(String curDate) {

		String[] date_array = StringUtil.seperates(curDate, "-");

		Date dt = new Date(Integer.parseInt(date_array[0]) - 1900, Integer.parseInt(date_array[1]) - 1, 1);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dt);

		System.out.println(formatDate(calendar.getTime(), "yyyy-MM-dd"));

		calendar.set(Calendar.MONTH, calendar.getTime().getMonth() + 1);

		calendar.set(Calendar.DATE, 1);

		return formatDate(calendar.getTime(), "yyyy-MM-dd");

	}
	/**
	 * 根据当前给的日期，得到上月一号的日期。例如：传入2005-11-21 ，怎结果是 2005-10-1
	 * @param curDate
	 * @return
	 */

	public static String getLastMonthDate(String curDate) {

		String[] date_array = StringUtil.seperates(curDate, "-");

		Date dt = new Date(Integer.parseInt(date_array[0]) - 1900, Integer.parseInt(date_array[1]) - 1, 1);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dt);

		System.out.println(formatDate(calendar.getTime(), "yyyy-MM-dd"));

		calendar.set(Calendar.MONTH, calendar.getTime().getMonth() - 1);

		calendar.set(Calendar.DATE, 1);

		return formatDate(calendar.getTime(), "yyyy-MM-dd");

	}
	/**
	 * 判断两个日期是否在同一年的同一个月
	 * @param dtStr1
	 * @param dtStr2
	 * @return true -是同年同月   false -不是同年同月
	 */

	public static boolean compareYearAndMonth(String dtStr1, String dtStr2) {

		boolean flag = false;
		//  正式系统的判断逻辑，在上线的时候要去掉解开
		String[] dtstr_array1 = StringUtil.seperates(dtStr1, "-");
		String[] dtstr_array2 = StringUtil.seperates(dtStr2, "-");
		if ( (dtstr_array1[0].equals(dtstr_array2[0])) &&
				( (dtstr_array1[1].equals(dtstr_array2[1]))))
			flag = true;

		return flag;

	}

	/**
	 * 得到当前时间
	 * @param args
	 */
	public static String getCurrentTime() {
		Date dt = new Date();
		return formatDate(dt, TIME_FMT);

	}

	/**
	 * 得到当前日期
	 * @param args
	 */
	public static String getCurrentDate() {
		Date dt = new Date();
		return formatDate(dt, DATE_FMT);

	}


	/**
	 * 得到没有固定日期的sleep时间
	 * @return
	 */
	public static  long getMonthSleeps(int day) {
		try {
			//得到现在的时间

			Date date1 = new Date();
			Calendar calendarnow = Calendar.getInstance();
			//得到下个月一号0点
			Date date2 = new Date();

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.MONTH, 1);
			calendar.set(calendar.get(Calendar.YEAR),
					(calendar.get(Calendar.MONTH))
					, day, 0, 0, 0);
			//计算毫秒数
			System.out.println(calendar.get(Calendar.YEAR)+"年"+
					(calendar.get(Calendar.MONTH))+"月"+
			"1");
			long millingSecond = calendar.getTime().getTime() - date1.getTime();
			System.out.println("毫秒差："+millingSecond);
			return millingSecond;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}



	/**
	 * 得到每天hour点0分的sleep时间
	 * @return
	 */
	public static  long getDaySleeps(int hour) {
		try {
			//得到现在的时间

			Date date1 = new Date();
			Calendar calendarnow = Calendar.getInstance();
			//得到下个月一号0点
			Date date2 = new Date();

			Calendar calendar = Calendar.getInstance();
			System.out.println("now calendar is:"+calendar.getTime().toLocaleString());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			calendar.set(calendar.get(Calendar.YEAR),
					(calendar.get(Calendar.MONTH))
					, calendar.get(Calendar.DAY_OF_MONTH), hour, 0, 0);
			//计算毫秒数
			System.out.println("after  calendar is:"+calendar.getTime().toLocaleString());

			long millingSecond = calendar.getTime().getTime() - date1.getTime();
			System.out.println("毫秒差："+millingSecond);
			return millingSecond;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}

	/**
	 * 比较两个日期的大小
	 * @return  <0 - 小于， 0 - 等于， >0 - 大于
	 */
	public static int compareDate(String str1,String str2){
		int i= 0;
		try {
			Date dt1 = toDate(str1, DATE_FMT);
			Date dt2 = toDate(str2, DATE_FMT);
			i = dt1.compareTo(dt2);
		}
		catch (ParseException ex) {
			ex.printStackTrace();
		}
		return i;
	}

	/**
	 * 比较两个日期的大小
	 * @return  <0 - 小于， 0 - 等于， >0 - 大于
	 */
	public static int compareDate(Date dt1,Date dt2){
		int i= 0;
		try {
			i = dt1.compareTo(dt2);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return i;
	}
	public static long compareYearMonth(Date dt1,Date dt2){
		long i=0;
		try{
			String dStr1 = formatDate(dt1,"yyyy-MM");
			String dStr2 = formatDate(dt2,"yyyy-MM");
			long year1 = Long.valueOf(getYear(dStr1));
			long year2 = Long.valueOf(getYear(dStr2));

			long day1 = Long.valueOf(getMonth(dStr1));
			long day2 = Long.valueOf(getMonth(dStr2));
			i = (year1 - year2) *12 + (day1 - day2);
		}catch (Exception e){
			e.printStackTrace();

		}
		return i;
	}
	/**
	 * 得到两个日期的月份差
	 * @param d1
	 * @param d2
	 * @return
	 * @throws Exception
	 */
	public static int compareMonth(Date d1,Date d2) throws Exception 
	{

		try {
			String dStr1 = formatDate(d1);
			String dStr2 = formatDate(d2);
			int month1 = Integer.parseInt(getMonth(dStr1));
			int month2 = Integer.parseInt(getMonth(dStr2));
			int result = month1 - month2;
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("日起比较错误");

		}

	}
//	添加 天.小时.分钟
	public static String addTimes(String  sDate,int days,int hours,int minutes)  throws ParseException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.toDate(sDate, "yyyy-MM-dd HH:mm:ss"));
		calendar.add(Calendar.DATE, days);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		calendar.add(Calendar.MINUTE, minutes);
		return DateUtil.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 为优惠券序列号生成获取当前时间
	 * @return
	 */
	public static String getCouponTime() {
		String curTime = null;
		long createTime = new Date().getTime();
		curTime = "cp" + createTime;
		return curTime;
	}

	public static void main(String[] args) {
		String str1 = "2007-9-04 12:32:11";
		try{
			Date date = new Date();
			String date2 = DateUtil.addTimes("2000-01-01 00:00:00", 0, 32, 14);
			System.out.println(date2);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}