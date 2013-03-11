<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>任务修改</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/My97DatePicker/WdatePicker.js" ></script>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<style type="text/css">
			.uploadPic
			{
			    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
			}
		</style>

		<script type="text/javascript">
		$().ready(function() {

			$("#taskform").validate({   
		        rules: {
		            "task.sortNum": {
		            	required:true,
		            	min: 0
		            },
		            "task.keyWord": {
		            	required:true,
		            	minlength: 4,
		            	maxlength:50
		            }
		        },   
		        messages: {   
		            "task.sortNum": {
				            	required:"请添写编号",
				            	min: "不能低于0"
				    },
		            "task.keyWord": {
		            	required:"请填写关键字",
		            	minlength: "不能低于4个字符",
		            	maxlength: "不能超出50个字符"
		            }
		        }      
   	 		});  
		})	
					
			function changeMainPic(ipt) {
				var img = document.getElementById("mainpicImg");
				var div = document.getElementById("mainpicDiv");
				if(ipt.value != '') {
					if(IsIE()) {
						ipt.select();    
		        		var imgSrc = document.selection.createRange().text; 
						div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
						div.style.height = "100px";
						div.style.width = "100px";
					} else {
						img.src = ipt.files.item(0).getAsDataURL();
						img.style.display = "";
					}
				} else {
					img.style.display = "none";
				}
			}
			
			function IsIE() {
			    if (window.navigator.userAgent.indexOf("MSIE")>=1) {
			        //IE浏览器
			        return true;
			    }else{
			        return false;
			    }
			}
			 
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}

		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath%>/task/task!updateTask.action" method="post"  enctype="multipart/form-data" id="taskform">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">任务修改</font>
				<font class="lists_fright">
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
						<td width="200">
							排序编号
						</td>
						<td class="lists_tleft">
							<input type="text" name="task.sortNum" id="task.sortNum" value="<s:property value='task.sortNum'/>" />
						</td>
					</tr>
					
					<tr>
						<td width="200">
							关键字
						</td>
						<td class="lists_tleft">
							<input type="text" name="task.keyWord" id="task.keyWord" value="<s:property value='task.keyWord'/>" />
						</td>
					</tr>
					
					<tr>
						<td>
							任务名称
						</td>
						<td class="lists_tleft">
							<input type="text" name="task.taskName" id="task.taskName" value="<s:property value='task.taskName'/>"/>
							<s:if test="task.isRecommend == 1">
								<input type="checkbox" name="recommondTypes" value="1" checked/>设为推荐任务 
							</s:if>
							<s:else>
								<input type="checkbox" name="recommondTypes" value="1"/>设为推荐任务 
							</s:else>
							
							<s:if test="task.isTop == 1">
								<input type="checkbox" name="recommondTypes" value="2" checked/>设为置顶任务
							</s:if>
							<s:else>
								<input type="checkbox" name="recommondTypes" value="2"/>设为置顶任务
							</s:else>
						</td>
					</tr>
					<tr>
						<td>
							应用分类
							
						</td>
						<td class="lists_tleft">
							<s:select name="task.sortId" id="task.sortId" list="applicationSortMap"
								listKey="key" listValue="value" theme="simple">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							执行位置
						</td>
						<td class="lists_tleft">
							<input type="text" name="task.executeUrl" id="task.executeUrl" size="80" value="<s:property value='task.executeUrl'/>"/>（URL）
						</td>
					</tr>
					
					<tr>
						<td>
							任务内容
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="task.taskContent" style="height:80px;width:99%;"><s:property value='task.taskContent'/></textarea>
						</td>
					</tr>
					
					<tr>
						<td>
							 任务提示
						</td>
						<td class="lists_tleft">
							<textarea rows="" cols="" name="task.taskTips" style="height:80px;width:99%;"><s:property value='task.taskTips'/></textarea>
						</td>
					</tr>
					
					<tr>
						
						<td colspan="2" align="center">
							 <h3>附加属性</h3>
						</td>
					</tr>
					
					<tr>
						<td>
							 任务类型
						</td>
						<td class="lists_tleft"> <s:radio name='task.taskType' list="#{1:'正常任务',2:'每日任务',3:'循环任务'}"></s:radio>
									限制次数<input type=text name="task.limitNum" value="<s:property value='task.limitNum'/>" class="{required:true,digits:true,min:0,maxlength:20}"/>
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							 <h3>激活属性</h3>
						</td>
					</tr>
					
					<tr>
						<td>
							 任务级别
						</td>
						<td class="lists_tleft">
							<s:select name="task.taskLevel" id="task.taskLevel" list="userLevelMap"
								listKey="key" listValue="value" theme="simple">
							</s:select>
						</td>
					</tr>
					
					<tr>
						<td>
							 特殊条件
						</td>
						<td class="lists_tleft">
							<s:if test="task.isHasPreTask == 1">
								<input type="checkbox" name="specialCondition" value="1" checked/>前置任务 关联任务 
							</s:if>
							<s:else>
								<input type="checkbox" name="specialCondition" value="1"/>前置任务 关联任务 
							</s:else>
							
							<s:select name="task.preTaskId" id="task.preTaskId" list="taskKeyValueList"
								listKey="key" listValue="value" theme="simple">
							</s:select> <br />
							
							<s:if test="task.isLimitTimeTask == 1">
								<input type="checkbox" name="specialCondition" value="2" checked/>限时任务
							</s:if>
							<s:else>
								<input type="checkbox" name="specialCondition" value="2"/>限时任务
							</s:else>
							<input type="text" name="task.limitTimeStart" value="<s:date name="task.limitTimeStart" format="yyyy-MM-dd" />" readonly id="task.limitTimeStart" onclick="WdatePicker()" onfocus="inputOnFocus()" />
							<input type="text" name="task.limitTimeEnd" value="<s:date name="task.limitTimeEnd" format="yyyy-MM-dd" />" readonly id="task.limitTimeEnd" onclick="WdatePicker()" onfocus="inputOnFocus()" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							 <h3>结果判断</h3>
						</td>
					</tr>
					
					<tr>
						<td class="lists_tleft" colspan="2">
							<s:if test="task.resultType == 1">
								<input type="radio" name="task.resultType" value="1" checked/>
							</s:if>
							<s:else>
								<input type="radio" name="task.resultType" value="1"/>
							</s:else>
							
							等级判断： <s:select name="task.judgeLevel" id="task.judgeLevel" list="userLevelMap"
								listKey="key" listValue="value" theme="simple">
							</s:select><br />
							
							<s:if test="task.resultType == 2">
								<input type="radio" name="task.resultType" value="2" checked/>
							</s:if>
							<s:else>
								<input type="radio" name="task.resultType" value="2"/>
							</s:else>
							页面访问判断： 目标页面URL:<input type="text" name="task.judgeUrl" value="<s:property value='task.judgeUrl'/>" size="80"/><br />
							
							<s:if test="task.resultType == 3">
								<input type="radio" name="task.resultType" value="3" checked/>
							</s:if>
							<s:else>
								<input type="radio" name="task.resultType" value="3"/>
							</s:else>
							执行结果判断：<br />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							 <h3>任务奖励</h3>
						</td>
					</tr>
					
					<tr>
						<td colspan="2"  class="lists_tleft">
							获取积分：
							<s:if test="task.isJifen == 1">
								<input type="checkbox" name="isJiFen" value="1" checked/>
							</s:if>
							<s:else>
								<input type="checkbox" name="isJiFen" value="1"/>
							</s:else>
							
							
							兑换积分 成长积分 数量：<input type="text" name="task.jifen" value="<s:property value='task.jifen'/>" class="{required:true,digits:true,min:0,maxlength:20}"/><br />
							获取经验：<input type="text" name="task.experience" value="<s:property value='task.experience'/>" class="{required:true,digits:true,min:0,maxlength:20}" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							 <h3>图标</h3>
						</td>
					</tr>
					
					<tr>
						<td>
							 ＊上　　传:文    件:
						</td>
						<td class="lists_tleft">
							<input type="file" name="iconPic" id="iconPic"
								 onchange="changeMainPic(this)"/>(格式为.gif)
							<div class="uploadPic" id="mainpicDiv"></div>
							<img id="mainpicImg"  src="<%=importURL %>/upload/task/<s:property value='task.iconUrl'/>"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<s:hidden name="task.iconUrl"></s:hidden>
							<s:hidden name="task.taskId"></s:hidden>
							<s:submit value="提交" />
						</td>
					</tr>
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
