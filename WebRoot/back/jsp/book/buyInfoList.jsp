<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>图书</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		function allCheck(cb) {
			$("input[name=ids]:checkbox").attr('checked', cb.checked);
		}
		function delBook(bookName,bookId) {
			if(window.confirm("请注意:你将要删除商品【"+bookName+"】,删除后将不能恢复，请确认删除？")) {
				var url="${pageUrlParms}";
				document.bookForm.action = "<%=contextPath%>/book/buyInfo!bookInfoUpdate.action?buyInfo.id="+bookId+"&status=1&url="+url;
				document.bookForm.submit();
			}
		}
		function allUpdateState(state){
			var num=0;
			var check=document.getElementsByName("ids");
	        for(var i=0;i<check.length;i++){
			if(check[i].checked==true)num++;
			}
	        if(num==0){alert("请至少选择一条信息");return;}
			var info="";
			if(state==2)
				info="确实要冻结这些商品吗";
			if(state==1)
				info="确实要解冻这些商品吗";
			if(state==3)
				info="确实要删除这些商品吗";
			if(window.confirm(info)) {
				var url="${pageUrlParms}";
				document.bookForm.action ="<%=contextPath%>/book/buyInfo!bookInfoUpdate.action?status=1&Batch=Batch&url="+url;
				document.bookForm.submit();				
			}
		}
	</script>

</head>
<body>
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_03.gif" /></td>
			<td class="lists_top">
				<font class="lists_fleft">图书订单列表</font>
				<font class="lists_fright"><img src="<%=contextPath%>/back/images/edt_tbl.gif" /><a href="###" onclick="allUpdateState(3)">删除</a></font>
			</td >
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_07.gif" /></td>
		</tr>
		<tr>
			<td class="lists_bor"></td>
			<td>
			<form name="bookForm" method="post">
				<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" >
					<tr class="lists_infobg">
								<td width="5%"><input type="checkbox"
									onclick="allCheck(this)" />ID号</td>
								<td width="5%">收货人姓名</td>
								<td width="20%">收货地址</td>
								<td width="10%">电话</td>
								<td width="10%">手机</td>
								<td width="10%">邮编</td>
								<td width="15%">书名</td>
								<td width="5%">购买数量</td>
								<td width="10%">创建日期</td>
								<td width="5%">备注</td>
								<td width="5%">操作</td>							
							</tr>
						
							<s:iterator value="page.pageResult" id="buyInfo">
								<tr>
								<td width="5%"><input type="checkbox" name="ids"
										value='<s:property value="#buyInfo.id"/>' /> <s:property value="#buyInfo.id"/></td>
								<!--
								<td width="5%">${buyInfo.cusName}</td>
								<td width="20%">${buyInfo.address}</td>
								<td width="10%">${buyInfo.tel}</td>
								<td width="10%">${buyInfo.mobilephone}</td>
								<td width="10%">${buyInfo.postalcode}</td>
								<td width="15%">${buyInfo.bookname}</td>
								<td width="5%">${buyInfo.buySum}</td>
								-->
								<td width="5%"><s:property value="#buyInfo.cusName"/></td>
								<td width="20%"><s:property value="#buyInfo.address"/></td>
								<td width="10%"><s:property value="#buyInfo.tel"/></td>
								<td width="10%"><s:property value="#buyInfo.mobilephone"/></td>
								<td width="10%"><s:property value="#buyInfo.postalcode"/></td>
								<td width="15%"><s:property value="#buyInfo.bookname"/></td>
								<td width="5%"><s:property value="#buyInfo.buySum"/></td>
								<td width="10%">
								<s:date name="createtime" format="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td width="5%"> <s:property value="#buyInfo.remark"/></td>
								<td width="5%">
									<a href="###" onclick="delBook('<s:property value="#buyInfo.bookname"/>','<s:property value="#buyInfo.id"/>')">删除</a>
								</td>
								</tr>
							</s:iterator>
			</table>
			</form>
			</td>
			<td class="lists_tright lists_bor2"></td>
		</tr>
		<tr>
			<td class="td_wid_l"><img src="<%=contextPath%>/back/images/tab_18.gif"/></td>
			<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
			<td class="td_wid_r"><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
		</tr>
</table>

</body>
</html>