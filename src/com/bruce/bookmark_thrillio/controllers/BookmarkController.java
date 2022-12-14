package com.bruce.bookmark_thrillio.controllers;

import com.bruce.bookmark_thrillio.constants.KidFriendlyStatus;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.User;
import com.bruce.bookmark_thrillio.managers.BookmarkManager;

public class BookmarkController {
	// Singleton Controller
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);

	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().share(user, bookmark);
		
	}
}
