package com.shangde.edu.stu.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *<p> Description: </p>
 * Author: baiang.zhao <br>
 * Date: May 24, 2011 <br>
 * Time: 6:14:46 PM <br>
 */
public class PlanCalendar {

	// 日历的缓冲流
	private  StringBuffer calendarBuffer;//= new StringBuffer();

	// 日历的Title
	private StringBuffer titleCalendar;
	
	public void getYearMonth(int index) {
		int leng = 0;
		/*这里的i初始值，你可以根据自己的需要来改变，也可以变成变量*/
		Calendar calendar = Calendar.getInstance();
		/*
		 * i是正数就是后i月，i是负数就是前i月，i是几就加几月，这里的例子是前6个月
		 * */
		calendar.add(Calendar.DATE, index); //得到某一天 
		calendar.add(Calendar.MONTH, index); //得到某一个月 

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;//这里月要加1s

		/*下面的是根据我自己需要用到的日期格式加的一些小处理*/
		String dateTime = year + "-" + month;
		// 日历Title赋值
		//titleCalendar = dateTime;

		System.out.println("-------" + dateTime + "-------");
		getDayCount(dateTime);
		System.out.println();
		leng++;
	}

	/*给一个yyyy-MM(2009-11)日期格式，判断出所传月一共多少天*/
	public int getDayCount(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		/*GregorianCalendar 是 Calendar 的一个具体子类，
		 * 提供了世界上大多数国家/地区使用的标准日历系统。*/
		Calendar calendar = new GregorianCalendar();
		int currentToday = calendar.get(Calendar.DAY_OF_MONTH);
		int currentMonth = calendar.get(Calendar.MONTH);
		try {
			/*使用给定的 Date 设置此 Calendar 的时间*/
			calendar.setTime(sdf.parse(dateTime));

			// 遍历得到所有日期
			int month = calendar.get(Calendar.MONTH);
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			// 打印标题
			System.out.println("sun mon tue wed thu fri sat");
			for (int i = Calendar.SUNDAY; i < weekday; i++) {
				System.out.print("    ");
			}
			do {
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				System.out.printf("%3d", day);

				if (month == currentMonth) {
					if (day == currentToday) {
						System.out.print("*");
					} else
						System.out.print(" ");
				} else {
					System.out.print(" ");
				}

				if (weekday == Calendar.SATURDAY) {
					System.out.println();
				}

				calendar.add(Calendar.DAY_OF_MONTH, 1);
				weekday = calendar.get(Calendar.DAY_OF_WEEK);
			} while (calendar.get(Calendar.MONTH) == month);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		/*返回此日历字段可能具有的最大值。DAY_OF_MONTH 用于指示一个月的某天*/
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}

	public void main(String[] args) throws ParseException {
		calendarBuffer = new StringBuffer();
		calendarBuffer.append("<tr>");
		calendarBuffer.append("<td>").append("").append("</td>");
		getYearMonth(-1);
	}

	public StringBuffer getCalendarBuffer() {
		return calendarBuffer;
	}

	public void setCalendarBuffer(StringBuffer calendarBuffer) {
		this.calendarBuffer = calendarBuffer;
	}

	public StringBuffer getTitleCalendar() {
		return titleCalendar;
	}

	public void setTitleCalendar(StringBuffer titleCalendar) {
		this.titleCalendar = titleCalendar;
	}

	

}
