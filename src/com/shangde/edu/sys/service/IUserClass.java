package com.shangde.edu.sys.service;

import java.util.List;

import com.shangde.edu.sys.condition.QueryUserClassCondition;
import com.shangde.edu.sys.domain.UserClass;

/**
 * UserClass管理接口
 * @author guoqiang.liu
 */
public interface IUserClass {
    /**
     * 添加UserClass
     * @param userClass 要添加的UserClass
     */
    public void addUserClass(UserClass userClass);
    /**
     * 根据id删除一个UserClass
     * @param userId 要删除的id
     * @param classId 要删除的id
     */
    public void delUserClassById(int userId,int classId);

    /**
     * 修改UserClass
     * @param userClass 要修改的UserClass
     */
    public void updateUserClass(UserClass userClass);

    /**
     * 根据id获取单个UserClass对象
     * @param userId 要查询的id
     * @param classId 要查询的id
     * @return 年级
     */
    public UserClass getUserClassById(int userId,int classId);

    /**
     * 根据条件获取UserClass列表
     * @param queryUserClassCondition 查询条件
     * @return 查询结果
     */
    public List<UserClass> getUserClassList(QueryUserClassCondition queryUserClassCondition);
}