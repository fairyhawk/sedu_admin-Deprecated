package com.shangde.edu.crm.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.domain.Users;
import com.shangde.edu.crm.condition.QueryUsersCondition;
import com.shangde.edu.stu.condition.QueryPlanCondition;

/**
 * User管理接口
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
public interface IUsers {
    /**
     * 添加User
     * @param user 要添加的User
     * @return id
     */
    public java.lang.Integer addUser(Users user);

    /**
     * 根据id删除一个User
     * @param id 要删除的id
     */
    public void delUserById(int id);

    /**
     * 修改User
     * @param user 要修改的User
     */
    public void updateUser(Users user);

    /**
     * 根据id获取单个User对象
     * @param id 要查询的id
     * @return 年级
     */
    public Users getUserById(int id);

    /**
     * 根据条件获取User列表
     * @param queryUserCondition 查询条件
     * @return 查询结果
     */
    public List<Users> getUserList(QueryUsersCondition queryUserCondition);
    
    /**
     *   范昕
     * 查询全部User列表 且做分页处理
     * Date : 2011-11-05 16:49:16
     * @param QueryUsersCondition 查询对象
     * @return 分页对象
     */
    public PageResult getUsersBackPaperByCondition(QueryUsersCondition queryUserCondition);
    
    /**
     *   范昕
     * 查询全部User列表 且做分页处理
     * Date : 2011-11-05 16:49:16
     * @param QueryUsersCondition 查询对象
     * @return 分页对象
     */     
    public PageResult searchUserListByParamCondition(QueryUsersCondition queryUserCondition);
    
    /**
     * 范昕
     * 根据id获取单个User对象
     *  Date : 2011-11-07 16:49:16
     */
    public List<Users> getUserByMobile(String mobile);
    
    /**
     * @author 王超
     * 获取所有自然留言状态用户
     * @return
     */
    public PageResult getAllMsgList(QueryUsersCondition queryUsersCondition);
}