<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>新建课程卡</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="<%=importURL%>/uploadify/uploadify.css" />
        <script type="text/javascript" src="<%=importURL%>/uploadify/swfobject.js"></script>
        <script type="text/javascript"  src="<%=importURL%>/uploadify/jquery.uploadify.v2.1.4_headimg.js"></script>
        <script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js"></script>
        <style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>
		<script type="text/javascript">
		 $(function(){
			 //校验
			 $("#cardCouseForm").validate();
			//上传图片
			var fileuploadIndex=0;
	        $("#fileupload").uploadify({
	                    'uploader':'<%=contextPath%>/uploadify/uploadify.swf',
	                    'script'  :'http://tp.highso.cn:8080/upload!shop.action;jsessionid=<%=session.getId()%>',
	                     'queueID':'fileQueue',
	                    'fileDataName':'fileupload',
	                    'auto':false,
	                    'multi':false,
	                    'hideButton':false,
	                    'buttonText':'Browse',
	                     'buttonImg':'<%=contextPath%>/uploadify/liulan.png',
	                     'width':105,
	                    'simUploadLimit' : 3,
	                    'sizeLimit'      : 512000,
	                    'queueSizeLimit' : 2,
	                    'fileDesc'       : '支持格式:jpg/gif/jpeg/png/bmp.',
	                    'fileExt'        : '*.jpg;*.gif;*.jpeg;*.png;*.bmp',
	                    'folder' : '/upload',
	                    'cancelImg':'<%=contextPath%>/uploadify/cancel.png',
	                     onSelect:function(event, queueID, fileObj){
	                    	$('#fileupload').uploadifyUpload();
	                     	fileuploadIndex=1;
	                    	$("#fileQueue").html("");
	                    	if(fileObj.size>512000){
	                    		alert("文件太大最大上传512kb");
	                    		return;
	                    	}
                    	},
	                    onComplete: function (event, queueID, fileObj, response, data)
	                    { 
	                    	$("#couponpic").attr("src","http://import.highso.org.cn/upload/shop/"+response);
	                    	$("#imageUrl").val(response);		
	                    	$("#couponpic").show();
	                    },
	                    onError: function(event, queueID, fileObj,errorObj)
	                    {
	                    	$("#fileQueue").html("<br/><font color='red'>"+fileObj.name+"上传失败</font>");
	                    }
                });
	        	$("#uploadifyUpload").click(function(){
	        		 if(fileuploadIndex==0)
	    			 {
	    				 alert("请选择图片 ");
	    				 return;
	    			 }	
	    			$('#fileupload').uploadifyUpload();
	        	});
	        	//保存课程卡
	        	$("#saveCardCouse").click(function(){
	        		var msg=validateInfo();
	        		if(msg!=''){
	        			alert(msg);
	        			return;
	        		}
	        		$("#cardCouseForm").attr("action","<%=contextPath%>/card/cardMain!saveCourseCardInfo.action");
	        		$("#cardCouseForm").submit();
	        	});
	        	//添加商品
	        	$("#addSell").click(function(){
	        		var pattern = /^[1-9]\d*$/;
	        		if(!pattern.test($("#sellId").val())){
	        			alert("商品ID只能为正整数！");
	        		}else{
	        			if(checkDuplicate()){
		        			alert("该商品ID在列表中已存在！");
		        			return;
		        		}
	        			//获取商品信息
	        			getSellInfo();
	        		}
	        	});
		    });
		 //删除商品
		 function deleteSell(obj){
			 if(window.confirm("是否删除该商品？")){
				 $("#liSell"+obj.id+"").remove();
			 }
		 }
		 //检查商品是否在列表中重复
		 function checkDuplicate(){
			 var sellIds=$("input[name=sellIds]");
			 for(var i=0;i<sellIds.length;i++){
				 if($("#sellId").val()==sellIds[i].value){
					 return true;
				 }
			 }
			 return false;
		 }
		 //获取商品信息
		 function getSellInfo(){
				$.ajax({
    				url :  "<%=contextPath%>/card/cardMain!getSellById.action",
    				data : {
    					"sellId" :  $.trim($("#sellId").val())
    				},
    				type : "post",
    				dataType : "json",
    				cache : false,
    				async : false,
    				success : function(result) {
    					addSellInfo(result);
    				},
    				error : function(error) {
    					alert(error);
    				}
    			});
				
		 }
		 function addSellInfo(result){
			 if(result.entity==null){
					alert("该商品ID不存在，请确认商品ID是否正确！");
					return;
				}
				html="<li id=\"liSell"+result.entity.sellId+"\">"
						+result.entity.sellName
						+"&nbsp;&nbsp;&nbsp;价格 ：&nbsp; "+result.entity.sellPrice
						+"&nbsp;&nbsp;&nbsp; <a href=\"javascript:void(0)\" onclick=\"deleteSell(this)\" id=\""+result.entity.sellId+"\">删除</a>"
						+"<input type=\"hidden\" name=\"sellIds\" value=\""+result.entity.sellId+"\"/>"
					+"</li>";
					
				$(html).appendTo($("ul"));
		 }
		 function validateInfo(){
			 var message="";
			 if(new Date($("#validBeginTime").val().replace(/-/g,"/"))>new Date($("#validEndTime").val().replace(/-/g,"/"))){
					message+="有效期开始时间不能大于有效期结束时间！\n";
				}
			 var dt=new Date($("#validBeginTime").val().replace(/-/g,"/"));
			 dt.setFullYear(dt.getFullYear()+3);
			 if(new Date($("#validEndTime").val().replace(/-/g,"/"))>dt){
					message+="有效期不能超过三年！\n";
				}
			 return message;
		 }
		</script>
		<style >
			body{margin-left: 10px;}
			body{ font-size:14px; color:#444444; background:#fff; font-family:"����", "Tahoma", "Helvetica Neue", Arial, Helvetica, sans-serif; }
	       .dotline {float:left;BORDER-BOTTOM-STYLE: dotted; BORDER-LEFT-STYLE: dotted; BORDER-RIGHT-STYLE: dotted; BORDER-TOP-STYLE: dotted;width:60%;height:1px;margin-top: 15px;margin-bottom: 10px}
	       div{padding-bottom:10px}
	       span{float:left;padding-right: 5px;width:80%}
	       .leftspan{padding-left:}
	       .border0{
	       border:0px;
	       }
	       #fileuploadUploader{float: left;}
        </style>
	</head>
	<body>
		<div>
			<form action="" method="post" name="cardCouseForm" id="cardCouseForm" enctype="multipart/form-data">
			<input type="hidden" name="cardMain.imageUrl" id="imageUrl" value="<s:property value='cardMain.imageUrl'/>"/>
			<input type="hidden" name="cardMain.cardType" id="cardType" value="1"/>
				<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
					<tr>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_03.gif" />
						</td>
						<td class="lists_top">
							<font class="lists_fleft">新建课程卡</font>
							</font>
						</td>
						<td class="td_wid_l">
							<img src="<%=contextPath%>/back/images/tab_07.gif" />
						</td>
					</tr>
					<tr>
						<td width="12"  class="lists_bor">
						</td>
						<td>
							<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info" id="tblList">
								<tbody id="tagTb">
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>商品 ID：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text"  id="sellId"  name="sellId" class="{required:true,digits:true,min:0,maxlength:20}"   style="width:5%"></input><a href="javascript:void(0)" id="addSell"><font color="black">&nbsp;&nbsp;添加此商品&gt;&gt;</font></a>
			    							<font color="black">
				    							<ul style="list-style:none">
				    							</ul> 
			    							</font>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>课程卡金额：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="cardMain.cardMoney" id="cardMoney" class="{required:true,number:true,min:0,maxlength:20}" value="<s:property value='cardMain.cardMoney'/>"  style="width:5%;"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>代理商金额：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="cardMain.agentMoney" id="agentMoney" class="{required:true,number:true,min:0,maxlength:20}" value="<s:property value='cardMain.agentMoney'/>"  style="width:5%;"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>课程卡数量：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="cardMain.cardCount" id="cardCount" class="{required:true,digits:true,min:0,maxlength:20}" value="<s:property value='cardMain.cardCount'/>"  style="width:5%;"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>课程卡名称：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="cardMain.cardName" id="cardName" class="{required:true,minlength:1,maxlength:100}" value="<s:property value='cardMain.cardName'/>"  style="width:20%;"/>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											<font color="red">*</font>使用有效期：
										</td>
										<td class="lists_tleft" style="color: #ff0000;">
											<input type="text" name="cardMain.validBeginTime" id="validBeginTime" readonly="readonly" class="{required:true}" style="width:7%;" value="<s:date name='cardMain.validBeginTime' format='yyyy-MM-dd' />" 
											onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"  />
											<font color="black"> - </font><input type="text" name="cardMain.validEndTime" id="validEndTime" readonly="readonly" class="{required:true}" style="width:7%;" value="<s:date name='cardMain.validEndTime' format='yyyy-MM-dd' />" 
											onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,autoPickDate:true})"  />&nbsp&nbsp&nbsp卡有效期不能超过3年
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											课程卡备注：
										</td>
										<td class="lists_tleft" >
											<textarea style="height:80px;width:30%;padding-bottom:3px;" name="cardMain.remark" class="{required:false,minlength:1,maxlength:200}"  id="remark"><s:property value="cardMain.remark" escape="true" /></textarea>
										</td>
									</tr>
									<tr>
										<td class="lists_tright" width="10%">
											上传验证图：
										</td>
						      			<td class="lists_tleft">
					 						<span style="border:0px;padding-top: 2px;padding-left: 2px;position: absolute;"><input type="file"  id="fileupload" style="float: left" /><input type="button" id="uploadifyUpload"  value="上传" style="width: 100px;height: 30px;padding-top: 0px"/></span>
												<div id="fileQueue" style="margin-top: 30px;border:0px"></div>
					   						<span style="float:left;">
					   						<s:if test="cardMain.cardMainId!=''">
					   							<s:if test="cardMain.imagesUrl==''">
					    							<img src="http://import.highso.org.cn/images/usercenter/leftnav_2.gif" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
					   							</s:if>
					   							<s:if test="cardMain.imagesUrl!=''">
					   								<img src="http://import.highso.org.cn/upload/shop/${cardMain.imagesUrl }" alt="" width="100px" height="100px"  style="padding-left: 80px" id="couponpic"/>
					   							</s:if>
					   						</s:if>
					   						<s:else>
					   							<img src="" alt="" width="100px" height="100px" style="display: none;padding-left: 80px" id="couponpic"/>
					   						</s:else>
					   						</span> 
					   					</td>
									</tr>
									<tr>
										<td colspan="2">
											<input  type="button" id="saveCardCouse" value="生成课程卡"/>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>	
					</table>
			</form>
		</div>
	</body>
</html>
