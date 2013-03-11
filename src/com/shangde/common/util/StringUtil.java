package com.shangde.common.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigDecimal;


public class StringUtil {

	

	public static int strToInt(String str) {

		int result = 0;

		try {

			result = Integer.parseInt(str);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return result;

	}

	public static String intToStr(int i) {

		return Integer.toString(i);

	}

	public static boolean inNumber(String str, String parent) {

		boolean is = true;

		int i;

		char c;

		if (str.length() == 0) {

			is = false;

		}

		else {

			for (i = 0; i < str.length(); i++) {

				c = str.charAt(i);

				System.out.print(c);

				int j = parent.indexOf(c);

				System.out.println("fgfg" + j);

				if (j < 0) {

					is = false;

					break;

				}

			}

		}

		return is;

	}


	

	public static boolean endsWith444(String str) {

		return (str.endsWith("444"));

	}

	public static boolean strSame(String str) {

		boolean is = true;

		char c;

		c = str.charAt(0);

		for (int i = 0; i < str.length(); i++) {

			if (c != str.charAt(i)) {

				is = false;

				break;

			}

		}

		return is;

	}

	public static boolean startSame(String str, int bit) {

		String substr = str.substring(0, bit);

		return (strSame(substr));

	}

	public static boolean endSame(String str, int bit) {

		int begin = str.length() - bit;

		int end = str.length();

		String substr = str.substring(begin, end);

		return (strSame(substr));

	}

	public static boolean sameSub6(String str) {

		return ((startSame(str, 6)) || (endSame(str, 6)));

	}

	public static boolean sameSub(String str, int bit) {

		boolean is = false;

		int i = 0;

		while ((i + bit) <= str.length()) {

			// System.out.println("i:"+i);

			// System.out.println("bit:"+bit);

			String substr = str.substring(i, bit + i);

			// System.out.println(substr);

			if (strSame(substr)) {

				is = true;

				break;

			}

			i++;

		}

		return is;

	}

	public static boolean sameSub5(String str) {

		return (sameSub(str, 5));

	}

	public static boolean sameSub4(String str) {

		return (sameSub(str, 4));

	}

	public static boolean end3Same(String str) {

		return (endSame(str, 3));

	}

	public static boolean start3Same(String str) {

		return (startSame(str, 3));

	}

	public static boolean continueNum(String str) {

		boolean is = true;

		int begin = 0;

		int end = begin + 1;

		int nextvalue = 0;

		int imme = 0;

		String sub = str.substring(begin, end);

		int value = Integer.parseInt(sub);

		while (end < str.length()) {

			begin++;

			end++;

			String sub1 = str.substring(begin, end);

			nextvalue = Integer.parseInt(sub1);

			imme = nextvalue - value;

			System.out.println("nextvalue" + nextvalue);

			System.out.println("value" + value);

			if (imme != 1) {

				System.out.println("nextvalue" + nextvalue);

				System.out.println("value" + value);

				is = false;

				break;

			}

			value = nextvalue;

		}

		return is;

	}

	public static String subEnds(String s, int bit) {

		return (s.substring(s.length() - bit, s.length()));

	}

	public static String subStarts(String s, int bit) {

		return (s.substring(0, bit));

	}

	public static boolean serEnd3(String str) {

		String sub = subEnds(str, 3);

		System.out.println(sub);

		return (continueNum(sub));

	}

	public static boolean serStart3(String str) {

		String sub = subStarts(str, 3);

		System.out.println(sub);

		return (continueNum(sub));

	}

	public static boolean start588(String str) {

		return (subStarts(str, 3).equals("588"));

	}

	public static boolean start555(String str) {

		return (subStarts(str, 3).equals("555"));

	}

	public static boolean isSequence(List ls) {

		boolean is = true;

		if (ls != null) {

			int count = ls.size();

			for (int i = 0; i < count - 1; i++) {

				String current = (String) ls.get(i);

				int cur = strToInt(current);

				int j = i + 1;

				String next = (String) ls.get(j);

				int ne = strToInt(next);

				int interval = ne - cur;

				if (interval != 1) {

					System.out.print("is" + interval);

					is = false;

					break;

				}

				else {

					System.out.print("is" + interval);

					is = true;

				}

			}

		}

		return is;

	}

	// by zhaojing 20041016 start

	/**
	 * 
	 * nvl
	 * 
	 * @param sIn
	 *            String
	 * 
	 * @return String
	 * 
	 */

	public static String nvl(String sIn) {

		if (sIn == null) {

			return "";

		}

		else {

			return sIn;

		}

	}

	/**
	 * 
	 * isNullString
	 * 
	 * @param str
	 *            String
	 * 
	 * @return boolean
	 * 
	 */

	public static boolean isNullString(Object str) {

		return (str == null || "".equals(str.toString().trim()));

	}


	public static String transNullString(Object str) {

		if (isNullString(str))
			return "";
		else
			return String.valueOf(str);

	}

	/**
	 * 
	 * replaceStr
	 * 
	 * @param src
	 *            String
	 * 
	 * @param sFnd
	 *            String
	 * 
	 * @param sRep
	 *            String
	 * 
	 * @return String
	 * 
	 */

	public static String replaceStr(String src, String sFnd, String sRep) {

		String sTemp = "";

		int endIndex = 0;

		int beginIndex = 0;

		do {

			endIndex = src.indexOf(sFnd, beginIndex);

			if (endIndex >= 0) { // mean found it.

				sTemp = sTemp + src.substring(beginIndex, endIndex) + sRep;

				beginIndex = endIndex + sFnd.length();

			}

			else if (beginIndex >= 0) {

				sTemp = sTemp + src.substring(beginIndex);

				break;

			}

		}

		while (endIndex >= 0);

		return sTemp;

	}

	/**
	 * 
	 * toHTMLOutStr
	 * 
	 * @param sIn
	 *            String
	 * 
	 * @return String
	 * 
	 */

	public static String toHTMLOutStr(String sIn) {

		if (isNullString(sIn)) {

			return "&nbsp;";

		}

		char[] content = new char[sIn.length()];

		sIn.getChars(0, sIn.length(), content, 0);

		final int size = 50;

		StringBuffer result = new StringBuffer(content.length + size);

		for (int i = 0; i < content.length; i++) {

			switch (content[i]) {

			case '<':

				result.append("&lt;");

				break;

			case '>':

				result.append("&gt;");

				break;

			case '&':

				result.append("&amp;");

				break;

			case '"':

				result.append("&quot;");

				break;

			default:

				result.append(content[i]);

			}

		}

		String retStr = replaceStr(result.toString(), "\n", "<br>");

		return retStr;

	}

	// by zhaojing 20041016 end

	public static String convertString2FixedLength(String str, int strLen) {

		if (str.length() == strLen) {

			return str;

		}

		if (str.length() > strLen) {

			return str.substring(0, strLen);

		}

		StringBuffer sb = new StringBuffer(str);

		while (sb.length() < strLen) {

			sb.append(" ");

		}

		return sb.toString();

	}

	public static String convertInt2FixedLength(int intValue, int strLen) {

		String str = String.valueOf(intValue);

		return convertString2FixedLength(str, strLen);

	}

	/**
	 * 
	 * 鍦ㄧ幇鏈夊瓧绗︿覆鐨勫乏杈硅ˉ瓒虫寚瀹氱殑瀛楃
	 * 
	 * @param strSrc
	 *            String 婧愬瓧绗︿覆
	 * 
	 * @param length
	 *            int 琛ヨ冻鍚庣殑闀垮害
	 * 
	 * @param pad
	 *            char 濉厖鐨勫瓧绗?
	 * 
	 * @return String 琛ヨ冻浠ュ悗鐨勫瓧绗︿覆
	 * 
	 */

	public static String lpad(String strSrc, int length, char pad) {

		if (strSrc.length() >= length) {

			return strSrc;

		}

		int diff = length - strSrc.length();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < diff; i++) {

			sb.append(pad);

		}

		return sb.toString() + strSrc;

	}

	/**
	 * 
	 * 鍦ㄧ幇鏈夊瓧绗︿覆鐨勫彸杈硅ˉ瓒虫寚瀹氱殑瀛楃
	 * 
	 * @param strSrc
	 *            String 婧愬瓧绗︿覆
	 * 
	 * @param length
	 *            int 琛ヨ冻鍚庣殑闀垮害
	 * 
	 * @param pad
	 *            char 濉厖鐨勫瓧绗?
	 * 
	 * @return String 琛ヨ冻浠ュ悗鐨勫瓧绗︿覆
	 * 
	 */

	public static String rpad(String strSrc, int length, char pad) {

		if (strSrc.length() >= length) {

			return strSrc;

		}

		int diff = length - strSrc.length();

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < diff; i++) {

			sb.append(pad);

		}

		return strSrc + sb.toString();

	}

	/**
	 * 
	 * 鍒嗗壊瀛楃涓?
	 * 
	 * @param s
	 *            婧愬瓧绗︿覆
	 * 
	 * @param s1
	 *            鍒嗗壊瀛椾覆鏍囪瘑瀛楃
	 * 
	 * @return 鍒嗗壊鍚庣殑瀛楃鏁扮粍
	 * 
	 */

	public static String[] seperates(String s, String s1) {

		String s2 = "";

		int i = 0;

		int j = 0;

		int k = 0;

		i = s.length();

		for (int l = 0; l < i; l++)

			if (s.substring(l, l + 1).equals(s1))

				k++;

		String as[] = new String[k + 1];

		try {

			for (int i1 = 0; i1 < i; i1++)

				if (s.substring(i1, i1 + 1).equals(s1)) {

					as[j] = s2;

					j++;

					s2 = "";

				}

				else {

					s2 = s2 + s.substring(i1, i1 + 1);

				}

			as[j] = s2;

		}

		catch (Exception exception) {

			System.out.println("message:" + exception);

		}

		return as;

	}

	/**
	 * 鏍规嵁鍘熶环鍜屾姌鎵ｈ绠楀埌鎶樻墸鍚庣殑浠锋牸銆備富瑕佹槸涓轰簡澶勭悊绮惧害闂
	 * 
	 * @param price
	 * @param off
	 *            鎶樻墸銆備緥濡?8鎶橈紝鍒欎紶鍏ョ殑鍙傛暟涓?0.8锛?.571鎶樺垯浼犲叆鐨勫弬鏁颁负0.3571
	 * @return
	 */
	public static double doubleMutil(double price, double off) {
		BigDecimal aa = new BigDecimal(price * off * 100);
		BigDecimal a100 = new BigDecimal(100);
		BigDecimal bb = aa.divide(a100, 4, BigDecimal.ROUND_HALF_UP);
		return bb.doubleValue();
	}


	/**
	 * 鐢熸垚鎸囧畾闀垮害鐨勯殢鏈哄瘑鐮?
	 * @param pwd_len
	 * @return
	 */
	public static String genRandomNum(int pwd_len){
		//35鏄洜涓烘暟缁勬槸浠?寮?鐨勶紝26涓瓧姣?10涓暟瀛?
		final int  maxNum = 36;
		int i;  //鐢熸垚鐨勯殢鏈烘暟
		int count = 0; //鐢熸垚鐨勫瘑鐮佺殑闀垮害
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while(count < pwd_len){
			//鐢熸垚闅忔満鏁帮紝鍙栫粷瀵瑰?锛岄槻姝㈢敓鎴愯礋鏁帮紝
			i = Math.abs(r.nextInt(maxNum));  //鐢熸垚鐨勬暟鏈?ぇ涓?6-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count ++;
			}
		}
		return pwd.toString();
	}
	/**
	 * 灏忓啓杞ぇ鍐?
	 * @param upperStr
	 * @return
	 * @throws Exception
	 */
	public static String toUpperStr(String lowStr) throws Exception
	{
		String upperStr = new String();
		try {
			StringBuffer sb = new StringBuffer("");
			for(int i = 0 ; i<lowStr.length() ; i++){
				if((int)lowStr.charAt(i)>=97 && (int)lowStr.charAt(i)<=122 ){
					sb.append((char)((int)lowStr.charAt(i)-32));
				}
				else
					sb.append(lowStr.charAt(i));
			}
			upperStr = sb.toString();
			return upperStr;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("瀛楃涓茶浆澶у啓閿欒");
		}
	}
	/**
	 * 澶у啓杞皬鍐?
	 * @param upperStr
	 * @return
	 * @throws Exception
	 */
	public static String toLowerStr(String upperStr) throws Exception
	{
		try {
			StringBuffer sb = new StringBuffer("");
			for(int i = 0 ; i<upperStr.length() ; i++){
				if((int)upperStr.charAt(i)>=65 && (int)upperStr.charAt(i)<=90 ){
					sb.append((char)((int)upperStr.charAt(i)+32));
				}
				else
					sb.append(upperStr.charAt(i));
			}
			return sb.toString();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("瀛楃涓茶浆灏忓啓閿欒");
		}
	}
	/**    
	 *   灏嗗瓧绗︿覆鎴煭锛屽彇鍓峮涓瓧绗︼紝鑻辨枃绠楀崐涓瓧绗︺?    
	 *   @param   orignalString   鍘熷瓧绗︿覆    
	 *   @param   length   闀垮害    
	 *   @param   chopedString   瓒呰繃閮ㄥ垎鐨勮〃绀哄瓧绗︿覆    
	 *   @return   鎴彇鐨勫瓧绗︿覆    
	 */     
	public   static   String   chop(String   orignalString,int   length,String   chopedString)   {    
		if   (orignalString==null||orignalString.length()==0)   {    
			return   orignalString;    
		}    
		orignalString=orignalString.replaceAll("   ","   ");    
		if   (orignalString.length()<length)   {    
			return   orignalString;    
		}    
		StringBuffer   buffer=new   StringBuffer(length);    
		length=length*2;    
		int   count=0;    
		int   stringLength=orignalString.length();    
		int   i=0;    
		for   (;count<length&&i<stringLength;i++)   {    
			char   c=orignalString.charAt(i);    
			if   (c<'\u00ff')   {    
				count++;    
			}   else   {    
				count+=2;    
			}    
			buffer.append(c);    
		}    
		if   (i<stringLength)   {    
			buffer.append(chopedString);    
		}    
		return   buffer.toString();    
	}

	public static boolean StringValidate(String str) {
		if (str != null && !str.trim().equals("") ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字符转Long
	 */
	public static Long StringToLong(List<Integer> bitNumber) {
		Long tl = 0l;
		for (int ti : bitNumber) {
			tl = tl ^ (1l << ti);
		}
		return tl;
	}

	/**
	 * Long转字符
	 * 
	 * @param value
	 * @return
	 */
	public static Map<Integer, Integer> LongToString(Long value) {
		Map<Integer, Integer> m = new HashMap<Integer, Integer>();
		char[] c = Long.toBinaryString(value).toCharArray();
		int index = c.length - 1;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '1') {
				int x = index - i;
				m.put(x, x);
			}
		}
		return m;
	}
	
	/**
	 * 根据正则查找匹配内容
	 * @param oldStr
	 * @param regex
	 * @param i
	 * @return
	 */
	public static String returnRegexStr(String oldStr,String regex,int i) {
		if(oldStr == null){
			return "";
		}
		String str = "";
		Pattern p = Pattern .compile(regex);
		Matcher m = p.matcher(oldStr);
		if(m.find() && i<=m.groupCount()){
			str = m.group(i);
		}
		return str;
	}
	
	/**
	 * 标签处理功能，获取参数
	 * @param parm
	 * @return
	 */
	public static Map<String,Object> getParm(String parm){
		Map<String,Object> parmMap = new HashMap<String,Object>();
		if(stringIsNull(parm)){
			return parmMap;
		}
		String[] parmArray = parm.split(",");
		
		for(int i = 0;i<parmArray.length; i++){
			String[] s = parmArray[i].split("::");
			parmMap.put(s[0], s[1]);
		}
		return parmMap;
	}

	/**
	 * 检查字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean stringIsNull(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 过滤HTML标签，by zhangjuqiang
	 * @param args
	 */
	public static String filterHTML(String str){
		if(str==null){
			return str;
		}
		String regexForHTML="<([^>]*)>|&(.+);";
		Pattern pattern=Pattern.compile(regexForHTML);
		Matcher matcher=pattern.matcher(str);
		StringBuffer sb=new StringBuffer();
		boolean result=matcher.find();
		while(result){
			matcher.appendReplacement(sb, "");
			result=matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			StringUtil su = new StringUtil();
			System.out.println(su.chop("fdafdsafdsa",1,"eeeeeeeeeeeee"));	
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(filterHTML("<font class=\"lists_fleft\">意见列表DDDD&nbsp;</font>"));
		
	}
}
