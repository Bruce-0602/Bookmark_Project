package com.bruce.bookmark_thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.bruce.bookmark_thrillio.constants.BookGenre;
import com.bruce.bookmark_thrillio.constants.KidFriendlyStatus;
import com.bruce.bookmark_thrillio.constants.MovieGenre;
import com.bruce.bookmark_thrillio.dao.BookmarkDao;
import com.bruce.bookmark_thrillio.entities.Book;
import com.bruce.bookmark_thrillio.entities.Bookmark;
import com.bruce.bookmark_thrillio.entities.Movie;
import com.bruce.bookmark_thrillio.entities.User;
import com.bruce.bookmark_thrillio.entities.UserBookmark;
import com.bruce.bookmark_thrillio.entities.WebLink;
import com.bruce.bookmark_thrillio.util.HttpConnect;
import com.bruce.bookmark_thrillio.util.IOUtil;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();// for Singleton pattern
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}// for Singleton pattern

	public static BookmarkManager getInstance() {// for Singleton pattern
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors, BookGenre genre,
			double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);

		return weblink;
	}

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);
		
		dao.saveUserBookmark(userBookmark);

	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		
		dao.updateKidFriendlyStatus(bookmark);
		
		System.out.println(
				"Kid-friendly status: " + kidFriendlyStatus + ", Marked by: " + user.getEmail() + ", " + bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);

		System.out.println("Data to be shared:");
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());

		} else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink) bookmark).getItemData());

		}
		
		dao.shareByInfo(bookmark);

	}
}
