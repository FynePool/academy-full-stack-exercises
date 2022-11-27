package com.bookstore.dao;

import java.util.*;
import com.bookstore.*;

public interface BookDao {
    List<Book> findAll();
    Optional<Book> findById(long id);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(Author author);
    Book saveOrUpdate(Book book);
}
