package com.shangde.edu.feed.mail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.common.util.DESCoder;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.service.ITaskLog;
import com.shangde.edu.feed.utils.MyThread;
import com.shangde.edu.feed.utils.PropertiesUtil;
import com.shangde.edu.feed.utils.Utils;

/**
 * 邮件发送执行类
 * 
 * @author Basil
 * 
 */
public class EmailExecutor extends MyThread {

	private static Logger logger = LoggerFactory.getLogger(EmailExecutor.class);
	private String addr;
	private ITaskLog taskLogService;
	private MimeMailService mimeMailService;
	private IMicroVideo microVideoService;
	private Integer taskId;
	private Integer videoId;
	private String templateContent;//模板内容
	
	private static List<MyThread> threads = new ArrayList<MyThread>();
	private static ExecutorService service = Executors.newFixedThreadPool(MyThread.getMaxThread());
	static {
		int tempMaxThread = MyThread.getMaxThread() + 1;
		for (int i = 0; i < tempMaxThread ; i++) {
			threads.add(new EmailExecutor());
		}
	}
	

	private EmailInfo email;

	public void runCode_() throws Exception {
		System.out.println("测试时使用，不发送任何任务!");
	}
	
	public void runCode() throws Exception {
		logger.info("当前邮件地址：{}", addr);
		String hostName = PropertiesUtil.getEntryValue("email.host", "email.properties");
		String popHost = PropertiesUtil.getEntryValue("email.pop","email.properties");
		String smtpPort = PropertiesUtil.getEntryValue("email.port","email.properties");
		String user = PropertiesUtil.getEntryValue("email.user","email.properties");
		String password = PropertiesUtil.getEntryValue("email.password", "email.properties");
		String from = PropertiesUtil.getEntryValue("email.from", "email.properties");
		String fromUser = PropertiesUtil.getEntryValue("email.from.user", "email.properties");
		String actionUrl = PropertiesUtil.getEntryValue("email.action","email.properties");

		Map<String, Object> context = new HashMap<String, Object>();
		/**
		 * actionUrl		邮件激活请求地址,如在如下参数
		 * @param taskId	任务id
		 * @param e			邮件名称加密格式
		 * @param videoId		视频id
		 * @param fromId	来源值 3=邮件标示
		 */
		String modelActionUrl = actionUrl + "?taskId=" + email.getMark() + "&e=" + DESCoder.encrypt(addr) + "&videoId=" + videoId + "&fromId=3";
		String videoName = microVideoService.getVideoAddressById(videoId).getName();
		//System.out.println(StringEscapeUtils.unescapeHtml(email.getContent()));
		String newContent = StringEscapeUtils.unescapeHtml(email.getContent());
		context.put("title",videoName);
		context.put("content", newContent);	
		context.put("link", modelActionUrl);
		
		
		HtmlEmail htmlMail = new HtmlEmail();
		htmlMail.setHostName(hostName); // 设定smtp服务器
		// email1.setSSL(Boolean.TRUE); // 设定是否使用SSL
		// email1.setSslSmtpPort("465"); // 设定SSL端口
		
/*		 * if (popHost != null && !popHost.equals("")) {
		 * htmlMail.setPopBeforeSmtp(true, popHost, user, password); }*/
		 
		// email1.setSSL(Boolean.TRUE); // 设定是否使用SSL
		// email1.setSslSmtpPort("465"); // 设定SSL端口
		// htmlMail.setPopBeforeSmtp(false, popHost, user, password);
		htmlMail.setSmtpPort(Integer.parseInt(smtpPort));
		htmlMail.setAuthentication(user, password); // 设定smtp服务器的认证资料信息

		htmlMail.addTo(addr);
		htmlMail.setFrom(from, fromUser);
		htmlMail.setSubject(email.getSubject());
		htmlMail.setCharset("UTF-8");
		/*String url = "<h3 style='line-height:30px; font-size:18px; text-align:center; color:#0000FF; padding-top:55px;'><strong><a href='" 
			+ actionUrl + "?taskId=" + email.getMark() + "&e=" + DESCoder.encrypt(addr) + "'>" + htmlMail.getSubject() + "</a></strong></h3>";*/
		
		//htmlMail.addPart(mimeMailService.generateContent(context),"text/html;charset=utf-8");
		htmlMail.addPart(Utils.getTemplateContent(templateContent, videoName, newContent,modelActionUrl),"text/html;charset=utf-8");
		//htmlMail.setHtmlMsg(mimeMailService.generateContent(context));
		htmlMail.send();
		// }
	}

	public void setArgs(Object... args) {
		if (args[0] != null && args[1] != null) {
			this.email = (EmailInfo) args[0];
			this.addr = args[1].toString();
			this.taskLogService = (ITaskLog) args[2];
			this.taskId = Integer.parseInt(args[3].toString());
			this.mimeMailService = (MimeMailService)args[4];
			this.microVideoService = (IMicroVideo)args[5];
			this.videoId = Integer.parseInt(args[6].toString().split(",")[0]);
			this.templateContent = args[7].toString();//模板内容
			
		} else {
			this.email = new EmailInfo();

		}
	}

	public static void send(EmailInfo email, String adr, ITaskLog taskLogService, Integer taskId,MimeMailService mimeMailService,IMicroVideo microVideoService,String videoId,String templateContent) {
		logger.info("邮件发送执行中...");
		MyThread my = MyThread.getMyThread(threads, email, adr, taskLogService, taskId,mimeMailService,microVideoService,videoId,templateContent);
		service.execute(my);
	}
	
	public void setSuccess() {
		try{
			taskLogService.updateTaskLogSendCount(this.taskId);
		}catch (Exception e) {
			logger.error("任务发送成功次数修改失败",e);
		}
		/**含义替换，如下代码不在使用
		if (email.getMark() != null && !email.getMark().equals("")) {
			taskLogService.updateTaskLogSendNum(Integer.parseInt(email.getMark()));
		}*/
	}
	public void setError() {
		try{
			taskLogService.updateTaskLogSendErrorCount(this.taskId);
		}catch (Exception e) {
			logger.error("任务发送失败次数修改失败",e);
		}
	}
	
	// ~Test
	/*public static void main(String[] args) {
		EmailInfo email = new EmailInfo();
		email.setContent("这是测试邮件");
		email.setAddr("liubaisheng@sunland.org.cn");
		
		email.setSubject("这是测试邮件标题");
		email.setMark("1000");

		try {
			int count = 0;
			for (String addr : email.getAddress()) {
				EmailExecutor.send(email, "liubaisheng@sunland.org.cn", null, 1);
				Long delay = PropertiesUtil.getLongEntryValue("email.properties", "email.delay");
				if (delay != null) {
					ThreadUtils.sleep(delay);
				} else {
					ThreadUtils.sleep(8000L);
				}
				System.out.println(addr);
				count++;
				System.out.println("计数" + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}
