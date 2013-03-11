package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.common.service.BaseService;
import com.shangde.edu.dis.condition.QueryUserDisCondition;
import com.shangde.edu.dis.domain.UserDis;

@SuppressWarnings("unchecked")
public class UserDisImpl extends BaseService implements IUserDis {
    public Integer addUserDis(UserDis userDis) {
        return simpleDao.createEntity("UserDis_NS.createUserDis", userDis);
    }

    public void delUserDisById() {
    }

    public void updateUserDis(UserDis userDis) {
        simpleDao.updateEntity("UserDis_NS.updateUserDis", userDis);
    }

    public UserDis getUserDisById() {
        return new UserDis();
    }

    public List<UserDis> getUserDisList(QueryUserDisCondition queryUserDisCondition) {
        return simpleDao.getForList("UserDis_NS.getUserDisList", queryUserDisCondition);
    }
    
    
    /*****V1.1方法(method)声明区域 开始*****/
	
	
	
	
	
	
	
	/*****V1.1方法(method)声明区域 结束*****/
}
