package com.bookstore.dao;

public interface DaoFactory extends AutoCloseable {
    BookDao getBookDao();
    AuthorDao getAuthorDao();
    PublisherDao getPublisherDao();
}
