package com.shangde.edu.webservice.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 全局字符串、参数获取工具类
 * 
 * @author Administrator
 */
public class IpUtil
{
	private static final Logger logger = Logger.getLogger(IpUtil.class);
	/**
	 * 得到请求Ip地址
	 * @param request
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-3
	 */
	public static String getIpAddr(HttpServletRequest request)
	{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获得客户端MAC地址
	 * @param request
	 * @return
	 * Author:Yangning
	 * CreateDate:2011-11-3
	 * @throws Exception 
	 */
	public static String getMacAddr(HttpServletRequest request) throws Exception
	{
		String mac = "";
		try
		{
			String os = System.getProperty("os.name").toLowerCase();
			if(os.contains("windows"))
			{
				Process process = Runtime.getRuntime().exec("ipconfig /all");
				BufferedReader buffer = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				for (String line = buffer.readLine(); line != null; line = buffer.readLine())
				{
					int index = line.indexOf("Physical Address");
					if (index <= 0)
					{
						continue;
					}
					mac = line.substring(index + 36).trim();
					break;
				}
				buffer.close();
				process.waitFor();
			}
		}
		catch (Exception e)
		{
			logger.error("IpUtil.getMacAddr \n\r" + e.getMessage());
			throw new Exception(e.getMessage());
		}
		return mac;
	}
}
	

