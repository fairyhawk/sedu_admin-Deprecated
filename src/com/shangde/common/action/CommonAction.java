package com.shangde.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.sys.domain.User;

import flex.messaging.FlexContext;

public class CommonAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5019186283793296718L;
	
	/**
	 * response的out对象
	 */
	private PrintWriter out = null;
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

	public static String SYS_USER_SESSION_NAME = "sedu_user";
	/** 分页结果对象 */
	private PageResult page;
	/** 分页页面地址及参数 */
	private String pageUrlParms;
	/** log对象 */
	private Log logger = LogFactory.getLog(getClass());
	/** request对象 */
	protected HttpServletRequest servletRequest;
	/** response对象 */
	private HttpServletResponse servletResponse;
	/** 系统已登录对象，若为null，则说明用户没有登录，跳转到登陆界面 */
	private User loginedUser;
	/** 系统配置服务类，提供没必要写在数据库中的各种配置参数 */
	private ConfigService configurator;
	private File[] files;
    private String[] filesFileName;
    private String[] filesContentType;
    private String savePath = "/upload";
    private String downloadFileName;
    private InputStream downloadStream;
    
    //页面显示信息，用于action向jsp传递提示信息(错误参数，或成功提示)
    private String message;
    
    /**
     * 登陆的用户id
     */
    private int loginedCusId = 0;

	@SuppressWarnings("unchecked")
	private Result result;

	@SuppressWarnings("unchecked")
	public Result getResult() {
		return result;
	}

	@SuppressWarnings("unchecked")
	public void setResult(Result result) {
		this.result = result;
	}

	public Log getLogger() {
		return logger;
	}

	public void setLogger(Log logger) {
		this.logger = logger;
	}

	public String getUuid() {
		return UUID.randomUUID().toString();
	}

	public HttpServletRequest getServletRequest() {
		return servletRequest != null ? servletRequest : FlexContext.getHttpRequest();
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public HttpServletResponse getServletResponse() {
		return (servletResponse != null ? servletResponse : FlexContext.getHttpResponse());
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	/**
	 * 获取URL及参数
	 * 
	 * @return 类似于a.action?name=tx&age=20
	 * @throws UnsupportedEncodingException 
	 */
	public String getUrlParms(){
		StringBuffer sbUrlParms = servletRequest.getRequestURL();	
		sbUrlParms.append("?");
		Enumeration parNames = servletRequest.getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=").append(
						URLEncoder.encode(servletRequest.getParameter(parName),"UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ERROR;
			}
		}
		return sbUrlParms.toString();
	}

	public String getUrlParms1() {
		return servletRequest.getQueryString();
	}

	public String getPageUrlParms() {
		return pageUrlParms;
	}

	public void setPageUrlParms() {
		this.pageUrlParms = getUrlParms();
	}
	
	public void setPageUrlParms(String pageUrlParms) {
		this.pageUrlParms = pageUrlParms;
	}

	public PageResult getPage() {
		return page;
	}

	public void setPage(PageResult page) {
		this.page = page;
	}

	public User getLoginedUser() {
		if (loginedUser == null) {
			loginedUser = (User) servletRequest.getSession().getAttribute(
					SYS_USER_SESSION_NAME);
		}
		return loginedUser;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	/**
	 * 获取当前日期，供freemarker使用
	 * 
	 * @return 当前日期
	 */
	public Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public void setSession(String name, Object o) {
		ActionContext.getContext().getSession().put(name, o);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getSession(String name) {
		if (ActionContext.getContext().getSession().get(name) == null) {
			return null;
		} else {
			return (T) ActionContext.getContext().getSession().get(name);
		}
	}

	public String execute() throws Exception {
		if (hasErrors()) {
			LOG.debug("action not executed, field or action errors");
			LOG.debug("Field errors: " + getFieldErrors());
			LOG.debug("Action errors: " + getActionErrors());
			return INPUT;
		}
		return SUCCESS;
	}
	
    protected void upload(List<String> nameList, List<File> fileList) throws Exception {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        for(int i=0; i<fileList.size(); i++) {
        fos = new FileOutputStream(this.getRealSavePath() + "/" +nameList.get(i));   
	        fis = new FileInputStream(fileList.get(i));   
	        byte[] b = new byte[1024];   
	        while((fis.read(b))!=-1){   
	            fos.write(b);   
	        }   
	        fos.close();   
	        fis.close();   
        }
    }
	
    protected void uploadForWeb(List<String> nameList, List<File> fileList) throws Exception {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        for(int i=0; i<fileList.size(); i++) {
        fos = new FileOutputStream(getRealSavePathForWeb() + "/" +nameList.get(i));   
	        fis = new FileInputStream(fileList.get(i));   
	        byte[] b = new byte[1024];   
	        while((fis.read(b))!=-1){   
	            fos.write(b);   
	        }   
	        fos.close();   
	        fis.close();   
        }
    }
    
    protected String getFileType(String fileName) {
    	return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }
    
    public String download() throws Exception {
    	try {
    		downloadStream = new FileInputStream(this.getRealSavePath() + "/" + this.downloadFileName);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "download";
    }
    
    protected String getRealSavePath() {
    	if(this.savePath.indexOf("/upload") == -1) {
    		this.savePath = "/upload" + this.savePath;
    	}
    	String realSavePath = ServletActionContext.getServletContext().getRealPath(this.savePath);
    	File file = new File(realSavePath);
    	if(!file.exists()) {
    		file.mkdirs();
    	}
    	return realSavePath;
    }
    
    protected String getRealSavePathForWeb() {
    	String realSavePath = ServletActionContext.getServletContext().getRealPath(savePath);
    	File file = new File(realSavePath);
    	if(!file.exists()) {
    		file.mkdirs();
    	}
    	return realSavePath;
    }
    
    protected String validateUpload(){
    	if(this.files == null ||  this.files.length == 0) {
    		return "请上传图片！";
    	}
        if(!this.validateType()) {
			return "上传的图片必须都是jpg格式，请重新上传！";
        }
        if(!this.validateLength()) {
    		return "上传的图片不能大于3M，请重新上传！";
       	}
    	return "true";
    }
    
    private boolean validateType() {
		for(int i=0; i<this.filesContentType.length; i++) {
			
	    	if(!("image/pjpeg".equals(filesContentType[i])
	    			|| "image/gif".equals(this.filesContentType[i])
	    			|| "image/bmp".equals(this.filesContentType[i])
	    			|| "image/png".equals(this.filesContentType[i])
	    			|| "image/jpeg".equals(this.filesContentType[i])
	    			|| "image/jpg".equals(this.filesContentType[i])
	    			|| "image/x-png".equals(this.filesContentType[i])))
		    	return false;
		}
    	return true;
    }
    
    private boolean validateLength() {
		for(int i=0; i<this.files.length; i++) {
	    	if(this.files[i].length() > 3072000)
		    	return false;
		}
    	return true;
    }
    
    protected String getRealPath(String path) {
    	return ServletActionContext.getServletContext().getRealPath(path);
    }

	/**
	 * 获取登陆用户的id
	 * @return
	 */
	protected int getLoginUserId() {
		String ukey = CookieHandler.getCookieValueByName(getServletRequest(), LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
		if(ukey == null || ukey.trim().equals("")) {
			return 0;
		} else {
			return Integer.valueOf(ukey.split(",")[0]);
		}
	}
    public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public PrintWriter getOut() {
		if(out == null) {
			try {
				servletRequest.setCharacterEncoding("GBK");
				out = getServletResponse().getWriter();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	
}
