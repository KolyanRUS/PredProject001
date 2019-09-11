package com.javamaster.service;

import com.javamaster.model.User;

import java.sql.SQLException;
import java.util.List;

interface UserService {
	void cleanTable() throws SQLException;
	void deleteId(int id) throws SQLException;
	void updateId(int id, String role, String name, String login, String password) throws SQLException;
	void insertUser(String role, String name, String password, String login) throws SQLException;
	long getUserId(String login) throws SQLException;
	List<User> getListUsers() throws SQLException;
	User getUser(String login) throws SQLException;
	User get(long id) throws SQLException;
}