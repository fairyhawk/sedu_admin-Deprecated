package com.shangde.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

public class IPUtil {

	public static String visitWeb(String urlStr) {
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		try {
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0(compatible;MSIE 6.0;Windows 2000)");
			in = httpConn.getInputStream();
			return convertStreamToString(in);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {

		StringBuilder sb = new StringBuilder();
		String line;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} finally {
			is.close();
		}
			return sb.toString();
		} else {
			return "";
		}
	}
	
	public static String getAddressByIP(String ip) {
		String js = visitWeb("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + ip);
		JSONObject jo = JSONObject.fromObject(js.substring(21));
		String province = jo.get("province") == null? "" : URLDecoder.decode(jo.get("province").toString());
		String city = jo.get("city") == null? "" : URLDecoder.decode(jo.get("city").toString());
		return (province.equals("")||province.equals(city))? city : province + " " + city;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String js = visitWeb("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=61.233.30.19");
		JSONObject jo = JSONObject.fromObject(js.substring(21));

		System.out.println(URLDecoder.decode(jo.get("country").toString()));
		System.out.println(URLDecoder.decode(jo.get("province").toString()));
		System.out.println(URLDecoder.decode(jo.get("city").toString()));
	}
}
