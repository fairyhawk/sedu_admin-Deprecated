package com.shangde.edu.sys.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.shangde.common.util.MD5;
import com.shangde.common.action.CommonAction;
import com.shangde.edu.cms.condition.QueryColumnsCondition;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.service.ColumnsImpl;
import com.shangde.edu.cms.service.IColumns;
import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.condition.QueryGroupCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.ClassT;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.domain.UserGradeSubjectRole;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.IGroup;
import com.shangde.edu.sys.service.IRole;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.service.IUser;
import com.shangde.edu.sys.service.IUserGradeSubjectRole;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 系统用户管理action类
 * @author guoqiang.liu
 * @version 1.0
 */
public class SysUserAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(SysUserAction.class);
	
	private static final long serialVersionUID = -2974696036235418230L;
	private User user;
	private List<User> userList = new ArrayList<User>();
	private IUser userService;
	private QueryUserCondition queryUserCondition;
	private IGroup groupService;
	private List<Group> groupList = new ArrayList<Group>();
	private QueryGroupCondition queryGroupCondition=new QueryGroupCondition();
	private String groupId;
	private IColumns columnsService ;
	private QueryColumnsCondition queryColumnsCondition;
	private List<Columns> columnList = new ArrayList<Columns>();
	private String searchKey;// 条件搜索
	private String searchType;
	private List<Role> roleList = new ArrayList<Role>();
	private List<Role> owerRoleList = new ArrayList<Role>();
	private IRole roleService;
	private String userType;
	private IGrade gradeService;
	private List<Grade> gradeList;
	private ISubject subjectService;
	private List<Subject> subjectList;
	private List<ClassT> classTList;
	private List<ClassT> cuClassTList;
	private List<UserGradeSubjectRole> ugsrList;
	private List<Function> functionList;
	private IUserGradeSubjectRole userGradeSubjectRoleService;
	private Group fg;// 一级组
	private Group sg;// 二级组
	private Group tg;// 三级组
	private String changePwd;
	private int usersId;

	public Group getTg() {
		return tg;
	}

	public void setTg(Group tg) {
		this.tg = tg;
	}

	public Group getFg() {
		return fg;
	}

	public void setFg(Group fg) {
		this.fg = fg;
	}

	public Group getSg() {
		return sg;
	}

	public void setSg(Group sg) {
		this.sg = sg;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Role> getOwerRoleList() {
		return owerRoleList;
	}

	public void setOwerRoleList(List<Role> owerRoleList) {
		this.owerRoleList = owerRoleList;
	}

	public IRole getRoleService() {
		return roleService;
	}

	public void setRoleService(IRole roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public IGroup getGroupService() {
		return groupService;
	}

	public void setGroupService(IGroup groupService) {
		this.groupService = groupService;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public QueryGroupCondition getQueryGroupCondition() {
		if (queryGroupCondition == null) {
			queryGroupCondition = new QueryGroupCondition();
		}
		return queryGroupCondition;
	}

	public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
		this.queryGroupCondition = queryGroupCondition;
	}

	public QueryUserCondition getQueryUserCondition() {
		if (queryUserCondition == null) {
			queryUserCondition = new QueryUserCondition();
			queryUserCondition.setUserType(-1);
		}
		return queryUserCondition;
	}

	public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
		this.queryUserCondition = queryUserCondition;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public IGrade getGradeService() {
		return gradeService;
	}

	public void setGradeService(IGrade gradeService) {
		this.gradeService = gradeService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
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

	public List<ClassT> getClassTList() {
		return classTList;
	}

	public void setClassTList(List<ClassT> classTList) {
		this.classTList = classTList;
	}


	public List<ClassT> getCuClassTList() {
		return cuClassTList;
	}

	public void setCuClassTList(List<ClassT> cuClassTList) {
		this.cuClassTList = cuClassTList;
	}

	public List<UserGradeSubjectRole> getUgsrList() {
		return ugsrList;
	}

	public void setUgsrList(List<UserGradeSubjectRole> ugsrList) {
		this.ugsrList = ugsrList;
	}

	public IUserGradeSubjectRole getUserGradeSubjectRoleService() {
		return userGradeSubjectRoleService;
	}

	public void setUserGradeSubjectRoleService(
			IUserGradeSubjectRole userGradeSubjectRoleService) {
		this.userGradeSubjectRoleService = userGradeSubjectRoleService;
	}

	/**
	 * 新增用户
	 * @return String
	 * @throws Exception
	 */
	public String toAddUser() {
		// 一级组
		try{
			groupList = this.groupService.getFirGroup(queryGroupCondition);
		}catch(Exception e){
			logger.error("SysUserAction.toAddUser", e);
			return ERROR;
		}
		return "toAddUser";
	}

	public String addUser() {
		try{
			user.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
			this.userService.addUser(user);
		}catch(Exception e){
			logger.error("SysUserAction.addUser", e);
			return ERROR;
		}
		return "relistAllUser";
	}
	/**
	 * 进入内容管理权限树
	 * @return String
	 */
	public String toSetUserContentRole(){
		try{
			//获取对应用户的user对象
			user=userService.getUserById(user.getUserId());
			//获取column列表
			columnList=columnsService.getColumnsList(queryColumnsCondition);
		}catch(Exception e){
			logger.error("SysUserAction.toSetUserContentRole", e);
			return ERROR;
		}
		return "toSetUserContentRole";
	}
	/**
	 * 更新用户内容管理权限
	 */
	public void updataUserRole(){
		user.setUserContentRole(user.getUserContentRole());
		userService.updateContentRole(user);
	}
	
	public IColumns getColumnsService() {
		return columnsService;
	}

	public void setColumnsService(IColumns columnsService) {
		this.columnsService = columnsService;
	}

	public QueryColumnsCondition getQueryColumnsCondition() {
		return queryColumnsCondition;
	}

	public void setQueryColumnsCondition(QueryColumnsCondition queryColumnsCondition) {
		this.queryColumnsCondition = queryColumnsCondition;
	}
	/**
	 * 三级联动
	 * @return String
	 * @throws Exception
	 */

	public String getAllGroup() throws IOException{
		try{
			List<Group> childGroupList = this.groupService.getChildGroupById("" + groupId);
			JSONObject obj = null;
			JSONArray jarray = new JSONArray();
			Map<String, String> map = null;

			for(int i=0; i < childGroupList.size(); i++) {
				map = new HashMap<String, String>(); 
				Group grouptemp = childGroupList.get(i); 
				map.put("id", "" + grouptemp.getGroupId()); 
				map.put("val", grouptemp.getGroupName()); 
				obj = JSONObject.fromObject(map);
				jarray.add(obj);
			}
			this.sendMessage(jarray.toString());
		}catch(Exception e){
			logger.error("SysUserAction.getAllGroup", e);
			return ERROR;
		}
		return null;


	}
	private void sendMessage(String content) throws IOException{  
		try{
			this.getServletResponse().setCharacterEncoding("UTF-8");      
			this.getServletResponse().getWriter().write(content);   
		}catch(Exception e){
			logger.error("SysUserAction.sendMessage", e);
		}
	} 

	/**
	 * 修改用户信息
	 * @return String
	 * @throws Exception
	 */
	public String toEditUser() {
		// 一级组
		try{
			groupList = this.groupService.getGroupList(queryGroupCondition);
			gradeList = this.gradeService.getGradeList(new QueryGradeCondition());
			subjectList = this.subjectService.getSubjectList(new QuerySubjectCondition());
			user = this.userService.getUserById(user.getUserId());
			//cuClassTList = this.classTService.getClassTListByUserId(user.getUserId());
			ugsrList = userGradeSubjectRoleService.getUGSRByUserId(user.getUserId());
			//User iUser = this.getLoginedUser();
			//classTList = this.classTService.getClassTList(new QueryClassTCondition());
			if (user.getGroup().getLevel() == 2) {
				fg = groupService.getGroupById(user.getGroup().getParentGroupId());
				sg = user.getGroup();
				tg = new Group();
				tg.setGroupId(-1);
				tg.setGroupName("请选择");
			} else if (user.getGroup().getLevel() == 3) {
				fg = groupService.getGroupById(groupService.getGroupById(
						user.getGroup().getParentGroupId()).getParentGroupId());
				//sg = groupService.getGroupById(fg.getGroupId());
				sg = groupService.getGroupById(user.getGroup().getParentGroupId());
				tg = user.getGroup();
			} else {
				fg = new Group();
				fg.setGroupId(-1);
				fg.setGroupName("请选择");
				sg = new Group();
				sg.setGroupId(-1);
				sg.setGroupName("请选择");
				tg = new Group();
				tg.setGroupId(-1);
				tg.setGroupName("请选择");
			}
		}catch(Exception e){
			logger.error("SysUserAction.toEditUser", e);
			return ERROR;
		}
		return "toEditUser";
	}

	public String editUser(){
		try{
			userService.updateUser(user);
		}catch(Exception e){
			logger.error("SysUserAction.editUser", e);
			return ERROR;
		}
		return "changeSuccess";
	}


	/**
	 * 用户列表
	 * @return String
	 * @throws Exception
	 */
	public String listAllUser() {
		try{
			setPage(this.userService.getAllUserList(getQueryUserCondition()));
			setPageUrlParms();
			usersId=this.getLoginedUser().getUserId();
			
			
		}catch(Exception e){
			logger.error("SysUserAction.listAllUser", e);
			return ERROR;
		}
		return "listAllUser";
	}

	/**
	 * 通过groupId查询组用户
	 * @return String
	 * @throws Exception
	 */
	public String listUserByGroupId() {
		try{
			getQueryUserCondition().setUserType(new Integer(userType));
			getQueryUserCondition().setGroupId(new Integer(groupId));
			setPage(this.userService.getUserListByGroupId(getQueryUserCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("SysUserAction.listUserByGroupId", e);
			return ERROR;
		}
		return "listUserByGroupId";
	}

	/**
	 * 通过关键字查询组用户
	 * @return String
	 * @throws Exception
	 */
	public String listUserByKey(){
		try{
			searchKey = URLDecoder.decode(searchKey, "UTF-8");
			getQueryUserCondition().setSearchKey(searchKey);
			setPage(this.userService.getUserListByKey(getQueryUserCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("SysUserAction.listUserByKey", e);
			return ERROR;
		}
		return "listUserByGroupId";
	}

	/**
	 * 冻结
	 * @return String
	 * @throws Exception
	 */
	public String freezeUser() {
		try{
			user = this.userService.getUserById(user.getUserId());
			if (user.getStatus() == User.USER_DEFAULT_STATUS) {
				user.setStatus(User.USER_FREEZE_STATUS);
			} else {
				user.setStatus(User.USER_DEFAULT_STATUS);
			}
			this.userService.updateUser(user);
		}catch(Exception e){
			logger.error("SysUserAction.freezeUser", e);
			return ERROR;
		}
		return "listAllUser";
	}

	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String deleteUser(){
		try{
			user = this.userService.getUserById(user.getUserId());
			user.setStatus(User.USER_DELETE_STATUS);
			this.userService.updateUser(user);
		}catch(Exception e){
			logger.error("SysUserAction.deleteUser", e);
			return ERROR;
		}
		return "listAllUser";
	}

	/**
	 * 用户密码重置（初始密码或随机密码，并email通知用户）
	 * @return String
	 * @throws Exception
	 */
	public String resetPassword() {
		return "resetPassword";
	}

	public String toEditUserGradeSubjectRoleSec() throws Exception {
		try{
			// 1通过user得到roleList
			user = this.userService.getUserById(user.getUserId());
			// 2通过roleList得到对应的subject和grade
			user = this.userService.getUserDtoByUserId(user.getUserId());
			
			//递归格式化角色权限列表
			/*for (Role role : user.getRoleList()) {
				functionList = new ArrayList<Function>();
				for (Function function : role.getFunctionList()) {
					if(function.getParentFunctionId()==1){
						functionList.add(function);
						this.getFun(role.getFunctionList(), function.getFunctionId());
					}
				}
				role.setFunctionList(functionList);
			}*/

		}catch(Exception e){
			logger.error("SysUserAction.toEditUserGradeSubjectRoleSec", e);
			return ERROR;
		}
		return "toEditUserGradeSubjectRoleSec";
	}

	public String toViewUserGradeSubjectRoleSec()  {
		try{
			// 1通过user得到roleList
			user = this.userService.getUserById(user.getUserId());
			// 2通过roleList得到对应的subject和grade
			user = this.userService.getUserDtoByUserId(user.getUserId());
		}catch(Exception e){
			logger.error("SysUserAction.toViewUserGradeSubjectRoleSec", e);
			return ERROR;
		}
		return "toViewUserGradeSubjectRoleSec";
	}

	/**
	 * 修改用户-入学年份-专业-角色-权限范围的对应关系
	 * @return String
	 * @throws Exception
	 */
	public String editUserGradeSubjectRoleSec() throws Exception {
		return "editUserGradeSubjectRoleSec";
	}

	public String changePwdSubmit() {
		try{
			User user = getLoginedUser();
			if (user.getLoginPwd().equals(MD5.getMD5(this.user.getLoginPwd()))) {
				user.setLoginPwd(MD5.getMD5(changePwd));
				userService.updateUserPwd(user);
				addActionError(getText("msg_changepwd_success"));
			} else {
				addActionError(getText("err_changepwd_verify"));
			}
		}catch(Exception e){
			logger.error("SysUserAction.changePwdSubmit", e);
			return ERROR;
		}
		return "changePwd";
	}
	public String userManagerSubmit() {
		try{
			String userName = this.getServletRequest().getParameter("userName");
			String pname = this.getServletRequest().getParameter("pname");
			String email = this.getServletRequest().getParameter("email");
			String tel = this.getServletRequest().getParameter("tel");
			String address = this.getServletRequest().getParameter("address");
			String zip = this.getServletRequest().getParameter("zip");
			User user = getLoginedUser();
			user.setUserName(userName);
			user.setEmail(email);
			user.setTel(tel);
			user.setAddress(address);
			user.setZip(zip);
			userService.updateUser(user);
			this.getServletRequest().getSession().setAttribute(
					CommonAction.SYS_USER_SESSION_NAME, user);
		}catch(Exception e){
			logger.error("SysUserAction.userManagerSubmit", e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 检查用户名是否可用
	 * @return
	 */
	public String checkLoginName() {
		try {
			String loginName = this.getQueryUserCondition().getSearchKey();
			if(loginName != null) {
				this.getQueryUserCondition().setSearchKey(loginName.trim());
				if(this.userService.checkLoginName(this.getQueryUserCondition())) {
					this.sendMessage("true");
					return null;
				}
			}
			this.sendMessage("false");
		} catch(Exception e) {
			logger.error("SysUserAction.checkLoginName", e);
			return ERROR;
		}
		return null;
	}

	/**
	 * 初始化修改密码
	 * @return
	 */
	public String toUpdatePwd() {
		try{
			user = this.userService.getUserById(user.getUserId());
		}catch(Exception e){
			logger.error("SysUserAction.toUpdatePwd", e);
			return ERROR;
		}
		return "toUpdatePwd";
	}

	/**
	 * 修改密码
	 * @return
	 */
	public String updatePwd() {
		try{
			User u = userService.getUserById(user.getUserId());
			u.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
			userService.updateUser(u);
		}catch(Exception e){
			logger.error("SysUserAction.updatePwd", e);
			return ERROR;
		}
		return "changeSuccess";
	}
	

	//递归角色权限菜单
	public void getFun(List<Function> lf, int functionId ){
		for (Function function : lf) {
			if(function.getParentFunctionId() == functionId){
				functionList.add(function);
				this.getFun(lf, function.getFunctionId());
			}
		}
	}
	
	public String userManager() {
		user = this.getLoginedUser();
		return "userManager";
	}

	public String changePwd() {
		return "changePwd";
	}

	public String getChangePwd() {
		return changePwd;
	}

	public void setChangePwd(String changePwd) {
		this.changePwd = changePwd;
	}

	public List<Columns> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Columns> columnList) {
		this.columnList = columnList;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
	
}
