<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书籍列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
			
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
					<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
			
		<script type="text/javascript">
		function toDeleteSc(cId){
			if(confirm('是否删除？')){
			$.ajax({
				url: "<%=contextPath%>/kb/studyCourse!deleteStudyCourse.action",
				data : {cId : cId},
				type : "post",
				cache : false,
				dataType : "json",
				error : function(){
				alert('error');
				},
				success:onback
			});
		}
		}
		function onback(result){
				if(result != null  &&  result.returnMessage != ''){
					alert("请删除子项！");
				}else{
					window.location.reload();
				}
		}
		</script>
	</head>
	<body>
		<form name="f1" method="post" action="books!getBooksList.action">
			<div class="main_right">
				<table width="100%" border="0" cellspacing="1" cellpadding="0"
					class="lists">
					<tr >
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">当前位置:[知识体系管理>>科目列表]</font>
							<font class="lists_fright"> </font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12" class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0"
								class="lists_info" onmouseover="changeto()" onmouseout="changeback()">
								<tr>
									<td>
										<input type="button" value="新建科目"
											onclick="document.location.href='<%=contextPath%>/kb/studyCourse!toStudyCourseAdd.action'" />
											
									</td>
									<td colspan="7"></td>
									<td><input type="button" value="返回" onclick="history.go(-1)"/></td>

								</tr>
								<tr>
									<td>
										索引
									</td>
									<td>
										科目名称
									</td>
									<td>
										从属专业
									</td>
									<td>
										包括章数
									</td>
									<td>
										版本
									</td>
									<td>
										创建时间
									</td>
									<td colspan="4">
										操作
									</td>
								</tr>
								<s:iterator value="scList" id="studycourse">
									<s:if test="#studycourse.cIndex!=null">
										<tr>
											<td>
												<s:property value="#studycourse.cIndex" />
											</td>
											<td>
												<s:property value="#studycourse.cName" />
											</td>
											<td>
												<s:property value="#studycourse.pName" />
											</td>
											<td>
												<s:property value="#studycourse.cCtSubject" />
											</td>
											<td>
												<s:property value="#studycourse.cVersion" />
											</td>
											<td>
												<s:date name="#studycourse.cCreatetime"
													format="yyyy-MM-dd HH:mm:ss" />
											</td>
											<td>
												<a href="#" onclick="toDeleteSc(<s:property value="#studycourse.cId" />)">删除</a>
											</td>
											<td>
												<a
													href='studyCourse!toEditStudyCourse.action?studycourse.cId=<s:property value="#studycourse.cId"/>&studycourse.pId=<s:property value="#studycourse.pId"/>'>
													修改</a>
											</td>
											<td>
												<a
													href="knowledge!getKnowledgeTree.action?knowledge.scId=<s:property value="#studycourse.cId"/>">进入结构树</a>
											</td>
										</tr>
									</s:if>
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

						</td>
						<td>
							<img src="<%=contextPath%>/back/images/tab_20.gif" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
