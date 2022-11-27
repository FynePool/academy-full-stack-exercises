package com.bookstore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.bookstore.Author;

@SpringBootApplication
@EntityScan(basePackageClasses = Author.class)
public class BookstoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApiApplication.class, args);
	}

}
