package com.shangde.edu.kno.action;

import java.util.HashMap;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;
import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.service.IPreNode;
/**
 * 预设项子节点处理ACTION
 * @author zhangjuqiang
 *
 */
public class PreNodeAction extends CommonAction {

	
	private static final long serialVersionUID = -6725247651587311769L;
	private IPreNode preNodeService;
	private int preNodeId;
	private int preId;
	private String preName;
	private PreNode preNode=new PreNode();
	private QueryPreNodeCondition pnCondition=new QueryPreNodeCondition();
	
	public IPreNode getPreNodeService() {
		return preNodeService;
	}
	public void setPreNodeService(IPreNode preNodeService) {
		this.preNodeService = preNodeService;
	}
	public PreNode getPreNode() {
		return preNode;
	}
	public void setPreNode(PreNode preNode) {
		this.preNode = preNode;
	}
	public QueryPreNodeCondition getPnCondition() {
		return pnCondition;
	}
	public void setPnCondition(QueryPreNodeCondition pnCondition) {
		this.pnCondition = pnCondition;
	}
	/**
	 * 添加结点
	 * @return
	 */
	public String addPreNode(){
		preNodeService.addPreNode(preNode);		
		return "rePreNodeList";
		
	}
	/**
	 * 更新结点
	 * @return
	 */
	public String updatePreNode(){
		preNodeService.updatePreNode(preNode);
		return "rePreNodeList";
	}
	/**
	 * 删除结点
	 * @return
	 */
	public String delPreNode(){
		preNodeService.delPreNodeById(preNode.getPreNodeId());
		return "rePreNodeList";
	}
	
	/**
	 * 添加/修改结点时，预先判断是否已存在
	 * @return
	 */
	public String addPreNodeBack(){
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("pid", preNodeId);
		map.put("name", preName);
		map.put("preId", preId);
		int count=preNodeService.getPreNodeCountByName(map);
		boolean has=false;
		if(count>0)has=true;
		else has=false;
		this.setResult(new Result<Object>(has,null,null,null));
		return "json";
	}
	//最多99层级
	public String getPreNodeCount(){
		pnCondition.setPreItemId(preId);
		int count=preNodeService.getProNodeCount(pnCondition);
		boolean yes=false;
		if(count<99)yes=true;
		else yes=false;
		this.setResult(new Result<Object>(yes,null,null,null));
		return "json";
	}
	public int getPreNodeId() {
		return preNodeId;
	}
	public void setPreNodeId(int preNodeId) {
		this.preNodeId = preNodeId;
	}
	public int getPreId() {
		return preId;
	}
	public void setPreId(int preId) {
		this.preId = preId;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
}
