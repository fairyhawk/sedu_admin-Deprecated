package com.shangde.edu.cus.util;

import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;
/**
 * webService数据解析
 * @author HQL
 *
 */
public class CusUtil {
	
	/**
	 * 统计用户视频数据
	 */
	public static void addCusVideoToTable(){
		IWebService webService = WebServiceFactory.getVideoService();
		webService.addCusVideoRankToTable();
	}
}
