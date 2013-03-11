package com.shangde.edu.feed.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import org.apache.commons.mail.SimpleEmail;

public class SendSimplEmail {
	
	
	public static void sendMail() throws Exception {
		List<InternetAddress> list = new ArrayList<InternetAddress>();
		list.add(new InternetAddress("liubaisheng@sunland.org.cn"));
		list.add(new InternetAddress("libaogang@sunland.org.cn"));
		SimpleEmail email = new SimpleEmail();
		email.setFrom("highso@sunland.org.cn");
		email.setCharset(" utf-8 ");
		email.setSentDate(new Date());
		email.setSubject(" 测试Quartz ");
		email.setHostName("multi.263xmail.com");
		email.setAuthentication("highso@sunland.org.cn", "shangde2010");
		email.setTo(list);
		email.setContent(" <h1>Hello,把凤姐许配给你,你看咋样?</h1> ", " text/html;charset=utf-8 ");
		email.send();
	}
}
