<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" media="screen"
	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript"
	src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/back_util.js"></script>
<script type="text/javascript"
	src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


			
	function delBook(bookName,bookId) {
		if(window.confirm("请注意:你将要删除商品【"+bookName+"】,删除后将不能恢复，请确认删除？")) {
			document.bookForm.action = "<%=contextPath%>/book/book!updateBookStatus.action?book.bookId="+Number(bookId)+"&book.status=3";
			document.bookForm.submit();
			return true;
		}else
		{
			return false;
		}
	}
	function addCoupon() {
		window.location.href = "<%=contextPath%>/cou/coupon!initAddCoupon.action";
	}
	
	function updateCoupon(id) {
		window.location.href = "<%=contextPath%>/cou/coupon!initUpdateCoupon.action?coupon.id=" + id;
	}
	
	function resetKey() {
		$("input[name=queryCouponCodeCondition.cInfo]").val("");
	}
	
	function allCheck(cb) {
		$("input[name=ids]:checkbox").attr('checked', cb.checked);
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
			document.bookForm.action ="<%=contextPath%>/book/book!UpdateBookByStateForMore.action?book.status="+state;
			document.bookForm.submit();
			
		}
	}

	function yan(){
		if((new Date($("#begindate").val().replace(/-/g,"/")))>(new Date($("#enddate").val().replace(/-/g,"/"))))
		{
			alert("开始时间已经大于结束时间");
			return false;
		}
		$("#keyword").val(encodeURIComponent($("#keyword").val()));
		document.searchForm.submit()
	}
	function updateBookStatus(bookId,status) {
			var bb=bookId;
			var cc=status;
			document.bookForm.action = "<%=contextPath%>/book/book!updateBookStatus.action?book.bookId="+Number(bb)+"&book.status="+Number(cc);
			document.bookForm.submit();
			return true;
	}
	function onChangStatus(){
		document.getElementById('checkStatusValue').value='';
		var status =document.getElementById('shopState').value;
		document.getElementById('checkStatusValue').value=Number(status);
		
	}
	
	function updateShopState(state){
		var num=0;
		var check=document.getElementsByName("ids");
        for(var i=0;i<check.length;i++){
		if(check[i].checked==true)num++;
		}
        if(num==0){alert("请至少选择一条信息");return;}
		var info="确实要上架这些商品吗";
		if(window.confirm(info)) {
			document.bookForm.action ="<%=contextPath%>/book/book!updateShopState.action?book.shopState="+state;		
			document.bookForm.submit();
		}
	}
	
	
</script>
</head>
<body>
	<!-- 查询块开始 -->
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
            <tr>
                <td class="td_wid_l">
                    <img src="<%=contextPath%>/back/images/tab_03.gif"/>
                </td>
                <td class="lists_top">
                    <font class="lists_fleft">图书管理</font>
                    <font class="lists_fright">

                    </font>
                </td>
                <td class="td_wid_r">
                    <img src="<%=contextPath%>/back/images/tab_07.gif"/>
                </td>
            </tr>



			<tr>
				<td width="12" class="lists_bor"></td>
                <td>
                <div class="msg-app">
					<form name="searchForm" id="searchForm"
						action="<%=contextPath%>/book/book!showBookListByWhere.action?bookCondition.currentPage=1"
						method="post">
						<table>
							<tr align="left" >
								<td align="left"  style="text-align:left">
									<table border="0" class="lists">
										<tr align="left">
											<td width="60" style="text-align:right">所属项目:</td>
											<td align="left" width="180" style="text-align:left">
 
						<s:select list="subjectList" listKey="subjectId"
													id="" listValue="subjectName"
													 theme="simple"
													name="bookCondition.subjectId" headerKey="-1"
													headerValue="---请选择---"></s:select></td>


											<td  style="text-align:right">分类:</td>
											<td class="lists_tleft" style="text-align:left"><select
												name="bookCondition.bookType" id="bookType" >
												    <option value="-1" selected="selected">全部</option>
													<option value="1">教材</option>
													<option value="2">教辅</option>
													<option value="3">大纲</option>
													<option value="4">套装</option>
											</select></td>
											<td style="text-align:right">&nbsp;&nbsp;是否在售:</td>
											<td class="lists_tleft" style="text-align:left"><select
												name="bookCondition.shopState" id="shopState">
													<option value="-1" selected="selected">全部</option>
													<option value="1">在售</option>
													<option value="2">不在售</option>
											</select></td>
											<td style="text-align:right">&nbsp;&nbsp;状态:</td>
											<td style="text-align:left"><select name="bookCondition.status" id="status">
													<option value="-1" selected="selected">全部</option>
													<option value="1">正常</option>
													<option value="2">冻结</option>
											</select></td>

											<td  style="text-align:right">&nbsp;&nbsp;推荐:</td>
											<td style="text-align:left"><select name="bookCondition.disProperty"
												id="disProperty">
													<option value="-1" selected="selected">全部</option>
													<option value="1">推荐</option>
													<option value="2">特价</option>
													<option value="3">精品</option>
													<option value="4">畅销</option>
											</select></td>
										</tr>

										<tr>
											<td  style="text-align:right">上架时间:</td>
											<td valign="middle"  colspan="5" style="text-align:left" >
												<span style="float: left"><input type="text" readonly="readonly" 
												name="bookCondition.upTime" id="begindate"
												onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
												value="<s:date name="bookCondition.upTime" format="yyyy-MM-dd HH:mm:ss"/>" /></span>
												<span style="float: left">&nbsp;&nbsp; &nbsp;&nbsp;到 &nbsp;&nbsp;&nbsp;&nbsp; </span>
												<span style="float: left;">
												<input type="text" readonly="readonly" name="bookCondition.dropTime" id="enddate"
												onfocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true,autoPickDate:true})"
												value="<s:date name="bookCondition.dropTime" format="yyyy-MM-dd HH:mm:ss"/>" />
												</span>
											</td>
										</tr>
										<tr>
											<td style="text-align:right">关键字:</td>
											<td class="lists_tleft" colspan="3" style="text-align:left">
												<input type="text" id="keyword" name="bookCondition.keyword"
												value="${bookCondition.keyword}" />
										    </td>
										    <td>
										    		<input type="button" onclick="yan()" value="查询"/> 
										    </td>
										</tr>
									</table></td>
							</tr>
						</table>
					</form>
                    </div>
                </td>
				<td width="16" class="lists_tright lists_bor2"></td>
			</tr>
			<tr>
				<td><img src="<%=contextPath%>/back/images/tab_18.gif" />
				</td>
				<td class="lists_bottom"></td>
				<td><img src="<%=contextPath%>/back/images/tab_20.gif" />
				</td>
			</tr>
		</table>

	</div>
	<!-- 查询块结尾 -->
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="lists">
			<tr >
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_03.gif" /></td>
				<td class="lists_top"><font class="lists_fleft">商品列表</font> <font
					class="lists_fright"> </font></td>
				<td class="td_wid_l"><img
					src="<%=contextPath%>/back/images/tab_07.gif" /></td>
			</tr>

            <tr>
                        <td class="lists_bor"></td>
                        <td>
                                <table class="" width="300" border="0" cellspacing="0" cellpadding="0">
                          							<tr>
                          								<td><img src="<%=contextPath%>/back/images/add_a.gif" />
                          								</td>
                          								<td><a
                          									href="<%=contextPath%>/book/book!toAddBook.action">新建</a>
                          								</td>
                                                          <td width="20"></td>
                                                          <td><img src="<%=contextPath%>/back/images/edt_tbl.gif" />
                                                                                    								</td>
                                                                                    								<td><a href="###" onclick="updateShopState(1)">上架</a></td>
                                                          <td width="20"></td>
                                                          <td><img src="<%=contextPath%>/back/images/edt_tbl.gif" />
                                                                                    								</td>
                                                                                    								<td><a href="###" onclick="allUpdateState(3)">删除</a></td>
                                                          <td width="20"></td>
                                                          <td><img src="<%=contextPath%>/back/images/edt_tbl.gif" />
                                                                                    								</td>
                                                                                    								<td><a href="###" onclick="allUpdateState(2)">冻结</a></td>
                                                          <td width="20"></td>
                                                          <td><img src="<%=contextPath%>/back/images/edt_tbl.gif" />
                                                                                    								</td>
                                                                                    								<td><a href="###" onclick="allUpdateState(1)">解冻</a></td>
                          							</tr>

                          						</table>

                        </td>
                        <td class="lists_tright lists_bor2"></td>
                    </tr>
			<tr>
				<td width="12" class="lists_bor"></td>
				<td>
					<form name="bookForm" method="post">
						<table width="100%" border="0" cellspacing="1" cellpadding="0"
							class="lists_info" style="word-wrap: break-word;">
							<tr class="lists_infobg">
								<td width="4%"><input type="checkbox"
									onclick="allCheck(this)" />编号</td>
								<td width="9%">商品名称</td>
								<td width="6%">作者</td>
								<td width="6%">出版社</td>
								<td width="8%">出版时间</td>
								<td width="6%">所属项目</td>
								<td width="3%">分类</td>
								<td width="8%">添加时间</td>
								<td width="8%">上架时间</td>
								<td width="8%">最后修改时间/修改人</td>
								<td width="4%">图书价格</td>
								<td width="8%">是否在售</td>
								<td width="2%">推荐</td>
								<td width="2%">特价</td>
								<td width="2%">精品</td>
								<td width="2%">畅销</td>
								<td width="3%">状态</td>
								<td width="9%">操作</td>
							</tr>

							<s:iterator id="book" value="page.pageResult">
								<tr>
									<td><input type="checkbox" name="ids"
										value='<s:property value="#book.bookId"/>' /> <s:property
											value="#book.bookId" /></td>
									<td><s:property value="#book.bookName" /></td>
									<td><s:property value="#book.author" /></td>
										<td><s:property value="#book.press" /></td>
											<td>
											<s:date name="#book.publishTime" format="yyyy-MM-dd HH:mm:ss" /></td>
									<td><s:property value="#book.subject.subjectName" /></td>

									<td>
									<s:if test="#book.bookType==1">
										教材
										</s:if>
									<s:if test="#book.bookType==2">
										教辅
										</s:if>
										<s:if test="#book.bookType==3">
										大纲
										</s:if>
										<s:if test="#book.bookType==4">
										套装
										</s:if>
									</td>
									<td><s:date name="#book.addTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									<td><s:date name="#book.upTime" format="yyyy-MM-dd HH:mm:ss"/></td>
									<td><s:date name="#book.updateTime"
											format="yyyy-MM-dd HH:mm:ss" /><br/>/<s:property
											value="#book.updateUser" /></td>
									<td><s:property value="#book.nowPrice" /></td>
									<td><s:if test="#book.shopState==2">
											否/下架时间: <br />
											<s:date name="#book.dropTime" format="yyyy-MM-dd HH:mm:ss" />
										</s:if> <s:if test="#book.shopState==1">
											是
										</s:if></td>
									
									<td>
									  <s:if test="#book.disproperty.indexOf('1')!=-1">
										★
										</s:if> <s:else>
										/
										</s:else>
									</td>
									<td>	<s:if test="#book.disproperty.indexOf('2')!=-1">
										★
										</s:if> <s:else>
										/
										</s:else></td>
									<td>	<s:if test="#book.disproperty.indexOf('3')!=-1">
										★
										</s:if> <s:else>
										/
										</s:else></td>
									<td>	<s:if test="#book.disproperty.indexOf('4')!=-1">
										★
										</s:if> <s:else>
										/
										</s:else></td>
									
								
										<td>
										<s:if test="#book.status==1">
											正常
										</s:if>
										<s:if test="#book.status==2">
											冻结
										</s:if>
										</td>
									<td><a href='<%=contextPath%>/book/book!bookInfo.action?book.bookId=<s:property value="#book.bookId"/>'>详情</a>
										| 
										<a href='<%=contextPath%>/book/book!toUpdateBook.action?book.bookId=<s:property value="#book.bookId"/>'>编辑</a>
										| 
										<a href="###" onclick="delBook('<s:property value="#book.bookName"/>','<s:property value="#book.bookId"/>')">删除</a>
										| <s:if test="#book.status==2">
											<a href="###" onclick="updateBookStatus('<s:property value="#book.bookId"/>','1')">解冻</a>
										</s:if> <s:if test="#book.status==1">
											<a href="###" onclick="updateBookStatus('<s:property value="#book.bookId"/>','2')">冻结</a>
										</s:if>
								
									</td>

								</tr>
							</s:iterator>
								
						</table>
					</form></td>
				<td width="16" class="lists_tright lists_bor2"></td>
			</tr>
			
			<tr>
				<td><img src="<%=contextPath%>/back/images/tab_18.gif" /></td>
				<td class="lists_bottom"><jsp:include page="/back/jsp/common/showPage.jsp" /></td>
				<td><img src="<%=contextPath%>/back/images/tab_20.gif" /></td>
			</tr>
		</table>
	</div>
	<script type="">
            $("#bookType").val('${bookCondition.bookType}');
            $("#shopState").val('${bookCondition.shopState}');
            $("#status").val('${bookCondition.status}');
            $("#disProperty").val('${bookCondition.disProperty}');
    </script>
</body>
</html>
