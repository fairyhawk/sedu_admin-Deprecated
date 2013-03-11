package com.shangde.edu.exam.action;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IQstmiddle;
import com.shangde.edu.sys.domain.User;

public class ExamQstMidAction extends CommonAction {
	//变量
	
	private static final Logger logger = Logger.getLogger(ExamQstMidAction.class);
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;
	
	/**
	 * 中间表的服务
	 */
	private IQstmiddle qstmiddleService;
	
	private int status;
	/**
	 * 单选试题id
	 */
	private String qstId;
	/**
	 * 多选试题id
	 */
	private String duoxuan_qstId;
	
	/**
	 * 判断试题id
	 */
	private String panduan_qstId;
	
	/**
	 * 材料试题id
	 */
	private String cailiao_qstId;
	
	/**
	 * 图表试题id
	 */
	private String tubiao_qstId;
	
	/**
	 * 主管试题id
	 */
	private String zhuguan_qstId;
	
	/**
	 * 试卷id
	 */
	private int epId;
	
	/**
	 * 单选试题类型
	 */
	private String qstType;
	
	/**
	 * 单选试题分数
	 */
	private String qstScore;
	/**
	 * 多选试题分数
	 */
	private String duoxuan_qstScore;
	
	/**
	 * 判断试题分数
	 */
	private String panduan_qstScore;
	
	/**
	 * 材料试题分数
	 */
	private String cailiao_qstScore;
	
	/**
	 * 图表试题分数
	 */
	private String tubiao_qstScore;
	
	/**
	 * 主管试题分数
	 */
	private String zhuguan_qstScore;
	/**
	 * 删除用分数
	 */
	private float score;
	/**
	 * 总分
	 */
	private float totleScore;
	
	private int eqId;
	
	/**
	 * 删除试题表试题弹框条件
	 */
	private int qstid;
	
	//方法
	/**
	 * 通过中间表eqId
	 * 删除方法
	 */
	public String ExamQstDel(){
		
		try {
			
			qstmiddleService.delQstmiddleById(eqId);
			Exampaper exam=exampaperService.getExampaperById(epId);
			Float examtotel=totleScore-score;
			int a=exam.getQstmiddlecount()-1;
			exam.setEpTotelScore(examtotel);
			exam.setQstmiddlecount(a);
			exam.setLastEditTime(new Date());
			exampaperService.updateExampaper(exam);
			this.setResult(new Result(true,"ok",null,null));
		} catch (RuntimeException e) {
			logger.error("ExamQstMidAction.ExamQstDel",e);
			this.setResult(new Result(true,"no",null,null));
		}
		
		return "json";
	}
	
	/**
	 *考试中间表的添加
	 *
	 */
	public String EcxamQstAdd(){
		try {
			float danxuanjifen=0;
			float duoxuanjifen=0;
			float panduanjifen=0;
			float cailiaojifen=0;
			float tubiaojifen=0;
			float jiandajifen=0;
			int x=0;
			String[] qstTypelist=qstType.split(",");
			for(int a=0;a<qstTypelist.length;a++){
				//添加单选题
				if(qstTypelist[a].trim().equals("1")){
					if(qstId!=null){
						String[] qstIdlist=qstId.split(",");
						String[] qstScorelist=qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<qstIdlist.length;i++)
						{
							x++;
							qstmid=new Qstmiddle();
							qstmid.setEpId(epId);
							qstmid.setQstType(1);
							qstmid.setQstScore(Float.parseFloat(qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							danxuanjifen+=Float.parseFloat(qstScorelist[i].trim());
						}
					}
				}
				
				//添加多选题
				if(qstTypelist[a].trim().equals("2")){
					if(duoxuan_qstId!=null){
						String[] duoxuan_qstIdlist=duoxuan_qstId.split(",");
						String[] duoxuan_qstScorelist=duoxuan_qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<duoxuan_qstIdlist.length;i++)
						{
							x++;
							qstmid=new Qstmiddle();
							
							qstmid.setEpId(epId);
							qstmid.setQstType(2);
							qstmid.setQstScore(Float.parseFloat(duoxuan_qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(duoxuan_qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							duoxuanjifen+=Float.parseFloat(duoxuan_qstScorelist[i].trim());
						}
					}
				}
				
				//添判断题
				if(qstTypelist[a].trim().equals("3")){
					if(panduan_qstId!=null){
						String[] panduan_qstIdlist=panduan_qstId.split(",");
						String[] panduan_qstScorelist=panduan_qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<panduan_qstIdlist.length;i++)
						{
							x++;
							qstmid=new Qstmiddle();
							
							qstmid.setEpId(epId);
							qstmid.setQstType(3);
							qstmid.setQstScore(Float.parseFloat(panduan_qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(panduan_qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							panduanjifen+=Float.parseFloat(panduan_qstScorelist[i].trim());
						}
					}
				}
				
				//添加材料分析题
				if(qstTypelist[a].trim().equals("4")){
					if(cailiao_qstId!=null){
						String[] cailiao_qstIdlist=cailiao_qstId.split(",");
						String[] cailiao_qstScorelist=cailiao_qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<cailiao_qstIdlist.length;i++)
						{
							x++;
							qstmid=new Qstmiddle();
							
							qstmid.setEpId(epId);
							qstmid.setQstType(4);
							qstmid.setQstScore(Float.parseFloat(cailiao_qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(cailiao_qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							cailiaojifen+=Float.parseFloat(cailiao_qstScorelist[i].trim());
							
							String[] cailiao_qstIdlistzi=servletRequest.getParameterValues("cailiao_qstId"+cailiao_qstIdlist[i].trim());
							String[] cailiao_qstScorelistzi=servletRequest.getParameterValues("cailiao_qstScore"+cailiao_qstIdlist[i].trim());
							if(cailiao_qstIdlistzi!=null&&cailiao_qstScorelistzi!=null){
								for(int h=0;h<cailiao_qstIdlistzi.length;h++){
									x++;
									qstmid=new Qstmiddle();
									qstmid.setEpId(epId);
									qstmid.setQstType(4);
									qstmid.setQstScore(Float.parseFloat(cailiao_qstScorelistzi[h].trim()));
									qstmid.setQstId(Integer.parseInt(cailiao_qstIdlistzi[h].trim()));
									qstmiddleService.addQstmiddle(qstmid);
									cailiaojifen+=Float.parseFloat(cailiao_qstScorelistzi[h].trim());
								}
							}
						}
					}
				}
				
				
				//添加图表题
				if(qstTypelist[a].trim().equals("5")){
					if(tubiao_qstId!=null){
						String[] tubiao_qstIdlist=tubiao_qstId.split(",");
						String[] tubiao_qstScorelist=tubiao_qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<tubiao_qstIdlist.length;i++)
						{
							x++;
							qstmid=new Qstmiddle();
							
							qstmid.setEpId(epId);
							qstmid.setQstType(5);
							qstmid.setQstScore(Float.parseFloat(tubiao_qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(tubiao_qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							tubiaojifen+=Float.parseFloat(tubiao_qstScorelist[i].trim());
						}
					}
				}
				
				//添加主管题
				if(qstTypelist[a].trim().equals("6")){
					if(zhuguan_qstId!=null){
						String[] zhuguan_qstIdlist=zhuguan_qstId.split(",");
						String[] zhuguan_qstScorelist=zhuguan_qstScore.split(",");
						Qstmiddle qstmid =null;
						for(int i=0;i<zhuguan_qstIdlist.length;i++)
						{
							qstmid=new Qstmiddle();
							x++;
							qstmid.setEpId(epId);
							qstmid.setQstType(6);
							qstmid.setQstScore(Float.parseFloat(zhuguan_qstScorelist[i].trim()));
							qstmid.setQstId(Integer.parseInt(zhuguan_qstIdlist[i].trim()));
							qstmiddleService.addQstmiddle(qstmid);
							jiandajifen+=Float.parseFloat(zhuguan_qstScorelist[i].trim());
							
						}
					}
				}
			}
			Exampaper exam=exampaperService.getExampaperById(epId);
			Float examtotel=Float.parseFloat(servletRequest.getParameter("totelscore").toString());
			int examLevel=Integer.parseInt(servletRequest.getParameter("fabu"));
			exam.setLevel(examLevel);
			exam.setQstmiddlecount(x+exam.getQstmiddlecount());
			float zongjifen=danxuanjifen+duoxuanjifen+panduanjifen+cailiaojifen+tubiaojifen+jiandajifen;
			if(status==2) {
				 zongjifen+=examtotel;
			}
			exam.setEpTotelScore(zongjifen);
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			exam.setAuthor(users.getLoginName());
			exam.setLastEditTime(new Date());
			exampaperService.updateExampaper(exam);
			
		} catch (RuntimeException e) {
			logger.error("ExamQstMidAction.EcxamQstAdd",e);
			return ERROR;
		}
		return "EcxamQstAdd";
	}
	
	@SuppressWarnings("unchecked")
	public String Qstdelinfo(){
		try {
			StringBuffer buf = new StringBuffer();
			List<Qstmiddle> mid=qstmiddleService.getQstbyQstid(qstid);
			for(int i=0;i<mid.size();i++){
				buf.append(mid.get(i).getExam().getEpName()+"  ");
			}
			this.setResult(new Result(true,buf.toString(),null,null));
		} catch (RuntimeException e) {
			logger.error("ExamQstMidAction.Qstdelinfo",e);
			return ERROR;
		}
		
		return "json";
	}
	
	public IQstmiddle getQstmiddleService() {
		return qstmiddleService;
	}

	public void setQstmiddleService(IQstmiddle qstmiddleService) {
		this.qstmiddleService = qstmiddleService;
	}

	public String getQstId() {
		return qstId;
	}

	public void setQstId(String qstId) {
		this.qstId = qstId;
	}

	public int getEpId() {
		return epId;
	}

	public void setEpId(int epId) {
		this.epId = epId;
	}

	public String getDuoxuan_qstId() {
		return duoxuan_qstId;
	}

	public void setDuoxuan_qstId(String duoxuan_qstId) {
		this.duoxuan_qstId = duoxuan_qstId;
	}

	public String getPanduan_qstId() {
		return panduan_qstId;
	}

	public void setPanduan_qstId(String panduan_qstId) {
		this.panduan_qstId = panduan_qstId;
	}

	public String getCailiao_qstId() {
		return cailiao_qstId;
	}

	public void setCailiao_qstId(String cailiao_qstId) {
		this.cailiao_qstId = cailiao_qstId;
	}

	public String getTubiao_qstId() {
		return tubiao_qstId;
	}

	public void setTubiao_qstId(String tubiao_qstId) {
		this.tubiao_qstId = tubiao_qstId;
	}

	public String getZhuguan_qstId() {
		return zhuguan_qstId;
	}

	public void setZhuguan_qstId(String zhuguan_qstId) {
		this.zhuguan_qstId = zhuguan_qstId;
	}

	public String getQstType() {
		return qstType;
	}

	public void setQstType(String qstType) {
		this.qstType = qstType;
	}

	public String getQstScore() {
		return qstScore;
	}

	public void setQstScore(String qstScore) {
		this.qstScore = qstScore;
	}

	public String getDuoxuan_qstScore() {
		return duoxuan_qstScore;
	}

	public void setDuoxuan_qstScore(String duoxuan_qstScore) {
		this.duoxuan_qstScore = duoxuan_qstScore;
	}

	public String getPanduan_qstScore() {
		return panduan_qstScore;
	}

	public void setPanduan_qstScore(String panduan_qstScore) {
		this.panduan_qstScore = panduan_qstScore;
	}

	public String getCailiao_qstScore() {
		return cailiao_qstScore;
	}

	public void setCailiao_qstScore(String cailiao_qstScore) {
		this.cailiao_qstScore = cailiao_qstScore;
	}

	public String getTubiao_qstScore() {
		return tubiao_qstScore;
	}

	public void setTubiao_qstScore(String tubiao_qstScore) {
		this.tubiao_qstScore = tubiao_qstScore;
	}

	public String getZhuguan_qstScore() {
		return zhuguan_qstScore;
	}

	public void setZhuguan_qstScore(String zhuguan_qstScore) {
		this.zhuguan_qstScore = zhuguan_qstScore;
	}

	public int getEqId() {
		return eqId;
	}

	public void setEqId(int eqId) {
		this.eqId = eqId;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public int getQstid() {
		return qstid;
	}

	public void setQstid(int qstid) {
		this.qstid = qstid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public float getTotleScore() {
		return totleScore;
	}

	public void setTotleScore(float totleScore) {
		this.totleScore = totleScore;
	}
}
