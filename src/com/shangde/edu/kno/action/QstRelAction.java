package com.shangde.edu.kno.action;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.exam.condition.QueryOptionsCondition;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.service.IOptions;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.kno.domain.QstRel;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.IQstRel;
import com.shangde.edu.kno.service.ISysNode;

public class QstRelAction extends CommonAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<QstRel> qstRelList=new ArrayList <QstRel>();
	private IQstRel qstRelService;
	private IOptions optionsService;
	private QueryOptionsCondition queryOptionsCondition;
	private int ksnId;
	private int qrId;
	private int qstId;
	private List <SysNode> sysNodeList=new ArrayList <SysNode>();
	private List <Options> optionList =new ArrayList <Options>();
	private List <SysNode> sysNodeList1=new ArrayList <SysNode>();
	/**
	 * 增加试题知识点关联公共方法
	 * @return
	 */
	private SysNode sysNode;
	private ISysNode sysNodeService;
	private Qst qst;
	private IQst qstService;
	/**
	 * 结束
	 */


	
	/**
	 * @author 王超
	 * 转到试题知识点关联列表
	 */
	public String toQstRelList(){
		try {
			//获取父节点列表
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList.add(sysNodeList1.get(i-1));
			}
			//获取试题关联列表
			qstRelList=qstRelService.getQstRelListByKsnId(ksnId);
			return "toQstRelList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 删除关联
	 * @return
	 */
	public String delQstRel(){
		try {
			qstRelService.delQstRelById(qrId);
			this.setResult(new Result(true,"","",null));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 转到试题预览页面
	 * @return
	 */
	public String toQstPreview(){
		ksnId=Integer.parseInt(servletRequest.getParameter("ksnId"));
		qst=qstService.getQstById(qstId);
		optionList=qstRelService.getOptionsListByQstId(qstId);
		return "qstPreview";
	}
	
	/**
	 * @author 王超
	 * 增加关联公共方法
	 * @param qstId试题id
	 * @param ksnId知识树节点id
	 */
	public void addQstRel(int qstId,int ksnId){
		qst=qstService.getQstById(qstId);
		sysNode=sysNodeService.getSysNodeById(ksnId);
		QstRel qstRel=new QstRel();
		qstRel.setKsnId(sysNode.getKsnId());
		qstRel.setNodeId(sysNode.getNodeId());
		qstRel.setQstContent(qst.getQstContent());
		qstRel.setQstType(qst.getQstType());
		qstRel.setQstId(qst.getQstId());
		qstRelService.addQstRel(qstRel);
	}
	
	public List<QstRel> getQstRelList() {
		return qstRelList;
	}

	public void setQstRelList(List<QstRel> qstRelList) {
		this.qstRelList = qstRelList;
	}

	public IQstRel getQstRelService() {
		return qstRelService;
	}

	public void setQstRelService(IQstRel qstRelService) {
		this.qstRelService = qstRelService;
	}
	
	public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getQrId() {
		return qrId;
	}

	public void setQrId(int qrId) {
		this.qrId = qrId;
	}
	
	public SysNode getSysNode() {
		return sysNode;
	}
	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}
	public ISysNode getSysNodeService() {
		return sysNodeService;
	}
	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}
	public Qst getQst() {
		return qst;
	}
	public void setQst(Qst qst) {
		this.qst = qst;
	}
	public IQst getQstService() {
		return qstService;
	}
	public void setQstService(IQst qstService) {
		this.qstService = qstService;
	}
	public List<SysNode> getSysNodeList() {
		return sysNodeList;
	}
	public void setSysNodeList(List<SysNode> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}
	public List<Options> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<Options> optionList) {
		this.optionList = optionList;
	}
	public int getQstId() {
		return qstId;
	}
	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
	public QueryOptionsCondition getQueryOptionsCondition() {
		if(queryOptionsCondition==null){
			queryOptionsCondition=new QueryOptionsCondition();
		}
		return queryOptionsCondition;
	}
	public void setQueryOptionsCondition(QueryOptionsCondition queryOptionsCondition) {
		this.queryOptionsCondition = queryOptionsCondition;
	}
	public IOptions getOptionsService() {
		return optionsService;
	}
	public void setOptionsService(IOptions optionsService) {
		this.optionsService = optionsService;
	}
}
