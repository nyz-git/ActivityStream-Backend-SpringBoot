package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.User;

public interface UserDAO {

	//for registering a user
	public Boolean register(User user);

	//for validating a user aka LOGIN
	public User validate(User user);

	//for updating user
	public Boolean update(User user);

	//for deleting a user
	public Boolean delete(String emailid);

	//for fetching all information of user
	public List<User> getAllUsers();
	
	

}
