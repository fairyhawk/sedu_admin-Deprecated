package com.shangde.edu.crm.service;

import java.util.List;
import com.shangde.edu.crm.domain.Users;
import com.shangde.edu.crm.condition.QueryUsersCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 *Users 接口管理
 *User:guoqiang.liu
 *Date:2011-11-03 
 */
@SuppressWarnings("unchecked")
public class UsersImpl extends BaseService implements IUsers{
    public java.lang.Integer addUser(Users user) {
return simpleDao.createEntity("Users_NS.createUser",user);
    }

    public void delUserById(int id){
        simpleDao.deleteEntity("Users_NS.deleteUserById",id);
    }

    public void updateUser(Users user) {
        simpleDao.updateEntity("Users_NS.updateUser",user);
    }

    public Users getUserById(int id) {
        return simpleDao.getEntity("Users_NS.getUserById",id);
    }

    public List<Users> getUserList(QueryUsersCondition queryUserCondition) {
        return simpleDao.getForList("Users_NS.getUserList",queryUserCondition);
    }
    
    /**
     *   范昕
     * 查询全部User列表 且做分页处理
     * Date : 2011-11-05 16:49:16
     * @param QueryUsersCondition 查询对象
     * @return 分页对象
     */
    public PageResult getUsersBackPaperByCondition(QueryUsersCondition queryUserCondition){
    	return simpleDao.getPageResult("Users_NS.getBackUserList", "Users_NS.getBcakUserListCount", queryUserCondition);
    }
    
    /**
     *   范昕
     * 查询全部User列表 且做分页处理
     * Date : 2011-11-05 16:49:16
     * @param QueryUsersCondition 查询对象
     * @return 分页对象
     */     
    public PageResult searchUserListByParamCondition(QueryUsersCondition queryUserCondition){
    	return simpleDao.getPageResult("Users_NS.searchUserByParam", "Users_NS.searchUserByParamCount", queryUserCondition);
    }
    /**
     * 范昕
     * 根据id获取单个User对象
     *  Date : 2011-11-07 16:49:16
     */
    public List<Users> getUserByMobile(String mobile){
    	return simpleDao.getForList("Users_NS.getUserByMobile",mobile);
    }
    
    /**
     * @author 王超
     * 获取所有自然留言状态用户
     * @return
     */
    public PageResult getAllMsgList(QueryUsersCondition queryUsersCondition){
    	return simpleDao.getPageResult("Users_NS.getAllMsgList", "Users_NS.getAllMsgListCount", queryUsersCondition);
    }
}
