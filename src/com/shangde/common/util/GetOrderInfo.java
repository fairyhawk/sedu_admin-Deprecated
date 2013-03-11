package com.shangde.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetOrderInfo {
	// 获得亿起发URL的返回值，接口1实现
	public static String getHttpMessageFromGet() {
		BufferedReader br=null;
		try {
			String urlstr = "http://highso.org.cn/util/QueryAdvertOrder!queryOrder.action?src=emar&cid=101&orderTime=2011-4-22";
			URL url = new URL(urlstr);
			// 获得连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			// 网页提交方式
			connection.setRequestMethod("GET");
			// 取得inputstream.发送请求
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = br.readLine() ;
			if(!"Did not meet the conditions of the data".equals(line)){
				while(line!=null){
					System.out.println(line);
					line = br.readLine() ;
				}
				
				connection.disconnect();
			}else{
				return "Did not meet the conditions of the data" ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null ;
	}

	public static void main(String[] args) {
		new GetOrderInfo().getHttpMessageFromGet();
	}

}
