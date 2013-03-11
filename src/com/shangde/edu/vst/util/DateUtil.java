package com.shangde.edu.vst.util;



/**
 * 全局字符串、参数获取工具类
 * 
 * @author Administrator
 */
public class DateUtil
{
	public static String getTimeLength(int minus)
	{
		StringBuffer sb = new StringBuffer();
		if(minus>0)
		{
			if(minus/60>0)
				sb.append(minus/60).append("小时").append(minus%60).append("分");
			else
				sb.append(minus).append("分");
		}
		else 
			sb.append("0分");
		return sb.toString();
	}
	
	public static String getTimeLengthNum(int second)
	{
		StringBuffer sb = new StringBuffer();
		if(second>0)
		{
			if(second/60>0)
			{
				int minute = second/60;
				
				if(minute>9)
					sb.append(minute);
				else 
					sb.append("0").append(minute);			
			}
			else
				sb.append("00");
			second = second%60;
			sb.append(":");
			if(second>9)
				sb.append(second);
			else 
				sb.append("0").append(second);
		}
		else 
			sb.append("00:00");
		return sb.toString();
	}
}
	

