package com.shangde.edu.sys.service;

import java.util.List;
import java.util.Set;

import com.shangde.edu.sys.condition.QueryRoleCondition;
import com.shangde.edu.sys.domain.Role;

/**
 * 角色管理接口
 * @author guoqiang.liu
 */
public interface IRole {
    /**
     * 添加角色
     * @param role 要添加的角色
     * @return id
     */
    public java.lang.Integer addRole(Role role);

    /**
     * 根据id删除一个角色
     * @param roleId 要删除的id
     */
    public void delRoleById(int roleId);

    /**
     * 修改角色
     * @param role 要修改的角色
     */
    public void updateRole(Role role);

    /**
     * 根据id获取单个角色对象
     * @param roleId 要查询的id
     * @return 年级
     */
    public Role getRoleById(int roleId);

    /**
     * 根据条件获取角色列表
     * @param queryRoleCondition 查询条件
     * @return 查询结果
     */
    public List<Role> getRoleList(QueryRoleCondition queryRoleCondition);

    /**
     * @return 角色列表
     */
    public List<Role> getScopeRoleList();
    
    /**
     * 更新某一角色的功能对应表
     * @param roleId 角色id
     * @param functionIdList 功能id列表
     */
    public void updateRoleFunction(int roleId, Set<Integer> functionIdList);
    /**
     * 得到role对象
     */
    public Role getRoleInfo(int userId,int roleId);
    /**
     * 教师用户角色与应用范围设置修改 
     */
    public void updateRefRole(String userId,String roleId,String gradeIds);
    /**
     * 添加用户角色(设置包括subject和grade)
     * @param userId
     * @param roleId
     * @param gradeIds
     */
    public void addRoleRef(String userId,String roleId,String gradeIds);
    /**
     * 删除用户角色(设置包括subject和grade)
     * @param userId
     * @param roleId
     * @param gradeIds
     */
    public void delRoleRef(String userId,String roleId);
    
    /**
     * 删除角色的所有roleRef
     * @param roleId
     */
    public void delUserGradeSubjectRoleByRole(int roleId);
}