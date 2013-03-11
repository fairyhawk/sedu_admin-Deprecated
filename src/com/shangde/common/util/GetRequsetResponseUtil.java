package com.shangde.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class GetRequsetResponseUtil {
	/**
	 * 获取request方法
	 * @return 返回requset 对象
	 */
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public static PrintWriter getPrintWriter(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			return response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpServletResponse getResponse(){
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}
}
