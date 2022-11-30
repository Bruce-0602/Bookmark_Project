package com.bruce.bookmark_thrillio.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bruce.bookmark_thrillio.DataStore;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.UserBookmark;
import com.bruce.bookmark_thrillio.entities.WebLink;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
	
	// In real application, would use SQL or hibernate queries
	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);
		
		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink)bookmark);
		}
		
		return result;
	}
	
	public List<WebLink> getWebLinks (WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();
		List<WebLink> allWebLinks = getAllWebLinks();
		
		for (WebLink webLink : allWebLinks) {
			if (webLink.getDownloadStatus().equals(downloadStatus)) {
				result.add(webLink);
			}
		}
		
		return result;
	}
	
}
