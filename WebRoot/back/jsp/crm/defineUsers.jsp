<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    	<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
    <title>坐席自定义</title>
    <script type="text/javascript">
    $(function(){
  		//全部移到右边
       $('#alladd').click(function() {
	    $('#leftselect option').remove().appendTo('#rightselect');
	    });
	    //全部移动左边
	   $('#allremove').click(function() {
	    $('#rightselect option').remove().appendTo('#leftselect');
	    });
	       //移到右边
	    $('#add').click(function() {
	    $('#leftselect option:selected').remove().appendTo('#rightselect');
	    });
	    //移到左边
	    $('#remove').click(function() {
	    $('#rightselect option:selected').remove().appendTo('#leftselect');
	    });
	       //双击选项
	    $('#leftselect').dblclick(function(){
	        $("option:selected",this).remove().appendTo('#rightselect');
	    });
	    //双击选项
	       $('#rightselect').dblclick(function(){
	        $("option:selected",this).remove().appendTo('#leftselect');
	    });
	   
	});
	function search(){
		var keyword=$('#keyword').val();
		if(keyword==""){
		alert('请输入坐席!');
		return;
		}
			$.ajax({
									url : "<%=contextPath%>/crm/selfDefine!getSearchUnsignSeats.action",  
									data : {
									"keyword":keyword
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var userList=eval(result.returnMessage);
									var left='';
									if(userList.length==0){
										alert('未找到');
									}
									for(var i=0;i<userList.length;i++){
											left+='<option value="'+userList[i].userId+'">'+userList[i].userName+'</option>';
										}
											$('#leftselect').html(left);
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}
	function selected(groupId){
		if(groupId!=0){
			$.ajax({
									url : "<%=contextPath%>/crm/selfDefine!getGroupUserInfo.action",  
									data : {
									"groupIds":groupId
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									var userList=eval(result.returnMessage);
									if(bo){
										var right='<s:property value="seatsList"/>';
										var rights='';
										'<s:iterator value="seatsList" id="seatsList">'
											var status=true;
												for(var i=0;i<userList.length;i++){
												
													if('<s:property value="#seatsList.userId"/>'==userList[i].userId){
														status=false;
														break;
													}
												}
												if(status&&'<s:property value="#seatsList.groupId"/>'==groupId){
													rights+='<option value="'+'<s:property value="#seatsList.userId"/>'+'">'+'<s:property value="#seatsList.userName"/>'+'</option>';
												}
												$('#rightselect').html(rights);
										'</s:iterator>'
									}
									var left='';
									for(var i=0;i<userList.length;i++){
											left+='<option value="'+userList[i].userId+'">'+userList[i].userName+'</option>';
										}
											$('#leftselect').html(left);
									},
									error: function(){ 
										alert('error');  
									}
				  });
				  }
		}
		
		function submits(){
			var right=document.getElementById('rightselect');
			var ss='';
			var groupId=$('#group').val();
			if(groupId==null){
				groupId=0;
			}
			for(var i=0;i<right.options.length;i++){
				if(i==0){
					ss+=right.options[i].value;
				}else{
					ss+=','+right.options[i].value;
				}
			}
			
			$.ajax({
									url : "<%=contextPath%>/crm/selfDefine!seatsAdd.action",  
									data : {
									"seatsId":ss,
									"groupIds":groupId
									},  // 参数  
									type : "post",  
									cache : false,  
									dataType : "json",  //返回json数据  
									success:function(result){
									var bo=result.jumpType;
									if(bo){
										alert('调整保存成功');
									}
									},
									error: function(){ 
										alert('error');  
									}
				  });
		}

		//修改定时延时数
		function updateTiming(){
			var timingNum = $('input:radio[name="timingNum"]:checked');
			var num ;
			if(timingNum.attr("id")=="yanshi"){
				num = $("#timingNum").val();
				if(num==''){
					alert('请输入延时时间');
					return;
				}
				if(isNaN(num) || num<0){
					alert("延时必须为正整数");
					return ;
				}
				if(num==0){
				num=1;
				}
			}else{
				$("#timingNum").attr("value", "");
				num = 1;
			}
			var url = "<%=contextPath%>/crm/selfDefine!updateTiming.action";
			$.post(url, {timingNum: num}, function(data){
				if(data.jumpType==true){
					alert("保存成功！");
				}else{
					alert("保存失败！");
				}
			}, 'json');
			
		}
		
	
    </script>

  </head>
  
  <body>
  
    <form action="<%=contextPath%>/crm/selfDefine!seatsAdd.action" id="seatsDefine" name="seatsDefine" method="post">
   	<table width="100%"  border="0" cellspacing="0"  cellpadding="0" class="lists">

		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top" >
			  <font class="lists_fleft" id="title">坐席自定义</font></td>
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_07.gif" />

			</td>
		</tr>
		
		<tr>
			<td width="12" class="lists_bor">
			</td>
			<td >
				<table cellspacing="0" cellpadding="0" border="0" width="900px" class="lists_info crm_lineh">
					<tr>
						<td width="150px">
							所属项目组
						</td>
						
						<td width="250px">
							销售坐席
						</td>
						
						<td width="100px">
						
						</td>
						
						<td width="250px">
							自然注册指派坐席
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td>
							
							<select multiple="multiple" id="group"  style="width: 100px;height:260px;" onchange="selected(this.value);">
							<s:iterator value="groupList" id="groupList">
								<option value='<s:property value="#groupList.groupId"/>'><s:property value="#groupList.groupName"/></option>
							</s:iterator>
							</select>
					    </td>
					    <td align="left" valign="top">
						      <div style="overflow:hidden;"> 
						    <input type="text" id="keyword" value="" style="width: 114px; float: left;margin-left: 66px;"/>
						    <img src="<%=contextPath%>/back/images/CK.gif" onclick="search();" style="float: left;  height: 16px; margin-left: 5px; width: 16px" />
						   </div>
						      <select multiple="multiple" id="leftselect"  style="width: 118px;height:235px;"></select>
						  
					    </td>
					    <td>
					  	  
					    	<input type="button" id="add" value="添加&gt;&gt;"/><br><br>
					    	<input type="button" id="remove" value="删除&lt;&lt;"/><br><br>
					    	<input type="button" id="alladd" value="全部添加&gt;&gt;"/><br><br>
					    	<input type="button" id="allremove" value="全部删除&lt;&lt;"/>
					     </td>
					     <td>
					     <select multiple="multiple" id="rightselect" name="rightselect" style="width: 150px;height:260px;">
					     	<s:iterator value="userList" id="userList">
								<option value='<s:property value="#userList.userId"/>' ><s:property value="#userList.userName"/></option>
							</s:iterator>
					     </select>
					    </td>
					    
					    <td>
					    	<input type="button" onclick="submits()" value="保存"/>
					    </td>
					</tr>
				</table>
    		</td>
			<td width="16" class="lists_tright lists_bor2">

			</td>
		</tr>
		<tr>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom" >
			</td>
			<td class="td_wid_r">
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
				<tr >
					
					<td  class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_03.gif" />
					</td>
					<td class="lists_top">
						<font class="lists_fleft">即时自定义</font>
					</td>
					<td  class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_07.gif" />
					</td>
					
				</tr>
				<tr>
					<td  class="lists_bor">
					</td>
					<td>
					<table width="50%">
					<tr>
						<td width="30%">延时<input type="radio" name="timingNum" id="yanshi" <s:property value="timingNum>1?'checked=\"checked\"':''"/> />
											<input type="text" size="5" id="timingNum" value="<s:property value="timingNum>1?timingNum:''"/>" /> 分钟 </td>
						<td width="20%">即时<input type="radio" name="timingNum" id="jishi" <s:property value="timingNum==1?'checked=\"checked\"':''"/>/></td>
						<td width="50%"><input type="button" value="保存" onclick="updateTiming()"/></td>
					</tr>
					</table>
					</td>
					<td width="16" class="lists_tright lists_bor2">
					</td>
				</tr>
				<tr>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_18.gif" />
					</td>
					<td class="lists_bottom">

					</td>
					<td class="td_wid_l">
						<img src="<%=contextPath%>/back/images/tab_20.gif" />
					</td>
				</tr>
			</table>
	
	</form>
  </body>
</html>
