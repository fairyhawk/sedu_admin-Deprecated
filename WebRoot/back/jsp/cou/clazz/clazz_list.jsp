<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>班级列表</title>
		<link rel="stylesheet" type="text/css" media="screen"
			href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		 function freeze(clazzId,obj){
		 		
		 		var ss = obj.innerHTML;
		 		alert(ss);
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 		}else{
		 			vv = '冻结';
		 		}
                $.ajax({
                    url:"<%=contextPath%>/cou/clazz!freezeClazz.action",
                    data:"clazz.clazzId="+clazzId,
                    dataType:"text",
                    type : "post",  
					cache : false, 
                    error:function(){
                    	alert("error");
                    },
                    success:function(msg){
                        obj.innerHTML=vv;
                    }
                });
         }
         
        function All(e){
		    var f=document.getElementById("clazzlist");
		    for(i=0;i<f.elements.length;i++){
			 if(f.elements[i].name=="clazzIds"){
			     f.elements[i].checked=e.checked;}
			 }
		}
		
		function deleteAll(ObjectForm){
			var i = 0;
			var f=document.clazzlist;
			if(f.clazzIds != null){
				if(f.clazzIds.length!=null){
					for(var j=0;j<f.clazzIds.length;j++){
				
						if(f.clazzIds[j].checked == true){
						 i++;
						 }
					}
					}else
					{
						if(f.clazzIds.checked == true)
						{
							i++;
						}		
					}
				if(i==0){
					alert("没有选择需要冻结的选项！");
					return false;
				}
				if(confirm("是否冻结？")){
					clazzlist.action="<%=contextPath%>/cou/clazz!freezeAllClazz.action";
					clazzlist.submit();
				}
			}
		 }
       </script>
	</head>
	<body>
		<div>
		<form id="clazzlist" name="clazzlist" method="post" action="<%=contextPath %>/cou/clazz!listClazzs.action">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="lists">
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">班级列表</font>
					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
				</tr>
				
				
				<tr>
	            <td class="lists_bor"></td>
	            <td>
	              <div class="msg-zy">
				<table class="" border="0" cellspacing="0"  cellpadding="0">
						<tr>
						<td>
							<s:hidden name="queryClazzCondition.currentPage" value="1" />
							所属专业：
							<s:select name="clazz.subjectId" id="clazz.subjectId"
								list="subjectList" listKey="subjectId"
								listValue="subjectName" headerKey="-1" headerValue="请选择"
								theme="simple">
							</s:select>
						</td>
						<td width="20px;"></td>
								<td>
									按课程名：
									<input type="text" name="searchKey" id="searchKey"
										value="${searchKey}" />
								</td>
								<td width="20px;"></td>
								<td>
									<img src="<%=contextPath%>/back/images/add_a.gif" />
								</td>
								<td>
									<a href="javascript:document.clazzlist.submit()">查询</a>
								</td>
								<td width="20px;"></td>
								<td>
										<img src="<%=contextPath%>/back/images/add_a.gif" />
								</td>
								<td>
									<a href="<%=contextPath%>/cou/clazz!toAddClazz.action">添加班级</a>
								</td>
								<td width="20px;"></td>
								<td>
										<img src="<%=contextPath%>/back/images/del_a.gif" />
									</td>
									<td>
										<a href="javascript:deleteAll(document.clazzlist);">批量冻结</a>
									</td>
						</tr>		
						</table>
					 </td>
            <td class="lists_tright lists_bor2"></td>
        </tr>
				
				
				
				
				<tr>
					<td width="12" class="lists_bor">
					</td>
					<td>
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" onmouseover="changeto()"
							onmouseout="changeback()">
							<tr class="lists_infobg">
								<td>
									<input type="checkbox" name="c1" onclick="All(this);" />
								</td>
								<td>
									课程名
								</td>

								<td>
									状态
								</td>

								<td>
									添加时间
								</td>


								<td>
									操作
								</td>
							</tr>
							<s:iterator value="page.pageResult" id="clazz">
								<tr>
									<td>
										<input type="checkbox" name="clazzIds"
											value='<s:property value="#clazz.clazzId"/>' />
									</td>
									<td>
										<s:property value="#clazz.title" />
									</td>

									<td>
										<s:if test="#clazz.status==0">
							  正常
							</s:if>
										<s:if test="#clazz.status==1">
							  冻结
							</s:if>
									</td>

									<td>
										<s:date name="#clazz.addTime" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td>
										<a
											href="<%=contextPath%>/cou/clazz!toUpdateClazz.action?clazz.clazzId=<s:property value='#clazz.clazzId'/>">修改</a>

										<s:if test="#clazz.status==0">
											<a href='#' onclick="freeze(${clazz.clazzId},this);">冻结</a>
										</s:if>
										<s:if test="#clazz.status==1">
											<a href='#'
												onclick="freeze(<s:property value="#clazz.clazzId"/>,this);">解冻</a>
										</s:if>

										<a
											href="<%=contextPath%>/cou/clazz!toClazzCourse.action?queryClazzCouCondition.currentPage=1&clazz.clazzId=<s:property value='#clazz.clazzId'/>">班级课程管理</a>
									</td>
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
			</form>
		</div>
	</body>
</html>
