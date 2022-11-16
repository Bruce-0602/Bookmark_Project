package com.bruce.bookmark_thrillio.dao;

import com.bruce.bookmark_thrillio.DataStore;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.UserBookmark;

public class BookmarkDao {
	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
		
	}
}
