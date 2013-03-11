package com.shangde.edu.kno.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kno.condition.QueryPreItemCondition;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;
import com.shangde.edu.kno.condition.QuerySysCondition;
import com.shangde.edu.kno.condition.QuerySysNodeCondition;
import com.shangde.edu.kno.domain.NodePreMid;
import com.shangde.edu.kno.domain.PreItem;
import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.dto.SysNodeDTO;
import com.shangde.edu.kno.service.INodePreMid;
import com.shangde.edu.kno.service.IPreItem;
import com.shangde.edu.kno.service.IPreNode;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class KnowledgeSystemAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger=Logger.getLogger(KnowledgeSystemAction.class);
	private ISys sysService;
	private ISysNode sysNodeService;
	private Sys sys;
	private SysNode sysNode;
	private SysNode sNode;
	private NodePreMid nodePreMid;
	private NodePreMid nPreMid;
	private INodePreMid nodePreMidService;
	private IPreItem preItemService;
	private IPreNode preNodeService;
	private PreItem preItem;
	private PreNode preNode;
	private QuerySysCondition querySysCondition;
	private QueryPreItemCondition queryPreItemCondition;
	private QueryPreNodeCondition queryPreNodeCondition;
	private QuerySysNodeCondition querySysNodeCondition;
	private ISubject subjectService;
	private List<Subject> subjectList=new  ArrayList<Subject>();
	private Subject subject;
	private int ksId;
	private int ksnId;
	private int status;
	private String nodeId;
	private int parentId;
	private int preId;
	private String reason;
	private List<Sys> sysinfolist =new ArrayList<Sys>();
	private List<PreItem> preItemlist =new ArrayList<PreItem>();
	private List<PreNode> preNodelist =new ArrayList<PreNode>();
	private List<SysNode> sysNodelist =new ArrayList<SysNode>();
	private List <SysNode> sysNodeList1=new ArrayList <SysNode>();
	private List <SysNode> sysNodeList2=new ArrayList <SysNode>();
	
	/**
	 * 子节点数量
	 */
	private int count;
	private List<SysNode> sysTree=new ArrayList<SysNode>();
	private List<SysNodeDTO> sysNodeList=new ArrayList<SysNodeDTO>();
	
	/**
	 * @author 王超
	 * 录入管理
	 * @return
	 */
	public String knowledgeSystemList(){
		try {
			//专业列表
			subjectList=subjectService.getAllSubject();
			this.getQuerySysCondition().setPageSize(20);
			if(subject!=null){
			this.getQuerySysCondition().setSubjectId(subject.getSubjectId());
			}
			//设置页面
			this.setPage(sysService.listSysByCondition(this.getQuerySysCondition()));
			this.getPage().setPageSize(20);
			this.setPageUrlParms();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return "knowledgeSystemList";
	}
	/**
	 * @author 王超
	 * 转到新建知识树
	 * @return
	 */
	public String toAddKnoSys(){
		try {
			//获取专业列表
			subjectList=subjectService.getAllSubject();
			
			return "knowledgeSystemAdd";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * 新建知识树
	 * @return
	 */
	public String addKnoSys(){
		try {
			sys.setSubjectId(subject.getSubjectId());
			sys.setSubjectName(subjectService.getSubjectById(subject.getSubjectId()).getSubjectName());
			sys.setStatus(1);
			sysService.addSys(sys);
			sysNode.setKsId(sys.getKsId());
			sysNode.setLevel(1);
			sysNode.setNodeName(sys.getSubjectName());
			sysNode.setParentId(-1);
			sysNode.setSortId(0);
			//获取登录用户对象
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			sysNode.setAuthor(users.getLoginName());
			sysNode.setCreateTime(new Date());
			sysNodeService.addSysNode(sysNode);

			sys=sysService.getSysById(sys.getKsId());
			sysNode=sysNodeService.getSysNodeBySysId(sys.getKsId());
			//获取知识树列表
			if(ksnId==0){
				ksnId=sysNode.getKsnId();
			}
			sysTree=sysNodeService.getSysTreeById(sys.getKsId());
			return "knowledgeTree";
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 新建知识树节点
	 * @return
	 */
	public String addSysNode(){
		try {
			int a=sysNodeService.getCountByParentId(sysNode.getParentId())+1;
			if(a!=sysNode.getSortId()){
				//排序id没有按照正常顺序排
				this.getQuerySysNodeCondition().setParentId(sysNode.getParentId());
				this.getQuerySysNodeCondition().setMinId(sysNode.getSortId());
				//获取需要重新排序的节点列表
				sysNodelist=sysNodeService.getSysNodeListByCondition(this.getQuerySysNodeCondition());
				for(int i=0;i<sysNodelist.size();i++){
					//对子节点重新排序
					SysNode s=sysNodelist.get(i);
					s.setSortId(s.getSortId()+1);
					sysNodeService.updateSysNode(s);
				}
			}
			//获取登录用户对象
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			sysNode.setAuthor(users.getLoginName());
			sysNode.setCreateTime(new Date());
			sysNode.setLastEditTime(new Date());
			sysNodeService.addSysNode(sysNode);
			
			if(nodePreMid.getPreNodeId()!=-1){
			//没有选择预设项时
			nodePreMid.setKsnId(sysNode.getKsnId());
			nodePreMid.setNodeId(sysNode.getNodeId());
			nodePreMid.setPreNodeName(preNodeService.getPreNodeById(nodePreMid.getPreNodeId()).getPreNodeName());
			nodePreMidService.addNodePreMid(nodePreMid);
			}
			sys=sysService.getSysById(sysNode.getKsId());
			ksnId=sysNode.getParentId();
			sysNode=sysNodeService.getSysNodeBySysId(sysNode.getKsId());
			//获取知识树列表
			sysTree=sysNodeService.getSysTreeById(sys.getKsId());
			return "knowledgeTree";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 更新知识树节点
	 * @return
	 */
	public String updateSysNode(){
		try {
			//获取登录用户对象
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			sNode=sysNodeService.getSysNodeById(sysNode.getKsnId());
			if(sNode.getSortId()>sysNode.getSortId()){
				//新排序id变小
				this.getQuerySysNodeCondition().setMinId(sysNode.getSortId());
				this.getQuerySysNodeCondition().setMaxId(sNode.getSortId());
				this.getQuerySysNodeCondition().setParentId(sNode.getParentId());
				sysNodelist=sysNodeService.getSysNodeListByCondition(this.getQuerySysNodeCondition());
				for(int i=0;i<sysNodelist.size()-1;i++){
					SysNode s=sysNodelist.get(i);
					s.setSortId(s.getSortId()+1);
					sysNodeService.updateSysNode(s);
				}
			}
			if(sNode.getSortId()<sysNode.getSortId()){
				//新排序id变大
				this.getQuerySysNodeCondition().setMaxId(sysNode.getSortId());
				this.getQuerySysNodeCondition().setMinId(sNode.getSortId());
				this.getQuerySysNodeCondition().setParentId(sNode.getParentId());
				sysNodelist=sysNodeService.getSysNodeListByCondition(this.getQuerySysNodeCondition());
				for(int i=1;i<sysNodelist.size();i++){
					SysNode s=sysNodelist.get(i);
					s.setSortId(s.getSortId()-1);
					sysNodeService.updateSysNode(s);
				}
			}
			sNode.setAuthor(users.getLoginName());
			sNode.setLastEditTime(new Date());
			sNode.setNodeId(sysNode.getNodeId());
			sNode.setNodeName(sysNode.getNodeName());
			sNode.setSortId(sysNode.getSortId());
			sysNodeService.updateSysNode(sNode);
			//已选择预设项
				if(nodePreMid.getPreNodeId()!=-1){
					nPreMid=nodePreMidService.getNodePreMidByKsnId(sNode.getKsnId());
					if(nPreMid==null){
						//之前未选择过预设项
						nodePreMid.setKsnId(sysNode.getKsnId());
						nodePreMid.setNodeId(sysNode.getNodeId());
						nodePreMid.setPreNodeName(preNodeService.getPreNodeById(nodePreMid.getPreNodeId()).getPreNodeName());
						nodePreMidService.addNodePreMid(nodePreMid);
					}else{
						//之前选择过预设项
					nPreMid.setPreId(nodePreMid.getPreId());
					nPreMid.setPreNodeId(nodePreMid.getPreNodeId());
					nPreMid.setPreNodeName(preNodeService.getPreNodeById(nodePreMid.getPreNodeId()).getPreNodeName());
					nodePreMidService.updateNodePreMid(nPreMid);
					}
				}else{
					nPreMid=nodePreMidService.getNodePreMidByKsnId(sNode.getKsnId());
					//未选择预设项而之前选择过预设项
					if(nPreMid!=null){
						nodePreMidService.delNodePreMidById(nPreMid.getId());
					}
				}
			ksnId=sNode.getParentId();
			sys=sysService.getSysById(sNode.getKsId());
			sysNode=sysNodeService.getSysNodeBySysId(sNode.getKsId());
			//获取知识树列表
			sysTree=sysNodeService.getSysTreeById(sys.getKsId());
			return "knowledgeTree";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 删除知识树节点
	 * @return
	 */
	public String delSysNode(){
		try {
			int count=sysNodeService.getCountByParentId(ksnId);
			if(count==0){
				//没有子节点就删除
			sysNodeService.delSysNodeById(ksnId);
			this.setResult(new Result(true,"","",null));
			}else{
				this.setResult(new Result(false,"","",null));
			}
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 转到修改知识树页面
	 * @return
	 */
	public String toUpdateKnoSys(){
		
		try {
			sys=sysService.getSysById(ksId);
			return "knowledgeSystemUpdate";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 更新知识树数据
	 * @return
	 */
	public String updateKnoSys(){
		try {
			sys.setSubjectId(subject.getSubjectId());
			sys.setSubjectName(subjectService.getSubjectById(subject.getSubjectId()).getSubjectName());
			sysService.updateSys(sys);
			
			sys=sysService.getSysById(sys.getKsId());
			sysNode=sysNodeService.getSysNodeBySysId(sys.getKsId());
			//获取知识树列表
			if(ksnId==0){
				ksnId=sysNode.getKsnId();
			}
			sysTree=sysNodeService.getSysTreeById(sys.getKsId());
			return "knowledgeTree";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 修改知识树状态
	 * @return
	 */
	public String changeStatus(){
		try {
			sys=sysService.getSysById(ksId);
			sys.setStatus(status);
			sysService.updateSys(sys);
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
	 * 审核管理
	 * @return
	 */
	public String knowledgeSystemListRes(){
		try {
			subjectList=subjectService.getAllSubject();
			this.getQuerySysCondition().setPageSize(20);
			if(status!=0){
				this.getQuerySysCondition().setStatus(status);
			}
			if(subject!=null){
			this.getQuerySysCondition().setSubjectId(subject.getSubjectId());
			}
			//设置审核页面
			this.setPage(sysService.listSysByCondition(this.getQuerySysCondition()));
			this.getPage().setPageSize(20);
			this.setPageUrlParms();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return "knowledgeSystemListRes";
	}
	/**
	 * @author 王超
	 * 转到知识树审核页面
	 * @return
	 */
	public String toKnowledgeSystemRes(){
		sys=sysService.getSysById(ksId);
		return "toKnowledgeSystemRes";
	}
	/**
	 * @author 王超
	 * 转到知识树详细审核页面
	 * @return
	 */
	public String toKnowledgeTreeRes(){
		try {
			sys=sysService.getSysInFoByKsId(ksId);
			String author=sysNodeService.getSysAuthorByKsId(ksId);
			sys.setAuthor(author);
			sysNode=sysNodeService.getSysNodeBySysId(ksId);
			if(ksnId==0){
				ksnId=sysNode.getKsnId();
			}
			//获取知识树列表
			sysTree=sysNodeService.getSysTreeById(ksId);
			return "knowledgeTreeRes";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 加载知识树审核的节点列表
	 * @return
	 */
	public String knowledgeTreeZiRes(){
		try {
			//获取父节点列表
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList2.add(sysNodeList1.get(i-1));
			}
			sysNodeList=sysNodeService.getSysNodeDTOListByParentId(ksnId);
			sysNode=sysNodeService.getSysNodeBySysId(ksId);
			return "knowledgeTreeZiRes";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 转到知识树页面
	 * @return
	 */
	public String toKnowledgeTree(){
		try {
			sys=sysService.getSysById(ksId);
			sysNode=sysNodeService.getSysNodeBySysId(ksId);
			if(ksnId==0){
				ksnId=sysNode.getKsnId();
			}
			//获取知识树列表
			sysTree=sysNodeService.getSysTreeById(ksId);
			return "knowledgeTree";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 加载知识树的节点列表
	 * @return
	 */
	public String knowledgeTreeZi(){
		try {
			//获取预设项列表
			preItemlist=preItemService.getPreItemList(this.getQueryPreItemCondition());
			//获取当前节点的子节点数量
			count=sysNodeService.getCountByParentId(ksnId);
			sNode=sysNodeService.getSysNodeById(ksnId);
			//获取父节点列表
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList2.add(sysNodeList1.get(i-1));
			}
			sysNodeList=sysNodeService.getSysNodeDTOListByParentId(ksnId);
			sysNode=sysNodeService.getSysNodeBySysId(ksId);
			return "knowledgeTreeZi";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 删除知识树
	 * @return
	 */
	public String delSys(){
		try {
			int count=sysNodeService.getSysNodeCount(ksId);
			if(count==1){
			//没有相关子节点就删除
			sysService.delSysById(ksId);
			sysNodeService.delSysNodeById(sysNodeService.getSysNodeBySysId(ksId).getKsnId());
			this.setResult(new Result(true,"","",null));
			}else{
			this.setResult(new Result(false,"","",null));
			}
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
	}
	/**
	 * @author 张聚强
	 * 获取预设项节点
	 * @return
	 */
	public String getPreNodeById(){
		try {
			this.getQueryPreNodeCondition().setPreItemId(preId);
			preNodelist=preNodeService.getPreNodeListByPreItemId(queryPreNodeCondition);
			this.setResult(new Result(false,"","",preNodelist));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 何海强
	 * 转到知识树管理页面
	 * @return
	 */
	public String knowLedgeMain(){
		try {
			//消息列表显示
			
			sysinfolist=sysService.getSysInFoList();
			
			//项目列表显示
			this.getQuerySysCondition().setPageSize(20);
			this.setPage(sysService.listSysByCondition(this.getQuerySysCondition()));
			this.getPage().setPageSize(20);
			this.setPageUrlParms();
			return "knowledgeMain";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 获取修改节点信息
	 * @return
	 */
	public String getSysNodeInfo(){
		//当前节点的预设项关联对象
		nodePreMid=nodePreMidService.getNodePreMidByKsnId(ksnId);
		if(nodePreMid==null){
			this.setResult(new Result(false,null,null,null));
		}else{
			this.setResult(new Result(true,null,null,nodePreMid));
		}
		return "json";
	}
	/**
	 *@author 张聚强
	 * 返工
	 * @return
	 */
	public String redo(){
		try{
			sys=sysService.getSysById(ksId);
			sys.setStatus(5);
			sys.setReason(reason);
			sys.setReviewer(getLoginedUser().getLoginName());
			sysService.updateSys(sys);
			this.setResult(new Result(true,"","",null));
		}catch(RuntimeException e){
			logger.error(e.getMessage());
			this.setResult(new Result(false,"","",null));
		}
		return "json";
	}
	public ISys getSysService() {
		return sysService;
	}
	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}
	public QuerySysCondition getQuerySysCondition() {
		if(querySysCondition==null){
			querySysCondition=new QuerySysCondition();
		}
		return querySysCondition;
	}
	public void setQuerySysCondition(QuerySysCondition querySysCondition) {
		this.querySysCondition = querySysCondition;
	}
	public ISubject getSubjectService() {
		return subjectService;
	}
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public Sys getSys() {
		return sys;
	}
	public void setSys(Sys sys) {
		this.sys = sys;
	}
	public int getKsId() {
		return ksId;
	}
	public void setKsId(int ksId) {
		this.ksId = ksId;
	}
	public ISysNode getSysNodeService() {
		return sysNodeService;
	}
	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}
	public int getKsnId() {
		return ksnId;
	}
	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public List<SysNode> getSysTree() {
		return sysTree;
	}
	public void setSysTree(List<SysNode> sysTree) {
		this.sysTree = sysTree;
	}
	public List<SysNodeDTO> getSysNodeList() {
		return sysNodeList;
	}
	public void setSysNodeList(List<SysNodeDTO> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}
	public List<Sys> getSysinfolist() {
		return sysinfolist;
	}
	public void setSysinfolist(List<Sys> sysinfolist) {
		this.sysinfolist = sysinfolist;
	}
	public IPreItem getPreItemService() {
		return preItemService;
	}
	public void setPreItemService(IPreItem preItemService) {
		this.preItemService = preItemService;
	}
	public List<PreItem> getPreItemlist() {
		return preItemlist;
	}
	public void setPreItemlist(List<PreItem> preItemlist) {
		this.preItemlist = preItemlist;
	}
	public QueryPreItemCondition getQueryPreItemCondition() {
		if(queryPreItemCondition==null){
			queryPreItemCondition=new QueryPreItemCondition();
		}
		return queryPreItemCondition;
	}
	public void setQueryPreItemCondition(QueryPreItemCondition queryPreItemCondition) {
		this.queryPreItemCondition = queryPreItemCondition;
	}
	public PreItem getPreItem() {
		return preItem;
	}
	public void setPreItem(PreItem preItem) {
		this.preItem = preItem;
	}
	public int getPreId() {
		return preId;
	}
	public void setPreId(int preId) {
		this.preId = preId;
	}
	public IPreNode getPreNodeService() {
		return preNodeService;
	}
	public void setPreNodeService(IPreNode preNodeService) {
		this.preNodeService = preNodeService;
	}
	public QueryPreNodeCondition getQueryPreNodeCondition() {
		if(queryPreNodeCondition==null){
			queryPreNodeCondition=new QueryPreNodeCondition();
		}
		return queryPreNodeCondition;
	}
	public void setQueryPreNodeCondition(QueryPreNodeCondition queryPreNodeCondition) {
		this.queryPreNodeCondition = queryPreNodeCondition;
	}
	public List<PreNode> getPreNodelist() {
		return preNodelist;
	}
	public void setPreNodelist(List<PreNode> preNodelist) {
		this.preNodelist = preNodelist;
	}
	public NodePreMid getNodePreMid() {
		return nodePreMid;
	}
	public void setNodePreMid(NodePreMid nodePreMid) {
		this.nodePreMid = nodePreMid;
	}
	public INodePreMid getNodePreMidService() {
		return nodePreMidService;
	}
	public void setNodePreMidService(INodePreMid nodePreMidService) {
		this.nodePreMidService = nodePreMidService;
	}
	public PreNode getPreNode() {
		return preNode;
	}
	public void setPreNode(PreNode preNode) {
		this.preNode = preNode;
	}
	public NodePreMid getNPreMid() {
		return nPreMid;
	}
	public void setNPreMid(NodePreMid preMid) {
		nPreMid = preMid;
	}
	public SysNode getSNode() {
		return sNode;
	}
	public void setSNode(SysNode node) {
		sNode = node;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public QuerySysNodeCondition getQuerySysNodeCondition() {
		if(querySysNodeCondition==null){
			querySysNodeCondition=new QuerySysNodeCondition();
		}
		return querySysNodeCondition;
	}
	public void setQuerySysNodeCondition(QuerySysNodeCondition querySysNodeCondition) {
		this.querySysNodeCondition = querySysNodeCondition;
	}
	public List<SysNode> getSysNodeList2() {
		return sysNodeList2;
	}
	public void setSysNodeList2(List<SysNode> sysNodeList2) {
		this.sysNodeList2 = sysNodeList2;
	}
}
