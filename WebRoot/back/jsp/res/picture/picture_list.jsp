<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>书籍列表</title>
		<link href="<%=contextPath%>/back/style/data_table.css" rel="stylesheet"	type="text/css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/back_util.js"></script>
	<script language="JavaScript">
	function All(e)
		{
	    var f=window.f1;
	    for(i=0;i<f.elements.length;i++){
		 if(f.elements[i].name=="bkId"){
		      		f.elements[i].checked=e.checked;}
					}
		}
		
	function deleteAll(ObjectForm){
	var i = 0;
	var f=document.f1;
	if(f.bkId.length!=null){
		for(var j=0;j<f.bkId.length;j++){
	
			if(f.bkId[j].checked == true){
			 i++;
			 }
		}
		}else
		{
			if(f.bkId.checked == true)
			{
				i++;
			}		
		}
		if(i==0){
			alert("没有选择需要删除的选项！");
			return false;
		}
	if(confirm("是否删除？")){
		ObjectForm.action="<%=contextPath%>/res/books!deleteAllBooks.action";
		ObjectForm.submit();
	}
}	
	</script>
	</head>
	<body>
	<div id="rightframe">
	       <div class="rf_title">
				<h2>
					用户列表
				</h2>
			</div>
			<table class="com_table">
				<tr>
					<td>
					
						<!--<s:hidden name="queryUserCondition.currentPage" value="1"/>-->
						<table>
							<tr>
								<td>
									按用户名/姓名检索
									<input type="text" name="searchKey" id="searchKey" value="${searchKey}"/>
								</td>
								<td>
									<input type="submit" value="查询" />
								</td>
								<td>
								    <input type="button" name="b1" value="添加" onclick="document.location.href='<%=contextPath%>/res/books!toAddBooks.action'" />
								</td>
								<td>
								    <input type="button" name="b1" value="多选删除" onclick="deleteAll(document.f1);" />
								</td>
							</tr>
						</table>
					
					</td>
					<s:if test="queryUserCondition.userType==0">
					<td>
						<input type="button" value="添加用户" onclick="document.location.href='<%=contextPath%>/sys/user!userAdd.action'" />
					</td>
					</s:if>
				</tr>
			</table>
			<form name="f1"  method="post">
			<table class="com_table" onmouseover="changeto()" onmouseout="changeback()">
				<tr>
				   <td>
				   <input type="checkbox" name="c1" onclick="All(this);"/>
				   </td>
					<td>
						书名
					</td>
					<td>
						作者
					</td>
					<td>
						类型
					</td>
					<td>
						标题
					</td>
					<td>
						价格
					</td>
					<td>
						促销方式
					</td>
					<td>
						折扣价格
					</td>
					<td>
						出版社
					</td>
					<td>
						图书条码
					</td>
					<td>
						上传时间
					</td>
				
					<td colspan="2">
						操作
					</td>
				</tr>
				<s:iterator value="page.pageResult" id="books">
					<tr>
						<td>
						  <input type="checkbox" name="bkId" value='<s:property value="bkId"/>'/> 
						</td>
						<td>
							<s:property value="bkName" />
						</td>
						<td>
							<s:property value="bkAuthor" />
						</td>
						<td>
							<s:property value="bkType" />
						</td>
						<td>
							<s:property value="bkTitle" />
						</td>
						<td>
							<s:property value="bkPrice" />
						</td>
						<td>
							<s:property value="bkSales" />
						</td>
						<td>
							<s:property value="discPrice" />
						</td>
						<td>
							<s:property value="publisher" />
						</td>
						<td>
							<s:property value="barcode" />
						</td>
						<td>
							<s:property value="createTime" />
						</td>
						
						<td>
							<a href='books!deleteBooks.action?books.bkId=<s:property value="bkId"/>' onclick="return confirm('是否删除？')">删除</a>
						</td>
						<td>
						    <a href='books!toEditBooks.action?books.bkId=<s:property value="bkId"/>'> 修改</a>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="13"><jsp:include page="/jsp/common/showPage.jsp" /></td>
				</tr>
			</table>
			</form>
		</div>
	</body>
</html>