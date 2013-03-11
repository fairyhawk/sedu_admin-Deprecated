package com.shangde.common.util;


/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */


import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class KeyWordFilter {
    private static Pattern pattern = null;

    static {
        StringBuffer patternBuf = new StringBuffer();
        try {
            InputStream in = KeyWordFilter.class.getClassLoader().getResourceAsStream("words.properties");
            Properties properties = new Properties();
            properties.load(in);
            
            Enumeration<?> enu = properties.propertyNames();
            while (enu.hasMoreElements()) {
                patternBuf.append((String) enu.nextElement() + "|");    //读取所有properties里的词，以 | 分隔
            }
            
            patternBuf.deleteCharAt(patternBuf.length() - 1);
            
            //默认下，properties文件读取编码： ISO8859-1
            pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO8859-1"), "UTF-8"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String doFilter(String str) {
        System.out.println("str:" + str);
        try {
            Matcher m = pattern.matcher(str);
            str = m.replaceAll("**");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}

