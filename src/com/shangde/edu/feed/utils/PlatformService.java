package com.shangde.edu.feed.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;

import org.apache.log4j.Logger;

import cn.highso.client.util.WebserviceUtil;
import cn.highso.core.mail.EmailInfo;
import cn.highso.core.protocol.TaskInfo;
import cn.highso.core.protocol.query.TaskCondition;
import cn.highso.core.webservice.hessian.IQueryService;

public class PlatformService {

	private static final Logger logger = Logger
			.getLogger(PlatformService.class);

	private static final String IP = "172.16.123.163";
	private static final String PORT = "8080";

	/**
	 * 查询接口地址
	 */
	private static final String QUERY_URL = "http://" + IP + ":" + PORT
			+ "/remoting/queryService";
	/**
	 * 执行接口地址
	 */
	private static final String EXECUTE_URL = "http://" + IP + ":" + PORT
			+ "/remoting/executeService";

	/**
	 * 查询某个任务某个时间段发出的数
	 * 
	 * @param taskId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getTaskDateSendNum(int taskId, String startTime,
			String endTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		IQueryService iQueryService = WebserviceUtil.instance
				.getQueryService(QUERY_URL);
		TaskCondition taskCondition = null;
		int num = 0;
		try {
			taskCondition = new TaskCondition();
			taskCondition.setTaskId(taskId);
			taskCondition.setStartTime(sdf.parse(startTime));
			taskCondition.setEndTime(sdf.parse(endTime));
			taskCondition.setCondition("time");

			num = Long.valueOf(iQueryService.stat("task", taskCondition))
					.intValue();// // 查询某一个任务，某个时间段中发出的数量
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 根据任务id获取任务发出{错误/成功}数据,根据参数condition决定查询的数据
	 * 
	 * @param taskId
	 *            任务id
	 * @param condition
	 *            success=成功数/error=失败数
	 * @return
	 */
	public static int getTaskSendConditionByTaskId(int taskId, String condition) {

		IQueryService iQueryService = null;
		TaskCondition taskCondition = null;
		int num = 0;
		try {
			iQueryService = WebserviceUtil.instance.getQueryService(QUERY_URL);
			taskCondition = new TaskCondition();
			taskCondition.setTaskId(taskId);
			taskCondition.setCondition(condition);// success=成功数/error=失败数
			num = Long.valueOf(iQueryService.stat("task", taskCondition))
					.intValue();
		} catch (Exception e) {
			logger.error("查询任务失败数接口{取得连接失败}-异常", e);
		}
		return num;
	}

	/**
	 * 发送邮件
	 * 
	 * @param taskId
	 * @param videoId
	 * @param sendTo
	 * @param title
	 * @param content
	 * @param sendTime
	 */
	public static void sendTask(int taskId, String videoId, String sendTo,
			String title, String taskContent, String templateContent,
			String sendTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		EmailInfo mail = new EmailInfo();

		StringBuffer url = new StringBuffer();
		StringBuffer content = new StringBuffer();
		try {

			// TODO:content，应该是模板+邮件动态内容组合最终内容
			try {
				content.append(Utils.getTemplateContent(templateContent, title,
						taskContent));
			} catch (Exception e) {
				logger.error("任务内容模板转换失败", e);
			}

			mail.setSubject(title);// 标题
			mail.setText(content.toString());// 内容
			mail.setTo(sendTo.split(","));// 用户人列表
			// url组合
			url.append(PropertiesUtil.getEntryValue("email.action",
					"email.properties"));
			/** 备注,$email,应该是加密后的值,加密处理交给底层平台完成 */
			url.append("?taskId=").append(taskId);
			url.append("&e=$email");
			url.append("&videoId=").append(videoId).append("&fromId=3");

			mail.setParams(Collections.singletonMap("link", url.toString()));// 底层接口要求格式

			TaskInfo taskInfo = new TaskInfo();
			taskInfo.setType(1);
			taskInfo.setData(mail);
			taskInfo.setStartTime(sdf.parse(sendTime));
			taskInfo.setMark(String.valueOf(taskId));

			WebserviceUtil.instance.getExecuteService(EXECUTE_URL).putTask(
					taskInfo);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		int taskId = 101;

		taskId = 1007;
		String videoId = "1";
		String sendTo = "libaogang@sunland.org.cn";
		String title = "测试-cs";
		String taskContent = "测试-cs-内容";
		String templateContent = "<html><head><title>测试" + "</title>"
				+ "</head>" + "<body>标题:-->$title" + "内容:-->$content"
				+ "连接:-->$link" + "</body>" + "</html>";
		String sendTime = "2011-11-22 13:24:00";

		// 发邮件测试
		// sendTask(taskId, videoId, sendTo, title, taskContent,
		// templateContent, sendTime);
		// 查询失败数
		System.out.println("发送失败数--->" + getTaskSendConditionByTaskId(taskId, "success"));
		// 查询发送数
		String startTime = "2011-11-22 00:00:00";
		String endTime = "2011-11-22 23:59:59";
		System.out.println("发送数--->"
				+ getTaskDateSendNum(taskId, startTime, endTime));

	}
}
