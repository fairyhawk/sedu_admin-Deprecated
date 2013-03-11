package com.shangde.edu.test.service;

import com.shangde.edu.test.domain.User;

public interface IUser {
		public  java.lang.Integer addTestUser(User user);

	    public void delTestUserById(int userId);

	    public void updateTestUser(User user);
	    public User getTestUserById(int userId);
}
