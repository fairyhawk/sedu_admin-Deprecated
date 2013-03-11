package com.shangde.common.listener;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;

import com.shangde.common.util.SpringContextHolder;
import com.shangde.common.util.json.JackJson;
import com.shangde.edu.sys.domain.DicCode;
import com.shangde.edu.sys.service.DicCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InitFieldListener implements ServletContextListener {
	 private static final Logger logger = LoggerFactory.getLogger(InitFieldListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		DicCodeService dicCodeService =SpringContextHolder.getBean("dicCodeService");
		dicCodeService.getFields();
		
	}
	

}
