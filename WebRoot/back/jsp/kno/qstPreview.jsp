<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_public.css" />
	<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_layout.css" />
	<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_popup.css" />
	<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_lacpage.css" />
    <title>My JSP 'qstPreview.jsp' starting page</title>
    <script type="text/javascript">
    	//返回试题关联列表
    	function cancle(ksnId){
    		window.location='<%=contextPath %>/kno/qstRel!toQstRelList.action?ksnId='+ksnId;
    	}
    </script>

  </head>
  <div><input type="button" value="返回" onclick="cancle(<s:property value="ksnId"/>)"/></div>
  		 <s:if test="qst.qstType==1">
                  <div class="ex_paper_box">
				 
                   <dl>
                    <dt> <s:property value="qst.qstContent" escape="false"/><span>[<s:property value="qst.score" />分]</span></dt>
					<s:iterator value="optionList" id="option">
                   		 <dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
					 </s:iterator>
                   </dl>
                   <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
					  <s:iterator value="optionList" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
								<input  type="radio" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
							</s:if>
					  </s:iterator>
                    </fieldset>
                    </div>
                </div>
			  </s:if>
				
				<!--多选-->
				 <s:if test="qst.qstType==2">	
                  <div class="ex_paper_box">
                   <dl>
                    <dt> <s:property value="qst.qstContent" escape="false"/><span>[<s:property value="qst.score" />分]</span></dt>
					<s:iterator value="optionList" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
						<dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						</s:if>
					</s:iterator>
                   </dl>
                   <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
				  <s:iterator value="optionList" id="option">
					<s:if test="#option.optContent!=null && #option.optContent!=''">
					<input type="checkbox" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
					</s:if>
				  </s:iterator>
                    </fieldset>
                    </div>
                </div>
				</s:if>
				<!---判断-->
				 <s:if test="qst.qstType==3">	
                   <div class="ex_paper_box">
                  <dl>
                    <dt><s:property value="qst.qstContent" escape="false"/><span>[<s:property value="qst.score" />分]</span></dt>  
                   </dl>
                   <div class="ex_check_answer"><span></span>
                    <fieldset>
						<s:iterator value="optionList" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
							  选择答案:<input type="radio" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optContent" escape="false"/></label>
							 </s:if>
						 </s:iterator>
                    </fieldset>
					
				   
                    </div>
                </div>
				</s:if>
				
				<!--材料分析-->
				 <s:if test="qst.qstType==4">	
                  <div class="ex_paper_box">
                   <dl>
                    <dt> <s:property value="qst.qstContent" escape="false"/><span></span></dt>
                   
                   </dl>
		
				</s:if>
				
				<!--图表题-->
				 <s:if test="qst.qstType==5">	
                  <div class="ex_paper_box">
                   <dl>
                    <dt> <s:property value="qst.qstContent" escape="false"/><span>[<s:property value="qst.score" />分]</span></dt>
					<s:iterator value="optionList" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
                   		 <dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						 </s:if>
					</s:iterator>
                   
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
				  <s:iterator value="optionList" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
					  	<input  type="checkbox" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
					  </s:if>
				  </s:iterator>
                    </fieldset>
                    </div>
                </div>
				</s:if>
				
				<!---问答题-->
				 <s:if test="qst.qstType==6">	
                  <div class="ex_paper_box">
                  <dl>
                    <dt><s:property value="qst.qstContent" escape="false"/><span>[<s:property value="qst.score" />分]</span></dt>  
                   </dl>
						<div class="ex_text_ta">
						<fieldset>
						<textarea  cols="60" rows="5" id="textarea"  onkeyup="this.value = this.value.slice(0,500)" disabled="disabled"></textarea>
						</fieldset>
						</div>
				   
                 </div>
				 </s:if>
  <body>
  	
  </body>
</html>
