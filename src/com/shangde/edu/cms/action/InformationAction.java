package com.shangde.edu.cms.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.SubjectUrl;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.ISubjectUrl;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InformationAction extends CommonAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String common;
	private Logger logger = LoggerFactory.getLogger(InformationAction.class);
	public String getCommon() {
		return common;
	}

	public void setCommon(String common) {
		this.common = common;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	private String url;
	
	
	private ISubject subjectService; //下拉菜单 项目
	private ISubjectUrl subjectUrlService;
	public ISubjectUrl getSubjectUrlService() {
		return subjectUrlService;
	}

	public void setSubjectUrlService(ISubjectUrl subjectUrlService) {
		this.subjectUrlService = subjectUrlService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	
	public List<Subject> lists;
	public List<Subject> getLists() {
		return lists;
	}

	public void setLists(List<Subject> lists) {
		this.lists = lists;
	}
	public SubjectUrl su;
	
	public SubjectUrl getSu() {
		return su;
	}

	public void setSu(SubjectUrl su) {
		this.su = su;
	}
	public Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 转到资讯管理
	 * 时间：2011-12-01 10:00
	 * wd
	 * @return
	 */
	public String toInformation(){
		lists = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		return "information";
	}
	


	/**
	 * 提取文件地址
	 * 时间：2011-12-01 10:21
	 * wd
	 * @throws IOException 
	 */
	public void extractFile() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");  
		PrintWriter out = response.getWriter();
		try{
			if(url!=null){
				File templateFile = new File(ServletActionContext.getServletContext().getRealPath(url));
				common = FileUtils.readFileToString(templateFile, "utf-8");
				out.print(common);
			}
		}catch(FileNotFoundException e){
			out.print("error01");
			logger.error("未发现文件"+e.toString());
		}catch (IOException e) {
			out.print("error02");
			logger.info("文件流错误"+e.toString());
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 修改文件
	 * 时间：2011-12-02 15:34
	 * wd
	 * @throws IOException 
	 */
	public void updateInformation() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");  
		PrintWriter out = response.getWriter();
//		common = new String(common.getBytes("ISO8859-1"),"UTF-8");
		FileOutputStream fos;
		try {
			String s=ServletActionContext.getServletContext().getRealPath(url);
			File file = new File(s) ;
			if(file.exists()){
				fos = new FileOutputStream(s);
				fos.write(common.getBytes("UTF-8"));
				out.print("success");				
			}else{
				throw new FileNotFoundException();  
			}
		} catch (FileNotFoundException e) {
			out.print("error01");
			logger.info("未发现文件"+e.toString());
		} catch (IOException e) {
			out.print("error02");
			logger.info("写入错误"+e.toString());
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 下拉菜单关联
	 * 时间：2011-12-13 11:30
	 * wd
	 * @throws IOException 
	 */
	public void getU() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		try{
			out = response.getWriter();
			su=subjectUrlService.getSubjectListByUserId(id);
			if(su==null){
				out.print("new");//新建
			}else{
				out.print(su.getSubjectUrl());				
			}
		}catch(Exception e){
			logger.info("写入错误"+e.toString());
		}
		finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 新建文件并添加数据库
	 * 时间：2011-12-13 20:30
	 * wd
	 * @throws IOException 
	 */
	public void createFolder() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");  
		PrintWriter out=null;
		try{
			out = response.getWriter();			
			String s=ServletActionContext.getServletContext().getRealPath(url);//本地化路径
			File file=new File(s);
			if(!file.exists()){//没有文件
	            FileOutputStream ous = new FileOutputStream(s);
	            BufferedOutputStream os = new BufferedOutputStream(ous);
	            os.close();
	            SubjectUrl su=new SubjectUrl();
				su.setSubjectId(id);
				su.setSubjectUrl(url);
				Integer num=subjectUrlService.addSubjectUrl(su);
				System.out.println(num);
	            out.print("success");
			}else{
				out.print("repeat");
			}
		}catch(Exception e){
			out.print("error");
			logger.info("错误"+e.toString());
		}
		finally{
			out.flush();
			out.close();
		}
	}
}
