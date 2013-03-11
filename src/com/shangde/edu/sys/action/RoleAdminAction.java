package com.shangde.edu.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.sys.condition.QueryFunctionCondition;
import com.shangde.edu.sys.condition.QueryGradeCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.IFunction;
import com.shangde.edu.sys.service.IGrade;
import com.shangde.edu.sys.service.IRole;
import com.shangde.edu.sys.service.ISubject;

/**
 * 角色管理action类
 * @author guoqiang.liu
 * @version 1.0
 */
public class RoleAdminAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(RoleAdminAction.class);
	
	private static final long serialVersionUID = 858277001097426212L;
	private IRole roleService;
	private IFunction functionService;
	private ISubject subjectService;
	private IGrade gradeService;
	private List<Role> roleList;
	private List<Function> firstLevelFunctionList;
	private List<Function> functionList = new ArrayList<Function>();
	private Set<Integer> selectedFunctionIds;
	private Role role;
	private Role addRole;
	private String userId;
	private String roleId;
	private List<Subject> subjectList = new ArrayList<Subject>();
	private String gradeIds;
	private int usersId;

	/**
	 * 删除角色
	 * @return String
	 * @throws Exception
	 */
	public String delRole(){
		try{
			roleService.delRoleById(role.getRoleId());
			role = null;
		}catch(Exception e){
			logger.error("RoleAdminAction.delRole", e);
			return ERROR;
		}
		return roleList();
	}

	/**
	 * 添加角色
	 * @return String
	 * @throws Exception
	 */
	public String addRole(){
		try{
			addRole.setCreateTime(new Date(System.currentTimeMillis()));
			addRole.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);
			addRole.setRoleTypeId(Role.ROLE_TYPE_DEFAULT);
			addRole.setStatus(Role.ROLE_STATUS_DEFAULT);
			addRole.setRoleId(roleService.addRole(addRole));
			role = addRole;
		}catch(Exception e){
			logger.error("RoleAdminAction.addRole", e);
			return ERROR;
		}
		return roleList();
	}



	/**
	 * 用户的角色列表以供编辑
	 * @return String
	 * @throws Exception
	 */
	public String roleList(){
		try{
			this.roleList = roleService.getScopeRoleList();
			this.firstLevelFunctionList = functionService.getUsableFunctionList();

			if(role == null){//如果没有接收到role.roleId的参数，则取role列表第一个
				role = roleList.get(0);
			} else{//否则根据id取出role对象
				role = roleService.getRoleById(role.getRoleId());
			}

			if(selectedFunctionIds == null)
				selectedFunctionIds = new HashSet<Integer>();
			for(Function function:role.getFunctionList()){
				selectedFunctionIds.add(function.getFunctionId());
			}
		}catch(Exception e){
			logger.error("RoleAdminAction.roleList", e);
			return ERROR;
		}
		return "roleList";
	}

	/**
	 * 编辑某用户角色下对应的专业，入学年份
	 * @return String
	 * @throws Exception
	 */
	public String toEditRole() {
		//通过userId和roleId
		try{
			role = this.roleService.getRoleInfo(new Integer(userId),new Integer(roleId));
			List<Subject> sjtList = this.subjectService.getSubjectList(new QuerySubjectCondition());
			StringBuffer tempGradeIds = new StringBuffer();
			for(Subject s1:sjtList){
				List<Grade> gradeList = this.gradeService.getGradeList(new  QueryGradeCondition());
				s1.setGradeList(gradeList);
				subjectList.add(s1);
			}
			for(Subject s2:role.getSubjectList()){
				for(Grade g1:s2.getGradeList()){
					tempGradeIds.append(s2.getSubjectId()+":"+g1.getGradeId()+",");
				}
			}
			tempGradeIds.deleteCharAt(tempGradeIds.length()-1);
			gradeIds = tempGradeIds.toString();
			
			//递归格式化角色权限列表
			for (Function function : role.getFunctionList()) {
				if(function.getParentFunctionId()==1){
					functionList.add(function);
					this.getFun(role.getFunctionList(), function.getFunctionId());
				}
			}
			role.setFunctionList(functionList);
			
		}catch(Exception e){
			logger.error("RoleAdminAction.toEditRole", e);
			return ERROR;
		}
		return "toEditRole";
	}

	/**
	 * 编辑某用户角色下对应的专业，入学年份
	 * @return String
	 * @throws Exception
	 */
	public String editRole() {
		try{
			this.roleService.updateRefRole(userId,roleId,gradeIds);
		}catch(Exception e){
			logger.error("RoleAdminAction.editRole", e);
			return ERROR;
		}
		return "toEditRoleRef";
	}
	/**
	 * 添加新角色（用户关联）
	 * @return String
	 * @throws Exception
	 */
	public String  toAddRoleRef(){
		try{
			roleList = this.roleService.getScopeRoleList();
//			subjectList = this.subjectService.getSubjectList(new QuerySubjectCondition());
//			for(Subject bb:subjectList){
//				List<Grade> gradeList = this.gradeService.getGradeList(new QueryGradeCondition());
//				bb.setGradeList(gradeList);
//			}
			userId = this.getUserId();
			usersId=this.getLoginedUser().getUserId();
			
		}catch(Exception e){
			logger.error("RoleAdminAction.toAddRoleRef", e);
			return ERROR;
		}
		return "toAddRoleRef";
	}
	public String  addRoleRef(){
		try{
			this.roleService.addRoleRef(userId,roleId,"");
		}catch(Exception e){
			logger.error("RoleAdminAction.addRoleRef", e);
			return ERROR;
		}
		return "toEditRoleRef";
	}
	/**
	 * 删除role(包括相关关联)
	 * @return String
	 * @throws Exception
	 */
	public String  deleteRoleRef(){
		try{
			this.roleService.delRoleRef(userId, roleId);
		}catch(Exception e){
			logger.error("RoleAdminAction.deleteRoleRef", e);
			return ERROR;
		}
		return "toEditRoleRef";
	}
	public String updateRoleFunction(){
		try{
			roleService.updateRoleFunction(role.getRoleId(),selectedFunctionIds);
		}catch(Exception e){
			logger.error("RoleAdminAction.updateRoleFunction", e);
			return ERROR;
		}
		return roleList();
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
	
	/*以上为业务实现方法，以下为bean方法*/

	public IRole getRoleService() {
		return roleService;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
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

	public List<Function> getFirstLevelFunctionList() {
		return firstLevelFunctionList;
	}

	public void setFirstLevelFunctionList(List<Function> firstLevelFunctionList) {
		this.firstLevelFunctionList = firstLevelFunctionList;
	}

	public IFunction getFunctionService() {
		return functionService;
	}

	public void setFunctionService(IFunction functionService) {
		this.functionService = functionService;
	}

	public Set<Integer> getSelectedFunctionIds() {
		return selectedFunctionIds;
	}

	public void setSelectedFunctionIds(Set<Integer> selectedFunctionIds) {
		this.selectedFunctionIds = selectedFunctionIds;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getAddRole() {
		return addRole;
	}

	public void setAddRole(Role addRole) {
		this.addRole = addRole;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public IGrade getGradeService() {
		return gradeService;
	}

	public void setGradeService(IGrade gradeService) {
		this.gradeService = gradeService;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public String getGradeIds() {
		return gradeIds;
	}

	public void setGradeIds(String gradeIds) {
		this.gradeIds = gradeIds;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}
}
