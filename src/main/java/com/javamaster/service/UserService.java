package com.javamaster.service;

import com.javamaster.model.AppUser;

import java.sql.SQLException;
import java.util.List;

interface UserService {
	void cleanTable() throws SQLException;
	void deleteId(int id) throws SQLException;
	void updateId(int id, String role, String name, String login, String password) throws SQLException;
	void insertUser(String role, String name, String password, String login) throws SQLException;
	long getUserId(String login) throws SQLException;
	List<AppUser> getListUsers() throws SQLException;
	AppUser getUser(String login) throws SQLException;
	AppUser get(long id) throws SQLException;
}