package com.bruce.bookmark_thrillio;

import java.util.ArrayList;
import java.util.List;

import com.bruce.bookmark_thrillio.constants.BookGenre;
import com.bruce.bookmark_thrillio.constants.Gender;
import com.bruce.bookmark_thrillio.constants.MovieGenre;
import com.bruce.bookmark_thrillio.constants.UserType;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.User;
import com.bruce.bookmark_thrillio.entities.UserBookmark;
import com.bruce.bookmark_thrillio.managers.BookmarkManager;
import com.bruce.bookmark_thrillio.managers.UserManager;
import com.bruce.bookmark_thrillio.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>(); // diamond notation will take care of RHS
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();

	}

	private static void loadUsers() {
		// String[] data = new String[TOTAL_USER_COUNT];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User");

		for (String row : data) {
			String[] values = row.split("\t");

			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3],
					values[4], gender, UserType.valueOf(values[6]));
			users.add(user);
		}
	}

	private static void loadWebLinks() {
//		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "WebLink");

		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3]/* , values[4] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		// String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie");

		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1], "",
					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks() {
		// String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);

	}
}
