package com.javamaster.service;

import com.javamaster.model.Role;
import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

interface UserService {
	void cleanTable() throws SQLException;
	void deleteId(int id) throws SQLException;
	void insertUser(String name, String password, Set<Role> roles) throws SQLException;
	long getUserId(String login) throws SQLException;
	List<User> getListUsers() throws SQLException;
	User getUser(String login) throws SQLException;
	User get(long id) throws SQLException;
}