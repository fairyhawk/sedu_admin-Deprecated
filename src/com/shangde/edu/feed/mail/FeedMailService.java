package com.shangde.edu.feed.mail;

import java.io.IOException;

import javax.mail.MessagingException;

/**
 * 消息订阅服务
 * 
 * @author Basil
 *
 */
public interface FeedMailService {
	
	// private MailSender mailSender;
	// private SimpleMailMessage mailMessage; // 邮件模板

	/**
	 * 邮件发送
	 */
	public void sendMail(EmailInfo email) throws MessagingException, IOException;

	/**
	 * 异步邮件发送
	 * 
	 * @param email
	 */
	public void sendMailByAsynchronousMode(EmailInfo email);
	
	/**
	 * 同步邮件发送
	 * 
	 * @param email
	 * @throws MessagingException
	 * @throws IOException
	 */
	public void sendMailBySynchronizationMode(EmailInfo email) throws MessagingException, IOException;

}
