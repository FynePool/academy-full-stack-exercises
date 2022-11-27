package com.bookstore;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class BookStoreTest {

    @Test
    void testAddBook() {
        BookStore bookStore = new BookStore(true);
        bookStore.addBook(new Book.Builder(1, "Harry Potter")
                                    .author(new Author(1, "J.K.", "Rowling"))
                                    .category(BookCategory.ADVENTURE)
                                    .category(BookCategory.FANTASY)
                                    .build());
        bookStore.addBook(new Book.Builder(2, "Dieci Piccoli Indiani")
                                    .author(new Author(2, "Agatha", "Christie"))
                                    .price(new BigDecimal("12.99"))
                                    .build());

        assertEquals(2, bookStore.getBooks().size());
    }

    @Test
    void testRemoveBook() {
        BookStore bookStore = new BookStore(true);
        bookStore.addBook(new Book.Builder(1, "Harry Potter")
                                    .author(new Author(1, "J.K.", "Rowling"))
                                    .build());
        bookStore.addBook(new Book.Builder(2, "Dieci Piccoli Indiani")
                                    .author(new Author(2, "Agatha", "Christie"))
                                    .price(new BigDecimal("12.99"))
                                    .build());

        bookStore.removeBook(new Book.Builder(1, "Not important").build());

        assertEquals(1, bookStore.getBooks().size());
        assertEquals(2L, bookStore.getBooks().get(0).getIsbn());
    }

    @Test
    void testSearchBooksByTitle() {
        BookStore bookStore = new BookStore(true);
        bookStore.addBook(new Book.Builder(1, "Libro Java 1").build());
        bookStore.addBook(new Book.Builder(2, "Libro Python").build());
        bookStore.addBook(new Book.Builder(3, "Ancora un libro su java").build());

        List<Book> result = bookStore.searchBooksByTitle("Java");
        
        assertEquals(2, result.size());
    }

    @Test
    void testSearchBooksByAuthor() {
        BookStore bookStore = new BookStore(true);
        bookStore.addBook(new Book.Builder(1, "Libro Java 1")
                                    .author(new Author(1, "Primo", "Autore"))
                                    .author(new Author(2, "Secondo", "Autore"))
                                    .build());
        bookStore.addBook(new Book.Builder(2, "Libro Python")
                                    .author(new Author(2, "Secondo", "Autore"))
                                    .build());
        bookStore.addBook(new Book.Builder(3, "Ancora un libro su java")
                                    .author(new Author(1, "Primo", "Autore"))
                                    .build());

        
        ArrayList<Book> result = (ArrayList<Book>) bookStore.getBooks()
                                                            .stream()
                                                            .filter(ele -> ele.hasThisAuthor((long) 1))
                                                            .collect(Collectors.toList());
        
        assertEquals(2, result.size());
    }
}
