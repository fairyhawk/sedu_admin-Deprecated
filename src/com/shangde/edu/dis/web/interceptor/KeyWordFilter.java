package com.shangde.edu.dis.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shangde.edu.dis.domain.DisWord;
import com.shangde.edu.dis.service.IDisWord;

/**
 * 过滤敏感词
 * @author changfu.dai
 *
 */
public class KeyWordFilter {
	
	private IDisWord disWordService;
	private static KeyWordFilter instance = null ;
	private StringBuffer patternBuf = null;
	private Pattern pattern = null;
	
	public static KeyWordFilter getInstance(IDisWord disWordService) {   
		      if (instance == null) {  
		            instance = new KeyWordFilter(disWordService); 
		      }   
		      return instance;   
	}
	
	/**
	 *	更新数据时重新new敏感词过滤对象
	 */
	public void init(){
		creatKeyWords();
	}
	/**
	 * 从数据库初始化正则表达式字符串
	 * @param disWordService 敏感词实现对象
	 */
	public KeyWordFilter(IDisWord disWordService){
		this.disWordService = disWordService;
		init();
	}
	
	/**
	 * 创建和更新关键词正则表达式
	 */
	private void creatKeyWords(){
		patternBuf = new StringBuffer("");
		List<DisWord> dwList = disWordService.getAllDisWord();
		if(dwList.size()>0){
			patternBuf.append("(");
			for (DisWord word : dwList) {
				patternBuf.append(word.getWord()+"|");
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			patternBuf.append(")");
			try {
				String pt = new String(patternBuf.toString().getBytes("utf-8"), "utf-8");
				pattern = Pattern.compile(pt, Pattern.CASE_INSENSITIVE);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			pattern = null;
		} 
	}
	
	/**
	 * 过滤敏感词
	 * @param str	要过滤的字符串
	 * @return 	过滤后的字符串
	 */
	public String doFilter(String str) {
		if(pattern!=null&&str!=null){
			Matcher m = pattern.matcher(str);
			str = m.replaceAll("**");
		}
		return str;
	}

}
