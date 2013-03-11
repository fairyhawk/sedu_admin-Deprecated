package com.shangde.common.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;



public class AdvertTrackAction {
	/**
	 * @param src
	 * 		  广告主为亿起发指定的标识，用来判断流量来自于亿起发联盟
	 * @param cid
	 * 		  广告主在亿起发推广的标识，广告主在亿起发平台的推广可有多个标识，
	 * 		  此参数可用来区分不同的推广方式，此参数需要回传给亿起发。
	 * @param wi
	 * 		  亿起发下级网站信息，须原样回传给亿起发，作为亿起发结算的依据
	 * @param url
	 * 		  广告最终着陆地址
	 */
	private String src ;
	private int cid ;
	private String wi ;
	private String url ;
	private CookieHandler ck ;
	
	public String toAdvertPage(){
		/**
		 * 写入Cookies
		 */
		HttpServletResponse response = ServletActionContext.getResponse() ;
		
		try {
			ck.createCookie(response,"SRC", src, 30) ;	
			ck.createCookie(response,"CID", cid+"", 30) ;
			ck.createCookie(response,"WI", wi, 30) ;
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			return null ;
		}
		
		return "success" ;
	}
	
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getWi() {
		return wi;
	}
	public void setWi(String wi) {
		this.wi = wi;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public CookieHandler getCk() {
		return ck;
	}

	public void setCk(CookieHandler ck) {
		this.ck = ck;
	}
}
