<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/include/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HighSo嗨学网_考试中心 远程网络职业教育领跑者 尚德机构旗下</title>
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_public.css" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_layout.css" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_popup.css" />
<link rel="stylesheet" type="text/css" href="<%=importURL%>/styles/usercenter/uc_lacpage.css" />
<!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection" /><![endif]-->


</head>

<body>
<div class="container con_top_bg2 con_pos">

	<div class="con_main1 wid-840 left">
        <div class="con_main_kc">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
    	  <tr>
    	    <td height="650">
				<form action="qstManager!judgmentPaper.action" method="post" id="exampaper">
		 			<input type="hidden"  name="epid" value="<s:property value="exam.epId" />"/>
					 <s:set name="danxuan" value="0"/>
					<s:set name="duoxuan" value="0"/>
				   <s:set name="panduan" value="0"/>
				   <s:set name="cailiao" value="0"/>
				   <s:set name="cailiaozinumber" value="0"/>
				   <s:set name="cailiaozixu" value="0"/>
				   <s:set name="tubiao" value="0"/>
				   <s:set name="zhuguan" value="0"/>
				   <s:set name="jishu" value="0" />
					
            <div class="pbottom-10">
            	<h4 class="title"><img src="<%=importURL%>/images/usercenter/icon_12.png" alt="考试" />考试中心<span class="ex_back"></span></h4>
      
              <div class="ex_paper_wrap">
           	    <h3 class="bgcolor1"><s:if test="exam.eptype==2">仿真自测</s:if><s:elseif test="exam.eptype==1">真题测试</s:elseif><s:elseif test="exam.eptype==3">单元练习</s:elseif><s:elseif test="exam.eptype==4">专题练习</s:elseif>--<s:property value="exam.epName"/><span>[共<strong><s:property value="exam.qstmiddle.size()"/></strong>题]</span></h3> 
                
			
				 <s:iterator id="qsts" value="exam.qstmiddle" status="count">
				  <!--单选-->
				  <s:if test="#qsts.qstType==1">
				 
				 <s:set name="danxuan" value="#danxuan+1"/>
				  <s:if test="#danxuan<=1"> 
				  <h5>单项选择题</h5> 
				   </s:if>
				   
                  <div class="ex_paper_box">
				 
                   <dl>
                    <dt> <a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1" />.<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
					<s:iterator value="#qsts.qst.options" id="option">
                   		 <dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
					 </s:iterator>
					 <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="radio" value="isnull" checked="checked"  /></dd>
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
					  <s:iterator value="#qsts.qst.options" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
								<input name="res<s:property value="#count.index+1"/>" type="radio" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
							</s:if>
					  </s:iterator>
                    </fieldset>
                    </div>
                </div>
			  </s:if>
				
				<!--多选-->
				 <s:if test="#qsts.qstType==2">	
				  <s:set name="duoxuan" value="#duoxuan+1"/> 	 
			 		 <s:if test="#duoxuan<=1">
                  <h5>多项选择题</h5>
				  </s:if>
                  <div class="ex_paper_box">
                   <dl>
                    <dt> <a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1" />.<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
					<s:iterator value="#qsts.qst.options" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
						<dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						</s:if>
					</s:iterator>
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
				  <s:iterator value="#qsts.qst.options" id="option">
					<s:if test="#option.optContent!=null && #option.optContent!=''">
					<input name="res<s:property value="#count.index+1"/>" type="checkbox" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
					</s:if>
				  </s:iterator>
				  
				   <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="checkbox" value="isnull" checked="checked"  /></dd>
                    </fieldset>
                    </div>
                </div>
				</s:if>
				<!---判断-->
				 <s:if test="#qsts.qstType==3">	
				 	  <s:set name="panduan" value="#panduan+1"/>
			 			<s:if test="#panduan<=1">
				   <h5>判断题</h5>
				   </s:if>
                   <div class="ex_paper_box">
                  <dl>
                    <dt><a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1" />.<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>  
                   </dl>
                   <div class="ex_check_answer"><span></span>
                    <fieldset>
						<s:iterator value="#qsts.qst.options" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
							  选择答案:<input name="res<s:property value="#count.index+1"/>" type="radio" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optContent" escape="false"/></label>
							 </s:if>
						 </s:iterator>
                    </fieldset>
					
				   <input name="res<s:property value="#count.index+1"/>" type="radio" value="isnull" checked="checked"  style="display:none;"/>
				   
                    </div>
                </div>
				</s:if>
				
				<!--材料分析-->
				 <s:if test="#qsts.qstType==4">	
				 <s:set name="cailiao" value="#cailiao+1"/>
			  <s:if test="#cailiao<=1">
                  <h5>材料解析题</h5>
				  </s:if>
                  <div class="ex_paper_box">
				  <s:if test="#qsts.qstScore==0">
				  <s:set name="cailiaozixu" value="0"/>
                   <dl>
                    <dt> <a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1-#cailiaozinumber"/>.<s:property value="#qsts.qst.qstContent" escape="false"/><span></span></dt>
                   
                   </dl>
				    <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="radio" value="A" checked="checked"  /></dd>
				   </s:if>
		<!--  -->		
				<s:if test="#qsts.qstScore!=0">
                  <s:set name="cailiaozinumber" value="#cailiaozinumber+1"/>
                  <s:set name="cailiaozixu" value="#cailiaozixu+1"/>
				  <!--判断单选多选1是单选2是多选-->
				  <s:if test="#qsts.qst.qstType==1">
				   <dl>
                    <dt><a name="mao<s:property value="#count.index+1"/>"></a>(<s:property value="#cailiaozixu"/>)<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
					 	<s:iterator value="#qsts.qst.options" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
						<dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						</s:if>
						</s:iterator>
                    
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
								<s:iterator value="#qsts.qst.options" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
							
							<input name="res<s:property value="#count.index+1"/>" type="radio" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
							</s:if>
							</s:iterator>
							  <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="radio" value="isnull" checked="checked"  /></dd>
				  </s:if>
						
				  <s:if test="#qsts.qst.qstType==2">
				   <dl>
                    <dt><a name="mao<s:property value="#count.index+1"/>"></a>(<s:property value="#cailiaozixu"/>)<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
					 	<s:iterator value="#qsts.qst.options" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
						<dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						</s:if>
						</s:iterator>
                    
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
								<s:iterator value="#qsts.qst.options" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
							
							<input name="res<s:property value="#count.index+1"/>" type="checkbox" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
							</s:if>
							</s:iterator>
							  <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="checkbox" value="isnull" checked="checked"  /></dd>
				  		</s:if>
						
						
						  <s:if test="#qsts.qst.qstType==6">
						   <input  type="hidden" name="zhuguan" value="1" id="zhuguan"/>
						   <dl>
                    <dt><a name="mao<s:property value="#count.index+1"/>"></a>(<s:property value="#cailiaozixu"/>)<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
                    
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
							<s:iterator value="#qsts.qst.options" id="option">
							<s:if test="#option.optContent!=null && #option.optContent!=''">
								<div class="ex_text_ta">
								<fieldset>
								<textarea name="res<s:property value="#count.index+1"/>" cols="60" rows="5" id="textarea"  onkeyup="this.value = this.value.slice(0,500)" disabled="disabled"></textarea>
								<input  type="hidden"  value="<s:property value="#qsts.score"/>" id="fenid<s:property value="#multiQstCount"/>"/>
								</fieldset>
								</div>
							 </s:if>
						  </s:iterator>
							
							 
				  		</s:if>
				</s:if>
				
                    </fieldset>
                    </div>
				</s:if>
				
				<!--图表题-->
				 <s:if test="#qsts.qstType==5">	
				 <s:set name="tubiao" value="#tubiao+1"/>
			  <s:if test="#tubiao<=1">
				  <h5>图表题</h5>
				  </s:if>
                  <div class="ex_paper_box">
                   <dl>
                    <dt> <a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1-#cailiaozinumber"/>.<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>
					<s:iterator value="#qsts.qst.options" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
                   		 <dd><s:property value="#option.optOrder" escape="false"/>.<s:property value="#option.optContent" escape="false"/></dd>
						 </s:if>
					</s:iterator>
                   
                   </dl>
                    <div class="ex_check_answer"><span></span>
                    <fieldset>
                  选择答案:
				  <s:iterator value="#qsts.qst.options" id="option">
						<s:if test="#option.optContent!=null && #option.optContent!=''">
					  	<input name="res<s:property value="#count.index+1"/>" type="checkbox" value="<s:property value="#option.optOrder" escape="false" />" /><label><s:property value="#option.optOrder" escape="false"/></label>
					  </s:if>
				  </s:iterator>
				   <dd style="display:none;"><input name="res<s:property value="#count.index+1"/>" type="checkbox" value="isnull" checked="checked"  /></dd>
                    </fieldset>
                    </div>
                </div>
				</s:if>
				
				<!---问答题-->
				 <s:if test="#qsts.qstType==6">	
				 <s:set name="zhuguan" value="#zhuguan+1"/>
				  <input  type="hidden" name="zhuguan" value="1" id="zhuguan"/>
			  <s:if test="#zhuguan<=1">
                  <h5>简答题</h5>
				  </s:if>
                  <div class="ex_paper_box">
                  <dl>
                    <dt><a name="mao<s:property value="#count.index+1"/>"></a><s:property value="#count.index+1-#cailiaozinumber"/>.<s:property value="#qsts.qst.qstContent" escape="false"/><span>[<s:property value="#qsts.qstScore" />分]</span></dt>  
                   </dl>
				   <s:iterator value="#qsts.qst.options" id="option">
	  				<s:if test="#option.optContent!=null && #option.optContent!=''">
						<div class="ex_text_ta">
						<fieldset>
						<textarea name="res<s:property value="#count.index+1"/>" cols="60" rows="5" id="textarea"  onkeyup="this.value = this.value.slice(0,500)" disabled="disabled"disabled="disabled"></textarea>
						<input  type="hidden"  value="<s:property value="#qsts.qstScore"/>" id="fenid<s:property value="#multiQstCount"/>"/>
						</fieldset>
						</div>
					 </s:if>
				  </s:iterator>
				   
                 </div>
				 </s:if>
               </s:iterator>
			   
            </div>
             
            </div>
			<input type="hidden" id="qstNum" value="<s:property value="exam.qstmiddle.size"/>" />
			<input type="hidden" name="subRlt" id="subRlt" value="" />
			<input type="hidden" name="usetime" value="" id="usetimes" />
			<input  type="hidden"  value="<s:property value="#multiQstCount"/>" id="fenshuid" name="fenshuid"/>
			<input  type="hidden" name="zipingfen" value="" id="zipingfen"/>
			<input  type="hidden" name="zhuguan" value="0" id="zhuguan"/>
			</form>
            </td>
          </tr>
        </table>
  		</div>
    	<img id="con_con_bottom2" src="<%=importURL%>/images/usercenter/con3_bg_bottom.png" />
       
    </div>
	<div class="clear"></div>
    <div class="goto_top"></div>
</div>

</body>
</html>
