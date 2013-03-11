package com.shangde.edu.vst.remote;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-6
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public class WebServiceFactory {
	
	private static final Logger logger = Logger.getLogger(WebServiceFactory.class);
	
	private static XFireProxyFactory factoryInstance = null;
	
	//private static final String CONNECT_URL = "http://t3back.highso.net.cn/stSrv.jspx";
	private static final String CONNECT_URL = "http://127.0.0.1:8080/hadata/stSrv.ws";
	private WebServiceFactory(){
	}
	
	private synchronized static XFireProxyFactory getInstance(){
		if(factoryInstance == null){
			factoryInstance = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
		}
		return factoryInstance;
	}
	
	public static IWebService getVideoService(){
			Service srvcModel = new ObjectServiceFactory().create(IWebService.class);
			IWebService iWebService = null;
			try {
				 iWebService = (IWebService)getInstance().create(srvcModel,CONNECT_URL);
			} catch (MalformedURLException e) {
				logger.error("WebServiceFactory.getVideoService", e);
			}
		return iWebService;
	}
	
	
	public static void main(String [] args){
		// for(int i=0;i<1000;i++){
			IWebService webService = WebServiceFactory.getVideoService();
			System.out.println(webService.getVStByAllSubject("0&0&0&0"));
			System.out.println("\n\n");
			// webService.getVStRecordCount("0&0&0");
		// }
	}
}
