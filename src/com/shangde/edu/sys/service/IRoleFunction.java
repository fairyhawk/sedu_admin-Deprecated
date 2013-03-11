package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QueryRoleFunctionCondition;
import com.shangde.edu.sys.domain.RoleFunction;

/**
 * RoleFunction管理接口
 * @author guoqiang.liu
 */
public interface IRoleFunction {
    /**
     * 添加RoleFunction
     * @param roleFunction 要添加的RoleFunction
     */
    public void addRoleFunction(RoleFunction roleFunction);

    /**
     * 根据id删除一个RoleFunction
     * @param roleId 要删除的id
     * @param functionId 要删除的id
     */
    public void delRoleFunctionById(int roleId,int functionId);

    /**
     * 修改RoleFunction
     * @param roleFunction 要修改的RoleFunction
     */
    public void updateRoleFunction(RoleFunction roleFunction);

    /**
     * 根据id获取单个RoleFunction对象
     * @param roleId 要查询的id
     * @param functionId 要查询的id
     * @return 年级
     */
    public RoleFunction getRoleFunctionById(int roleId,int functionId);

    /**
     * 根据条件获取RoleFunction列表
     * @param queryRoleFunctionCondition 查询条件
     * @return 查询结果
     */
    public List<RoleFunction> getRoleFunctionList(QueryRoleFunctionCondition queryRoleFunctionCondition);
}