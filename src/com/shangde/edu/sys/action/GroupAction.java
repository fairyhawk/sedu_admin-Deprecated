package com.shangde.edu.sys.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.sys.condition.QueryGroupCondition;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.dto.KeyValueDTO;
import com.shangde.edu.sys.service.IGroup;

/**
 * 用户组管理action类
 * @author guoqiang.liu
 * @version 1.0
 */
public class GroupAction extends CommonAction{
	
	private static final Logger logger = Logger.getLogger(GroupAction.class);
	
	private static final long serialVersionUID = 7206401215912138624L;
	private int groupId;
	private Group group;
	private IGroup groupService;
	private List<Group> groupList = new ArrayList<Group>();
	private QueryGroupCondition queryGroupCondition;
	private String groupIds;

	public String getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}
	public QueryGroupCondition getQueryGroupCondition() {
		return queryGroupCondition;
	}
	public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
		this.queryGroupCondition = queryGroupCondition;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public IGroup getGroupService() {
		return groupService;
	}
	public void setGroupService(IGroup groupService) {
		this.groupService = groupService;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	/**
	 * 组的树形结构列表
	 * @return String
	 * @throws Exception
	 */
	public String GroupTreeList(){
		try{
			groupList = this.groupService.getGroupList(queryGroupCondition);
		}catch(Exception e){
			logger.error("GroupAction.GroupTreeList", e);
			return ERROR;
		}
		return "GroupTreeList";
	}

	/**
	 * 获取子组列表
	 * @return
	 * @throws Exception
	 * @author chenshuai
	 */
	public String getChildGroupById() throws  IOException{
		try{
			List<Group> childGroupList = this.groupService.getChildGroupById("" + groupId);
			
			List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
			
			KeyValueDTO keyvalue = null;

			for(int i=0; i < childGroupList.size(); i++) {
				keyvalue = new KeyValueDTO();
				Group grouptemp = childGroupList.get(i); 
				
				keyvalue.setKey(grouptemp.getGroupId());
				keyvalue.setValue(grouptemp.getGroupName());
				myList.add(keyvalue);
			}
			
			this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
		}catch(Exception e){
			logger.error("GroupAction.getChildGroupById", e);
			return ERROR;
		}
		return "getChildGroupById";
	}

	/**
	 * 发送GROUP的子集合
	 * @param content
	 * @throws IOException
	 * @author chenshuai
	 */
	private void sendMessage(String content) throws  IOException{
			this.getServletResponse().setCharacterEncoding("UTF-8");      
			this.getServletResponse().getWriter().write(content);  
	}

	public String techIndex(){
		return "techIndex";
	}
	public String techLeftframe(){
		try{
			groupList = this.groupService.getGroupListForTechTree(queryGroupCondition);
		}catch(Exception e){
			logger.error("GroupAction.techLeftframe", e);
			return ERROR;
		}
		return "techLeftframe";
	}
	public String techRightframe() {
		return "techRightframe";
	}
	public String switchframe() {
		return "switchframe";
	}
	public String stuIndex() {
		return "stuIndex";
	}
	public String stuLeftframe() {
		groupList = this.groupService.getGroupListForTechTree(queryGroupCondition);
		return "stuLeftframe";
	}
	/**
	 * 添加组
	 * @return
	 * @throws Exception
	 */
	public String toAddGroup() {
		//一级组
		try{
			groupList = this.groupService.getFirGroup(queryGroupCondition);
		}catch(Exception e){
			logger.error("GroupAction.toAddGroup", e);
			return ERROR;
		}
		return "toAddGroup";
	}
	public String addGroup() {
		try{
			this.groupService.addGroup(group);
		}catch(Exception e){
			logger.error("GroupAction.addGroup", e);
			return ERROR;
		}
		return "changeSuccess";
	}
	/**
	 * 删除组(删除组下的所有组元素和组下的所有用户)
	 * @return
	 * @throws Exception
	 */
	public String 	toDeleteGroup(){
		try{
			groupList = this.groupService.getGroupListForTechTree(queryGroupCondition);
		}catch(Exception e){
			logger.error("GroupAction.toDeleteGroup", e);
			return ERROR;
		}
		return "toDeleteGroup";
	}
	public String deleteGroup(){
		try{
			this.groupService.deleteGroups(groupIds);
		}catch(Exception e){
			logger.error("GroupAction.deleteGroup", e);
			return ERROR;
		}
		return "deleteGroup";
	}
	/**
	 * 往组中添加用户
	 * @return
	 * @throws Exception
	 */
	public String toUserRefToGroup(){
		return "toUserRefToGroup";
	}
	public String userRefToGroup(){
		return "userRefToGroup";
	}
	/**
	 * 批量导入用户
	 * @return
	 * @throws Exception
	 */ String toManyUserRefToGroup(){
		 return "toManyUserRefToGroup";
	 }
	 public String manyUserRefToGroup(){
		 return "manyUserRefToGroup";
	 }
	 /**
	  * 批量导入用户
	  * @return
	  * @throws Exception
	  */
	 public String listUserFromGroup(){
		 return "listUserFromGroup";
	 }
	 /**
	  * 从组中删除用户
	  * @return
	  * @throws Exception
	  */
	 public String deleteUserFromGroup(){
		 return "deleteUserFromGroup";
	 }
	 public int getGroupId() {
		 return groupId;
	 }
	 public void setGroupId(int groupId) {
		 this.groupId = groupId;
	 }
}
