package com.shangde.edu.kno.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kno.domain.Resolve;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.IResolve;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.sys.domain.User;

public class ResolveAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ResolveAction.class);
	
	private int ksnId;
	private int resId;
	private IResolve resolveService;
	private ISysNode sysNodeService;
	private ISys sysService;
	private Resolve resolve;
	private Resolve res;
	private Sys sys;
	private SysNode sysNode;
	private List <Resolve> resList=new ArrayList<Resolve>();
	private List <SysNode> sysNodeList=new ArrayList <SysNode>();
	private List <SysNode> sysNodeList1=new ArrayList <SysNode>();
	
	/**
	 * 转到解析列表
	 */
	public String resolveList(){
		try {
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList.add(sysNodeList1.get(i-1));
			}
			resList=resolveService.getResolveListByKsnId(ksnId);
			return "resolveList";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("resolveAction.resolveList", e);
			return ERROR;
		}
	}
	/**
	 * 转到解析列表
	 */
	public String resolveListRes(){
		try {
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList.add(sysNodeList1.get(i-1));
			}
			resList=resolveService.getResolveListByKsnId(ksnId);
			return "resolveListRes";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("resolveAction.resolveListRes", e);
			return ERROR;
		}
	}
	/**
	 * 转到添加解析页面
	 * @return
	 */
	public String toResolveAdd(){
		try {
			sysNode=sysNodeService.getSysNodeById(ksnId);
			sys=sysService.getSysById(sysNode.getKsId());
			return "toResolveAdd";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("resolveAction.toResolveAdd", e);
			return ERROR;
		}
	}
	/**
	 * 添加解析
	 * @return
	 */
	public String resolveAdd(){
		try {
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			resolve.setResPerson(users.getLoginName());
			resolve.setCreateTime(new Date());
			resolve.setKsnId(ksnId);
			sysNode=sysNodeService.getSysNodeById(ksnId);
			resolve.setNodeId(sysNode.getNodeId());
			if(resolve.getIsFirst()==1){
				Resolve res=resolveService.getResolveFirstByKsnId(ksnId);
				if(res!=null){
					res.setIsFirst(0);
					resolveService.updateResolve(res);
				}
			}
			resolveService.addResolve(resolve);
			resList=resolveService.getResolveListByKsnId(ksnId);
			return "resolveList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("resolveAction.resolveAdd", e);
			return ERROR;
		}
	}
	/**
	 * 转到解析更新页面
	 * @return
	 */
	public String toResolveUpdate(){
		try{
			sysNode=sysNodeService.getSysNodeById(ksnId);
			sys=sysService.getSysById(sysNode.getKsId());
			resolve=resolveService.getResolveById(resId);
		}catch(Exception e){
			logger.error("resolveAction.toResolveUpdate", e);
			return ERROR;
		}
		return "toResolveUpdate";
	}
	/**
	 * 更新解析
	 * @return
	 */
	public String resolveUpdate(){
		try {
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			res=resolveService.getResolveById(resId);
			res.setResPerson(users.getLoginName());
			res.setLastEditTime(new Date());
			res.setResContent(resolve.getResContent());
			res.setIsFirst(resolve.getIsFirst());
			res.setResKeyword(resolve.getResKeyword());
			if(res.getIsFirst()==1){
				Resolve reso=resolveService.getResolveFirstByKsnId(res.getKsnId());
				if(reso!=null){
					reso.setIsFirst(0);
					resolveService.updateResolve(reso);
				}
			}
			resolveService.updateResolve(res);
			resList=resolveService.getResolveListByKsnId(ksnId);
			return "resolveList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("resolveAction.resolveUpdate", e);
			return ERROR;
		}
	}
	/**
	 * 删除解析
	 * @return
	 */
	public String delResolve(){
		try{
			resolveService.delResolveById(resId);
			this.setResult(new Result(true,"","",null));
		}catch(Exception e){
			logger.error("resolveAction.delResolve", e);
			this.setResult(new Result(false,"","",null));
		}
		return "json";
	}
	

	public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public IResolve getResolveService() {
		return resolveService;
	}

	public void setResolveService(IResolve resolveService) {
		this.resolveService = resolveService;
	}

	public List<Resolve> getResList() {
		return resList;
	}

	public void setResList(List<Resolve> resList) {
		this.resList = resList;
	}

	public ISysNode getSysNodeService() {
		return sysNodeService;
	}

	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public ISys getSysService() {
		return sysService;
	}

	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}

	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public Resolve getResolve() {
		return resolve;
	}

	public void setResolve(Resolve resolve) {
		this.resolve = resolve;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}
	public Resolve getRes() {
		return res;
	}
	public void setRes(Resolve res) {
		this.res = res;
	}
	public List<SysNode> getSysNodeList() {
		return sysNodeList;
	}
	public void setSysNodeList(List<SysNode> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}
}
