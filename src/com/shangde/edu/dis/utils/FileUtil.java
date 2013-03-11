package com.shangde.edu.dis.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

public class FileUtil {

	
	public static String kindeditorUpload(String savePath,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
//		boolean isCreate=false;
		//String savePath = (String) this.getSession("kindeditorSavePath");
		if (savePath == null || savePath.equals(""))
		{
			savePath = "dis/temp";
		}
		// 小组模块中kindeditor编辑器，上传时，所使用到的中间存储目录
		String kindeditorSavePath = "upload/" + savePath;
		String newFileName = null;
		StringBuffer str = new StringBuffer();
		
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		String fileName = wrapper.getFileNames("imgFile")[0];
		
		//Log.debug("文件名" + fileName);
		// 获得未见过滤器
		File file = wrapper.getFiles("imgFile")[0];
		
		response.setCharacterEncoding("UTF-8");
		//文件大小校验 10M = 10485760
		//struts.multipart.maxSize
		if(file.length() > 10485760){
			
			str.append("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=utf-8'/></head><body>");
			str.append("<script type='text/javascript'>");
			
			str.append("alert('文件过大!')");
			
			
			str.append("</script>");
			str.append("</body></html>");
		}
		if(str.length() == 0){
			// ----------- 重新构建上传文件名----------------------
			final Lock lock = new ReentrantLock();
			String newName = null;
			lock.lock();
			try {
				// 加锁为防止文件名重复
				newName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
			} finally {
				lock.unlock();
			}
			// ------------ 锁结束 -------------
	
			String fileDirectory = request.getSession().getServletContext().getRealPath("/") + kindeditorSavePath;
			File isD = new File(fileDirectory);
			// 校验如果目录不存在，则创建目录
//			if (!isD.isDirectory()) {
//				isCreate=isD.mkdirs();
//			}
//			if(!isCreate)throw new IOException("创建文件夹失败！");
			
			if(!isD.isDirectory()){
				boolean isCreate=isD.mkdirs();
				if(!isCreate){
					throw new IOException("创建文件夹失败！");
				}
			}
			
			
			
			
			
			// 获取文件输出流
			FileOutputStream fos = new FileOutputStream(fileDirectory + "/" + newName);
			// 设置 KE 中的图片文件地址
			newFileName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/" + kindeditorSavePath + "/" + newName;
			byte[] buffer = new byte[1024];
	
			// 获取内存中当前文件输入流
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
			// 发送给 KE
		}
		
		str.append("<html><head><title>Insert Image</title><meta http-equiv='content-type' content='text/html; charset=utf-8'/></head><body>");
		str.append("<script type='text/javascript'>");
		str.append("parent.parent.KE.plugin['image'].insert('" + wrapper.getParameter("id") + "','" + newFileName + "','" + wrapper.getParameter("imgTitle") + "','" + wrapper.getParameter("imgWidth")+ "','" + wrapper.getParameter("imgHeight") + "','" + wrapper.getParameter("imgBorder") + "','" + wrapper.getParameter("align") + "');</script>");
		str.append("</body></html>");
		
		return str.toString();
	}
	
	public static String upload(String savePath,String newName,HttpServletRequest request,HttpServletResponse response) throws IOException{

		boolean isCreate=false;
		String result = "";//[1=文件过大,2=IOException,3=...],非之内，则为文件名称
		
		//String savePath = (String) this.getSession("kindeditorSavePath");
		if (savePath == null ||"".equals(savePath))
		{
			savePath = "dis/temp";
		}
		// 小组模块中kindeditor编辑器，上传时，所使用到的中间存储目录
		String kindeditorSavePath = "upload/" + savePath;
		String newFileName = null;
		
		response.setCharacterEncoding("UTF-8");
		// Struts2 请求 包装过滤器
		MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// 获得上传的文件名
		String fileName = null;
		File file = null;
		
		try{
			fileName = wrapper.getFileNames("imgFile")[0];
			// 获得未见过滤器
			file = wrapper.getFiles("imgFile")[0];
			//文件大小校验 10M = 10485760
			//struts.multipart.maxSize
			if(file.length() > 10485760){
				result = "1";
			}
		}catch (Exception e) {
			result = "2";
		}
		
		if(result.length() == 0){
			// ----------- 重新构建上传文件名----------------------
			final Lock lock = new ReentrantLock();
			lock.lock();
			try {
				//如果 文件名称未指定，则再重新创建
				// 加锁为防止文件名重复
				if(newName == null){
					newName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."), fileName.length());	
				}
			} finally {
				lock.unlock();
			}
			// ------------ 锁结束 -------------
	
			String fileDirectory = request.getSession().getServletContext().getRealPath("/") + kindeditorSavePath;
			File isD = new File(fileDirectory);
			// 校验如果目录不存在，则创建目录
			if (!isD.isDirectory()) {
				isCreate=isD.mkdirs();
			}
			if(!isCreate)throw new IOException("创建文件夹失败！");
			// 获取文件输出流
			FileOutputStream fos = new FileOutputStream(fileDirectory + "/" + newName);
			// 设置 KE 中的图片文件地址
			newFileName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/" + kindeditorSavePath + "/" + newName;
			byte[] buffer = new byte[1024];
	
			// 获取内存中当前文件输入流
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
			result = newName;//文件名
		}
		return result;
	}
}
