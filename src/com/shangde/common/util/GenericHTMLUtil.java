package com.shangde.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.shangde.edu.cms.domain.Article;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class GenericHTMLUtil {

	/**
	 * 生成列表页html
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getArticleListHTML(String articleArray, String path, String columnName) throws UnsupportedEncodingException {
		StringBuffer html = new StringBuffer("");
		html.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>\r\n");
		html.append("<html xmlns='http://www.w3.org/1999/xhtml'>\r\n");
		html.append("<head>\r\n");
		html.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />\r\n");
		html.append("<title>文章频道列表页</title>\r\n");
		html.append("<link rel='stylesheet' media='all' type='text/css' href='../../..//web/style/public.css' />\r\n");
		html.append("<link rel='stylesheet' media='all' type='text/css' href='../../..//web/style/course_list.css' />\r\n");
		html.append("<script type='text/javascript' src='../../..//back/script/jquery-1.3.2.js'></script> \r\n");
		html.append("<script type='text/javascript'>\r\n");
		html.append("	var articleList = [\r\n");
		html.append(articleArray);
		html.append("	];\r\n");
		html.append("	$().ready(function() {\r\n");
		html.append("		goPage(1);\r\n");
		html.append("	});\r\n");
		html.append("	\r\n");
		html.append("	var total = articleList.length;\r\n");
		html.append("	var size = 10;\r\n");
		html.append("	var totalPage = Math.ceil(total/size);\r\n");
		html.append("	\r\n");
		html.append("	function goPage(pageNo) {\r\n");
		html.append("		showPages(pageNo);\r\n");
		html.append("		showArticle(pageNo);\r\n");
		html.append("	}\r\n");
		html.append("	\r\n");
		html.append("	function showPages(pageNo) {\r\n");
		html.append("		var maxNum = pageNo > 4 ? 6 : 11 - pageNo;\r\n");
		html.append("		var html = '';\r\n");
		html.append("		if(pageNo == 1) {\r\n");
		html.append("			html += '<span class=disabled> 上一页 </span>';\r\n");
		html.append("		} else {\r\n");
		html.append("			html += '<a href=javascript:void(0); onclick=goPage(' + (pageNo-1 ) + ')> 上一页 </a>';\r\n");
		html.append("		}\r\n");
		html.append("		if(pageNo > 5) {\r\n");
		html.append("   			html += '| ';\r\n");
		html.append("   		}\r\n");
		html.append("   		for(var i = 4; i > 0; i--) {\r\n");
		html.append("   			if(pageNo > i) {\r\n");
		html.append("   				html += '<a href=javascript:void(0); onclick=goPage(' + (pageNo-i ) + ')>' + (pageNo-i) + '</a>';\r\n");
		html.append("   			}\r\n");
		html.append("   		}\r\n");
		html.append("  	 	html += '<span class=current>'+pageNo+'</span>';\r\n");
		html.append("	   	for(var i = 1; i < maxNum; i++) {\r\n");
		html.append("	   		if(pageNo + i <= totalPage) {\r\n");
		html.append("	   			html += '<a href=javascript:void(0); onclick=goPage(' + (pageNo+i) + ')>' + (pageNo+i) + '</a>';\r\n");
		html.append("	   		} else {\r\n");
		html.append("	   			break;\r\n");
		html.append("	   		}\r\n");
		html.append("	   	}\r\n");
		html.append("   		if(pageNo + maxNum <= totalPage) {\r\n");
		html.append("	   		html += '| ';\r\n");
		html.append("	   	}\r\n");
		html.append("	   	if(pageNo == totalPage) {\r\n");
		html.append("			html += '<span class=disabled> 下一页 </span>';\r\n");
		html.append("		} else {\r\n");
		html.append("			html += '<a href=javascript:void(0); onclick=goPage(' + (pageNo+1) + ')> 下一页 </a>';\r\n");
		html.append("	   	}\r\n");
		html.append("	   	\r\n");
		html.append("	   	if(pageNo == 1) {\r\n");
		html.append("			html += '<span class=disabled> 首页 </span>';\r\n");
		html.append("		} else {\r\n");
		html.append("			html += '<a href=javascript:void(0); onclick=goPage(' + 1 + ')> 首页 </a>';\r\n");
		html.append("		}\r\n");
		html.append("	   	if(pageNo == totalPage) {\r\n");
		html.append("			html += '<span class=disabled> 尾页 </span>';\r\n");
		html.append("		} else {\r\n");
		html.append("			html += '<a href=javascript:void(0); onclick=goPage(' + totalPage + ')> 尾页 </a>';\r\n");
		html.append("	   	}\r\n");
		html.append("		html += ' 共' + totalPage + '页';\r\n");
		html.append("		$('#page_div').html(html);\r\n");
		html.append("	}\r\n");
		html.append("\r\n");
		html.append("	function showArticle(pageNo) {\r\n");
		html.append("		var html = '';\r\n");
		html.append("		if(pageNo <totalPage) {\r\n");
		html.append("			for(var i = (pageNo-1) * size; i < pageNo*size; i++) {\r\n");
		html.append("				html += '<li><span>' + articleList[i][0] + '</span><a href="+path+"/"+"' + articleList[i][1] + '.shtml target=_blank>' + articleList[i][2] + '</a></li>';\r\n");
		html.append("			}	\r\n");
		html.append("		} else {\r\n");
		html.append("			for(var i=(pageNo-1) * size; i < total; i++) {\r\n");
		html.append("				html += '<li><span>' + articleList[i][0] + '</span><a href="+path+"/"+"' + articleList[i][1] + '.shtml target=_blank>' + articleList[i][2] + '</a></li>';\r\n");
		html.append("			}	\r\n");
		html.append("		}\r\n");
		html.append("		$('#article_ul').html(html);\r\n");
		html.append("	}\r\n");
		html.append("</script>\r\n");
		html.append("</head>\r\n");
		html.append("\r\n");
		html.append("<body>\r\n");
		html.append("<!-- 页头 -->\r\n");
		html.append("<div class='top_nav'> \r\n");
		html.append("  <!-- LOGO --> \r\n");
		html.append("  <span></span>\r\n");
		html.append("  <!-- LOGO //--> \r\n");
		html.append("  <!-- 导航 --> \r\n");
		html.append("  <a href='../../..//web/jsp/index.jsp' class='top_nav_1on' title='首页'>首页</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindaoTest.action?course.subjectId=7' class='top_nav_2' title='注册会计师'>注册会计师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=3' class='top_nav_3' title='会计资格证'>会计资格证</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=5' class='top_nav_4' title='司法考试'>司法考试</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=1' class='top_nav_5' title='人力资源'>人力资源</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=2' class='top_nav_6' title='心理咨询师'>心理咨询师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=6' class='top_nav_7' title='迷你课程'>迷你课程</a> \r\n");
		html.append("  <!-- 导航 //--> \r\n");
		html.append("</div>\r\n");
		html.append("<div class='clear'></div>\r\n");
		html.append("<!-- 页头 //--> \r\n");
		html.append("<!-- banner -->\r\n");
		html.append("<div class='course_list_banner news_banner_bg'>文章列表</div>\r\n");
		html.append("<!-- banner //-->\r\n");
		html.append("<div class='clear'></div>\r\n");
		html.append("<div class='main'>  \r\n");
		html.append("\r\n");
		html.append("  <!-- 标签 -->\r\n");
		html.append("  <div class='course_list'>\r\n");
		html.append("    <div class='course_list_tag'>\r\n");
		html.append("      <div><a href='#'>");
		html.append(columnName);
		html.append("</a></div>\r\n");
		html.append("      <span class='more'><a href='");
		html.append("../../..//static/article/column_list.shtml");
		html.append("' target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class='news_box_list news_box_full'>\r\n");
		html.append("    	<ul id='article_ul'>\r\n");
		html.append("        </ul>\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("  <!-- 标签 // -->    \r\n");
		html.append("  <div class='clear'></div>\r\n");
		html.append("  <!-- 分页 --> \r\n");
		html.append("  <div class='manu' id='page_div'>\r\n");
		html.append("  </div>\r\n");
		html.append("  <!-- 分页 //--> \r\n");
		html.append("  \r\n");
		html.append("</div>\r\n");
		html.append("<!-- 页尾 -->\r\n");
		html.append("<div class='foot'><img src='../../..//web/images/foot.gif' /></div>\r\n");
		html.append("<!-- 页尾 //-->\r\n");
		html.append("</body>\r\n");
		html.append("</html>\r\n");
		return html.toString();
	}
	
	/**
	 * 生成文章内容html
	 * @param title
	 * @param content
	 * @return
	 */
	public static String getContentFrame(Article article, int currentPage, int totalPage) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer html = new StringBuffer("");
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		html.append("<head>\r\n");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
		html.append("<title>\r\n");
		html.append(article.getTitle());
		html.append("</title>\r\n");
		html.append("<link rel=\"stylesheet\" media=\"all\" type=\"text/css\" href=\"../../..//web/style/public.css\" />\r\n");
		html.append("<link rel=\"stylesheet\" media=\"all\" type=\"text/css\" href=\"../../..//web/style/course_list.css\" />\r\n");
		
		html.append("<script type=\"text/javascript\" src=\"../../..//back/script/jquery-1.3.2.js\"></script>\r\n");
		html.append("<script type=\"text/javascript\">\r\n");
		html.append("	var totalPage = " + totalPage + ";\r\n");
		html.append("	var currentPage = " + currentPage + ";\r\n");
		html.append("	$().ready(function() {\r\n");
		html.append("		var html = '';\r\n");
		html.append("		for(var i=1; i<totalPage+1; i++) {\r\n");
		html.append("			if(i == currentPage) {\r\n");
		html.append("				html += \"<span class=current>\"+currentPage+\"</span>\";\r\n");
		html.append("			} else {\r\n");
		html.append("				html += \"<a href='../../..//static/article/"+getDirName(article.getColumnId())+"/"+article.getArticleId()+"_\"+i+\".shtml'>\" + i + \"</a>\";\r\n");
		html.append("			}\r\n");
		html.append("		}\r\n");
		html.append("		$(\"#page_div\").html(html);\r\n");
		html.append("	});\r\n");
		html.append("</script>\r\n");
		
		html.append("</head>\r\n");
		html.append("\r\n");
		html.append("<body>\r\n");
		html.append("<!-- 页头 -->\r\n");
		html.append("<div class=\"top_nav\"> \r\n");
		html.append("  <!-- LOGO --> \r\n");
		html.append("  <span></span>\r\n");
		html.append("  <!-- LOGO //--> \r\n");
		html.append("  <!-- 导航 --> \r\n");
		html.append("  <a href='../../..//web/jsp/index.jsp' class='top_nav_1on' title='首页'>首页</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindaoTest.action?course.subjectId=7' class='top_nav_2' title='注册会计师'>注册会计师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=3' class='top_nav_3' title='会计资格证'>会计资格证</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=5' class='top_nav_4' title='司法考试'>司法考试</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=1' class='top_nav_5' title='人力资源'>人力资源</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=2' class='top_nav_6' title='心理咨询师'>心理咨询师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=6' class='top_nav_7' title='迷你课程'>迷你课程</a> \r\n");
		html.append("  <!-- 导航 //--> \r\n");
		html.append("</div>\r\n");
		html.append("<div class=\"clear\"></div>\r\n");
		html.append("<!-- 页头 //--> \r\n");
		html.append("<!-- banner -->\r\n");
		html.append("<div class=\"course_list_banner news_banner_bg\">文章内容</div>\r\n");
		html.append("<!-- banner //-->\r\n");
		html.append("<div class=\"clear\"></div>\r\n");
		html.append("<div class=\"main\">  \r\n");
		html.append("	<div class=\"news_page\">\r\n");
		html.append("    	<div class=\"news_page_title\">\r\n");
		html.append(article.getTitle());
		html.append("</div>\r\n");
		html.append("        <div class=\"news_page_info\">发布者：\r\n");
		html.append(article.getAuthor());
		html.append("  发表日期:\r\n");
		html.append(format.format(article.getCreateDate()));
		html.append("   点击次数:\r\n");
		html.append(article.getClickTimes());
		html.append("次</div>\r\n");
		html.append("        <div class=\"news_page_content\">\r\n");
		html.append(article.getContent());
		html.append("        </div>\r\n");
		html.append("    </div>  \r\n");
		html.append(" <div class=\"manu_center\" id=\"page_div\">\r\n");
		html.append(" </div>\r\n");
		html.append("</div><!-- main //-->\r\n");

		html.append("<!-- 页尾 -->\r\n");
		html.append("<div class=\"foot\"><img src=\"../../..//web/images/foot.gif\" /></div>\r\n");
		html.append("<!-- 页尾 //-->\r\n");
		html.append("</body>\r\n");
		html.append("</html>\r\n");
		return html.toString();
	}
	
	public static String getColumnListHTML(List<List<Article>> list) throws UnsupportedEncodingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer html = new StringBuffer("\r\n");
		html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
		html.append("<head>\r\n");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n");
		html.append("<title>文章列表</title>\r\n");
		html.append("<link rel=\"stylesheet\" media=\"all\" type=\"text/css\" href=\"../../..//web/style/public.css\" />\r\n");
		html.append("<link rel=\"stylesheet\" media=\"all\" type=\"text/css\" href=\"../../..//web/style/course_list.css\" />\r\n");
		html.append("</head>\r\n");
		html.append("\r\n");
		html.append("<body>\r\n");
		html.append("<!-- 页头 -->\r\n");
		html.append("<div class=\"top_nav\"> \r\n");
		html.append("  <!-- LOGO --> \r\n");
		html.append("  <span></span>\r\n");
		html.append("  <!-- LOGO //--> \r\n");
		html.append("  <!-- 导航 --> \r\n");
		html.append("  <a href='../../..//web/jsp/index.jsp' class='top_nav_1on' title='首页'>首页</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindaoTest.action?course.subjectId=7' class='top_nav_2' title='注册会计师'>注册会计师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=3' class='top_nav_3' title='会计资格证'>会计资格证</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=5' class='top_nav_4' title='司法考试'>司法考试</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=1' class='top_nav_5' title='人力资源'>人力资源</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=2' class='top_nav_6' title='心理咨询师'>心理咨询师</a> ");
		html.append("<a href='../../..//cou/courseweb!toPreparePindao.action?course.subjectId=6' class='top_nav_7' title='迷你课程'>迷你课程</a> \r\n");
		html.append("  <!-- 导航 //--> \r\n");
		html.append("</div>\r\n");
		html.append("<div class=\"clear\"></div>\r\n");
		html.append("<!-- 页头 //--> \r\n");
		html.append("<!-- banner -->\r\n");
		html.append("<div class=\"course_list_banner news_banner_bg\">栏目列表</div>\r\n");
		html.append("<!-- banner //-->\r\n");
		html.append("<div class=\"clear\"></div>\r\n");
		html.append("<div class=\"main\">  \r\n");
		html.append("\r\n");
		html.append("  <!-- 标签 -->\r\n");
		html.append("  <div class=\"course_list news_box\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"../../..//static/article/7/article_list.shtml\" target=_blank>注册会计师</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/7/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(0).size(); i++) {
			Article article = list.get(0).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/7/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>		\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("  <div class=\"course_list news_box marl\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"../../..//static/article/3/article_list.shtml\" target=_blank>会计资格证</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/3/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(3).size(); i++) {
			Article article = list.get(3).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/3/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>		\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("  <!-- 标签 // -->\r\n");
		html.append("  \r\n");
		html.append("  <!-- 标签 -->\r\n");
		html.append("  <div class=\"course_list news_box\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"../../..//static/article/5/article_list.shtml\" target=_blank>司法考试</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/5/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(4).size(); i++) {
			Article article = list.get(4).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/5/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>		\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("\r\n");
		html.append("  <div class=\"course_list news_box marl\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"index.html\" target=_blank>人力资源</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/1/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(2).size(); i++) {
			Article article = list.get(2).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/1/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>		\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("  <!-- 标签 // -->\r\n");
		html.append("  \r\n");
		html.append("  <!-- 标签 -->\r\n");
		html.append("  <div class=\"course_list news_box\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"../../..//static/article/2/article_list.shtml\" target=_blank>心理咨询师</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/2/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(1).size(); i++) {
			Article article = list.get(1).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/2/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("\r\n");
		html.append("  <div class=\"course_list news_box marl\">\r\n");
		html.append("    <div class=\"course_list_tag\">\r\n");
		html.append("      <div><a href=\"../../..//static/article/6/article_list.shtml\" target=_blank>迷你课程</a></div>\r\n");
		html.append("      <span class=\"more\"><a href=\"");
		html.append("../../..//static/article/6/article_list.shtml");
		html.append("\" target=_blank>点击查看更多</a></span>\r\n");
		html.append("    </div>\r\n");
		html.append("	<div class=\"news_box_list\">\r\n");
		html.append("    	<ul>\r\n");
		for(int i=0; i<list.get(5).size(); i++) {
			Article article = list.get(5).get(i);
			html.append("        	<li><span>");
			html.append(format.format(article.getCreateDate()));
			html.append("</span><a href=\"");
			html.append("../../..//static/article/6/");
			html.append(article.getArticleId());
			html.append("_1.shtml");
			html.append("\" target=\"_blank\">");
			html.append(article.getTitle());
			html.append("</a></li>\r\n");
		}
		html.append("        </ul>\r\n");
		html.append("    </div>\r\n");
		html.append("  </div>\r\n");
		html.append("  <!-- 标签 // -->\r\n");
		html.append("  \r\n");
		html.append("  <div class=\"clear\"></div>\r\n");
		html.append("</div>\r\n");
		html.append("<!-- 页尾 -->\r\n");
		html.append("<div class=\"foot\"><img src=\"../../..//web/images/foot.gif\" /></div>\r\n");
		html.append("<!-- 页尾 //-->\r\n");
		html.append("</body>\r\n");
		html.append("</html>\r\n");

		return html.toString();
	}
	
	/**
	 * 根据栏目id获取文件夹名称
	 * @return
	 */
	public static int getDirName(int columnId) {
		switch(columnId) {
			case 2:
				return 7;
			case 3:
				return 2;
			case 4:
				return 1;
			case 5:
				return 3;
			case 6:
				return 5;
			case 7:
				return 6;
			default:
				return 4;
		}
	}
	
	public static void main(String args) throws IOException {
		File file = new File("D:/Program Files/work space../../..//WebRoot/static/article/article_list.html\r\n\r\n");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String str = null;
		while((str = reader.readLine())!=null) {
			str=str.replace("\"", "'\r\n\r\n");
			System.out.println("html.append(\""+str+"\")\r\n");
		}
	}
}
