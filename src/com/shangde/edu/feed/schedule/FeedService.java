package com.shangde.edu.feed.schedule;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangde.edu.feed.domain.TaskList;
import com.shangde.edu.feed.domain.TaskSendLog;
import com.shangde.edu.feed.mail.EmailExecutor;
import com.shangde.edu.feed.mail.EmailInfo;
import com.shangde.edu.feed.mail.MimeMailService;
import com.shangde.edu.feed.service.IMicroVideo;
import com.shangde.edu.feed.service.ITaskLog;
import com.shangde.edu.feed.service.ITaskSendLog;
import com.shangde.edu.feed.service.ITemplate;
import com.shangde.edu.feed.utils.PropertiesUtil;
import com.shangde.edu.feed.utils.ThreadUtils;

/**
 * 订阅服务
 * 
 * @author Basil
 * 
 */
@Service("feedService")
public class FeedService implements Serializable {

	private static final long serialVersionUID = -122323253784533L;
	private static Logger logger = LoggerFactory.getLogger(FeedService.class);

	/**
	 * 邮件发送
	 * 
	 * @param triggerName
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void postEmail(String triggerName, TaskList task, ITaskLog taskLogService,ITaskSendLog taskSendLogService, MimeMailService mimeMailService, IMicroVideo microVideoService,ITemplate feedTempageService,String templateContent) throws MessagingException, IOException {
		logger.info("当前任务ID: " + task.getId());
	

		EmailInfo email = new EmailInfo();
		//TODO: 99=test user
		if (task.getSendObject() != 99)
		{
			email.setAddr(PropertiesUtil.getEntryValue("email.exlist", "email.properties") + "," + task.getUserData());
		}
		else
		{
			email.setAddr(task.getUserData());
		}
		email.setMark(String.valueOf(task.getId()));
		email.setSubject(task.getName());

		email.setContent(task.getContent());

		logger.info("Email sending...");

		if (email.getAddress().length > 0) {
			logger.info("邮件将要发送的数量: {}", email.getAddress().length);
			for (String addr : email.getAddress()) {
				// TODO: 多个视频上传显示名称
				EmailExecutor.send(email, addr, taskLogService, task.getId(), mimeMailService, microVideoService, task.getVideoId(),templateContent);
				
				//任务发送记录，[某个任务发送至某个人]
				taskSendLogService.addTaskSendLog(getTaskSendLog(task.getId(),addr));
				
				Long delay = PropertiesUtil.getLongEntryValue("email.delay", "email.properties");

				if (delay != null) {
					ThreadUtils.sleep(delay);
					logger.info("当前邮件发送间隔为: " + delay.toString() + "秒钟");
				} else {
					ThreadUtils.sleep(10000L);
					logger.info("未设置 email.delay 当前邮件发送间隔为: 10秒钟");
				}
			}
		}
	}
	
	/**
	 * 创建对象
	 * 
	 * @param email
	 * @param taskId
	 * @return
	 */
	private TaskSendLog getTaskSendLog(int taskId,String email){
		
		TaskSendLog taskSendLog = new TaskSendLog();
		taskSendLog.setTaskListId(taskId);
		taskSendLog.setEmail(email);
		taskSendLog.setPubDate(new Date());
		
		return taskSendLog;
	}

	public void postMessage(String triggerName) {
		logger.info("短信发送...");
	}

}
