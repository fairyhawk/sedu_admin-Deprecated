package com.shangde.edu.feed.mail;

import java.util.Collections;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangde.edu.feed.utils.template.VelocityUtils;
/**
 * MIME邮件服务类.
 * 
 * Velocity引擎生成的的html格式邮件
 */
public class MimeMailService {

	private static final String DEFAULT_ENCODING = "utf-8";

	private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);


	private VelocityEngine velocityEngine;

	private String templateFileName;

	/**
	 * 使用Velocity生成html格式内容.
	 */
	public String generateContent(String userName) throws MessagingException {

		Map context = Collections.singletonMap("userName", userName);
		return VelocityUtils.renderFile(templateFileName, velocityEngine, DEFAULT_ENCODING, context);
	}

	/**
	 * 使用Velocity生成html格式内容.
	 */
	public String generateContent(Map context) throws MessagingException {

//		Map context = Collections.singletonMap("userName", values.get("userName"));
		
		return VelocityUtils.renderFile(templateFileName, velocityEngine, DEFAULT_ENCODING, context);
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

}
