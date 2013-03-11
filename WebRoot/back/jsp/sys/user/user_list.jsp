<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
		<script type="text/javascript">
		 function freeze(userId,obj){
		 		var ss = obj.innerText;
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 		}else{
		 			vv = '冻结';
		 		}
                $.ajax({
                    url:"<s:url action="user!freezeUser"/>",
                    data:"user.userId="+userId,
                    dataType:"text",
                    success:function(msg){
                        obj.innerText=vv;
                    }
                });
            }

			function search(){
				var $searchKey = $("#searchKey").val();
				var searchKey = $.trim($searchKey) 
				if(searchKey==""){
					alert("请输入用户名/姓名！");
				}else{
					searchKey = encodeURIComponent(encodeURIComponent(searchKey));
					window.location="<%=contextPath%>/sys/user!listUserByKey.action?queryUserCondition.currentPage=1&searchKey="+searchKey;
				}
			}
       </script>
	</head>
	<body>

  <!-- start content -->
  <div id="right-content">
    <!--  right 拷贝开始 -->
    <table width="100%" cellspacing="0" cellpadding="0" border="0" class="lists">
      <tbody>
        <tr>
          <td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif"></td>
          <td class="lists_top"><font class="lists_fleft"> 用户列表 </font></td>
          <td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif"></td>
        </tr>
        <tr>
          <td class="lists_bor"></td>
          <td><div class="msg-xt">
              <table width="100%" cellspacing="1" cellpadding="0" border="0">
                <tr>
                  <td> 
                  <font class="lists_fleft">
					<table class="lists_fleft" border="0" cellspacing="0"  cellpadding="0"><tr><td>
							按用户名/姓名检索：
							<input type="text" name="searchKey" id="searchKey" value="${searchKey}"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</td></tr></table>
					<table class="lists_fleft" width="60" border="0" cellspacing="0"  cellpadding="0"><tr><td><img src="<%=contextPath%>/back/images/add_a.gif"/></td><td><a href="javascript:search()">查询</a></td></tr></table>
					</font>
                </tr>
              </table>
            </div></td>
          <td class="lists_tright lists_bor2"></td>
        </tr>
        <tr>
          <td class="lists_bor"></td>
          <td>
          <table width="100%" cellspacing="1" cellpadding="0" border="0" class="lists_info">
              <tbody>
                <tr class="lists_infobg">
                  <td>
                        用户名
					</td>
					<td>
						真实姓名
					</td>
					<td>
						用户组
					</td>
					<td>
						状态
					</td>
					<td>
						联系电话
					</td>
					<td>
						加入时间
					</td>
					<s:if test="queryUserCondition.userType==1">
						<td>
							权限明细
						</td>
					</s:if>
					<td>
						操作
					</td>
                </tr>
                <s:iterator value="page.pageResult" id="user">
						<tr>
							<td>
								<s:property value="loginName" />
							</td>
							<td>
								<s:property value="userName" />
							</td>
							<td>
								<s:property value="group.groupName" />
							</td>
							<td>
								<s:if test="#user.status==0">
								  正常
								</s:if>
								<s:if test="#user.status==1">
								  冻结
								</s:if>
							</td>
							<td>
								<s:property value="tel" />
							</td>
							<td>
								<s:date name="createTime" format="yyyy:MM:dd HH:mm:ss"/>
							</td>
							<td>
								<s:if test="queryUserCondition.isSold==1">
								<a	href='user!toEditUser.action?user.userId=<s:property value="userId"/>'>修改</a>
								<a href='user!toUpdatePwd.action?user.userId=<s:property value="userId"/>'>修改密码</a>
								<s:if test="usersId==433"><a href='user!toEditUserGradeSubjectRoleSec.action?user.userId=<s:property value="userId"/>'>角色对应范围修改</a></s:if>
								
								</s:if>
								<s:else>
									<a	href='user!toEditUserGradeSubjectRoleSec.action?user.userId=<s:property value="userId"/>'>角色对应范围修改</a>
									<a	href='user!toEditUser.action?user.userId=<s:property value="userId"/>'>修改</a>
									<s:if test="#user.status==0">
										<a href='#' onclick="freeze(${user.userId},this);">冻结</a>	
									</s:if>
									<s:if test="#user.status==1">
										<a href='#' onclick="freeze(${user.userId},this);">解冻</a>	
									</s:if>
									<s:if test="queryUserCondition.userType==1">
										<a
											href='user!deleteUser.action?user.userId=<s:property value="userId"/>'>删除</a>
									</s:if>
									<a href='user!toUpdatePwd.action?user.userId=<s:property value="userId"/>'>修改密码</a>
									<a href='user!toSetUserContentRole.action?user.userId=<s:property value="userId"/>'>内容管理权限修改</a>
									<a href="loginLog!getByUserId.action?queryLoginLogCondition.currentPage=1&queryLoginLogCondition.userId=<s:property value="userId"/>">查看登录日志</a>
								
								</s:else>
								</td>
						</tr>
					</s:iterator>
    
              </tbody>
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
</div>
    <!--  right 拷贝结束 -->

	</body>
</html>
