package com.shangde.common.util.page;

public class PageUtilImpl implements IPageUtil{

	public String pageHTML(PageInfo pageInfo) {
		StringBuffer sb = new StringBuffer();
		
		int totalPage = pageInfo.getTotalPage();
		int pageNo = pageInfo.getPageNo();
		int startPage = 1;
		
		if(pageNo > 6) {
			if((totalPage - pageNo)>5) {
				startPage = pageNo - 5;
			} else {
				startPage = totalPage - 10;
			}
		}
		

		if(pageNo == 1) {
			sb.append("<a>上一页</a>");
		} else {
			sb.append("<a href='javascript:goPage(" + (pageNo - 1) + ")'>上一页</a>");
		}

		if((totalPage - startPage) <11) {
			for(int i=0; i <= (totalPage - startPage); i++) {
				if(pageNo == (startPage + i)) {
					sb.append("<a style='color:#fff; background:#c00000;'>" + (startPage+i) + "</a>");
				} else {
					sb.append("<a href='javascript:goPage(" + (startPage+i) + ")'>" + (startPage+i) + "</a>");
				}
			}
		} else {
			for(int i=0; i<8; i++) {
				if(pageNo == (startPage + i)) {
					sb.append("<a style='color:#fff; background:#c00000;'>" + (startPage+i) + "</a>");
				} else {
					sb.append("<a href='javascript:goPage(" + (startPage+i) + ")'>" + (startPage+i) + "</a>");
				}
			}
			sb.append("…&nbsp;");
			for(int i=1; i>=0; i--) {
				if(pageNo == (totalPage - i)) {
					sb.append("<a style='color:#fff; background:#c00000;'>" + pageNo + "</a>");
				} else {
					sb.append("<a href='javascript:goPage(" + (totalPage-i) + ")'>" + (totalPage-i) + "</a>");
				}
			}
		}

		if(pageNo == totalPage) {
			sb.append("<a>下一页</a>");
		} else {
			sb.append("<a href='javascript:goPage(" + (pageNo + 1) + ")'>下一页</a>");
		}
		sb.append( "到 第<input maxlength=5 type=text class=ans_feiye_text id='iptPageNo' onchange='iptChange()'/>页<input type=button class=ans_feiye_but value=确定 onclick='goIptPage()'/>");
		
		
		sb.append("<script type=\"text/javascript\" language=\"JavaScript\">");
		sb.append("var totalPage = " + totalPage + ";");
		sb.append("function iptChange() {");
		sb.append("	var iptPageNo = parseInt(\\$(\"#iptPageNo\").val());");
		sb.append("	if(isNaN(iptPageNo) || iptPageNo == '' || iptPageNo < 1) {");
		sb.append("		\\$(\"#iptPageNo\").val(1);");
		sb.append("	} else if(iptPageNo > totalPage) {");
		sb.append("		\\$(\"#iptPageNo\").val(totalPage);");
		sb.append("	}");
		sb.append("}");
		sb.append("function goIptPage() {");
		sb.append("	var iptPageNo = \\$(\"#iptPageNo\").val();");
		sb.append("	if(isNaN(iptPageNo) || iptPageNo == '') {");
		sb.append("		iptPageNo = 1;");
		sb.append("	}");
		sb.append("	goPage(iptPageNo);");
		sb.append("}");
		sb.append("");
		sb.append("function goPage(pageNum){");
		sb.append("    window.location.href = 'index_' + pageNum + '.shtml';");
		sb.append("}");
		sb.append("</script>");
		return sb.toString();
	}
}
