package com.bookstore.dao;

import java.util.*;
import com.bookstore.*;

public interface PublisherDao {
    List<Publisher> findAll();
    Optional<Publisher> findById(long id);
    Publisher saveOrUpdate(Publisher publisher);
}
