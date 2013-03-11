package com.shangde.edu.webservice.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.webservice.service.IWebService;
import com.shangde.edu.webservice.util.IpUtil;
import com.shangde.edu.webservice.util.XmlConvertUtil;


/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:鹰眼接口通过http get 请求返回官网数据
 *
 * @author		LiuQingGang
 * @date		2011-11-10
 * @version 	修改人:YangNing
 * 				修改日期:2011-11-15
 * 				
 *              
 */
public class WebServiceAction extends CommonAction {
	
	private static Logger logger = Logger.getLogger(WebServiceAction.class);
    /**
     * serialVersionUID
     */
	
	//充许表
	private static final String [] [] ALLOW_TABLE_NAMES = {{"cou_sell_way_tbl","sell_id"},{"finance_contract_tbl","id"},{"finance_cash_record_tbl","id"},{"sys_user_tbl","user_id"},{"sys_group_tbl","group_id"},{"crm_record_tbl","id"},{"cus_customer_tbl","cus_id"},{"crm_sellrecord_tbl","crm_id"},{"sys_subject_tbl","subject_id"},{"crm_chance_tbl","id"},{"crm_user_tbl","id"},{"sys_sub_group_tbl","id"}};
	
	
	
	//每次取最大条数
	private static final int MAX_RECORD_SIZE = 100000;
	
    private static final long serialVersionUID = 1L;
    
    /**
     * 允许的IP段
     */
    private static final String ALLOW_IP_RANGE = "172.16";
    /**
     * 获得数据时主键的最小值，不包含此值
     */
    private Integer beginId;
    /**
     * 获得数据时主键的最大值，不包含此值
     */
    private Integer endId;
    private String pid;
    private String pkey;
    private String accessToken;


	private String tableName;
	private IWebService httpWebService;
    
  
	public String gethighsoToken(){
		ServletResponse response = this.getServletResponse();
    	response.setContentType("text/plain;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		String returnCode = "error";
		try{
			out = response.getWriter();
	    	if(pid != null && pkey != null){
	    		if(pid.equals("sunland") && pkey.equals("sunland")){
	    			UUID uuid = UUID.randomUUID();
	    			String token = uuid.toString();
	    			token = token.replace("-", "");
	    			this.getServletRequest().getSession().setAttribute("token", token);
	    			returnCode = token;
	    		}
	    	}
		}catch(Exception e){
			logger.info("WebServiceAction.gethighsoToken" ,e);
		}finally{
			logger.info("returnCode:" + returnCode +"\tTableName:" + tableName+ "\tbeginId:" + beginId +"\tendId:" + endId + "\tipAddress:"  + new Date());
			out.write(returnCode);
		}
		return null;
    }
    /**
     * 功能:辅助方法，表名是否允许
     * @param tableName
     * @return
     * Author:Yangning
     * CreateDate:2011-12-17
     */
    private boolean checkExistTables(String tableName){
    	if(tableName != null){
	    	for(int i = 0; i < ALLOW_TABLE_NAMES.length; i++){
	    		if(tableName.toLowerCase().equals(ALLOW_TABLE_NAMES[i][0])){
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
    
    /*
     * 
    */
    public String gethighSoData() {
    	StringBuffer sb = new StringBuffer();
		ServletResponse response = this.getServletResponse();
		HttpServletRequest request = this.getServletRequest();
		//客户端ip地址
		String clientIp = null;
		//返回码
		String returnCode = null;
		//返回记录条数
		int recordSize = 0;
		response.setContentType("text/xml;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		try{
			out = response.getWriter();
			clientIp = IpUtil.getIpAddr(request);
			//权限标记位
			boolean flag = false;
			String token = (String)getServletRequest().getSession().getAttribute("token");
			if(token != null && accessToken != null){
				if(token.equals(accessToken)){
					flag = true;
				}
			}
			if(flag && checkExistTables(tableName)){
				
					//输入参数非法判断
					if(tableName == null || beginId == null || endId == null || (endId - beginId) < 0 || (endId - beginId) > MAX_RECORD_SIZE){
						sb.append("<root returnCode=\"");
						sb.append("A02");
						returnCode = "A02";
						sb.append("\">");
					}
					else {
						Map map = array2Map(tableName);
						map.put("beginId",beginId);
						map.put("endId", endId);
						List<Object> list = httpWebService.getObjectList(map);
						if(list != null && list.size() > 0){
							sb.append("<root returnCode=\"");
							sb.append("A01\" ");
							sb.append("tableName=\"");
							sb.append(tableName+"\"");
							sb.append(">");
							sb.append(XmlConvertUtil.listtoXml(list,tableName));
							returnCode = "A01";
							recordSize = list.size();
						}
					else{
						sb.append("<root returnCode=\"");
						sb.append("A04\" ");
						sb.append("tableName=\"");
						sb.append(tableName+"\"");
						sb.append(">");
						returnCode = "A04";
					}
			}
		}else{
			//权限不请允许处理
			sb.append("<root returnCode=\"");
			sb.append("A03");
			sb.append("\">");
			returnCode = "A03";
		}
		}catch(Exception e){
			//捕捉异常时返回A05
			sb.delete(0,sb.length());
			sb.append("<root returnCode=\"");
			sb.append("A05\"");
			sb.append(">");
			returnCode = "A05";
			logger.error("WebServiceAction.gethighSoData \n\r",e);
		}finally{
			
			sb.append("</root>");
			logger.info("returnCode:" + returnCode +"\tTableName:" + tableName+ "\tbeginId:" + beginId +"\tendId:" + endId + "\tipAddress:" + clientIp +  "\trecordSize:" + recordSize + "\tinvokeTime:" + new Date());
			out.write(sb.toString());
		}
        return null;
    }
    
	private static Map array2Map(String tableName){
    	Map<String,String> map = new HashMap<String,String>();
    	for(int i = 0; i<ALLOW_TABLE_NAMES.length;i++){
    		if(tableName.equals(ALLOW_TABLE_NAMES[i][0])){
    			map.put("tableName",ALLOW_TABLE_NAMES[i][0]);
    			map.put("id",ALLOW_TABLE_NAMES[i][1]);
    		}
    	}
    	return map;
    }
    
	
	 public String gethighSoDataMaxId() {
	    	StringBuffer sb = new StringBuffer();
			ServletResponse response = this.getServletResponse();
			HttpServletRequest request = this.getServletRequest();
			//客户端ip地址
			String clientIp = null;
			//返回码
			String returnCode = null;
			//返回记录条数
			int recordSize = 0;
			response.setContentType("text/xml;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			try{
				out = response.getWriter();
				clientIp = IpUtil.getIpAddr(request);
				//权限标记位
				boolean flag = false;
				String token = (String)getServletRequest().getSession().getAttribute("token");
				if(token != null && accessToken != null){
					if(token.equals(accessToken)){
						flag = true;
					}
				}
				if(flag && checkExistTables(tableName)){
						//输入参数非法判断
						if(tableName == null){
							sb.append("<root returnCode=\"");
							sb.append("A02");
							returnCode = "A02";
							sb.append("\">");
						}
						else {
							Map map = array2Map(tableName);
							Integer count = httpWebService.getObjectMaxId(map);
							if(count != null && count > 0){
								sb.append("<root returnCode=\"");
								sb.append("A01\" ");
								sb.append("tableName=\"");
								sb.append(tableName+"\" ");
								sb.append("result=\"");
								sb.append(count+"\" ");
								sb.append(">");
								returnCode = "A01";
							}
						else{
							sb.append("<root returnCode=\"");
							sb.append("A04\" ");
							sb.append("tableName=\"");
							sb.append(tableName+"\"");
							sb.append(">");
							returnCode = "A04";
						}
				}
			}else{
				//权限不请允许处理
				sb.append("<root returnCode=\"");
				sb.append("A03");
				sb.append("\">");
				returnCode = "A03";
			}
			}catch(Exception e){
				//捕捉异常时返回A05
				sb.delete(0,sb.length());
				sb.append("<root returnCode=\"");
				sb.append("A05\"");
				sb.append(">");
				returnCode = "A05";
				logger.error("WebServiceAction.gethighSoData \n\r",e);
			}finally{
				sb.append("</root>");
				logger.info("returnCode:" + returnCode +"\tTableName:" + tableName+ "\tbeginId:" + beginId +"\tendId:" + endId + "\tipAddress:" + clientIp +  "\trecordSize:" + recordSize + "\tinvokeTime:" + new Date());
				out.write(sb.toString());
			}
	        return null;
	 }
	 
	 public String gethighSoDataMinId() {
	    	StringBuffer sb = new StringBuffer();
			ServletResponse response = this.getServletResponse();
			HttpServletRequest request = this.getServletRequest();
			//客户端ip地址
			String clientIp = null;
			//返回码
			String returnCode = null;
			//返回记录条数
			int recordSize = 0;
			response.setContentType("text/xml;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			try{
				out = response.getWriter();
				clientIp = IpUtil.getIpAddr(request);
				//权限标记位
				boolean flag = false;
				String token = (String)getServletRequest().getSession().getAttribute("token");
				if(token != null && accessToken != null){
					if(token.equals(accessToken)){
						flag = true;
					}
				}
				if(flag && checkExistTables(tableName)){
						//输入参数非法判断
						if(tableName == null){
							sb.append("<root returnCode=\"");
							sb.append("A02");
							returnCode = "A02";
							sb.append("\">");
						}
						else {
							Map map = array2Map(tableName);
							Integer count = httpWebService.getObjectMinId(map);
							if(count != null && count > 0){
								sb.append("<root returnCode=\"");
								sb.append("A01\" ");
								sb.append("tableName=\"");
								sb.append(tableName+"\" ");
								sb.append("result=\"");
								sb.append(count+"\" ");
								sb.append(">");
								returnCode = "A01";
							}
						else{
							sb.append("<root returnCode=\"");
							sb.append("A04\" ");
							sb.append("tableName=\"");
							sb.append(tableName+"\"");
							sb.append(">");
							returnCode = "A04";
						}
				}
			}else{
				//权限不请允许处理
				sb.append("<root returnCode=\"");
				sb.append("A03");
				sb.append("\">");
				returnCode = "A03";
			}
			}catch(Exception e){
				//捕捉异常时返回A05
				sb.delete(0,sb.length());
				sb.append("<root returnCode=\"");
				sb.append("A05\"");
				sb.append(">");
				returnCode = "A05";
				logger.error("WebServiceAction.gethighSoData \n\r",e);
			}finally{
				sb.append("</root>");
				logger.info("returnCode:" + returnCode +"\tTableName:" + tableName+ "\tbeginId:" + beginId +"\tendId:" + endId + "\tipAddress:" + clientIp +  "\trecordSize:" + recordSize + "\tinvokeTime:" + new Date());
				out.write(sb.toString());
			}
	        return null;
	 }
	
    
    /**
     * 功能：返回数据总数量
     * @return
     * Author:Yangning
     * CreateDate:2011-12-17
     
    public String gethighSoDataCount() {
    	StringBuffer sb = new StringBuffer();
		ServletResponse response = this.getServletResponse();
		HttpServletRequest request = this.getServletRequest();
		//客户端ip地址
		String clientIp = null;
		//返回码
		String returnCode = null;
		//返回记录条数
		int recordSize = 0;
		response.setContentType("text/xml;");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		try{
			out = response.getWriter();
			clientIp = IpUtil.getIpAddr(request);
			//权限标记位
			boolean flag = false;
			String token = (String)getServletRequest().getSession().getAttribute("token");
			if(token != null && accessToken != null){
				if(token.equals(accessToken)){
					flag = true;
				}
			}
			if(flag && checkExistTables(tableName)){
					//输入参数非法判断
					if(tableName == null){
						sb.append("<root returnCode=\"");
						sb.append("A02");
						returnCode = "A02";
						sb.append("\">");
					}
					else {
						Integer count = httpWebService.getObjectCount(tableName);
						if(count != null && count > 0){
							sb.append("<root returnCode=\"");
							sb.append("A01\" ");
							sb.append("tableName=\"");
							sb.append(tableName+"\" ");
							sb.append("recordCount=\"");
							sb.append(count+"\" ");
							sb.append(">");
							returnCode = "A01";
						}
					else{
						sb.append("<root returnCode=\"");
						sb.append("A04\" ");
						sb.append("tableName=\"");
						sb.append(tableName+"\"");
						sb.append(">");
						returnCode = "A04";
					}
			}
		}else{
			//权限不请允许处理
			sb.append("<root returnCode=\"");
			sb.append("A03");
			sb.append("\">");
			returnCode = "A03";
		}
		}catch(Exception e){
			//捕捉异常时返回A05
			sb.delete(0,sb.length());
			sb.append("<root returnCode=\"");
			sb.append("A05\"");
			sb.append(">");
			returnCode = "A05";
			logger.error("WebServiceAction.gethighSoData \n\r",e);
		}finally{
			sb.append("</root>");
			logger.info("returnCode:" + returnCode +"\tTableName:" + tableName+ "\tbeginId:" + beginId +"\tendId:" + endId + "\tipAddress:" + clientIp +  "\trecordSize:" + recordSize + "\tinvokeTime:" + new Date());
			out.write(sb.toString());
		}
        return null;
    }
    */

    public Integer getBeginId() {
        return beginId;
    }

    public void setBeginId(Integer beginId) {
        this.beginId = beginId;
    }

    public Integer getEndId() {
        return endId;
    }

    public void setEndId(Integer endId) {
        this.endId = endId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getPkey() {
		return pkey;
	}
	public void setPkey(String pkey) {
		this.pkey = pkey;
	}
	
	public IWebService getHttpWebService() {
		return httpWebService;
	}
	public void setHttpWebService(IWebService httpWebService) {
		this.httpWebService = httpWebService;
	}
}
