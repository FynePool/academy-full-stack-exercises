package com.bookstore.dao.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.bookstore.*;
import java.math.*;
import com.bookstore.dao.*;

public class JdbcBookDao implements BookDao {

    private Connection conn;
    private PreparedStatement idStatement, findAllStatement/*, saveStatement, updateStatement*/;

    private final String FIND_BY_ID = "SELECT * FROM book WHERE id = ?";
    private final String FIND_ALL = "SELECT * FROM book";
    /*
    private final String SAVE_BOOK = "INSERT INTO book(title, price) VALUES(?, ?)";;
    private final String UPDATE_BOOK = "UPDATE book SET title = ?, price = ? WHERE id = ?";
     */
    private final String categoriesByBookIdQuery = "SELECT name FROM book_category WHERE id IN (SELECT book_category_id FROM book_book_category WHERE book_id = ?);";
    private final String authorsByBookIdQuery = "SELECT * FROM author WHERE id IN (SELECT author_id FROM book_author WHERE book_id = ?);";
    private final String booksByTitleQuery = "SELECT b.id, b.publisher_id, b.title, b.price, pub.name FROM book b JOIN publisher pub ON b.publisher_id = pub.id WHERE b.title like '%?%';";
    private final String booksByAuthorQuery = "SELECT b.id, b.publisher_id, b.title, b.price, pub.name FROM book b JOIN publisher pub ON b.publisher_id = pub.id WHERE b.id in (SELECT bau.book_id FROM author au join book_author bau on au.id = bau.author_id WHERE au.id = ?);"; 

    public JdbcBookDao(Connection conn) throws SQLException {
        this.conn = conn;
        prepareStatement();
    }

    private void prepareStatement() throws SQLException {
        idStatement = conn.prepareStatement(FIND_BY_ID);
        findAllStatement = conn.prepareStatement(FIND_ALL);
        /*
        saveStatement = conn.prepareStatement(SAVE_BOOK);
        updateStatement = conn.prepareStatement(UPDATE_BOOK);
        */
        System.out.println("Statement prepared");
    }

    @Override
    public List<Book> findAll() {
       List<Book> books = new ArrayList<>();
        try (ResultSet rs = findAllStatement.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                //String isbn = rs.getString("isbn");
                Date year = rs.getDate("year");
                books.add(new Book(id, title, null, price, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Optional<Book> findById(long id) {
        try (ResultSet rs = idStatement.executeQuery()) {
            while (rs.next()) {
                Optional.of(buildBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private Book buildBook(ResultSet res) throws SQLException {
        long bookId =  res.getLong("id");
        return new Book.Builder(bookId, res.getString("title"))
                        .authors(getAuthorsByBookId(bookId).stream().toArray(Author[]::new))
                        .categories(getCategoriesByBookId(bookId).stream().toArray(BookCategory[]::new))
                        .publisher(new Publisher(res.getLong("publisher_id"), res.getString("name")))
                        .price(res.getBigDecimal("price"))
                        .build();
    }
    private List<Author> getAuthorsByBookId(Long bookId) throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(authorsByBookIdQuery);) {
            pstmt.setLong(1, bookId);
            try (ResultSet res = pstmt.executeQuery();) {
                while (res.next()) {
                    authors.add(new Author(res.getLong("id"), res.getString("first_name"), res.getString("last_name")));
                }
            }
        } 
        return authors;
    }
    private SortedSet<BookCategory> getCategoriesByBookId(Long bookId) throws SQLException {
        SortedSet<BookCategory> categories = new TreeSet<>();
        try (PreparedStatement pstmt = conn.prepareStatement(categoriesByBookIdQuery);) {
            pstmt.setLong(1, bookId);
            try (ResultSet res = pstmt.executeQuery();) {
                while (res.next())
                    categories.add(BookCategory.getByValue(res.getString("name")));
            }
        } 
        return categories;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(booksByTitleQuery);) {
            pstmt.setString(1, title);
            try (ResultSet res = pstmt.executeQuery();) {
                while (res.next())
                    books.add(buildBook(res));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(booksByAuthorQuery);) {
            pstmt.setLong(1, author.getId());
            try (ResultSet res = pstmt.executeQuery();) {
                while (res.next())
                    books.add(buildBook(res));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public Book saveOrUpdate(Book book) {
        return null;
    }

}