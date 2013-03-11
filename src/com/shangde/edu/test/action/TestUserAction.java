package com.shangde.edu.test.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.MD5;
import com.shangde.edu.test.domain.Group;
import com.shangde.edu.test.domain.Subject;
import com.shangde.edu.test.domain.User;
import com.shangde.edu.test.service.IGroup;
import com.shangde.edu.test.service.ISubject;
import com.shangde.edu.test.service.IUser;


public class TestUserAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(TestUserAction.class);
	private int groupId;
	private Group group=null;
	private User user=null;
	private Subject subject=null;
	private IGroup testgroupService;
	private IUser testuserService; 
	private ISubject testsubjectService;
	private List<Group> groupList = new ArrayList<Group>();
	
	//新增信息
	public String addTestUser() {
		try{
			user=new User();
			group=new Group();
			subject=new Subject();
			user.setGroupId(38);
			user.setLoginPwd("123");
			user.setUserName("test");
			user.setUserTypeId(1);
			user.setLoginName("testUserName");
			group.setLevel(1);
			group.setGroupName("test");
			group.setStatus(1);
		//	subject.setStatus(0);
		//	subject.setSubjectName("test");
			this.testuserService.addTestUser(user);
			this.testgroupService.addGroup(group);
			this.testsubjectService.addSubject(subject);
		}catch(Exception e){
			logger.error("TestUserAction.addUser", e);
			return ERROR;
		}
		return "good";
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Subject getSubject() {
		return subject;
	}
	public IGroup getTestgroupService() {
		return testgroupService;
	}
	public void setTestgroupService(IGroup testgroupService) {
		this.testgroupService = testgroupService;
	}
	public IUser getTestuserService() {
		return testuserService;
	}
	public void setTestuserService(IUser testuserService) {
		this.testuserService = testuserService;
	}
	public ISubject getTestsubjectService() {
		return testsubjectService;
	}
	public void setTestsubjectService(ISubject testsubjectService) {
		this.testsubjectService = testsubjectService;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	
	
}
