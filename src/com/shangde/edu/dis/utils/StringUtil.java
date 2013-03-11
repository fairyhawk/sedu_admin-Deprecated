package com.shangde.edu.dis.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	public static String floor()
	{
		return "asd";
	}
	public static String chop(String orignalString, int length) {
	
		orignalString += "asdf";
		if (orignalString == null || orignalString.length() == 0) {
			return orignalString;
		}
		orignalString = orignalString.replaceAll("   ", "   ");
		if (orignalString.length() < length) {
			return orignalString;
		}
		StringBuffer buffer = new StringBuffer(length);
		length = length * 2;
		int count = 0;
		int stringLength = orignalString.length();
		int i = 0;
		for (; count < length && i < stringLength; i++) {
			char c = orignalString.charAt(i);
			if (c < '\u00ff') {
				count++;
			} else {
				count += 2;
			}
			buffer.append(c);
		}

		System.out.println(buffer.toString());
		return buffer.toString();
	}

	public static String subString(String str, int len, String... elide) {
		if (str == null) {
			return "";
		}
		byte[] strByte = str.getBytes();
		int strLen = strByte.length;
		// int elideLen = (elide.trim().length() == 0) ? 0 :
		// elide.getBytes().length;
		if (len >= strLen || len < 1) {
			return str;
		}
		/*
		 * if (len - elideLen > 0) { len = len - elideLen; }
		 */
		int count = 0;
		for (int i = 0; i < len; i++) {
			int value = (int) strByte[i];
			if (value < 0) {
				count++;
			}
		}
		if (count % 2 != 0) {
			len = (len == 1) ? len + 1 : len - 1;
		}
		return new String(strByte, 0, len) + elide[0].trim();
	}
	
	
	/**
	 * 过滤其它标签
	 * 
	 * @param element 过滤的元素，值(字符串)
	 * @return
	 */
	public static String getTxtWithoutHTMLElement(String element) {
		if (null == element || "".equals(element.trim())) {
			return element;
		}
		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {
				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);
		return txt.toString();
	}
	
	/**
	 * 特殊字符替换
	 * @param str
	 * @return
	 */
	public static String specialCharacterFiltering(String str) {
		if (null == str || "".equals(str.trim())) {
			return str;
		}
		str = str.replaceAll("&nbsp;", " ");
		return str;
	}
	
	public static void main(String []args){
		String element = null ; /*"<po>fdsafsd<kkk><email>ddddddddddddd</fdas><img src='http://baidu.com'/>";*/
		String s = specialCharacterFiltering(element);
		System.out.println(s);
	}
	
}
