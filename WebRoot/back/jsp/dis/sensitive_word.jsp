<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>敏感词设置</title>
	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script type="text/javascript">
		function check(){
			var $word = $("#word").attr("value");
			var word = $word;
			if($word==""){
				alert("请输入敏感词！");
				return false;
			}
			var txt=new RegExp("[ ,\\`,\\~,\\!,\\@,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\.,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
			if(txt.test($word)){
				alert("敏感字不能含有特殊字符");
				return false;
			}
			if($word.length>25){
				alert("敏感字长度不能大于25个字符！");
				return false;
			}
		}

        function del(id,page){
		    if(confirm("确定删除吗?")){
				window.location.href="<%=contextPath%>/dis/back!delDisWordById.action?id="+id+"&page.currentPage="+page;	
			}
        }
		
	</script>
</head>
<body>
	<div>
		<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr>
					<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
					<td class="lists_top">
							<font class="lists_fleft">敏感词设置</font>
							<font class="lists_fright"></font>	
					</td>
					<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
				</tr>
				<tr>		
					<td class="lists_bor"></td>
					<td>
							<form action="<%=contextPath%>/dis/back!addSensWord.action" method="post" onsubmit="return check()">
								<input type="hidden" name="page.currentPage" value="1"/><!-- 添加后回到第一页 -->
								<table border="0" width="100%">
									<tr align="center">
										<td align="center" style="color: red">*提示：敏感词在用户发布时会被自动替换成"***"</td>
									</tr>
									<tr>
										<td align="center">新增敏感词：<input type="text" name="word" id="word" /> 
											<input type="submit" value="添 加" />
										</td>
									</tr>
								</table>
							</form>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				
				<tr>		
					<td class="lists_bor"></td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
								<tr class="lists_infobg">
									<td>ID</td>
									<td>敏感词</td>
									<td>添加时间</td>
									<td>操作</td>
								</tr>
								<s:iterator value="page.pageResult" var="dis" status="index">
								<tr>
									<td><s:property value="#index.index+1+((page.currentPage-1)*page.pageSize)" /></td>
									<td><s:property value="#dis.word" /> </td>
									<td><s:date name="#dis.createTime" format="yyyy-MM-dd HH:mm"/> </td>
									<td>
									    <a href="#" onclick="del('<s:property value="#dis.id" />','<s:property value="page.currentPage" />');">删除</a>
									</td>
								</tr>
								</s:iterator>
						</table>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">
						<s:if test="page.totalPage > 1">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</s:if>
					</td>
					<td class="td_wid_r">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
		</table>
		</div>
	</body>
</html>