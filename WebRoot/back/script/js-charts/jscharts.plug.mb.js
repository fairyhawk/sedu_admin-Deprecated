
/*
 * JSCharts MBString Patch for v2.06, by iLwave [28th Apr, 2010]
 *
 * page charset UTF-8, then include this script (below) with JSChart.
 *
 * demo:
 *
 *  var myChart = new JSChart('graph', 'line');
 * 		myChart.patchMbString();
 *
 *		//native code below.
 *
 */

JSChart.prototype.patchMbString = function(bUseVML) {
	
	if (this.bMbStringPathed) return false; //避免重复patch
	
	var bUseVML = bUseVML || false;
	var _this = this;
	
	var fGetVmlStrokeText = function() {

		var sVML = "<v:textbox id=\"{11}\" inset=\"0pt,0pt,0pt,0pt\" style=\"position:absolute; left:{1}px; top:{2}px; font-size:{3}px; font-weight:normal; width:auto; text-spacing:2px; font-family:{7}; color:{8}; margin:0px;\" rotation={10}>{0}</v:textbox>";
		
		for (var i=0; i<12; i++) sVML = sVML.replace("{" + i + "}", arguments[i]);
		
		return sVML;
		
	};
	
	this.isIE = "\v" == "v";
	this.fontFamily = "Tahoma"; // default font family
	
	this.setFontFamily = function(sFontFamily) {
		this.fontFamily = sFontFamily;
	};
	
	bUseVML ?
	
	this.jC.ez = function(text,dd,de,fontsize,eY,eZ,fa,fb,color,dG,fc,id) {
		
		if (typeof this.I === false || typeof text === 'undefined' || typeof dd === 'undefined' || typeof de === 'undefined') {
		    return false;
		}
		
		var nMbTextCount = text.replace(/[^\u4e00-\u9fa5]/ig, "").length;
		
		dd -= nMbTextCount * fontsize * 0.6; // 修复中文字符的双倍宽度导致的偏移

		var gE = this.eX(fontsize,eY,eZ,fa,fb,color,dG,fc,id);
		
		this.I.strokeStyle = this.dT(gE.color,gE.dG);
		
		if(!_this.isIE) {

			this.I.font = (gE.fontsize+1) + "px " + _this.fontFamily;
			this.I.fillStyle = gE.color;
			this.I.fillText(text,dd-2,de+10,gE.eY);
			
		} else {
			
			document.getElementById(this.D).getElementsByTagName('div')[0].innerHTML += fGetVmlStrokeText(text,dd,de,gE.fontsize+2,gE.eY,gE.eZ,gE.fa,_this.fontFamily,gE.color,gE.dG*100,gE.fc,gE.id);
			
		}

	} :
	
	this.jC.ez = function(text,dd,de,fontsize,eY,eZ,fa,fb,color,dG,fc,id) {

		if (typeof this.I === false || typeof text === 'undefined' || typeof dd === 'undefined' || typeof de === 'undefined') {
		    return false;
		}
		
		var nMbTextCount = text.replace(/[^\u4e00-\u9fa5]/ig, "").length;
		
		dd -= nMbTextCount * fontsize * 0.6; // 修复中文字符的双倍宽度导致的偏移

		var gE = this.eX(fontsize,eY,eZ,fa,fb,color,dG,fc,id);
		
		this.I.strokeStyle = this.dT(gE.color,gE.dG);
		this.I.font = (gE.fontsize+1) + "px " + _this.fontFamily;
		this.I.fillStyle = gE.color;
		this.I.fillText(text,dd-2,de+10,gE.eY);

	};
	
	this.bMbStringPathed = true;
};