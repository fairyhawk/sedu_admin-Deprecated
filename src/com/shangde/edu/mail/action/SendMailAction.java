package com.shangde.edu.mail.action;


import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;


/**
 * 邮件发送action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class SendMailAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SendMailAction.class);
	
	String SMTPHost=""; //SMTP服务器
	String POPHost=""; //SMTP服务器
	String user=""; //登录SMTP服务器的帐号
	String password=""; //登录SMTP服务器的密码
	 
	String from =""; //发件人邮箱
	String to =""; //收件人邮箱
	String subject =""; //邮件标题
	String content =""; //邮件内容
	 
//	//无参数构造方法
//	public SendHtmlMail() {}
//	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
//		try{			
//			//解决内容的中文问题
//			content = new String(content.getBytes("ISO8859-1"),"gb2312");
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}
		this.content = content;
	}

	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSMTPHost() {
		return SMTPHost;
	}
	public void setSMTPHost(String host) {
		SMTPHost = host;
	}
	
	public String getPOPHost() {
		return POPHost;
	}
	public void setPOPHost(String host) {
		POPHost = host;
	}

	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
//		try{			
//			//解决标题的中文问题
//			subject = new String(subject.getBytes("ISO8859-1"),"gb2312");
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}		
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	 

	//发送邮件
	public boolean send(){
		//创建一个属性对象
		Properties props = new Properties();
		//指定SMTP服务器
		props.put("mail.smtp.host", SMTPHost);
		//指定是否需要SMTP验证		
		props.put("mail.smtp.auth", "true");
		//指定pop3服务器		
		if(!POPHost.equals("")){
		props.put("mail.pop.host ", POPHost); 
		props.put("mail.pop.auth ", "true "); 
		}
		try{
			//创建一个授权验证对象
			SmtpAuth auth = new SmtpAuth();
			auth.setAccount(user,password);
			
			//创建一个Session对象
			Session mailSession = Session.getDefaultInstance(props,auth);
			//mailSession.setDebug(true);
			
			//创建一个MimeMessage 对象
			MimeMessage message=new MimeMessage(mailSession);

			//指定发件人邮箱
			message.setFrom(new InternetAddress(from));
			//指定收件人邮箱
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			//指定邮件主题
			message.setSubject(subject);
			//指定邮件内容、ContentType及编码方式
			message.setContent(content,"text/html;charset=gb2312");
			//指定邮件发送日期
			message.setSentDate(new Date());
			//指定邮件优先级 1：紧急 3：普通 5：缓慢
			message.setHeader("X-Priority","1");
			message.saveChanges();
			
			//创建一个Transport对象
			Transport transport = mailSession.getTransport("smtp");
			//连接SMTP服务器
			transport.connect(SMTPHost, user, password);
			//发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		}catch(Exception ex){
			logger.error("SendMailAction.send",ex);
			return false;
		}
	}
	
	//定义一个SMTP授权验证类
	static class SmtpAuth extends Authenticator{
		String user,password;
		//设置帐号信息
		void setAccount(String user,String password){
			this.user = user;
			this.password = password;
		}
		//取得PasswordAuthentication对象
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}


	public String sendMail() {
		try {
			boolean status = this.send();
			if (status){
				System.out.println("恭喜您，邮件发送成功！");
			}else{
				System.out.println("对不起，邮件发送失败！");
			}
		} catch (Exception e) {
			logger.error("SendMailAction.sendMail", e);
			return ERROR;
		}
		return "htmlMail";

	}
	public String toSendMail() {
	
		return "htmlMail";

	}
}
