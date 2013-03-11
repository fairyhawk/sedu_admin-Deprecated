package com.shangde.edu.sys.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.sys.condition.QueryGroupCondition;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.User;

/**
 * Group对象操作实现类
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class GroupImpl extends BaseService implements IGroup{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addGroup(Group group) {
		group.setCreateTime(new Date());
		group.setUpdateTime(new Date());
		return simpleDao.createEntity("Group_NS.createGroup",group);
	}

	public void delGroupById(int groupId){
		simpleDao.deleteEntity("Group_NS.deleteGroupById",groupId);
	}

	public void updateGroup(Group group) {
		simpleDao.updateEntity("Group_NS.updateGroup",group);
	}

	public Group getGroupById(int groupId) {
		return simpleDao.getEntity("Group_NS.getGroupById",groupId);
	}

	public List<Group> getGroupList(QueryGroupCondition queryGroupCondition) {
		return simpleDao.getForList("Group_NS.getGroupList",queryGroupCondition);
	}

	public List<Group> getGroupListForTechTree(
			QueryGroupCondition queryGroupCondition) {
		return simpleDao.getForList("Group_NS.getGroupListForTechTree",queryGroupCondition);
	}

	public List<Group> getFirGroup(QueryGroupCondition queryGroupCondition) {
		return simpleDao.getForList("Group_NS.getFirGroup",queryGroupCondition);
	}

	public List<Group> getFirGroupForDwr() {
		 List<Group> gg = simpleDao.getForList("Group_NS.getFirGroup",new QueryGroupCondition());
		 return gg;
	}

	public List<Group> getChildGroupById(String groupId) {
		return simpleDao.getForList("Group_NS.getChildGroupById",new Integer(groupId));
	}

	public void deleteGroups(String groupIds) {
		if(groupIds == null  ||  "".equals(groupIds.trim())) {
			return;
		}
		groupIds = groupIds.replaceAll(" ", "");
		String[] groupIdsArray = groupIds.split(",");
		for(int i = 0 ; i < groupIdsArray.length;i++){
			Group group = simpleDao.getEntity("Group_NS.getGroupById",new Integer(groupIdsArray[i]));
			group.setStatus(Group.GROUP_DELETE_STATUS);
			this.updateGroup(group);
			if(group.getLevel()!=1){
				List<User> userList = simpleDao.getForList("User_NS.getUserListByGroupId2",group.getGroupId());
				for(int k = 0 ; k < userList.size() ; k++){
					User user = userList.get(k);
					user.setStatus(User.USER_DELETE_STATUS);
					simpleDao.updateEntity("User_NS.updateUser",user);
				}
			}
		}
	}
}
