package com.bruce.bookmark_thrillio.dao;

import com.bruce.bookmark_thrillio.DataStore;
import com.bruce.bookmark_thrillio.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
