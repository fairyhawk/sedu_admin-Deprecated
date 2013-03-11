package com.shangde.edu.feed.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import au.com.bytecode.opencsv.CSVWriter;
import cn.highso.core.utils.template.VelocityUtils;

/**
 * 工具包
 * 
 * @author Libg
 * @author ...
 *
 */
public class Utils {

	/**
	 * 根据http请求url取得io流
	 * 
	 * @param urlAction
	 * @return InputStream
	 * 
	 * @author Libg
	 * 
	 */
	public static InputStream getInputStream(String urlAction) {

		URL url = null;
		HttpURLConnection httpUrlConnection = null;
		InputStream inputStream = null;
		try {
			url = new URL(urlAction);
			httpUrlConnection = (HttpURLConnection) url.openConnection();

			if (httpUrlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpUrlConnection.getInputStream();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * 根据io流读取文本信息
	 * 
	 * @param is
	 *            InputStream对象
	 * @param charEncoding
	 *            编码 例如：UTF-8,gb2312
	 * 
	 * @author Libg
	 * 
	 * @return
	 */
	public static String getFileContent(InputStream is, String charEncoding) {
		StringBuffer content = new StringBuffer();
		BufferedReader buffReader = null;
		try {

			Reader reader = new InputStreamReader(is, charEncoding);
			buffReader = new BufferedReader(reader);
			String line;
			while ((line = buffReader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffReader != null) {
				try {
					buffReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content.toString();
	}

	/**
	 * 时间转换 yyyy-MM-dd HH:mm:ss 转换成 ss mm HH dd MM ? yyyy
	 * 
	 * @param time
	 *            yyyy-MM-dd HH:mm:ss
	 * 
	 * @author Libg
	 * 
	 * @return
	 */
	public static String getCronExpression(String time) {

		StringBuffer cronExpression = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cl = sdf.getCalendar();
		try {

			cl.setTime(sdf.parse(time));

			cronExpression.append(cl.get(Calendar.SECOND)).append(" ");
			cronExpression.append(cl.get(Calendar.MINUTE)).append(" ");
			cronExpression.append(cl.get(Calendar.HOUR_OF_DAY)).append(" ");
			cronExpression.append(cl.get(Calendar.DAY_OF_MONTH)).append(" ");
			cronExpression.append(cl.get(Calendar.MONTH) + 1).append(" ");
			cronExpression.append("?").append(" ");
			cronExpression.append(cl.get(Calendar.YEAR));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cronExpression.toString();
	}

	/**
	 * 分钟++
	 * 
	 * @param time
	 *            yyyy-MM-dd HH:mm:ss
	 * @param minuteIncremental
	 *            分钟增量（表示将当前时间分钟增加数）
	 *
	 * @author Libg
	 *
	 * @return
	 */
	public static String getMinuteIncremental(String time, int minuteIncremental) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = null;
		try {
			sdf.parse(time);
			cal = sdf.getCalendar();
			cal.add(Calendar.MINUTE, minuteIncremental);// 分钟++
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdf.format(cal.getTime());
	}

	/**
	 * 百分比计算并保留n位小数
	 * 
	 * @param num1
	 * @param num2
	 * 
	 * @author Libg
	 * 
	 * @return
	 */
	public static String percentage(double num1, double num2, int n) {

		int param = 10;
		for (int i = 0; i < n; i++) {
			param *= 10;
		}
		double result = ((double) Math.round(num1 / num2 * 100 * param)) / param;

		return String.valueOf(result);
	}
	
	/**
	 * 创建一个csv扩展名的文件：二维数组中的每一个行列(注意： (title)头部有几列，(body)二维数组行列就有多少列)
	 * @param title csv内容头部信息
	 * @param body csv内容信息
	 * @param fileURL  csv保存的物理位置
	 * @throws Exception
	 * 
	 * @author Libg
	 * 
	 */
	public static void writeCSV(String[] title, String[][] body,String fileURL) throws Exception {

		//String[] title = new String[] { "姓名", "密码" };
		//String[][] body = new String[][] { { "张三", "zhangsan" },{ "李四", "lisi" }, { "王五", "wangwu" } };
		if(title == null || body == null){
			throw new Exception("空指针错误");
		}
		OutputStreamWriter out = null; 
		File file = null;
		CSVWriter writer = null;
		try {
			/**创建文件物理位置*/
			//file = new File(fileURL);
			out = new OutputStreamWriter(new FileOutputStream(new File(fileURL)), Charset.forName("GBK"));
			/**取得物理文件对象*/
			//writer = new CSVWriter(new FileWriter(file));
			writer = new CSVWriter(out);
			/**CSVWriter API 函数 writeNext 输出一个数组 = 头部*/
			writer.writeNext(title);
			/**CSVWriter API 函数 writeNext 这里进行迭代二维数组中的每一个行列
                             (注意： 头部有几列，二维数组行列就有多少列)
                        */
			for (int i = 0; i < body.length; i++) {
				writer.writeNext(body[i]);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	/**
	 * 数字从1-10范围内小写转大写,后期多的情况下，实现算法需要改变...
	 * 
	 * @param num
	 * @return
	 */
	public static String changeToBig(int num){
		
		String result = null;
		
		switch (num) {
			case 1:
				result = "一";
			break;
			case 2:
				result = "二";
			break;
			case 3:
				result = "三";
			break;
			case 4:
				result = "四";
			break;
			case 5:
				result = "五";
			break;
			case 6:
				result = "六";
			break;
			case 7:
				result = "七";
			break;
			case 8:
				result = "八";
			break;
			case 9:
				result = "九";
			break;
			case 10:
				result = "十";
			break;
			default:
				result = "零";
			break;
		}
		
		return result;
	}
	
	/**
	 * 获取客户IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}
	
	/**
	 * 获取浏览器head中 user-agent 数据
	 * @param request
	 * @return
	 */
	public static String getHeadUserAgent(HttpServletRequest request){
		return request.getHeader("user-agent");
	}
	
	/**
	 * 设置模板中微学习视频路径
	 * 
	 * @param content
	 *            模板内容
	 * @param link
	 *            视频路径
	 * @return
	 */
	public static String getTemplateContent(String templateContent,
			String title, String content, String link) {

		try {
			VelocityEngine ve = new VelocityEngine();
			Properties properties = new Properties();
			properties.put("resource.loader", "srl");
			properties.put("srl.resource.loader.class",
					"com.shangde.edu.feed.utils.template.TemplateRourceLoader");
			properties.put("default.contentType", "text/html; charset=UTF-8");
			properties.put("input.encoding", "UTF-8");
			properties.put("output.encoding", "UTF-8");
			ve.init(properties);
			Template emailContent = ve.getTemplate(templateContent);
			VelocityContext context = new VelocityContext();
			if (title != null) {
				context.put("title", title);
			}
			if (content != null) {
				context.put("content", content);
			}
			if (link != null) {
				context.put("link", link);
			}
			StringWriter writer = new StringWriter();
			emailContent.merge(context, writer);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getTemplateContent(String templateContent,
			String title, String content) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("title", title);
		map.put("content", content);

		return VelocityUtils.renderTemplateContent(templateContent, map);
	}
	
	/**
	 * 倒计时
	 * 
	 * @param ms
	 *            毫秒
	 * @param format
	 *            展示的效果格式 例如： yyyy年MM月dd号
	 * @return
	 */
	public static String countDown(long ms, String format) {
		int year = 0, month = 0, date = 0, hourOfDay = 0, minute = 0, second = 0;

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(ms);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		date = c.get(Calendar.DAY_OF_MONTH);
		hourOfDay = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(year, month, date, hourOfDay, minute, second);// 设置结束时间
		long time = cal.getTime().getTime();
		long days = 0l, days_ = 0l;
		long hours = 0l, hours_ = 0l;
		long mins = 0l, mins_ = 0l;
		long secs = 0l;
		Date now = null;
		String result = null;
		try {
			// 如果结束毫秒数 大于 当前系统毫秒数，说明没有结束
			if (time > System.currentTimeMillis()) {
				now = new Date();
				days = (time - now.getTime()) / (24 * 60 * 60 * 1000);
				days_ = (time - now.getTime()) % (24 * 60 * 60 * 1000);
				hours = days_ / (60 * 60 * 1000);
				hours_ = days_ % (60 * 60 * 1000);
				mins = hours_ / (60 * 1000);
				mins_ = hours_ % (60 * 1000);
				secs = mins_ / (1000);
				// 设置还有多少要到达的 时，分，秒
				cal.set(0, 0, 0, (int) hours, (int) mins, (int) (secs + 1));
				result = sdf.format(cal.getTime());
			} else {
				result = "已结束";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	


	/**
	 * 转移String类型
	 * 
	 * @param o
	 * @return
	 */
	public static String stringValueOf(Object o){
		return String.valueOf(o);
	}
	
	public static String formatMinutes(int second){
		DecimalFormat format = new DecimalFormat("#0.00");
		return format.format(second * 1.0 /60 * 1.0);
	}
}
