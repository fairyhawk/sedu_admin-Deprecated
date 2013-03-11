<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>自然留言用户</title>
    <link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">自然留言用户</font>
						<font class="lists_fright"> </font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				<tr>
					<td class="lists_bor"></td>
					<td>
					<form name="searchForm" id="searchForm" action="<%=contextPath%>/crm/naturalMsg!toNaturalMsg.action?queryUsersCondition.currentPage=1" method="post" broder="0">
					
					
						<table width="100%" border="0" cellspacing="1" cellpadding="0" align="left" class="lists">
							<tr align="left" >
								<td align="left"  style="text-align:left">
								<div class="msg-yw">
									<table border="0" class="lists" cellpadding="0" cellspacing="0">
									
										
										<tr>
										<td width="60px">所属项目:</td>
											<td align="left" width="180px" style="text-align:left" >
											<%-- <s:select name="fSortId" id="fSortId" list="courseSortList" listKey="coursesortId" listValue="coursesortName" theme="simple" onchange="onchangeFirstSort(this.value);">
						</s:select>

						<s:select name="sSortId" id="sSortId" list="#{}" headerKey="-1" headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
						</s:select> --%>
												<s:select list="subjectList" listKey="subjectId"   id="subjectList"
													listValue="subjectName"    value="queryUsersCondition.subjectId"
													 onchange="onchangeTeacher(this.value)" theme="simple" name="queryUsersCondition.subjectId"
												headerKey="0" headerValue="---请选择---" ></s:select>
											</td>
											   	<td >&nbsp;&nbsp;&nbsp;姓名:</td>
											<td align="left" class="lists_tleft" colspan="3" style="text-align:left">
												<input type="text" name="queryUsersCondition.realName"  />
												<s:submit value="查询" />
											</td>
											</tr>	
											<tr>
										
										</tr>
									</table>
								</div>
								</td>
                                </tr> 
						</table>
						
						</form>
					</td>
					<td class="lists_tright lists_bor2"></td>
				</tr>
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
    <table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
		<tr class="lists_infobg"><td width="160px;">姓名</td><td width="160px;">邮箱</td><td width="250px;">手机</td><td width="60px;">专业</td><td width="60px;">注册时间</td>
		</tr>
		
		<s:iterator value="page.pageResult" id="user"
										status="status">
				<td><s:property value="#user.realName"/></td>
				<td><s:property value="#user.email"/></td>		
				<td><s:property value="#user.mobile"/></td>
				<td><s:property value="#user.subjectName"/>
				    
				</td>
				<td><s:date name="#user.regTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</s:iterator>					
	</table>
	</td>
						<td width="16" class="lists_tright lists_bor2">
						</td>
					</tr>
					<tr>
						<td>
							<img src="<%=contextPath%>/back/images/tab_18.gif" />
						</td>
						<td class="lists_bottom">
							<jsp:include page="/back/jsp/common/showPage.jsp" />
						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
  </body>
</html>
