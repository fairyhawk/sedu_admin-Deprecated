package com.shangde.edu.cus.action;

import java.util.regex.Pattern;

public class FunctionUtil {
	public static void main(String[] args) {
		String str = "<p><font size=\"2\"><span style=\" mce_style=\"font-size: 10.5pt\">"
				+ "依据绩abc效管理体系的规定，公司决定于</span><span style=\" mce_style=\"font-size: 10.5pt\">"
				+ "2008</span><span style=\" mce_style=\"font-size: 10.5pt\">年</span><span style=\" "
				+ "mce_style=\"font-size: 10.5pt\">12</span><span style=\" mce_style=\"font-size: 10.5pt\">"
				+ "月</span><span style=\" mce_style=\"font-size: 10.5pt\">22</span><span style=\" "
				+ "mce_style=\"font-size: 10.5pt\">日</span><span style=\" mce_style=\"font-size: 10.5pt\">"
				+ "\"-2009</span><span style=\" mce_style=\"font-size: 10.5pt\">年</span><span style=\" "
				+ "mce_style=\"font-size: 10.5pt\">1</span><span style=\" mce_style=\"font-size: 10.5pt\">"
				+ "月</span><span style=\" mce_style=\"font-size: 10.5pt\"> 23& </span><span style=\" "
				+ "mce_style=\"font-size: 10.5pt\">日期间进行</span><span style=\" mce_style=\"font-size: "
				+ "10.5pt\">2008</span><span style=\" mce_style=\"font-size: 10.5pt\">年年度绩效考评工作，"
				+ "具体事项如下：</span></font></p>";
		String str_text = Html2Text(str);
		System.out.println(str_text);
		String slice = abbreviate(str_text, 100, "...");
		System.out.println(slice);
	}

	/**
	 * @param str :
	 *            source string
	 * @param width :
	 *            string's byte width
	 * @param ellipsis :
	 *            a string added to abbreviate string bottom
	 * @return String Object
	 * 
	 */
	public static String abbreviate(String str, int width, String ellipsis) {
		if (str == null || "".equals(str)) {
			return "";
		}

		int d = 0; // byte length
		int n = 0; // char length
		for (; n < str.length(); n++) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width) {
				break;
			}
		}

		if (d > width) {
			n = n - ellipsis.length() / 2;
			return str.substring(0, n > 0 ? n : 0) + ellipsis;
		}

		return str = str.substring(0, n);
	}

	/**
	 * @param str :
	 *            source string
	 * @param width :
	 *            string's byte width
	 * @param ellipsis :
	 *            a string added to abbreviate string bottom
	 * @return String Object
	 * 
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;

		java.util.regex.Pattern p_html1;
		java.util.regex.Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";
			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签
			htmlStr = htmlStr.replaceAll("&nbsp;", " ");
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}
}
