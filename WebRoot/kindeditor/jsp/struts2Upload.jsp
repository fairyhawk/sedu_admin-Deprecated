<%@ page language="java" pageEncoding="GBK"%>
<%@page import="java.io.*" %>
<%@page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper" %>
<%@page import="java.util.concurrent.locks.*" %>
<%
	//小组模块中kindeditor编辑器，上传时，所使用到的中间存储目录
	String kindeditorSavePath = "upload/" + (String)session.getAttribute("kindeditorSavePath");
	//Struts2  请求 包装过滤器
	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
	// 获得上传的文件名 
	String fileName = wrapper.getFileNames("imgFile")[0];
	//获得未见过滤器 
	File file = wrapper.getFiles("imgFile")[0];
	//----------- 重新构建上传文件名----------------------
	final Lock lock = new ReentrantLock();
	String newName = null;
	lock.lock();
	try {
		//加锁为防止文件名重复 
		newName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
	}finally {
		lock.unlock();
	}
	//------------ 锁结束 -------------
	
	String fileDirectory = request.getSession().getServletContext().getRealPath("/") + kindeditorSavePath;
	File isD = new File(fileDirectory);
	//校验如果目录不存在，则创建目录
	if(!isD.isDirectory()){
		isD.mkdirs();
	}
	//获取文件输出流 
	FileOutputStream fos = new FileOutputStream(fileDirectory);
	//设置 KE 中的图片文件地址 
	String newFileName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + kindeditorSavePath +"/" + newName;
	byte[] buffer = new byte[1024];
	//获取内存中当前文件输入流 
	InputStream in = new FileInputStream(file);
	try {
		int num = 0;
		while ((num = in.read(buffer)) > 0) {
			fos.write(buffer, 0, num);
		}
	} catch (Exception e) {
		e.printStackTrace(System.err);
	} finally {
		in.close();
		fos.close();
	}
	//发送给KE 
	out.println("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=gbk'/></head><body>");
	out.println("<script type='text/javascript'>");
	out.println("parent.parent.KE.plugin['image'].insert('"
			+ wrapper.getParameter("id") + "','" + newFileName + "','"
			+ wrapper.getParameter("imgTitle") + "','"
			+ wrapper.getParameter("imgWidth") + "','"
			+ wrapper.getParameter("imgHeight") + "','"
			+ wrapper.getParameter("imgBorder") + "','"
			+ wrapper.getParameter("align") + "');</script>");
	out.println("</body></html>");
%>