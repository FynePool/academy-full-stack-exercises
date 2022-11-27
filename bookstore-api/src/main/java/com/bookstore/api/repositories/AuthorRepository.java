package com.bookstore.api.repositories;

import org.springframework.data.repository.CrudRepository;
import com.bookstore.*;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}