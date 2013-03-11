package com.shangde.edu.kno.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.kno.condition.QueryPreItemCondition;
import com.shangde.edu.kno.condition.QueryPreNodeCondition;
import com.shangde.edu.kno.domain.PreItem;
import com.shangde.edu.kno.domain.PreNode;
import com.shangde.edu.kno.service.IPreItem;
import com.shangde.edu.kno.service.IPreNode;

/**
 * 处理预设项的ACTION
 * @author zhangjuqiang
 *
 */
public class PreItemAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3048494168336031397L;
	private IPreItem preItemService;
	private IPreNode preNodeService;
	private PreItem preItem=new PreItem();
	private PreNode preNode=new PreNode();
	private List<PreNode> preNodeList=new ArrayList<PreNode>();
	private List<PreItem> preItemList=new ArrayList<PreItem>();
	private String preNodeNames[];
	private String preNodeSorts[];
	private String preNodeIds[];
	private int preId;
	private int flag;
	private String preName;
	private QueryPreNodeCondition pnCondition=new QueryPreNodeCondition();
	private QueryPreItemCondition piCondition=new QueryPreItemCondition();

	public PreNode getPreNode() {
		return preNode;
	}

	public void setPreNode(PreNode preNode) {
		this.preNode = preNode;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setPreItem(PreItem preItem) {
		this.preItem = preItem;
	}

	public IPreNode getPreNodeService() {
		return preNodeService;
	}

	public void setPreNodeService(IPreNode preNodeService) {
		this.preNodeService = preNodeService;
	}

	public IPreItem getPreItemService() {
		return preItemService;
	}

	public void setPreItemService(IPreItem preItemService) {
		this.preItemService = preItemService;
	}
	/**
	 * 添加预设项操作
	 * @return
	 */
	public String addPreItem(){
		//添加预设项条目
		Date date=Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate=sdf.format(date);
		preItem.setCreateTime(sDate);
		preItem.setLastEditTime(sDate);
		preItem.setAuthor(getLoginedUser().getUserName());
		preItem.setStatus(1);
		int pid=preItemService.addPreItem(preItem);
		//以下添加预设项子结点，即选项描述
		generatePreNodeList(pid,1);
		preNodeService.addEntity(preNodeList);
		if(pid>0){
			return "rePreItemList";
		}
		return ERROR;
	}

	//生成预设项子结点List
	private void generatePreNodeList(int pid,int type) {
		servletRequest=ServletActionContext.getRequest();
		preNodeNames=servletRequest.getParameterValues("preNodeName");
		preNodeSorts=servletRequest.getParameterValues("preNodeSort");
		//1为添加、2为修改，修改才需要节点ID，添加自动生成不需要
		if(type==2)
			preNodeIds=servletRequest.getParameterValues("preNodeId");
		if(preNodeNames!=null&&preNodeNames.length>0){
			for(int i=0; i<preNodeNames.length;i++){
				preNode=new PreNode();
				preNode.setPreId(pid);
				preNode.setPreNodeName(preNodeNames[i]);
				preNode.setSortId(Integer.parseInt(preNodeSorts[i]));
				if(type==2)
					preNode.setPreNodeId(Integer.parseInt(preNodeIds[i]));
				preNodeList.add(preNode);
			}
		}
	}
	
	/**
	 * 删除预设项
	 * @return
	 */
	public String delPreItem(){
		//先删除子结点，再删除预设项
		pnCondition.setPreItemId(preItem.getPreId());
		List<Integer> pnids=preNodeService.getPreNodeIdsByPreItemId(pnCondition);
		preNodeService.delPreNodes(pnids);
		//删除预设项
		preItemService.delPreItemById(preItem.getPreId());
		return "rePreItemList";
	}
	/**
	 * 添加/修改时，先判断是否已存在预设项名，不能重名。
	 * @return
	 */
	public String addPreItemBack(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("pid", preId);
		map.put("pname", preName);
		int count=preItemService.getPreItemCountByName(map);
		boolean has=false;
		if(count>0)has=true;
		else has=false;
		this.setResult(new Result<Object>(has, null, null,null));
		return "json";
	}
	
	/**
	 * 修改预设项
	 * @return
	 */
	public String updatePreItem(){
		Date date=Calendar.getInstance().getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PreItem tempPreItem=null;
		//修改内容的操作
		if(flag==0){
			preItem.setLastEditTime(sdf.format(date));
			preItem.setAuthor(getLoginedUser().getUserName());
			//preItem.setStatus(1);
			preItemService.updatePreItem(preItem);
		}else{//冻结、恢复的操作
			tempPreItem=preItemService.getPreItemById(preItem.getPreId());
			if(tempPreItem!=null){
				//tempPreItem.setLastEditTime(sdf.format(date));
				//tempPreItem.setAuthor(getLoginedUser().getUserName());
				if(flag==1){
					tempPreItem.setStatus(0);
				}else if(flag==2){
					tempPreItem.setStatus(1);		
				}
			}			
			preItemService.updatePreItem(tempPreItem);
		}
		//如果flag为1或2，表示只是冻结或恢复。故不需操作子结点
		if(flag!=1&&flag!=2){
			generatePreNodeList(preItem.getPreId(),2);
			if(preNodeList.size()>0)
				preNodeService.updateEntity(preNodeList);
		}
		return "rePreItemList";
	}
	/**
	 * 查询预设项集合
	 */
	public String showPreItemList(){
		//preItemList=preItemService.getPreItemList(piCondition);
		piCondition.setPageSize(20);
		this.setPage(preItemService.getPreItemPageList(piCondition));
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(20);
		 }
		return "preItemList";
	}
	/**
	 * 显示预设项详细
	 * @return
	 */
	public String showPreItemDetail(){
		preItem=preItemService.getPreItemById(preItem.getPreId());
		pnCondition.setPreItemId(preItem.getPreId());
		pnCondition.setPageSize(20);
		this.setPage(preNodeService.getPreNodePageList(pnCondition)); 
		setPageUrlParms();
		if(getPage()!=null){
			   getPage().setPageSize(20);
		 }
		return "preItemDetail";
	}
	/**
	 * 显示预设项
	 * @return
	 */
	public String showPreItem(){
		preItem=preItemService.getPreItemById(preId);
		pnCondition.setPreItemId(preId);
		preNodeList=preNodeService.getPreNodeListByPreItemId(pnCondition);
		preItem.setPreNodeList(preNodeList);
		JSONObject jsPreItem=JSONObject.fromObject(preItem);
		//添加setresult
		this.setResult(new Result<Object>(true, jsPreItem.toString(), null,		
				null));
		return "json";
	}
	public List<PreNode> getPreNodeList() {
		return preNodeList;
	}

	public void setPreNodeList(List<PreNode> preNodeList) {
		this.preNodeList = preNodeList;
	}

	public String[] getPreNodeNames() {
		return preNodeNames;
	}

	public void setPreNodeNames(String[] preNodeNames) {
		this.preNodeNames = preNodeNames;
	}

	public QueryPreNodeCondition getPnCondition() {
		return pnCondition;
	}

	public void setPnCondition(QueryPreNodeCondition pnCondition) {
		this.pnCondition = pnCondition;
	}

	public QueryPreItemCondition getPiCondition() {
		return piCondition;
	}

	public void setPiCondition(QueryPreItemCondition piCondition) {
		this.piCondition = piCondition;
	}

	public PreItem getPreItem() {
		return preItem;
	}

	public void setPreItemList(List<PreItem> preItemList) {
		this.preItemList = preItemList;
	}

	public List<PreItem> getPreItemList() {
		return preItemList;
	}

	public String[] getPreNodeSorts() {
		return preNodeSorts;
	}

	public void setPreNodeSorts(String[] preNodeSorts) {
		this.preNodeSorts = preNodeSorts;
	}

	public int getPreId() {
		return preId;
	}

	public void setPreId(int preId) {
		this.preId = preId;
	}

	public String[] getPreNodeIds() {
		return preNodeIds;
	}

	public void setPreNodeIds(String[] preNodeIds) {
		this.preNodeIds = preNodeIds;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}
}
