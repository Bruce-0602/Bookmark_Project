package com.bruce.bookmark_thrillio.dao;

import java.util.List;

import com.bruce.bookmark_thrillio.DataStore;
import com.bruce.bookmark_thrillio.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
