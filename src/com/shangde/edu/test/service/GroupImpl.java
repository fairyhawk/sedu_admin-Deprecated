package com.shangde.edu.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.edu.test.domain.Group;

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
		return simpleDao.createEntity("testGroup_NS.createGroup",group);
	}

	public void delGroupById(int groupId){
		simpleDao.deleteEntity("testGroup_NS.deleteGroupById",groupId);
	}

	public void updateGroup(Group group) {
		simpleDao.updateEntity("testGroup_NS.updateGroup",group);
	}

	public Group getGroupById(int groupId) {
		return simpleDao.getEntity("testGroup_NS.getGroupById",groupId);
	}

	
	

	
	

	public List<Group> getChildGroupById(String groupId) {
		return simpleDao.getForList("testGroup_NS.getChildGroupById",new Integer(groupId));
	}

	public void deleteGroups(String groupIds) {
		
	}

	public List<Group> getFirGroupForDwr() {
		// TODO Auto-generated method stub
		return null;
	}
}
