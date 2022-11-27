package com.bookstore.dao;

import java.util.*;
import com.bookstore.*;

public interface AuthorDao {

    public List<Author> findAll();
    public Optional<Author> findById(long id);
    public Author saveOrUpdate(Author publisher);
    
}
