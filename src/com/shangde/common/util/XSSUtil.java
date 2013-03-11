package com.shangde.common.util;

/**
 * 防止xss攻击替换标签
 * 
 * @author Administrator
 * 
 */
public class XSSUtil {

	/**
	 * 分割字符串
	 */
	public static String[] stringSpilit(String str, String spilit_sign) {
		String[] spilit_string = str.split(spilit_sign);
		if (spilit_string[0].equals("")) {
			String[] new_string = new String[spilit_string.length - 1];
			for (int i = 1; i < spilit_string.length; i++)
				new_string[i - 1] = spilit_string[i];
			return new_string;
		} else
			return spilit_string;
	}

	/**
	 * 用特殊的字符连接字符串
	 */
	public static String stringConnect(String[] strings, String spilit_sign) {
		String str = "";
		for (int i = 0; i < strings.length; i++) {
			str += strings[i] + spilit_sign;
		}
		return str;
	}

	/**
	 * 替换 过滤 此类只需调用此方法
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		String[] str_arr = stringSpilit(str, "");
		for (int i = 0; i < str_arr.length; i++) {
			for (int j = 0; j < FilterChars.length; j++) {
				if (FilterChars[j][0].equals(str_arr[i]))
					str_arr[i] = FilterChars[j][1];
			}
		}
		return (stringConnect(str_arr, "")).trim();
	}

	private static String[][] FilterChars = { { "<", "&lt;" }, { ">", "&gt;" },
			{ " ", "&nbsp;" }, { "\"", "&quot;" }, { "&", "&amp;" } };

}
