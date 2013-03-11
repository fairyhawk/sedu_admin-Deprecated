package com.shangde.edu.feed.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailSendTest {
	public void sendEmail(int emailNum) throws EmailException{
		HtmlEmail email = new HtmlEmail(); 
		email.setHostName("multi.263xmail.com"); // 设定smtp服务器
//		email1.setSSL(Boolean.TRUE); // 设定是否使用SSL
//		email1.setSslSmtpPort("465"); // 设定SSL端口
		email.setSmtpPort(25);
		email.setAuthentication("highso@sunland.org.cn", "shangde2010"); // 设定smtp服务器的认证资料信息
		email.addTo("liubaisheng@sunland.org.cn"); 
		email.setFrom("highso@sunland.org.cn", "highso"); 
		email.setSubject("第" + emailNum + "封测试邮件"); 
		email.setCharset("UTF-8"); 
//		File file = new File("/home/soluo/email.jpg"); 
//		email.embed(file); 
		email.setHtmlMsg("邮件内容，可以带html标签,这是第" + emailNum + "封邮件"); 
		email.send();
//		EmailExecutorPool.getInstance().getExecutorService() .execute(new EmailExecutor(email)); 
	}   
	
	public static void main(String[] args) { 
		
			EmailSendTest test = new EmailSendTest(); 
			try {
				test.sendEmail(0);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	} 
}
