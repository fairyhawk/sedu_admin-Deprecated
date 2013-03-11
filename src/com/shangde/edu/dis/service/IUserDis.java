package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.edu.dis.condition.QueryUserDisCondition;
import com.shangde.edu.dis.domain.UserDis;

public interface IUserDis {

	public Integer addUserDis(UserDis userDis);

	public void delUserDisById();

	public void updateUserDis(UserDis userDis);

	public UserDis getUserDisById();

	public List<UserDis> getUserDisList(
			QueryUserDisCondition queryUserDisCondition);
}